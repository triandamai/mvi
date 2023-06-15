# GOAL 
Repository ini bertujuan untuk membuat annotation processor yang dapat meng generate boilerplate ketike membuat aplikasi

*Asumsikan kita memiliki Design Pattern MVI

Untuk membuat fitur/halaman cukup dengan mendeklasaikan sebuah fungsi `@Composable` dengan anotasi `@Page` seperti berikut:
```kotlin
@Page(
    route="Routing",
    arguments=navArgument("id"){type=NavType.StringType},
    viewModel=DetailQuizViewModel::class
)
@Composable
internal fun ScreenDetailQuiz(
    uiEvent: UIListenerData<DetailQuizState, DetailQuizDataState, DetailQuizEvent, AREventListener>
) = UiWrapperData(uiEvent) {
    
    BaseScreen(
        controller = controller,
        bottomSheet = {
           
        }
    ) {
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
                //action
                dispatch(DetailQuizEvent.ChangeMessage("World"))
            }
        ){
            Text("Hello ${state.message}")
        }
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