package com.core.app.injector.module

import androidx.appcompat.app.AppCompatActivity
import com.core.app.injector.scope.PerActivity
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.fullscreen.FullScreenActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fullscreen.FullScreenInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
object InterceptorActivityModule {

    @JvmStatic
    @Provides
    @PerActivity
    internal fun inflateLayoutInterceptor(activity: AppCompatActivity): InflateLayoutInterceptor {
        return InflateLayoutActivityInterceptor(activity, activity as InflateLayoutInterceptorCallback)
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun autoInflateLayoutInterceptor(activity: AppCompatActivity): AutoInflateLayoutInterceptor {
        return AutoInflateLayoutActivityInterceptor(activity, activity as AutoInflateLayoutInterceptorCallback)
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun injectFragmentInterceptor(activity: AppCompatActivity): InjectFragmentInterceptor {
        return InjectFragmentActivityInterceptor(activity, activity as InjectFragmentInterceptorCallback<*>)
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun fullScreenInterceptor(activity: AppCompatActivity): FullScreenInterceptor {
        return FullScreenActivityInterceptor(activity)
    }

}
