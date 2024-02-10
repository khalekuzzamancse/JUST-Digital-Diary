package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HomeDestination(
    onEvent: (HomeDestinationEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxSize()
    ) {


    }




}

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    username: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Hello , $username !",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "Have a nice day!",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}



