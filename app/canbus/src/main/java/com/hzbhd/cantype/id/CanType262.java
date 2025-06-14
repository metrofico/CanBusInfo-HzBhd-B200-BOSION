package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType262.kt */

/* loaded from: classes2.dex */
public final class CanType262 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "菲亚特", "菲翔", "2012", "Simple", "Fiat", "Viaggio", "2012", 262, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "菲亚特", "致悦", "2014", "Simple", "Fiat", "Ottimo", "2014", 262, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "菲亚特", "菲翔", "2012", "BAOGOOD", "Fiat", "Viaggio", "2012", 262, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "菲亚特", "致悦", "2014", "BAOGOOD", "Fiat", "Ottimo", "2014", 262, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
