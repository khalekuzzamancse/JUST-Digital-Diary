@file:Suppress("unused")
package common.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Object that provides animation transitions for navigation.
 * Since this object is static, it doesn't store the animation, just returns it.
 * This avoids potential memory leaks by keeping animations purely functional.
 *
 * Example usage in NavHost:
 *
 *```kotlin
 * NavHost(
 *    navController = navController,
 *    startDestination = _NavRoute.UpdateFaculty.ROUTE,
 *    enterTransition = {
 *        NavAnimations.Enter.slideInHorizontally()
 *    },
 *    exitTransition = {
 *        NavAnimations.Exit.slideOutHorizontally()
 *    },
 *    popEnterTransition = {
 *        NavAnimations.PopEnter.scaleIn()
 *    },
 *    popExitTransition = {
 *        NavAnimations.PopExit.scaleOut()
 *    }
 * ) {
 *    composable(route = _NavRoute.UpdateFaculty.ROUTE) {
 *        // Composable content for UpdateFaculty screen
 *    }
 * }
 * ```
 */

object NavAnimations {

    /**
     * Object that provides enter transitions.
     */
    object Enter {
        fun slideInHorizontally(): EnterTransition {
            return slideInHorizontally(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
        }

        fun slideInVertically(): EnterTransition {
            return slideInVertically(animationSpec = tween(600)) + fadeIn(animationSpec = tween(600))
        }

        fun scaleIn(): EnterTransition {
            return scaleIn(initialScale = 0.8f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        }
    }

    /**
     * Object that provides exit transitions.
     */
    object Exit {
        fun slideOutHorizontally(): ExitTransition {
            return slideOutHorizontally(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
        }

        fun slideOutVertically(): ExitTransition {
            return slideOutVertically(animationSpec = tween(600)) + fadeOut(animationSpec = tween(600))
        }

        fun scaleOut(): ExitTransition {
            return scaleOut(targetScale = 1.1f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        }
    }

    /**
     * Object that provides pop enter transitions.
     */
    object PopEnter {
        fun scaleIn(): EnterTransition {
            return scaleIn(initialScale = 1.2f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        }

        fun slideInHorizontally(): EnterTransition {
            return slideInHorizontally(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
        }

        fun slideInVertically(): EnterTransition {
            return slideInVertically(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
        }
    }

    /**
     * Object that provides pop exit transitions.
     */
    object PopExit {
        fun scaleOut(): ExitTransition {
            return scaleOut(targetScale = 0.8f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        }

        fun slideOutHorizontally(): ExitTransition {
            return slideOutHorizontally(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
        }

        fun slideOutVertically(): ExitTransition {
            return slideOutVertically(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
        }
    }
}

//TODO: Type writing effect

@Composable
fun TypeWriter(
    text: String,
    delay:Long,
    decorator:@Composable (text: String) ->Unit,
) {

    TypeWriter(
        text= AnnotatedString(text),
        delay=delay,
        decorator={
            decorator(it.text)
        }
    )
}

@Composable
fun TypeWriter(
    text: AnnotatedString,
    delay:Long,
    decorator:@Composable (AnnotatedString)->Unit
) {
    var currentText by remember { mutableStateOf(AnnotatedString("")) }
    OnEachCharacter(text=text, delay =delay){
        currentText=it
    }

    decorator(currentText)
}


@Composable
private fun OnEachCharacter(
    text: AnnotatedString,
    delay:Long,
    onSubstringProcessed: (AnnotatedString) -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (currentIndex < text.length) {
                val substring = text.subSequence(0, currentIndex + 1)
                onSubstringProcessed(substring)
                delay(delay)
                currentIndex++
            }
        }
    }

}


