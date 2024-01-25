package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader

@Composable
fun RegistrationHeaderSection(
    modifier: Modifier,
) {
   Surface(
       modifier = modifier,
       shape = RoundedCornerShape(bottomStart = 80.dp),
       color = MaterialTheme.colorScheme.primary
   ) {
       Box(modifier = Modifier
           .fillMaxWidth()
           .height(100.dp),
       ){
               ImageLoader(
                   url = "https://automation.just.edu.bd/images/just-logo.png",
                   modifier = Modifier.height(60.dp).align(Alignment.Center)
               )

       }
   }
}