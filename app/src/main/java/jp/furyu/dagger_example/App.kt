package jp.furyu.dagger_example

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import jp.furyu.dagger_example.di.DaggerAppComponent
import jp.furyu.dagger_example.di.NetworkModule
import jp.furyu.dagger_example.di.RepositoryModule


class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    // TODO: outとは
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
