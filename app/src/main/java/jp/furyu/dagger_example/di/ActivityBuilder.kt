package jp.furyu.dagger_example.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.furyu.dagger_example.ui.MainActivity

@Module
internal abstract class ActivityBuilder {
    // fragmentはMainModule::classが提供
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainInjector(): MainActivity
}
