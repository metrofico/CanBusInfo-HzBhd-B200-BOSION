package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.constant.disc.MpegConstantsDef;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType154.kt */

/* loaded from: classes2.dex */
public final class CanType154 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "菲亚特", "菲翔", "2012-Present", "Hiworld", "Fiat", "Viaggio", "2012-Present", MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "致悦", "2014-Present", "Hiworld", "Fiat", "Ottimo", "2014-Present", MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
