package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MyPanoramicView extends RelativeLayout {
    private String TAG;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private LinearLayout mCurrentBtns;

    /* compiled from: MyPanoramicView.kt */
    
    public enum SIDE {
        FRONT,
        REAR
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

    public MyPanoramicView(Context context) {
        super(context);
        this.TAG = "_30_MyPanoramicView";
        this.mCurrentBtns = (LinearLayout) _$_findCachedViewById(R.id.ll_front);
        LayoutInflater.from(context).inflate(R.layout.layout_panoramic_30_view, this);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._30.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_all))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_only))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_left))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 3});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_right))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_back))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_rear_all))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_rear_only))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_rear_left))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8});
                    return;
                }
                if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_rear_right))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 9});
                } else if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_rear_down))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 10});
                } else if (Intrinsics.areEqual(v, (ImageButton) MyPanoramicView.this._$_findCachedViewById(R.id.ib_front_right_left))) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 11});
                }
            }
        };
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_all)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_only)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_left)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_right)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_back)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_all)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_only)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_left)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_right)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_down)).setOnClickListener(onClickListener);
        ((ImageButton) _$_findCachedViewById(R.id.ib_front_right_left)).setOnClickListener(onClickListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean zIsPanoramic = CommUtil.isPanoramic(getContext());
        boolean zIsBackCamera = CommUtil.isBackCamera(getContext());
        String str = this.TAG;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("onAttachedToWindow: isPanoramic %s|  isBackCamera %s|", Arrays.copyOf(new Object[]{Boolean.valueOf(zIsPanoramic), Boolean.valueOf(zIsBackCamera)}, 2));

        Log.i(str, str2);
        if (zIsPanoramic) {
            updateSide(SIDE.FRONT.ordinal());
        }
        if (zIsBackCamera) {
            updateSide(SIDE.REAR.ordinal());
        }
    }

    public final void updateSide(int side) {
        if (side == SIDE.FRONT.ordinal()) {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_front)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_rear)).setVisibility(4);
            this.mCurrentBtns = (LinearLayout) _$_findCachedViewById(R.id.ll_front);
        } else if (side == SIDE.REAR.ordinal()) {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_front)).setVisibility(4);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_rear)).setVisibility(0);
            this.mCurrentBtns = (LinearLayout) _$_findCachedViewById(R.id.ll_rear);
        }
    }

    public final void updateBtns(int index) {
        View childAt;
        View childAt2;
        try {
            int childCount = this.mCurrentBtns.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (i == index) {
                    LinearLayout linearLayout = this.mCurrentBtns;
                    if (linearLayout != null && (childAt2 = linearLayout.getChildAt(i)) != null) {
                        childAt2.setBackgroundResource(R.drawable.drive_data_btn_p);
                    }
                } else {
                    LinearLayout linearLayout2 = this.mCurrentBtns;
                    if (linearLayout2 != null && (childAt = linearLayout2.getChildAt(i)) != null) {
                        childAt.setBackgroundResource(R.drawable.drive_data_btn_n);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    public final void showIbRearDown() {
        ((ImageButton) _$_findCachedViewById(R.id.ib_rear_down)).setVisibility(0);
        Log.i(this.TAG, "showIbRearDown: ib_rear_down View.VISIBLE");
    }
}
