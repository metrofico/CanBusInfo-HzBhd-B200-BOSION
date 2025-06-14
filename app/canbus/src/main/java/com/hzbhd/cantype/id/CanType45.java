package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType45.kt */

/* loaded from: classes2.dex */
public final class CanType45 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "本田", "CR-V", "2012(Low)", "Xinbas", "Honda", "CR-V", "2012(Low)", 45, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "本田", "CR-V", "2012(Medium)", "Xinbas", "Honda", "CR-V", "2012(Medium)", 45, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "本田", "CR-V", "2015(Low)", "Xinbas", "Honda", "CR-V", "2015(Low)", 45, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "本田", "思域", "2012(Low)", "Xinbas", "Honda", "Civic", "2012(Low)", 45, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "本田", "思域", "2012(Medium)", "Xinbas", "Honda", "Civic", "2012(Medium)", 45, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
