package com.example.gfood.data.source.remote.fetchjson

import android.os.AsyncTask
import com.example.gfood.data.source.remote.OnFetchDataJsonListener
import com.example.gfood.utils.KeyEntity
import java.lang.Exception

@Suppress("DEPRECATION", "UNCHECKED_CAST")
class GetJsonFromUrl<T> constructor(
    private val keyEntity: KeyEntity,
    private val listener: OnFetchDataJsonListener<T>

) : AsyncTask<String?, Unit?, String?>() {
    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJsonFromUrl(params[0])

        } catch (e: Exception) {
            this.exception = e
        }
        return data
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null && !result.isBlank()) {
            val data = ParseDataWithJson().parseJson(result, keyEntity)
            data.let {
                listener.onSuccess(it as T)
            }
        } else listener.onError(exception)
    }
}
