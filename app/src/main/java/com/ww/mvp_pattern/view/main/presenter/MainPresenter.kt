package com.ww.mvp_pattern.view.main.presenter

import android.content.Context
import com.ww.mvp_pattern.adapter.contract.ImageAdapterContract
import com.ww.mvp_pattern.data.ImageItem
import com.ww.mvp_pattern.data.source.image.SampleImageRepository
import com.ww.mvp_pattern.data.source.image.SampleImageSource

class MainPresenter(
    private val view: MainContract.View,
    private val imageData: SampleImageRepository,
    private val adapterModel: ImageAdapterContract.Model,
    private val adapterView: ImageAdapterContract.View
) : MainContract.Presenter {

    init {
        adapterView.onClickFunc = {
            onClickListener(it)
        }
    }

    override fun loadItems(context: Context, isClear: Boolean) {
        imageData.getImages(context, 10, object : SampleImageSource.LoadImageCallback {
            override fun onLoadImages(list: ArrayList<ImageItem>) {
                if (isClear) {
                    adapterModel.clearItem()
                }

                adapterModel.addItems(list)
                adapterView.notifyAdapter()
            }
        })
    }

    private fun onClickListener(position: Int) {
        adapterModel.getItem(position).let {
            view.showToast(it.title)
        }
    }
}