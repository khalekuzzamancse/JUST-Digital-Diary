package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.ui.text.TextStyle
import com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.ui.theme.theme.AppTheme
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootModule


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme{
                RootModule()


            }
        }
    }
}


