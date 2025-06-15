package com.hzbhd.canbus.car._325;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) (SharePreUtil.getIntValue(context, "is_air_left_temp", 0) + 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) (SharePreUtil.getIntValue(context, "is_air_left_temp", 0) - 1)});
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) (SharePreUtil.getIntValue(context, "is_air_right_temp", 0) + 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) (SharePreUtil.getIntValue(context, "is_air_right_temp", 0) - 1)});
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte) (SharePreUtil.getIntValue(context, "is_air_right_temp", 0) - 1)});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            Context context = UiMgr.this.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte) (SharePreUtil.getIntValue(context, "is_air_right_temp", 0) + 1)});
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:4:0x001c  */
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r6) {
                /*
                    r5 = this;
                    com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r2
                    com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                    java.lang.String[][] r0 = r0.getLineBtnAction()
                    r1 = 0
                    r0 = r0[r1]
                    r6 = r0[r6]
                    r6.hashCode()
                    int r0 = r6.hashCode()
                    r2 = 2
                    r3 = 1
                    r4 = -1
                    switch(r0) {
                        case -1786872896: goto L34;
                        case 99489345: goto L29;
                        case 1438998804: goto L1e;
                        default: goto L1c;
                    }
                L1c:
                    r1 = r4
                    goto L3d
                L1e:
                    java.lang.String r0 = "auto_1_2"
                    boolean r6 = r6.equals(r0)
                    if (r6 != 0) goto L27
                    goto L1c
                L27:
                    r1 = r2
                    goto L3d
                L29:
                    java.lang.String r0 = "in_out_auto_cycle"
                    boolean r6 = r6.equals(r0)
                    if (r6 != 0) goto L32
                    goto L1c
                L32:
                    r1 = r3
                    goto L3d
                L34:
                    java.lang.String r0 = "steering_wheel_heating"
                    boolean r6 = r6.equals(r0)
                    if (r6 != 0) goto L3d
                    goto L1c
                L3d:
                    r6 = 4
                    switch(r1) {
                        case 0: goto L84;
                        case 1: goto L67;
                        case 2: goto L42;
                        default: goto L41;
                    }
                L41:
                    goto L99
                L42:
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.auto_1_2
                    if (r0 != 0) goto L4e
                    byte[] r0 = new byte[r6]
                    r0 = {x00b2: FILL_ARRAY_DATA , data: [22, 58, 2, 1} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                L4e:
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.auto_1_2
                    if (r0 != r3) goto L5a
                    byte[] r0 = new byte[r6]
                    r0 = {x00b8: FILL_ARRAY_DATA , data: [22, 58, 2, 2} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                L5a:
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.auto_1_2
                    if (r0 != r2) goto L99
                    byte[] r6 = new byte[r6]
                    r6 = {x00be: FILL_ARRAY_DATA , data: [22, 58, 2, 0} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
                    goto L99
                L67:
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.in_out_auto_cycle
                    if (r0 == 0) goto L6f
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.in_out_auto_cycle
                    if (r0 != r3) goto L77
                L6f:
                    byte[] r0 = new byte[r6]
                    r0 = {x00c4: FILL_ARRAY_DATA , data: [22, 58, 1, 1} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                L77:
                    int r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.in_out_auto_cycle
                    if (r0 != r3) goto L99
                    byte[] r6 = new byte[r6]
                    r6 = {x00ca: FILL_ARRAY_DATA , data: [22, 49, 1, 0} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
                    goto L99
                L84:
                    boolean r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.steering_wheel_heating
                    if (r0 == 0) goto L91
                    byte[] r6 = new byte[r6]
                    r6 = {x00d0: FILL_ARRAY_DATA , data: [22, 58, 10, 0} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
                    goto L99
                L91:
                    byte[] r6 = new byte[r6]
                    r6 = {x00d6: FILL_ARRAY_DATA , data: [22, 58, 10, 1} // fill-array
                    com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
                L99:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._325.UiMgr.AnonymousClass1.onClickItem(int):void");
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[1][i];
                str.hashCode();
                if (str.equals("power")) {
                    if (GeneralAirData.power) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 1});
                    }
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[2][i];
                str.hashCode();
                if (str.equals("front_defog")) {
                    if (GeneralAirData.front_defog) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 11, 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 11, 1});
                    }
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[3][i];
                str.hashCode();
                switch (str) {
                    case "blow_window":
                        if (!GeneralAirData.front_left_blow_window) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 3, 1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 3, 0});
                            break;
                        }
                    case "blow_foot":
                        if (!GeneralAirData.front_left_blow_foot) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 5, 1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 5, 0});
                            break;
                        }
                    case "blow_head":
                        if (!GeneralAirData.front_left_blow_head) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 4, 1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 4, 0});
                            break;
                        }
                }
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_303_setting_content_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, (byte) i3});
                        break;
                    case "_303_setting_content_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 19, (byte) i3});
                        break;
                    case "_303_setting_content_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 18, (byte) i3});
                        break;
                    case "_303_setting_content_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 16, (byte) i3});
                        break;
                    case "_303_setting_content_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_303_setting_content_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte) i3});
                        break;
                    case "_325_setting_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte) i3});
                        break;
                    case "_303_setting_content_11":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3});
                        break;
                    case "_303_setting_content_12":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3});
                        break;
                    case "_303_setting_content_13":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3});
                        break;
                    case "_303_setting_content_14":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i3});
                        break;
                    case "_303_setting_content_15":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte) i3});
                        break;
                    case "_303_setting_content_16":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte) i3});
                        break;
                    case "_303_setting_content_17":
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte) i3});
                        break;
                    case "_303_setting_content_22":
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 5, (byte) i3});
                        break;
                    case "_303_setting_content_23":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_24":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_25":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_26":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_27":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_28":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte) (i3 + 1)});
                        break;
                    case "_303_setting_content_30":
                        CanbusMsgSender.sendMsg(new byte[]{22, -52, 2, (byte) i3});
                        break;
                    case "_303_setting_content_37":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte) (i3 + 1)});
                        break;
                    case "language_setup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_325_light_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte) i3});
                        break;
                    case "_325_setting_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte) i3});
                        break;
                    case "_303_setting_content_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 17, (byte) i3});
                        break;
                    case "_303_setting_content_18":
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte) i3});
                        break;
                    case "_303_setting_content_19":
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte) i3});
                        break;
                    case "_303_setting_content_20":
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte) i3});
                        break;
                    case "_303_setting_content_21":
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 4, (byte) i3});
                        break;
                    case "_303_setting_content_38":
                        CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._325.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_303_setting_content_29")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -52, 1, 1});
                }
            }
        });
    }
}
