package com.hzbhd.canbus.car._45;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getOriginalCarDevicePageUiSet(context).setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._45.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    UiMgr.this.send0x90HostControlInfo(4);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.send0x90HostControlInfo(9);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.send0x90HostControlInfo(1);
                } else if (i == 3) {
                    UiMgr.this.send0x90HostControlInfo(8);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.send0x90HostControlInfo(3);
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 1});
    }

    public void send0x82MediaType(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i});
    }

    public void send0x83RadioInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void send0x84DiscInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10, (byte) i11, (byte) i12});
    }

    public void send0x86VolControl(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(i, 6, 1), DataHandleUtils.getIntFromByteWithBit(i, 5, 1), DataHandleUtils.getIntFromByteWithBit(i, 4, 1), DataHandleUtils.getIntFromByteWithBit(i, 3, 1), DataHandleUtils.getIntFromByteWithBit(i, 2, 1), DataHandleUtils.getIntFromByteWithBit(i, 1, 1), DataHandleUtils.getIntFromByteWithBit(i, 0, 1))});
    }

    public void send0x90HostControlInfo(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, b, 0});
    }

    public void send0x91UsbControlInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, (byte) i, (byte) i2});
    }

    public void send0x92TimeInfo(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -110, (byte) i, (byte) i2, (byte) i3});
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
