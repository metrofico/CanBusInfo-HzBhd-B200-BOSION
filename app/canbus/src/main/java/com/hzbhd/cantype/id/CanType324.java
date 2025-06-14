package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType324.kt */

/* loaded from: classes2.dex */
public final class CanType324 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("保仕杰 BSJ", "丰田", "默认", "默认", "BSJ", "Toyota", "Default", "Default", 324, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("保仕杰 BSJ", "丰田", "雷克萨斯", "（带原车设备）", "BSJ", "Toyota", "Lexus", "（With original vehicle equipment）", 324, 1, 1, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
