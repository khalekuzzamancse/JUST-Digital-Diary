package just.digitaldiary

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension to create a DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class TokenManager(private val context: Context) {

    companion object {
        // This is where we create the key for storing the token in DataStore
        private val USER_TOKEN_KEY = preferencesOf("user_token")
    }

    // Function to save the token in DataStore
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    // Function to clear the token (used for logout)
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_TOKEN_KEY)
        }
    }

    // Observable flow to get the token value
    val tokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY]
        }
}
