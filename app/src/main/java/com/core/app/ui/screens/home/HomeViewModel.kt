package com.core.app.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.app.base.BaseViewModel
import com.core.app.model.ProjectModel
import com.core.commons.Event
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase,
        private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    private val mProjectList = MutableLiveData<List<Project>>()
    val projectModelList: LiveData<List<ProjectModel>> = Transformations
            .map(mProjectList) {
                it.map { project -> ProjectModel(project) }
            }

    private val mProjectSelected = MutableLiveData<Event<Project>>()
    val projectSelected: LiveData<Event<Project>> = mProjectSelected

    private val mLogoutCompleted = MutableLiveData<Event<Boolean>>()
    val logoutCompleted: LiveData<Event<Boolean>> = mLogoutCompleted

    init {
        retrieveProjectList()
    }

    fun test() {
        getProjectListUseCase.test2("raxden")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            Timber.d("$it.status")
                            printData("onNext received: ", it.data)
                        }
                )
                .addTo(mCompositeDisposable)
    }

    private fun printData(message: String, data: Any? = null) {
        val threadName = Thread.currentThread().name
        (data as? List<*>)?.let { list ->
            if (list.isEmpty())
                Timber.d("[$threadName]: $message - empty list ")
            else
                Timber.d("[$threadName]: $message - items(${list.size}) ")
        } ?: Timber.d("[$threadName]: $message ")
    }

    fun onItemSelected(position: Int) {
        mProjectList.value?.get(position)?.let { mProjectSelected.value = Event(it) }
    }

    fun performLogout() {
        logoutUseCase.execute()
                .subscribeWith(onComplete = { mLogoutCompleted.value = Event(true) })
                .addTo(mCompositeDisposable)
    }

    fun retrieveProjectList() {
        getProjectListUseCase.execute()
                .subscribeWith(
                        onStart = {
                            mLoaderManager.push()
                        },
                        onError = {
                            mThrowable.value = it
                            mProjectList.value = emptyList()
                            mLoaderManager.pop()
                        },
                        onSuccess = {
                            mProjectList.value = it
                            mLoaderManager.pop()
                        },
                        onComplete = {
                            mProjectList.value = emptyList()
                            mLoaderManager.pop()
                        }
                )
                .addTo(mCompositeDisposable)
    }
}