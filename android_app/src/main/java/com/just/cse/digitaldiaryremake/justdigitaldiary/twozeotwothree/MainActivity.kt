package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.Flaky
import androidx.compose.material.icons.rounded.Psychology
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.LoginScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.FacultyLayoutFactoryDemo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Icons.Rounded.Flaky
        setContent {


            FacultyLayoutFactoryDemo()
            //LoginScreen()
            //LoginSection()
         //   LoginScreenSlotMediumScreen()
        }
    }
}


