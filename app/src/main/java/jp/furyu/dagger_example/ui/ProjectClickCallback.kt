package jp.furyu.dagger_example.ui

import jp.furyu.dagger_example.dto.GitHubProject

interface ProjectClickCallback {
    fun onClick(project: GitHubProject): Void
}
