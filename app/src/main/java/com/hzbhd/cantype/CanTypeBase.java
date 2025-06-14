package com.hzbhd.cantype;

import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;

import java.util.ArrayList;

import kotlin.Metadata;


public interface CanTypeBase {
    ArrayList<CanTypeAllEntity> getList();
}
