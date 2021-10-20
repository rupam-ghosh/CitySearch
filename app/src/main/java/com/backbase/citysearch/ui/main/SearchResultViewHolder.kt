package com.backbase.citysearch.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.citysearch.R
import com.backbase.citysearch.models.City

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView
    private val subtitle: TextView
    fun setCity(position: Int, city: City) {
        title.text = String.format("%s, %s", city.name, city.country)
        subtitle.text = String.format("%s, %s", city.coord!!.lat, city.coord!!.lon)
        itemView.tag = position
    }

    init {
        title = itemView.findViewById(R.id.title)
        subtitle = itemView.findViewById(R.id.subtitle)
    }
}