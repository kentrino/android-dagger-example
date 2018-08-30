package jp.furyu.dagger_example.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.furyu.dagger_example.ui.HomeFragment

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeTopFragmentInjector(): HomeFragment
}
