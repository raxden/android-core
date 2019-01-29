package com.core.app.helper

import android.app.Activity
import android.os.Bundle
import com.core.app.ui.login.LoginActivity
import com.core.app.ui.project.list.ProjectListActivity
import com.core.app.ui.splash.SplashActivity
import com.raxdenstudios.navigation.NavigationManager

class NavigationHelper(private val mActivity: Activity) {

    fun launchSplash() {
        NavigationManager.Builder(mActivity)
                .navigateToClass(SplashActivity::class.java)
                .launchAndFinish()
    }

    fun launchLoginAndFinishCurrentView() {
        NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateToClass(LoginActivity::class.java)
                .launchAndFinish()
    }

    fun launchProjectListAndFinishCurrentView() {
        NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateToClass(ProjectListActivity::class.java)
                .launchAndFinish()
    }

    private fun getExtras(): Bundle {
        return mActivity.intent.extras ?: Bundle()
    }

}