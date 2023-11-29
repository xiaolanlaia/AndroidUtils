package com.wjf.androidutils.utils.persistent

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.wjf.androidutils.MyApplication
import com.wjf.androidutils.utils.CoroutineUtils.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/11/29 8:22
 *
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtils {

    fun putValue(key: String, value: Any) {
        launch {
            when (value) {
                is Int -> {
                    val keyValue = intPreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }

                is Double -> {
                    val keyValue = doublePreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }

                is String -> {
                    val keyValue = stringPreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Boolean -> {
                    val keyValue = booleanPreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Float -> {
                    val keyValue = floatPreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Long -> {
                    val keyValue = longPreferencesKey(key)
                    MyApplication.instance.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
            }
        }
    }

    fun getInt(key: String, result: (Int) -> Unit) {

        launch {
            val valueFlow: Flow<Int> = MyApplication.instance.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[intPreferencesKey(key)] ?: 0
                }
            result(valueFlow.first())
        }
    }


    fun getDouble(key: String, result: (Double) -> Unit) {

        launch {
            val valueFlow: Flow<Double> = MyApplication.instance.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[doublePreferencesKey(key)] ?: 0.00
                }
            result(valueFlow.first())
        }
    }


    fun getString(key: String, result: (String) -> Unit) {

        launch {
            val valueFlow: Flow<String> = MyApplication.instance.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[stringPreferencesKey(key)] ?: ""
                }
            result(valueFlow.first())
        }
    }

    fun getBoolean(key: String, result: (Boolean) -> Unit) {

        launch {
            val valueFlow: Flow<Boolean> = MyApplication.instance.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[booleanPreferencesKey(key)] ?: false
                }
            result(valueFlow.first())
        }
    }

}