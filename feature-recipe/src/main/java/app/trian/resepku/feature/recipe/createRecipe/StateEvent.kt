/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe.createRecipe

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.CookingStep
import com.bluehabit.budgetku.data.model.Ingredient
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreateRecipeState(
    val visibleBottomBar: Boolean = true,
    val step: Int = 0,

    val dataIngredient: @RawValue List<Ingredient> = listOf(),
    val dataCookingStep: @RawValue List<CookingStep> = listOf()
) : Parcelable

@Immutable
@Parcelize
data class CreateRecipeDataState(
    val a: String = ""
) : Parcelable

sealed interface CreateRecipeEvent {
    //page
    data class ChangeStep(val isNextStep: Boolean) : CreateRecipeEvent

    //cooking step
    object AddNewCookingStep: CreateRecipeEvent
    data class ReorderCookingStep(val from: Int, val to: Int) : CreateRecipeEvent
    data class ChangeCookingStep(val data: CookingStep) : CreateRecipeEvent
    data class RemoveCookingStep(val id: String) : CreateRecipeEvent

    //ingredient
    object AddNewIngredient : CreateRecipeEvent
    data class ReorderIngredient(val from: Int, val to: Int) : CreateRecipeEvent
    data class ChangeIngredient(val data: Ingredient) : CreateRecipeEvent
    data class RemoveIngredient(val id: String) : CreateRecipeEvent

}