package app.trian.mvi.processor.model

import com.squareup.kotlinpoet.MemberName

data class ScreenDependencies(
    val memberName: MemberName,
    val type:String,
    val value:String
)
