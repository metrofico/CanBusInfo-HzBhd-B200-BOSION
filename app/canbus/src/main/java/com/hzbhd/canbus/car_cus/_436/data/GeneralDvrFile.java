package com.hzbhd.canbus.car_cus._436.data;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GeneralDvrFile {
    private static GeneralDvrFile generalDvrFile = null;
    public static int pageNumber = 1;
    public List<Integer> item1 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.1
    };
    public List<Integer> item2 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.2
    };
    public List<Integer> item3 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.3
    };
    public List<Integer> item4 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.4
    };
    public List<Integer> item5 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.5
    };
    public List<Integer> item6 = new ArrayList<Integer>() { // from class: com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile.6
    };

    public static GeneralDvrFile getInstance() {
        if (generalDvrFile == null) {
            generalDvrFile = new GeneralDvrFile();
        }
        return generalDvrFile;
    }

    private GeneralDvrFile() {
    }
}
