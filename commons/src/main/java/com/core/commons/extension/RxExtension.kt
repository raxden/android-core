package com.core.commons.extension

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private val onStartStub: () -> Unit = {}
private val onErrorStub: (e: Throwable) -> Unit = { _ -> }
private val onSuccessStub: (Any) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

fun <T : Any> Single<T>.subscribeWith(
        onStart: () -> Unit = onStartStub,
        onError: (e: Throwable) -> Unit = onErrorStub,
        onSuccess: (t: T) -> Unit = onSuccessStub
): Disposable = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<T>() {
            override fun onStart() {
                onStart()
            }

            override fun onError(e: Throwable) {
                onError(e)
            }

            override fun onSuccess(t: T) {
                onSuccess(t)
            }
        })

fun <T : Any> Maybe<T>.subscribeWith(
        onStart: () -> Unit = onStartStub,
        onError: (e: Throwable) -> Unit = onErrorStub,
        onSuccess: (t: T) -> Unit = onSuccessStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableMaybeObserver<T>() {
            override fun onStart() {
                onStart()
            }

            override fun onSuccess(t: T) {
                onSuccess(t)
            }

            override fun onComplete() {
                onComplete()
            }

            override fun onError(e: Throwable) {
                onError(e)
            }
        })

fun Completable.subscribeWith(
        onStart: () -> Unit = onStartStub,
        onError: (e: Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableCompletableObserver() {
            override fun onStart() {
                onStart()
            }

            override fun onComplete() {
                onComplete()
            }

            override fun onError(e: Throwable) {
                onError(e)
            }
        })