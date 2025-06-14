package com.hzbhd.canbus.control.air_control.button;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.ui_datas.GeneralAirData;

/* loaded from: classes2.dex */
public class AirBtnControl extends AbstractAirControl {
    private String[][] mAirBtnActions;
    private OnAirBtnClickListener[] mOnAirBtnClickListeners;

    public AirBtnControl(String[][] strArr, OnAirBtnClickListener[] onAirBtnClickListenerArr) {
        this.mAirBtnActions = strArr;
        this.mOnAirBtnClickListeners = onAirBtnClickListenerArr;
    }


    @Override
    public void action(String command) {
        if (mOnAirBtnClickListeners != null && mOnAirBtnClickListeners.length > 0) {
            switch (command) {
                case "air.ac.on":
                    if (GeneralAirData.ac) return;
                    command = "ac";
                    break;
                case "ac.open":
                    if (GeneralAirData.power) return;
                    command = "power";
                    break;
                case "air.in.out.cycle.off":
                    if (GeneralAirData.in_out_cycle) return;
                    command = "in_out_cycle";
                    break;
                case "air.ac.off":
                    if (!GeneralAirData.ac) return;
                    command = "ac";
                    break;
                case "ac.close":
                    if (!GeneralAirData.power) return;
                    command = "power";
                    break;
                case "air.in.out.cycle.on":
                    if (!GeneralAirData.in_out_cycle) return;
                    command = "in_out_cycle";
                    break;
            }

            if (mAirBtnActions != null) {
                for (int i = 0; i < mAirBtnActions.length; i++) {
                    String[] actions = mAirBtnActions[i];
                    for (int j = 0; j < actions.length; j++) {
                        if (actions[j].equals(command)) {
                            OnAirBtnClickListener listener = mOnAirBtnClickListeners[i];
                            if (listener != null) {
                                listener.onClickItem(j);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}
