package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;

import java.util.List;

/* loaded from: classes2.dex */
public class SettingPageUiSet {
    private List<ListBean> list;
    private OnConfirmDialogListener onSettingConfirmDialogListener;
    private OnSettingItemClickListener onSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
    private OnSettingItemSeekbarSetTextListener onSettingItemSeekbarSetTextListener;
    private OnSettingItemSelectListener onSettingItemSelectListener;
    private OnSettingItemSwitchListener onSettingItemSwitchListener;
    private OnSettingItemTouchListener onSettingItemTouchListener;
    private OnSettingPageStatusListener onSettingPageStatusListener;

    public void setOnSettingConfirmDialogListener(OnConfirmDialogListener onConfirmDialogListener) {
        this.onSettingConfirmDialogListener = onConfirmDialogListener;
    }

    public OnConfirmDialogListener getOnSettingConfirmDialogListener() {
        return this.onSettingConfirmDialogListener;
    }

    public OnSettingItemTouchListener getOnSettingItemTouchListener() {
        return this.onSettingItemTouchListener;
    }

    public void setOnSettingItemTouchListener(OnSettingItemTouchListener onSettingItemTouchListener) {
        this.onSettingItemTouchListener = onSettingItemTouchListener;
    }

    public OnSettingItemClickListener getOnSettingItemClickListener() {
        return this.onSettingItemClickListener;
    }

    public void setOnSettingItemClickListener(OnSettingItemClickListener onSettingItemClickListener) {
        this.onSettingItemClickListener = onSettingItemClickListener;
    }

    public OnSettingItemSeekbarSelectListener getOnSettingItemSeekbarSelectListener() {
        return this.onSettingItemSeekbarSelectListener;
    }

    public void setOnSettingItemSeekbarSelectListener(OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener) {
        this.onSettingItemSeekbarSelectListener = onSettingItemSeekbarSelectListener;
    }

    public OnSettingPageStatusListener getOnSettingPageStatusListener() {
        return this.onSettingPageStatusListener;
    }

    public void setOnSettingPageStatusListener(OnSettingPageStatusListener onSettingPageStatusListener) {
        this.onSettingPageStatusListener = onSettingPageStatusListener;
    }

    public OnSettingItemSelectListener getOnSettingItemSelectListener() {
        return this.onSettingItemSelectListener;
    }

    public void setOnSettingItemSelectListener(OnSettingItemSelectListener onSettingItemSelectListener) {
        this.onSettingItemSelectListener = onSettingItemSelectListener;
    }

    public OnSettingItemSwitchListener getOnSettingItemSwitchListener() {
        return this.onSettingItemSwitchListener;
    }

    public void setOnSettingItemSwitchListener(OnSettingItemSwitchListener onSettingItemSwitchListener) {
        this.onSettingItemSwitchListener = onSettingItemSwitchListener;
    }

    public OnSettingItemSeekbarSetTextListener getOnSettingItemSeekbarSetTextListener() {
        return this.onSettingItemSeekbarSetTextListener;
    }

    public void setOnSettingItemSeekbarSetTextListener(OnSettingItemSeekbarSetTextListener onSettingItemSeekbarSetTextListener) {
        this.onSettingItemSeekbarSetTextListener = onSettingItemSeekbarSetTextListener;
    }

    public List<ListBean> getList() {
        return this.list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private boolean isSelected;
        private List<ItemListBean> itemList;
        private String titleSrn;

        public String getTitleSrn() {
            return this.titleSrn;
        }

        public void setTitleSrn(String str) {
            this.titleSrn = str;
        }

        public boolean isIsSelected() {
            return this.isSelected;
        }

        public void setIsSelected(boolean z) {
            this.isSelected = z;
        }

        public List<ItemListBean> getItemList() {
            return this.itemList;
        }

        public void setItemList(List<ItemListBean> list) {
            this.itemList = list;
        }

        public static class ItemListBean<T> {
            private String confirmTis;
            private int id;
            private boolean isValueStr;
            private int max;
            private int min;
            private int style;
            private String titleSrn;
            private T value;
            private List<String> valueSrnArray;
            private int selectIndex = 0;
            private int progress = 0;
            private String unit = "null_value";
            private boolean enable = true;
            private boolean isProgressDraggable = true;

            public String getConfirmTis() {
                return this.confirmTis;
            }

            public void setConfirmTis(String str) {
                this.confirmTis = str;
            }

            public String getUnit() {
                return this.unit;
            }

            public void setUnit(String str) {
                this.unit = str;
            }

            public int getMin() {
                return this.min;
            }

            public void setMin(int i) {
                this.min = i;
            }

            public int getMax() {
                return this.max;
            }

            public void setMax(int i) {
                this.max = i;
            }

            public int getProgress() {
                return this.progress;
            }

            public void setProgress(int i) {
                this.progress = i;
            }

            public String getTitleSrn() {
                return this.titleSrn;
            }

            public void setTitleSrn(String str) {
                this.titleSrn = str;
            }

            public int getStyle() {
                return this.style;
            }

            public void setStyle(int i) {
                this.style = i;
            }

            public int getSelectIndex() {
                return this.selectIndex;
            }

            public void setSelectIndex(int i) {
                this.selectIndex = i;
            }

            public T getValue() {
                return this.value;
            }

            public void setValue(T t) {
                this.value = t;
            }

            public List<String> getValueSrnArray() {
                return this.valueSrnArray;
            }

            public void setValueSrnArray(List<String> list) {
                this.valueSrnArray = list;
            }

            public int getId() {
                return this.id;
            }

            public boolean isValueStr() {
                return this.isValueStr;
            }

            public void setValueStr(boolean z) {
                this.isValueStr = z;
            }

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean z) {
                this.enable = z;
            }

            public boolean isProgressDraggable() {
                return this.isProgressDraggable;
            }

            public void setProgressDraggable(boolean z) {
                this.isProgressDraggable = z;
            }
        }
    }
}
