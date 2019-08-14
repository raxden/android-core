package com.core.app.ui.screens.login

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.databinding.LoginActivityBinding
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.app.util.KeyboardManager
import com.core.app.util.KeyboardStatus
import com.core.commons.extension.getExtras
import com.core.commons.extension.subscribeWith
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject

class LoginActivity : AppActivity<LoginViewModel, LoginActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<LoginFragment> {

    companion object {
        fun intent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    @Inject
    lateinit var mKeyboardManager: KeyboardManager

    private var mLoginFragment: LoginFragment? = null
    private var mHandler = Handler()
    private var mCurtainReady = false
    private var mAnimatorIsWorking = false
    private var mCurrentCurtainPercent: Float = 0.0f

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
        viewModel.userLogged.observe(owner, Observer {
            animateCurtain(mCurrentCurtainPercent, 1.0f, 400) {
                navigationHelper.launchHome(it, true)
            }
        })
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        mKeyboardManager.status()
                .subscribeWith(onNext = {
                    if (mCurtainReady) when (it) {
                        KeyboardStatus.OPEN -> animateCurtain(mCurrentCurtainPercent, 0.25f, 250)
                        KeyboardStatus.CLOSED -> animateCurtain(mCurrentCurtainPercent, 0.60f, 250)
                    }
                }
                ).addTo(compositeDisposable)

        mHandler.postDelayed({
            animateCurtain(1.0f, 0.60f, 400)
            mCurtainReady = true
        }, 250)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): LoginFragment = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) { mLoginFragment = fragment }

    // =============================================================================================

    @Synchronized
    private fun animateCurtain(from: Float, to: Float, duration: Long, onComplete: () -> Unit = {}) {
        if (mAnimatorIsWorking) return
        ValueAnimator.ofFloat(from, to).run {
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {
                    mAnimatorIsWorking = false
                }

                override fun onAnimationStart(animation: Animator?) {
                    mAnimatorIsWorking = true
                }

                override fun onAnimationEnd(animation: Animator?) {
                    mAnimatorIsWorking = false
                    onComplete()
                }
            })
            addUpdateListener { animator ->
                header_container_view.layoutParams = (header_container_view.layoutParams as ConstraintLayout.LayoutParams).apply {
                    matchConstraintPercentHeight = animator.animatedValue as Float
                    mCurrentCurtainPercent = matchConstraintPercentHeight
                }
            }
            interpolator = AccelerateDecelerateInterpolator()
            this.duration = duration
            start()
        }
    }
}
