package com.example.eventshistory

import android.app.Application
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.instream.MobileInstreamAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        MobileInstreamAds.setAdGroupPreloading(INSTREAM_AD_GROUP_PRELOADING_ENABLED)
        MobileAds.initialize(this){  }
    }

    private companion object {
        private const val INSTREAM_AD_GROUP_PRELOADING_ENABLED = true
    }
}