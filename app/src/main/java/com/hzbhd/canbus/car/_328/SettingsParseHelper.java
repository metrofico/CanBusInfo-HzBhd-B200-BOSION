package com.hzbhd.canbus.car._328;

import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;



/* renamed from: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$SettingsParseHelper, reason: from toString */

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

    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.parse.hashCode();
    }

    public String toString() {
        return "SettingsParseHelper(title=" + this.title + ", parse=" + this.parse + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsParseHelper(String title, Function1<? super Integer, Integer> parse) {


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
