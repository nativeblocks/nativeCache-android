package io.nativeblocks.nativecache

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.nativeblocks.cache.NativeMemoryCacheProvider
import io.nativeblocks.cache.NativeStorageCacheProvider

class MainActivity : AppCompatActivity() {

    private val storageCache = NativeStorageCacheProvider(this)
    private val memoryCache = NativeMemoryCacheProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storageCache.push("name", "John")
        val nameInStorage = storageCache.pull("name", "unKnown")
        print(nameInStorage)

        memoryCache.push("name", "John")
        val nameInMemory = memoryCache.pull("name", "unKnown")
        print(nameInMemory)
    }
}