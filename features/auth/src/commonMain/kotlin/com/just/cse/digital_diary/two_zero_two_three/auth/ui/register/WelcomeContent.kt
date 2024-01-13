package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Welcome() {
    Column (
        modifier = Modifier.wrapContentSize()
    ){
        LoginSectionHeader()
    }

}

@Composable
fun WelcomeExpandedScreen(
    modifier: Modifier=Modifier
) {

    Row (
        modifier =modifier,
    ){

//        Image (
//            painter = painterResource ("images/just_ground.jpg"),
//            contentDescription = null,
//            modifier = Modifier
//        )
    }

}

@Composable
fun LoginSectionHeader(
    modifier: Modifier=Modifier
) {
    Column (
        modifier =modifier,
    ){
        Text(
            text="Welcome to our login screen",
            style = MaterialTheme.typography.headlineSmall
        )
//        Image (
//            painter = painterResource ("images/just_logo.jpg"),
//            contentDescription = null,
//            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally)
//        )
    }

}