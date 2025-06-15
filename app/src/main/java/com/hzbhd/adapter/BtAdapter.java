package com.hzbhd.adapter;

import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.bean.Device;
import com.hzbhd.proxy.bluetooth.listener.BtListener;
import com.hzbhd.proxy.bluetooth.manager.BtManager;

import java.util.ArrayList;

public final class BtAdapter {
    private static int callStatus;
    private static int callTime;
    private static boolean isAudioTransferTowardsAG;
    private static boolean isCallingFlag;
    private static boolean isHfpConnected;
    private static boolean isMicMute;
    public static final BtAdapter INSTANCE = new BtAdapter();
    private static final BtManager btManager = BtManager.INSTANCE;
    private static byte[] phoneNumber = new byte[0];
    private static String mTitle = "";
    private static String mArtist = "";
    private static String mAlbum = "";
    private static final MediaShareData.Bluetooth mCanInf = MediaShareData.Bluetooth.INSTANCE;
    private static BtConstants.CallState curCallState = BtConstants.CallState.NORMAL;

    private BtAdapter() {
    }

    public void scan(boolean scan) {
        if (scan) {
            btManager.sendAction(BtConstants.BT_ACTION.SCAN);
        } else {
            btManager.sendAction(BtConstants.BT_ACTION.STOP_SCAN);
        }
    }

