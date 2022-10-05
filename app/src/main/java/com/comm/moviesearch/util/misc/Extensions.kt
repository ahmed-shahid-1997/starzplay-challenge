package com.comm.moviesearch

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.initActionbar(title: String?, showHome: Boolean) {
    supportActionBar?.run {
        setDisplayShowHomeEnabled(showHome)
        setDisplayHomeAsUpEnabled(showHome)
        this.title = title
    }
}