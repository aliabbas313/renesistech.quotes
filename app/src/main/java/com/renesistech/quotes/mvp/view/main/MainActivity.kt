package com.renesistech.quotes.mvp.view.main

import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.renesistech.quotes.R
import com.renesistech.quotes.mvp.data.local.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var quotesAdapter: QuotesAdapter
    var mydb: DatabaseHelper? = null
    var cacheData: ArrayList<QuoteListModel> = ArrayList<QuoteListModel>()
    lateinit var quoteListModel: QuoteListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        FirebaseMessaging.ion().setAutoInitEnabled(false);
        mydb = DatabaseHelper(applicationContext)

        val cursor: Cursor? = mydb!!.getTeamNameEmail()

        while (!cursor!!.isAfterLast) {
            quoteListModel = QuoteListModel("Funny Quotes",cursor.getString(0),cursor.getString(1))
            cacheData.add(quoteListModel)
            cursor.moveToNext()
        }
        view_pager.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        quotesAdapter = QuotesAdapter(applicationContext, cacheData)
        view_pager.adapter = quotesAdapter
    }
}