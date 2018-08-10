package jp.furyu.dagger_example

import android.net.Network
import io.reactivex.Flowable
import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.internal.operators.flowable.FlowableReplay.observeOn
import io.reactivex.schedulers.Schedulers
import jp.furyu.dagger_example.repository.GitHubProjectRepository
import javax.inject.Inject
import jp.furyu.dagger_example.DaggerTestComponent
import jp.furyu.dagger_example.di.NetworkModule
import jp.furyu.dagger_example.dto.GitHubProject

class TestMain {
    @Inject
    lateinit var gitHubProjectRepository: GitHubProjectRepository

    private val component: TestComponent

    init {
        component = DaggerTestComponent
                .builder()
                .networkModule(NetworkModule()).build()
        component.inject(this)
    }

    fun testGetProjectList() {
        Flowable.just("kentrino")
                .observeOn(Schedulers.io())
                .flatMap(gitHubProjectRepository::getProjectList)
                .publish().connect()
    }
}
