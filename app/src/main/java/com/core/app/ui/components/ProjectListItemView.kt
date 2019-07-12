package com.core.app.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.core.app.R
import com.core.app.base.component.BaseComponentBindingView
import com.core.app.databinding.ProjectListItemViewBinding
import com.core.domain.Project

class ProjectListItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : BaseComponentBindingView<ProjectListItemViewBinding>(context, attrs, defStyleAttr, defStyleRes) {

    override val mStyleable: IntArray
        get() = R.styleable.ProjectListItemView

    override val mLayoutId: Int
        get() = R.layout.project_list_item_view

    override fun onLoadStyledAttributes(attrs: TypedArray) {}

    override fun onBindingCreated(binding: ProjectListItemViewBinding) {}

    fun setItem(item: Project) {
        mBinding.item = item
        mBinding.executePendingBindings()
    }
}