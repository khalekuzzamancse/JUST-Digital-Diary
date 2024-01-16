package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader

@Composable
fun LoginHeaderSection() {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(bottomStart = 100.dp),
        // color = MaterialTheme.colorScheme.primary
    ) {

            Column {
                ImageLoader(
                    url = "https://just.edu.bd/logo/download.png",
                    modifier = Modifier.height(100.dp),
                    onSuccess = {
                    }

                )



        }
    }
}