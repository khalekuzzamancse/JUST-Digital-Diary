package just.digitaldiary
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")
    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    suspend fun saveToken(token: String) {
        runCatching {
            context.dataStore.edit { preferences ->
                preferences[TOKEN_KEY] = token
            }
        }
    }

    val tokenFlow: Flow<String?> = context.dataStore.data
        .catch {
            emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[TOKEN_KEY]
        }

    suspend fun clearToken() {
        runCatching {
            context.dataStore.edit { preferences ->
                preferences.remove(TOKEN_KEY)
            }
        }
    }
     suspend fun retrieveToken(): String? {
        return tokenFlow.firstOrNull()
    }
}
