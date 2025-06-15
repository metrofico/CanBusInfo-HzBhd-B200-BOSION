package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.LinkedHashMap;
import java.util.Map;

import kotlin.jvm.internal.Intrinsics;

public final class AmplifierPosView extends RelativeLayout {
    public Map<Integer, View> _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmplifierPosView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmplifierPosView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmplifierPosView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
    }
}
