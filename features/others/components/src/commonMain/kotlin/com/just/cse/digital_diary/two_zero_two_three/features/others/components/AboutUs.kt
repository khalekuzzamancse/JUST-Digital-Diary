package com.just.cse.digital_diary.two_zero_two_three.features.others.components

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us.AboutUsDestination

@Composable
fun AboutUsDestination(
    repository: OtherInfoRepository,
) {
    AboutUsDestination(
        repository =repository,
    )

}