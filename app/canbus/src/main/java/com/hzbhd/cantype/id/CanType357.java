package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType357.kt */

/* loaded from: classes2.dex */
public final class CanType357 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "日产", "天籁", "2013", "Xinbas", "Nissan", "Altima/Teana", "2013", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "日产", "天籁", "2014", "Xinbas", "Nissan", "Altima/Teana", "2014", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "日产", "奇骏", "--", "Xinbas", "Nissan", "X-TRAIL", "--", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "日产", "楼兰", "2015", "Xinbas", "Nissan", "Murano", "2015", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "日产", "蓝鸟", "2016", "Xinbas", "Nissan", "Bulebird", "2016", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "日产", "逍客", "--", "Xinbas", "Nissan", "QASHQAI", "--", 357, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
