package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.RootNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
fun RootModule() {
    AppTheme {

        var loginSuccess by remember {
            mutableStateOf(false)
        }
        if (loginSuccess) {
            RootNavGraph()
        } else {
            AuthModuleEntryPoint(
                onLoginSuccess = {
                    loginSuccess = true
                }
            )
        }
    }
//    LaunchedEffect(Unit){
//    val res= Response("https://sebi.io/demo-image-api/pictures.json").get()
//      println(res)
//



}



