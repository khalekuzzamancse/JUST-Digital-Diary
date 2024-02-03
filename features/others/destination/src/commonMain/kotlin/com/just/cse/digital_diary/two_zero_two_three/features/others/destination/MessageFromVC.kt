package com.just.cse.digital_diary.two_zero_two_three.features.others.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.features.others.components.MessageFromVCDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.features_other.OtherInfoComponentProvider

@Composable
fun MessageFromVCDestination(

) {
    MessageFromVCDestination(
        repository = OtherInfoComponentProvider.getOtherInfoRepository(),
    )
}