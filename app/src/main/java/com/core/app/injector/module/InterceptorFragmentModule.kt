package com.core.app.injector.module

import androidx.fragment.app.DialogFragment
import com.core.app.injector.scope.PerFragment
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewDialogFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
object InterceptorFragmentModule {

    @JvmStatic
    @Provides
    @PerFragment
    internal fun autoInflateViewInterceptor(dialogFragment: DialogFragment): AutoInflateViewInterceptor {
        return AutoInflateViewDialogFragmentInterceptor(dialogFragment, dialogFragment as AutoInflateViewInterceptorCallback)
    }

}
