package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.constant.disc.MpegConstantsDef;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType159.kt */

/* loaded from: classes2.dex */
public final class CanType159 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "福特", "福克斯", "2005-2011(Gen2)", "Hiworld", "Ford", "Focus", "2005-2011(Gen2)", MpegConstantsDef.MPEG_COMM_STATUS_REQ, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "福特", "蒙迪欧", "2007-2012(Gen4)", "Hiworld", "Ford", "Mondeo", "2007-2012(Gen4)", MpegConstantsDef.MPEG_COMM_STATUS_REQ, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "福特", "麦柯斯", "2006-Present(Gen1)", "Hiworld", "Ford", "S-Max", "2006-Present(Gen1)", MpegConstantsDef.MPEG_COMM_STATUS_REQ, 1, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "福特", "远征", "2007-Present(Gen3)", "Hiworld", "Ford", "Expedition", "2007-Present(Gen3)", MpegConstantsDef.MPEG_COMM_STATUS_REQ, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "福特", "F500", "2009-2014(Gen12)", "Hiworld", "Ford", "F500", "2009-2014(Gen12)", MpegConstantsDef.MPEG_COMM_STATUS_REQ, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
