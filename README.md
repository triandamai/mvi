# Android MVI
[![](https://jitpack.io/v/triandamai/mvi.svg)](https://jitpack.io/#triandamai/mvi)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Memulai
Anda dapat mencoba dengan menambahkan dependencies pada `build.gradle` atau `build.gradle.kts`:
1. root project gradle `build.gradle`:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
2. app `build.gradle`:
```groovy
plugins{
    id 'com.google.devtools.ksp' version '1.8.0-1.0.9'
}
dependencies {
    implementation 'com.github.triandamai.mvi:ui:<version>'
    implementation 'com.github.triandamai.mvi:processor:<version>'
    ksp 'com.github.triandamai.mvi:processor:<version>'
}
```

# Why?
Project ini bertujuan untuk mengurangi boilerplate dan konfigurasi ketika membuat aplikasi, dengan memberikan solusi berupa `prebuild configuration` dan `annotation processor`.

Sebagai contoh untuk membuat sebuah halaman cukup dengan mendeklasaikan sebuah fungsi `@Composable` dengan anotasi `@Navigation`,`Argument` seperti berikut:

### Halaman Pertama

```kotlin
@Navigation(
    route="halaman-pertama",
    viewModel=ListQuizViewModel::class
)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<ListQuizState, ListQuizEvent>
) = UiWrapper(uiEvent) { //utility untuk men-dsl uiEvent
    Button(
        onClick={
            //navigasi
            router.navigate("halaman-kedua","World")
        }
    ){ Text("Ke halaman Kedua") }
}
```
### Halaman Kedua:
```kotlin
@Navigation(
    route="halaman-kedua",
    viewModel=DetailQuizViewModel::class
)
@Argument(name="quizName", type=NavType.StringType)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<DetailQuizState, DetailQuizEvent>
) = UiWrapper(uiEvent) { //utility untuk men-dsl uiEvent
    LaunchedEffect(this){
        val arg = backStackEntry.arguments.get<String>("quizName")
        commit{copy(message=arg)}
    }
    Text("Hello ${state.message}")
}
```
Processor akan mencari halaman dengan anotasi `Navigation` dan membuat component sesuai dengan nama module yang kemudian di pakai pada entry point  `MainActivity`:
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiController = rememberUIController(event = EventListener())
            
            QuizTheme {
                NavHost(controller,"halaman-pertama") {
                    quizComponent(uiController = uiController)
                }
            }
        }
    }
}
```

# MVI(Model View Intent)

## Model
```kotlin
@Immutable
@Parcelize
data class StartQuizState(
    val showContent:Boolean=false,
    val quizId:String=""
) :  Parcelable
```

## Event
```kotlin
sealed interface StartQuizEvent {
    object ShowResult:StartQuizEvent
}
```

## ViewModel
```kotlin

@HiltViewModel
class StartQuizViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val getQuizQuestionUseCase: GetQuizQuestionUseCase
) : BaseViewModelData<StartQuizState, StartQuizEvent>(
    context,
    DetailQuizState(),
    DetailQuizDataState()
) {
    init {
        handleActions()
        getQuiz()
    }

    private fun quizId(): String = savedStateHandle.get<String>(StartQuiz.argKey).orEmpty()

    private fun getQuiz() = async {
        getQuizQuestionUseCase(quizId())
            .onEach(
                loading = {},
                error = {},
                success = {
                    commit { copy(questions=it) }
                }
            )
    }

    override fun handleActions() = onEvent { event->
        when(event){
            StartQuizEvent.ShowResult->{
                //show
            }
        }
    }

}
```


Project ini tidak untuk menggantikan arsitektur yang sudah ada seperti hilt,navigation component dll,
tetapi ini dibuat diatas arsitektur yang sudah ada dan akan men-cover beberapa permasalahan:
1. Mengurangi boilerplate code
2. Mengurangi konfigurasi yang berlebihan
3. Pengujian yang lebih baik
4. Memudahkan proses `Preview` dan LiveEdit
5. membuat sebuah rule atau aturan untuk memudahkan kolaborasi dengan tim

# LICENSE

```text
MIT License

Copyright (c) 2023 trian damai

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
