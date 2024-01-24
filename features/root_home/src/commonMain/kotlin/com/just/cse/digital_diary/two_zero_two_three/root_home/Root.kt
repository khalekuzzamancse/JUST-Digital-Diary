package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme
import com.just.cse.digitaldiary.twozerotwothree.core.di.diTest


@Composable
fun RootModule(appEvent: AppEvent) {
    AppTheme {
        LaunchedEffect(Unit){
            diTest()
        }

  }


}



