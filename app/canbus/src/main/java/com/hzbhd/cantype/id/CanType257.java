package com.hzbhd.cantype.id;

import androidx.core.view.InputDeviceCompat;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType257.kt */

/* loaded from: classes2.dex */
public final class CanType257 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "吉利", "GC7", "2013-Present", "Hiworld", "Geely", "GC7", "2013-Present", InputDeviceCompat.SOURCE_KEYBOARD, 0, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
