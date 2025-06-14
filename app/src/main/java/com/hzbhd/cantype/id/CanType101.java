package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType101.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType101;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType101 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "奔驰", "A", "(W169)", "Simple", "Benz", "A", "(W169)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "B", "(W245)", "Simple", "Benz", "B", "(W245)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "唯亚诺", "默认", "Simple", "Benz", "Viano", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "凌特", "默认", "Simple", "Benz", "Sprinter", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "威霆", "2003", "Simple", "Benz", "Vito", "2003", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "A", "(W169)", "BAOGOOD", "Benz", "A Class", "(W169)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "B", "(W245)", "BAOGOOD", "Benz", "B Class", "(W245)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "唯亚诺", "默认", "BAOGOOD", "Benz", "Viano", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "凌特", "默认", "BAOGOOD", "Benz", "Sprinter", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "威霆", "2003", "BAOGOOD", "Benz", "Vito", "2003", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
