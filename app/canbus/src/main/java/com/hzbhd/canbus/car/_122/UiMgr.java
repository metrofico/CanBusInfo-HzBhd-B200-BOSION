package com.hzbhd.canbus.car._122;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._122.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
                    return;
                }
                if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (i + 1)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.MIDDLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (i + 1)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (i + 1)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._122.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 1 + 9)});
                } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 1 + 9)});
                }
            }
        });
    }
}
