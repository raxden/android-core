package com.core.app.ui.screens.project.list.view

import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.app.model.ProjectModel
import com.core.app.model.mapper.ProjectModelDataMapper
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase,
        private val projectModelDataMapper: ProjectModelDataMapper
) : BaseViewModel(){

    private var projectList: List<Project> = emptyList()

    val projectModelList: MutableLiveData<List<ProjectModel>> = MutableLiveData()
    val projectSelected: MutableLiveData<Project> = MutableLiveData()

    override fun onCreated() {
        retrieveProjectList()
    }

    fun onItemSelected(position: Int) {
        projectList[position].also { projectSelected.postValue(it) }
    }

    private fun retrieveProjectList() {
        getProjectListUseCase.execute()
                .map {
                    projectList = it
                    projectModelDataMapper.transform(it)
                }
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