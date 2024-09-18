package common.newui

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import justdigitaldiary.common.ui.generated.resources.Res
import justdigitaldiary.common.ui.generated.resources.teacher_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileImagePlaceHolder(
    modifier: Modifier=Modifier,
) {
    val res: DrawableResource = Res.drawable.teacher_icon
//after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
    Image(
        modifier=modifier,
        painter = painterResource(res),//org.jetbrains.compose.resources.
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )

}