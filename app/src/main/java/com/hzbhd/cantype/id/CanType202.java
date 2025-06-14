package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType202.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType202;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType202 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "奥迪", "A3", "2003-2013(Gen2)", "Hiworld", "Audi", "A3", "2003-2013(Gen2)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "A4", "2004-2008(Gen3)", "Hiworld", "Audi", "A4", "2004-2008(Gen3)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "A6", "Before 2004", "Hiworld", "Audi", "A6", "Before 2004", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "奥迪", "TT", "2006-2014(Gen2)", "Hiworld", "Audi", "TT", "2006-2014(Gen2)", HotKeyConstant.K_VOICE_HANGUP, 0, 0, 0, 1, 7, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
