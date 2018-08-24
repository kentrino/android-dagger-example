package jp.furyu.dagger_example.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T : Any?> Observable<T>.toLiveData(strategy: BackpressureStrategy = BackpressureStrategy.LATEST) = toFlowable(strategy).toLiveData()
