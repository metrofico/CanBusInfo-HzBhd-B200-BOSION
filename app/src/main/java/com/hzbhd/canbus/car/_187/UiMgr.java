package com.hzbhd.canbus.car._187;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private AirActivity mActivity;
    private Context mContext;
    private MsgMgr mMsgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._187.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte) i3});
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._187.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                int i4;
                if (i == 0) {
                    if (i2 == 0) {
                        int i5 = i3 - UiMgr.this.mMsgMgr.mAmpAslValueNow;
                        i4 = i5 <= 0 ? i5 : 33;
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) (i4 >= 0 ? i4 : 49)});
                        UiMgr.this.mMsgMgr.mAmpAslValueNow = i3;
                        return;
                    }
                    if (i2 == 2) {
                        int i6 = i3 - UiMgr.this.mMsgMgr.mAmpSurroundValueNow;
                        i4 = i6 <= 0 ? i6 : 33;
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte) (i4 >= 0 ? i4 : 49)});
                        UiMgr.this.mMsgMgr.mAmpSurroundValueNow = i3;
                    }
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._187.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                    UiMgr.this.sendAmplifierCommand(36, i, GeneralAmplifierData.leftRight);
                } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                    UiMgr.this.sendAmplifierCommand(37, i, GeneralAmplifierData.frontRear);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._187.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME) {
                    UiMgr.this.sendAmplifierCommand(33, i, GeneralAmplifierData.volume);
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                    UiMgr.this.sendAmplifierCommand(34, i, GeneralAmplifierData.bandBass);
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                    UiMgr.this.sendAmplifierCommand(35, i, GeneralAmplifierData.bandTreble);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierCommand(int i, int i2, int i3) {
        int i4;
        int i5 = i2 - i3;
        if (i5 > 0) {
            i4 = 33;
        } else if (i5 >= 0) {
            return;
        } else {
            i4 = 49;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i4});
    }
}
