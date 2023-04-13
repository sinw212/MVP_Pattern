package com.ww.mvp_pattern.adapter.contract

import com.ww.mvp_pattern.data.ImageItem

interface ImageAdapterContract {

    interface View {

        var onClickFunc : ((Int) -> Unit)?

        fun notifyAdapter()
    }

    interface Model {

        fun addItems(imageItems: ArrayList<ImageItem>)

        fun clearItem()

        fun getItem(position: Int): ImageItem
    }
}