package com.core.app.ui.project.list.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.mvvm.BaseViewModel
import com.core.commons.extension.subscribeWith
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectListViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var getProjectListUseCase: GetProjectListUseCase

    private val mProjectList: MutableLiveData<List<Project>> = MutableLiveData()

    fun retrieveProjectList(userID: String) {
        getProjectListUseCase.execute(userID)
                .subscribeWith(
                        onStart = { mLoaderManager.push("retreive project list") },
                        onError = {
                            mLoaderManager.pop()
                            mErrorManager.set(it)
                        },
                        onSuccess = {
                            mLoaderManager.pop()
                            mProjectList.value = it
                        },
                        onComplete = {
                            mLoaderManager.pop()
                            mProjectList.value = emptyList()
                        }
                )
                .addTo(mCompositeDisposable)
    }

    fun getProjectList(): LiveData<List<Project>> {
        return mProjectList
    }
}