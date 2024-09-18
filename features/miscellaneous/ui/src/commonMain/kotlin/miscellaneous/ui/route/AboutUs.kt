package miscellaneous.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import miscellaneous.domain.repoisitory.OtherInfoRepository
import miscellaneous.ui.aboutus.AboutUsState
import miscellaneous.ui.aboutus._AboutUsState

@Composable
internal fun _AboutUsDestination(
    repository: OtherInfoRepository,
) {
    var state by remember { mutableStateOf<AboutUsState?>(null) }
    LaunchedEffect(Unit) {
        val result = repository.getAboutUs()
        if (result.isSuccess) {
            val data=result.getOrNull()
            if (data!=null){
                state = AboutUsState(
                    appName =data.appName,
                    developedDepartmentName =data.developedDepartmentName,
                    universityName =data.universityName,
                    otherInfo =data.otherInfo
                )
            }

        }
    }
    state?.let { aboutUsState ->
        _AboutUsState(
            state = aboutUsState,
        )
    }


}