package com.hzbhd.canbus.car._11;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_11_LANGUAGE = "share_11_language";
    private int mBtnHeight;
    private int mBtnThreeEnd;
    private int mBtnWidth;
    private Context mContext;
    private int mMaxHight;
    private int mMaxWidth;
    private MsgMgr mMsgMgr;
    private int mMinuteFuelLeft = 6;
    private int mHistoricalLeft = 7;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(4);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.this.mDifferent != 2) {
                UiMgr.this.sendAirCommand(12);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(11);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.this.mDifferent != 2) {
                UiMgr.this.sendAirCommand(14);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(13);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(12);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(39);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(38);
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (!SharePreUtil.getBoolValue(this.mContext, "share_11_is_suppot_tire", true)) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
        if (SharePreUtil.getBoolValue(this.mContext, "share_11_is_suppot_hybrid", true)) {
            return;
        }
        removeMainPrjBtnByName(context, MainAction.HYBIRD);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        getHybirdPageUiSet(context).setOnHybirdPageStatusListener(new OnHybirdPageStatusListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 31, 0});
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m46lambda$new$0$comhzbhdcanbuscar_11UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m47lambda$new$1$comhzbhdcanbuscar_11UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m48lambda$new$2$comhzbhdcanbuscar_11UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m49lambda$new$3$comhzbhdcanbuscar_11UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m50lambda$new$4$comhzbhdcanbuscar_11UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        if (this.mDifferent == 2) {
            airUiSet.getFrontArea().setShowSeatCold(true);
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                        return;
                    }
                    if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                        return;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        return;
                    } else {
                        if (i2 != 3) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                        return;
                    }
                }
                if (i == 1) {
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                            break;
                        case 9:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                            break;
                        case 10:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                            break;
                        case 11:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                            break;
                        case 12:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) i3});
                            break;
                        case 13:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                            break;
                        case 14:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) i3});
                            break;
                    }
                    return;
                }
                if (i == 2) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                        return;
                    } else {
                        if (i2 != 1) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                        return;
                    }
                }
                if (i != 3) {
                    if (i == 4) {
                        if (i2 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                            return;
                        } else if (i2 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                            return;
                        } else {
                            if (i2 != 3) {
                                return;
                            }
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) i3});
                            return;
                        }
                    }
                    if (i != 5) {
                        return;
                    }
                    if (i2 != 0) {
                        if (i2 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                            return;
                        } else {
                            if (i2 != 2) {
                                return;
                            }
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                            return;
                        }
                    }
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                        return;
                    } else {
                        if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                            return;
                        }
                        return;
                    }
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                    return;
                }
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                    return;
                }
                if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte) i3});
                    return;
                }
                if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte) i3});
                    return;
                }
                if (i2 != 5) {
                    return;
                }
                int i4 = (UiMgr.this.mDifferent == 1 ? 0 : 2) + i3;
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i4});
                SharePreUtil.setIntValue(context, UiMgr.SHARE_11_LANGUAGE, i4);
                try {
                    if (UiMgr.this.mMsgMgr == null || UiMgr.this.mDifferent != 1) {
                        return;
                    }
                    UiMgr.this.mMsgMgr.updateSetting(i, i2, i3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == UiMgr.this.mMinuteFuelLeft) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
                    }
                } else if (i == UiMgr.this.mHistoricalLeft) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_326_radar_info_1")) {
                    UiMgr.this.mMsgMgr.updateSettings(UiMgr.this.mContext, "FRONT_RADAR_KEY", i, i2, i3);
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 1) {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                } else if (i == 4 && i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                }
            }
        });
        if (this.mDifferent == 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("language_status_0");
            arrayList.add("language_status_1");
            settingUiSet.getList().get(3).getItemList().get(5).setValueSrnArray(arrayList);
            settingUiSet.getList().get(3).getItemList().get(5).setValue("null_value");
        }
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                try {
                    if (UiMgr.this.mMsgMgr != null) {
                        UiMgr.this.mMsgMgr.updateSettingData(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass21.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass21.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    Log.i("ljq", "position: value = " + i);
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + 7)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
                }
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.11
            /* JADX WARN: Type inference failed for: r2v1, types: [com.hzbhd.canbus.car._11.UiMgr$11$1] */
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    new Thread() { // from class: com.hzbhd.canbus.car._11.UiMgr.11.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            super.run();
                            try {
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 35, 0});
                                sleep(100L);
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39, 0});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Log.i("_11_", "UiMgr: different: " + this.mDifferent);
        Log.i("_11_", "UiMgr: dp495" + CommUtil.getDimenByResId(context, "dp495"));
        Log.i("_11_", "UiMgr: dp190" + CommUtil.getDimenByResId(context, "dp190"));
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._11.UiMgr.12
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public void onTouchItem(MotionEvent motionEvent) {
                boolean zIsBackCamera = CommUtil.isBackCamera(context);
                boolean zIsPanoramic = CommUtil.isPanoramic(context);
                Log.i("_11_", "onTouchItem: isBackCamera: " + zIsBackCamera + ", isParkPanoramic: " + zIsPanoramic);
                if (motionEvent.getAction() == 0) {
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();
                    Log.i("_11_", "onTouchItem: x: " + x + ", y: " + y);
                    if (y > CommUtil.getDimenByResId(context, "dp592") || y < CommUtil.getDimenByResId(context, "dp495")) {
                        return;
                    }
                    if (!zIsBackCamera) {
                        if (zIsPanoramic) {
                            Log.i("_11_", "onTouchItem: panoramic");
                            if (x <= CommUtil.getDimenByResId(context, "dp190") && x >= CommUtil.getDimenByResId(context, "dp30")) {
                                Log.i("_11_", "onTouchItem: panoramic_1");
                                CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
                                return;
                            } else {
                                if (x > CommUtil.getDimenByResId(context, "dp708") || x < CommUtil.getDimenByResId(context, "dp539")) {
                                    return;
                                }
                                Log.i("_11_", "onTouchItem: panoramic_2");
                                CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
                                return;
                            }
                        }
                        return;
                    }
                    Log.i("_11_", "onTouchItem: back");
                    if (x <= CommUtil.getDimenByResId(context, "dp190") && x >= CommUtil.getDimenByResId(context, "dp30")) {
                        Log.i("_11_", "onTouchItem: back_1");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, 1});
                    } else if (x <= CommUtil.getDimenByResId(context, "dp708") && x >= CommUtil.getDimenByResId(context, "dp539")) {
                        Log.i("_11_", "onTouchItem: back_2");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 7});
                    } else {
                        if (x > CommUtil.getDimenByResId(context, "dp1009") || x < CommUtil.getDimenByResId(context, "dp850")) {
                            return;
                        }
                        Log.i("_11_", "onTouchItem: back_3");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
                    }
                }
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_11-UiMgr, reason: not valid java name */
    /* synthetic */ void m46lambda$new$0$comhzbhdcanbuscar_11UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_11-UiMgr, reason: not valid java name */
    /* synthetic */ void m47lambda$new$1$comhzbhdcanbuscar_11UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_11-UiMgr, reason: not valid java name */
    /* synthetic */ void m48lambda$new$2$comhzbhdcanbuscar_11UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_11-UiMgr, reason: not valid java name */
    /* synthetic */ void m49lambda$new$3$comhzbhdcanbuscar_11UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_11-UiMgr, reason: not valid java name */
    /* synthetic */ void m50lambda$new$4$comhzbhdcanbuscar_11UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getRearArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: com.hzbhd.canbus.car._11.UiMgr$21, reason: invalid class name */
    static /* synthetic */ class AnonymousClass21 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(19);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "rear_power":
                sendAirCommand(42);
                break;
            case "blow_positive":
                sendAirCommand(8);
                break;
            case "blow_negative":
                sendAirCommand(7);
                break;
            case "ac":
                sendAirCommand(23);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
            case "auto_wind_mode":
                sendAirCommand(32);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(final int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, 1});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._11.UiMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, 0});
            }
        }, 100L);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._11.UiMgr$20] */
    private void sendPanoramicCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._11.UiMgr.20
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, (byte) i, 0});
            }
        }.start();
        playBeep();
    }

    private boolean isPanoramicUseBtn() {
        return this.mDifferent == 6;
    }

    private boolean isPanoramicUseCoordinate() {
        int i = this.mDifferent;
        return i == 4 || i == 7;
    }

    private void initPanoramicBtnPara(Context context) {
        this.mMaxWidth = CommUtil.getDimenByResId(context, "dp1024");
        this.mMaxHight = CommUtil.getDimenByResId(context, "dp600");
        this.mBtnWidth = CommUtil.getDimenByResId(context, "dp140");
        this.mBtnHeight = CommUtil.getDimenByResId(context, "dp100");
        this.mBtnThreeEnd = ((this.mMaxWidth * 3) / 4) - 20;
    }

    public void removeAmplifierModel() {
        removeMainPrjBtnByName(this.mContext, MainAction.AMPLIFIER);
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }
}
