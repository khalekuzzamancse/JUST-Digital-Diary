package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.os.Bundle
import android.provider.DocumentsContract.Root
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import com.example.compose.AppTheme
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootModule


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Icons.Outlined.ExpandMore
            AppTheme{
                RootModule()
            }

        }
    }
}


