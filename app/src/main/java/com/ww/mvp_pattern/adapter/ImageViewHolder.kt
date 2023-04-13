package com.ww.mvp_pattern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ww.mvp_pattern.R
import com.ww.mvp_pattern.data.ImageItem
import com.ww.mvp_pattern.util.ImageAsync

class ImageViewHolder(
    parent: ViewGroup,
    private val listenerFunc: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)) {

    private val imageView by lazy {
        itemView.findViewById(R.id.img_view) as ImageView
    }

    private val textView by lazy {
        itemView.findViewById(R.id.text) as TextView
    }

    fun onBind(item: ImageItem, position: Int) {
        ImageAsync(imageView.context, imageView).execute(item.resource)
        textView.text = item.title

        itemView.setOnClickListener {
            listenerFunc?.invoke(position)
        }
    }
}