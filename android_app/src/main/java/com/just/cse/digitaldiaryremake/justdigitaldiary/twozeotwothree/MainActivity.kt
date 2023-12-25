package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Flaky
import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.NavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Icons.Rounded.Flaky
        setContent {
            AppTheme{
                NavHost()
            }

        }
    }
}


