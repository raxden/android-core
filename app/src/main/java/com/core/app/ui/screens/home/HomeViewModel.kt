package com.core.app.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.app.base.BaseViewModel
import com.core.app.model.ProjectModel
import com.core.commons.Event
import com.core.commons.Resource
import com.core.commons.extension.notifyObservers
import com.core.commons.extension.subscribeWith
import com.core.commons.extension.toLiveData
import com.core.domain.Project
import com.core.domain.User
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

    private val mUser: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = mUser

    private val projectList: LiveData<Resource<List<Project>>> = Transformations
            .switchMap(mUser) { user ->
                getProjectListUseCase.execute(user.username)
                        .subscribeOn(Schedulers.io())
                        .toLiveData()
            }

    val projectModelList: LiveData<Resource<List<ProjectModel>>> = Transformations
            .map(projectList) {
                Resource(
                        it.status,
                        it.data?.map { project -> ProjectModel(project) },
                        it.throwable
                )
            }

    private val mProjectSelected = MutableLiveData<Event<Project>>()
    val projectSelected: LiveData<Event<Project>> = mProjectSelected

    private val mLogoutCompleted = MutableLiveData<Event<Boolean>>()
    val logoutCompleted: LiveData<Event<Boolean>> = mLogoutCompleted

    fun refresh() {
        mUser.notifyObservers()
    }

    fun setUser(user: User) {
        mUser.value = user
    }

    fun onItemSelected(position: Int) {
        projectList.value?.data?.get(position)?.let { mProjectSelected.value = Event(it) }
    }

    fun performLogout() {
        logoutUseCase.execute()
                .subscribeWith(onComplete = { mLogoutCompleted.value = Event(true) })
                .addTo(mCompositeDisposable)
    }
}