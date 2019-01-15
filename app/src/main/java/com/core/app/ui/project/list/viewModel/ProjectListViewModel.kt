package com.core.app.ui.project.list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.mvvm.BaseViewModel
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var getProjectListUseCase: GetProjectListUseCase

    private val mProjectList: MutableLiveData<List<Project>> = MutableLiveData()

    fun retrieveProjectList(userID: String) {
        mDisposableManager.add(getProjectListUseCase.execute(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableMaybeObserver<List<Project>>() {
                    override fun onStart() {
                        mLoaderManager.push()
                    }

                    override fun onSuccess(t: List<Project>) {
                        mLoaderManager.pull()
                        mProjectList.value = t
                    }

                    override fun onComplete() {
                        mLoaderManager.pull()
                        mProjectList.value = emptyList()
                    }

                    override fun onError(e: Throwable) {
                        mLoaderManager.pull()
//                        showError()
                    }
                }))
    }

    fun getProjectList() : LiveData<List<Project>> {
        return mProjectList
    }

}