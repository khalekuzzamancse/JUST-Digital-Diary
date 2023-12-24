package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Flaky
import androidx.compose.material3.Card
import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.FacultyLayoutBuilder2Demo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.MainScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.FacultyLayoutFactoryDemo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Icons.Rounded.Flaky
        setContent {
            AppTheme{
                Navigator(MainScreen())
            }

//            FacultyLayoutFactoryDemo()
            //LoginScreen()
            //LoginSection()
         //   LoginScreenSlotMediumScreen()
        }
    }
}


