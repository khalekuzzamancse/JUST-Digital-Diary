package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.features.common_ui.MySearchBar
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.AuthNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.RootNavGraph

@Composable
//
fun RootModule() {
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