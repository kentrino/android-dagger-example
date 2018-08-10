package jp.furyu.dagger_example

import jp.furyu.dagger_example.repository.GitHubProjectRepository
import javax.inject.Inject
import jp.furyu.dagger_example.DaggerTestComponent

class TestMain {
    @Inject
    lateinit var gitHubProjectRepository: GitHubProjectRepository

    private val component: TestComponent

    init {
        component = DaggerTestComponent.builder().build()
        component.inject(this)
    }
}
