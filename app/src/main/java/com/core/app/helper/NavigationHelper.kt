package com.core.app.helper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.core.app.ui.screens.login.LoginActivity
import com.core.app.ui.screens.project.list.ProjectListActivity
import com.core.app.ui.screens.splash.SplashActivity
import com.core.commons.AppUtils

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

    fun launchGooglePlay(finishCurrentActivity: Boolean = false) {
        AppUtils.getPackageName(activity).run {
            try {
                activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?userId=$this")))
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?userId=$this")))
            }
        }
        if (finishCurrentActivity) activity.finish()
    }
}