package io.nativeblocks.nativecache

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.nativeblocks.cache.NATIVE_LOCAL_CACHE
import io.nativeblocks.cache.NativeLocalCacheProvider

class MainActivity : AppCompatActivity() {

    val localCache =
        NativeLocalCacheProvider(getSharedPreferences(NATIVE_LOCAL_CACHE, Context.MODE_PRIVATE))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        localCache.push("name", "John")
        val name = localCache.pull("name", "unKnown")
        print(name)
    }
}