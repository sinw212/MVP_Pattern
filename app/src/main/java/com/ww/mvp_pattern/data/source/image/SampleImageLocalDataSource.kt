package com.ww.mvp_pattern.data.source.image

import android.content.Context
import com.ww.mvp_pattern.data.ImageItem

object SampleImageLocalDataSource : SampleImageSource {

    override fun getImages(context: Context, size: Int, loadImageCallback: SampleImageSource.LoadImageCallback?) {
        val list = ArrayList<ImageItem>()
        for (index in 0..size) {
            val name = String.format("sample_%02d", (Math.random() * 15).toInt())
            val resource = context.resources.getIdentifier(name, "drawable", context.packageName)
            list.add(ImageItem(resource, name))
        }
        loadImageCallback?.onLoadImages(list)
    }
}