package com.hzbhd.canbus.car._3;

import android.content.Context;
import com.hzbhd.canbus.car._3.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.util.CommUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0017H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"com/hzbhd/canbus/car/_3/MsgMgr$initParsers$1$17", "Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_3/MsgMgr;", "df_2Integer", "Ljava/text/DecimalFormat;", "getDf_2Integer", "()Ljava/text/DecimalFormat;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "Lkotlin/collections/ArrayList;", "parse0x20", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "parse0x30", "parse0x40", "parse0x50", "parse0x70", "unit0x10", "Lkotlin/Function0;", "", "value0x10", "", "value0x11", "parse", "dataLength", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$17 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final DecimalFormat df_2Integer;
    private final ArrayList<DriverUpdateEntity> list;
    private final Function1<DriverUpdateEntity, Unit> parse0x20;
    private final Function1<DriverUpdateEntity, Unit> parse0x30;
    private final Function1<DriverUpdateEntity, Unit> parse0x40;
    private final Function1<DriverUpdateEntity, Unit> parse0x50;
    private final Function1<DriverUpdateEntity, Unit> parse0x70;
    final /* synthetic */ MsgMgr this$0;
    private final Function0<String> unit0x10;
    private int value0x10;
    private int value0x11;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$17(final MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x50】Driving Data");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
        this.unit0x10 = new Function0<String>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$unit0x10$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return (msgMgr.mCanbusInfoInt[3] & 1) == 1 ? "mi" : "km";
            }
        };
        this.parse0x20 = new Function1<DriverUpdateEntity, Unit>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$parse0x20$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DriverUpdateEntity driverUpdateEntity) {
                invoke2(driverUpdateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DriverUpdateEntity driverUpdateEntity) {
                Intrinsics.checkNotNullParameter(driverUpdateEntity, "$this$null");
                ArrayList arrayList = this.this$0.list;
                StringBuilder sb = new StringBuilder();
                MsgMgr msgMgr2 = msgMgr;
                arrayList.add(driverUpdateEntity.setValue(sb.append(msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5], msgMgr.mCanbusInfoInt[6], msgMgr.mCanbusInfoInt[7]) / 10.0f).append(' ').append((String) this.this$0.unit0x10.invoke()).toString()));
            }
        };
        this.parse0x30 = new Function1<DriverUpdateEntity, Unit>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$parse0x30$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DriverUpdateEntity driverUpdateEntity) {
                invoke2(driverUpdateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DriverUpdateEntity driverUpdateEntity) {
                String strSubstring;
                Intrinsics.checkNotNullParameter(driverUpdateEntity, "$this$null");
                MsgMgr msgMgr2 = msgMgr;
                int iMergeValue = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5], msgMgr.mCanbusInfoInt[6], msgMgr.mCanbusInfoInt[7]);
                String str = "";
                switch (iMergeValue) {
                    case 65534:
                    case 65535:
                        strSubstring = "--";
                        break;
                    default:
                        String strValueOf = String.valueOf(iMergeValue / 10.0f);
                        int length = strValueOf.length();
                        int i = 0;
                        for (int i2 = 0; i2 < length; i2++) {
                            char cCharAt = strValueOf.charAt(i2);
                            if ('0' <= cCharAt && cCharAt < ':') {
                                i++;
                            }
                            if (i == 3 || i2 == strValueOf.length() - 1) {
                                strSubstring = strValueOf.substring(0, i2 + 1);
                                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                                break;
                            }
                        }
                        strSubstring = "";
                        break;
                }
                ArrayList arrayList = this.list;
                StringBuilder sbAppend = new StringBuilder().append(strSubstring).append(' ');
                int i3 = msgMgr.mCanbusInfoInt[3];
                if (i3 == 0) {
                    str = "L/100km";
                } else if (i3 == 1) {
                    str = "km/L";
                } else if (i3 == 2 || i3 == 3) {
                    str = "mpg";
                } else if (i3 != 4) {
                    switch (i3) {
                        case 18:
                            str = "kWh/100km";
                            break;
                        case 19:
                            str = "km/kWh";
                            break;
                        case 20:
                            str = "kWh/mi";
                            break;
                        case 21:
                            str = "mi/kWh";
                            break;
                    }
                } else {
                    str = "l/h";
                }
                arrayList.add(driverUpdateEntity.setValue(sbAppend.append(str).toString()));
            }
        };
        this.df_2Integer = new DecimalFormat("00");
        this.parse0x40 = new Function1<DriverUpdateEntity, Unit>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$parse0x40$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DriverUpdateEntity driverUpdateEntity) {
                invoke2(driverUpdateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DriverUpdateEntity driverUpdateEntity) {
                Intrinsics.checkNotNullParameter(driverUpdateEntity, "$this$null");
                ArrayList arrayList = this.this$0.list;
                StringBuilder sb = new StringBuilder();
                MsgMgr msgMgr2 = msgMgr;
                StringBuilder sbAppend = sb.append(msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5]) / 10.0f).append(' ');
                if ((msgMgr.mCanbusInfoInt[3] & 1) == 1) {
                    MsgMgr msgMgr3 = msgMgr;
                    String str = this.this$0.getDf_2Integer().format(((msgMgr.mCanbusInfoInt[4] + (msgMgr.mCanbusInfoInt[5] * 256)) / 10) * 1.6d);
                    Intrinsics.checkNotNullExpressionValue(str, "df_2Integer.format((mCan…InfoInt[5]*256)/10 * 1.6)");
                    msgMgr3.updateSpeedInfo(Integer.parseInt(str));
                } else {
                    MsgMgr msgMgr4 = msgMgr;
                    String str2 = this.this$0.getDf_2Integer().format(Integer.valueOf((msgMgr.mCanbusInfoInt[4] + (msgMgr.mCanbusInfoInt[5] * 256)) / 10));
                    Intrinsics.checkNotNullExpressionValue(str2, "df_2Integer.format((mCan…CanbusInfoInt[5]*256)/10)");
                    msgMgr4.updateSpeedInfo(Integer.parseInt(str2));
                }
                arrayList.add(driverUpdateEntity.setValue(sbAppend.append(Unit.INSTANCE).toString()));
            }
        };
        this.parse0x50 = new Function1<DriverUpdateEntity, Unit>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$parse0x50$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DriverUpdateEntity driverUpdateEntity) {
                invoke2(driverUpdateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DriverUpdateEntity driverUpdateEntity) {
                Intrinsics.checkNotNullParameter(driverUpdateEntity, "$this$null");
                MsgMgr msgMgr2 = msgMgr;
                int iMergeValue = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[3], msgMgr.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5]);
                ArrayList arrayList = this.list;
                StringBuilder sbAppend = new StringBuilder().append(iMergeValue / 60).append(" : ");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(iMergeValue % 60)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                arrayList.add(driverUpdateEntity.setValue(sbAppend.append(str).toString()));
            }
        };
        this.parse0x70 = new Function1<DriverUpdateEntity, Unit>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$17$parse0x70$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DriverUpdateEntity driverUpdateEntity) {
                invoke2(driverUpdateEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DriverUpdateEntity driverUpdateEntity) {
                Intrinsics.checkNotNullParameter(driverUpdateEntity, "$this$null");
                ArrayList arrayList = this.this$0.list;
                StringBuilder sb = new StringBuilder();
                MsgMgr msgMgr2 = msgMgr;
                arrayList.add(driverUpdateEntity.setValue(sb.append(((short) msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5])) / 10.0f).append(' ').append((msgMgr.mCanbusInfoInt[3] & 1) == 1 ? "km/kwh" : "kwh/100km").toString()));
            }
        };
    }

    public final DecimalFormat getDf_2Integer() {
        return this.df_2Integer;
    }

    @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            switch (this.this$0.mCanbusInfoInt[2]) {
                case 16:
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_10h");
                    if (driverUpdateEntity != null) {
                        MsgMgr msgMgr = this.this$0;
                        this.value0x10 = msgMgr.mergeValue(msgMgr.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5]);
                        this.list.add(driverUpdateEntity.setValue(this.value0x10 + ' ' + this.unit0x10.invoke()));
                        break;
                    }
                    break;
                case 17:
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_11h");
                    if (driverUpdateEntity2 != null) {
                        MsgMgr msgMgr2 = this.this$0;
                        this.value0x11 = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr2.mCanbusInfoInt[5]);
                        this.list.add(driverUpdateEntity2.setValue(this.value0x11 + ' ' + this.unit0x10.invoke()));
                        break;
                    }
                    break;
                case 18:
                    DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_12h");
                    if (driverUpdateEntity3 != null) {
                        this.list.add(driverUpdateEntity3.setValue((this.value0x10 + this.value0x11) + ' ' + this.unit0x10.invoke()));
                        break;
                    }
                    break;
                case 32:
                    DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_20h");
                    if (driverUpdateEntity4 != null) {
                        this.parse0x20.invoke(driverUpdateEntity4);
                        break;
                    }
                    break;
                case 33:
                    DriverUpdateEntity driverUpdateEntity5 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_21h");
                    if (driverUpdateEntity5 != null) {
                        this.parse0x20.invoke(driverUpdateEntity5);
                        break;
                    }
                    break;
                case 34:
                    DriverUpdateEntity driverUpdateEntity6 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_22h");
                    if (driverUpdateEntity6 != null) {
                        this.parse0x20.invoke(driverUpdateEntity6);
                        break;
                    }
                    break;
                case 48:
                    DriverUpdateEntity driverUpdateEntity7 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_30h");
                    if (driverUpdateEntity7 != null) {
                        this.parse0x30.invoke(driverUpdateEntity7);
                        break;
                    }
                    break;
                case 49:
                    DriverUpdateEntity driverUpdateEntity8 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_31h");
                    if (driverUpdateEntity8 != null) {
                        this.parse0x30.invoke(driverUpdateEntity8);
                        break;
                    }
                    break;
                case 50:
                    DriverUpdateEntity driverUpdateEntity9 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_32h");
                    if (driverUpdateEntity9 != null) {
                        this.parse0x30.invoke(driverUpdateEntity9);
                        break;
                    }
                    break;
                case 64:
                    DriverUpdateEntity driverUpdateEntity10 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_40h");
                    if (driverUpdateEntity10 != null) {
                        this.parse0x40.invoke(driverUpdateEntity10);
                        break;
                    }
                    break;
                case 65:
                    DriverUpdateEntity driverUpdateEntity11 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_41h");
                    if (driverUpdateEntity11 != null) {
                        this.parse0x40.invoke(driverUpdateEntity11);
                        break;
                    }
                    break;
                case 66:
                    DriverUpdateEntity driverUpdateEntity12 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_42h");
                    if (driverUpdateEntity12 != null) {
                        this.parse0x40.invoke(driverUpdateEntity12);
                        break;
                    }
                    break;
                case 80:
                    DriverUpdateEntity driverUpdateEntity13 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_50h");
                    if (driverUpdateEntity13 != null) {
                        this.parse0x50.invoke(driverUpdateEntity13);
                        break;
                    }
                    break;
                case 81:
                    DriverUpdateEntity driverUpdateEntity14 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_51h");
                    if (driverUpdateEntity14 != null) {
                        this.parse0x50.invoke(driverUpdateEntity14);
                        break;
                    }
                    break;
                case 82:
                    DriverUpdateEntity driverUpdateEntity15 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_52h");
                    if (driverUpdateEntity15 != null) {
                        this.parse0x50.invoke(driverUpdateEntity15);
                        break;
                    }
                    break;
                case 96:
                    DriverUpdateEntity driverUpdateEntity16 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_2_driver_15");
                    if (driverUpdateEntity16 != null) {
                        MsgMgr msgMgr3 = this.this$0;
                        this.list.add(driverUpdateEntity16.setValue(msgMgr3.mergeValue(msgMgr3.mCanbusInfoInt[4], msgMgr3.mCanbusInfoInt[5]) + ' ' + ((msgMgr3.mCanbusInfoInt[3] & 1) == 1 ? "l/h" : "gal/h")));
                        break;
                    }
                    break;
                case 97:
                    DriverUpdateEntity driverUpdateEntity17 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_2_driver_16");
                    if (driverUpdateEntity17 != null) {
                        MsgMgr msgMgr4 = this.this$0;
                        ArrayList<DriverUpdateEntity> arrayList = this.list;
                        StringBuilder sbAppend = new StringBuilder().append(msgMgr4.mergeValue(msgMgr4.mCanbusInfoInt[4], msgMgr4.mCanbusInfoInt[5]) / 10.0f).append(' ');
                        int i = msgMgr4.mCanbusInfoInt[3] & 3;
                        String str = "mpg";
                        if (i == 0) {
                            str = "L/100km";
                        } else if (i == 1) {
                            str = "km/L";
                        } else if (i != 2 && i != 3) {
                            str = "";
                        }
                        arrayList.add(driverUpdateEntity17.setValue(sbAppend.append(str).toString()));
                        break;
                    }
                    break;
                case 112:
                    DriverUpdateEntity driverUpdateEntity18 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_70h");
                    if (driverUpdateEntity18 != null) {
                        this.parse0x70.invoke(driverUpdateEntity18);
                        break;
                    }
                    break;
                case 113:
                    DriverUpdateEntity driverUpdateEntity19 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_71h");
                    if (driverUpdateEntity19 != null) {
                        this.parse0x70.invoke(driverUpdateEntity19);
                        break;
                    }
                    break;
                case 114:
                    DriverUpdateEntity driverUpdateEntity20 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_72h");
                    if (driverUpdateEntity20 != null) {
                        this.parse0x70.invoke(driverUpdateEntity20);
                        break;
                    }
                    break;
                case 128:
                    DriverUpdateEntity driverUpdateEntity21 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_3_50h_80h");
                    if (driverUpdateEntity21 != null) {
                        MsgMgr msgMgr5 = this.this$0;
                        Context context = this.$context;
                        ArrayList<DriverUpdateEntity> arrayList2 = this.list;
                        StringBuilder sbAppend2 = new StringBuilder().append(msgMgr5.mCanbusInfoInt[3]).append(' ');
                        int i2 = msgMgr5.mCanbusInfoInt[4];
                        arrayList2.add(driverUpdateEntity21.setValue(sbAppend2.append(CommUtil.getStrByResId(context, i2 != 1 ? i2 != 3 ? i2 != 5 ? i2 != 9 ? "_3_50h_80h_p2_e" : "_3_50h_80h_p2_9" : "_3_50h_80h_p2_5" : "_3_50h_80h_p2_3" : "_3_50h_80h_p2_1")).toString()));
                        break;
                    }
                    break;
            }
            this.this$0.updateGeneralDriveData(this.list);
            this.this$0.updateDriveDataActivity(null);
        }
    }
}
