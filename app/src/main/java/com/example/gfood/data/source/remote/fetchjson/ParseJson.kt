package com.example.gfood.data.source.remote.fetchjson

import com.example.gfood.data.model.Category
import com.example.gfood.data.model.CategoryEntry
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
}
