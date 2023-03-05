package com.arjun.qoutesapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {

    var quoteList : Array<QuoteData> = emptyArray()
    var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<QuoteData> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<QuoteData>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() =
        if (index < quoteList.size -1) {
               quoteList[++index]
        }else{
            index = -1
            quoteList[++index]
        }

    fun previousQuote() =
        if (index > 0) {
            quoteList[--index]
        } else {
            index = quoteList.size
            quoteList[--index]
        }
}