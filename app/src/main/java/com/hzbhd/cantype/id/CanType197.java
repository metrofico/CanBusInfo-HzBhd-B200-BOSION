package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType197.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/cantype/id/CanType197;", "Lcom/hzbhd/cantype/CanTypeBase;", "()V", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanType197 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("馨飞扬", "丰田", "默认", "默认", CanTypeMsg.XFY, "Toyota", "Default", "Defualt", HotKeyConstant.K_TTM, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "本田", "思域", "(Manual AC)", CanTypeMsg.XFY, "Honda", "Civic", "(Manual AC)", HotKeyConstant.K_TTM, 16, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "本田", "思域", "(Auto AC)", CanTypeMsg.XFY, "Honda", "Civic", "(Auto AC)", HotKeyConstant.K_TTM, 17, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Manual AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Manual AC)(Left Rudder)", HotKeyConstant.K_TTM, 32, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Auto AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Auto AC)(Left Rudder)", HotKeyConstant.K_TTM, 33, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Manual AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Manual AC)(Left Rudder)", HotKeyConstant.K_TTM, 32, 16, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Auto AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Auto AC)(Left Rudder)", HotKeyConstant.K_TTM, 33, 17, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Manual AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Manual AC)(Right Rudder)", HotKeyConstant.K_TTM, 32, 128, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Auto AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Auto AC)(Right Rudder)", HotKeyConstant.K_TTM, 33, 129, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Manual AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Manual AC)(Right Rudder)", HotKeyConstant.K_TTM, 32, 144, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Auto AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Auto AC)(Right Rudder)", HotKeyConstant.K_TTM, 33, 145, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "默认", CanTypeMsg.XFY, "Hyundai", "Elantra", "Defualt", HotKeyConstant.K_TTM, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
