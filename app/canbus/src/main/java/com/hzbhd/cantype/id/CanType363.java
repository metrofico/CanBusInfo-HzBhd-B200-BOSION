package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType363.kt */

/* loaded from: classes2.dex */
public final class CanType363 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "宝骏", "730", "2014", "Hiworld", "BAOJUN", "730", "2014", HotKeyConstant.K_AIR_FRONT_DEFOG, 1, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "宝骏", "730", "2016", "Hiworld", "BAOJUN", "730", "2016", HotKeyConstant.K_AIR_FRONT_DEFOG, 2, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "宝骏", "560", "2015", "Hiworld", "BAOJUN", "560", "2015", HotKeyConstant.K_AIR_FRONT_DEFOG, 3, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "宝骏", "510", "2017", "Hiworld", "BAOJUN", "510", "2017", HotKeyConstant.K_AIR_FRONT_DEFOG, 4, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "宝骏", "730", "2017", "Hiworld", "BAOJUN", "730", "2017", HotKeyConstant.K_AIR_FRONT_DEFOG, 5, 0, 0, 1, 5, 1, 1, 1, " "), new CanTypeAllEntity("威驰", "宝骏", "530", "2018", "Hiworld", "BAOJUN", "530", "2018", HotKeyConstant.K_AIR_FRONT_DEFOG, 6, 0, 0, 1, 5, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
