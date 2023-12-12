package com.wjf.moduleutils.persistent

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
import com.wjf.moduleutils.CoroutineUtils
import com.wjf.moduleutils.ModuleUtilsConstant
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

class DataStoreUtils {
    companion object{
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { DataStoreUtils() }
    }

    fun putValue(key: String, value: Any) {
        CoroutineUtils.instance.launch {
            when (value) {
                is Int -> {
                    val keyValue = intPreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }

                is Double -> {
                    val keyValue = doublePreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }

                is String -> {
                    val keyValue = stringPreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Boolean -> {
                    val keyValue = booleanPreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Float -> {
                    val keyValue = floatPreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
                is Long -> {
                    val keyValue = longPreferencesKey(key)
                    ModuleUtilsConstant.moduleUtilsContext.dataStore.edit { settings ->
                        settings[keyValue] = value
                    }
                }
            }
        }
    }

    fun getInt(key: String, result: (Int) -> Unit) {

        CoroutineUtils.instance.launch {
            val valueFlow: Flow<Int> = ModuleUtilsConstant.moduleUtilsContext.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[intPreferencesKey(key)] ?: 0
                }
            result(valueFlow.first())
        }
    }


    fun getDouble(key: String, result: (Double) -> Unit) {

        CoroutineUtils.instance.launch {
            val valueFlow: Flow<Double> = ModuleUtilsConstant.moduleUtilsContext.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[doublePreferencesKey(key)] ?: 0.00
                }
            result(valueFlow.first())
        }
    }


    fun getString(key: String, result: (String) -> Unit) {

        CoroutineUtils.instance.launch {
            val valueFlow: Flow<String> = ModuleUtilsConstant.moduleUtilsContext.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[stringPreferencesKey(key)] ?: ""
                }
            result(valueFlow.first())
        }
    }

    fun getBoolean(key: String, result: (Boolean) -> Unit) {

        CoroutineUtils.instance.launch {
            val valueFlow: Flow<Boolean> = ModuleUtilsConstant.moduleUtilsContext.dataStore.data
                .map { preferences ->
                    // No type safety.
                    preferences[booleanPreferencesKey(key)] ?: false
                }
            result(valueFlow.first())
        }
    }

}