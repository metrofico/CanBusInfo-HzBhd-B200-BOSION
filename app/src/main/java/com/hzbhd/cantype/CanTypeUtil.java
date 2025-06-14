package com.hzbhd.cantype;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.cantype.id.CanType0;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanTypeUtil.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/cantype/CanTypeUtil;", "", "()V", "getCanType", "Lcom/hzbhd/cantype/CanTypeBase;", "type", "", "getCanTypeAllEntityByCompanyAndCategory", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/adapter/bean/CanTypeAllEntity;", "Lkotlin/collections/ArrayList;", "company", "", "category", "canType", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CanTypeUtil {
    public static final CanTypeUtil INSTANCE = new CanTypeUtil();

    private CanTypeUtil() {
    }

    public final CanTypeBase getCanType(int type) throws IllegalAccessException, InstantiationException {
        try {
            Object objNewInstance = Class.forName("com.hzbhd.cantype.id.CanType" + type).newInstance();
            Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.hzbhd.cantype.CanTypeBase");
            return (CanTypeBase) objNewInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return new CanType0();
        }
    }

    public final ArrayList<CanTypeAllEntity> getCanTypeAllEntityByCompanyAndCategory(String company, String category, int canType) {
        Intrinsics.checkNotNullParameter(company, "company");
        Intrinsics.checkNotNullParameter(category, "category");
        ArrayList<CanTypeAllEntity> arrayList = new ArrayList<>();
        Iterator<CanTypeAllEntity> it = getCanType(canType).getList().iterator();
        while (it.hasNext()) {
            CanTypeAllEntity next = it.next();
            if (company.equals(next.getProtocol_company()) & category.equals(next.getCar_category())) {
                arrayList.add(next);
            } else if (company.equals(next.getEnglish_protocol_company()) & category.equals(next.getEnglish_car_category())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
