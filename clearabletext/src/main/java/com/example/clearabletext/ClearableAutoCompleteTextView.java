package com.example.clearabletext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.ContextCompat;

/**
 * sub class of {@link android.widget.AutoCompleteTextView} that includes a clear (dismiss / close) button with
 * a OnClearListener to handle the event of clicking the button
 * based on code from
 * <a href ="https://github.com/openforis/collect-mobile/blob/master/android/src/main/java/org/openforis/collect/android/gui/util/ClearableAutoCompleteTextView.java">ClearAutoCompleteTextView</a>
 *
 * @author Michael Derazon
 */
public class ClearableAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    // was the text just cleared?
    boolean justCleared = false;

    // if not set otherwise, the default clear listener clears the text in the
    // text view
    private OnClearListener defaultClearListener = new OnClearListener() {

        @Override
        public void onClear() {
            ClearableAutoCompleteTextView et = ClearableAutoCompleteTextView.this;
            et.setText("");
        }
    };

    private OnClearListener onClearListener = defaultClearListener;

    // The image we defined for the clear button
    private Drawable drawableClear;

    public interface OnClearListener {
        void onClear();
    }

    /* Required methods, not used in this implementation */
    public ClearableAutoCompleteTextView(Context context) {
        super(context);
        init(context, null);
    }

    /* Required methods, not used in this implementation */
    public ClearableAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /* Required methods, not used in this implementation */
    public ClearableAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        drawableClear = ContextCompat.getDrawable(context,
                com.example.clearabletext.R.drawable.ic_cancel);

        if(attrs != null)
            initAttrs(context, attrs);

        // if the clear button is pressed, fire up the handler. Otherwise do nothing
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ClearableAutoCompleteTextView et = ClearableAutoCompleteTextView.this;

                if (et.getCompoundDrawables()[2] == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (event.getX() > et.getWidth() - et.getPaddingRight() - drawableClear.getIntrinsicWidth()) {
                    onClearListener.onClear();
                    justCleared = true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(text.length() > 0)
            showClearButton();
        else
            hideClearButton();
    }

    public void setSrcClear(@NonNull Drawable drawable) {
        this.drawableClear = drawable;
    }

    public void setSrcClear(Context context, @DrawableRes int resId) {
        this.drawableClear = ContextCompat.getDrawable(context, resId);
    }

    public void setOnClearListener(final OnClearListener clearListener) {
        this.onClearListener = clearListener;
    }

    public void hideClearButton() {
        this.setCompoundDrawables(null, null, null, null);
    }

    public void showClearButton() {
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableClear, null);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ClearableAutoCompleteTextView, 0, 0);
        try {
            Drawable drawable = array.getDrawable(R.styleable.ClearableAutoCompleteTextView_srcClear);
            if(drawable != null)
                setSrcClear(drawable);
        } finally {
            array.recycle();
        }
    }
}