package io.nativeblocks.cache

import io.nativeblocks.cache.CacheTime.EXPIRE_TIME
import io.nativeblocks.cache.CacheTime.NONE_EXPIRE_TIME

class NativeMemoryCacheProvider : INativeCache {

    private val memory = hashMapOf<String, String>()

    private val postFixTimeToLife = "timeToLife"

    override fun push(key: String, value: String?, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        memory[key] = value.toString()
    }

    override fun push(key: String, value: Boolean, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        memory[key] = value.toString()
    }

    override fun push(key: String, value: Float, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        memory[key] = value.toString()
    }

    override fun push(key: String, value: Int, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        memory[key] = value.toString()
    }

    override fun push(key: String, value: Long, timeToLife: Long) {
        addTimeToLife(key, timeToLife)
        memory[key] = value.toString()
    }

    override fun pull(key: String, defaultValue: String): String {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return memory[key] ?: defaultValue
    }

    override fun pull(key: String, defaultValue: Boolean): Boolean {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return memory[key]?.toBoolean() ?: defaultValue
    }

    override fun pull(key: String, defaultValue: Float): Float {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return memory[key]?.toFloat() ?: defaultValue
    }

    override fun pull(key: String, defaultValue: Int): Int {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return memory[key]?.toInt() ?: defaultValue
    }

    override fun pull(key: String, defaultValue: Long): Long {
        if (!valid(key)) {
            remove(key + postFixTimeToLife)
            remove(key)
            return defaultValue
        }
        return memory[key]?.toLong() ?: defaultValue
    }

    override fun remove(key: String) {
        memory.remove(key)
    }

    override fun clear() {
        memory.clear()
    }

    override fun has(key: String): Boolean {
        return memory[key] != null
    }

    override fun valid(key: String): Boolean {
        if (!has(key)) return false

        return when (val timeToLife = memory[key + postFixTimeToLife]?.toLong() ?: EXPIRE_TIME) {
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

        memory[key + postFixTimeToLife] = time.toString()
    }
}