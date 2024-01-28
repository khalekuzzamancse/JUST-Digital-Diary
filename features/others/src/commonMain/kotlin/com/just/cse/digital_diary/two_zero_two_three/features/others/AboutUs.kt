package com.just.cse.digital_diary.two_zero_two_three.features.others

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl

@Composable
fun AboutUsDestination() {
    AboutUsDestination(
        repository = OtherInfoRepositoryImpl(),
    )

}