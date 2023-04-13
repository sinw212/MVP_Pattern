package com.ww.mvp_pattern.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ww.mvp_pattern.adapter.contract.ImageAdapterContract
import com.ww.mvp_pattern.data.ImageItem

class ImageAdapter : ImageAdapterContract.View, RecyclerView.Adapter<ImageViewHolder>(), ImageAdapterContract.Model {

    private lateinit var imageList: ArrayList<ImageItem>

    override var onClickFunc: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        imageList[position].let {
            holder.onBind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent, onClickFunc)

    override fun getItemCount() = imageList.size

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun getItem(position: Int) = imageList[position]

    override fun addItems(imageItems: ArrayList<ImageItem>) {
        this.imageList = imageItems
    }

    override fun clearItem() {
        imageList.clear()
    }
}