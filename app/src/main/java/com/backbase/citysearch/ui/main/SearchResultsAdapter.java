package com.backbase.citysearch.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.backbase.citysearch.R;
import com.backbase.citysearch.models.City;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {
    private List<City> searchResult;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private View.OnClickListener onClickListener;

    public List<City> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<City> searchResult) {
        this.searchResult = searchResult;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        inflatedView.setOnClickListener(onClickListener);
        return new SearchResultViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.setCity(position, getSearchResult().get(position));
    }

    @Override
    public int getItemCount() {
        return getSearchResult() != null ? getSearchResult().size() : 0;
    }
}
