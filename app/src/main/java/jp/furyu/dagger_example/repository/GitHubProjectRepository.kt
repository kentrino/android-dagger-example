package jp.furyu.dagger_example.repository

import io.reactivex.Flowable
import jp.furyu.dagger_example.dto.GitHubProject

interface GitHubProjectRepository {
    fun getProjectList(userId: String): Flowable<List<GitHubProject>>
}
