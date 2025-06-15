package com.hzbhd.ui.view.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;

import com.hzbhd.R;


public final class DoubleChangeColorView extends ColorView {
    private int id_d1;
    private int id_d2;
    private int id_n1;
    private int id_n2;
    private int id_p1;
    private int id_p2;
    private int id_s1;
    private int id_s2;
    private Context mContext;
    private final ContentObserver mSettingsContentObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1] */
    public DoubleChangeColorView(Context context, AttributeSet attrs) {
        super(context, attrs);


        final Handler handler = new Handler(Looper.getMainLooper());
        this.mSettingsContentObserver = new ContentObserver(handler) { // from class: com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                updateAllColor();
            }
        };
        init(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1] */
    public DoubleChangeColorView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);


        final Handler handler = new Handler(Looper.getMainLooper());
        this.mSettingsContentObserver = new ContentObserver(handler) { // from class: com.hzbhd.ui.view.colorview.DoubleChangeColorView$mSettingsContentObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                updateAllColor();
            }
        };
        init(context, attrs);
    }

    private final void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.double_color_attr);
        this.id_n1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_n1, 0);
        this.id_p1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_p1, 0);
        this.id_s1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_s1, 0);
        this.id_d1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_d1, 0);
        this.id_n2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_n2, 0);
        this.id_p2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_p2, 0);
        this.id_s2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_s2, 0);
        this.id_d2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.double_color_attr_double_d2, 0);
        if (this.id_n1 != 0 || this.id_p1 != 0 || this.id_s1 != 0 || this.id_d1 != 0) {
            setBackground(new PressedDrawable(context, this.id_n1, this.id_p1, this.id_s1, this.id_d1));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("changeAllColor"), false, this.mSettingsContentObserver);
        updateAllColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAllColor() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "changeAllColor", 0);
        if (i == 0) {
            Context context = getContext();

            setBackground(new PressedDrawable(context, this.id_n1, this.id_p1, this.id_s1, this.id_d1));
        } else {
            if (i != 1) {
                return;
            }
            Context context2 = getContext();

            setBackground(new PressedDrawable(context2, this.id_n2, this.id_p2, this.id_s2, this.id_d2));
        }
    }
}
