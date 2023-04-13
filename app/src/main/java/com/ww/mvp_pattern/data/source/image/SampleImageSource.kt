package com.ww.mvp_pattern.data.source.image

import android.content.Context
import com.ww.mvp_pattern.data.ImageItem

interface SampleImageSource {

    interface LoadImageCallback {

        fun onLoadImages(list: ArrayList<ImageItem>)
    }

    fun getImages(context: Context, size: Int, loadImageCallback: LoadImageCallback?)
}