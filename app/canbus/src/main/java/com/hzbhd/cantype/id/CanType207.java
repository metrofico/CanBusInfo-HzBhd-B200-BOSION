package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType207.kt */

/* loaded from: classes2.dex */
public final class CanType207 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "宝马", "BMW3/E9x", "2004-2011(Gen5)", "Hiworld", "BMW", "BMW3/E9x", "2004-2011(Gen5)", HotKeyConstant.K_NEXT_HANGUP, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "宝马", "BMW3/F30", "2012-Present(Gen6)", "Hiworld", "BMW", "BMW3/F30", "2012-Present(Gen6)", HotKeyConstant.K_NEXT_HANGUP, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "宝马", "BMWX1/E84", "2009-2015(Gen1)", "Hiworld", "BMW", "BMWX1/E84", "2009-2015(Gen1)", HotKeyConstant.K_NEXT_HANGUP, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
