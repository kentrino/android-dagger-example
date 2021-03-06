package jp.furyu.dagger_example.repository

import jp.furyu.dagger_example.api.GitHubApi
import javax.inject.Inject

class GitHubProjectRepositoryImpl @Inject constructor(private val gitHubApi: GitHubApi): GitHubProjectRepository {
    override fun getProjectList(userId: String) = gitHubApi.getProjectList(userId)
}
