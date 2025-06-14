package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType219.kt */

/* loaded from: classes2.dex */
public final class CanType219 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("睿志诚", "标致", "MPV Tepee", " ", "Raise", "Peugeot", "MPV Tepee", "", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "307", "2004-2013", "Raise", "Peugeot", "307", "2004-2013", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "408", "2010-2013", "Raise", "Peugeot", "408", "2010-2013", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "408SW", " ", "Raise", "Peugeot", "408SW", "", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "408CC", " ", "Raise", "Peugeot", "408CC", "", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "3008", "2013-2019", "Raise", "Peugeot", "3008", "2013-2019", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "508", " ", "Raise", "Peugeot", "508", "", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "), new CanTypeAllEntity("睿志诚", "标致", "RZC", " ", "Raise", "Peugeot", "RZC", "", HotKeyConstant.K_THIRD_KUWO, 0, 0, 2, 1, 3, 1, 1, 1, " "));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
