package com.sample.bonial_examination.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.sample.bonial_examination.R

class BrochurePremiumViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val ivImg: SimpleDraweeView
    val tvItemName: TextView
    val cvItem: CardView
    val clContainerOne: ConstraintLayout

    init {
        ivImg = view.findViewById(R.id.ivImg)
        tvItemName = view.findViewById(R.id.tvItemName)
        cvItem = view.findViewById(R.id.cvItem)
        clContainerOne = view.findViewById(R.id.clContainerOne)
    }


}