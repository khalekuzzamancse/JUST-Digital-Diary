package core.network


import android.net.ConnectivityManager
import android.net.NetworkCapabilities

@Suppress("MissingPermission")
//actual fun netManagerProvider(): NetManager {
//    return object : NetManager {
//        override fun isInternetAvailable(): Boolean {
//            val connectivityManager = NetManagerProvider.connectivityManager
//            val network = connectivityManager?.activeNetwork ?: return false
//            val actNw = connectivityManager.getNetworkCapabilities(network) ?: return false
//            return when {
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                else -> false
//            }
//
//        }
//
//    }
//}

object NetManagerProvider {
     var connectivityManager: ConnectivityManager?=null
        private set

    /**
     * Not holding the context reference,just one time use
     */
    fun setConnectivityManager(manager: ConnectivityManager) {
        connectivityManager=manager
    }

}
