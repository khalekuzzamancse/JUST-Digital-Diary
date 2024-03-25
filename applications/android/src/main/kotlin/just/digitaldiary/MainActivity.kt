package just.digitaldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import just.digitaldiary.theme.AppTheme
import navigation.AndroidRootNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler= AppEventHandler(this)

        setContent {
            AppTheme {
                AndroidRootNavigation(
                    onEvent = eventHandler::handleEvent
                )
            }
        }
    }
}



