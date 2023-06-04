/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.createRecipe

import app.trian.resepku.base.BaseViewModelData
import app.trian.resepku.data.model.CookingStep
import app.trian.resepku.data.model.Ingredient
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
) : BaseViewModelData<CreateRecipeState, CreateRecipeDataState, CreateRecipeEvent>(
    CreateRecipeState(),
    CreateRecipeDataState()
) {
    init {
        handleActions()
    }

    //page
    private fun calculatePage(
        isNext: Boolean
    ) = asyncWithState {
        //go to next screen
        if (isNext) {
            val gotToNextPage = if (step < 4) step + 1 else step
            commit {
                copy(
                    step = gotToNextPage,
                    visibleBottomBar = gotToNextPage != 3
                )
            }
            return@asyncWithState
        }

        //close when success screen
        if (step == 3) {
            navigateUp()
            return@asyncWithState
        }

        //confirmation when at the beginning screen
        if (step == 0) {
            showBottomSheet()
            return@asyncWithState
        }

        //onBackPressed
        val goToPreviousPage = step - 1
        commit {
            copy(
                step = goToPreviousPage,
                visibleBottomBar = goToPreviousPage != 3
            )
        }
    }

//cooking step


    private fun reorderCookingStep(from: Int, to: Int) = asyncWithState {
        if (from >= dataCookingStep.size || to >= dataCookingStep.size) return@asyncWithState
        val data = dataCookingStep.toMutableList().apply {
            add(to, removeAt(from))
        }
        commit {
            copy(
                dataCookingStep = data
            )
        }
    }

    private fun addNewCookingStep() = asyncWithState {
        commit {
            copy(
                dataCookingStep = dataCookingStep.toMutableList().plus(
                    CookingStep(
                        id = UUID.randomUUID().toString(),
                        value = ""
                    )
                )
            )
        }
    }

    private fun changeCookingStep(
        data: CookingStep
    ) = asyncWithState {

        val findIndex = dataCookingStep
            .withIndex()
            .first { (_, value) -> value.id == data.id }
            .index

        if (findIndex != -1) {
            val cookingSteps = dataCookingStep.toMutableList()
            cookingSteps[findIndex] = data
            commit {
                copy(
                    dataCookingStep = cookingSteps
                )
            }
        }
    }

    private fun removeCookingStep(ingredientId: String) = asyncWithState {
        val findIndex = dataCookingStep
            .withIndex()
            .first { (_, value) -> value.id == ingredientId }
            .index

        if (findIndex != -1) {
            val cookingSteps = dataCookingStep.toMutableList()
            cookingSteps.removeAt(findIndex)
            commit { copy(dataCookingStep = cookingSteps) }
        }
    }

//end

    //ingredient
    private fun reorderIngredient(from: Int, to: Int) = asyncWithState {
        if (from >= dataIngredient.size || to >= dataIngredient.size) return@asyncWithState
        val data = dataIngredient.toMutableList().apply {
            add(to, removeAt(from))
        }
        commit {
            copy(
                dataIngredient = data
            )
        }
    }

    private fun addNewPlainIngredient() = asyncWithState {
        commit {
            copy(
                dataIngredient = dataIngredient.toMutableList().plus(
                    Ingredient(
                        id = UUID.randomUUID().toString(),
                        value = ""
                    )
                )
            )
        }
    }

    private fun changeIngredient(
        data: Ingredient
    ) = asyncWithState {

        val findIndex = dataIngredient
            .withIndex()
            .first { (_, value) -> value.id == data.id }
            .index

        if (findIndex != -1) {
            val ingredients = dataIngredient.toMutableList()
            ingredients[findIndex] = data
            commit {
                copy(
                    dataIngredient = ingredients
                )
            }
        }
    }

    private fun removeIngredient(ingredientId: String) = asyncWithState {
        val findIndex = dataIngredient
            .withIndex()
            .first { (_, value) -> value.id == ingredientId }
            .index

        if (findIndex != -1) {
            val ingredients = dataIngredient.toMutableList()
            ingredients.removeAt(findIndex)
            commit { copy(dataIngredient = ingredients) }
        }
    }

//end


    override fun handleActions() = onEvent { event ->
        when (event) {
            is CreateRecipeEvent.ChangeStep -> calculatePage(event.isNext)

            CreateRecipeEvent.AddNewCookingStep -> addNewCookingStep()
            is CreateRecipeEvent.ReorderCookingStep -> reorderCookingStep(event.from, event.to)
            is CreateRecipeEvent.ChangeCookingStep -> changeCookingStep(event.data)
            is CreateRecipeEvent.RemoveCookingStep -> removeCookingStep(event.id)

            CreateRecipeEvent.AddNewIngredient -> addNewPlainIngredient()
            is CreateRecipeEvent.ReorderIngredient -> reorderIngredient(event.from, event.to)
            is CreateRecipeEvent.ChangeIngredient -> changeIngredient(event.data)
            is CreateRecipeEvent.RemoveIngredient -> removeIngredient(event.id)

        }
    }

}