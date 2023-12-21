package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Welcome() {
    Column (
        modifier = Modifier.wrapContentSize()
    ){
        JUSTLogoAndGreetings()
    }

}

@Composable
fun WelcomeExpandedScreen(
    modifier: Modifier=Modifier
) {

    Row (
        modifier =modifier,
    ){

        Image (
            painter = painterResource ("images/just_ground.jpg"),
            contentDescription = null,
            modifier = Modifier
        )
    }

}

@Composable
fun JUSTLogoAndGreetings(
    modifier: Modifier=Modifier
) {
    Column (
        modifier =modifier,
    ){
        Text(
            text="Welcome to our login screen",
            style = MaterialTheme.typography.headlineSmall
        )
        Image (
            painter = painterResource ("images/just_logo.jpg"),
            contentDescription = null,
            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally)
        )
    }

}