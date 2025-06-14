package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType226.kt */

/* loaded from: classes2.dex */
public final class CanType226 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda6(Auto AC)", "2003-2015(Gen1)", "Xinbas", "Mazda", "Mazda6(Auto AC)", "2003-2015(Gen1)", HotKeyConstant.K_ANDROIDAUTO, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("鑫巴斯", "马自达", "Mazda6(Manual AC)", "2005-2015(Gen1)", "Xinbas", "Mazda", "Mazda6(Manual AC)", "2005-2015(Gen1)", HotKeyConstant.K_ANDROIDAUTO, 1, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
