package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class TirePageUiSet {
    private boolean isHaveSpareTire = true;
    private boolean isInitResToString;
    private OnTirePageStatusListener onTirePageStatusListener;
    private List<TireItem> tireInfoStrList;

    public TirePageUiSet() {
    }

    public boolean isInitResToString() {
        return this.isInitResToString;
    }

    public void setInitResToString(boolean z) {
        this.isInitResToString = z;
    }

    public TirePageUiSet(TireItem[] tireItemArr) {
        this.tireInfoStrList = Arrays.asList(tireItemArr);
    }

    public void setHaveSpareTire(boolean z) {
        this.isHaveSpareTire = z;
    }

    public boolean isHaveSpareTire() {
        return this.isHaveSpareTire;
    }

    public List<TireItem> getTireInfoStrList() {
        return this.tireInfoStrList;
    }

    public OnTirePageStatusListener getOnTirePageStatusListener() {
        return this.onTirePageStatusListener;
    }

    public void setOnTirePageStatusListener(OnTirePageStatusListener onTirePageStatusListener) {
        this.onTirePageStatusListener = onTirePageStatusListener;
    }

    public class TireItem {
        private List<LineItem> list;
        private int whichTire;

        public int getWhichTire() {
            return this.whichTire;
        }

        public List<LineItem> getList() {
            return this.list;
        }

        public TireItem(int i, LineItem[] lineItemArr) {
            this.whichTire = i;
            this.list = Arrays.asList(lineItemArr);
        }
    }

    public static class LineItem {
        private String title;
        private String value;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }
}
