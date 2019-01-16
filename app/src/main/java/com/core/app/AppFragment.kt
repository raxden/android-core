package com.core.app

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.core.app.base.mvvm.BaseViewFragment

abstract class AppFragment<VM : ViewModel, VDB : ViewDataBinding>: BaseViewFragment<VM, VDB>()