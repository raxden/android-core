package com.core.app.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.Event
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase,
        private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    private val mProjectList = MutableLiveData<List<Project>>()
    val projectList: LiveData<List<Project>> = mProjectList

    private val mProjectSelected = MutableLiveData<Event<Project>>()
    val projectSelected: LiveData<Event<Project>> = mProjectSelected

    private val mLogoutCompleted = MutableLiveData<Event<Boolean>>()
    val logoutCompleted: LiveData<Event<Boolean>> = mLogoutCompleted

    init {
        retrieveProjectList()
    }

    fun onItemSelected(project: Project) {
        mProjectSelected.value = Event(project)
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