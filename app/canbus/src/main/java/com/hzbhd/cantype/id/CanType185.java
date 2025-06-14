package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType185.kt */

/* loaded from: classes2.dex */
public final class CanType185 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "Chery", "瑞虎3", "2016-2018", "Hiworld", "Chery", "Tiggo3", "2016-2018", HotKeyConstant.K_CAN_CONFIG, 0, 1, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "瑞虎3X", "2017-Present", "Hiworld", "Chery", "Tiggo3X", "2017-Present", HotKeyConstant.K_CAN_CONFIG, 0, 2, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "艾瑞泽5", "2018-Present", "Hiworld", "Chery", "Arrizo5", "2018-Present", HotKeyConstant.K_CAN_CONFIG, 0, 3, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "瑞虎5", "2018-Present", "Hiworld", "Chery", "Tiggo5", "2018-Present", HotKeyConstant.K_CAN_CONFIG, 0, 4, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "瑞虎7/5X", "2016-Present", "Hiworld", "Chery", "Tiggo 7/5X", "2016-Present", HotKeyConstant.K_CAN_CONFIG, 0, 5, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "艾瑞泽7", "2016-Present", "Hiworld", "Chery", "Arrizo7", "2016-Present", HotKeyConstant.K_CAN_CONFIG, 0, 6, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "艾瑞泽GX/EX", "2016-Present", "Hiworld", "Chery", "Arrizo GX/EX", "2016-Present", HotKeyConstant.K_CAN_CONFIG, 0, 7, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "Chery", "瑞虎8", "2016-Present", "Hiworld", "Chery", "Ruihu 8", "2016-Present", HotKeyConstant.K_CAN_CONFIG, 0, 8, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
