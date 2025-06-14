package com.hzbhd.canbus.ui_datas;

import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GeneralSyncData {
    public static int mInfoLineShowAmount = 0;
    public static boolean mIsShowSoftKey = false;
    public static boolean mIsSyncTimeChange = false;
    public static int mSelectedLineIndex = -1;
    public static int mSyncScreemNumber;
    public static String mSyncTime;
    public static int mSyncTopIconCount;
    public static int[] mSyncTopIconResIdArray;
    public static List<SyncListUpdateEntity> mInfoUpdateEntityList = new ArrayList();
    public static List<SyncSoftKeyUpdateEntity> mSoftKeyUpdateEntityList = new ArrayList();
}
