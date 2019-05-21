package com.core.app.helper

import android.app.Activity
import androidx.core.content.ContextCompat
import com.core.app.ui.screens.login.LoginActivity
import com.core.app.ui.screens.project.list.ProjectListActivity
import com.core.app.ui.screens.splash.SplashActivity

class NavigationHelper(private val activity: Activity) {

    fun launchSplash() {
        SplashActivity.intent(activity).run {
            ContextCompat.startActivity(activity, this, null)
            activity.finish()
        }
    }

    fun launchLogin(finishCurrentActivity: Boolean = false) {
        LoginActivity.intent(activity).run {
            ContextCompat.startActivity(activity, this, null)
        }
        if (finishCurrentActivity) activity.finish()
    }

    fun launchProjectList(finishCurrentActivity: Boolean = false) {
        ProjectListActivity.intent(activity).run {
            ContextCompat.startActivity(activity, this, null)
        }
        if (finishCurrentActivity) activity.finish()
    }
}