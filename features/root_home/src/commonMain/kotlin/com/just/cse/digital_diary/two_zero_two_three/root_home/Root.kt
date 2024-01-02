package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.ImageLoader
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.AuthNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.RootNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
//
fun RootModule() {
    AppTheme{
        var loginSuccess by remember {
            mutableStateOf(false)
        }
        if(loginSuccess){
            RootNavGraph()
        }
        else{
            AuthNavGraph(
                onLoginSuccess ={
                    loginSuccess=true
                }
            )
        }
    }


}


