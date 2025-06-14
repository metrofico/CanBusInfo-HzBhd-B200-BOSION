package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import java.util.List;

/* loaded from: classes2.dex */
public class OriginalCarDevicePageUiSet {
    private boolean isHaveSongList;
    private List<Item> items;
    private OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListeners;
    private OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener;
    private OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener;
    private OnOriginalSongItemClickListener onOriginalSongItemClickListener;
    private OnOriginalTopBtnClickListener onOriginalTopBtnClickListeners;
    private String[] rowBottomBtnAction;
    private String[] rowTopBtnAction;
    private boolean isHavePlayTimeSeekBar = true;
    private boolean isSongListShowIndex = true;
    private boolean isTopBtnCanScroll = false;

    public OnOriginalSongItemClickListener getOnOriginalSongItemClickListener() {
        return this.onOriginalSongItemClickListener;
    }

    public void setOnOriginalSongItemClickListener(OnOriginalSongItemClickListener onOriginalSongItemClickListener) {
        this.onOriginalSongItemClickListener = onOriginalSongItemClickListener;
    }

    public boolean isTopBtnCanScroll() {
        return this.isTopBtnCanScroll;
    }

    public void setTopBtnCanScroll(boolean z) {
        this.isTopBtnCanScroll = z;
    }

    public boolean isHaveSongList() {
        return this.isHaveSongList;
    }

    public void setHaveSongList(boolean z) {
        this.isHaveSongList = z;
    }

    public boolean isHavePlayTimeSeekBar() {
        return this.isHavePlayTimeSeekBar;
    }

    public void setHavePlayTimeSeekBar(boolean z) {
        this.isHavePlayTimeSeekBar = z;
    }

    public OnOriginalCarDevicePageStatusListener getOnOriginalCarDevicePageStatusListener() {
        return this.onOriginalCarDevicePageStatusListener;
    }

    public void setOnOriginalCarDevicePageStatusListener(OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener) {
        this.onOriginalCarDevicePageStatusListener = onOriginalCarDevicePageStatusListener;
    }

    public String[] getRowBottomBtnAction() {
        return this.rowBottomBtnAction;
    }

    public void setRowBottomBtnAction(String[] strArr) {
        this.rowBottomBtnAction = strArr;
    }

    public OnOriginalTopBtnClickListener getOnOriginalTopBtnClickListeners() {
        return this.onOriginalTopBtnClickListeners;
    }

    public void setOnOriginalTopBtnClickListeners(OnOriginalTopBtnClickListener onOriginalTopBtnClickListener) {
        this.onOriginalTopBtnClickListeners = onOriginalTopBtnClickListener;
    }

    public OnOriginalBottomBtnClickListener getOnOriginalBottomBtnClickListeners() {
        return this.onOriginalBottomBtnClickListeners;
    }

    public void setOnOriginalBottomBtnClickListeners(OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener) {
        this.onOriginalBottomBtnClickListeners = onOriginalBottomBtnClickListener;
    }

    public String[] getRowTopBtnAction() {
        return this.rowTopBtnAction;
    }

    public void setRowTopBtnAction(String[] strArr) {
        this.rowTopBtnAction = strArr;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }

    public static class Item {
        private String imageId;
        private String titleSrn;
        private String value;

        public Item(String str, String str2, String str3) {
            this.imageId = str;
            this.titleSrn = str2;
            this.value = str3;
        }

        public String getTitleSrn() {
            return this.titleSrn;
        }

        public void setTitleSrn(String str) {
            this.titleSrn = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public String getImageId() {
            return this.imageId;
        }

        public void setImageId(String str) {
            this.imageId = str;
        }
    }

    public OnOriginalCarDeviceBackPressedListener getOnOriginalCarDeviceBackPressedListener() {
        return this.onOriginalCarDeviceBackPressedListener;
    }

    public void setOnOriginalCarDeviceBackPressedListener(OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener) {
        this.onOriginalCarDeviceBackPressedListener = onOriginalCarDeviceBackPressedListener;
    }

    public boolean isSongListShowIndex() {
        return this.isSongListShowIndex;
    }

    public void setSongListShowIndex(boolean z) {
        this.isSongListShowIndex = z;
    }
}
