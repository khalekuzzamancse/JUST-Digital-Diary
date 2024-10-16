@file:Suppress("SpellCheckingInspection")
package common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import justdigitaldiary.core.`common-ui`.generated.resources.Res
import justdigitaldiary.core.`common-ui`.generated.resources.empty_content
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyContentScreen(
    message: String = "No content found",
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ,  // Add padding if needed
        contentAlignment = Alignment.Center  // Center content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * - after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
             * - or do it for the particular module as `./gradlew :core:common-ui:generateComposeResClass`
             * - If not found, then go to build folder and find the location of the `Res` class(generated)
             * - Then copy the package name and import it; example as: `justdigitaldiary.core.`common-ui`.generated.resources.Res`
             * or ```import justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon```
             * - if the package or module name contain the `-` then it can causes this kind of problem
             */
            /**
             * - after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
             * - or do it for the particular module as `./gradlew :core:common-ui:generateComposeResClass`
             * - If not found, then go to build folder and find the location of the `Res` class(generated)
             * - Then copy the package name and import it; example as: `justdigitaldiary.core.`common-ui`.generated.resources.Res`
             * or ```import justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon```
             * - if the package or module name contain the `-` then it can causes this kind of problem
             */
            /**
             * - after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
             * - or do it for the particular module as `./gradlew :core:common-ui:generateComposeResClass`
             * - If not found, then go to build folder and find the location of the `Res` class(generated)
             * - Then copy the package name and import it; example as: `justdigitaldiary.core.`common-ui`.generated.resources.Res`
             * or ```import justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon```
             * - if the package or module name contain the `-` then it can causes this kind of problem
             */

            /**
             * - after that compile it again to generate Res class, use : .\gradlew generateComposeResClass
             * - or do it for the particular module as `./gradlew :core:common-ui:generateComposeResClass`
             * - If not found, then go to build folder and find the location of the `Res` class(generated)
             * - Then copy the package name and import it; example as: `justdigitaldiary.core.`common-ui`.generated.resources.Res`
             * or ```import justdigitaldiary.core.`common-ui`.generated.resources.teacher_icon```
             * - if the package or module name contain the `-` then it can causes this kind of problem
             */
            val res: DrawableResource = Res.drawable.empty_content
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(res),//org.jetbrains.compose.resources.
                contentDescription = null,

                )
//            Icon(
//                imageVector = Icons.Default.HourglassEmpty,
//                contentDescription = "No content icon",
//                modifier = Modifier.size(128.dp),
//                tint = Color.Gray
//            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                fontSize = 20.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}