package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType361.kt */

/* loaded from: classes2.dex */
public final class CanType361 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "丰田", "锐志", "2013", "Xinbas", "Toyota", "enterprising spirit", "2013", HotKeyConstant.K_AIR_TEMP_DOWN, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "凯美瑞", "2012 - 2018", "Xinbas", "Toyota", "Camry", "2012 - 2018", HotKeyConstant.K_AIR_TEMP_DOWN, 2, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "汉兰达", "2015 - 2018", "Xinbas", "Toyota", "highLand Cruiserer", "2015 - 2018", HotKeyConstant.K_AIR_TEMP_DOWN, 3, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "荣放", "2013 - 2016", "Xinbas", "Toyota", "Rongfang", "2013 - 2016", HotKeyConstant.K_AIR_TEMP_DOWN, 4, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "雷凌", "2014 - 2017", "Xinbas", "Toyota", "Lei Ling", "2014 - 2017", HotKeyConstant.K_AIR_TEMP_DOWN, 5, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "卡罗拉", "2014 - 2017", "Xinbas", "Toyota", "Corolla", "2014 - 2017", HotKeyConstant.K_AIR_TEMP_DOWN, 6, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "丰田", "霸道", "2014 - 2018", "Xinbas", "Toyota", "cruiser", "2014 - 2018", HotKeyConstant.K_AIR_TEMP_DOWN, 7, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
