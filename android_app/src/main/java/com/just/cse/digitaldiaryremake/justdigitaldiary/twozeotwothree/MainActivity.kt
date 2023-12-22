package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.Demo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo()
            //LoginSection()
         //   LoginScreenSlotMediumScreen()
        }
    }
}


