# GOAL 
Repository ini bertujuan untuk membuat annotation processor yang dapat meng generate boilerplate ketike membuat aplikasi

*Asumsikan kita memiliki Design Pattern MVI

Untuk membuat fitur/halaman cukup dengan mendeklasaikan sebuah fungsi `@Composable` dengan anotasi `@Page` seperti berikut:
```kotlin
@Page(
    route="halaman-pertama",
    viewModel=DetailQuizViewModel::class
)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<DetailQuizState, DetailQuizEvent>
) = UiWrapper(uiEvent) {
    //content
    Button(
        onClick={
            //mutasi state
            commit { copy(count = state.count+1) } 
        }
    ){
        Text("${state.count}")
    }

    Button(
        onClick={
            router.navigate("halaman-kedua",state.count)
        }
    ){
        Text("Ke halaman Kedua")
    }
}
```

```kotlin
@Page(
    route="halaman-kedua/{id}",
    arguments=navArgument("id"){type=NavType.StringType},
    viewModel=DetailQuizViewModel::class
)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListener<DetailQuizState, DetailQuizEvent>
) = UiWrapper(uiEvent) {
    LaunchedEffect(this){
        val arg = router.arguments.get<String>("id")
        commit{copy(message=arg)}
    }

    Button(
        onClick={
            //action
            dispatch(DetailQuizEvent.ChangeMessage("World"))
        }
    ){
        Text("Hello ${state.message}")
    }
}
```

Kita tidak perku mendefinisikan NavHost, initialize ViewModel dengan otomatis akan men-generate routing beserta konfigurasinya di belakang layar
dan cukup dengan menulis di `MainActivity`:
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var uiController: UIController<EventListener>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            uiController = rememberUIController(
                event = EventListener()
            )
            BaseMainApp(uiController) {
                AppNavigation.routes(uiController = uiController)
            }
        }
    }
    
}
```

Project ini tidak akan mereplace arsitektur yang sudah ada seperti hilt,navigation component dll,
project ini dibuat diatas arsitektur yang sudah ada dan akan men-cover beberapa permasalahan:
1. Mengurangi boilerplate code
2. Mengurangi painfull konfigurasi
3. Pengujian yang lebih baik dan terisolasi antara UI dan Unit Test
4. membuat sebuah rule yang memudahkan proses development dengan tim yang scalable