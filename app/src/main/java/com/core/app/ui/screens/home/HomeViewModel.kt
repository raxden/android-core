package com.core.app.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.app.base.BaseViewModel
import com.core.app.model.ProjectModel
import com.core.app.util.OpenForTesting
import com.core.commons.Event
import com.core.commons.extension.notifyObservers
import com.core.domain.Project
import com.core.domain.User
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@OpenForTesting
class HomeViewModel @Inject constructor(
        private val getProjectListUseCase: GetProjectListUseCase,
        private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    private val mProjectSelected = MutableLiveData<Event<Project>>()
    val projectSelected: LiveData<Event<Project>> = mProjectSelected

    private val mLogoutCompleted = MutableLiveData<Event<Boolean>>()
    val logoutCompleted: LiveData<Event<Boolean>> = mLogoutCompleted

    private val mUser = MutableLiveData<User>()
    val user: LiveData<User> = mUser

    private val mProjectList = MediatorLiveData<List<Project>>().apply {
        addSource(mUser) {
            mUser.value?.let { retrieveProjectList(it.username) }
        }
    }
    val projectModelList: LiveData<List<ProjectModel>> = Transformations
            .map(mProjectList) { it.map { project -> ProjectModel(project) } }

    fun setUser(user: User) {
        mUser.value = user
    }

    fun refresh() {
        mUser.notifyObservers()
    }

    fun onItemSelected(position: Int) {
        mProjectList.value?.get(position)?.let { mProjectSelected.value = Event(it) }
    }

    fun performLogout() {
        logoutUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onComplete = { mLogoutCompleted.value = Event(true) })
                .addTo(mCompositeDisposable)
    }

    private fun retrieveProjectList(username: String) {
        getProjectListUseCase.execute(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mLoaderManager.push() }
                .subscribeBy(
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