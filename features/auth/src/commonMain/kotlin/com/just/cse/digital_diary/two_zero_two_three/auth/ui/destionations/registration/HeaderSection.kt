package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

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
import com.just.cse.digital_diary.features.common_ui.network_image.ImageLoader

@Composable
fun RegistrationHeaderSection() {
   Surface(
       modifier = Modifier,
       shape = RoundedCornerShape(bottomStart = 100.dp),
       color = MaterialTheme.colorScheme.primary
   ) {
       Box(modifier = Modifier
           .fillMaxWidth()
           .height(200.dp),
       ){
               ImageLoader(
                   url = "https://automation.just.edu.bd/images/just-logo.png",
                   modifier = Modifier.height(100.dp).align(Alignment.Center)
               )

       }
   }
}