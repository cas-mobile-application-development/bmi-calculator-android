package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Settings(
    private val context: Context
) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    private object SettingsKeys {
        val USERNAME = stringPreferencesKey("name")
    }

    fun getUsername(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[SettingsKeys.USERNAME]
        }
    }

    suspend fun setUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[SettingsKeys.USERNAME] = username
        }
    }


}

