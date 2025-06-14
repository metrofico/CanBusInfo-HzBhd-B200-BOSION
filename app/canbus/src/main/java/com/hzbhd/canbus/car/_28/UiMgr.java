package com.hzbhd.canbus.car._28;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final String _28_AMPLIFIER_MUTE = "_28_amplifier_mute";
    private MsgMgr mMsgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        getMsgMgr(context).updateSetting(0, 0, SharePreUtil.getIntValue(context, _28_AMPLIFIER_MUTE, 0));
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._28.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_197_amplifier_mute")) {
                    SharePreUtil.setIntValue(context, UiMgr._28_AMPLIFIER_MUTE, i3);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte) i3});
                    UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._28.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$0(amplifierPosition, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._28.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                UiMgr.lambda$new$1(amplifierBand, i);
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._28.UiMgr$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ void lambda$new$0(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass2.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 6 + 10)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 6 + 10)});
        }
    }

    static /* synthetic */ void lambda$new$1(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass2.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (i + 6)});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (i + 6)});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (i + 6)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
