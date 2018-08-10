package jp.furyu.dagger_example.di

import dagger.Binds
import dagger.Module
import jp.furyu.dagger_example.repository.GitHubProjectRepository
import jp.furyu.dagger_example.repository.GitHubProjectRepositoryImpl

@Module
interface RepositoryModule {
    @Binds
    fun bindGitHubProjectRepository(repository: GitHubProjectRepositoryImpl): GitHubProjectRepository
}
