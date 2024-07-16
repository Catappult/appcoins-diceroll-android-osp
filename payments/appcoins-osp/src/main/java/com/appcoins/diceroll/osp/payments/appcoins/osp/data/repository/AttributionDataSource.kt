package com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository.PreferencesKeys.ATTRIBUTION_COMPLETE
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository.PreferencesKeys.GUEST_UID
import com.appcoins.diceroll.osp.payments.appcoins.osp.data.repository.PreferencesKeys.OEM_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AttributionDataSource @Inject constructor(
    private val preferences: DataStore<Preferences>,
) {

    /**
     * Sets the user attribution response.
     */
    suspend fun saveUserAttribution(oemId: String? = null, guestUid: String? = null) {
        withContext(Dispatchers.IO) {
            preferences.edit { prefs ->
                oemId?.let { prefs[OEM_ID] = it }
                guestUid?.let { prefs[GUEST_UID] = it }
                prefs[ATTRIBUTION_COMPLETE] = true
            }
        }
    }

    /**
     * GuestUid saved [String].
     */
    suspend fun getGuestUid(): String? =
        preferences.data.map { prefs -> prefs[GUEST_UID] }.firstOrNull()

    /**
     * OemId saved [String].
     */
    suspend fun getOemId(): String? =
        preferences.data.map { prefs -> prefs[OEM_ID] }.firstOrNull()

    /**
     * User attribution is complete [Boolean].
     */
    suspend fun isUserAttributionCompleted(): Boolean =
        preferences.data.map { prefs -> prefs[ATTRIBUTION_COMPLETE] == true }.first()
}

object PreferencesKeys {
    val OEM_ID = stringPreferencesKey("oem_id")
    val GUEST_UID = stringPreferencesKey("guest_uid")
    val ATTRIBUTION_COMPLETE = booleanPreferencesKey("attribution_complete")
}
