package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType202.kt */

/* loaded from: classes2.dex */
public final class CanType202 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "奥迪", "A3", "2003-2013(Gen2)", "Hiworld", "Audi", "A3", "2003-2013(Gen2)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "A4", "2004-2008(Gen3)", "Hiworld", "Audi", "A4", "2004-2008(Gen3)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "A6", "Before 2004", "Hiworld", "Audi", "A6", "Before 2004", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "TT", "2006-2014(Gen2)", "Hiworld", "Audi", "TT", "2006-2014(Gen2)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
