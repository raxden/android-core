package com.core.app.base

import com.core.app.injector.scope.PerBroadcastReceiver
import com.core.commons.DisposableManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Provides base broadcastreceiver dependencies. This must be included in all broadcastreceivers modules, which must
 * provide a concrete implementation of [android.content.BroadcastReceiver].
 */
@Module
abstract class BaseBroadcastReceiverModule {

    @Module
    companion object {

        const val DISPOSABLE_BROADCAST_RECEIVER_MANAGER = "BaseServiceModule.disposableBroadcastReceiverManager"

        @JvmStatic
        @Provides
        @Named(DISPOSABLE_BROADCAST_RECEIVER_MANAGER)
        @PerBroadcastReceiver
        internal fun disposableBroadcastReceiverManager(): DisposableManager = DisposableManager()
    }
}
