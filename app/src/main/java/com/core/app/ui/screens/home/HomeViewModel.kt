package com.core.app.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.Event
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase
) : BaseViewModel() {

    private val mProjectList = MutableLiveData<List<Project>>()
    private val mProjectSelected = MutableLiveData<Event<Project>>()

    val projectList: LiveData<List<Project>> get() = mProjectList
    val projectSelected: LiveData<Event<Project>> get() = mProjectSelected

    init {
        retrieveProjectList()
    }

    fun onItemSelected(project: Project) {
        mProjectSelected.value = Event(project)
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