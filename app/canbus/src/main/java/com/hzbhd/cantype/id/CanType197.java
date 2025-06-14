package com.hzbhd.cantype.id;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.cantype.CanTypeBase;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CanType197.kt */

/* loaded from: classes2.dex */
public final class CanType197 implements CanTypeBase {
    private final ArrayList<CanTypeAllEntity> list = CollectionsKt.arrayListOf(new CanTypeAllEntity("馨飞扬", "丰田", "默认", "默认", CanTypeMsg.XFY, "Toyota", "Default", "Defualt", HotKeyConstant.K_TTM, 1, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "本田", "思域", "(Manual AC)", CanTypeMsg.XFY, "Honda", "Civic", "(Manual AC)", HotKeyConstant.K_TTM, 16, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "本田", "思域", "(Auto AC)", CanTypeMsg.XFY, "Honda", "Civic", "(Auto AC)", HotKeyConstant.K_TTM, 17, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Manual AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Manual AC)(Left Rudder)", HotKeyConstant.K_TTM, 32, 0, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Auto AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Auto AC)(Left Rudder)", HotKeyConstant.K_TTM, 33, 1, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Manual AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Manual AC)(Left Rudder)", HotKeyConstant.K_TTM, 32, 16, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Auto AC)(Left Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Auto AC)(Left Rudder)", HotKeyConstant.K_TTM, 33, 17, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Manual AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Manual AC)(Right Rudder)", HotKeyConstant.K_TTM, 32, 128, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2012 (Auto AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2012 (Auto AC)(Right Rudder)", HotKeyConstant.K_TTM, 33, 129, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Manual AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Manual AC)(Right Rudder)", HotKeyConstant.K_TTM, 32, 144, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "2015 (Auto AC)(Right Rudder)", CanTypeMsg.XFY, "Hyundai", "Elantra", "2015 (Auto AC)(Right Rudder)", HotKeyConstant.K_TTM, 33, 145, 0, 1, 0, 1, 0, 0, "null"), new CanTypeAllEntity("馨飞扬", "现代", "朗动", "默认", CanTypeMsg.XFY, "Hyundai", "Elantra", "Defualt", HotKeyConstant.K_TTM, 0, 0, 0, 1, 0, 1, 0, 0, "null"));

    @Override // com.hzbhd.cantype.CanTypeBase
    public ArrayList<CanTypeAllEntity> getList() {
        return this.list;
    }
}
