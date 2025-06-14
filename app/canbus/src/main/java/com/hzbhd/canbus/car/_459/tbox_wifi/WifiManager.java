package com.hzbhd.canbus.car._459.tbox_wifi;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class WifiManager {
    private String macAddress;
    public String wifiName;
    public String wifiPassword;

    public int getAsciiValue(char c) {
        return c;
    }

    private static class Holder {
        private static final WifiManager INSTANCE = new WifiManager();

        private Holder() {
        }
    }

    private WifiManager() {
        this.wifiName = "ZERON TBOX";
        this.wifiPassword = "12345678";
    }

    public static WifiManager getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        this.macAddress = ((android.net.wifi.WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        Log.d("DEVICE_CONFIG", "产品唯一标识符=" + this.macAddress);
    }

    public void register() {
        BaseUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._459.tbox_wifi.WifiManager.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() throws InterruptedException {
                for (int i = 0; i < 20; i++) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 1, 1, 1, 16, 16, 16, 0, 0, 1});
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    public void requestAction(int i) {
        String str = this.macAddress;
        if (str == null) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 2, (byte) Integer.parseInt(str.substring(0, 2), 16), (byte) Integer.parseInt(this.macAddress.substring(3, 5), 16), (byte) Integer.parseInt(this.macAddress.substring(6, 8), 16), (byte) Integer.parseInt(this.macAddress.substring(9, 11), 16), (byte) Integer.parseInt(this.macAddress.substring(12, 14), 16), (byte) Integer.parseInt(this.macAddress.substring(15, 17), 16), (byte) i, 1});
    }

    public void modifyWiFiName() {
        int i;
        int asciiValue;
        int i2;
        int asciiValue2;
        int i3;
        int asciiValue3;
        int i4;
        int asciiValue4;
        int i5;
        int asciiValue5;
        int i6;
        int asciiValue6;
        int i7;
        int asciiValue7;
        int i8;
        int asciiValue8;
        int i9;
        int asciiValue9;
        int i10;
        int asciiValue10;
        int i11;
        int asciiValue11;
        int i12;
        int asciiValue12;
        int i13;
        int asciiValue13;
        int i14;
        int asciiValue14;
        int i15;
        int asciiValue15;
        int i16;
        int asciiValue16;
        int length = this.wifiName.length();
        int asciiValue17 = length > 0 ? getAsciiValue(this.wifiName.charAt(0)) : 255;
        int asciiValue18 = length > 1 ? getAsciiValue(this.wifiName.charAt(1)) : 255;
        int asciiValue19 = length > 2 ? getAsciiValue(this.wifiName.charAt(2)) : 255;
        int asciiValue20 = length > 3 ? getAsciiValue(this.wifiName.charAt(3)) : 255;
        int asciiValue21 = length > 4 ? getAsciiValue(this.wifiName.charAt(4)) : 255;
        int asciiValue22 = length > 5 ? getAsciiValue(this.wifiName.charAt(5)) : 255;
        int asciiValue23 = length > 6 ? getAsciiValue(this.wifiName.charAt(6)) : 255;
        int asciiValue24 = length > 7 ? getAsciiValue(this.wifiName.charAt(7)) : 255;
        int asciiValue25 = length > 8 ? getAsciiValue(this.wifiName.charAt(8)) : 255;
        int asciiValue26 = length > 9 ? getAsciiValue(this.wifiName.charAt(9)) : 255;
        int asciiValue27 = length > 10 ? getAsciiValue(this.wifiName.charAt(10)) : 255;
        int asciiValue28 = length > 12 ? getAsciiValue(this.wifiName.charAt(11)) : 255;
        int asciiValue29 = length > 12 ? getAsciiValue(this.wifiName.charAt(12)) : 255;
        int asciiValue30 = length > 13 ? getAsciiValue(this.wifiName.charAt(13)) : 255;
        int asciiValue31 = length > 14 ? getAsciiValue(this.wifiName.charAt(14)) : 255;
        if (length > 15) {
            i = asciiValue31;
            asciiValue = getAsciiValue(this.wifiName.charAt(15));
        } else {
            i = asciiValue31;
            asciiValue = 255;
        }
        if (length > 16) {
            i2 = asciiValue;
            asciiValue2 = getAsciiValue(this.wifiName.charAt(16));
        } else {
            i2 = asciiValue;
            asciiValue2 = 255;
        }
        if (length > 17) {
            i3 = asciiValue2;
            asciiValue3 = getAsciiValue(this.wifiName.charAt(17));
        } else {
            i3 = asciiValue2;
            asciiValue3 = 255;
        }
        if (length > 18) {
            i4 = asciiValue3;
            asciiValue4 = getAsciiValue(this.wifiName.charAt(18));
        } else {
            i4 = asciiValue3;
            asciiValue4 = 255;
        }
        if (length > 19) {
            i5 = asciiValue4;
            asciiValue5 = getAsciiValue(this.wifiName.charAt(19));
        } else {
            i5 = asciiValue4;
            asciiValue5 = 255;
        }
        if (length > 20) {
            i6 = asciiValue5;
            asciiValue6 = getAsciiValue(this.wifiName.charAt(20));
        } else {
            i6 = asciiValue5;
            asciiValue6 = 255;
        }
        if (length > 21) {
            i7 = asciiValue6;
            asciiValue7 = getAsciiValue(this.wifiName.charAt(21));
        } else {
            i7 = asciiValue6;
            asciiValue7 = 255;
        }
        int i17 = asciiValue7;
        int asciiValue32 = length > 22 ? getAsciiValue(this.wifiName.charAt(22)) : 255;
        if (length > 23) {
            i8 = asciiValue32;
            asciiValue8 = getAsciiValue(this.wifiName.charAt(23));
        } else {
            i8 = asciiValue32;
            asciiValue8 = 255;
        }
        int i18 = asciiValue8;
        int asciiValue33 = length > 24 ? getAsciiValue(this.wifiName.charAt(24)) : 255;
        if (length > 25) {
            i9 = asciiValue33;
            asciiValue9 = getAsciiValue(this.wifiName.charAt(25));
        } else {
            i9 = asciiValue33;
            asciiValue9 = 255;
        }
        if (length > 26) {
            i10 = asciiValue9;
            asciiValue10 = getAsciiValue(this.wifiName.charAt(26));
        } else {
            i10 = asciiValue9;
            asciiValue10 = 255;
        }
        if (length > 27) {
            i11 = asciiValue10;
            asciiValue11 = getAsciiValue(this.wifiName.charAt(27));
        } else {
            i11 = asciiValue10;
            asciiValue11 = 255;
        }
        if (length > 28) {
            i12 = asciiValue11;
            asciiValue12 = getAsciiValue(this.wifiName.charAt(28));
        } else {
            i12 = asciiValue11;
            asciiValue12 = 255;
        }
        if (length > 29) {
            i13 = asciiValue12;
            asciiValue13 = getAsciiValue(this.wifiName.charAt(29));
        } else {
            i13 = asciiValue12;
            asciiValue13 = 255;
        }
        if (length > 30) {
            i14 = asciiValue13;
            asciiValue14 = getAsciiValue(this.wifiName.charAt(30));
        } else {
            i14 = asciiValue13;
            asciiValue14 = 255;
        }
        if (length > 31) {
            i15 = asciiValue14;
            asciiValue15 = getAsciiValue(this.wifiName.charAt(31));
        } else {
            i15 = asciiValue14;
            asciiValue15 = 255;
        }
        int i19 = asciiValue15;
        int asciiValue34 = length > 32 ? getAsciiValue(this.wifiName.charAt(32)) : 255;
        if (length > 33) {
            i16 = asciiValue34;
            asciiValue16 = getAsciiValue(this.wifiName.charAt(33));
        } else {
            i16 = asciiValue34;
            asciiValue16 = 255;
        }
        int asciiValue35 = length > 34 ? getAsciiValue(this.wifiName.charAt(34)) : 255;
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 32, (byte) asciiValue17, (byte) asciiValue18, (byte) asciiValue19, (byte) asciiValue20, (byte) asciiValue21, (byte) asciiValue22, (byte) asciiValue23, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 33, (byte) asciiValue24, (byte) asciiValue25, (byte) asciiValue26, (byte) asciiValue27, (byte) asciiValue28, (byte) asciiValue29, (byte) asciiValue30, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 34, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 35, (byte) i17, (byte) i8, (byte) i18, (byte) i9, (byte) i10, (byte) i11, (byte) i12, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 36, (byte) i13, (byte) i14, (byte) i15, (byte) i19, (byte) i16, (byte) asciiValue16, (byte) asciiValue35, 1});
    }

    public void modifyWiFiPassword() {
        int i;
        int asciiValue;
        int i2;
        int asciiValue2;
        int i3;
        int asciiValue3;
        int i4;
        int asciiValue4;
        int length = this.wifiPassword.length();
        int asciiValue5 = length > 0 ? getAsciiValue(this.wifiPassword.charAt(0)) : 255;
        int asciiValue6 = length > 1 ? getAsciiValue(this.wifiPassword.charAt(1)) : 255;
        int asciiValue7 = length > 2 ? getAsciiValue(this.wifiPassword.charAt(2)) : 255;
        int asciiValue8 = length > 3 ? getAsciiValue(this.wifiPassword.charAt(3)) : 255;
        int asciiValue9 = length > 4 ? getAsciiValue(this.wifiPassword.charAt(4)) : 255;
        int asciiValue10 = length > 5 ? getAsciiValue(this.wifiPassword.charAt(5)) : 255;
        int asciiValue11 = length > 6 ? getAsciiValue(this.wifiPassword.charAt(6)) : 255;
        int asciiValue12 = length > 7 ? getAsciiValue(this.wifiPassword.charAt(7)) : 255;
        int asciiValue13 = length > 8 ? getAsciiValue(this.wifiPassword.charAt(8)) : 255;
        int asciiValue14 = length > 9 ? getAsciiValue(this.wifiPassword.charAt(9)) : 255;
        int asciiValue15 = length > 10 ? getAsciiValue(this.wifiPassword.charAt(10)) : 255;
        int asciiValue16 = length > 12 ? getAsciiValue(this.wifiPassword.charAt(11)) : 255;
        int asciiValue17 = length > 12 ? getAsciiValue(this.wifiPassword.charAt(12)) : 255;
        int asciiValue18 = length > 13 ? getAsciiValue(this.wifiPassword.charAt(13)) : 255;
        int asciiValue19 = length > 14 ? getAsciiValue(this.wifiPassword.charAt(14)) : 255;
        int asciiValue20 = length > 15 ? getAsciiValue(this.wifiPassword.charAt(15)) : 255;
        if (length > 16) {
            i = asciiValue20;
            asciiValue = getAsciiValue(this.wifiPassword.charAt(16));
        } else {
            i = asciiValue20;
            asciiValue = 255;
        }
        if (length > 17) {
            i2 = asciiValue;
            asciiValue2 = getAsciiValue(this.wifiPassword.charAt(17));
        } else {
            i2 = asciiValue;
            asciiValue2 = 255;
        }
        if (length > 18) {
            i3 = asciiValue2;
            asciiValue3 = getAsciiValue(this.wifiPassword.charAt(18));
        } else {
            i3 = asciiValue2;
            asciiValue3 = 255;
        }
        if (length > 19) {
            i4 = asciiValue3;
            asciiValue4 = getAsciiValue(this.wifiPassword.charAt(19));
        } else {
            i4 = asciiValue3;
            asciiValue4 = 255;
        }
        int asciiValue21 = length > 20 ? getAsciiValue(this.wifiPassword.charAt(20)) : 255;
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 48, (byte) asciiValue5, (byte) asciiValue6, (byte) asciiValue7, (byte) asciiValue8, (byte) asciiValue9, (byte) asciiValue10, (byte) asciiValue11, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 49, (byte) asciiValue12, (byte) asciiValue13, (byte) asciiValue14, (byte) asciiValue15, (byte) asciiValue16, (byte) asciiValue17, (byte) asciiValue18, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 50, (byte) asciiValue19, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) asciiValue4, (byte) asciiValue21, 1});
    }
}
