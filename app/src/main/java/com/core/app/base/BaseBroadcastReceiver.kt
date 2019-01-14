package com.core.app.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.AndroidInjection

abstract class BaseBroadcastReceiver : BroadcastReceiver() {

//    @Inject
//    @field:Named(DISPOSABLE_BROADCAST_RECEIVER_MANAGER)
//    lateinit var mDisposableManager: DisposableManager

    override fun onReceive(context: Context, intent: Intent) {
        AndroidInjection.inject(this, context)
    }

}
