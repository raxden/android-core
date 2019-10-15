package com.core.app

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.core.app.base.viewmodel.BaseViewModel
import com.core.app.base.fragment.BaseViewModelFragment

abstract class AppFragmentDialog<VM : BaseViewModel, VDB : ViewDataBinding>
    : BaseViewModelFragment<VM, VDB>() {

    private var onDismissListener: MutableList<DialogInterface.OnDismissListener> = mutableListOf()

    override val layoutId: Int
        get() = javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
                .takeIf { it.isNotEmpty() }?.let {
                    resources.getIdentifier(it.replace("R.layout.", ""), "layout", context?.packageName)
                } ?: 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = super.onCreateDialog(savedInstanceState).apply {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        onDismissListener.clear()
        onDismissListener = mutableListOf()
        super.onDestroy()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener.forEach { it.onDismiss(dialog) }
    }

    fun addOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        this.onDismissListener.add(onDismissListener)
    }

    fun removeOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        this.onDismissListener.remove(onDismissListener)
    }
}