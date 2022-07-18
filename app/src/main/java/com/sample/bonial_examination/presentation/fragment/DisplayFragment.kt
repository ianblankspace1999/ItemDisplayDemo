package com.sample.bonial_examination.presentation.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.ph.examination.adapter.ItemListAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.flexbox.*
import com.sample.bonial_examination.R
import com.sample.bonial_examination.data.model.Item
import com.sample.bonial_examination.data.model.ResourceState
import com.sample.bonial_examination.presentation.helper.ItemFilterHelper
import com.sample.bonial_examination.presentation.helper.ViewHelper
import com.sample.bonial_examination.presentation.viewmodel.DisplayFragmentViewModel
import kotlinx.android.synthetic.main.fragment_display.*


interface DisplayCallBack {
    fun displayNoDataText()
    fun removeNoDataText()
}

class DisplayFragment: Fragment(), DisplayCallBack {

    lateinit var viewModel: DisplayFragmentViewModel

    private var itemList: ArrayList<Item> = ArrayList()
    private var itemListFiltered: ArrayList<Item> = ArrayList()


    private val rvDisplay by lazy{ view?.findViewById<RecyclerView>(R.id.rvDisplay) }
    private lateinit var adapter: ItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayFragmentViewModel::class.java)
        activity?.let {
            viewModel.getItems(it)
        }
        initRecyclerView()
        observeItemsData()
        initView()
    }

    override fun displayNoDataText() {
        tvNoData.visibility = View.VISIBLE
    }

    override fun removeNoDataText() {
        tvNoData.visibility = View.GONE
    }

    private fun initView() {
        btnFilter.setOnClickListener {
            if (btnFilter.text == activity?.resources?.getString(R.string.button_text_filter)) {
                btnFilter.text = activity?.resources?.getString(R.string.button_text)
                adapter.changeData(itemListFiltered)
            } else {
                btnFilter.text = activity?.resources?.getString(R.string.button_text_filter)
                adapter.changeData(itemList)
            }
        }
        btnRefresh.setOnClickListener {
            activity?.let { activity ->
                viewModel.getItems(activity)
            }
            it.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        activity?.let {
            Fresco.initialize(it)
            var orientation = resources.configuration.orientation
            adapter = ItemListAdapter(this,
                itemList,
                ViewHelper().getScreenWidthResolution(it),
                orientation == Configuration.ORIENTATION_PORTRAIT,
            this)
            rvDisplay?.adapter = adapter
            val layoutManager = FlexboxLayoutManager(it)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START
            rvDisplay?.setLayoutManager(layoutManager)
        }

    }

    private fun observeItemsData() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner) {
            when(it.status) {
                ResourceState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                ResourceState.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { items ->
                        itemList = items
                        itemListFiltered = ItemFilterHelper().returnLessEqualFiveKmDistance(items)
                        adapter.changeData(items)
                    }
                }

                ResourceState.ERROR -> {
                    progressBar.visibility = View.GONE
                    btnRefresh.visibility = View.VISIBLE
                    ViewHelper().showError(requireActivity(), activity?.resources?.getString(R.string.error_dialog).toString())
                }
            }
        }
    }
}