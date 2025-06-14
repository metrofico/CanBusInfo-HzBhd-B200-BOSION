package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.CanTypeBase;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType101.kt */

/* loaded from: classes2.dex */
public final class CanType101 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("欣朴 ", "奔驰", "A", "(W169)", "Simple", "Benz", "A", "(W169)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "B", "(W245)", "Simple", "Benz", "B", "(W245)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "唯亚诺", "默认", "Simple", "Benz", "Viano", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "凌特", "默认", "Simple", "Benz", "Sprinter", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("欣朴 ", "奔驰", "威霆", "2003", "Simple", "Benz", "Vito", "2003", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "A", "(W169)", "BAOGOOD", "Benz", "A Class", "(W169)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "B", "(W245)", "BAOGOOD", "Benz", "B Class", "(W245)", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "唯亚诺", "默认", "BAOGOOD", "Benz", "Viano", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "凌特", "默认", "BAOGOOD", "Benz", "Sprinter", "Defualt", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("宝谷", "奔驰", "威霆", "2003", "BAOGOOD", "Benz", "Vito", "2003", 101, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
