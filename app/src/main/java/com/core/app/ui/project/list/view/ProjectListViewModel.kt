package com.core.app.ui.project.list.view

import androidx.lifecycle.MutableLiveData
import com.core.app.base.mvvm.BaseViewModel
import com.core.app.ui.project.list.adapter.ProjectListAdapter
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(
        private val mGetProjectListUseCase: GetProjectListUseCase
) : BaseViewModel(), ProjectListAdapter.AdapterCallback {

    val projectList: MutableLiveData<List<Project>> = MutableLiveData()
    val projectSelected: MutableLiveData<Project> = MutableLiveData()

    override fun onCreated() {
        retrieveProjectList()
    }

    override fun itemSelected(position: Int) {
        projectList.value?.get(position).also {
            projectSelected.postValue(it)
        }
    }

    private fun retrieveProjectList() {
        mGetProjectListUseCase.execute()
                .subscribeWith(
                        onStart = {
                            mLoaderManager.push("retrieve project list")
                        },
                        onError = {
                            mLoaderManager.pop()
                            mErrorManager.set(it)
                        },
                        onSuccess = {
                            mLoaderManager.pop()
                            projectList.value = it
                        },
                        onComplete = {
                            mLoaderManager.pop()
                            projectList.value = emptyList()
                        }
                )
                .addTo(mCompositeDisposable)
    }
}