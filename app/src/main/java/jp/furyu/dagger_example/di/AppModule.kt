package jp.furyu.dagger_example.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import jp.furyu.dagger_example.App
import javax.inject.Singleton


@Module(includes = [
    ViewModelModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
abstract class AppModule {
}
