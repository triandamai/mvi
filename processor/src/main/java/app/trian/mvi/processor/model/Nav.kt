package app.trian.mvi.processor.model

data class Nav(
    val route: String,
    val parent: String = "",
    val group: String = "",
    val arguments: List<NavArgument> = listOf(),
    val deepLink: List<String> = listOf(),
    val screen: Screen,
    val viewModel: ViewModel
)