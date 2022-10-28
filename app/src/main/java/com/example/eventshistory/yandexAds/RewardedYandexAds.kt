package com.example.eventshistory.yandexAds

import android.content.Context
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAd
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener

class RewardedYandexAds(
    context:Context
): RewardedAdEventListener {

    private var rewardedAd:RewardedAd? = RewardedAd(context)

    init { configureRewardedAd() }

    fun show() = loadRewardedAd()

    private fun loadRewardedAd() = rewardedAd?.loadAd(AdRequest.Builder().build())

    private fun configureRewardedAd() {
        rewardedAd?.setAdUnitId(AD_UNIT_ID)
        rewardedAd?.setRewardedAdEventListener(this)
    }

    private companion object { const val AD_UNIT_ID = "R-M-1997536-1" }

    override fun onAdLoaded() { rewardedAd?.show() }

    override fun onAdFailedToLoad(p0: AdRequestError) = Unit

    override fun onAdShown() = Unit

    override fun onAdDismissed() = Unit

    override fun onRewarded(p0: Reward) = Unit

    override fun onAdClicked() = Unit

    override fun onLeftApplication() = Unit

    override fun onReturnedToApplication() = Unit

    override fun onImpression(p0: ImpressionData?) = Unit

    fun destroy() {
        rewardedAd?.destroy()
        rewardedAd = null
    }
}