    public void pair(String address, boolean pair) {
        if (pair) {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.PAIR, address);
        } else {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.UNPAIR, address);
        }
    }

    public void connectHfp(String address, boolean conn) {
        if (conn) {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.CONN_HFP, address);
        } else {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.DISCONN_HFP, address);
        }
    }

    public void connectA2dp(String address, boolean conn) {
        if (conn) {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.CONN_A2DP, address);
        } else {
            btManager.sendDeviceAction(BtConstants.BT_ACTION.DISCONN_A2DP, address);
        }
    }

    public void setAutoConn(boolean autoConn) {
        if (autoConn) {
            btManager.sendAction(BtConstants.BT_ACTION.AUTOCONN);
        } else {
            btManager.sendAction(BtConstants.BT_ACTION.NOT_AUTOCONN);
        }
    }

    public void call(String number) {
        btManager.call(number);
    }

    public void a2dpPlay() {
        btManager.sendAction(BtConstants.BT_ACTION.A2DP_PLAY);
    }

    public void a2dpPause() {
        btManager.sendAction(BtConstants.BT_ACTION.A2DP_PAUSE);
    }

    public void a2dpPrev() {
        btManager.sendAction(BtConstants.BT_ACTION.A2DP_PREV);
    }

    public void a2dpNext() {
        btManager.sendAction(BtConstants.BT_ACTION.A2DP_NEXT);
    }

    public void setMicOut(boolean out) {
        if (out) {
            btManager.sendAction(BtConstants.BT_ACTION.MIC_OUT);
        } else {
            btManager.sendAction(BtConstants.BT_ACTION.MIC_IN);
        }
    }

    public void syncPhoneBook() {
        btManager.sendAction(BtConstants.BT_ACTION.SYNC_PHONE_BOOK);
    }

    public void answer() {
        btManager.sendAction(BtConstants.BT_ACTION.ANSWER);
    }

    public void hangup() {
        btManager.sendAction(BtConstants.BT_ACTION.HANDUP);
    }

    public void muteMic(boolean mute) {
        if (mute) {
            btManager.sendAction(BtConstants.BT_ACTION.MUTE_MIC);
        } else {
            btManager.sendAction(BtConstants.BT_ACTION.UNMUTE_MIC);
        }
    }

    public void sendKey(String key) {
        btManager.sendKey(key);
    }

    public void setBtName(String name) {
        btManager.setName(name);
    }

    public String getHfpDeviceName() {
        ArrayList<Device> conHfp = btManager.getConHfp();
        return conHfp.isEmpty() ? "" : conHfp.get(0).getName();
    }

    public MediaShareData.Bluetooth getMCanInf() {
        return mCanInf;
    }

    public BtConstants.CallState getCurCallState() {
        return curCallState;
    }

    public void setCurCallState(BtConstants.CallState callState) {
        curCallState = callState;
    }

    public void registerListener() {
        btManager.setBtCallBack(new BtListener() {
            @Override
            public void onCloseA2dp() {
            }

            @Override
            public void onCloseHfp() {
            }

            @Override
            public void onCurrA2dpAddressChange(String address) {
            }

            @Override
            public void onCurrHfpAddressChange(String address) {
            }

            @Override
            public void onMicOutChange(boolean out) {
            }

            @Override
            public void onPairedChange(ArrayList<Device> pairList) {
            }

            @Override
            public void onVisibleDeviceChange(ArrayList<Device> deviceList) {
            }

            @Override
            public void updateIsAutoAnswer(boolean autoAnswer) {
            }

            @Override
            public void updateIsAutoConn(boolean autoConn) {
            }

            @Override
            public void updateName(String name) {
            }

            @Override
            public void updateWechatCall(BtCall changeCall) {
            }

            @Override
            public void onHfpConnChange(ArrayList<Device> hfpList) {
                isHfpConnected = !hfpList.isEmpty();
                mCanInf.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG);
                mCanInf.notifyBtStatusChange();
            }

            @Override
            public void updateBtStatus(BtConstants.BT_STATUS statusName, BtStatus status) {
                switch (statusName) {
                    case CALLING:
                        if (status.getIsCalling()) {
                            updateCallState(BtConstants.CallState.CALLING);
                        } else if (!status.getIsInComing() && !status.getIsOutGoing()) {
                            updateCallState(BtConstants.CallState.NORMAL);
                        }
                        break;

                    case INCOMING:
                        if (status.getIsInComing()) {
                            updateCallState(BtConstants.CallState.IN_CALLING);
                        } else if (!status.getIsCalling() && !status.getIsOutGoing()) {
                            updateCallState(BtConstants.CallState.NORMAL);
                        }
                        break;

                    case OUTGOING:
                        if (status.getIsOutGoing()) {
                            updateCallState(BtConstants.CallState.OUT_CALLING);
                        } else if (!status.getIsInComing() && !status.getIsCalling()) {
                            updateCallState(BtConstants.CallState.NORMAL);
                        }
                        break;
                }
            }

            @Override
            public void updateCall(ArrayList<BtCall> calls, BtCall changeCall) {
                if (!calls.isEmpty()) {
                    phoneNumber = calls.get(0).getCallNum().getBytes();
                    callTime = (int) calls.get(0).getCallingTime();
                    updateCallState(calls.get(0).getCallState());
                }
            }

            @Override
            public void updateCallDevice(BtConstants.CallDevice callDevice) {
                isAudioTransferTowardsAG = callDevice == BtConstants.CallDevice.Phone;
                mCanInf.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG);
                mCanInf.notifyBtStatusChange();
            }

            @Override
            public void updateId3(String title, String artist, String album) {
                mCanInf.btMusicId3InfoChange(title != null ? title : "", artist != null ? artist : "", album != null ? album : "");
            }

            @Override
            public void updateMicMute(boolean mute) {
                isMicMute = mute;
                mCanInf.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG);
                mCanInf.notifyBtStatusChange();
            }
        });
    }

    private void updateCallState(BtConstants.CallState callState) {
        callStatus = getCallStatus(callState);
        isCallingFlag = callState != BtConstants.CallState.NORMAL;
        switch (callState) {
            case OUT_CALLING:
                mCanInf.btPhoneOutGoingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
                break;
            case IN_CALLING:
                mCanInf.btPhoneIncomingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
                break;
            case CALLING:
                mCanInf.btPhoneTalkingWithTimeInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG, callTime);
                break;
            case NORMAL:
                mCanInf.btPhoneHangUpInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
                break;
        }
        curCallState = callState;
        mCanInf.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG);
        mCanInf.notifyBtStatusChange();
    }

    private int getCallStatus(BtConstants.CallState callState) {
        switch (callState) {
            case OUT_CALLING:
                return 2;
            case IN_CALLING:
                return 3;
            case CALLING:
                return 4;
            case NORMAL:
                return 0;
            default:
                return callStatus;
        }
    }
}
