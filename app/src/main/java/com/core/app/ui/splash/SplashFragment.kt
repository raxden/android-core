package com.core.app.ui.splash

import android.os.Bundle
import com.core.app.base.BaseFragment
import com.core.app.base.mvvm.BaseViewFragment
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback

class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

}