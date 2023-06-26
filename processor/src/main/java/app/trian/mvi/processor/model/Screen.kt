package app.trian.mvi.processor.model

data class Screen(
    val locationPackage: String,
    val name: String,
    val uiContract: ScreenDependencies,
    val eventContract: ScreenDependencies?
)
