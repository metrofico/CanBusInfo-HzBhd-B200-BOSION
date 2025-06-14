package com.hzbhd.canbus.car._94;

import android.util.Log;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0015\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fH\u0016¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$8", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "day", "", "hour", "minute", "minuteRecord", "month", "second", "year", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$8 extends MsgMgr.Parser {
    private int day;
    private int hour;
    private int minute;
    private int minuteRecord;
    private int month;
    private int second;
    final /* synthetic */ MsgMgr this$0;
    private int year;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$8(MsgMgr msgMgr) {
        super(msgMgr, "【0x26】车身日期时间信息");
        this.this$0 = msgMgr;
        this.minuteRecord = -1;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1056setOnParseListeners$lambda0(this.f$0, msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1057setOnParseListeners$lambda1(this.f$0, msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1058setOnParseListeners$lambda2(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1059setOnParseListeners$lambda3(this.f$0, msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1060setOnParseListeners$lambda4(this.f$0, msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m1061setOnParseListeners$lambda5(this.f$0, msgMgr6);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m1056setOnParseListeners$lambda0(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.year = this$1.mCanbusInfoInt[2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m1057setOnParseListeners$lambda1(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.month = this$1.mCanbusInfoInt[3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m1058setOnParseListeners$lambda2(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.day = this$1.mCanbusInfoInt[4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m1059setOnParseListeners$lambda3(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.hour = this$1.mCanbusInfoInt[5];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m1060setOnParseListeners$lambda4(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.minute = this$1.mCanbusInfoInt[6];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m1061setOnParseListeners$lambda5(MsgMgr$initParsers$1$8 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.second = this$1.mCanbusInfoInt[7];
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        super.parse(dataLength);
        int i = this.minuteRecord;
        int i2 = this.minute;
        if (i != i2) {
            this.minuteRecord = i2;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("parse: %d年%d月%d日%d时%d分%d秒", Arrays.copyOf(new Object[]{Integer.valueOf(this.year), Integer.valueOf(this.month), Integer.valueOf(this.day), Integer.valueOf(this.hour), Integer.valueOf(this.minute), Integer.valueOf(this.second)}, 6));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            Log.i(MsgMgr.TAG, str);
        }
    }
}
