package just.digitaldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


            }
        }
    }
}



