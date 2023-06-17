# Why?
Project ini bertujuan untuk  mengurangi boilerplate dan konfigurasi ketika membuat aplikasi, dengan memberikan solusi berupa prebuild konfiguration dan `annotation processor`.

Sebagai contoh untuk membuat sebuah halaman cukup dengan mendeklasaikan sebuah fungsi `@Composable` dengan anotasi `@Navigation` seperti berikut:

## Halaman Pertama

```kotlin
@Navigation(
    route="halaman-pertama",
    viewModel=DetailQuizViewModel::class
)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<DetailQuizState, DetailQuizEvent>
) = UiWrapper(uiEvent) { //utility untuk men-dsl uiEvent
    //content
    Button(
        onClick={
            //mutasi state
            commit { copy(count = state.count+1) } 
        }
    ){ Text("${state.count}") }

    Button(
        onClick={
            //navigasi
            router.navigate("halaman-kedua",state.count)
        }
    ){ Text("Ke halaman Kedua") }
}
```
## Halaman Kedua:

```kotlin
@Navigation(
    route="halaman-kedua",
    viewModel=DetailQuizViewModel::class
)
@Argument(name="id", type=NavType.StringType)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<DetailQuizState, DetailQuizEvent>
) = UiWrapper(uiEvent) { //utility untuk men-dsl uiEvent
    LaunchedEffect(this){
        val arg = backStackEntry.arguments.get<String>("id")
        commit{copy(message=arg)}
    }

    Button(
        onClick={
            //action
            dispatch(DetailQuizEvent.ChangeMessage("World"))
        }
    ){ Text("Hello ${state.message}") }
}
```

Pada entry point  `MainActivity`:
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var uiController: UIController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            uiController = rememberUIController(event = EventListener())
            
            BaseMainApp(uiController) {
                NavHost(controller,"halaman-pertama") {
                    quizComponent(uiController = uiController)
                }
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