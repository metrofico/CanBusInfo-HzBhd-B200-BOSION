package com.hzbhd.canbus.car_cus._277.ui_set;

import android.text.TextUtils;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleDiagnosisPageUiSet {
    private List<ListBean> list;
    private OnConfirmDialogListener mOnSettingConfirmDialogListener;
    private OnSettingItemTouchListener mOnSettingItemTouchListener;
    private OnSettingItemClickListener onSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
    private OnSettingItemSelectListener onSettingItemSelectListener;
    private OnSettingPageStatusListener onSettingPageStatusListener;

    public void setOnSettingConfirmDialogListener(OnConfirmDialogListener onConfirmDialogListener) {
        this.mOnSettingConfirmDialogListener = onConfirmDialogListener;
    }

    public OnConfirmDialogListener getmOnSettingConfirmDialogListener() {
        return this.mOnSettingConfirmDialogListener;
    }

    public OnSettingItemTouchListener getmOnSettingItemTouchListener() {
        return this.mOnSettingItemTouchListener;
    }

    public void setmOnSettingItemTouchListener(OnSettingItemTouchListener onSettingItemTouchListener) {
        this.mOnSettingItemTouchListener = onSettingItemTouchListener;
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
        private String value;

        public String getTitleSrn() {
            return this.titleSrn;
        }

        public void setTitleSrn(String str) {
            this.titleSrn = str;
        }

        public String getValue() {
            return this.value;
        }

        public boolean setValue(String str) {
            if (TextUtils.equals(this.value, str)) {
                return false;
            }
            this.value = str;
            return true;
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
            private int max;
            private int min;
            private int progress;
            private int selectIndex;
            private int style;
            private String titleSrn;
            private String unit;
            private T value;
            private List<String> valueSrnArray;

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
        }
    }
}
