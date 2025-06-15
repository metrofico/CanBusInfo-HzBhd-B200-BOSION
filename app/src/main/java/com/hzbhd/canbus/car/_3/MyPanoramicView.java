package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.CanbusMsgSender;

import java.util.LinkedHashMap;
import java.util.Map;

import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;


public final class MyPanoramicView extends RelativeLayout {
    private static final String TAG = "_3_MyPanoramicView";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final byte[] m0xC6Command;

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
        this.m0xC6Command = new byte[]{22, -58, 71, 0};
        LayoutInflater.from(getContext()).inflate(R.layout.layout_panoramic_3_view, this);
        ((SeekBar) _$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 71;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 72;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 73;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m0xC6Command = new byte[]{22, -58, 71, 0};
        LayoutInflater.from(getContext()).inflate(R.layout.layout_panoramic_3_view, this);
        ((SeekBar) _$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 71;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 72;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 73;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m0xC6Command = new byte[]{22, -58, 71, 0};
        LayoutInflater.from(getContext()).inflate(R.layout.layout_panoramic_3_view, this);
        ((SeekBar) _$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 71;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 72;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 73;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.m0xC6Command = new byte[]{22, -58, 71, 0};
        LayoutInflater.from(getContext()).inflate(R.layout.layout_panoramic_3_view, this);
        ((SeekBar) _$_findCachedViewById(R.id.sb_brightness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 71;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_contrast)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 72;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        ((SeekBar) _$_findCachedViewById(R.id.sb_saturation)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car._3.MyPanoramicView.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte[] bArr = MyPanoramicView.this.m0xC6Command;
                bArr[2] = 73;
                bArr[3] = (byte) progress;
                CanbusMsgSender.sendMsg(bArr);
            }
        });
    }

    public void update(int brightness, int contrast, int saturation) {
        ((TextView) _$_findCachedViewById(R.id.tv_brightness)).setText(String.valueOf(RangesKt.coerceIn(brightness, new IntRange(30, 70))));
        ((TextView) _$_findCachedViewById(R.id.tv_contrast)).setText(String.valueOf(RangesKt.coerceIn(contrast, new IntRange(30, 70))));
        ((TextView) _$_findCachedViewById(R.id.tv_saturation)).setText(String.valueOf(RangesKt.coerceIn(saturation, new IntRange(30, 70))));
    }

    public void showHideWindow() {
        _$_findCachedViewById(R.id.gl_color_set).setVisibility(_$_findCachedViewById(R.id.gl_color_set).getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
