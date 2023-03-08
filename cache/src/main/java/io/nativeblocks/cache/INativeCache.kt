package io.nativeblocks.cache

interface INativeCache {
    fun push(key: String, value: String?, @CacheTimeType timeToLife: Long = CacheTime.NONE_EXPIRE_TIME)
    fun push(key: String, value: Boolean, @CacheTimeType timeToLife: Long = CacheTime.NONE_EXPIRE_TIME)
    fun push(key: String, value: Float, @CacheTimeType timeToLife: Long = CacheTime.NONE_EXPIRE_TIME)
    fun push(key: String, value: Int, @CacheTimeType timeToLife: Long = CacheTime.NONE_EXPIRE_TIME)
    fun push(key: String, value: Long, @CacheTimeType timeToLife: Long = CacheTime.NONE_EXPIRE_TIME)

    fun pull(key: String, defaultValue: String): String
    fun pull(key: String, defaultValue: Boolean): Boolean
    fun pull(key: String, defaultValue: Float): Float
    fun pull(key: String, defaultValue: Int): Int
    fun pull(key: String, defaultValue: Long): Long

    fun remove(key: String)
    fun clear()

    fun has(key: String): Boolean
    fun valid(key: String): Boolean
}

