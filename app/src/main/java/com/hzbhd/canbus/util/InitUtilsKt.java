package com.hzbhd.canbus.util;

import android.content.Context;

import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public final class InitUtilsKt {
    public static Context mContext;
    private static HashMap<String, DriverDataPageUiSet.Page.Item> mDrivingItemIndex = new HashMap<>();
    private static HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndex = new HashMap<>();
    public static AbstractUiMgr mUiMgr;

    public static HashMap<String, DriverDataPageUiSet.Page.Item> getMDrivingItemIndex() {
        return mDrivingItemIndex;
    }

    public static void setMDrivingItemIndex(HashMap<String, DriverDataPageUiSet.Page.Item> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        mDrivingItemIndex = map;
    }

    public static HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> getMSettingItemIndex() {
        return mSettingItemIndex;
    }

    public static void setMSettingItemIndex(HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        mSettingItemIndex = map;
    }

    public static AbstractUiMgr getMUiMgr() {
        AbstractUiMgr abstractUiMgr = mUiMgr;
        if (abstractUiMgr != null) {
            return abstractUiMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
        return null;
    }

    public static void setMUiMgr(AbstractUiMgr abstractUiMgr) {
        Intrinsics.checkNotNullParameter(abstractUiMgr, "<set-?>");
        mUiMgr = abstractUiMgr;
    }

    public static Context getMContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    public static void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        mContext = context;
    }

    public static String getSettingViewItemName(int i, int i2) {
        String titleSrn = getMUiMgr().getSettingUiSet(getMContext()).getList().get(i).getItemList().get(i2).getTitleSrn();
        Intrinsics.checkNotNullExpressionValue(titleSrn, "mUiMgr.getSettingUiSet(m….itemList[right].titleSrn");
        return titleSrn;
    }

    public static void init(Context context, AbstractUiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        setMUiMgr(uiMgr);
        setMContext(context);
    }

    public static /* synthetic */ void initSettingItemsIndexHashMap$default(Context context, AbstractUiMgr abstractUiMgr, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = mSettingItemIndex;
        }
        initSettingItemsIndexHashMap(context, abstractUiMgr, map);
    }

    public static void initSettingItemsIndexHashMap(Context context, AbstractUiMgr uiMgr, HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> hashMap) {
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        Intrinsics.checkNotNull(context);
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
                Intrinsics.checkNotNullExpressionValue(titleSrn, "itemListBean.titleSrn");
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(itemListBean, "this[left].itemList[right]");
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
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        Intrinsics.checkNotNull(context);
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
                Intrinsics.checkNotNullExpressionValue(titleSrn, "item.titleSrn");
                DriverDataPageUiSet.Page.Item item = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(item, "this[pageIndex].itemList[itemIndex]");
                hashMap.put(titleSrn, item);
                i3 = i4;
            }
            i = i2;
        }
    }
}
