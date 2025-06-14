package com.hzbhd.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BhdEditText.kt */
@SuppressLint("AppCompatCustomView")

public final class BhdEditText extends EditText {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdEditText(Context context) {
        super(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdEditText(Context context, AttributeSet attr) {
        super(context, attr);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdEditText(Context context, AttributeSet attr, int i) {
        super(context, attr, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdEditText(Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new MyInputConnection(this, super.onCreateInputConnection(outAttrs), false);
    }

    /* compiled from: BhdEditText.kt */
    private final class MyInputConnection extends InputConnectionWrapper {
        final BhdEditText this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyInputConnection(BhdEditText this$0, InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
            this.this$0 = this$0;
        }

        @Override
        // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean finishComposingText() {
            this.this$0.clearFocus();
            return super.finishComposingText();
        }
    }
}
