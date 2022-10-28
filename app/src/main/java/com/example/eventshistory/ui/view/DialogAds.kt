package com.example.eventshistory.ui.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    val rewardedYandexAds = remember { RewardedYandexAds(context) }
    var buttonText by rememberSaveable { mutableStateOf("Реклама") }

    OnLifecycleEvent { _, lifecycleEvent ->
        if (lifecycleEvent == Lifecycle.Event.ON_DESTROY){
            rewardedYandexAds.destroy()
        }
    }

    AlertDialog(
        shape = AbsoluteRoundedCornerShape(15.dp),
        backgroundColor = Color.Transparent,
        onDismissRequest = {
            buttonText = "Реклама"
            onDismissRequest()
        },
        buttons = {
            Box {
                Image(
                    bitmap = Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.resources, com.example.eventshistory.R.drawable.skip),
                        screenWidthDp / 2,
                        screenHeightDp / 13,
                        false
                    ).asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                        .height((screenHeightDp / 12).dp)
                        .width((screenWidthDp / 2.2).dp)
                        .clickable {
                            if (buttonText == "Реклама")
                                buttonText = "Смотреть"
                            else if (buttonText == "Смотреть"){
                                rewardedYandexAds.show()
                                buttonText = "Реклама"
                                onAdsShow()
                            }
                        }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 5.dp
                        )
                        .height((screenHeightDp / 12).dp)
                        .width((screenWidthDp / 2.2).dp)
                        .clickable {
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
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
            }
        }
    )
}