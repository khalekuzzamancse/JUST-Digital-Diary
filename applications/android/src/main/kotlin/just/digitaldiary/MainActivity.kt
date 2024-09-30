package just.digitaldiary

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Today
import core.network.NetManagerProvider
import just.digitaldiary.theme.AppTheme
import navigation.RootNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler= AppEventHandler(this)
//        enableEdgeToEdge()
        //initialize the network manger
//        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        NetManagerProvider.setConnectivityManager(connectivityManager)
        setContent {
            AppTheme {
                RootNavHost {}
//                AndroidRootNavigation(
//                    onEvent = eventHandler::handleEvent
//                )

            }
        }
    }
}



