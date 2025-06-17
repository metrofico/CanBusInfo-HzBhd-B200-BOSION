package com.hzbhd.canbus.car._331;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;




public final class CusPanoramicView extends RelativeLayout {
    public Map<Integer, View> _$_findViewCache;
    private final RadarView mRadarView;

    public CusPanoramicView(Context context) {
        this(context, null, 0, 6, null);
    }

    public CusPanoramicView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

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
    public CusPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._$_findViewCache = new LinkedHashMap();
        View viewFindViewById = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_331_view, this).findViewById(R.id.radarView);

        this.mRadarView = (RadarView) viewFindViewById;
    }

    public /* synthetic */ CusPanoramicView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void refreshUi() {
        this.mRadarView.updateUi();
    }
}
