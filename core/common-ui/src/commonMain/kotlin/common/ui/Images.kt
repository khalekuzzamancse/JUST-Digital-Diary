@file:Suppress("SpellCheckingInspection")
package common.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import justdigitaldiary.core.`common-ui`.generated.resources.Res
import justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon

import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileImagePlaceHolder(
    modifier: Modifier=Modifier,
) {
    val res: DrawableResource = Res.drawable.teacher_icon
    /**
     * - after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
     * - or do it for the particular module as ./gradlew :core:common-ui:generateComposeResClass
     * - If not found, then go to build folder and find the location of the `Res` class(generated)
     * - Then copy the package name and import it; example as: `justdigitaldiary.core.`common-ui`.generated.resources.Res`
     * or `justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon`
     * - if the package or module name contain the `-` then it can causes this kind of problem
     */
    Image(
        modifier=modifier,
        painter = painterResource(res),//org.jetbrains.compose.resources.
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
    )

}


@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String,
    onSuccess:()->Unit={}
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(url)
            // .placeholderMemoryCacheKey(screen.placeholder)
            // .apply { extras.setAll(screen.image.extras) }
            .build(),
        contentDescription = null,
        onState = {
            if( it is AsyncImagePainter.State.Success) {
                onSuccess()
            }
        },
        modifier = modifier,
    )

}