package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType329.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType329;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType329 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("威驰", "江淮瑞风", "S5", "2016-Present", "Hiworld", "JAC Refine", "S5", "2016-Present", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "江淮瑞风", "S3", "2015-2016 Gen2", "Hiworld", "JAC Refine", "S3", "2015-2016 Gen2", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "江淮瑞风", "S3", "2016-Present Gen3", "Hiworld", "JAC Refine", "S3", "2016-Present Gen3", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "江淮瑞风", "M4", "2016-Present Gen1", "Hiworld", "JAC Refine", "M4", "2016-Present Gen1", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "江淮瑞风", "S2 MINI", "2017-Present", "Hiworld", "JAC Refine", "S2 MINI", "2017-Present", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"), new CanTypeAllEntity("威驰", "江淮瑞风", "S1", "2017-Present", "Hiworld", "JAC Refine", "S1", "2017-Present", 329, 0, 0, 0, 1, 5, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
