package com.hzbhd.canbus.car._434.speech;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.car._434.speech.SRxData;


public class SysToSpeechReceiver {
    private Context mContext;
    private Intent mIntent = null;
    private AcCtrl acCtrl = null;
    private CarCtrl carCtrl = null;
    private SpeechCtrl speechCtrl = null;

    public SysToSpeechReceiver(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSeech(Intent intent) {
        this.mContext.sendBroadcast(intent);
    }

    public AcCtrl getAc() {
        if (this.acCtrl == null) {
            this.acCtrl = new AcCtrl();
        }
        return this.acCtrl;
    }

    public class AcCtrl {
        public AcCtrl() {
        }

        public void sendAir(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.air.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "air");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendAuto(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.auto.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "auto");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendCold(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.cold.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "cold");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendWarm(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.warm.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "warm");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendLoop(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.loop");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "loop");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendDefrost(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.defrost.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "defrost");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendWind(int i) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.wind.change");
            SysToSpeechReceiver.this.mIntent.putExtra("number", i);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "wind");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendTemp(int i) {
            try {
                SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
                SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.temp.change");
                SysToSpeechReceiver.this.mIntent.putExtra("number", i);
                SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "temp");
                SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
                sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendMode(SRxData.Ac.Mode.ModeEnum modeEnum) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "ac.mode");
            if (modeEnum.ordinal() == SRxData.Ac.Mode.ModeEnum.face.ordinal()) {
                SysToSpeechReceiver.this.mIntent.putExtra("mode", "face");
            } else if (modeEnum.ordinal() == SRxData.Ac.Mode.ModeEnum.foot.ordinal()) {
                SysToSpeechReceiver.this.mIntent.putExtra("mode", "foot");
            } else if (modeEnum.ordinal() == SRxData.Ac.Mode.ModeEnum.facefoot.ordinal()) {
                SysToSpeechReceiver.this.mIntent.putExtra("mode", "face.foot");
            } else if (modeEnum.ordinal() == SRxData.Ac.Mode.ModeEnum.footdefrost.ordinal()) {
                SysToSpeechReceiver.this.mIntent.putExtra("mode", "foot.defrost");
            } else {
                SysToSpeechReceiver.this.mIntent.putExtra("mode", "other");
            }
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "mode");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }
    }

    public CarCtrl getCar() {
        if (this.carCtrl == null) {
            this.carCtrl = new CarCtrl();
        }
        return this.carCtrl;
    }

    public class CarCtrl {
        public CarCtrl() {
        }

        public void sendHeadlight(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.dipped.headlight.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "headlight");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendHighbeam(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.high.beam.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "highbeam");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendClearancelamps(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.clearance.lamps.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "clearancelamps");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendAlarmLight(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.alarm.light.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "alarmlight");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendToplight(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.toplight.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "toplight");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendReadinglamp(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.reading.lamp.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "readinglamp");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendWindow(boolean z, boolean z2) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.window.status");
            SysToSpeechReceiver.this.mIntent.putExtra("left.front", z);
            SysToSpeechReceiver.this.mIntent.putExtra("right.front", z2);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "window");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendLock(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.lock.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "lock");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendWiper(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.wiper.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "wiper");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendWiperMode(boolean z, boolean z2, boolean z3, boolean z4) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.wiper.mode");
            SysToSpeechReceiver.this.mIntent.putExtra("intermittent", z);
            SysToSpeechReceiver.this.mIntent.putExtra("speed.low", z2);
            SysToSpeechReceiver.this.mIntent.putExtra("speed.high", z3);
            SysToSpeechReceiver.this.mIntent.putExtra("wash", z4);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "wipermode");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void sendSpeed(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", "car.speed.status");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "speed");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }
    }

    public SpeechCtrl getSpeech() {
        if (this.speechCtrl == null) {
            this.speechCtrl = new SpeechCtrl();
        }
        return this.speechCtrl;
    }

    public class SpeechCtrl {
        public SpeechCtrl() {
        }

        public void speak(String str) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("action", str);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "speech.speak");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }

        public void openWindow(boolean z) {
            SysToSpeechReceiver.this.mIntent = new Intent("com.hzbhd.sys.to.speech");
            SysToSpeechReceiver.this.mIntent.putExtra("type", z);
            SysToSpeechReceiver.this.mIntent.putExtra("rx_type", "speech.window");
            SysToSpeechReceiver sysToSpeechReceiver = SysToSpeechReceiver.this;
            sysToSpeechReceiver.sendSeech(sysToSpeechReceiver.mIntent);
        }
    }
}
