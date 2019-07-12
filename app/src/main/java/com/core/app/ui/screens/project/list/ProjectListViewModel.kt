package com.core.app.ui.screens.project.list

import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase
) : BaseViewModel(){

    private var projectList: List<Project> = emptyList()

    val projectModelList: MutableLiveData<List<Project>> = MutableLiveData()
    val projectSelected: MutableLiveData<Project> = MutableLiveData()

    fun onItemSelected(position: Int) {
        projectList[position].also { projectSelected.postValue(it) }
    }

    fun retrieveProjectList() {
        getProjectListUseCase.execute()
                .subscribeWith(
                        onStart = {
                            mLoaderManager.push()
                        },
                        onError = {
                            mLoaderManager.pop()
                            mThrowable.value = it
                            projectModelList.value = emptyList()
                        },
                        onSuccess = {
                            mLoaderManager.pop()
                            projectModelList.value = it
                        },
                        onComplete = {
                            mLoaderManager.pop()
                            projectModelList.value = emptyList()
                        }
                )
                .addTo(mCompositeDisposable)
    }
}