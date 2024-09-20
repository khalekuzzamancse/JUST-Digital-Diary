package just.digitaldiary

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import core.network.NetManagerProvider
import just.digitaldiary.theme.AppTheme
import navigation.AndroidRootNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler= AppEventHandler(this)
//        enableEdgeToEdge()
        //initialize the network manger
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        NetManagerProvider.setConnectivityManager(connectivityManager)
        setContent {
            AppTheme {
                JsonFilePicker{
                    Log.d("JOSNPICker",it)

                }
//                AndroidRootNavigation(
//                    onEvent = eventHandler::handleEvent
//                )
            }
        }
    }
}



