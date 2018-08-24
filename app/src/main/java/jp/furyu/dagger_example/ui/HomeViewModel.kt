package jp.furyu.dagger_example.ui


import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jp.furyu.dagger_example.dto.GitHubProject
import jp.furyu.dagger_example.repository.GitHubProjectRepository
import jp.furyu.dagger_example.util.Result
import jp.furyu.dagger_example.util.defaultLogErrorHandler
import jp.furyu.dagger_example.util.toLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val repository: GitHubProjectRepository
) : ViewModel(), LifecycleObserver {

    private val disposable: CompositeDisposable = CompositeDisposable()

    // TODO: by lazy とは
    val projectList: LiveData<Result<List<GitHubProject>>> by lazy {
        repository.getProjectList("kentrino")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { Result.success(it) }
                .doOnError(defaultLogErrorHandler())
                .onErrorReturn { Result.failure(it) }
                .startWith(Result.loading())
                .toLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
