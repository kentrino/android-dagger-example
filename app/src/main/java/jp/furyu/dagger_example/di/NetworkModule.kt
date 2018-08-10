package jp.furyu.dagger_example.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import jp.furyu.dagger_example.App
import jp.furyu.dagger_example.api.GitHubApi
import jp.furyu.dagger_example.util.ApplicationJsonAdapterFactory
import jp.furyu.dagger_example.util.InstantAdapter
import jp.furyu.dagger_example.util.Memory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class NetworkModule {
    open fun buildOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(10L, TimeUnit.SECONDS)
                    .writeTimeout(10L, TimeUnit.SECONDS)
                    .readTimeout(30L, TimeUnit.SECONDS)
                    // .cache(Cache(File(app.cacheDir, "OkCache"), Memory.calcCacheSize(app, .25f)))
                    .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = buildOkHttpClient()

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .add(InstantAdapter.INSTANCE)
            .build()

    // TODO: @Named(hoge)とあったのを消したが何だったのか
    @Provides
    @Singleton
    @Named(GitHubApi.NAME)
    fun provideRetrofitGithub(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            // TODO: このAPIのURLは環境によって変えたい場合、どうするべきか
            // flavors.gradleに書いてあるサンプルあり
            .baseUrl(GitHubApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    // TODO: Namedで複数providerがあった場合に対処できる？
    @Provides
    @Singleton
    fun provideGitHubApi(@Named(GitHubApi.NAME) retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)
}
