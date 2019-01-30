package com.core.app

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.core.app.base.mvvm.BaseViewFragment
import com.core.app.base.mvvm.BaseViewModel

abstract class AppFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback: BaseViewFragment.BaseViewFragmentCallback>: BaseViewFragment<VM, VDB, TCallback>() {

    override fun onViewBinded(viewDataBinding: VDB, view: View, savedInstanceState: Bundle?) {
    }

    override fun observeViewModel(viewModel: VM) {
    }

}