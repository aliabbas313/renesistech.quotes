package com.renesistech.quotes.mvp.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.renesistech.quotes.R

class QuotesAdapter(val mcontext: Context, val ldata: ArrayList<QuoteListModel>) : RecyclerView.Adapter<QuotesAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val inflater: LayoutInflater = LayoutInflater.from(mcontext)
        val view = inflater.inflate(R.layout.item_quote, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return ldata.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val modeldata: QuoteListModel =ldata[position]
        holder.tv_fragment1.text=modeldata.data
        holder.tv_fragment2.text=modeldata.data2
        holder.tv_fragment3.text=modeldata.data3
    }

    inner class Viewholder (itemview: View) : RecyclerView.ViewHolder(itemview) {

        val tv_fragment1: TextView = itemview.findViewById(R.id.tv_fragment1)
        val tv_fragment2: TextView = itemview.findViewById(R.id.tv_fragment2)
        val tv_fragment3: TextView = itemview.findViewById(R.id.tv_fragment3)

    }

}