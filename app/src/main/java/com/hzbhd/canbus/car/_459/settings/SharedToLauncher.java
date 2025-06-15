package com.hzbhd.canbus.car._459.settings;

import com.hzbhd.canbus.car._459.tbox_wifi.TboxWifiState;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.constant.share.ShareConstants;


public class SharedToLauncher {
    public void syncStateToLauncher() {
        notifyOtherModule("BL", Integer.valueOf(OptionSettingsCmd459.getInstance().getBL()));
        notifyOtherModule("DCM", Boolean.valueOf(OptionSettingsCmd459.getInstance().getDCM()));
        notifyOtherModule("FL", Boolean.valueOf(OptionSettingsCmd459.getInstance().getFL()));
        notifyOtherModule("SPS", Boolean.valueOf(OptionSettingsCmd459.getInstance().getSPS()));
        notifyOtherModule("ER", Boolean.valueOf(OptionSettingsCmd459.getInstance().getER()));
        notifyOtherModule("SA", Boolean.valueOf(OptionSettingsCmd459.getInstance().getSA()));
        notifyOtherModule("AWS", Boolean.valueOf(OptionSettingsCmd459.getInstance().getAWS()));
        notifyOtherModule("LD", Boolean.valueOf(OptionSettingsCmd459.getInstance().getLD()));
        notifyOtherModule("CW", Boolean.valueOf(OptionSettingsCmd459.getInstance().getCW()));
        notifyOtherModule("VDM", OptionSettingsCmd459.getInstance().getVDM());
        notifyOtherModule("FLWSL", Boolean.valueOf(OptionSettingsCmd459.getInstance().getFLWSL()));
        notifyOtherModule("WIFI_CONNECT", TboxWifiState.connect);
        notifyOtherModule("WIFI_NAME", TboxWifiState.name);
        notifyOtherModule("WIFI_PASSWORD", TboxWifiState.password);
    }

    private synchronized void notifyOtherModule(String str, Object obj) {
        CanbusInfoChangeListener.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"" + str + "\",\"VALUE\":\"" + obj + "\"}");
    }
}
