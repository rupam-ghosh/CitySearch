package com.backbase.citysearch.models

import com.backbase.citysearch.models.City
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.backbase.citysearch.models.Coord

class City : Comparable<City> {
    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("_id")
    @Expose
    var id: Long = 0

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    override fun compareTo(o: City): Int {
        val nameCompareValue = name!!.compareTo(o.name!!)
        return if (nameCompareValue == 0) {
            country!!.compareTo(o.country!!)
        } else {
            nameCompareValue
        }
    }
}