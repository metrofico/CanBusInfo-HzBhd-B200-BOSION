package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.constant.disc.MpegConstantsDef;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType155.kt */

/* loaded from: classes2.dex */
public final class CanType155 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "菲亚特", "多宝", "默认", "Simple", "Fiat", "Doblo", "Default", MpegConstantsDef.MPEG_KEYBOARD_STATUS_INT, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "菲亚特", "多宝", "默认", "BAOGOOD", "Fiat", "Doblo", "Default", MpegConstantsDef.MPEG_KEYBOARD_STATUS_INT, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
