package com.arjun.qoutesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.arjun.todoapplication.R

class MainActivity : AppCompatActivity() {

    lateinit var text : TextView
    lateinit var mainViewModel: MainViewModel

    private val quoteText : TextView
        get() = findViewById(R.id.quoteText)

    private val authorText : TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(Observer())
        Log.d("Observer","onCreateMainActivity")

        text = findViewById(R.id.countView)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setText()
        setQuotes(mainViewModel.getQuote())
    }

    private fun setText() {
        text.text = mainViewModel.index.toString()
    }

    fun setQuotes(quote : QuoteData){
        quoteText.text  = quote.text
        authorText.text = quote.author
    }

    fun onNext(v: View){
        setQuotes(mainViewModel.nextQuote())
        setText()
    }
    fun onPrevious(view: View) {
        setQuotes(mainViewModel.previousQuote())
        setText()
      }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }
}