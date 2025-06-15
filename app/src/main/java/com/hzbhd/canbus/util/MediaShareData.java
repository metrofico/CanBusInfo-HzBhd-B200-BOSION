package com.hzbhd.canbus.util;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.SystemStatusDef;
import com.hzbhd.constant.audio.AudioConstants;
import com.hzbhd.constant.media.MeidaConstants;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.ui.util.BaseUtil;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import kotlin.jvm.internal.Intrinsics;


public final class MediaShareData {
    private static final int DEFAULT_INT = -1;
    private static final String DEFAULT_STRING = "";
    private static final String TAG = "MediaShareData";
    public static final MediaShareData INSTANCE = new MediaShareData();
    private static final MsgMgrInterface msgMgrInterface = MsgMgrFactory.getCanMsgMgr(BaseUtil.INSTANCE.getContext());

    private MediaShareData() {
    }

    public final void registerModuleListener(Context context) {
        System.INSTANCE.registerListener();
        Misc.INSTANCE.registerListener();
        Source.INSTANCE.registerListener(context);
        Screen.INSTANCE.registerListener();
        Radio.INSTANCE.registerListener();
        Bluetooth.INSTANCE.registerListener();
        Music.INSTANCE.registerListener();
        Video.INSTANCE.registerListener();
        Volume.INSTANCE.registerListener();
    }

    public static final class System {
        public static final System INSTANCE = new System();
        private static int backLight;
        private static int mSystemStatusPower;

        private System() {
        }

        public final int getMSystemStatusPower() {
            return mSystemStatusPower;
        }

