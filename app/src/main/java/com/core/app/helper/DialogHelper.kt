package com.core.app.helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.core.commons.extension.getExtras
import timber.log.Timber

class DialogHelper {

    private val extras: Bundle
    private val fragmentManager: FragmentManager

    constructor(activity: AppCompatActivity) {
        extras = activity.getExtras() ?: Bundle()
        fragmentManager = activity.supportFragmentManager
    }

    constructor(fragment: Fragment) {
        extras = fragment.arguments ?: Bundle()
        fragmentManager = fragment.childFragmentManager
    }

    private fun showDialog(dialog: DialogFragment, tag: String) {
        try {
            if (fragmentManager.findFragmentByTag(tag) != null) return
            val ft = fragmentManager.beginTransaction()
            ft.add(dialog, tag)
            ft.commitAllowingStateLoss()
            //                show(fragmentManager, tag);
        } catch (e: IllegalStateException) {
            Timber.e(e)
        }
    }
}