package com.hzbhd.canbus.car_cus._448;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car._444.HwCarSettings;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.car_cus._448.util.Heartbeat;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DvrController {
    private DecimalFormat TwoDigitNumber;
    private Context context;

    private static class Holder {
        private static final DvrController controller = new DvrController();

        private Holder() {
        }
    }

    private DvrController() throws RemoteException {
        this.TwoDigitNumber = new DecimalFormat("###00");
        DvrSender.send(new byte[]{35, 8});
        DvrSender.send(new byte[]{88, 0, 0, 0, 0});
        DvrSender.send(new byte[]{89, 0, 0, 0, 0});
        Heartbeat.getInstance().start(1000, new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.DvrController.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(new byte[]{35, 8});
                DvrSender.send(new byte[]{88, 0, 0, 0, 0});
                DvrSender.send(new byte[]{89, 0, 0, 0, 0});
                Heartbeat.getInstance().reset(1000);
            }
        });
    }

    public static DvrController getInstance() {
        return Holder.controller;
    }

    public void setData(Context context, int[] iArr) throws RemoteException {
        this.context = context;
        Log.d("fxHouTest", "dvrInfo[4]=" + iArr[4] + "   dvrInfo[5]=" + iArr[5]);
        if (iArr[0] == 170 && iArr[1] == 77 && iArr[2] == 68) {
            int i = iArr[4];
            if (i == 32) {
                set0x20(iArr);
                return;
            }
            if (i == 33) {
                set0x21(iArr);
                return;
            }
            if (i == 85) {
                set0x55(iArr);
                return;
            }
            if (i == 95) {
                set0x5F(iArr);
                return;
            }
            if (i == 99) {
                set0x63(iArr);
                return;
            }
            if (i == 112) {
                set0x70(iArr);
                return;
            }
            if (i != 113) {
                switch (i) {
                    case 80:
                        set0x50(iArr);
                        break;
                    case 81:
                        set0x51(iArr);
                        break;
                    case 82:
                        set0x52(iArr);
                        break;
                    case 83:
                        set0x53(iArr);
                        break;
                    default:
                        switch (i) {
                            case 88:
                                set0x58(iArr);
                                break;
                            case 89:
                                set0x59(iArr);
                                break;
                            case 90:
                                set0x5A(iArr);
                                break;
                            case 91:
                                set0x5B(iArr);
                                break;
                            case 92:
                                set0x5C(iArr);
                                break;
                        }
                }
                return;
            }
            set0X71(iArr);
        }
    }

    private void set0x21(int[] iArr) {
        int i = iArr[5];
        if (i == 209) {
            DvrData.fastState = "快退 x1";
        } else if (i == 210) {
            DvrData.fastState = "快退 x2";
        } else if (i == 212) {
            DvrData.fastState = "快退 x4";
        } else if (i == 216) {
            DvrData.fastState = "快退 x8";
        } else if (i == 228) {
            DvrData.fastState = "快进 x4";
        } else if (i == 232) {
            DvrData.fastState = "快进 x8";
        } else if (i == 225) {
            DvrData.fastState = " ";
        } else if (i == 226) {
            DvrData.fastState = "快进 x2";
        } else if (i == 240) {
            DvrData.fastState = "解锁状态";
        } else if (i == 241) {
            DvrData.fastState = "锁定状态";
        }
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    private void set0x63(int[] iArr) throws RemoteException {
        if (iArr[5] == 129) {
            DvrData.systemMode = 1;
            DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
            DvrSender.send(new byte[]{64, 36});
        }
    }

    private void set0x70(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(iArr[6]));
        arrayList.add(Integer.valueOf(iArr[7]));
        arrayList.add(Integer.valueOf(iArr[8]));
        arrayList.add(Integer.valueOf(iArr[9]));
        arrayList.add(Integer.valueOf(iArr[10]));
        arrayList.add(Integer.valueOf(iArr[11]));
        arrayList.add(Integer.valueOf(iArr[12]));
        arrayList.add(Integer.valueOf(iArr[13]));
        arrayList.add(Integer.valueOf(iArr[14]));
        arrayList.add(Integer.valueOf(iArr[15]));
        arrayList.add(Integer.valueOf(iArr[16]));
        arrayList.add(Integer.valueOf(iArr[17]));
        arrayList.add(Integer.valueOf(iArr[18]));
        arrayList.add(Integer.valueOf(iArr[19]));
        arrayList.add(Integer.valueOf(iArr[20]));
        arrayList.add(Integer.valueOf(iArr[21]));
        arrayList.add(Integer.valueOf(iArr[22]));
        arrayList.add(Integer.valueOf(iArr[23]));
        arrayList.add(Integer.valueOf(iArr[24]));
        if (iArr[5] == 1) {
            DvrData.file1NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file1Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file1Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file1Type = "JPG";
            } else {
                DvrData.file1Type = "VIDEO";
            }
        }
        if (iArr[5] == 2) {
            DvrData.file2NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file2Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file2Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file2Type = "JPG";
            } else {
                DvrData.file2Type = "VIDEO";
            }
        }
        if (iArr[5] == 3) {
            DvrData.file3NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file3Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file3Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file3Type = "JPG";
            } else {
                DvrData.file3Type = "VIDEO";
            }
        }
        if (iArr[5] == 4) {
            DvrData.file4NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file4Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file4Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file4Type = "JPG";
            } else {
                DvrData.file4Type = "VIDEO";
            }
        }
        if (iArr[5] == 5) {
            DvrData.file5NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file5Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file5Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file5Type = "JPG";
            } else {
                DvrData.file5Type = "VIDEO";
            }
        }
        if (iArr[5] == 6) {
            DvrData.file6NameList = arrayList;
            if (iArr[25] == 1) {
                DvrData.file6Lock = HwCarSettings.LOCK;
            } else {
                DvrData.file6Lock = "UNLOCK";
            }
            if (iArr[26] == 1) {
                DvrData.file6Type = "JPG";
            } else {
                DvrData.file6Type = "VIDEO";
            }
        }
        if (iArr[5] == 254) {
            DvrData.filePlayingList = arrayList;
        }
        DvrObserver.getInstance().dataChange(DvrMode.PLAY_BACK_MODE);
    }

    private void set0X71(int[] iArr) {
        int i = iArr[5];
        if (i == 0) {
            DvrData.numberOfFiles = 0;
        } else if (i == 1) {
            DvrData.numberOfFiles = 1;
        } else if (i == 2) {
            DvrData.numberOfFiles = 2;
        } else if (i == 3) {
            DvrData.numberOfFiles = 3;
        } else if (i == 4) {
            DvrData.numberOfFiles = 4;
        } else if (i == 5) {
            DvrData.numberOfFiles = 5;
        } else if (i == 6) {
            DvrData.numberOfFiles = 6;
        }
        DvrObserver.getInstance().dataChange(DvrMode.PLAY_BACK_MODE);
    }

    private void set0x5F(int[] iArr) {
        DvrData.czxt = "";
        DvrData.fbl = iArr[5] - 128;
        DvrData.sjbz = iArr[6] - 128;
        int i = iArr[7];
        if (i == 129) {
            DvrData.xhly = 0;
        } else if (i == 131) {
            DvrData.xhly = 1;
        } else if (i == 133) {
            DvrData.xhly = 2;
        }
        DvrData.lxsy = iArr[8] - 128;
        int i2 = iArr[10];
        if (i2 == 128) {
            DvrData.zlgy = 0;
        } else if (i2 == 129) {
            DvrData.zlgy = 1;
        } else if (i2 == 131) {
            DvrData.zlgy = 2;
        } else if (i2 == 133) {
            DvrData.zlgy = 3;
        }
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
        DvrData.systemDate = (iArr[12] + SystemConstant.THREAD_SLEEP_TIME_2000) + "/" + this.TwoDigitNumber.format(iArr[13]) + "/" + this.TwoDigitNumber.format(iArr[14]);
        DvrData.systemTime = this.TwoDigitNumber.format(iArr[15]) + ":" + this.TwoDigitNumber.format(iArr[16]) + ":" + this.TwoDigitNumber.format(iArr[17]);
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    private void set0x5C(int[] iArr) {
        if (iArr[5] == 128) {
            DvrData.czxt = "重设系统失败";
        }
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x5B(int[] iArr) {
        int i = iArr[5];
        if (i == 128) {
            DvrData.gsh = "格式化失败";
        } else if (i == 129) {
            DvrData.gsh = "正在格式化...";
        } else if (i == 130) {
            DvrData.gsh = "格式化完成";
        }
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x58(int[] iArr) {
        if (iArr[5] == 2) {
            DvrData.systemDate = (iArr[6] + SystemConstant.THREAD_SLEEP_TIME_2000) + "/" + this.TwoDigitNumber.format(iArr[7]) + "/" + this.TwoDigitNumber.format(iArr[8]);
        }
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    private void set0x59(int[] iArr) {
        if (iArr[5] == 2) {
            DvrData.systemTime = this.TwoDigitNumber.format(iArr[6]) + ":" + this.TwoDigitNumber.format(iArr[7]) + ":" + this.TwoDigitNumber.format(iArr[8]);
        }
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    private void set0x20(int[] iArr) {
        if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 3, 2) == 0) {
            DvrData.videoStateIcon = 2;
            DvrData.videoStateStr = "已停止";
            Log.d("videoStateStr", "停止录制");
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 3, 2) == 1) {
            DvrData.videoStateIcon = 1;
            DvrData.videoStateStr = "正在录制";
            DvrData.systemMode = 2;
            Log.d("videoStateStr", "正在录制");
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 3, 2) == 2) {
            DvrData.videoStateIcon = 4;
            DvrData.videoStateStr = "回放暂停";
            Log.d("videoStateStr", "回放暂停");
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 3, 2) == 3) {
            DvrData.videoStateIcon = 3;
            DvrData.videoStateStr = "正在回放";
            DvrData.systemMode = 1;
            Log.d("videoStateStr", "正在回放");
        }
        if (iArr[6] != 255) {
            DvrData.playTime = this.TwoDigitNumber.format(iArr[7]) + ":" + this.TwoDigitNumber.format(iArr[8]) + ":" + this.TwoDigitNumber.format(iArr[9]);
        } else {
            DvrData.playTime = " ";
        }
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    private void set0x50(int[] iArr) {
        DvrData.fbl = iArr[5] - 128;
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x51(int[] iArr) {
        DvrData.sjbz = iArr[5] - 128;
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x52(int[] iArr) {
        int i = iArr[5];
        if (i == 129) {
            DvrData.xhly = 0;
        } else if (i == 131) {
            DvrData.xhly = 1;
        } else if (i == 133) {
            DvrData.xhly = 2;
        }
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x53(int[] iArr) {
        DvrData.lxsy = iArr[5] - 128;
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x55(int[] iArr) {
        int i = iArr[5];
        if (i == 128) {
            DvrData.zlgy = 0;
        } else if (i == 129) {
            DvrData.zlgy = 1;
        } else if (i == 131) {
            DvrData.zlgy = 2;
        } else if (i == 133) {
            DvrData.zlgy = 3;
        }
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }

    private void set0x5A(int[] iArr) {
        DvrData.rjbb = (iArr[5] + SystemConstant.THREAD_SLEEP_TIME_2000) + "." + iArr[6] + "." + iArr[7] + " V" + iArr[8] + "";
        DvrObserver.getInstance().dataChange(DvrMode.FUNCTION_SETTINGS_MODE);
    }
}
