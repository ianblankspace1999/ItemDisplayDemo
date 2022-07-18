package com.exam.ph.examination.adapter

import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import com.sample.bonial_examination.R
import com.sample.bonial_examination.data.model.Item
import com.sample.bonial_examination.presentation.adapter.viewholder.BrochurePremiumViewHolder
import com.sample.bonial_examination.presentation.adapter.viewholder.BrochureViewHolder
import com.sample.bonial_examination.presentation.fragment.DisplayCallBack
import com.sample.bonial_examination.presentation.helper.ViewHelper


class ItemListAdapter(context: Fragment, itemList: ArrayList<Item>, widthDimension: Float,
    isPortrait: Boolean, callBack: DisplayCallBack? = null)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val brochurePremiumStr = "brochurePremium"
    }
    private val context = context
    private val widthDimension = widthDimension
    private val isPortrait = isPortrait
    private val callBack = callBack

    private var itemList = itemList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ItemType.BROCHURE.type -> {
                val view: View = LayoutInflater.from(context.context).inflate(R.layout.item_brochure, parent, false)
                BrochureViewHolder(view)
            } else -> {
                val view: View = LayoutInflater.from(context.context).inflate(R.layout.item_brochure_premium, parent, false)
                BrochurePremiumViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType) {
            ItemType.BROCHURE.type -> {
                var holder1 = holder as BrochureViewHolder
                    var content =
                        Gson().fromJson(Gson().toJson(itemList[position].content), Item.Content::class.java)
                content.retailer?.name?.let {
                    holder1.tvItemName.text = it
                }
                ViewHelper().bindLoadImage(holder1.ivImg, content.brochureImage)

                val dimen = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    widthDimension,
                    context.resources.displayMetrics
                )
                if (isPortrait) {
                    holder1.clContainerOne.layoutParams = FrameLayout.LayoutParams(
                        (dimen / 2.1).toInt(),
                        (dimen * 1.2).toInt())
                } else {
                    holder1.clContainerOne.layoutParams = FrameLayout.LayoutParams(
                        (dimen / 3.3).toInt(),
                        (dimen * 0.7).toInt())
                }
            } else -> {
            var holder2 = holder as BrochurePremiumViewHolder
            var content =
                Gson().fromJson(Gson().toJson(itemList[position].content), Item.Content::class.java)
            content.retailer?.name?.let {
                holder2.tvItemName.text = it
            }

            ViewHelper().bindLoadImage(holder2.ivImg, content.brochureImage)
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position].contentType == brochurePremiumStr) {
            ItemType.BROCHURE_PREMIUM.type
        } else {
            ItemType.BROCHURE.type
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun changeData(itemList: ArrayList<Item>) {
        if (itemList.size == 0) {
            callBack?.displayNoDataText()
        } else {
            callBack?.removeNoDataText()
        }
        this.itemList = itemList
        notifyDataSetChanged()
    }

    enum class ItemType(val type: Int) {
        BROCHURE(1),
        BROCHURE_PREMIUM(2)
    }

}