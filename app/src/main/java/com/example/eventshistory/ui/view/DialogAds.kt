package com.example.eventshistory.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.eventshistory.ui.theme.Teal200
import com.example.eventshistory.ui.theme.tintColor
import com.example.eventshistory.yandexAds.RewardedYandexAds

@Composable
fun DialogAds(
    onAdsShow:() -> Unit,
    onDismissRequest:() -> Unit
) {
    val context = LocalContext.current
    val rewardedYandexAds = remember { RewardedYandexAds(context) }
    var buttonText by rememberSaveable { mutableStateOf("Реклама") }

    OnLifecycleEvent { _, lifecycleEvent ->
        if (lifecycleEvent == Lifecycle.Event.ON_DESTROY){
            rewardedYandexAds.destroy()
        }
    }

    AlertDialog(
        shape = AbsoluteRoundedCornerShape(15.dp),
        backgroundColor = tintColor,
        onDismissRequest = {
            buttonText = "Реклама"
            onDismissRequest()
        },
        buttons = {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (buttonText == "Реклама")
                        buttonText = "Смотреть"
                    else if (buttonText == "Смотреть"){
                        rewardedYandexAds.show()
                        buttonText = "Реклама"
                        onAdsShow()
                    }
                }
            ) {
                Text(
                    text = buttonText,
                    color = Teal200
                )
            }
        }
    )
}