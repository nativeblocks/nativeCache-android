package io.nativeblocks.cache

import android.content.Context
import android.content.SharedPreferences
import io.nativeblocks.cache.CacheTime.EXPIRE_TIME
import io.nativeblocks.cache.CacheTime.NONE_EXPIRE_TIME

const val NATIVE_STORAGE_CACHE = "nativeblocks_sdk_storage"

class NativeStorageCacheProvider constructor(
    context: Context
) : INativeCache {

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(NATIVE_STORAGE_CACHE, Context.MODE_PRIVATE)
    }

    private val postFixTimeToLife = "timeToLife"

    override fun push(key: String, value: String?, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun push(key: String, value: Boolean, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun push(key: String, value: Float, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun push(key: String, value: Int, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun push(key: String, value: Long, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun pull(key: String, defaultValue: String): String {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun pull(key: String, defaultValue: Boolean): Boolean {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun pull(key: String, defaultValue: Float): Float {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun pull(key: String, defaultValue: Int): Int {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun pull(key: String, defaultValue: Long): Long {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    override fun has(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun valid(key: String): Boolean {
        if (!has(key)) return false

        return when (val timeToLife =
            sharedPreferences.getLong(key + postFixTimeToLife, EXPIRE_TIME)) {
            EXPIRE_TIME -> false
            NONE_EXPIRE_TIME -> true
            else -> System.currentTimeMillis() < timeToLife
        }
    }

    private fun addTimeToLife(key: String, timeToLife: Long) {
        val time = if (timeToLife == NONE_EXPIRE_TIME)
            timeToLife
        else
            System.currentTimeMillis() + timeToLife

        sharedPreferences.edit()
            .putLong(key + postFixTimeToLife, time)
            .apply()
    }
}