package core.database.api

object RemoteCacheHelper {
    suspend fun <T> fetchFromRemoteOrCache(
        remoteApi: Any?, // Add remoteApi as a parameter to check for null
        remoteCall: suspend () -> T,
        cacheCall: suspend () -> T,
        cacheUpdate: suspend (T) -> Unit
    ): T {
        // If remoteApi is null, fall back to the cache directly
        if (remoteApi == null) return cacheCall()

        return try {
            val serverResponse = remoteCall()
            try {
                //Execution is here means server has success response
                //If remote give response then update the cache
                cacheUpdate(serverResponse)
                //Since server give response so return the sever response
                serverResponse
            } catch (cacheInsertionEx: Exception) {
                // Log cache insertion exception if needed
                //Since server give response so return the sever response even if the update to cache is failed
                serverResponse
            }


        } catch (serverException: Exception) {
            try {
                cacheCall()
            } catch (cacheEx: Exception) {
                throw serverException // propagate the server exception if cache also fails
            }
        }
    }
}
