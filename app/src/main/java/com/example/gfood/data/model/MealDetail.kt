package com.example.gfood.data.model

data class MealDetail(
    val id: String?,
    val title: String?,
    val instructions: String?,
    val linkVideo: String?,
    val listIngredient: MutableList<String>?
)

object MealDetailEntry {
    const val ID = "idMeal"
    const val TITLE = "strMeal"
    const val INSTRUCTION = "strInstructions"
    const val LINK_VIDEO = "strYoutube"
    const val INGREDIENT = "strIngredient"
    const val MEAL_DETAIL = "meals"
}
