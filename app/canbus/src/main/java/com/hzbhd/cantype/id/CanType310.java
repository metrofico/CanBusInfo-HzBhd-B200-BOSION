package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: CanType310.kt */

/* loaded from: classes2.dex */
public final class CanType310 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威沃思", "丰田", "默认", "默认", "WWS", "Toyota", "Default", "Default", NfDef.BT_MODE_NONE, 0, 255, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "2", "默认", "WWS", "Toyota", "2.0", "Default", NfDef.BT_MODE_NONE, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "3", "默认", "WWS", "Toyota", "3.0", "Default", NfDef.BT_MODE_NONE, 0, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "Prado", "2010（Auto Air Conditioner）", "WWS", "Toyota", "Prado", "2010（Auto Air Conditioner）", NfDef.BT_MODE_NONE, 0, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "Prado", "2014（Auto Air Conditioner）", "WWS", "Toyota", "Prado", "2014（Auto Air Conditioner）", NfDef.BT_MODE_NONE, 0, 2, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "Prado", "2014（Manual Air Conditioner）", "WWS", "Toyota", "Prado", "2014（Manual Air Conditioner）", NfDef.BT_MODE_NONE, 0, 3, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "雷克萨斯", "IS", "高配 High configuration", "WWS", "Lexus", "IS", "High configuration High configuration", NfDef.BT_MODE_NONE, 0, 4, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "雷克萨斯", "IS", "低配 Low configuration", "WWS", "Lexus", "IS", "Low configuration Low configuration", NfDef.BT_MODE_NONE, 0, 4, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "雷克萨斯", "GS", "高配 High configuration", "WWS", "Lexus", "GS", "High configuration High configuration", NfDef.BT_MODE_NONE, 0, 5, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "雷克萨斯", "GS", "低配 Low configuration", "WWS", "Lexus", "GS", "Low configuration Low configuration", NfDef.BT_MODE_NONE, 0, 5, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "雷克萨斯", "RX270", "2011", "WWS", "Lexus", "RX270", "2011", NfDef.BT_MODE_NONE, 0, 6, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("威沃思", "丰田", "LAND", "2011", "WWS", "Toyota", "Land Cruiser CRUISE", "2011", NfDef.BT_MODE_NONE, 0, 7, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
