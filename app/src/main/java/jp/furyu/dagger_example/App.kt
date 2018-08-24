package jp.furyu.dagger_example

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import jp.furyu.dagger_example.di.DaggerAppComponent
import jp.furyu.dagger_example.di.NetworkModule
import jp.furyu.dagger_example.di.RepositoryModule


class App : DaggerApplication() {
// TODO: これは必要？
//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//        androidInjector = DaggerAppComponent.builder()
//                .application(this)
//                .database(databaseModule())
//                .network(networkModule())
//                .build()
//    }

    override fun onCreate() {
        super.onCreate()
    }

    // TODO: outとは
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
    // TODO: これは必要？
    protected open fun networkModule(): NetworkModule = NetworkModule()
}
