package com.hzbhd.canbus.car._94;

import android.util.SparseIntArray;
import com.hzbhd.R;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$17 extends MsgMgr.Parser {
    private final SparseIntArray syncMenuIconResIdArray;
    final /* synthetic */ MsgMgr this$0;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m968setOnParseListeners$lambda1() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m970setOnParseListeners$lambda3() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m971setOnParseListeners$lambda4() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m974setOnParseListeners$lambda7() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m975setOnParseListeners$lambda8() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$17(MsgMgr msgMgr) {
        super(msgMgr, "【0x50】SYNC 菜单信息");
        this.this$0 = msgMgr;
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.syncMenuIconResIdArray = sparseIntArray;
        sparseIntArray.put(0, R.drawable.ford_sync_icon_null);
        sparseIntArray.append(2, R.drawable.ford_sync_icon_3);
        sparseIntArray.append(10, R.drawable.ford_sync_icon_11);
        sparseIntArray.append(8, R.drawable.ford_sync_icon_9);
        sparseIntArray.append(5, R.drawable.ford_sync_icon_6);
        sparseIntArray.append(12, R.drawable.ford_sync_icon_13);
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m968setOnParseListeners$lambda1();
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m969setOnParseListeners$lambda2(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m970setOnParseListeners$lambda3();
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m971setOnParseListeners$lambda4();
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m972setOnParseListeners$lambda5(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m973setOnParseListeners$lambda6(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m974setOnParseListeners$lambda7();
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m975setOnParseListeners$lambda8();
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m969setOnParseListeners$lambda2(MsgMgr this$0) {

        GeneralSyncData.mSelectedLineIndex = this$0.mCanbusInfoInt[3] - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m972setOnParseListeners$lambda5(MsgMgr this$0) {

        if (this$0.mCanbusInfoInt[5] != 0) {
            GeneralSyncData.mSelectedLineIndex = this$0.mCanbusInfoInt[6] - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m973setOnParseListeners$lambda6(MsgMgr$initParsers$1$17 this$0, MsgMgr this$1) {


        GeneralSyncData.mSyncTopIconResIdArray[0] = this$0.syncMenuIconResIdArray.get(this$1.mCanbusInfoInt[7]);
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            this.this$0.updateSyncActivity(null);
        }
    }
}
