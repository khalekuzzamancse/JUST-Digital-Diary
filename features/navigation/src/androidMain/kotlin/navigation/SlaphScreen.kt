package navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import navigation.splash_screen.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    var finished by remember { mutableStateOf(false) }
    val logoScale by animateFloatAsState(
        targetValue = if (finished)1f else 0f, label = ""
    )
    LaunchedEffect(Unit){
        delay(150)
        finished=true
    }
    var showScreen by remember { mutableStateOf(false) }
    LaunchedEffect(Unit){
        delay(10)
        showScreen=true
    }
    AnimatedVisibility(showScreen){
        SplashScreen(
            modifier = Modifier.animateContentSize()
        ) {
            Image(
                painter = painterResource(R.drawable.just_logo),
                contentDescription = null,
                modifier = Modifier.size(150.dp).graphicsLayer {
                    scaleX=logoScale
                    scaleY=logoScale
                }
            )
        }
    }


}