package io.nativeblocks.cache

import androidx.annotation.LongDef

@LongDef(
    CacheTime.NONE_EXPIRE_TIME,
    CacheTime.ONE_SECOND,
    CacheTime.ONE_MINUTES,
    CacheTime.ONE_HOURS,
    CacheTime.ONE_DAYS,
    CacheTime.ONE_WEEKS,
    CacheTime.ONE_MONTH,
    CacheTime.ONE_YEAR
)
@Retention(AnnotationRetention.SOURCE)
annotation class CacheTimeType

object CacheTime {
    const val EXPIRE_TIME = -1L
    const val NONE_EXPIRE_TIME = 0L
    const val ONE_SECOND = 1000L
    const val ONE_MINUTES = 60 * ONE_SECOND
    const val ONE_HOURS = 60 * ONE_MINUTES
    const val ONE_DAYS = 24 * ONE_HOURS
    const val ONE_WEEKS = 7 * ONE_DAYS
    const val ONE_MONTH = 30 * ONE_DAYS
    const val ONE_YEAR = 365 * ONE_DAYS
}
