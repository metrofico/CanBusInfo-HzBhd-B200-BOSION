package com.hzbhd.canbus.car._312;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;


public class UiMgr extends AbstractUiMgr {
    protected AmplifierParams mAmplifierParams;
    private MsgMgr mMsgMgr;
    private final String TAG = "_306_UiMgr";
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._312.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.lambda$new$0(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._312.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.lambda$new$1(settingUiSet, i, i2);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._312.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                m492lambda$new$2$comhzbhdcanbuscar_312UiMgr(amplifierPosition, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._312.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                m493lambda$new$3$comhzbhdcanbuscar_312UiMgr(amplifierBand, i);
            }
        });
        initAmplifierData(amplifierPageUiSet);
    }

    static /* synthetic */ void lambda$new$0(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_312_interior_light":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                break;
            case "_312_auto_door_unlock":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                break;
            case "amplifier_switch":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) i3});
                break;
            case "_312_hazard_warning_flasher":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                break;
            case "_312_one_touch_lane_changer":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                break;
            case "_41_park_assist":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                break;
            case "_312_security_re_locking":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                break;
            case "_312_auto_door_lock":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                break;
            case "_312_wheel_control_type":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                break;
            case "_312_rab":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                break;
            case "_312_defogger":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                break;
        }
    }

    static /* synthetic */ void lambda$new$1(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("vm_golf7_vehicle_setup_factory_setup")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, -86});
        }
    }

    /* renamed from: com.hzbhd.canbus.car._312.UiMgr$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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
                iArr2[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_312-UiMgr, reason: not valid java name */
    /* synthetic */ void m492lambda$new$2$comhzbhdcanbuscar_312UiMgr(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) this.mAmplifierParams.sendBalance(i)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) this.mAmplifierParams.sendBalance(i)});
        }
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_312-UiMgr, reason: not valid java name */
    /* synthetic */ void m493lambda$new$3$comhzbhdcanbuscar_312UiMgr(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) i});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) this.mAmplifierParams.sendBand(i)});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) this.mAmplifierParams.sendBand(i)});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) this.mAmplifierParams.sendBand(i)});
        }
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    protected static class AmplifierParams {
        private final ItemParams balance;
        private final ItemParams band;
        private final int volumeMax;

        public AmplifierParams(int i, ItemParams itemParams, ItemParams itemParams2) {
            this.volumeMax = i;
            this.balance = itemParams;
            this.band = itemParams2;
        }

        public int sendBand(int i) {
            return i + this.band.min;
        }

        public int sendBalance(int i) {
            return i + this.balance.mid;
        }

        public int getSeekBarMax() {
            return this.band.max - this.band.min;
        }

        public int getBandRange() {
            return this.band.mid - this.band.min;
        }

        public int getBalanceRange() {
            return this.balance.max - this.balance.min;
        }

        public int getVolumeMax() {
            return this.volumeMax;
        }

        public ItemParams getBand() {
            return this.band;
        }

        public ItemParams getBalance() {
            return this.balance;
        }

        private static class ItemParams {
            private final int max;
            private final int mid;
            private final int min;

            public ItemParams(int i, int i2, int i3) {
                this.min = i;
                this.mid = i2;
                this.max = i3;
            }
        }
    }

    private void initAmplifierData(AmplifierPageUiSet amplifierPageUiSet) {
        if (DataHandleUtils.getIntFromByteWithBit(this.mDifferent, 0, 4) == 1) {
            this.mAmplifierParams = new AmplifierParams(63, new AmplifierParams.ItemParams(3, 10, 17), new AmplifierParams.ItemParams(5, 10, 15));
        } else {
            this.mAmplifierParams = new AmplifierParams(38, new AmplifierParams.ItemParams(1, 10, 19), new AmplifierParams.ItemParams(1, 10, 19));
        }
        amplifierPageUiSet.setSeekBarMax(this.mAmplifierParams.getSeekBarMax());
        amplifierPageUiSet.setBandRange(this.mAmplifierParams.getBandRange());
        amplifierPageUiSet.setBalanceRange(this.mAmplifierParams.getBalanceRange());
        amplifierPageUiSet.setSeekBarVolumeMax(this.mAmplifierParams.getVolumeMax());
    }

    int getBandMin() {
        return this.mAmplifierParams.getBand().min;
    }

    int getBalanceMid() {
        return this.mAmplifierParams.getBalance().mid;
    }
}
