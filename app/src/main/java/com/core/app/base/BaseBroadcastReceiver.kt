package com.core.app.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.core.app.base.BaseBroadcastReceiverModule.Companion.DISPOSABLE_BROADCAST_RECEIVER_MANAGER
import com.core.commons.DisposableManager
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Named

abstract class BaseBroadcastReceiver : BroadcastReceiver() {

    @Inject
    @field:Named(DISPOSABLE_BROADCAST_RECEIVER_MANAGER)
    lateinit var mDisposableManager: DisposableManager

    override fun onReceive(context: Context, intent: Intent) {
        AndroidInjection.inject(this, context)
    }
}
