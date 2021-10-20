package com.backbase.citysearch.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backbase.citysearch.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CityListFragment : Fragment(), TextWatcher {
    private var mViewModel: CityListViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var message: TextView? = null
    private var textInputEditText: TextInputEditText? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CityListViewModel::class.java)
        mViewModel?.cityDataMutableLiveData?.observe(this, Observer<CityData>() {
            fun onChanged(cityData: CityData) {
                val adapter = recyclerView?.adapter as SearchResultsAdapter?
                adapter?.searchResult = cityData.cities
                adapter?.notifyDataSetChanged()
                updateUI(cityData)
            }
        })
        onInputQueryChange(if (savedInstanceState != null) savedInstanceState.getString(ARGUMENT_QUERY) else "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ARGUMENT_QUERY, textInputEditText!!.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar2)
        toolbar.setTitle(R.string.app_name)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        val textInputLayout: TextInputLayout = view.findViewById(R.id.textInputLayout)
        textInputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
        textInputEditText = view.findViewById(R.id.textInputEditText)
        textInputEditText?.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI)
        textInputEditText?.addTextChangedListener(this)
        message = view.findViewById(R.id.message)
        recyclerView = view.findViewById(R.id.recyclerView)
        val searchResultsAdapter = SearchResultsAdapter()
        searchResultsAdapter.onClickListener = View.OnClickListener { v ->
            val position = v.tag as Int
            val bundle = Bundle()
            mViewModel?.cityDataMutableLiveData?.value?.let {
                it.cities?.get(position)?.let {
                    bundle.putDouble(CityMapFragment.ARGUMENT_LATITUDE,
                            it.coord!!.lat)
                    bundle.putDouble(CityMapFragment.ARGUMENT_LONGITUDE,
                            it.coord!!.lon)
                    bundle.putString(CityMapFragment.ARGUMENT_TITLE,
                            it.name)
                }
            }
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CityMapFragment.newInstance(bundle))
                    .addToBackStack(CityMapFragment::class.java.name)
                    .commit()
        }
        recyclerView?.apply {
            recyclerView?.setAdapter(searchResultsAdapter)
            val linearLayoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(recyclerView?.getContext(),
                    linearLayoutManager.orientation)
            recyclerView?.addItemDecoration(dividerItemDecoration)
            recyclerView?.setLayoutManager(linearLayoutManager)
        }
    }

    private fun updateUI(cityData: CityData) {
        when (cityData.loadingState) {
            CityDataLoadingState.EMPTY -> {
                message!!.visibility = View.VISIBLE
                message!!.setText(R.string.noresult)
                recyclerView!!.visibility = View.INVISIBLE
                message!!.visibility = View.INVISIBLE
                recyclerView!!.visibility = View.VISIBLE
            }
            CityDataLoadingState.LOADED -> {
                message!!.visibility = View.INVISIBLE
                recyclerView!!.visibility = View.VISIBLE
            }
            CityDataLoadingState.LOADING -> {
                message!!.visibility = View.VISIBLE
                message!!.setText(R.string.loading)
                recyclerView!!.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        mViewModel!!.cityDataMutableLiveData.removeObservers(this)
        super.onDestroyView()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // no implementation
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onInputQueryChange(s.toString())
    }

    override fun afterTextChanged(s: Editable) {
        // no implementation
    }

    private fun onInputQueryChange(query: String?) {
        mViewModel!!.searchInitiated(query)
    }

    companion object {
        private const val ARGUMENT_QUERY = "query"
        @JvmStatic
        fun newInstance(): CityListFragment {
            return CityListFragment()
        }
    }
}