        public final int getBackLight() {
            return backLight;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SYS_POWER, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$System$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.System.m1170registerListener$lambda0(i);
                }
            });
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SYS_BACKLIGHT_STATUS, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$System$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.System.backLight = i;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1170registerListener$lambda0(int i) {
            mSystemStatusPower = i;
            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
            if (msgMgrInterface != null) {
                msgMgrInterface.sourceSwitchNoMediaInfoChange(i == SystemStatusDef.POWER_STATUS.ACC_OFF.ordinal());
            }
        }
    }

    public static final class Misc {
        public static final Misc INSTANCE = new Misc();
        private static int miscReverse;

        private Misc() {
        }

        public final int getMiscReverse() {
            return miscReverse;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Misc$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Misc.miscReverse = i;
                }
            });
        }
    }

    public static final class Source {
        private static int currentCamera;
        private static int id;
        public static final Source INSTANCE = new Source();
        private static String pkgName = MediaShareData.DEFAULT_STRING;
        private static String clsName = MediaShareData.DEFAULT_STRING;
        private static final Map<String, ComponentName> originalDeviceMap = new HashMap<String, ComponentName>() {{
            put(Constant.OriginalDeviceActivity.getClassName(), Constant.OriginalDeviceActivity);
            put(Constant.SyncActivity.getClassName(), Constant.SyncActivity);
            put(Constant.OnStarActivity.getClassName(), Constant.OnStarActivity);
        }};
        private static SourceConstantsDef.SOURCE_ID source = SourceConstantsDef.SOURCE_ID.NULL;
        private static SourceConstantsDef.SOURCE_ID preSource = SourceConstantsDef.SOURCE_ID.NULL;
        private static final Map<SourceConstantsDef.SOURCE_ID, List<Runnable>> sourceMap = new HashMap<>();

        static {
            sourceMap.put(SourceConstantsDef.SOURCE_ID.FM, Arrays.asList(null, // No hay acción asociada para el primer "Runnable"
                    new Runnable() {
                        @Override
                        public void run() {
                            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                            if (msgMgrInterface != null) {
                                msgMgrInterface.radioDestroy();
                            }
                        }
                    }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.VIDEO, Arrays.asList(null, // No hay acción asociada para el primer "Runnable"
                    new Runnable() {
                        @Override
                        public void run() {
                            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                            if (msgMgrInterface != null) {
                                msgMgrInterface.videoDestroy();
                            }
                        }
                    }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.MUSIC, Arrays.asList(null, // No hay acción asociada para el primer "Runnable"
                    new Runnable() {
                        @Override
                        public void run() {
                            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                            if (msgMgrInterface != null) {
                                msgMgrInterface.musicDestroy();
                            }
                        }
                    }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.BTAUDIO, Arrays.asList(new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.btMusicInfoChange();
                    }
                }
            }, new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.btMusiceDestdroy();
                    }
                }
            }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.AUX1, Arrays.asList(new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.auxInInfoChange();
                    }
                }
            }, new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.auxInDestdroy();
                    }
                }
            }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.ATV, Arrays.asList(new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.atvInfoChange();
                    }
                }
            }, new Runnable() {
                @Override
                public void run() {
                    MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                    if (msgMgrInterface != null) {
                        msgMgrInterface.atvDestdroy();
                    }
                }
            }));

            sourceMap.put(SourceConstantsDef.SOURCE_ID.DTV, Arrays.asList(new Runnable() {
                                                                              @Override
                                                                              public void run() {
                                                                                  MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                                                                                  if (msgMgrInterface != null) {
                                                                                      msgMgrInterface.dtvInfoChange();
                                                                                  }
                                                                              }
                                                                          }, null // No hay acción asociada para el segundo "Runnable"
            ));
        }


        private Source() {
        }

        public final int getId() {
            return id;
        }

        public final String getPkgName() {
            return pkgName;
        }

        public final String getClsName() {
            return clsName;
        }

        public final int getCurrentCamera() {
            return currentCamera;
        }

        public final SourceConstantsDef.SOURCE_ID getSource() {
            return source;
        }

        public final void setSource(SourceConstantsDef.SOURCE_ID source_id) {
            source = source_id;
        }

        public final void registerListener(Context context) {
            String strRegisterShareStringListener = ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CURRENT_SOURCE_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Source.m1168registerListener$lambda0(str);
                }
            });
            if (strRegisterShareStringListener != null) {
                parseSourceInfo(strRegisterShareStringListener);
            }
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_CURRENT_CAMERA, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Source.m1169registerListener$lambda1(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1168registerListener$lambda0(String str) {
            INSTANCE.parseSourceInfo(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-1, reason: not valid java name */
        public static final void m1169registerListener$lambda1(int i) {
            Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_CAMERA [" + i + ']');
            currentCamera = i;
            if (i == SourceConstantsDef.SOURCE_ID.FRONTCAMERA.getValue()) {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.cameraInfoChange();
                    return;
                }
                return;
            }
            MsgMgrInterface msgMgrInterface2 = MediaShareData.msgMgrInterface;
            if (msgMgrInterface2 != null) {
                msgMgrInterface2.cameraDestroy();
            }
        }

        private final void parseSourceInfo(String it) {
            try {
                Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_SOURCE_INFO [" + it + ']');
                JSONObject jSONObject = new JSONObject(it);

                // Asignación de valores con comprobación nula
                id = jSONObject.optInt("id");
                pkgName = jSONObject.optString("pkgName");
                Intrinsics.checkNotNullExpressionValue(pkgName, "optString(\"pkgName\")");

                clsName = jSONObject.optString("clsName");
                Intrinsics.checkNotNullExpressionValue(clsName, "optString(\"clsName\")");

                // Obtener y asignar tipo de fuente
                SourceConstantsDef.SOURCE_ID type = SourceConstantsDef.SOURCE_ID.getType(id);
                Intrinsics.checkNotNullExpressionValue(type, "getType(id)");
                source = type;

                Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_SOURCE_INFO source[" + source + "] preSource[" + preSource + ']');

                // Notificar cambios de fuente para el caso de FM
                if (source == SourceConstantsDef.SOURCE_ID.FM && !Radio.INSTANCE.getRadioInfoSave().equals("{}")) {
                    Radio.INSTANCE.notifyRadioInfo(Radio.INSTANCE.getRadioInfoSave());
                }

                // Notificar cambio de fuente a través de la interfaz de mensajes
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.sourceSwitchChange(source.name());
                }

                // Verificar si ha cambiado la fuente y ejecutar las acciones correspondientes
                if (source != preSource) {
                    // Ejecutar la acción asociada a la fuente previa (segundo "Runnable")
                    List<Runnable> previousSourceActions = sourceMap.get(preSource);
                    if (previousSourceActions != null && !previousSourceActions.isEmpty()) {
                        previousSourceActions.get(1).run();
                    }

                    // Ejecutar la acción asociada a la nueva fuente (primer "Runnable")
                    List<Runnable> currentSourceActions = sourceMap.get(source);
                    if (currentSourceActions != null && !currentSourceActions.isEmpty()) {
                        currentSourceActions.get(0).run();
                    }
                }

                // Actualizar la fuente previa
                preSource = source;

            } catch (Throwable e) {
                Log.e(MediaShareData.TAG, "Error parsing source info", e);
            }
        }
    }

    public static final class Screen {
        public static final Screen INSTANCE = new Screen();
        private static int screenBacklight;

        private Screen() {
        }

        public final int getScreenBacklight() {
            return screenBacklight;
        }

        public final void registerListener() {
            screenBacklight = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SCREEN_BACKLIGHT, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Screen$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    Log.i(MediaShareData.TAG, "Screen: SHARE_SCREEN_BACKLIGHT [" + i + ']');
                    screenBacklight = i;
                }
            });
        }


    }

    public static final class Radio {
        private static int currClickPresetIndex;
        private static int isStereo;
        private static int mIsStereo;
        private static int mPresetIndex;
        public static final Radio INSTANCE = new Radio();
        private static String mBand = MediaShareData.DEFAULT_STRING;
        private static String mFreq = MediaShareData.DEFAULT_STRING;
        private static String mUnit = MediaShareData.DEFAULT_STRING;
        private static String mPsName = MediaShareData.DEFAULT_STRING;
        private static String currBand = MediaShareData.DEFAULT_STRING;
        private static String currentFreq = MediaShareData.DEFAULT_STRING;
        private static String psName = MediaShareData.DEFAULT_STRING;
        private static String radioInfoSave = "{}";

        private Radio() {
        }

        public final String getMBand() {
            return mBand;
        }

        private final void setMBand(String str) {
            mBand = str;
            currBand = str;
        }

        public final String getMFreq() {
            return mFreq;
        }

        private final void setMFreq(String str) {
            mFreq = str;
            currentFreq = str;
        }

        public final String getMUnit() {
            return mUnit;
        }

        public final int getMPresetIndex() {
            return mPresetIndex;
        }

        private final void setMPresetIndex(int i) {
            mPresetIndex = i;
            currClickPresetIndex = i;
        }

        public final String getMPsName() {
            return mPsName;
        }

        private final void setMPsName(String str) {
            mPsName = str;
            psName = str;
        }

        public final int getMIsStereo() {
            return mIsStereo;
        }

        private final void setMIsStereo(int i) {
            mIsStereo = i;
            isStereo = i;
        }

        public final String getRadioInfoSave() {
            return radioInfoSave;
        }

        public final void setRadioInfoSave(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            radioInfoSave = str;
        }

        public final void registerListener() {
            String strRegisterShareStringListener = ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_RADIO_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Radio$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    Log.i(MediaShareData.TAG, "Radio: SHARE_RADIO_INFO [" + str + ']');
                    if (str != null) {
                        INSTANCE.notifyRadioInfo(str);
                    }
                }
            });
            radioInfoSave = strRegisterShareStringListener;
            if (strRegisterShareStringListener != null) {
                notifyRadioInfo(strRegisterShareStringListener);
            }
        }


        public final void notifyRadioInfo(String it) {
            MsgMgrInterface msgMgrInterface;
            try {
                JSONObject jSONObject = new JSONObject(it);
                Radio radio = INSTANCE;
                String strOptString = jSONObject.optString("band");
                radio.setMBand(strOptString);
                String strOptString2 = jSONObject.optString(LcdInfoShare.RadioInfo.freq);
                radio.setMFreq(strOptString2);
                mUnit = jSONObject.optString("unit");
                radio.setMPresetIndex(jSONObject.optInt("presetIndex"));
                String strOptString4 = jSONObject.optString("psName");
                radio.setMPsName(strOptString4);
                radio.setMIsStereo(jSONObject.optInt("isStereo"));
                if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.FM || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                    return;
                }
                msgMgrInterface.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
            } catch (Throwable w) {
                w.printStackTrace();
            }
        }
    }

    public static final class Bluetooth {
        private static final long POST_DELAY = 300;
        private static int callStatus;
        private static boolean isAudioTransferTowardsAG;
        private static boolean isCallingFlag;
        private static boolean isHfpConnected;
        private static boolean isMicMute;
        public static final Bluetooth INSTANCE = new Bluetooth();
        private static byte[] phoneNumber = new byte[0];
        private static final Handler handler = new Handler(Looper.getMainLooper());
        private static final Runnable btStatusRunnable = new Runnable() { // from class: com.hzbhd.canbus.util.MediaShareData$Bluetooth$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgrInterface msgMgrInterface;
                if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                    return;
                }
                msgMgrInterface.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG, 0, 0, null);

            }
        };
        private static final Runnable notifyBtStatusRunnable = new Runnable() { // from class: com.hzbhd.canbus.util.MediaShareData$Bluetooth$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgrInterface msgMgrInterface;
                if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                    return;
                }
                msgMgrInterface.notifyBtStatusChange();
            }
        };

        private Bluetooth() {
        }

        public final String getCallState() {
            int i = callStatus;
            return i != 1 ? i != 2 ? i != 4 ? i != 5 ? "NO_CALL" : "END_CALL" : "CALLING" : "OUT_CALLING" : "IN_CALLING";
        }

        public final void registerListener() {
            BtAdapter.INSTANCE.registerListener();
        }


        public final void btPhoneStatusInfoChange(int callStatus2, byte[] number, boolean isHfpConn, boolean isCalling, boolean isMicMute2, boolean isAudioAG) {
            phoneNumber = number;
            isHfpConnected = isHfpConn;
            isCallingFlag = isCalling;
            isAudioTransferTowardsAG = isAudioAG;
            Handler handler2 = handler;
            Runnable runnable = btStatusRunnable;
            handler2.removeCallbacks(runnable);
            handler2.postDelayed(runnable, POST_DELAY);
        }

        public final void btPhoneOutGoingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneOutGoingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneIncomingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneIncomingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneTalkingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneTalkingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneTalkingWithTimeInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG, int time) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneTalkingWithTimeInfoChange(number, isMicMute2, isAudioAG, time);
        }

        public final void btPhoneHangUpInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneHangUpInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btMusicId3InfoChange(String title, String artist, String album) {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTAUDIO || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btMusicId3InfoChange(title, artist, album);
        }

        public final void notifyBtStatusChange() {
            Handler handler2 = handler;
            Runnable runnable = notifyBtStatusRunnable;
            handler2.removeCallbacks(runnable);
            handler2.postDelayed(runnable, POST_DELAY);
        }
    }

    public static final class Music {
        private static int aPlayIndex;
        private static byte currentAllMinute;
        private static byte currentAllMinuteStr;
        private static byte currentHour;
        private static byte currentMinute;
        private static byte currentPlayingIndexHigh;
        private static int currentPlayingIndexLow;
        private static long currentPos;
        private static byte currentSecond;
        private static boolean isPlaying;
        private static boolean isReportFromPlay;
        private static int mDuration;
        private static int mPlayIndex;
        private static int mPlayMode;
        private static int mPlaySize;
        private static int mPlayState;
        private static long mPos;
        private static byte playModel;
        private static byte playRatio;
        private static byte storagePath;
        private static int totalCount;
        private static long totalTime;
        public static final Music INSTANCE = new Music();
        private static String mPath = MediaShareData.DEFAULT_STRING;
        private static String mName = MediaShareData.DEFAULT_STRING;
        private static String mArtist = MediaShareData.DEFAULT_STRING;
        private static String mAlbumName = MediaShareData.DEFAULT_STRING;
        private static String mAlbumPath = MediaShareData.DEFAULT_STRING;
        private static String currentHourStr = MediaShareData.DEFAULT_STRING;
        private static String currentMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentSecondStr = MediaShareData.DEFAULT_STRING;
        private static String songTitle = MediaShareData.DEFAULT_STRING;
        private static String songAlbum = MediaShareData.DEFAULT_STRING;
        private static String songArtist = MediaShareData.DEFAULT_STRING;

        private Music() {
        }

        public final int getMPlayState() {
            return mPlayState;
        }

        private final void setMPlayState(int i) {
            mPlayState = i;
            isPlaying = i == MeidaConstants.PLAY_STATE.STARTED.ordinal();
        }

        public final String getMPath() {
            return mPath;
        }

        private final void setMPath(String str) {
            mPath = str;
            storagePath = str.contains("emulated") ? (byte) 9 : (byte) 0;
        }

        public final String getMName() {
            return mName;
        }

        private final void setMName(String str) {
            mName = str;
            songTitle = str;
        }

        public final String getMArtist() {
            return mArtist;
        }

        private final void setMArtist(String str) {
            mArtist = str;
            songArtist = str;
        }

        public final String getMAlbumName() {
            return mAlbumName;
        }

        private final void setMAlbumName(String str) {
            mAlbumName = str;
            songAlbum = str;
        }

        public final String getMAlbumPath() {
            return mAlbumPath;
        }

        public final int getAPlayIndex() {
            return aPlayIndex;
        }

        private final void setAPlayIndex(int i) {
            aPlayIndex = i;
            int i2 = i + 1;
            currentPlayingIndexLow = i2 & 255;
            currentPlayingIndexHigh = (byte) ((i2 >> 8) & 255);
            mPlayIndex = i2;
        }

        public final int getMPlaySize() {
            return mPlaySize;
        }

        private final void setMPlaySize(int i) {
            mPlaySize = i;
            totalCount = i;
        }

        public final int getMPlayMode() {
            return mPlayMode;
        }

        private final void setMPlayMode(int i) {
            mPlayMode = i;
            playModel = (byte) i;
        }

        public final long getMPos() {
            return mPos;
        }

        private final void setMPos(long j) {
            mPos = j;

            // Cálculos de horas, minutos y segundos
            currentHour = (byte) (j / 3600000); // Horas: milisegundos / 3600000 (ms en una hora)
            currentMinute = (byte) ((j % 3600000) / 60000); // Minutos: residuo de horas / 60000 (ms en un minuto)
            currentSecond = (byte) ((j % 60000) / 1000); // Segundos: residuo de minutos / 1000 (ms en un segundo)

            // Calcular minutos totales
            byte totalMinutes = (byte) (j / 60000);
            currentAllMinuteStr = totalMinutes;
            currentAllMinute = totalMinutes;

            // Formateo de horas, minutos y segundos como cadenas con 2 dígitos
            currentHourStr = String.format("%02d", currentHour);
            currentMinuteStr = String.format("%02d", currentMinute);
            currentSecondStr = String.format("%02d", currentSecond);

            // Actualizar la posición
            currentPos = mPos;
        }

        public final int getMDuration() {
            return mDuration;
        }

        private final void setMDuration(int i) {
            mDuration = i;
            playRatio = i == 0 ? (byte) 0 : (byte) ((mPos * 100) / i);
            totalTime = i;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MUSIC_PLAY_STATE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_PLAY_STATE [" + i + ']');
                    Music music = INSTANCE;
                    music.setMPlayState(i);
                    music.infoChange();
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_SONG_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_SONG_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        Music music = INSTANCE;
                        String strOptString = jSONObject.optString("path");
                        music.setMPath(strOptString);
                        String strOptString2 = jSONObject.optString(LcdInfoShare.MediaInfo.name);
                        music.setMName(strOptString2);
                        String strOptString3 = jSONObject.optString(LcdInfoShare.MediaInfo.artist);
                        music.setMArtist(strOptString3);
                        String strOptString4 = jSONObject.optString("albumName");
                        music.setMAlbumName(strOptString4);
                        String strOptString5 = jSONObject.optString("albumPath");
                        mAlbumPath = strOptString5;
                        music.infoChange();
                    } catch (Throwable w) {
                        w.printStackTrace();
                        Log.e(MediaShareData.TAG, "error mediasharedata SHARE_MUSIC_SONG_INFO", w);
                    }
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_TIME_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda2
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_TIME_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        Music music = INSTANCE;
                        music.setMPos(jSONObject.optLong("pos"));
                        music.setMDuration(jSONObject.optInt("duration"));
                        music.infoChange();
                    } catch (Throwable w) {
                        w.printStackTrace();
                        Log.e(MediaShareData.TAG, "error mediasharedata SHARE_MUSIC_TIME_INFO", w);
                    }
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_NUM_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda3
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_NUM_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        int iOptInt = jSONObject.optInt("playIndex", -1);
                        if (!Integer.valueOf(iOptInt).equals(-1)) {
                            INSTANCE.setAPlayIndex(iOptInt);
                        }
                        int iOptInt2 = jSONObject.optInt("playSize", -1);
                        if (!Integer.valueOf(iOptInt2).equals(-1)) {
                            INSTANCE.setMPlaySize(iOptInt2);
                        }
                        int iOptInt3 = jSONObject.optInt("playMode", -1);
                        if (!Integer.valueOf(iOptInt3).equals(-1)) {
                            INSTANCE.setMPlayMode(iOptInt3);
                        }
                        INSTANCE.infoChange();
                    } catch (Throwable w) {
                        w.printStackTrace();
                        Log.e(MediaShareData.TAG, "error mediasharedata SHARE_MUSIC_NUM_INFO", w);
                    }
                }
            });
        }


        private final void infoChange() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.MUSIC || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.musicInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, mPlayIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
        }
    }

    public static final class Video {
        private static byte currentAllMinute;
        private static byte currentHour;
        private static byte currentMinute;
        private static byte currentPlayingIndexHigh;
        private static int currentPlayingIndexLow;
        private static int currentPos;
        private static byte currentSecond;
        private static int duration;
        private static boolean isPlaying;
        private static int mDuration;
        private static int mPlayIndex;
        private static int mPlayMode;
        private static int mPlaySize;
        private static int mPlayState;
        private static long mPos;
        private static byte playMode;
        private static byte playRatio;
        private static byte storagePath;
        private static int totalCount;
        public static final Video INSTANCE = new Video();
        private static String mPath = MediaShareData.DEFAULT_STRING;
        private static String mName = MediaShareData.DEFAULT_STRING;
        private static String mArtist = MediaShareData.DEFAULT_STRING;
        private static String mAlbumName = MediaShareData.DEFAULT_STRING;
        private static String mAlbumPath = MediaShareData.DEFAULT_STRING;
        private static String currentAllMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentHourStr = MediaShareData.DEFAULT_STRING;
        private static String currentMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentSecondStr = MediaShareData.DEFAULT_STRING;

        private Video() {
        }

        public final int getMPlayState() {
            return mPlayState;
        }

        private final void setMPlayState(int i) {
            mPlayState = i;
            isPlaying = i == MeidaConstants.PLAY_STATE.STARTED.ordinal();
        }

        public final String getMPath() {
            return mPath;
        }

        private final void setMPath(String str) {
            mPath = str;
            storagePath = str.contains("emulated") ? (byte) 9 : (byte) 0;
        }

        public final String getMName() {
            return mName;
        }

        public final String getMArtist() {
            return mArtist;
        }

        public final String getMAlbumName() {
            return mAlbumName;
        }

        public final String getMAlbumPath() {
            return mAlbumPath;
        }

        public final long getMPos() {
            return mPos;
        }

        private final void setMPos(long j) {
            mPos = j;

            // Calcular hora, minuto, segundo a partir de los milisegundos (j)
            currentHour = (byte) (j / 3600000); // Milisegundos / 3600000 (ms en una hora)
            currentMinute = (byte) ((j % 3600000) / 60000); // Residuos de horas / 60000 (ms en un minuto)
            currentSecond = (byte) ((j % 60000) / 1000); // Residuos de minutos / 1000 (ms en un segundo)

            // Calcular minutos totales
            currentAllMinute = (byte) (j / 60000); // Milisegundos / 60000 (ms en un minuto)

            // Formatear las horas, minutos y segundos a 2 dígitos
            currentAllMinuteStr = String.format("%02d", currentAllMinute);
            currentHourStr = String.format("%02d", currentHour);
            currentMinuteStr = String.format("%02d", currentMinute);
            currentSecondStr = String.format("%02d", currentSecond);

            // Asignar el valor de la posición
            currentPos = (int) j;
        }


        public final int getMDuration() {
            return mDuration;
        }

        private final void setMDuration(int i) {
            mDuration = i;
            playRatio = (byte) ((mPos * 100) / i);
            duration = i;
        }

        public final int getMPlayIndex() {
            return mPlayIndex;
        }

        private final void setMPlayIndex(int i) {
            mPlayIndex = i;
            int i2 = i + 1;
            currentPlayingIndexLow = i2 & 255;
            currentPlayingIndexHigh = (byte) ((i2 >> 8) & 255);
        }

        public final int getMPlaySize() {
            return mPlaySize;
        }

        private final void setMPlaySize(int i) {
            mPlaySize = i;
            totalCount = i;
        }

        public final int getMPlayMode() {
            return mPlayMode;
        }

        private final void setMPlayMode(int i) {
            mPlayMode = i;
            playMode = (byte) i;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_VIDEO_PLAY_STATE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_PLAY_STATE [" + i + ']');
                    Video video = INSTANCE;
                    video.setMPlayState(i);
                    video.infoChange();
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_MOVIE_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_MOVIE_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        Video video = INSTANCE;
                        String strOptString = jSONObject.optString("path");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(\"path\")");
                        video.setMPath(strOptString);
                        video.infoChange();
                    } catch (Throwable e) {
                        Log.e(MediaShareData.TAG, "error SHARE_VIDEO_MOVIE_INFO", e);
                    }
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_TIME_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda2
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_TIME_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        Video video = INSTANCE;
                        video.setMPos(jSONObject.optLong("pos"));
                        video.setMDuration(jSONObject.optInt("duration"));
                        video.infoChange();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        Log.e(MediaShareData.TAG, "error SHARE_VIDEO_TIME_INFO", e);
                    }
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_NUM_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda3
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    try {
                        Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_NUM_INFO [" + str + ']');
                        JSONObject jSONObject = new JSONObject(str);
                        int iOptInt = jSONObject.optInt("playIndex", -1);
                        if (!Integer.valueOf(iOptInt).equals(-1)) {
                            INSTANCE.setMPlayIndex(iOptInt);
                        }
                        int iOptInt2 = jSONObject.optInt("playSize", -1);
                        if (!Integer.valueOf(iOptInt2).equals(-1)) {
                            INSTANCE.setMPlaySize(iOptInt2);
                        }
                        int iOptInt3 = jSONObject.optInt("playMode", -1);
                        if (!Integer.valueOf(iOptInt3).equals(-1)) {
                            INSTANCE.setMPlayMode(iOptInt3);
                        }
                        INSTANCE.infoChange();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        Log.e(MediaShareData.TAG, "error SHARE_VIDEO_NUM_INFO", e);
                    }
                }
            });
        }


        private final void infoChange() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.VIDEO || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.videoInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
        }
    }

    public static final class Volume {
        public static final Volume INSTANCE = new Volume();
        private static boolean isMute;
        private static int volume;

        private Volume() {
        }

        public final int getVolume() {
            return volume;
        }

        public final boolean isMute() {
            return isMute;
        }

        public final void registerListener() {
            int iRegisterShareIntListener = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_AUDIO_VOLUME, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Volume$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    Log.i(MediaShareData.TAG, "Volume: on SHARE_AUDIO_VOLUME [" + i + ']');
                    INSTANCE.infoChange(i);
                }
            });
            Log.i(MediaShareData.TAG, "Volume: register SHARE_AUDIO_VOLUME [" + iRegisterShareIntListener + ']');
            infoChange(iRegisterShareIntListener);
            if (volume == 127) {
                final TimerUtil timerUtil = new TimerUtil();
                timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.util.MediaShareData$Volume$registerListener$3$1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (Volume.INSTANCE.getVolume() == 127) {
                            int i = ShareDataManager.getInstance().getInt(ShareConstants.SHARE_AUDIO_VOLUME);
                            Log.i("MediaShareData", "Volume: get SHARE_AUDIO_VOLUME [" + i + ']');
                            Volume.INSTANCE.infoChange(i);
                            return;
                        }
                        timerUtil.stopTimer();
                    }
                }, 0L, 1000L);
            }
        }


        /* JADX INFO: Access modifiers changed from: private */
        public final void infoChange(int info) {
            MsgMgrInterface msgMgrInterface;
            volume = AudioConstants.getVolume(info);
            isMute = AudioConstants.isMute(info);
            if (volume == 127 || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.currentVolumeInfoChange(volume, isMute);
        }
    }
}
