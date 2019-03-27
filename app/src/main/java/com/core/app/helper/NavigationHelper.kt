package com.core.app.helper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.core.app.ui.login.LoginActivity
import com.core.app.ui.project.list.ProjectListActivity
import com.core.app.ui.splash.SplashActivity

class NavigationHelper(private val mActivity: Activity) {

    fun launchSplash() {
        Intent(mActivity, SplashActivity::class.java).also { intent ->
            ContextCompat.startActivity(mActivity, intent, null)
            mActivity.finish()
        }
    }

    fun launchLogin(extras: Bundle? = null, finishCurrentView: Boolean = false) {
        Intent(mActivity, LoginActivity::class.java).also { intent ->
            extras?.let { intent.putExtras(it) }
            ContextCompat.startActivity(mActivity, intent, null)
        }
        if (finishCurrentView) mActivity.finish()
    }

    fun launchProjectList(extras: Bundle? = null, finishCurrentView: Boolean = false) {
        Intent(mActivity, ProjectListActivity::class.java).also { intent ->
            extras?.let { intent.putExtras(it) }
            ContextCompat.startActivity(mActivity, intent, null)
        }
        if (finishCurrentView) mActivity.finish()
    }
}