package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType217.kt */

/* loaded from: classes2.dex */
public final class CanType217 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "菲亚特", "领雅", "2007-Present", "Hiworld", "Fiat", "Linea", "2007-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "旁多", "2005-Present", "Hiworld", "Fiat", "Punto", "2005-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "多宝", "2009-2015(Gen2)", "Hiworld", "Fiat", "Doblo", "2009-2015(Gen2)", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "博悦", "2007-Present", "Hiworld", "Fiat", "Bravo", "2007-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "乌诺", "2010-Present", "Hiworld", "Fiat", "Uno", "2010-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "派力奥", "2011-Present(Gen2)", "Hiworld", "Fiat", "Palio", "2011-Present(Gen2)", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "500", "2007-Present", "Hiworld", "Fiat", "500", "2007-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "菲亚特", "Strada", "2013-Present", "Hiworld", "Fiat", "Strada", "2013-Present", HotKeyConstant.K_NEWCANBUS_ACTIVITY, 0, 0, 0, 1, 7, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
