package core.database.api

suspend fun <T> fetchWithCache(
    remoteFetch: suspend () -> T,
    cacheFetch: suspend () -> T,
    cacheInsert: suspend (T) -> Unit,
    notConnectedRemote: Boolean
): T {
    if (notConnectedRemote) return cacheFetch()
    try {
        val entities = remoteFetch()
        cacheInsert(entities)
        return entities
    } catch (serverException: Exception) {
        try {
            return cacheFetch()
        } catch (_: Exception) {
            throw serverException
        }
    }
}
