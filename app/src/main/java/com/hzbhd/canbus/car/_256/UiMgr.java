package com.hzbhd.canbus.car._256;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;


public class UiMgr extends AbstractUiMgr {
    public static byte m0x06Byte;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() == 0) {
            removeSettingLeftItem(context, 1, 1);
            removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
        }
    }

    public UiMgr(Context context) {
        getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte) i3});
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    LogUtil.showLog("click 0");
                } else {
                    if (i != 1) {
                        return;
                    }
                    LogUtil.showLog("click 1");
                }
            }
        });
        PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelLongKeyPositionListener(new OnPanelLongKeyPositionListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener
            public void longClick(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, true)});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, true)});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, true)});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte) DataHandleUtils.setIntByteWithBit(0, 7, true)});
                }
            }
        });
        panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
            public void onTouch(int i, MotionEvent motionEvent) {
                if (i == 0) {
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, true)});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, false)});
                        return;
                    }
                    return;
                }
                if (i == 1) {
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, true)});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, false)});
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, true)});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, false)});
                        return;
                    }
                    return;
                }
                if (i != 3) {
                    return;
                }
                if (motionEvent.getAction() == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte) DataHandleUtils.setIntByteWithBit(0, 7, true)});
                }
                if (motionEvent.getAction() == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte) DataHandleUtils.setIntByteWithBit(0, 7, false)});
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass7.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + 16)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 16)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._256.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass7.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 10)});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 10)});
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._256.UiMgr$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
