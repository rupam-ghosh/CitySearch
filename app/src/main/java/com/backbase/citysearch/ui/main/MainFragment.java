package com.backbase.citysearch.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.backbase.citysearch.R;
import com.backbase.citysearch.models.City;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainFragment extends Fragment implements TextWatcher {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private TextView message;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getSearchResultsLiveData().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                SearchResultsAdapter adapter = (SearchResultsAdapter) recyclerView.getAdapter();
                adapter.setSearchResult(cities);
                adapter.notifyDataSetChanged();
                updateUI(cities);
            }
        });
        List<City> value = mViewModel.getSearchResultsLiveData().getValue();
        updateUI(value);
        mViewModel.searchInitiated("", getActivity().getApplication());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText textInputEditText = view.findViewById(R.id.textInputEditText);
        textInputEditText.addTextChangedListener(this);
        message = view.findViewById(R.id.message);
        recyclerView = view.findViewById(R.id.recyclerView);
        SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter();
        recyclerView.setAdapter(searchResultsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void updateUI(List<City> value) {
        if (value == null || value.size() == 0) {
            message.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            message.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.getSearchResultsLiveData().removeObservers(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no implementation
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mViewModel.searchInitiated(s.toString(), getActivity().getApplication());
    }

    @Override
    public void afterTextChanged(Editable s) {
        // no implementation
    }
}