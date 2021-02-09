package com.backbase.citysearch.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.backbase.citysearch.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

public class MainFragment extends Fragment implements TextWatcher {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private TextView message;
    private TextInputEditText textInputEditText;
    private static final String ARGUMENT_QUERY = "query";

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
        mViewModel.getCityDataMutableLiveData().observe(this, new Observer<CityData>() {
            @Override
            public void onChanged(CityData cityData) {
                SearchResultsAdapter adapter = (SearchResultsAdapter) recyclerView.getAdapter();
                adapter.setSearchResult(cityData.getCities());
                adapter.notifyDataSetChanged();
                updateUI(cityData);
            }
        });
        onInputQueryChange(savedInstanceState != null ? savedInstanceState.getString(ARGUMENT_QUERY) : "");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(ARGUMENT_QUERY, textInputEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar2);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        textInputLayout.setEndIconMode(END_ICON_CLEAR_TEXT);
        textInputEditText = view.findViewById(R.id.textInputEditText);
        textInputEditText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        textInputEditText.addTextChangedListener(this);
        message = view.findViewById(R.id.message);
        recyclerView = view.findViewById(R.id.recyclerView);
        SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter();
        searchResultsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                Bundle bundle = new Bundle();
                bundle.putDouble(CityMapFragment.ARGUMENT_LATITUDE,
                        mViewModel.getCityDataMutableLiveData().getValue().getCities().get(position).getCoord().getLat());
                bundle.putDouble(CityMapFragment.ARGUMENT_LONGITUDE,
                        mViewModel.getCityDataMutableLiveData().getValue().getCities().get(position).getCoord().getLon());
                bundle.putString(CityMapFragment.ARGUMENT_TITLE,
                        mViewModel.getCityDataMutableLiveData().getValue().getCities().get(position).getName());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CityMapFragment.newInstance(bundle))
                        .addToBackStack(CityMapFragment.class.getName())
                        .commit();
            }
        });
        recyclerView.setAdapter(searchResultsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void updateUI(CityData cityData) {
        switch(cityData.getLoadingState()) {
            case EMPTY:
                message.setVisibility(View.VISIBLE);
                message.setText(R.string.noresult);
                recyclerView.setVisibility(View.INVISIBLE);
            case LOADED:
                message.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case LOADING:
                message.setVisibility(View.VISIBLE);
                message.setText(R.string.loading);
                recyclerView.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        mViewModel.getCityDataMutableLiveData().removeObservers(this);
        super.onDestroyView();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no implementation
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onInputQueryChange(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        // no implementation
    }

    private void onInputQueryChange(String query) {
        mViewModel.searchInitiated(query);
    }
}