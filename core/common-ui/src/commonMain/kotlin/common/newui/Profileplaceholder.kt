package common.newui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileImagePlaceHolder(
    modifier: Modifier=Modifier,
) {
   // val res: DrawableResource = Res.drawable.teacher_icon
//after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
//    Image(
//        modifier=modifier,
//        painter = painterResource(res),//org.jetbrains.compose.resources.
//        contentDescription = null,
//        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
//    )

}