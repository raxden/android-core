package com.core.app.lifecycle.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

@PerActivity
class ViewDataBindingActivityLifecycle<TFragment : Fragment> @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

}