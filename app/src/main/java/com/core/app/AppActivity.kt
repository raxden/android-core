package com.core.app

import androidx.databinding.ViewDataBinding
import com.core.app.base.BaseFragmentActivity

abstract class AppActivity<VDB : ViewDataBinding> : BaseFragmentActivity<VDB>()