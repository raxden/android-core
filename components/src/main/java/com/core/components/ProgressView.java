package com.core.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProgressView extends BaseComponentView {

    private View mContainer;
    private ContentLoadingProgressBar mContentLoadingProgressBar;
    private AppCompatTextView mLabelView;

    private int mTextColor;
    private int mBackgroundColor;
    private int mIndeterminateTint;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadAttributes(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressView, 0, 0);
        mBackgroundColor = typedArray.getColor(R.styleable.ProgressView_progressBackgroundColor, -1);
        mTextColor = typedArray.getColor(R.styleable.ProgressView_progressTextColor, -1);
        mIndeterminateTint = typedArray.getColor(R.styleable.ProgressView_progressIndeterminateTint, -1);
        typedArray.recycle();
    }

    @Override
    protected void bindViews() {
        mContainer = findViewById(R.id.progress);
        mContentLoadingProgressBar = findViewById(R.id.progressbar);
        mLabelView = findViewById(R.id.progress_label);
    }

    @Override
    protected void loadData() {
        setProgressTextColor(mTextColor);
        setProgressBackgroundColor(mBackgroundColor);
        setProgressIndeterminateTint(mIndeterminateTint);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.progress_view;
    }

    public void setProgressTextColor(int textColor) {
        if (mLabelView == null) return;
        mLabelView.setTextColor(textColor);
    }

    public void setProgressBackgroundColor(int backgroundColor) {
        if (mContainer == null) return;
        mContainer.setBackgroundColor(backgroundColor);
    }

    public void setProgressIndeterminateTint(int indeterminateTint) {
        if (mContentLoadingProgressBar == null) return;
        Drawable progressDrawable = mContentLoadingProgressBar.getIndeterminateDrawable().mutate();
        progressDrawable.setColorFilter(indeterminateTint, android.graphics.PorterDuff.Mode.MULTIPLY);
        mContentLoadingProgressBar.setProgressDrawable(progressDrawable);
    }

}
