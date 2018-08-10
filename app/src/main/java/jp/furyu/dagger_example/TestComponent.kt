package jp.furyu.dagger_example

import dagger.Component
import jp.furyu.dagger_example.di.NetworkModule
import jp.furyu.dagger_example.di.RepositoryModule
import jp.furyu.dagger_example.repository.GitHubProjectRepository
import javax.inject.Singleton


@Singleton
@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class
])
interface TestComponent {
    fun inject(main: TestMain)
}
