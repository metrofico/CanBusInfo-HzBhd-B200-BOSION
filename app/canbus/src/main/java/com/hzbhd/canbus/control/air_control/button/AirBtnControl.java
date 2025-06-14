package com.hzbhd.canbus.control.air_control.button;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.control.air_control.AbstractAirControl;

/* loaded from: classes2.dex */
public class AirBtnControl extends AbstractAirControl {
    private String[][] mAirBtnActions;
    private OnAirBtnClickListener[] mOnAirBtnClickListeners;

    public AirBtnControl(String[][] strArr, OnAirBtnClickListener[] onAirBtnClickListenerArr) {
        this.mAirBtnActions = strArr;
        this.mOnAirBtnClickListeners = onAirBtnClickListenerArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ab, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0088  */
    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void action(java.lang.String r6) {
        /*
            r5 = this;
            com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener[] r0 = r5.mOnAirBtnClickListeners
            boolean r0 = com.android.internal.util.ArrayUtils.isEmpty(r0)
            if (r0 == 0) goto L9
            return
        L9:
            r6.hashCode()
            r0 = -1
            int r1 = r6.hashCode()
            r2 = 0
            switch(r1) {
                case -1412208249: goto L4d;
                case -1226270570: goto L42;
                case -866529054: goto L37;
                case -828782905: goto L2c;
                case 629126444: goto L21;
                case 1496068108: goto L16;
                default: goto L15;
            }
        L15:
            goto L57
        L16:
            java.lang.String r1 = "air.in.out.cycle.on"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L1f
            goto L57
        L1f:
            r0 = 5
            goto L57
        L21:
            java.lang.String r1 = "ac.close"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L2a
            goto L57
        L2a:
            r0 = 4
            goto L57
        L2c:
            java.lang.String r1 = "air.ac.off"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L35
            goto L57
        L35:
            r0 = 3
            goto L57
        L37:
            java.lang.String r1 = "air.in.out.cycle.off"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L40
            goto L57
        L40:
            r0 = 2
            goto L57
        L42:
            java.lang.String r1 = "ac.open"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L4b
            goto L57
        L4b:
            r0 = 1
            goto L57
        L4d:
            java.lang.String r1 = "air.ac.on"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L56
            goto L57
        L56:
            r0 = r2
        L57:
            java.lang.String r1 = "in_out_cycle"
            java.lang.String r3 = "ac"
            java.lang.String r4 = "power"
            switch(r0) {
                case 0: goto L7e;
                case 1: goto L77;
                case 2: goto L72;
                case 3: goto L6d;
                case 4: goto L68;
                case 5: goto L61;
                default: goto L60;
            }
        L60:
            goto L84
        L61:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.in_out_cycle
            if (r6 != 0) goto L66
            return
        L66:
            r6 = r1
            goto L84
        L68:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.power
            if (r6 != 0) goto L7c
            return
        L6d:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.ac
            if (r6 != 0) goto L83
            return
        L72:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.in_out_cycle
            if (r6 == 0) goto L66
            return
        L77:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.power
            if (r6 == 0) goto L7c
            return
        L7c:
            r6 = r4
            goto L84
        L7e:
            boolean r6 = com.hzbhd.canbus.ui_datas.GeneralAirData.ac
            if (r6 == 0) goto L83
            return
        L83:
            r6 = r3
        L84:
            java.lang.String[][] r0 = r5.mAirBtnActions
            if (r0 == 0) goto Lae
            r0 = r2
        L89:
            java.lang.String[][] r1 = r5.mAirBtnActions
            int r1 = r1.length
            if (r0 >= r1) goto Lae
            r1 = r2
        L8f:
            java.lang.String[][] r3 = r5.mAirBtnActions
            r3 = r3[r0]
            int r4 = r3.length
            if (r1 >= r4) goto Lab
            r3 = r3[r1]
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto La8
            com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener[] r6 = r5.mOnAirBtnClickListeners
            r6 = r6[r0]
            if (r6 == 0) goto La7
            r6.onClickItem(r1)
        La7:
            return
        La8:
            int r1 = r1 + 1
            goto L8f
        Lab:
            int r0 = r0 + 1
            goto L89
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.control.air_control.button.AirBtnControl.action(java.lang.String):void");
    }
}
