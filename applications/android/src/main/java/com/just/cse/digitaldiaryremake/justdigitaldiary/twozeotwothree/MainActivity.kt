package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.navigation.AndroidRootNavigation
import com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.ui.theme.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler=AppEventHandler(this)

        setContent {
            AppTheme {
                AndroidRootNavigation(
                    appEvent = eventHandler.appEvent
                )


            }
        }
    }
}



