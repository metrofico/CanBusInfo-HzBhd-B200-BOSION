package com.hzbhd.canbus.park.external360cam;

import com.hzbhd.canbus.adapter.util.FutureUtil;

/* loaded from: classes2.dex */
public class External360CamCmds {
    private static ItfcCmds Cmds;
    static External360CamCmds mExternal360CamCmds;

    public static External360CamCmds getInstance() {
        if (mExternal360CamCmds == null) {
            mExternal360CamCmds = new External360CamCmds();
        }
        return mExternal360CamCmds;
    }

    public ItfcCmds getCmds() {
        int iIs360External = FutureUtil.instance.is360External();
        ItfcCmds itfcCmds = Cmds;
        if (itfcCmds != null) {
            return itfcCmds;
        }
        if (iIs360External == 1) {
            CmdsHanSheng cmdsHanSheng = new CmdsHanSheng();
            Cmds = cmdsHanSheng;
            return cmdsHanSheng;
        }
        if (iIs360External == 2) {
            CmdsMalaysia cmdsMalaysia = new CmdsMalaysia();
            Cmds = cmdsMalaysia;
            return cmdsMalaysia;
        }
        CmdsDefualt cmdsDefualt = new CmdsDefualt();
        Cmds = cmdsDefualt;
        return cmdsDefualt;
    }
}
