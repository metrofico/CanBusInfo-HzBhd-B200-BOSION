package com.hzbhd.canbus.vm.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hzbhd.canbus.vm.binding.ReverseMainBinding;
import com.hzbhd.ui.life.BaseFrameLayout;

import java.util.LinkedHashMap;
import java.util.Map;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class ReverseMainView extends BaseFrameLayout {
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
    public ReverseMainView(Context context) {
        super(context);

        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReverseMainView(Context context, AttributeSet attrs) {
        super(context, attrs);


        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReverseMainView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        this._$_findViewCache = new LinkedHashMap();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReverseMainView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        this._$_findViewCache = new LinkedHashMap();
    }

    @Override // com.hzbhd.ui.life.BaseFrameLayout
    public ReverseMainBinding getBinding() {
        Context context = getContext();

        return new ReverseMainBinding(context);
    }
}
