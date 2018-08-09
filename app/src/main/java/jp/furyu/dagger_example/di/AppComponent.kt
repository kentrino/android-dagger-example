package jp.furyu.dagger_example.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import jp.furyu.dagger_example.App
import javax.inject.Singleton


@Singleton
@Component(modules = [
    NetworkModule::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent

        // fun network(network: NetworkModule): Builder
        // fun database(database: DatabaseModule): Builder
    }

    override fun inject(app: App)
}
