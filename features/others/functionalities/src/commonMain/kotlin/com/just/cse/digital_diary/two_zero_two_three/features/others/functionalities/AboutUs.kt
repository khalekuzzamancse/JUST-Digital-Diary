package com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AboutUsResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.about_us.AboutUs
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.about_us.state.AboutUsState

@Composable
fun AboutUsDestination(
    repository: OtherInfoRepository,
) {
    AboutUs(
        repository =repository,
    )

}
@Composable
private fun AboutUs(
    repository: OtherInfoRepository,
) {
    var state by remember { mutableStateOf<AboutUsState?>(null) }
    LaunchedEffect(Unit) {
        val result = repository.getAboutUs()
        if (result is AboutUsResponseModel.Success) {
            state = AboutUsState(
                appName = result.data.appName,
                developedDepartmentName = result.data.developedDepartmentName,
                universityName = result.data.universityName,
                otherInfo = result.data.otherInfo
            )
        }
    }
    state?.let { aboutUsState ->
        AboutUs(
            state = aboutUsState,
        )
    }


}