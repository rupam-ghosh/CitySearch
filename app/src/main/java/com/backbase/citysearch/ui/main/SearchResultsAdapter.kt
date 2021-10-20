package com.backbase.citysearch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.citysearch.R
import com.backbase.citysearch.models.City

class SearchResultsAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {
    var searchResult: List<City>? = null
    var onClickListener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        inflatedView.setOnClickListener(onClickListener)
        return SearchResultViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.setCity(position, searchResult!![position])
    }

    override fun getItemCount(): Int {
        return if (searchResult != null) searchResult!!.size else 0
    }
}