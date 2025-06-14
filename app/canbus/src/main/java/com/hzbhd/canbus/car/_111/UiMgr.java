package com.hzbhd.canbus.car._111;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import java.util.Objects;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    public UiMgr(final Context context) {
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._111.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                UiMgr.lambda$new$0(amplifierBand, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._111.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$1(amplifierPosition, i);
            }
        });
        getSettingUiSet(context).setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._111.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.lambda$new$2(context, i, i2, i3);
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._111.UiMgr$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ void lambda$new$0(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 1)});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 1)});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) (i + 1)});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) i});
        }
    }

    static /* synthetic */ void lambda$new$1(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) (10 - i)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 10)});
        }
    }

    static /* synthetic */ void lambda$new$2(Context context, int i, int i2, int i3) {
        if (i != 0) {
            return;
        }
        ((MsgMgr) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgr(context))).updateAmplifierSwitch(i3);
    }
}
