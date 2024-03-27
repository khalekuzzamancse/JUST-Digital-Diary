package core.network

actual fun netManagerProvider(): NetManager {
    return object :NetManager{
        override fun isInternetAvailable(): Boolean {
            return false
        }

    }
}