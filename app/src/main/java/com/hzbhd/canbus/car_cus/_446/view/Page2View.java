package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.util.SendUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class Page2View extends LinearLayout implements CanInfoObserver {
    private static final String KEY_TIAN_CHUANG_TAG = "KEY_TIAN_CHUANG_TAG";
    private SeekBar back_door_seekbar;
    private Context context;
    private SwitchView switchView1;
    private TextView up_back_door;
    private View view;

    public Page2View(Context context) {
        this(context, null);
    }

    public Page2View(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Page2View(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_page2, (ViewGroup) this, true);
        this.view = viewInflate;
        this.back_door_seekbar = (SeekBar) viewInflate.findViewById(R.id.back_door_seekbar);
        this.up_back_door = (TextView) this.view.findViewById(R.id.up_back_door);
        this.switchView1 = (SwitchView) this.view.findViewById(R.id.suo_che_tian_chuang_guan_bi);
        initData();
        initAction();
    }

    private void initData() {
        updateUi();
        this.back_door_seekbar.setMax(60);
        this.back_door_seekbar.setProgress(SharePreUtil.getIntValue(this.context, KEY_TIAN_CHUANG_TAG, 100) - 40);
    }

    private void initAction() {
        this.back_door_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._446.view.Page2View.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) throws InterruptedException {
                WmCarData.backDoorValue = seekBar.getProgress() + 40;
                SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, (byte) DataHandleUtils.setOneBit(WmCarData.backDoorValue << 1, 0, 1), 0, 0, 0, 112, 0, 0, 0});
                SharePreUtil.setIntValue(Page2View.this.context, Page2View.KEY_TIAN_CHUANG_TAG, WmCarData.backDoorValue);
            }
        });
        this.up_back_door.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.Page2View.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws InterruptedException {
                SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 0, 0, 0, 0, 0, 8, 106});
                SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 0, 0, 0, 0, 0, 0, 106});
            }
        });
        this.switchView1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page2View.3
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.suoCheTianChaungZiDongGuanBi) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 112, 0, 1, 0});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 112, 0, 2, 0});
                }
            }
        });
    }

    public void updateUi() {
        this.switchView1.select(WmCarData.suoCheTianChaungZiDongGuanBi);
    }

    @Override // com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver
    public void dataChange() {
        updateUi();
    }
}
