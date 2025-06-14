package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.view.EqSeekBarView;
import com.hzbhd.canbus.view.LineBtnView;

import java.util.LinkedHashMap;
import java.util.Map;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;


public final class AmplifierActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final String SHARE_PRE_AMPLIFIER_INDEX = "share_pre_amplifier_index";
    public static final String SHARE_PRE_USER_BASS = "share_pre_user_bass";
    public static final String SHARE_PRE_USER_CUSTOM_BASS = "share_pre_user_custom_bass";
    public static final String SHARE_PRE_USER_CUSTOM_BASS_2 = "share_pre_user_custom_bass_2";
    public static final String SHARE_PRE_USER_MEGA_BASS = "share_pre_user_mega_bass";
    public static final String SHARE_PRE_USER_MIDDLE = "share_pre_user_middle";
    public static final String SHARE_PRE_USER_TREBLE = "share_pre_user_treble";
    public static final String SHARE_PRE_USER_VOLUME = "share_pre_user_volume";
    private int mBalanceRange;
    private int mBandRange;
    private int mBgWidthHeight;
    private int mFrontRearValue;
    private int mLeftRightValue;
    private String[] mLineBtnAction;
    private int mOldHeight;
    private OnAmplifierCreateAndDestroyListener mOnAmplifierCreateAndDestroyListener;
    private OnAmplifierPositionListener mOnAmplifierPositionListener;
    private OnAmplifierResetPositionListener mOnAmplifierResetPositionListener;
    private OnAmplifierSeekBarListener mOnAmplifierSeekBarListener;
    private int mVolumeRange;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int mFadBalanceSz = 14;
    private float mFadBalanceFloatSz = 14.0f;
    private final EqSeekBarView.OnMinPlusClickListener mEqSeekBarView = new EqSeekBarView.OnMinPlusClickListener() { // from class: com.hzbhd.canbus.activity.AmplifierActivity$mEqSeekBarView$1
        @Override // com.hzbhd.canbus.view.EqSeekBarView.OnMinPlusClickListener
        public void onClickMin(SeekBar seekBar) {
            int i;
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                int progress = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getProgress() - 1;
                i = progress >= 0 ? progress : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener != null) {
                    onAmplifierSeekBarListener.progress(AmplifierActivity.AmplifierBand.TREBLE, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener2 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener2 != null) {
                    onAmplifierSeekBarListener2.progress(AmplifierActivity.AmplifierBand.TREBLE_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                int progress2 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getProgress() - 1;
                i = progress2 >= 0 ? progress2 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener3 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener3 != null) {
                    onAmplifierSeekBarListener3.progress(AmplifierActivity.AmplifierBand.MIDDLE, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener4 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener4 != null) {
                    onAmplifierSeekBarListener4.progress(AmplifierActivity.AmplifierBand.MIDDLE_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                int progress3 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getProgress() - 1;
                i = progress3 >= 0 ? progress3 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener5 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener5 != null) {
                    onAmplifierSeekBarListener5.progress(AmplifierActivity.AmplifierBand.BASS, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener6 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener6 != null) {
                    onAmplifierSeekBarListener6.progress(AmplifierActivity.AmplifierBand.BASS_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                int progress4 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getProgress() - 1;
                i = progress4 >= 0 ? progress4 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener7 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener7 != null) {
                    onAmplifierSeekBarListener7.progress(AmplifierActivity.AmplifierBand.VOLUME, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener8 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener8 != null) {
                    onAmplifierSeekBarListener8.progress(AmplifierActivity.AmplifierBand.VOLUME_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                int progress5 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getProgress() - 1;
                i = progress5 >= 0 ? progress5 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener9 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener9 != null) {
                    onAmplifierSeekBarListener9.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener10 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener10 != null) {
                    onAmplifierSeekBarListener10.progress(AmplifierActivity.AmplifierBand.MEGA_BASS_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                int progress6 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getProgress() - 1;
                i = progress6 >= 0 ? progress6 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener11 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener11 != null) {
                    onAmplifierSeekBarListener11.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener12 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener12 != null) {
                    onAmplifierSeekBarListener12.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_Min, i);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                int progress7 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getProgress() - 1;
                i = progress7 >= 0 ? progress7 : 0;
                OnAmplifierSeekBarListener onAmplifierSeekBarListener13 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener13 != null) {
                    onAmplifierSeekBarListener13.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, i);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener14 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener14 != null) {
                    onAmplifierSeekBarListener14.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Min, i);
                }
            }
        }

        @Override // com.hzbhd.canbus.view.EqSeekBarView.OnMinPlusClickListener
        public void onClickPlus(SeekBar seekBar) {
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                int progress = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getProgress() + 1;
                if (progress > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getMax()) {
                    progress = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener != null) {
                    onAmplifierSeekBarListener.progress(AmplifierActivity.AmplifierBand.TREBLE, progress);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener2 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener2 != null) {
                    onAmplifierSeekBarListener2.progress(AmplifierActivity.AmplifierBand.TREBLE_Plus, progress);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                int progress2 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getProgress() + 1;
                if (progress2 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getMax()) {
                    progress2 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener3 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener3 != null) {
                    onAmplifierSeekBarListener3.progress(AmplifierActivity.AmplifierBand.MIDDLE, progress2);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener4 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener4 != null) {
                    onAmplifierSeekBarListener4.progress(AmplifierActivity.AmplifierBand.MIDDLE_Plus, progress2);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                int progress3 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getProgress() + 1;
                if (progress3 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getMax()) {
                    progress3 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener5 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener5 != null) {
                    onAmplifierSeekBarListener5.progress(AmplifierActivity.AmplifierBand.BASS, progress3);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener6 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener6 != null) {
                    onAmplifierSeekBarListener6.progress(AmplifierActivity.AmplifierBand.BASS_Plus, progress3);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                int progress4 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getProgress() + 1;
                if (progress4 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getMax()) {
                    progress4 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener7 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener7 != null) {
                    onAmplifierSeekBarListener7.progress(AmplifierActivity.AmplifierBand.VOLUME, progress4);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener8 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener8 != null) {
                    onAmplifierSeekBarListener8.progress(AmplifierActivity.AmplifierBand.VOLUME_Plus, progress4);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                int progress5 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getProgress() + 1;
                if (progress5 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getMax()) {
                    progress5 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener9 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener9 != null) {
                    onAmplifierSeekBarListener9.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, progress5);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener10 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener10 != null) {
                    onAmplifierSeekBarListener10.progress(AmplifierActivity.AmplifierBand.MEGA_BASS_Plus, progress5);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                int progress6 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getProgress() + 1;
                if (progress6 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getMax()) {
                    progress6 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener11 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener11 != null) {
                    onAmplifierSeekBarListener11.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, progress6);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener12 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener12 != null) {
                    onAmplifierSeekBarListener12.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_Plus, progress6);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                int progress7 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getProgress() + 1;
                if (progress7 > ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getMax()) {
                    progress7 = ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getMax();
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener13 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener13 != null) {
                    onAmplifierSeekBarListener13.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, progress7);
                }
                OnAmplifierSeekBarListener onAmplifierSeekBarListener14 = mOnAmplifierSeekBarListener;
                if (onAmplifierSeekBarListener14 != null) {
                    onAmplifierSeekBarListener14.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Plus, progress7);
                }
            }
        }
    };
    private final SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.activity.AmplifierActivity$mOnSeekBarChangeListener$1
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            try {
                if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setValue(String.valueOf(progress - mBandRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setValue(String.valueOf(progress - mBandRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setValue(String.valueOf(progress - mBandRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setValue(String.valueOf(progress - mVolumeRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setValue(String.valueOf(progress - mBandRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setValue(String.valueOf(progress - mBandRange));
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setValue(String.valueOf(progress));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            OnAmplifierSeekBarListener onAmplifierSeekBarListener;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener2;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener3;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener4;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener5;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener6;
            OnAmplifierSeekBarListener onAmplifierSeekBarListener7;
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            try {
                SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_AMPLIFIER_INDEX, 6);
                if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_TREBLE, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener7 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener7.progress(AmplifierActivity.AmplifierBand.TREBLE, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_MIDDLE, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener6 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener6.progress(AmplifierActivity.AmplifierBand.MIDDLE, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_BASS, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener5 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener5.progress(AmplifierActivity.AmplifierBand.BASS, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setValue(String.valueOf(seekBar.getProgress() - mVolumeRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_VOLUME, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener4 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener4.progress(AmplifierActivity.AmplifierBand.VOLUME, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_MEGA_BASS, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener3 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener3.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_CUSTOM_BASS, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener2 = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener2.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, seekBar.getProgress());
                    }
                } else if (Intrinsics.areEqual(seekBar, ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                    ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setValue(String.valueOf(seekBar.getProgress() - mBandRange));
                    SharePreUtil.setIntValue(AmplifierActivity.this, AmplifierActivity.SHARE_PRE_USER_CUSTOM_BASS_2, seekBar.getProgress());
                    if (mOnAmplifierSeekBarListener != null && (onAmplifierSeekBarListener = mOnAmplifierSeekBarListener) != null) {
                        onAmplifierSeekBarListener.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, seekBar.getProgress());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private View.OnTouchListener mPositionTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent == null) {
                return false;
            }

            int action = motionEvent.getAction();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    try {
                        // Ejecutar lógica cuando el toque empieza (ACTION_DOWN)
                        SendKeyManager.getInstance().playBeep(0);
                        reportLeftRightPosition(0);
                        Thread.sleep(100L);
                        reportFrontRearPosition(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    // Actualizar la posición del punto cuando el dedo se mueve
                    updatePointPosition(motionEvent.getX(), motionEvent.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    // Lógica para cuando se deja de tocar
                    break;
            }

            return true;
        }
    };

    public enum AmplifierBand {
        VOLUME, TREBLE, MIDDLE, BASS, MEGA_BASS, CUSTOM_BASS, CUSTOM_2_BASS, VOLUME_Min, TREBLE_Min, MIDDLE_Min, BASS_Min, MEGA_BASS_Min, CUSTOM_BASS_Min, CUSTOM_BASS_2_Min, VOLUME_Plus, TREBLE_Plus, MIDDLE_Plus, BASS_Plus, MEGA_BASS_Plus, CUSTOM_BASS_Plus, CUSTOM_BASS_2_Plus
    }

    public enum AmplifierPosition {
        LEFT_RIGHT, FRONT_REAR, LEFT, RIGHT, FRONT, REAR
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(i, viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amplifier);
        initViews();
        OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = this.mOnAmplifierCreateAndDestroyListener;
        if (onAmplifierCreateAndDestroyListener != null) {
            onAmplifierCreateAndDestroyListener.create();
        }
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = this.mOnAmplifierCreateAndDestroyListener;
        if (onAmplifierCreateAndDestroyListener != null) {
            onAmplifierCreateAndDestroyListener.destroy();
        }
    }

    public void initViews() {
        AmplifierActivity amplifierActivity = this;
        AmplifierPageUiSet amplifierPageUiSet = getUiMgrInterface(amplifierActivity).getAmplifierPageUiSet(amplifierActivity);
        if (amplifierPageUiSet == null) {
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_front_rear_value)).setText("0");
        ((TextView) _$_findCachedViewById(R.id.tv_left_right_value)).setText("0");
        if (amplifierPageUiSet.getIsCanBalanceControl()) {
            _$_findCachedViewById(R.id.rl_bg).setOnTouchListener(this.mPositionTouchListener);
        } else {
            _$_findCachedViewById(R.id.iv_reset_position).setVisibility(4);
        }
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setCanMinPlus(amplifierPageUiSet.getIsCanSeekBarMinPlus());
        if (!amplifierPageUiSet.getIsRlControl()) {
            _$_findCachedViewById(R.id.iv_top).setVisibility(View.GONE);
            _$_findCachedViewById(R.id.iv_left).setVisibility(View.GONE);
            _$_findCachedViewById(R.id.iv_right).setVisibility(View.GONE);
            _$_findCachedViewById(R.id.iv_bottom).setVisibility(View.GONE);
        }
        if (amplifierPageUiSet.getIsHaveVolumeControl()) {
            _$_findCachedViewById(R.id.seek_bar_volume).setVisibility(View.VISIBLE);
        } else {
            _$_findCachedViewById(R.id.seek_bar_volume).setVisibility(View.GONE);
        }
        if (amplifierPageUiSet.getIsHaveBandMiddle()) {
            _$_findCachedViewById(R.id.seek_bar_middle).setVisibility(View.VISIBLE);
        } else {
            _$_findCachedViewById(R.id.seek_bar_middle).setVisibility(View.GONE);
        }
        if (amplifierPageUiSet.getIsHaveMegaBass()) {
            _$_findCachedViewById(R.id.seek_bar_mega_bass).setVisibility(View.VISIBLE);
        } else {
            _$_findCachedViewById(R.id.seek_bar_mega_bass).setVisibility(View.GONE);
        }
        if (amplifierPageUiSet.getIsHaveCustom()) {
            _$_findCachedViewById(R.id.seek_bar_custom).setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(amplifierPageUiSet.getCustomTitle())) {
                ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setTitle(getString(CommUtil.getStrIdByResId(amplifierActivity, amplifierPageUiSet.getCustomTitle())));
            }
        } else {
            _$_findCachedViewById(R.id.seek_bar_custom).setVisibility(View.GONE);
        }
        if (amplifierPageUiSet.getIsHaveCustom2()) {
            _$_findCachedViewById(R.id.seek_bar_custom_2).setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(amplifierPageUiSet.getCustom2Title())) {
                ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setTitle(getString(CommUtil.getStrIdByResId(amplifierActivity, amplifierPageUiSet.getCustom2Title())));
            }
        } else {
            _$_findCachedViewById(R.id.seek_bar_custom_2).setVisibility(View.GONE);
        }
        this.mVolumeRange = amplifierPageUiSet.getVolumeRange();
        this.mBandRange = amplifierPageUiSet.getBandRange();
        int balanceRange = amplifierPageUiSet.getBalanceRange();
        this.mBalanceRange = balanceRange;
        this.mFadBalanceSz = balanceRange / 2;
        this.mFadBalanceFloatSz = balanceRange / 2;
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setOnPlusMinClickListener(this.mEqSeekBarView);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setSeekBarTouchable(amplifierPageUiSet.getIsCanRateControl());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarVolumeMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMax());
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().setMax(amplifierPageUiSet.getSeekBarMaxCustom2());
        _$_findCachedViewById(R.id.seek_bar_volume).setEnabled(amplifierPageUiSet.getIsVolumeEnabled());
        _$_findCachedViewById(R.id.seek_bar_high).setEnabled(amplifierPageUiSet.getIsHighEnabled());
        _$_findCachedViewById(R.id.seek_bar_middle).setEnabled(amplifierPageUiSet.getIsMiddleEnabled());
        _$_findCachedViewById(R.id.seek_bar_low).setEnabled(amplifierPageUiSet.getIsLowEnabled());
        _$_findCachedViewById(R.id.seek_bar_custom).setEnabled(amplifierPageUiSet.getIsCustomEnabled());
        _$_findCachedViewById(R.id.seek_bar_custom_2).setEnabled(amplifierPageUiSet.getIsCustom2Enabled());
        this.mLineBtnAction = amplifierPageUiSet.getLineBtnAction();
        this.mOnAmplifierPositionListener = amplifierPageUiSet.getOnAmplifierPositionListener();
        this.mOnAmplifierSeekBarListener = amplifierPageUiSet.getOnAmplifierSeekBarListener();
        this.mOnAmplifierResetPositionListener = amplifierPageUiSet.getOnAmplifierResetPositionListener();
        this.mOnAmplifierCreateAndDestroyListener = amplifierPageUiSet.getOnAmplifierCreateAndDestroyListener();
        if (this.mLineBtnAction.length == 0) {
            _$_findCachedViewById(R.id.lbv_top).setVisibility(View.GONE);
        } else {
            ((LineBtnView) _$_findCachedViewById(R.id.lbv_top)).initButton(amplifierActivity, this.mLineBtnAction, true, null, amplifierPageUiSet.getOnAirBtnClickListeners());
        }
        if (amplifierPageUiSet.getIsHaveRateControl()) {
            _$_findCachedViewById(R.id.ll_rate_control).setVisibility(View.VISIBLE);
        } else {
            _$_findCachedViewById(R.id.ll_rate_control).setVisibility(4);
        }
        _$_findCachedViewById(R.id.rl_bg).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hzbhd.canbus.activity.AmplifierActivity.initViews.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LogUtil.showLog("fang", "mOldHeight:" + AmplifierActivity.this.mOldHeight + " rl_bg.height:" + AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).getHeight());
                if (AmplifierActivity.this.mOldHeight != AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).getHeight()) {
                    AmplifierActivity amplifierActivity2 = AmplifierActivity.this;
                    amplifierActivity2.mOldHeight = amplifierActivity2._$_findCachedViewById(R.id.rl_bg).getHeight();
                    ViewGroup.LayoutParams layoutParams = AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).getLayoutParams();
                    layoutParams.width = AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).getHeight();
                    AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).setLayoutParams(layoutParams);
                    AmplifierActivity.this._$_findCachedViewById(R.id.rl_bg).invalidate();
                    AmplifierActivity.this.mBgWidthHeight = layoutParams.width;
                    AmplifierActivity.this.refreshUi(null);
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (!isShouldRefreshUi()) {
            return;
        }
        LogUtil.showLog("AmplifierActivity refreshUi");
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_volume)).setProgress(GeneralAmplifierData.volume);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_high)).setProgress(GeneralAmplifierData.bandTreble);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_middle)).setProgress(GeneralAmplifierData.bandMiddle);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_low)).setProgress(GeneralAmplifierData.bandBass);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_mega_bass)).setProgress(GeneralAmplifierData.megaBass);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom)).setProgress(GeneralAmplifierData.customBass);
        ((EqSeekBarView) _$_findCachedViewById(R.id.seek_bar_custom_2)).setProgress(GeneralAmplifierData.custom2Bass);
        LogUtil.showLog("refresh:FrontRear:" + GeneralAmplifierData.frontRear + " LeftRight:" + GeneralAmplifierData.leftRight);
        LogUtil.showLog("refresh:mFrontRearValue:" + this.mFrontRearValue + " mLeftRightValue:" + this.mLeftRightValue);
        if (this.mFrontRearValue != GeneralAmplifierData.frontRear && this.mLeftRightValue != GeneralAmplifierData.leftRight) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            updatePointByValue();
        }
        if (this.mFrontRearValue != GeneralAmplifierData.frontRear) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            updatePointFrontRearByValue();
        }
        if (this.mLeftRightValue != GeneralAmplifierData.leftRight) {
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            updatePointLeftRightByValue();
        }
        if (GeneralAmplifierData.frontRear == 0) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            updatePointFrontRearByValue();
        }
        if (GeneralAmplifierData.leftRight == 0) {
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            updatePointLeftRightByValue();
        }
        String[] strArr = this.mLineBtnAction;
        if (strArr == null) {
            return;
        }
        IntRange indices = strArr != null ? ArraysKt.getIndices(strArr) : null;
        Intrinsics.checkNotNull(indices);
        int first = indices.getFirst();
        int last = indices.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            String[] strArr2 = this.mLineBtnAction;
            Intrinsics.checkNotNull(strArr2);
            if (Intrinsics.areEqual(strArr2[first], GeneralAmplifierData.bose_center)) {
                ((LineBtnView) _$_findCachedViewById(R.id.lbv_top)).getBtnItemView(first).turn(GeneralAmplifierData.bose_center_b);
            }
            if (first == last) {
                return;
            } else {
                first++;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_top) {
            reportFrontRearPosition(1);
            reportFrontPosition(1);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_left) {
            reportLeftRightPosition(-1);
            reportLeftPosition(-1);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_bottom) {
            reportFrontRearPosition(-1);
            reportRearPosition(-1);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_right) {
            reportLeftRightPosition(1);
            reportRightPosition(1);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_reset_position) {
            OnAmplifierResetPositionListener onAmplifierResetPositionListener = this.mOnAmplifierResetPositionListener;
            if (onAmplifierResetPositionListener != null) {
                onAmplifierResetPositionListener.resetClick();
            }
            int i = this.mBgWidthHeight;
            updatePointPosition(i / 2.0f, i / 2.0f);
            reportFrontRearPosition(0);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
            }
            reportLeftRightPosition(0);
        }
    }

    private void updatePointLeftRightByValue() {
        LogUtil.showLog("updatePointLeftRightByValue");
        int n = this.mLeftRightValue;
        int n2 = this.mBalanceRange;
        this.updatePointPosition((float) (n + n2 / 2) / (float) n2 * (float) (this.mBgWidthHeight - this.getPointWidth()) + (float) this.getPointWidth2(), this._$_findCachedViewById(R.id.iv_point).getY() + (float) this.getPointWidth2());
    }

    private void updatePointFrontRearByValue() {
        LogUtil.showLog("updatePointFrontRearByValue");
        float f = this._$_findCachedViewById(R.id.iv_point).getX();
        float f2 = this.getPointWidth2();
        int n = -this.mFrontRearValue;
        int n2 = this.mBalanceRange;
        this.updatePointPosition(f + f2, (float) (n + n2 / 2) / (float) n2 * (float) (this.mBgWidthHeight - this.getPointWidth()) + (float) this.getPointWidth2());
    }

    private final void updatePointByValue() {
        LogUtil.showLog("updatePointByValue");
        int n = this.mLeftRightValue;
        int n2 = this.mBalanceRange;
        float f = (float) (n + n2 / 2) / (float) n2;
        float f2 = this.mBgWidthHeight - this.getPointWidth();
        LogUtil.showLog("mFrontRearValue:" + this.mFrontRearValue);
        LogUtil.showLog("mBalanceRange:" + this.mBalanceRange);
        LogUtil.showLog("1mBgWidthHeight:" + this.mBgWidthHeight);
        LogUtil.showLog("1getPointWidth():" + this.getPointWidth());
        n2 = -this.mFrontRearValue;
        n = this.mBalanceRange;
        this.updatePointPosition(f * f2, (float) (n2 + n / 2) / (float) n * (float) (this.mBgWidthHeight - this.getPointWidth()));
    }


    private int getPointWidth2() {
        return _$_findCachedViewById(R.id.iv_point).getWidth() / 2;
    }

    private int getPointWidth() {
        return _$_findCachedViewById(R.id.iv_point).getWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePointPosition(float x, float y) {
        LogUtil.showLog("y:" + y);
        if (x < getPointWidth2()) {
            x = getPointWidth2();
        }
        if (x > _$_findCachedViewById(R.id.rl_bg).getHeight() - getPointWidth2()) {
            x = _$_findCachedViewById(R.id.rl_bg).getHeight() - getPointWidth2();
        }
        if (y < getPointWidth2()) {
            y = getPointWidth2();
        }
        if (y > _$_findCachedViewById(R.id.rl_bg).getHeight() - getPointWidth2()) {
            y = _$_findCachedViewById(R.id.rl_bg).getHeight() - getPointWidth2();
        }
        _$_findCachedViewById(R.id.iv_point).setX(x - getPointWidth2());
        _$_findCachedViewById(R.id.iv_point).setY(y - getPointWidth2());
        _$_findCachedViewById(R.id.iv_point).invalidate();
        LogUtil.showLog("setX:" + x);
        LogUtil.showLog("setY:" + y);
        LogUtil.showLog("getPointWidth2():" + getPointWidth2());
        LogUtil.showLog("mBgWidthHeight:" + this.mBgWidthHeight);
        LogUtil.showLog("getPointWidth():" + getPointWidth());
        LogUtil.showLog("mBalanceRange:" + this.mBalanceRange);
        this.mLeftRightValue = MathKt.roundToInt(((x - getPointWidth2()) / (this.mBgWidthHeight - getPointWidth())) * this.mBalanceRange) - (this.mBalanceRange / 2);
        this.mFrontRearValue = -(MathKt.roundToInt(((y - getPointWidth2()) / (this.mBgWidthHeight - getPointWidth())) * this.mBalanceRange) - (this.mBalanceRange / 2));
        LogUtil.showLog("update:FrontRear:" + this.mFrontRearValue + " LeftRight:" + this.mLeftRightValue);
        int i = this.mFrontRearValue;
        int i2 = this.mBalanceRange;
        if (i > i2 / 2) {
            this.mFrontRearValue = i2 / 2;
        }
        if (this.mFrontRearValue < (-(i2 / 2))) {
            this.mFrontRearValue = -(i2 / 2);
        }
        if (this.mLeftRightValue < (-(i2 / 2))) {
            this.mLeftRightValue = -(i2 / 2);
        }
        if (this.mLeftRightValue > i2 / 2) {
            this.mLeftRightValue = i2 / 2;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_front_rear_value)).setText(String.valueOf(this.mFrontRearValue));
        ((TextView) _$_findCachedViewById(R.id.tv_left_right_value)).setText(String.valueOf(this.mLeftRightValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportLeftRightPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.LEFT_RIGHT, this.mLeftRightValue + value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportFrontRearPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.FRONT_REAR, this.mFrontRearValue + value);
        }
    }

    private void reportLeftPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.LEFT, this.mLeftRightValue + value);
        }
    }

    private void reportRightPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.RIGHT, this.mLeftRightValue + value);
        }
    }

    private void reportFrontPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.FRONT, this.mFrontRearValue + value);
        }
    }

    private void reportRearPosition(int value) {
        OnAmplifierPositionListener onAmplifierPositionListener = this.mOnAmplifierPositionListener;
        if (onAmplifierPositionListener != null) {
            onAmplifierPositionListener.position(AmplifierPosition.REAR, this.mFrontRearValue + value);
        }
    }
}
