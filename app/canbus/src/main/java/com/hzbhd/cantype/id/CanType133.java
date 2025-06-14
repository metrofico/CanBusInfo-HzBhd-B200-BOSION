package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType133.kt */

/* loaded from: classes2.dex */
public final class CanType133 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda3", "2011-2013", "Xinbas", "Mazda", "Mazda3", "2011-2013", 133, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda6", "2009-2015(Gen2)", "Xinbas", "Mazda", "Mazda6", "2009-2015(Gen2)", 133, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda8", "2011-2013", "Xinbas", "Mazda", "Mazda8", "2011-2013", 133, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda9", "默认", "Xinbas", "Mazda", "Mazda9", "Defualt", 133, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "CX-7", "2014", "Xinbas", "Mazda", "CX-7", "2014", 133, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
