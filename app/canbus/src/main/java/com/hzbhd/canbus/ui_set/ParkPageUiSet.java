package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import java.util.List;

/* loaded from: classes2.dex */
public class ParkPageUiSet {
    private int cusVideoHeight;
    private int cusVideoStartX;
    private int cusVideoStartY;
    private int cusVideoWidth;
    private boolean isCusVideoSize;
    private boolean isShowCusPanoramicView;
    private OnBackCameraStatusListener onBackCameraStatusListener;
    private OnPanoramicItemClickListener onPanoramicItemClickListener;
    private OnPanoramicItemTouchListener onPanoramicItemTouchListener;
    private List<Bean> panoramicBtnList;
    private boolean isShowTrack = false;
    private boolean isShowRadar = false;
    private boolean isShowRadarDistance = false;
    private boolean isShowRadarLocation = false;
    private boolean isShowPanoramic = false;
    private boolean isHaveLeftRightRadar = false;
    private boolean isShowCustomTrack = false;
    private boolean isShowCustomBaseLine = false;

    public boolean isShowCusPanoramicView() {
        return this.isShowCusPanoramicView;
    }

    public void setShowCusPanoramicView(boolean z) {
        this.isShowCusPanoramicView = z;
    }

    public OnBackCameraStatusListener getOnBackCameraStatusListener() {
        return this.onBackCameraStatusListener;
    }

    public void setOnBackCameraStatusListener(OnBackCameraStatusListener onBackCameraStatusListener) {
        this.onBackCameraStatusListener = onBackCameraStatusListener;
    }

    public boolean isShowCustomTrack() {
        return this.isShowCustomTrack;
    }

    public void setShowCustomTrack(boolean z) {
        this.isShowCustomTrack = z;
    }

    public boolean isShowCustomBaseLine() {
        return this.isShowCustomBaseLine;
    }

    public void setShowCustomBaseLine(boolean z) {
        this.isShowCustomBaseLine = z;
    }

    public void setShowTrack(boolean z) {
        this.isShowTrack = z;
    }

    public void setShowRadarDistance(boolean z) {
        this.isShowRadarDistance = z;
    }

    public void setShowRadarLocation(boolean z) {
        this.isShowRadarLocation = z;
    }

    public void setShowPanoramic(boolean z) {
        this.isShowPanoramic = z;
    }

    public boolean isShowRadar() {
        return this.isShowRadar;
    }

    public void setShowRadar(boolean z) {
        this.isShowRadar = z;
    }

    public boolean isIsShowTrack() {
        return this.isShowTrack;
    }

    public boolean isIsShowRadar() {
        return this.isShowRadar;
    }

    public boolean isIsShowRadarDistance() {
        return this.isShowRadarDistance;
    }

    public boolean isIsShowRadarLocation() {
        return this.isShowRadarLocation;
    }

    public boolean isShowPanoramic() {
        return this.isShowPanoramic;
    }

    public boolean isHaveLeftRightRadar() {
        return this.isHaveLeftRightRadar;
    }

    public List<Bean> getPanoramicBtnList() {
        return this.panoramicBtnList;
    }

    public void setPanoramicBtnList(List<Bean> list) {
        this.panoramicBtnList = list;
    }

    public OnPanoramicItemTouchListener getOnPanoramicItemTouchListener() {
        return this.onPanoramicItemTouchListener;
    }

    public void setOnPanoramicItemTouchListener(OnPanoramicItemTouchListener onPanoramicItemTouchListener) {
        this.onPanoramicItemTouchListener = onPanoramicItemTouchListener;
    }

    public OnPanoramicItemClickListener getOnPanoramicItemClickListener() {
        return this.onPanoramicItemClickListener;
    }

    public void setOnPanoramicItemClickListener(OnPanoramicItemClickListener onPanoramicItemClickListener) {
        this.onPanoramicItemClickListener = onPanoramicItemClickListener;
    }

    public static class Bean {
        private String imgRes;
        private boolean isSelect;
        private int style;
        private String titleSrn;

        public Bean(int i, String str, String str2) {
            this.style = i;
            this.titleSrn = str;
            this.imgRes = str2;
        }

        public int getStyle() {
            return this.style;
        }

        public String getTitleSrn() {
            return this.titleSrn;
        }

        public String getImgRes() {
            return this.imgRes;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }

        public void setTitleSrn(String str) {
            this.titleSrn = str;
        }
    }

    public boolean isCusVideoSize() {
        return this.isCusVideoSize;
    }

    public int getCusVideoStartX() {
        return this.cusVideoStartX;
    }

    public void setCusVideoStartX(int i) {
        this.cusVideoStartX = i;
    }

    public int getCusVideoStartY() {
        return this.cusVideoStartY;
    }

    public void setCusVideoStartY(int i) {
        this.cusVideoStartY = i;
    }

    public int getCusVideoWidth() {
        return this.cusVideoWidth;
    }

    public void setCusVideoWidth(int i) {
        this.cusVideoWidth = i;
    }

    public int getCusVideoHeight() {
        return this.cusVideoHeight;
    }

    public void setCusVideoHeight(int i) {
        this.cusVideoHeight = i;
    }
}
