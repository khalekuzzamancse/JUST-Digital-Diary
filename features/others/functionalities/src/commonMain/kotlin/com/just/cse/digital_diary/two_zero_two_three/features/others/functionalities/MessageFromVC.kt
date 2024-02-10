package com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.VCInfoResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.message_from_vc.MessageFromVC
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.message_from_vc.VCInfoState

@Composable
fun MessageFromVCDestination(
    repository: OtherInfoRepository
) {
    var state by remember {
        mutableStateOf<VCInfoState?>(null)
    }
    LaunchedEffect(Unit){
        val res=repository.getVCInfo()
        if (res is VCInfoResponseModel.Success){
            state= VCInfoState(
                name = res.data.name,
                details = res.data.details,
                message = res.data.message,
                imageUrl = res.data.imageUrl
            )
        }
    }
    state?.let {
        MessageFromVC(
            messageText =it.message,
            vcName = it.name,
            moreInfoOfVC = it.details,
            imageUrl = it.imageUrl
        )
    }

}
