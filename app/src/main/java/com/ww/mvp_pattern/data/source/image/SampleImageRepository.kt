package com.ww.mvp_pattern.data.source.image

import android.content.Context
import com.ww.mvp_pattern.data.ImageItem

object SampleImageRepository : SampleImageSource {
    //Memory cache가 가능하고, Local, Remote DataSource를 한번에 불러올 수 있음

    private val sampleImageLocalDataSource = SampleImageLocalDataSource

    override fun getImages(context: Context, size: Int, loadImageCallback: SampleImageSource.LoadImageCallback?) {
        sampleImageLocalDataSource.getImages(context, size, object : SampleImageSource.LoadImageCallback {
            override fun onLoadImages(list: ArrayList<ImageItem>) {
                loadImageCallback?.onLoadImages(list)
            }
        })
    }
}