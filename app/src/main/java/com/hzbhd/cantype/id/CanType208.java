package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType208.kt */

/* loaded from: classes2.dex */
public final class CanType208 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "奔驰", "A", "2004-2012(Gen2)(W169)", "Hiworld", "Benz", "A Class", "2004-2012(Gen2)(W169)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "B", "2005-2011(Gen1)(W245)", "Hiworld", "Benz", "B Class", "2005-2011(Gen1)(W245)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "唯雅诺", "2010(Gen2)(W245)", "Hiworld", "Benz", "Viano", "2010(Gen2)(W245)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "威霆", "2003-2013(Gen2)", "Hiworld", "Benz", "Vito W639", "2003-2013(Gen2)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "R-Class", "2006-2014(Gen2)", "Hiworld", "Benz", "R-Class W251", "2006-2014(Gen2)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "ML300 350 450 w164", "2005-2011(Gen2)", "Hiworld", "Benz", "ML300 350 450 w164", "2005-2011(Gen2)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "GL350 400 450 x164", "2005-2013(Gen2)", "Hiworld", "Benz", "GL350 400 450 x164", "2005-2013(Gen2)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奔驰", "Sprinter NCV3", "2006-2018(Gen2)", "Hiworld", "Benz", "Sprinter NCV3", "2006-2018(Gen2)", HotKeyConstant.K_SOS, 0, 0, 0, 1, 6, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
