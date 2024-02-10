package com.just.cse.digital_diary.two_zero_two_three.features.others.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities.AboutUsDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.features_other.OtherInfoComponentProvider

@Composable
fun AboutUsDestination(
) {
    AboutUsDestination(
        repository =OtherInfoComponentProvider.getOtherInfoRepository(),
    )

}