package jp.furyu.dagger_example.di

import dagger.Module
import dagger.Provides
import jp.furyu.dagger_example.App
import jp.furyu.dagger_example.util.Memory
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {
    open fun buildOkHttpClient(app: App): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(10L, TimeUnit.SECONDS)
                    .writeTimeout(10L, TimeUnit.SECONDS)
                    .readTimeout(30L, TimeUnit.SECONDS)
                    .cache(Cache(File(app.cacheDir, "OkCache"),
                            Memory.calcCacheSize(app, .25f)))
                    .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(app: App): OkHttpClient = buildOkHttpClient(app)
}
