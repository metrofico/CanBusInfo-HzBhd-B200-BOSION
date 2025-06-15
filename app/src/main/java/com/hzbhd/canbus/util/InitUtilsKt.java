package com.hzbhd.canbus.util;

import android.content.Context;

import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public final class InitUtilsKt {
    public static Context mContext;
    private static HashMap<String, DriverDataPageUiSet.Page.Item> mDrivingItemIndex = new HashMap<>();
    private static HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndex = new HashMap<>();
    public static AbstractUiMgr mUiMgr;

    public static HashMap<String, DriverDataPageUiSet.Page.Item> getMDrivingItemIndex() {
        return mDrivingItemIndex;
    }

    public static void setMDrivingItemIndex(HashMap<String, DriverDataPageUiSet.Page.Item> map) {

        mDrivingItemIndex = map;
    }

    public static HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> getMSettingItemIndex() {
        return mSettingItemIndex;
    }

    public static void setMSettingItemIndex(HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map) {

        mSettingItemIndex = map;
    }

    public static AbstractUiMgr getMUiMgr() {
        AbstractUiMgr abstractUiMgr = mUiMgr;
        if (abstractUiMgr != null) {
            return abstractUiMgr;
        }

        return null;
    }

    public static void setMUiMgr(AbstractUiMgr abstractUiMgr) {

        mUiMgr = abstractUiMgr;
    }

    public static Context getMContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }

        return null;
    }

    public static void setMContext(Context context) {

        mContext = context;
    }

    public static String getSettingViewItemName(int i, int i2) {
        String titleSrn = getMUiMgr().getSettingUiSet(getMContext()).getList().get(i).getItemList().get(i2).getTitleSrn();

        return titleSrn;
    }

    public static void init(Context context, AbstractUiMgr uiMgr) {


        setMUiMgr(uiMgr);
        setMContext(context);
    }

    public static /* synthetic */ void initSettingItemsIndexHashMap$default(Context context, AbstractUiMgr abstractUiMgr, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = mSettingItemIndex;
        }
        initSettingItemsIndexHashMap(context, abstractUiMgr, map);
    }

    public static void initSettingItemsIndexHashMap(Context context, AbstractUiMgr uiMgr, HashMap<String, SettingPageUiSet.ListBean.ItemListBean> hashMap) {


        init(context, uiMgr);
        List<SettingPageUiSet.ListBean> list = getMUiMgr().getSettingUiSet(context).getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = it.next().getItemList().iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                int i4 = i3 + 1;
                String titleSrn = it2.next().getTitleSrn();

                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = list.get(i).getItemList().get(i3);

                hashMap.put(titleSrn, itemListBean);
                i3 = i4;
            }
            i = i2;
        }
    }

    public static /* synthetic */ void initDrivingItemsIndexHashMap$default(Context context, AbstractUiMgr abstractUiMgr, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = mDrivingItemIndex;
        }
        initDrivingItemsIndexHashMap(context, abstractUiMgr, map);
    }

    public static void initDrivingItemsIndexHashMap(Context context, AbstractUiMgr uiMgr, HashMap<String, DriverDataPageUiSet.Page.Item> hashMap) {


        init(context, uiMgr);
        List<DriverDataPageUiSet.Page> list = getMUiMgr().getDriverDataPageUiSet(context).getList();
        Iterator<DriverDataPageUiSet.Page> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Iterator<DriverDataPageUiSet.Page.Item> it2 = it.next().getItemList().iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                int i4 = i3 + 1;
                String titleSrn = it2.next().getTitleSrn();

                DriverDataPageUiSet.Page.Item item = list.get(i).getItemList().get(i3);

                hashMap.put(titleSrn, item);
                i3 = i4;
            }
            i = i2;
        }
    }
}
