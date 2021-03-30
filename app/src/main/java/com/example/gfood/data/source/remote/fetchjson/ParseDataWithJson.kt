package com.example.gfood.data.source.remote.fetchjson

import com.example.gfood.data.model.CategoryEntry
import com.example.gfood.utils.KeyEntity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {
    fun getJsonFromUrl(urlString: String?): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.apply {
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = REST_GET
            doOutput = true
            connect()
        }

        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuffer = StringBuffer()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuffer.append(line)
        }
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuffer.toString()
    }

    private fun parseJsonToData(jsonArray: JSONArray, keyEntity: KeyEntity): Any {
        val data = mutableListOf<Any>()
        try {
            for (i in 0 until (jsonArray.length() ?: 0)) {
                val jsonObjects = jsonArray.getJSONObject(i)
                val item = ParseDataWithJson().parseJsonToObject(jsonObjects, keyEntity)
                item?.let { data.add(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun parseJsonToObject(jsonObject: JSONObject, keyEntity: KeyEntity): Any? {
        val parseJson = ParseJson()
        return when (keyEntity) {
            KeyEntity.CATEGORY -> {
                parseJson.parseJsonToCategory(jsonObject = jsonObject)
            }
            else -> null
        }
    }

    fun parseJson(jsonString: String, keyEntity: KeyEntity): Any? =
        try {
            when (keyEntity) {
                KeyEntity.CATEGORY -> {
                    parseJsonToData(
                        jsonArray = JSONObject(jsonString).getJSONArray(CategoryEntry.CATEGORY),
                        keyEntity
                    )
                }
                else -> null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    companion object {
        private const val TIME_OUT = 10000
        private val REST_GET: String = "GET"
    }
}
