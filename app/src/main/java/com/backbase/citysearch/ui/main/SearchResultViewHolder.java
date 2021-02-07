package com.backbase.citysearch.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.backbase.citysearch.R;
import com.backbase.citysearch.models.City;

public class SearchResultViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView subtitle;

    public SearchResultViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        subtitle = itemView.findViewById(R.id.subtitle);
    }

    public void setCity(City city) {
        title.setText(String.format("%s, %s", city.getName(), city.getCountry()));
        subtitle.setText(String.format("%s, %s", city.getCoord().getLat(), city.getCoord().getLon()));
    }
}
