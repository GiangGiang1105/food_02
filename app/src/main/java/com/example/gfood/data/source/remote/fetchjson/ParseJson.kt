package com.example.gfood.data.source.remote.fetchjson

import com.example.gfood.data.model.*
import org.json.JSONObject

class ParseJson {

    fun parseJsonToCategory(jsonObject: JSONObject?): Category =
        jsonObject.run {
            Category(
                id = jsonObject?.getString(CategoryEntry.ID),
                name = jsonObject?.getString(CategoryEntry.NAME),
                image = jsonObject?.getString(CategoryEntry.IMAGE),
                description = jsonObject?.getString(CategoryEntry.DESCRIPTION)
            )
        }

    fun parseJsonToMeal(jsonObject: JSONObject?): Meal =
        jsonObject.run {
            Meal(
                id = jsonObject?.getString(MealEntry.ID),
                title = jsonObject?.getString(MealEntry.TITLE),
                image = jsonObject?.getString(MealEntry.IMAGE)
            )
        }

    fun parseJsonToMealDetail(jsonObject: JSONObject?): MealDetail {
        val listIngredient = mutableListOf<String>()
        for (n in 1..10) {
            jsonObject?.getString(MealDetailEntry.INGREDIENT + n)?.let {
                if (it != null && it.isEmpty()) listIngredient.add(it)
            }
        }
        return jsonObject.run {
            MealDetail(
                id = jsonObject?.getString(MealDetailEntry.ID),
                title = jsonObject?.getString(MealDetailEntry.TITLE),
                listIngredient = listIngredient,
                linkVideo = jsonObject?.getString(MealDetailEntry.LINK_VIDEO),
                instructions = jsonObject?.getString(MealDetailEntry.INSTRUCTION)
            )
        }
    }
}
