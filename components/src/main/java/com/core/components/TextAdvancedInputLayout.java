package com.core.components;

import android.content.Context;
import com.google.android.material.textfield.TextInputLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Angel on 09/10/2017.
 */

public class TextAdvancedInputLayout extends TextInputLayout {

    public TextAdvancedInputLayout(Context context) {
        super(context);
    }

    public TextAdvancedInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextAdvancedInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHintGravity(int gravity) {
        try {
            Field helperField  = TextInputLayout.class.getDeclaredField("mCollapsingTextHelper");
            helperField.setAccessible(true);
            Object helper = helperField.get(this);

            Method setCollapsedTextGravity = helper.getClass().getDeclaredMethod("setCollapsedTextGravity", int.class);
            setCollapsedTextGravity.setAccessible(true);
            setCollapsedTextGravity.invoke(helper, Gravity.TOP | (gravity & ~Gravity.VERTICAL_GRAVITY_MASK));

            Method setExpandedTextGravity = helper.getClass().getDeclaredMethod("setExpandedTextGravity", int.class);
            setExpandedTextGravity.setAccessible(true);
            setExpandedTextGravity.invoke(helper, gravity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (!enabled) {
            return;
        }
        try {
            Field errorViewField = TextInputLayout.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setGravity(Gravity.END);
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.END;
                errorView.setLayoutParams(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
