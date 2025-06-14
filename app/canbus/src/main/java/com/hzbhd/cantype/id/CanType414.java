package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType414.kt */

/* loaded from: classes2.dex */
public final class CanType414 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "东风", "风神AX5", "默认", "Hiworld", "DFM", "FengshenAX5", "Defualt", 414, 0, 4, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "东风", "风神AX7(手动空调不带全景)", "默认", "Hiworld", "DFM", "FengshenAX7(Manual air conditioning without panoramic view)", "Defualt", 414, 1, 3, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "东风", "风神AX7(自动空调带全景)", "默认", "Hiworld", "DFM", "FengshenAX7(Automatic air conditioning with panoramic view)", "Defualt", 414, 1, 10, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "东风", "风神AX7(自动空调不带全景)", "默认", "Hiworld", "DFM", "FengshenAX7(Automatic air conditioning without panoramic view)", "Defualt", 414, 1, 11, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
