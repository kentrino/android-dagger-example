package jp.furyu.dagger_example

import dagger.Component
import jp.furyu.dagger_example.di.RepositoryModule
import jp.furyu.dagger_example.repository.GitHubProjectRepository


// @Singleton
@Component(modules = [
    RepositoryModule::class
])
interface TestComponent {
    fun provideGitHubProjectRepository(): GitHubProjectRepository
    fun inject(main: TestMain)
}
