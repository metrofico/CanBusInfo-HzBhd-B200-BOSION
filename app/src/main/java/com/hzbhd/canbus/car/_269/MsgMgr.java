package com.hzbhd.canbus.car._269;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.location.ILocAidlInterface;
import com.hzbhd.canbus.adapter.location.LocationChange;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.config.constant.PackageName;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;


public class MsgMgr extends AbstractMsgMgr {
    protected static final byte AMP_BAL_OFFSET = 10;
    protected static final byte AMP_DEFAULT_BAL_BAND = 9;
    protected static final byte AMP_DEFAULT_EQ = 0;
    protected static final byte AMP_DEFAULT_VOL = 8;
    protected static final byte AMP_EQ_OFFSET = 1;
    protected static final byte AMP_VOL_OFFSET = 22;
    private static final String AUTONAVI_CUR_POINT_NUM = "CUR_POINT_NUM";
    private static final String AUTONAVI_EXTRA_STATE = "EXTRA_STATE";
    private static final int AUTONAVI_EXTRA_STATE_END_NAVI = 9;
    private static final int AUTONAVI_EXTRA_STATE_START_NAVI = 8;
    private static final String AUTONAVI_ICON = "ICON";
    private static final int AUTONAVI_KEY_EXTRA_ROAD_INFO = 10056;
    private static final int AUTONAVI_KEY_ID_GUIDE = 10001;
    private static final int AUTONAVI_KEY_MAP_RUNNING_ST = 10019;
    private static final String AUTONAVI_KEY_TYPE = "KEY_TYPE";
    private static final String AUTONAVI_NEXT_ROAD_NAME = "NEXT_ROAD_NAME";
    private static final String AUTONAVI_ROUTE_ALL_DIS = "ROUTE_ALL_DIS";
    private static final String AUTONAVI_ROUTE_REMAIN_DIS = "ROUTE_REMAIN_DIS";
    private static final String AUTONAVI_SEG_REMAIN_DIS = "SEG_REMAIN_DIS";
    private static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    protected static final int CAN_269_AMP_DEFAULT_EQ_VALUE = 9;
    protected static final String CAN_269_SAVE_AMP_BASS = "__269_SAVE_AMP_BASS";
    protected static final String CAN_269_SAVE_AMP_FR = "__269_SAVE_AMP_FR";
    protected static final String CAN_269_SAVE_AMP_LR = "__269_SAVE_AMP_LR";
    protected static final String CAN_269_SAVE_AMP_MID = "__269_SAVE_AMP_MID";
    protected static final String CAN_269_SAVE_AMP_TRE = "__269_SAVE_AMP_TRE";
    protected static final String CAN_269_SAVE_AMP_VOL = "__269_SAVE_AMP_VOL";
    protected static final String CAN_269_SAVE_IS_AMP_FIRST = "CAN_269_SAVE_IS_AMP_FIRST";
    private static boolean mIsBackOpened = false;
    private static boolean mIsBackOpenedRec = false;
    private static boolean mIsFLOpened = false;
    private static boolean mIsFLOpenedRec = false;
    private static boolean mIsFROpened = false;
    private static boolean mIsFROpenedRec = false;
    private static boolean mIsFourDoorVersion = true;
    private static boolean mIsFrontOpened = false;
    private static boolean mIsFrontOpenedRec = false;
    private static boolean mIsRLOpened = false;
    private static boolean mIsRLOpenedRec = false;
    private static boolean mIsRROpened = false;
    private static boolean mIsRROpenedRec = false;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private ILocAidlInterface mILocAidlInterface;
    private LocationManager mLocationManager;
    private Timer mTimer;
    private int mNaviAllDist = 0;
    private int mNaviRemainDist = 0;
    private int mNaviNextRoadDist = 0;
    private int mNaviTurningIconId = 0;
    private int mNaviPointNum = 0;
    private int mNaviRunningSt = 0;
    private float mNaviNextRoadDistKm = 0.0f;
    private String mNaviNextRoadDistStr = "";
    private String mNaviNextRoadName = "";
    private String mNaviDestName = "";
    private String mNaviStartName = "";
    private String mSongRec = "";
    private String mArtistRec = "";
    private String mAlbumRec = "";
    private boolean mIsPayingRec = false;
    private final String ACTION_CAN_DOOR_CHANGED = "ACTION_CAN_DOOR_CHANGED";
    private final String KEY_FOUR_DOOR_VERSION = "KEY_FOUR_DOOR_VERSION";
    private final String KEY_DOOR_FL = "KEY_DOOR_FL";
    private final String KEY_DOOR_FR = "KEY_DOOR_FR";
    private final String KEY_DOOR_RL = "KEY_DOOR_RL";
    private final String KEY_DOOR_RR = "KEY_DOOR_RR";
    private final String KEY_DOOR_BACK = "KEY_DOOR_BACK";
    LocChange mLocChange = null;
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._269.MsgMgr.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("AUTONAVI_STANDARD_BROADCAST_SEND")) {
                int intExtra = intent.getIntExtra(MsgMgr.AUTONAVI_KEY_TYPE, 0);
                if (intExtra != MsgMgr.AUTONAVI_KEY_ID_GUIDE) {
                    if (intExtra == MsgMgr.AUTONAVI_KEY_MAP_RUNNING_ST) {
                        Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_MAP_RUNNING_ST>");
                        MsgMgr.this.mNaviRunningSt = intent.getIntExtra(MsgMgr.AUTONAVI_EXTRA_STATE, 0);
                        try {
                            Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_MAP_RUNNING_ST> mNaviRunningSt=" + MsgMgr.this.mNaviRunningSt);
                        } catch (Exception unused) {
                        }
                        if (MsgMgr.this.mNaviRunningSt == 8) {
                            CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -96, 6, 10, 7});
                            return;
                        } else {
                            if (MsgMgr.this.mNaviRunningSt == 9) {
                                CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -96, 6, 10, 8});
                                return;
                            }
                            return;
                        }
                    }
                    if (intExtra != MsgMgr.AUTONAVI_KEY_EXTRA_ROAD_INFO) {
                        return;
                    }
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO>");
                    try {
                        JSONObject jSONObject = new JSONObject(intent.getStringExtra("EXTRA_ROAD_INFO"));
                        MsgMgr.this.mNaviDestName = (String) jSONObject.get("ToPoiName");
                        MsgMgr.this.mNaviStartName = (String) jSONObject.get("FromPoiName");
                        try {
                            Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO> mNaviDestName=" + MsgMgr.this.mNaviDestName);
                            Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_EXTRA_ROAD_INFO> mNaviStartName=" + MsgMgr.this.mNaviStartName);
                        } catch (Exception unused2) {
                        }
                        MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviStartName + "", (byte) 4);
                        MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviDestName + "", (byte) 5);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE>");
                MsgMgr.this.mNaviAllDist = intent.getIntExtra(MsgMgr.AUTONAVI_ROUTE_ALL_DIS, 0);
                MsgMgr.this.mNaviRemainDist = intent.getIntExtra(MsgMgr.AUTONAVI_ROUTE_REMAIN_DIS, 0);
                MsgMgr.this.mNaviTurningIconId = intent.getIntExtra(MsgMgr.AUTONAVI_ICON, 0);
                MsgMgr.this.mNaviPointNum = intent.getIntExtra(MsgMgr.AUTONAVI_CUR_POINT_NUM, 0);
                MsgMgr.this.mNaviNextRoadDist = intent.getIntExtra(MsgMgr.AUTONAVI_SEG_REMAIN_DIS, 0);
                MsgMgr.this.mNaviNextRoadName = intent.getStringExtra(MsgMgr.AUTONAVI_NEXT_ROAD_NAME);
                try {
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviAllDist=" + MsgMgr.this.mNaviAllDist);
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviRemainDist=" + MsgMgr.this.mNaviRemainDist);
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviTurningIconId=" + MsgMgr.this.mNaviTurningIconId);
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviPointNum=" + MsgMgr.this.mNaviPointNum);
                    Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviNextRoadName=" + MsgMgr.this.mNaviNextRoadName);
                } catch (Exception unused3) {
                }
                CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -96, 6, 1, (byte) MsgMgr.this.mNaviTurningIconId, (byte) MsgMgr.this.mNaviPointNum});
                MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviAllDist + "", (byte) 2);
                MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviRemainDist + "", (byte) 3);
                MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviNextRoadName + "", (byte) 6);
                MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviNextRoadDist + "", (byte) 7);
                if (MsgMgr.this.mNaviNextRoadDist / 1000 >= 10) {
                    MsgMgr.this.mNaviNextRoadDistStr = (MsgMgr.this.mNaviNextRoadDist / 1000) + "";
                } else if (MsgMgr.this.mNaviNextRoadDist < 1000 || MsgMgr.this.mNaviNextRoadDist >= 10000) {
                    MsgMgr.this.mNaviNextRoadDistStr = MsgMgr.this.mNaviNextRoadDist + "";
                    MsgMgr.this.sendNaviCmds(MsgMgr.this.mNaviNextRoadDist + "", (byte) 7);
                } else {
                    MsgMgr.this.mNaviNextRoadDistKm = r10.mNaviNextRoadDist / 1000.0f;
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.mNaviNextRoadDistKm = DataHandleUtils.getRound(msgMgr.mNaviNextRoadDistKm, 2);
                    MsgMgr.this.mNaviNextRoadDistStr = (MsgMgr.this.mNaviNextRoadDistKm + "").substring(0, 3);
                }
                Log.i("[PENGLING]", "SND: <AUTONAVI_KEY_ID_GUIDE> mNaviNextRoadDistStr=" + MsgMgr.this.mNaviNextRoadDistStr);
                MsgMgr msgMgr2 = MsgMgr.this;
                msgMgr2.sendNaviCmds(msgMgr2.mNaviNextRoadDistStr, (byte) 7);
                MsgMgr msgMgr3 = MsgMgr.this;
                msgMgr3.sendNaviCmds(msgMgr3.mNaviNextRoadDist >= 1000 ? "KM" : "M", (byte) 8);
            }
        }
    };
    LocationListener locationListener = new LocationListener() { // from class: com.hzbhd.canbus.car._269.MsgMgr.4
        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            Location lastKnownLocation = MsgMgr.this.mLocationManager.getLastKnownLocation(str);
            if (lastKnownLocation != null) {
                MsgMgr.this.sendDrirectAndAltitude(lastKnownLocation);
            }
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                MsgMgr.this.sendDrirectAndAltitude(location);
            }
        }
    };

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        mIsFourDoorVersion = getCurrentCanDifferentId() == 0;
        Log.i("[PENGLING]", "{initCommand}   _269");
        initAmpPrams(this.mContext);
        initPenglingCmds();
        updateAmplifierActivity(null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
        context.registerReceiver(this.receiver, intentFilter);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PackageName.bhd_midware, "com.hzbhd.midware.location.LocationService"));
        context.bindService(intent, new ServiceConnection() { // from class: com.hzbhd.canbus.car._269.MsgMgr.1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MsgMgr.this.mILocAidlInterface = ILocAidlInterface.Stub.asInterface(iBinder);
                try {
                    MsgMgr.this.mILocAidlInterface.addCallBack(MsgMgr.this.getLocChange());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }

    LocChange getLocChange() {
        if (this.mLocChange == null) {
            this.mLocChange = new LocChange();
        }
        return this.mLocChange;
    }

    private class LocChange extends LocationChange {
        private LocChange() {
        }

        @Override // com.hzbhd.canbus.adapter.location.LocationChange, com.hzbhd.canbus.adapter.location.LocationCallback
        public void getBearAndAltitude(float f, double d) throws RemoteException {
            super.getBearAndAltitude(f, d);
            int i = (int) f;
            int i2 = (int) d;
            byte b = (byte) (i >> 8);
            byte b2 = (byte) (i & 255);
            byte b3 = (byte) (i2 >> 8);
            byte b4 = (byte) (i2 & 255);
            try {
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValue=" + i);
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValue=" + i2);
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValueHi=" + ((int) b));
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValueLo=" + ((int) b2));
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValueHi=" + ((int) b));
                Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValueLo=" + ((int) b4));
            } catch (Exception unused) {
            }
            CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -96, 9, b, b2, b3, b4});
        }
    }

    private void initPenglingCmds() {
        TimerTask timerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._269.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr.this.initAmpCmds();
                MsgMgr.this.mTimer.cancel();
            }
        };
        Timer timer = new Timer();
        this.mTimer = timer;
        timer.schedule(timerTask, 6000L, 500L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte[] bArr = {AMP_VOL_OFFSET, -96, 11, CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 17, (byte) 18), CommUtil.getRadioFreqHiOrLow(str, str2, true), CommUtil.getRadioFreqHiOrLow(str, str2, false)};
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{AMP_VOL_OFFSET, -96, 10, 2});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        try {
            CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{AMP_VOL_OFFSET, -96, 6, 9}, Util.exceptBOMHead((i + "-" + getDateTime2Digit(i3) + "-" + getDateTime2Digit(i4) + " " + getDateTime2Digit(i8) + ":" + getDateTime2Digit(i6) + ":00").getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendBtMusicPlayPause(true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        sendBtMusicPlayPause(false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = (int) (j2 / 1000);
        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -96, 8, (byte) ((i4 % 3600) / 60), (byte) (i4 % 60), b4, b5});
        if (str6 != this.mArtistRec) {
            sendId3Cmds(str6, (byte) 1);
        }
        if (str4 != this.mSongRec) {
            sendId3Cmds(str4, (byte) 2);
        }
        if (str5 != this.mAlbumRec) {
            sendId3Cmds(str5, (byte) 4);
        }
        sendMusicPlayPause(z);
        this.mIsPayingRec = z;
        this.mSongRec = str4;
        this.mArtistRec = str6;
        this.mAlbumRec = str5;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String str) {
        super.musicLrcInfoChange(str);
        Log.i("[PENGLING]", "SND: musicLrcurrentAmpVolInfoChange lrc=" + str);
        sendId3Cmds(str, (byte) 3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        Log.i("[PENGLING]", "SND: btMusicId3InfoChange title=" + str);
        if (str2 != this.mArtistRec) {
            sendId3Cmds(str2, (byte) 1);
        }
        if (str != this.mSongRec) {
            sendId3Cmds(str, (byte) 2);
        }
        if (str3 != this.mAlbumRec) {
            sendId3Cmds(str3, (byte) 4);
        }
        sendId3Cmds(str, (byte) 3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        Log.i("[PENGLING]", "SND: {musicDestroy}");
        sendMusicPlayPause(false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01");
            sndSwcKey();
        } else if (i == 9) {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09");
            setBrightness();
            sendDoorMsg();
        } else {
            if (i != 23) {
                return;
            }
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17");
            setTrack();
        }
    }

    private void sndSwcKey() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 23) {
            Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x17");
            realKeyClick(14);
            return;
        }
        if (i != 25) {
            switch (i) {
                case 17:
                    Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x11");
                    realKeyClick(7);
                    break;
                case 18:
                    Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x12");
                    realKeyClick(8);
                    break;
                case 19:
                    Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x13");
                    realKeyClick(45);
                    break;
                case 20:
                    Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x14");
                    realKeyClick(46);
                    break;
            }
            return;
        }
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--0x19");
        realKeyClick(3);
    }

    private void realKeyClick(int i) {
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x01--sndSwcKey--realKeyClick--KEY_STATE=" + this.mCanBusInfoInt[3]);
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void setTrack() {
        int i = this.mCanBusInfoInt[2];
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) i, (byte) 0, 128, 173, 8);
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17--setTrack  track=" + i);
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17--setTrack  IS_RIGHT=" + (i > 128));
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x17--setTrack  GeneralParkData.trackAngle=" + GeneralParkData.trackAngle);
        updateParkUi(null, this.mContext);
    }

    private void sendDoorMsg() {
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--sendDoorMsg");
        try {
            mIsFLOpened = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            mIsFROpened = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            mIsRLOpened = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            mIsRROpened = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            mIsBackOpened = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            if (isNeedSendDoorInfo()) {
                Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--sendDoorMsg--isNeedSendDoorInfo");
                Intent intent = new Intent("ACTION_CAN_DOOR_CHANGED");
                intent.putExtra("KEY_FOUR_DOOR_VERSION", mIsFourDoorVersion);
                intent.putExtra("KEY_DOOR_FL", mIsFLOpened);
                intent.putExtra("KEY_DOOR_FR", mIsFROpened);
                intent.putExtra("KEY_DOOR_RL", mIsRLOpened);
                intent.putExtra("KEY_DOOR_RR", mIsRROpened);
                intent.putExtra("KEY_DOOR_BACK", mIsBackOpened);
                this.mContext.sendBroadcast(intent);
            }
            mIsFLOpenedRec = mIsFLOpened;
            mIsFROpenedRec = mIsFROpened;
            mIsRLOpenedRec = mIsRLOpened;
            mIsRROpenedRec = mIsRROpened;
            mIsBackOpenedRec = mIsBackOpened;
        } catch (Exception unused) {
        }
    }

    private boolean isNeedSendDoorInfo() {
        return mIsFourDoorVersion ? (mIsFLOpened == mIsFLOpenedRec && mIsFROpenedRec == mIsFROpened && mIsRLOpenedRec == mIsRLOpened && mIsRROpenedRec == mIsRROpened && mIsBackOpenedRec == mIsBackOpened) ? false : true : (mIsFLOpened == mIsFLOpenedRec && mIsFROpenedRec == mIsFROpened && mIsBackOpenedRec == mIsBackOpened) ? false : true;
    }

    private void setBrightness() {
        int i = this.mCanBusInfoInt[8];
        Log.i("[PENGLING]", "REC: {canbusInfoChange} --0x09--setBrightness level=" + i);
        if (i <= 4) {
            setBacklightLevel(i + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDrirectAndAltitude(Location location) {
        int bearing = (int) location.getBearing();
        int altitude = (int) location.getAltitude();
        byte b = (byte) (bearing >> 8);
        byte b2 = (byte) (bearing & 255);
        byte b3 = (byte) (altitude >> 8);
        byte b4 = (byte) (altitude & 255);
        try {
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValue=" + bearing);
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValue=" + altitude);
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValueHi=" + ((int) b));
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} directValueLo=" + ((int) b2));
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValueHi=" + ((int) b));
            Log.i("[PENGLING]", "SND: {sendDrirectAndAltitude} altitudeValueLo=" + ((int) b4));
        } catch (Exception unused) {
        }
        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -96, 9, b, b2, b3, b4});
    }

    private void sendId3Cmds(String str, byte b) {
        try {
            CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{AMP_VOL_OFFSET, -96, b}, Util.exceptBOMHead(str.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void sendMusicPlayPause(boolean z) {
        byte[] bArr = new byte[5];
        bArr[0] = AMP_VOL_OFFSET;
        bArr[1] = -96;
        bArr[2] = 10;
        bArr[3] = 1;
        bArr[4] = z ? (byte) 1 : (byte) 2;
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendBtMusicPlayPause(boolean z) {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{AMP_VOL_OFFSET, -96, 10, 3, z ? (byte) 1 : (byte) 0});
    }

    private String getDateTime2Digit(int i) {
        if (i <= 9) {
            return "0" + i;
        }
        return "" + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNaviCmds(String str, byte b) {
        if (str == null) {
            return;
        }
        try {
            CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{AMP_VOL_OFFSET, -96, 6, b}, Util.exceptBOMHead(str.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void initAmpPrams(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_VOL, 8);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_FR, 9);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_LR, 9);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_TRE, 9);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_MID, 9);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, CAN_269_SAVE_AMP_BASS, 9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAmpCmds() {
        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -93, (byte) (((byte) GeneralAmplifierData.volume) + AMP_VOL_OFFSET), (byte) (((byte) GeneralAmplifierData.frontRear) + 10), (byte) (((byte) GeneralAmplifierData.leftRight) + 10), (byte) (((byte) GeneralAmplifierData.bandBass) + 1), (byte) (((byte) GeneralAmplifierData.bandMiddle) + 1), (byte) (((byte) GeneralAmplifierData.bandTreble) + 1)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmpPrams(this.mContext);
        initPenglingCmds();
    }

    void updateAmpUi(Context context) {
        updateAmplifierActivity(null);
    }
}
