package miscellaneous.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.message_from_vc.MessageFromVC
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.message_from_vc.VCInfoState
import miscellaneous.domain.repoisitory.OtherInfoRepository

@Composable
internal fun _MessageFromVCDestination(
    repository: OtherInfoRepository
) {
    var state by remember {
        mutableStateOf<VCInfoState?>(null)
    }
    LaunchedEffect(Unit){
        val response=repository.getVCInfo()
        if (response.isSuccess){
            val data=response.getOrNull()
            if (data!=null){
                state= VCInfoState(
                    name = data.name,
                    details =data.details,
                    message = data.message,
                    imageUrl =data.imageUrl
                )
            }

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
