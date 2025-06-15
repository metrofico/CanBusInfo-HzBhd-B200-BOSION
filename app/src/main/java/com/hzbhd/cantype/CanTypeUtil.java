package com.hzbhd.cantype;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.id.CanType0;

import java.util.ArrayList;
import java.util.Iterator;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class CanTypeUtil {
    public static final CanTypeUtil INSTANCE = new CanTypeUtil();

    private CanTypeUtil() {
    }

    public CanTypeBase getCanType(int type) {
        try {
            Object objNewInstance = Class.forName("com.hzbhd.cantype.id.CanType" + type).newInstance();

            return (CanTypeBase) objNewInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return new CanType0();
        }
    }

    public final ArrayList<CanTypeAllEntity> getCanTypeAllEntityByCompanyAndCategory(String company, String category, int canType) {


        ArrayList<CanTypeAllEntity> arrayList = new ArrayList<>();
        for (CanTypeAllEntity next : getCanType(canType).getList()) {
            if (company.equals(next.getProtocol_company()) & category.equals(next.getCar_category())) {
                arrayList.add(next);
            } else if (company.equals(next.getEnglish_protocol_company()) & category.equals(next.getEnglish_car_category())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
