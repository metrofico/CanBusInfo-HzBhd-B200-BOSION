package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class DriverDataPageUiSet {
    private String buttonText;
    private boolean isHaveBtn = false;
    private int leftPosition;
    private List<Page> list;
    private OnDriveDataPageStatusListener onDriveDataPageStatusListener;
    private int rightPosition;

    public String getButtonText() {
        return this.buttonText;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public int getLeftPosition() {
        return this.leftPosition;
    }

    public void setLeftPosition(int i) {
        this.leftPosition = i;
    }

    public int getRightPosition() {
        return this.rightPosition;
    }

    public void setRightPosition(int i) {
        this.rightPosition = i;
    }

    public boolean isHaveBtn() {
        return this.isHaveBtn;
    }

    public void setHaveBtn(boolean z) {
        this.isHaveBtn = z;
    }

    public OnDriveDataPageStatusListener getOnDriveDataPageStatusListener() {
        return this.onDriveDataPageStatusListener;
    }

    public void setOnDriveDataPageStatusListener(OnDriveDataPageStatusListener onDriveDataPageStatusListener) {
        this.onDriveDataPageStatusListener = onDriveDataPageStatusListener;
    }

    public List<Page> getList() {
        return this.list;
    }

    public void setList(List<Page> list) {
        this.list = list;
    }

    public static class Page {
        private String headTitleSrn;
        private List<Item> itemList;
        private int spanCount;

        public Page(String str, int i, Item[] itemArr) {
            this.headTitleSrn = str;
            this.itemList = Arrays.asList(itemArr);
            this.spanCount = i;
        }

        public String getHeadTitleSrn() {
            return this.headTitleSrn;
        }

        public void setHeadTitleSrn(String str) {
            this.headTitleSrn = str;
        }

        public List<Item> getItemList() {
            return this.itemList;
        }

        public void setItemList(List<Item> list) {
            this.itemList = list;
        }

        public int getSpanCount() {
            return this.spanCount;
        }

        public void setSpanCount(int i) {
            this.spanCount = i;
        }

        public static class Item {
            private String defaultValueSrn = "null_value";
            private boolean isTitleStr = false;
            private String titleSrn;
            private String value;

            public String getValue() {
                return this.value;
            }

            public void setValue(String str) {
                this.value = str;
            }

            public String getTitleSrn() {
                return this.titleSrn;
            }

            public void setTitleSrn(String str) {
                this.titleSrn = str;
            }

            public String getDefaultValueSrn() {
                return this.defaultValueSrn;
            }

            public void setDefaultValueSrn(String str) {
                this.defaultValueSrn = str;
            }

            public boolean isTitleStr() {
                return this.isTitleStr;
            }

            public void setTitleStr(boolean z) {
                this.isTitleStr = z;
            }
        }
    }
}
