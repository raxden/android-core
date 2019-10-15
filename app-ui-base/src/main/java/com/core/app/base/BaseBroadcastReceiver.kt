package com.core.app.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.core.app.base.broadcast.BaseBroadcastReceiverModule.Companion.DISPOSABLE_BROADCAST_RECEIVER_MANAGER
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

abstract class BaseBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        AndroidInjection.inject(this, context)
    }
}
