package com.core.app.helper

import android.app.Activity
import android.os.Bundle
import com.core.app.ui.project.list.ProjectListActivity
import com.core.app.ui.splash.SplashActivity
import com.raxdenstudios.navigation.NavigationManager

class NavigationHelper(private val mActivity: Activity) {

    fun launchSplash() {
        NavigationManager.Builder(mActivity)
                .navigateToClass(SplashActivity::class.java)
                .launchAndFinish()
    }

    fun launchProjectList() {
        NavigationManager.Builder(mActivity)
                .putData(getExtras())
                .navigateToClass(ProjectListActivity::class.java)
                .launch()
    }

    private fun getExtras(): Bundle {
        return mActivity.intent.extras ?: Bundle()
    }

}