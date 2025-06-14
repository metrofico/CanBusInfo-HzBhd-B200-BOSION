package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: MyPanoramicView.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB+\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0006\u0010\u000f\u001a\u00020\u0010J\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MyPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "m0xC6Command", "", "showHideWindow", "", "update", "brightness", "contrast", "saturation", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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

    public final void update(int brightness, int contrast, int saturation) {
        ((TextView) _$_findCachedViewById(R.id.tv_brightness)).setText(String.valueOf(RangesKt.coerceIn(brightness, (ClosedRange<Integer>) new IntRange(30, 70))));
        ((TextView) _$_findCachedViewById(R.id.tv_contrast)).setText(String.valueOf(RangesKt.coerceIn(contrast, (ClosedRange<Integer>) new IntRange(30, 70))));
        ((TextView) _$_findCachedViewById(R.id.tv_saturation)).setText(String.valueOf(RangesKt.coerceIn(saturation, (ClosedRange<Integer>) new IntRange(30, 70))));
    }

    public final void showHideWindow() {
        ((GridLayout) _$_findCachedViewById(R.id.gl_color_set)).setVisibility(((GridLayout) _$_findCachedViewById(R.id.gl_color_set)).getVisibility() == 0 ? 8 : 0);
    }
}
