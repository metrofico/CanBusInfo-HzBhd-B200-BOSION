package com.hzbhd.canbus.car._328;

import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B(\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001a\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0003J3\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\"\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"com/hzbhd/canbus/car/_328/MsgMgr$initParsers$1$SettingsParseHelper", "", LcdInfoShare.MediaInfo.title, "", "parse", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getParse", "()Lkotlin/jvm/functions/Function1;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lcom/hzbhd/canbus/car/_328/MsgMgr$initParsers$1$SettingsParseHelper;", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* renamed from: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$SettingsParseHelper, reason: from toString */
/* loaded from: classes2.dex */
public final /* data */ class SettingsParseHelper {
    private final Function1<Integer, Integer> parse;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SettingsParseHelper copy$default(SettingsParseHelper settingsParseHelper, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = settingsParseHelper.title;
        }
        if ((i & 2) != 0) {
            function1 = settingsParseHelper.parse;
        }
        return settingsParseHelper.copy(str, function1);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final Function1<Integer, Integer> component2() {
        return this.parse;
    }

    public final SettingsParseHelper copy(String title, Function1<? super Integer, Integer> parse) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(parse, "parse");
        return new SettingsParseHelper(title, parse);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsParseHelper)) {
            return false;
        }
        SettingsParseHelper settingsParseHelper = (SettingsParseHelper) other;
        return Intrinsics.areEqual(this.title, settingsParseHelper.title) && Intrinsics.areEqual(this.parse, settingsParseHelper.parse);
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.parse.hashCode();
    }

    public String toString() {
        return "SettingsParseHelper(title=" + this.title + ", parse=" + this.parse + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsParseHelper(String title, Function1<? super Integer, Integer> parse) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(parse, "parse");
        this.title = title;
        this.parse = parse;
    }

    public /* synthetic */ SettingsParseHelper(String str, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new Function1<Integer, Integer>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$SettingsParseHelper.1
            public final Integer invoke(int i2) {
                return Integer.valueOf((i2 >> 4) & 7);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        } : anonymousClass1);
    }

    public final Function1<Integer, Integer> getParse() {
        return this.parse;
    }

    public final String getTitle() {
        return this.title;
    }
}
