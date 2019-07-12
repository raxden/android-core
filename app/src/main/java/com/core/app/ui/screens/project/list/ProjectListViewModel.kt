package com.core.app.ui.screens.project.list

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

    fun onItemSelected(position: Int) {
        projectList[position].also { projectSelected.postValue(it) }
    }

    fun retrieveProjectList() {
        getProjectListUseCase.execute()
                .map {
                    projectList = it
                    projectModelDataMapper.transform(it)
                }
                .subscribeWith(
                        onStart = {
                            loaderManager.push()
                        },
                        onError = {
                            loaderManager.pop()
                            mThrowable.value = it
                            projectModelList.value = emptyList()
                        },
                        onSuccess = {
                            loaderManager.pop()
                            projectModelList.value = it
                        },
                        onComplete = {
                            loaderManager.pop()
                            projectModelList.value = emptyList()
                        }
                )
                .addTo(compositeDisposable)
    }
}