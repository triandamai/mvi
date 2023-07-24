# Android MVI
[![](https://jitpack.io/v/triandamai/mvi.svg)](https://jitpack.io/#triandamai/mvi)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Memulai
Add to `build.gradle` or `build.gradle.kts`:
1. root project gradle `build.gradle.kts`:
```kotlin
allprojects {
    repositories {
        maven(url = "https://jitpack.io")
    }
}
```
2. in your application module `build.gradle` or `build.gradle.kts`:
```groovy
plugins{
    id("com.google.devtools.ksp") version ("1.8.0-1.0.9")
}
dependencies {
    implementation("com.github.triandamai.mvi:ui:<version>")
    implementation("com.github.triandamai.mvi:processor:<version>")
    ksp("com.github.triandamai.mvi:processor:<version>")
}
```

# Example

### First Page/Route
```kotlin
@Navigation(
    route="first-page",
    viewModel=ListQuizViewModel::class
)
@Composable
fun DetailQuizScreen(
    uiContract: UIContract<ListQuizState, ListQuizAction>
) = UiWrapper(uiContract) { 
    Button(
        onClick={
            //navigate to second page with params
            navigator.navigate("second-page","World")
        }
    ){ Text("Send to Second Page") }
}
```
### Halaman Kedua:
```kotlin
@Navigation(
    route="second-page",
    viewModel=DetailQuizViewModel::class
)
@Argument(name="quizName", type=NavType.StringType)
@Composable
fun DetailQuizScreen(
    uiContract: UIContract<DetailQuizState,DetailQuizAction>
) = UiWrapper(uiContract) {
    LaunchedEffect(this){
        val arg = navigator.arguments.get<String>("quizName")
        commit{copy(message=arg)}
    }
    Text("Hello ${state.message}")
}
```
### Use in `MainActivity`:
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiController = rememberUIController(event = EventListener())
            
            QuizTheme {
                NavHost(controller,"first-page") {
                    //add this line
                    //this file are generated from annotation processor
                    quizComponent(uiController = uiController)
                }
            }
        }
    }
}
```
### ViewModel:
```kotlin
@HiltViewModel
class ListQuizViewModel @Inject constructor(
    private val getListQuizUseCase: GetListQuizUseCase
) : MviViewModel<ListQuizState, ListQuizAction>(
    ListQuizState(),
) {
    init {
        getListQuiz()
    }

    private fun getListQuiz() = async {
        getListQuizUseCase()
            .onEach(
                loading = {},
                error = { _, _ -> },
                success = {},
                empty = {}
            )
    }

    override fun onAction(action: ListQuizAction) {
        when (action) {
            ListQuizAction.Nothing -> {
                
            }
        }
    }

}
```

This project doesn't mean to replace existing architecture, and work seamless with the existing:
1. Reduce boiler plate
2. Better testing (Screen/Page can be tested separately from ViewModel)

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
