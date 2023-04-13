package com.ww.mvp_pattern.view.main.presenter

import android.content.Context

interface MainContract {

    interface View {

        fun showToast(title: String)
    }

    interface Presenter {

        fun loadItems(context: Context, isClear: Boolean)
    }
}