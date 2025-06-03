package com.velosobr.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "exchange_icons")

class ExchangeIconDataStore(private val context: Context) {

    private fun iconKey(exchangeId: String): Preferences.Key<String> =
        stringPreferencesKey("icon_url_$exchangeId")

    suspend fun saveIconUrl(exchangeId: String, url: String) {
        context.dataStore.edit { prefs ->
            prefs[iconKey(exchangeId)] = url
        }
    }

    suspend fun getIconUrl(exchangeId: String): String? =
        context.dataStore.data
            .map { prefs -> prefs[iconKey(exchangeId)] }
            .first()


    suspend fun clearIcon(exchangeId: String) {
        context.dataStore.edit { prefs ->
            prefs.remove(iconKey(exchangeId))
        }
    }
}