package jp.furyu.dagger_example.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import jp.furyu.dagger_example.App
import javax.inject.Singleton


@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {
    @Binds
    @Singleton
    // Singleton annotation isn't necessary (in this case since Application instance is unique)
    // but is here for convention.
    abstract fun application(app: App): App
}
