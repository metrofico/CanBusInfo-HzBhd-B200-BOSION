package com.hzbhd.canbus.car._58;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import java.util.ArrayList;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private OnOriginalTopBtnClickListener mTopBtn = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._58.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            if (GeneralOriginalCarDeviceData.power) {
                CanbusMsgSender.sendMsg(new byte[]{22, -84, 2, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -84, 1, 0});
            }
        }
    };
    private OnOriginalBottomBtnClickListener mBottomBtn = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._58.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, (byte) i});
        }
    };
    private OnOriginalCarDeviceBackPressedListener mBack = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._58.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -84, 2, 0});
            GeneralOriginalCarDeviceData.power = false;
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        try {
            this.mContext = context;
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
            this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
            originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mTopBtn);
            this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._58.UiMgr.1
                @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
                public void onClickBottomBtnItem(int i) {
                    String str = UiMgr.this.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[i];
                    str.hashCode();
                    if (str.equals("up")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, 0});
                    } else if (str.equals("down")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, 1});
                    }
                }
            });
            this.mOriginalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mBack);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_55_0xa4_item", ""));
            arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "total_file", ""));
            arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", ""));
            arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_104", ""));
            arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_55_0xa4_item5", ""));
            this.mOriginalCarDevicePageUiSet.setItems(arrayList);
            this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(new String[]{"power"});
            this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"up", "down"});
            this.mOriginalCarDevicePageUiSet.setHaveSongList(false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "错误报告：" + e);
        }
    }
}
