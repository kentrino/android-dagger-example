package jp.furyu.dagger_example.util

import timber.log.Timber

fun defaultLogErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }
