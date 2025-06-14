package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0015\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016¢\u0006\u0002\u0010\u0015R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$25", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "amplfierDataRecord", "", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "settingsDataRecord", "isNeedUpdateAmplifierActivity", "", "isNeedUpdateSettingActivity", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$25 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int[] amplfierDataRecord;
    private ArrayList<SettingUpdateEntity<Object>> list;
    private int[] settingsDataRecord;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$25(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x62】功放状态");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m982setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m983setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m984setOnParseListeners$lambda3(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m985setOnParseListeners$lambda6(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m986setOnParseListeners$lambda7(msgMgr5);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m982setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandTreble = (this$0.mCanbusInfoInt[2] >> 4) & 15;
        GeneralAmplifierData.bandMiddle = this$0.mCanbusInfoInt[2] & 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m983setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandBass = (this$0.mCanbusInfoInt[3] >> 4) & 15;
        GeneralAmplifierData.frontRear = (this$0.mCanbusInfoInt[3] & 15) - 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m984setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$25 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAmplifierData.leftRight = ((this$0.mCanbusInfoInt[4] >> 4) & 15) - 7;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("speed_linkage_volume_adjustment");
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[4] & 15)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m985setOnParseListeners$lambda6(MsgMgr this$0, MsgMgr$initParsers$1$25 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_sound_effect_mode");
        ArrayList<SettingUpdateEntity<Object>> arrayList = null;
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList2 = this$1.list;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList2 = null;
            }
            arrayList2.add(settingUpdateEntity.setValue(Integer.valueOf((this$0.mCanbusInfoInt[5] >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_position");
        if (settingUpdateEntity2 != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList3 = this$1.list;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
            } else {
                arrayList = arrayList3;
            }
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$0.mCanbusInfoInt[5] >> 5) & 3)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m986setOnParseListeners$lambda7(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.volume = this$0.mCanbusInfoInt[6];
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            super.parse(dataLength);
            if (isNeedUpdateAmplifierActivity()) {
                this.this$0.updateAmplifierActivity(null);
                MsgMgr msgMgr = this.this$0;
                msgMgr.saveAmplifierData(this.$context, msgMgr.mCanId);
            }
            if (isNeedUpdateSettingActivity()) {
                MsgMgr msgMgr2 = this.this$0;
                ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
                if (arrayList == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("list");
                    arrayList = null;
                }
                msgMgr2.updateGeneralSettingData(arrayList);
                this.this$0.updateSettingActivity(null);
            }
        }
    }

    private final boolean isNeedUpdateAmplifierActivity() {
        int[] iArr = {this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4] & NfDef.STATE_3WAY_M_HOLD, this.this$0.mCanbusInfoInt[6]};
        if (Arrays.equals(this.amplfierDataRecord, iArr)) {
            return false;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, 4);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
        this.amplfierDataRecord = iArrCopyOf;
        return true;
    }

    private final boolean isNeedUpdateSettingActivity() {
        int[] iArr = {this.this$0.mCanbusInfoInt[4] & 15, this.this$0.mCanbusInfoInt[5]};
        if (Arrays.equals(this.settingsDataRecord, iArr)) {
            return false;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, 2);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
        this.settingsDataRecord = iArrCopyOf;
        return true;
    }
}
