package app.trian.mvi.processor.model

import com.squareup.kotlinpoet.MemberName

enum class DependenciesType {
    EVENT,
    CONTRACT,
    OTHER
}

data class ScreenDependencies(
    val memberName: MemberName,
    val dependenciesType: DependenciesType,
    val value: String,
    val parameterName: String
)
