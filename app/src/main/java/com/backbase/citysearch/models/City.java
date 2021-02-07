package com.backbase.citysearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Comparable<City> {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("coord")
    @Expose
    private Coord coord;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public int compareTo(City o) {
        int nameCompareValue = this.name.compareTo(o.name);
        if (nameCompareValue == 0) {
            return this.country.compareTo(o.country);
        } else {
            return nameCompareValue;
        }
    }
}
