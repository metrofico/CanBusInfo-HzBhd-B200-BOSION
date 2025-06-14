package com.hzbhd.canbus.car_cus._451.activity.lexus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._451.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._451.data.EquipData;
import com.hzbhd.canbus.car_cus._451.observer.ObserverBuilding451;
import com.hzbhd.canbus.car_cus._451.view.AmplifierView;
import com.hzbhd.canbus.car_cus._451.view.AuxView;
import com.hzbhd.canbus.car_cus._451.view.BasicInfoView;
import com.hzbhd.canbus.car_cus._451.view.BtInfoView;
import com.hzbhd.canbus.car_cus._451.view.CdInfoView;
import com.hzbhd.canbus.car_cus._451.view.LyricTextViewView;
import com.hzbhd.canbus.car_cus._451.view.PhoneView;
import com.hzbhd.canbus.car_cus._451.view.UsbInfoView;
import com.hzbhd.canbus.car_cus._451.view.VolumeView;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class OriginalMediaActivity extends Activity implements CanInfoObserver {
    private AmplifierView amplifierView;
    private AuxView auxView;
    private BasicInfoView basicInfoView;
    private BtInfoView btInfoView;
    private CdInfoView cdInfoView;
    private LyricTextViewView lyricTextViewView;
    private PhoneView phoneView;
    private UsbInfoView usbInfoView;
    private VolumeView volumeView;
    private LinearLayout volume_lin;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_original_media);
        initView();
    }

    private void initView() {
        this.lyricTextViewView = (LyricTextViewView) findViewById(R.id.lyricTextViewView);
        this.amplifierView = (AmplifierView) findViewById(R.id.amplifierView);
        this.cdInfoView = (CdInfoView) findViewById(R.id.cdInfoView);
        this.auxView = (AuxView) findViewById(R.id.auxView);
        this.btInfoView = (BtInfoView) findViewById(R.id.btInfoView);
        this.usbInfoView = (UsbInfoView) findViewById(R.id.usbInfoView);
        this.phoneView = (PhoneView) findViewById(R.id.phoneView);
        this.basicInfoView = (BasicInfoView) findViewById(R.id.basicInfoView);
        this.volume_lin = (LinearLayout) findViewById(R.id.volume_lin);
        VolumeView volumeView = (VolumeView) findViewById(R.id.volume_view);
        this.volumeView = volumeView;
        volumeView.setMax(63);
        updateUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUi() {
        if (EquipData.mode.equals(EquipData.MODE.AMPL)) {
            this.amplifierView.setVisibility(0);
            this.cdInfoView.setVisibility(8);
            this.auxView.setVisibility(8);
            this.btInfoView.setVisibility(8);
            this.usbInfoView.setVisibility(8);
            this.phoneView.setVisibility(8);
        } else if (EquipData.mode.equals(EquipData.MODE.CD)) {
            this.amplifierView.setVisibility(8);
            this.cdInfoView.setVisibility(0);
            this.auxView.setVisibility(8);
            this.btInfoView.setVisibility(8);
            this.usbInfoView.setVisibility(8);
            this.phoneView.setVisibility(8);
        } else if (EquipData.mode.equals(EquipData.MODE.AUX)) {
            this.amplifierView.setVisibility(8);
            this.cdInfoView.setVisibility(8);
            this.auxView.setVisibility(0);
            this.btInfoView.setVisibility(8);
            this.usbInfoView.setVisibility(8);
            this.phoneView.setVisibility(8);
        } else if (EquipData.mode.equals(EquipData.MODE.BT)) {
            this.amplifierView.setVisibility(8);
            this.cdInfoView.setVisibility(8);
            this.auxView.setVisibility(8);
            this.btInfoView.setVisibility(0);
            this.usbInfoView.setVisibility(8);
            this.phoneView.setVisibility(8);
        } else if (EquipData.mode.equals(EquipData.MODE.USB)) {
            this.amplifierView.setVisibility(8);
            this.cdInfoView.setVisibility(8);
            this.auxView.setVisibility(8);
            this.btInfoView.setVisibility(8);
            this.usbInfoView.setVisibility(0);
            this.phoneView.setVisibility(8);
        } else if (EquipData.mode.equals(EquipData.MODE.PHONE)) {
            this.amplifierView.setVisibility(8);
            this.cdInfoView.setVisibility(8);
            this.auxView.setVisibility(8);
            this.btInfoView.setVisibility(8);
            this.usbInfoView.setVisibility(8);
            this.phoneView.setVisibility(0);
        }
        this.volume_lin.setVisibility(EquipData.isShowVolume ? 0 : 8);
        this.volumeView.setProgress(EquipData.volume);
        this.volumeView.setMute(EquipData.isMute);
        this.lyricTextViewView.setTxt(EquipData.txtInfo);
        this.amplifierView.setData(EquipData.bas, EquipData.mid, EquipData.tre, EquipData.fad, EquipData.bal, EquipData.asl);
        this.cdInfoView.setData(EquipData.cdNumber, EquipData.cdSongNumber, EquipData.cdTimeStr);
        this.btInfoView.setData(EquipData.btSongNumber, EquipData.btTimeStr);
        this.usbInfoView.setData(EquipData.usbFileNumber, EquipData.usbSongNumber, EquipData.usbTimeStr);
        this.basicInfoView.band_txt.setText(EquipData.band);
        this.basicInfoView.band_value_txt.setText(EquipData.frequency);
        this.basicInfoView.st_txt.setVisibility(EquipData.isShowSt ? 0 : 8);
        this.basicInfoView.preset_txt.setText(EquipData.presetStr);
        this.basicInfoView.asl_txt.setVisibility(EquipData.isShowAslIcon ? 0 : 8);
        this.basicInfoView.bt_txt.setVisibility(EquipData.isShowBtIcon ? 0 : 8);
        this.basicInfoView.phone_txt.setVisibility(EquipData.isShowPhoneIcon ? 0 : 8);
        if (EquipData.scan == 0) {
            this.basicInfoView.scan_txt.setVisibility(8);
        } else if (EquipData.scan == 1) {
            this.basicInfoView.scan_txt.setText("多碟+SCAN");
        } else if (EquipData.scan == 2) {
            this.basicInfoView.scan_txt.setText("SCAN");
        }
        if (EquipData.rpt == 0) {
            this.basicInfoView.rpt_txt.setVisibility(8);
        } else if (EquipData.rpt == 1) {
            this.basicInfoView.rpt_txt.setText("单碟+RPT");
        } else if (EquipData.rpt == 2) {
            this.basicInfoView.rpt_txt.setText("RPT");
        }
        if (EquipData.rand == 0) {
            this.basicInfoView.rand_txt.setVisibility(8);
        } else if (EquipData.rand == 1) {
            this.basicInfoView.rand_txt.setText("多碟+RAND");
        } else if (EquipData.rand == 2) {
            this.basicInfoView.rand_txt.setText("RADN");
        }
        if (EquipData.signal == 0) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_0_02);
        } else if (EquipData.signal == 1) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_1_02);
        } else if (EquipData.signal == 2) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_2_02);
        } else if (EquipData.signal == 3) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_3_02);
        } else if (EquipData.signal == 4) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_4_02);
        } else if (EquipData.signal == 5) {
            this.basicInfoView.signal_txt.setBackgroundResource(R.drawable._451_xin_5_02);
        }
        if (EquipData.disc_in == 0) {
            this.basicInfoView.disc_in.setVisibility(8);
            this.basicInfoView.disc_in.setTwinkle(false);
        } else if (EquipData.disc_in == 1) {
            this.basicInfoView.disc_in.setVisibility(0);
            this.basicInfoView.disc_in.setTwinkle(true);
        } else if (EquipData.disc_in == 2) {
            this.basicInfoView.disc_in.setVisibility(0);
            this.basicInfoView.disc_in.setTwinkle(false);
        }
        if (EquipData.disc1 == 0) {
            this.basicInfoView.disc1.setVisibility(8);
            this.basicInfoView.disc1.setTwinkle(false);
        } else if (EquipData.disc1 == 1) {
            this.basicInfoView.disc1.setVisibility(0);
            this.basicInfoView.disc1.setTwinkle(true);
        } else if (EquipData.disc1 == 2) {
            this.basicInfoView.disc1.setVisibility(0);
            this.basicInfoView.disc1.setTwinkle(false);
        }
        if (EquipData.disc2 == 0) {
            this.basicInfoView.disc2.setVisibility(8);
            this.basicInfoView.disc2.setTwinkle(false);
        } else if (EquipData.disc2 == 1) {
            this.basicInfoView.disc2.setVisibility(0);
            this.basicInfoView.disc2.setTwinkle(true);
        } else if (EquipData.disc2 == 2) {
            this.basicInfoView.disc2.setVisibility(0);
            this.basicInfoView.disc2.setTwinkle(false);
        }
        if (EquipData.disc3 == 0) {
            this.basicInfoView.disc3.setVisibility(8);
            this.basicInfoView.disc3.setTwinkle(false);
        } else if (EquipData.disc3 == 1) {
            this.basicInfoView.disc3.setVisibility(0);
            this.basicInfoView.disc3.setTwinkle(true);
        } else if (EquipData.disc3 == 2) {
            this.basicInfoView.disc3.setVisibility(0);
            this.basicInfoView.disc3.setTwinkle(false);
        }
        if (EquipData.disc4 == 0) {
            this.basicInfoView.disc4.setVisibility(8);
            this.basicInfoView.disc4.setTwinkle(false);
        } else if (EquipData.disc4 == 1) {
            this.basicInfoView.disc4.setVisibility(0);
            this.basicInfoView.disc4.setTwinkle(true);
        } else if (EquipData.disc4 == 2) {
            this.basicInfoView.disc4.setVisibility(0);
            this.basicInfoView.disc4.setTwinkle(false);
        }
        if (EquipData.disc5 == 0) {
            this.basicInfoView.disc5.setVisibility(8);
            this.basicInfoView.disc5.setTwinkle(false);
        } else if (EquipData.disc5 == 1) {
            this.basicInfoView.disc5.setVisibility(0);
            this.basicInfoView.disc5.setTwinkle(true);
        } else if (EquipData.disc5 == 2) {
            this.basicInfoView.disc5.setVisibility(0);
            this.basicInfoView.disc5.setTwinkle(false);
        }
        if (EquipData.disc6 == 0) {
            this.basicInfoView.disc6.setVisibility(8);
            this.basicInfoView.disc6.setTwinkle(false);
        } else if (EquipData.disc6 == 1) {
            this.basicInfoView.disc6.setVisibility(0);
            this.basicInfoView.disc6.setTwinkle(true);
        } else if (EquipData.disc6 == 2) {
            this.basicInfoView.disc6.setVisibility(0);
            this.basicInfoView.disc6.setTwinkle(false);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        ObserverBuilding451.getInstance().register(new OriginalMediaActivity$$ExternalSyntheticLambda0(this));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ObserverBuilding451.getInstance().unRegister(new OriginalMediaActivity$$ExternalSyntheticLambda0(this));
    }

    @Override // com.hzbhd.canbus.car_cus._451.Interface.CanInfoObserver
    public void dataChange() {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._451.activity.lexus.OriginalMediaActivity.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                OriginalMediaActivity.this.updateUi();
                return null;
            }
        });
    }
}
