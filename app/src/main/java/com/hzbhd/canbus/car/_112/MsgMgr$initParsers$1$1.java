package com.hzbhd.canbus.car._112;

import android.util.Log;
import com.hzbhd.canbus.car._112.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr$initParsers$1$1 extends MsgMgr.Parser {
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$1(MsgMgr msgMgr) {
        super(msgMgr, "【0x07】车辆行驶油耗信息");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public void parse(int dataLength) {
        String result;
        if (isDataChange()) {
            MsgMgr msgMgr = this.this$0;
            ArrayList arrayList = new ArrayList();
            MsgMgr msgMgr2 = this.this$0;
            final String str = msgMgr2.mUnits == 1 ? "KM" : "MP";
            int[] iArr = msgMgr2.mCanbusInfoInt;
            int i = iArr[2];
            int i2 = i - 1;
            switch (i) {
                case 1:
                    result = toInteger(new int[]{iArr[4], iArr[3]}) + ' ' + str;
                    break;
                case 2:
                case 3:
                case 7:
                    result = getResult(new int[]{iArr[4], iArr[3]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$1$parse$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(int[] getResult) {

                            return this.this$0.getBcdHigh(getResult[1]) + this.this$0.getBcdLow(getResult[1]) + '.' + this.this$0.getBcdHigh(getResult[0]) + this.this$0.getBcdLow(getResult[0]) + " L/100" + str;
                        }
                    });
                    break;
                case 4:
                case 8:
                    result = getResult(new int[]{iArr[4], iArr[3]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$1$parse$1$1$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(int[] getResult) {

                            return this.this$0.toInteger(getResult) + ' ' + str + "/H";
                        }
                    });
                    break;
                case 5:
                case 9:
                    result = getResult(new int[]{iArr[5], iArr[4], iArr[3]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$1$parse$1$1$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(int[] getResult) {

                            return (this.this$0.toInteger(getResult) / 10) + ' ' + str;
                        }
                    });
                    break;
                case 6:
                case 10:
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    result = String.format("%d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf((iArr[3] << 8) | iArr[4]), Integer.valueOf(iArr[5])}, 2));

                    break;
                default:
                    result = " ";
                    break;
            }
            arrayList.add(new DriverUpdateEntity(1, i2, result));
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toInteger(int[] iArr) {
        Log.i(MsgMgr.TAG, "toInteger: " + iArr.length);
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            i2 |= iArr[i] << (i3 * 8);
            i++;
            i3++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getBcdHigh(int i) {
        int i2 = (i >> 4) & 15;
        boolean z = false;
        if (10 <= i2 && i2 < 16) {
            z = true;
        }
        if (z) {
            return String.valueOf((char) (i2 + 55));
        }
        return String.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getBcdLow(int i) {
        int i2 = i & 15;
        boolean z = false;
        if (10 <= i2 && i2 < 16) {
            z = true;
        }
        if (z) {
            return String.valueOf((char) (i2 + 55));
        }
        return String.valueOf(i2);
    }

    private final String getResult(int[] params, Function1<? super int[], String> result) {
        for (int i : params) {
            if (i != 255) {
                return result.invoke(params);
            }
        }
        return "--";
    }
}
