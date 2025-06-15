package com.hzbhd.canbus.car._322;

import android.content.Context;
import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;




public final class MsgMgr$initParsers$1$17 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int[] amplifierData;
    private final ArrayList<SettingUpdateEntity<?>> list;
    private int[] settingData;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$17(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x70】功放信息");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m569setOnParseListeners$lambda1(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m570setOnParseListeners$lambda2(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m571setOnParseListeners$lambda3(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m572setOnParseListeners$lambda4(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m573setOnParseListeners$lambda5(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m574setOnParseListeners$lambda6(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m575setOnParseListeners$lambda9(msgMgr7, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m569setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_197_amplifier_mute");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 7) & 1)));
        }
        GeneralAmplifierData.volume = this$0.mCanbusInfoInt[2] & WorkQueueKt.MASK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m570setOnParseListeners$lambda2(MsgMgr this$0) {

        GeneralAmplifierData.leftRight = (this$0.mCanbusInfoInt[3] - 3) - 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m571setOnParseListeners$lambda3(MsgMgr this$0) {

        GeneralAmplifierData.frontRear = -((this$0.mCanbusInfoInt[4] - 3) - 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m572setOnParseListeners$lambda4(MsgMgr this$0) {

        GeneralAmplifierData.bandBass = this$0.mCanbusInfoInt[5] - 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m573setOnParseListeners$lambda5(MsgMgr this$0) {

        GeneralAmplifierData.bandMiddle = this$0.mCanbusInfoInt[6] - 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m574setOnParseListeners$lambda6(MsgMgr this$0) {

        GeneralAmplifierData.bandTreble = this$0.mCanbusInfoInt[7] - 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m575setOnParseListeners$lambda9(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_279_sound");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(((this$0.mCanbusInfoInt[8] >> 2) & 3) + 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_118_setting_title_97");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf(this$0.mCanbusInfoInt[8] & 3)));
        }
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            MsgMgr msgMgr = this.this$0;
            byte[] bArrCopyOf = Arrays.copyOf(msgMgr.mCanbusInfoByte, 9);

            msgMgr.mAmplifierData = bArrCopyOf;
            Context context = this.$context;
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : this.this$0.getMAmplifierData()) {
                stringBuffer.append(new StringBuilder().append((int) b).append(',').toString());
            }
            Unit unit = Unit.INSTANCE;
            SharePreUtil.setStringValue(context, "sahre_322_amplifier_datas", stringBuffer.toString());
            this.list.clear();
            super.parse(dataLength);
            if (isAmplifierChange()) {
                MsgMgr msgMgr2 = this.this$0;
                msgMgr2.saveAmplifierData(this.$context, msgMgr2.mCanId);
                this.this$0.updateAmplifierActivity(null);
            }
            if (isSettingChange()) {
                this.this$0.updateGeneralSettingData(this.list);
                this.this$0.updateSettingActivity(null);
            }
        }
    }

    private final boolean isAmplifierChange() {
        int[] iArrCopyOfRange = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 8);
        iArrCopyOfRange[0] = iArrCopyOfRange[0] & WorkQueueKt.MASK;
        if (Arrays.equals(this.amplifierData, iArrCopyOfRange)) {
            return false;
        }
        this.amplifierData = (int[]) iArrCopyOfRange.clone();
        return true;
    }

    private final boolean isSettingChange() {
        int[] iArr = {this.this$0.mCanbusInfoInt[2] & 128, this.this$0.mCanbusInfoInt[8]};
        if (Arrays.equals(this.settingData, iArr)) {
            return false;
        }
        this.settingData = (int[]) iArr.clone();
        return true;
    }
}
