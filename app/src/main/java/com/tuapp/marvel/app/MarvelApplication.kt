package com.tuapp.marvel.app

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()
        Coil.setImageLoader(imageLoader)
    }
}