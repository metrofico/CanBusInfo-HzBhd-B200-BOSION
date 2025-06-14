package com.hzbhd.canbus.car._432;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\bE\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u009c\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010wH\u0016J\u0019\u0010\u009f\u0001\u001a\u0002042\u0007\u0010 \u0001\u001a\u0002042\u0007\u0010\u009f\u0001\u001a\u000204J \u0010¡\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\t\u0010¢\u0001\u001a\u0004\u0018\u00010tH\u0016J\u001b\u0010£\u0001\u001a\u00030\u009d\u00012\u0007\u0010¤\u0001\u001a\u0002042\u0006\u0010Z\u001a\u00020[H\u0016J\u000b\u0010¥\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010¦\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¢\u0001\u001a\u00020tJ\u0014\u0010§\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010©\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010ª\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u0014\u0010«\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¬\u0001\u001a\u00020[H\u0002J\u000b\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0019\u0010®\u0001\u001a\u0002042\u0007\u0010¯\u0001\u001a\u0002042\u0007\u0010°\u0001\u001a\u000204J\u000b\u0010±\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0014\u0010²\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010³\u0001\u001a\u000204H\u0002J\u0014\u0010´\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010¨\u0001\u001a\u000204H\u0002J\u001d\u0010µ\u0001\u001a\u0002042\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010¶\u0001\u001a\u00020\u0004H\u0002J\u001d\u0010·\u0001\u001a\u0002042\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010¶\u0001\u001a\u00020\u0004H\u0002J\u0014\u0010¸\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010³\u0001\u001a\u000204H\u0002J\u0013\u0010¹\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0015\u0010º\u0001\u001a\u00030\u009d\u00012\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010wH\u0016J\t\u0010»\u0001\u001a\u00020[H\u0002J\t\u0010¼\u0001\u001a\u00020[H\u0002J\t\u0010½\u0001\u001a\u00020[H\u0002J\u0012\u0010¾\u0001\u001a\u00020[2\u0007\u0010¿\u0001\u001a\u000204H\u0002J\t\u0010À\u0001\u001a\u00020[H\u0002J\u0012\u0010Á\u0001\u001a\u00020[2\u0007\u0010Â\u0001\u001a\u000204H\u0002J\t\u0010Ã\u0001\u001a\u00020[H\u0002J\t\u0010Ä\u0001\u001a\u00020[H\u0002J\n\u0010Å\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Æ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ç\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010È\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010É\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ê\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ë\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ì\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Í\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Î\u0001\u001a\u00030\u009d\u0001H\u0003J\n\u0010Ï\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ð\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ñ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ò\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ó\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ô\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Õ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ö\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010×\u0001\u001a\u00030\u009d\u0001H\u0002J\u0013\u0010Ø\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Ù\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Ú\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\u0013\u0010Û\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020wH\u0002J\n\u0010Ü\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Ý\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010Þ\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010ß\u0001\u001a\u00030\u009d\u0001H\u0002J\n\u0010à\u0001\u001a\u00030\u009d\u0001H\u0002J\b\u0010á\u0001\u001a\u00030\u009d\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00106\"\u0004\b;\u00108R\u001a\u0010<\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\u001a\u0010?\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00106\"\u0004\bA\u00108R\u001a\u0010B\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00106\"\u0004\bD\u00108R\u001a\u0010E\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00106\"\u0004\bG\u00108R\u001a\u0010H\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u00106\"\u0004\bJ\u00108R\u001a\u0010K\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u00106\"\u0004\bM\u00108R\u001a\u0010N\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u00106\"\u0004\bP\u00108R\u001a\u0010Q\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u00106\"\u0004\bS\u00108R\u001a\u0010T\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00106\"\u0004\bV\u00108R\u001a\u0010W\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00106\"\u0004\bY\u00108R\u001a\u0010Z\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\\\"\u0004\b]\u0010^R\u001a\u0010_\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\\\"\u0004\b`\u0010^R\u001a\u0010a\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\\\"\u0004\bb\u0010^R\u001a\u0010c\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\\\"\u0004\bd\u0010^R\u001a\u0010e\u001a\u00020[X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\\\"\u0004\bf\u0010^R\u001a\u0010g\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010j\"\u0004\bo\u0010lR\u001a\u0010p\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010j\"\u0004\br\u0010lR\u000e\u0010s\u001a\u00020tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020hX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010v\u001a\u0004\u0018\u00010wX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{R\u001a\u0010|\u001a\u00020hX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010j\"\u0004\b~\u0010lR\u001c\u0010\u007f\u001a\u00020hX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010j\"\u0005\b\u0081\u0001\u0010lR\u001f\u0010\u0082\u0001\u001a\u00020tX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0012\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0089\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u00106\"\u0005\b\u008b\u0001\u00108R\u001d\u0010\u008c\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u00106\"\u0005\b\u008e\u0001\u00108R\u001d\u0010\u008f\u0001\u001a\u000204X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u00106\"\u0005\b\u0091\u0001\u00108R\u001d\u0010\u0092\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0006\"\u0005\b\u0094\u0001\u0010\bR\u0012\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006â\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_432/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "SoftKey0", "", "getSoftKey0", "()Ljava/lang/String;", "setSoftKey0", "(Ljava/lang/String;)V", "SoftKey1", "getSoftKey1", "setSoftKey1", "SoftKey2", "getSoftKey2", "setSoftKey2", "SoftKey3", "getSoftKey3", "setSoftKey3", "content0x78WorkingMode", "getContent0x78WorkingMode", "setContent0x78WorkingMode", "contentStr0x70", "getContentStr0x70", "setContentStr0x70", "contentStr0x71", "getContentStr0x71", "setContentStr0x71", "contentStr0x72", "getContentStr0x72", "setContentStr0x72", "countDownTimer", "Landroid/os/CountDownTimer;", "getCountDownTimer", "()Landroid/os/CountDownTimer;", "setCountDownTimer", "(Landroid/os/CountDownTimer;)V", "countDownTimerAutoPacking", "getCountDownTimerAutoPacking", "setCountDownTimerAutoPacking", "df_2Integer", "Ljava/text/DecimalFormat;", "getDf_2Integer", "()Ljava/text/DecimalFormat;", "df_2float", "getDf_2float", "dialog", "Landroid/app/AlertDialog;", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "icon1", "", "getIcon1", "()I", "setIcon1", "(I)V", "icon2", "getIcon2", "setIcon2", "icon3", "getIcon3", "setIcon3", "icon4", "getIcon4", "setIcon4", "icon5", "getIcon5", "setIcon5", "icon6", "getIcon6", "setIcon6", "icon7", "getIcon7", "setIcon7", "icon8", "getIcon8", "setIcon8", "image0", "getImage0", "setImage0", "image1", "getImage1", "setImage1", "image2", "getImage2", "setImage2", "image3", "getImage3", "setImage3", "isMute", "", "()Z", "setMute", "(Z)V", "isSelected0", "setSelected0", "isSelected1", "setSelected1", "isSelected2", "setSelected2", "isSelected3", "setSelected3", "m0x79Data", "", "getM0x79Data", "()[I", "setM0x79Data", "([I)V", "mAirData", "getMAirData", "setMAirData", "mBacklightInfo", "getMBacklightInfo", "setMBacklightInfo", "mCanBusByte", "", "mCanBusInfoInt", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mFrontRadarData", "getMFrontRadarData", "setMFrontRadarData", "mRearRadarData", "getMRearRadarData", "setMRearRadarData", "mTrackData", "getMTrackData", "()[B", "setMTrackData", "([B)V", "mUiMgr", "Lcom/hzbhd/canbus/car/_432/UiMgr;", "now0x24Data0", "getNow0x24Data0", "setNow0x24Data0", "nowData6", "getNowData6", "setNowData6", "nowIndex", "getNowIndex", "setNowIndex", "nowSyncTime", "getNowSyncTime", "setNowSyncTime", "park_1", "Landroid/widget/Button;", "park_2", "park_3", "park_4", "parking_content", "Landroid/widget/TextView;", "afterServiceNormalSetting", "", "context", "blockBit", "canBusInfo", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "get0x61Str", "getAsciiStr", "getBandState", "i", "getCdPlayState", "getCdState", "getEqData5Bit7Info", "boolBit7", "getFrequencyState", "getMsbLsbResult", "MSB", "LSB", "getPlayMode", "getRearTemperature", "tem", "getRunningState", "getStringIdByName", LcdInfoShare.MediaInfo.name, "getSyncIconResId", "getTemperature", "getUigMgr", "initCommand", "is0x79DataNoChange", "isAirDataChange", "isBacklightInfoChange", "isDoorInfoChange", "data0", "isFrontRadarDataChange", "isRearAirChange", "data6", "isRearRadarDataChange", "isTrackInfoChange", "set0x14BackLightInfo", "set0x16SpeedInfo", "set0x20SwcInfo", "set0x21AirInfo", "set0x22RearRadar", "set0x23FrontRadar", "set0x24BasicInfo", "set0x25RadarState", "set0x27Languge", "set0x28AutoPacking", "set0x29CarInfo", "set0x2bSeatInfo", "set0x30VersionInfo", "set0x35EspInfo", "set0x40SyncVersion", "set0x50SyncInfo", "set0x51SyncInfo", "set0x52SyncInfo", "set0x53SyncInfo", "set0x60RadioInfo", "set0x61RadioInfo", "set0x62CdInfo", "set0x63EqInfo", "set0x70SyncLastStr", "set0x71SyncNextStr", "set0x72SyncTimeInfo", "set0x78SyncState", "set0x79SyncAudio", "updateSYNC", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimerAutoPacking;
    private AlertDialog dialog;
    private boolean isMute;
    private boolean isSelected0;
    private boolean isSelected1;
    private boolean isSelected2;
    private boolean isSelected3;
    private Context mContext;
    private UiMgr mUiMgr;
    private int now0x24Data0;
    private int nowData6;
    private Button park_1;
    private Button park_2;
    private Button park_3;
    private Button park_4;
    private TextView parking_content;
    private int[] mCanBusInfoInt = new int[0];
    private byte[] mCanBusByte = new byte[0];
    private int nowIndex = -1;
    private String nowSyncTime = "";
    private String SoftKey0 = "Audio:";
    private String SoftKey1 = "";
    private String SoftKey2 = "";
    private String SoftKey3 = "";
    private int image0 = R.drawable.ford_sync_null;
    private int image1 = R.drawable.ford_sync_null;
    private int image2 = R.drawable.ford_sync_null;
    private int image3 = R.drawable.ford_sync_null;
    private String content0x78WorkingMode = "Working mode: STATELESS";
    private int icon1 = R.drawable.ford_sync_null;
    private int icon2 = R.drawable.ford_sync_null;
    private int icon3 = R.drawable.ford_sync_null;
    private int icon4 = R.drawable.ford_sync_null;
    private int icon5 = R.drawable.ford_sync_null;
    private int icon6 = R.drawable.ford_sync_null;
    private int icon7 = R.drawable.ford_sync_null;
    private int icon8 = R.drawable.ford_sync_null;
    private String contentStr0x72 = "SYNC STR SHORT";
    private String contentStr0x71 = "SYNC SET DOWN";
    private String contentStr0x70 = "SYNC SET UP";
    private final DecimalFormat df_2Integer = new DecimalFormat("00");
    private final DecimalFormat df_2float = new DecimalFormat("#0.00");
    private int[] mBacklightInfo = new int[0];
    private int[] mAirData = new int[0];
    private int[] mRearRadarData = new int[0];
    private int[] mFrontRadarData = new int[0];
    private int[] m0x79Data = new int[0];
    private byte[] mTrackData = new byte[0];

    private final String getBandState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "NONE" : "AM2" : "AM1" : "FM2" : "FM1" : "FM";
    }

    private final void set0x25RadarState() {
    }

    private final void set0x27Languge() {
    }

    private final void set0x29CarInfo() {
    }

    private final void set0x2bSeatInfo() {
    }

    private final void set0x40SyncVersion() {
    }

    private final void set0x50SyncInfo() {
    }

    private final void set0x51SyncInfo() {
    }

    public final int getMsbLsbResult(int MSB, int LSB) {
        return ((MSB & 255) << 8) | (LSB & 255);
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        if (context != null) {
            this.mContext = context;
            SelectCanTypeUtil.enableApp(context, Constant.SyncActivity);
        }
        GeneralSyncData.mSyncTopIconCount = 8;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        if (context != null) {
            this.mContext = context;
        }
    }

    /* renamed from: isMute, reason: from getter */
    public final boolean getIsMute() {
        return this.isMute;
    }

    public final void setMute(boolean z) {
        this.isMute = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        super.currentVolumeInfoChange(volValue, isMute);
        this.isMute = isMute;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        super.canbusInfoChange(context, canbusInfo);
        if (context == null || canbusInfo == null) {
            return;
        }
        this.mCanBusByte = canbusInfo;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14BackLightInfo();
            return;
        }
        if (i == 22) {
            set0x16SpeedInfo();
            return;
        }
        if (i == 43) {
            set0x2bSeatInfo();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i == 53) {
            set0x35EspInfo();
            return;
        }
        if (i == 64) {
            set0x40SyncVersion();
            return;
        }
        if (i == 120) {
            set0x78SyncState();
            return;
        }
        if (i != 121) {
            switch (i) {
                case 32:
                    set0x20SwcInfo();
                    break;
                case 33:
                    set0x21AirInfo();
                    break;
                case 34:
                    set0x22RearRadar();
                    break;
                case 35:
                    set0x23FrontRadar();
                    break;
                case 36:
                    set0x24BasicInfo();
                    break;
                case 37:
                    set0x25RadarState();
                    break;
                default:
                    switch (i) {
                        case 39:
                            set0x27Languge();
                            break;
                        case 40:
                            set0x28AutoPacking();
                            break;
                        case 41:
                            set0x29CarInfo();
                            break;
                        default:
                            switch (i) {
                                case 80:
                                    set0x50SyncInfo();
                                    break;
                                case 81:
                                    set0x51SyncInfo();
                                    break;
                                case 82:
                                    set0x52SyncInfo();
                                    break;
                                case 83:
                                    set0x53SyncInfo();
                                    break;
                                default:
                                    switch (i) {
                                        case 96:
                                            set0x60RadioInfo(context);
                                            break;
                                        case 97:
                                            set0x61RadioInfo(context);
                                            break;
                                        case 98:
                                            set0x62CdInfo(context);
                                            break;
                                        case 99:
                                            set0x63EqInfo(context);
                                            break;
                                        default:
                                            switch (i) {
                                                case 112:
                                                    set0x70SyncLastStr();
                                                    break;
                                                case 113:
                                                    set0x71SyncNextStr();
                                                    break;
                                                case 114:
                                                    set0x72SyncTimeInfo();
                                                    break;
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        set0x79SyncAudio();
    }

    private final void set0x63EqInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info0"), String.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info1"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info2"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info3"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info4"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info5"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info6"), String.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_EQ_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_EQ_info7"), getEqData5Bit7Info(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final void set0x62CdInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info1"), getCdState(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info2"), getCdPlayState(this.mCanBusInfoInt[3])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info3"), getPlayMode()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info4"), String.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info5"), String.valueOf(this.mCanBusInfoInt[6])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_CD_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_CD_info6"), this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[7])) + " : " + this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[8]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final void set0x61RadioInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info5"), getBandState(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info6"), String.valueOf(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final String get0x61Str() {
        int i = 0;
        byte[] bArr = new byte[0];
        byte[] bArr2 = this.mCanBusByte;
        if (bArr2.length < 6) {
            return " ";
        }
        int length = bArr2.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr2[i];
            int i3 = i2 + 1;
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 3) {
                bArr[i2 - 4] = this.mCanBusByte[4];
            }
            i++;
            i2 = i3;
        }
        return getAsciiStr(bArr);
    }

    private final void set0x60RadioInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info1"), getBandState(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info2"), getRunningState(this.mCanBusInfoInt[3])));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info3"), getFrequencyState()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(context).getDrivingPageIndexes(this.mContext, "_432_radio_info"), getUigMgr(context).getDrivingItemIndexes(this.mContext, "_432_radio_info4"), String.valueOf(this.mCanBusInfoInt[6])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
            Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._432.UiMgr");
            this.mUiMgr = (UiMgr) canUiMgr;
        }
        UiMgr uiMgr = this.mUiMgr;
        Intrinsics.checkNotNull(uiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._432.UiMgr");
        return uiMgr;
    }

    private final void set0x35EspInfo() {
        if (isTrackInfoChange()) {
            return;
        }
        byte[] bArr = this.mCanBusByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
        updateParkUi(null, this.mContext);
    }

    public final AlertDialog getDialog() {
        return this.dialog;
    }

    public final void setDialog(AlertDialog alertDialog) {
        this.dialog = alertDialog;
    }

    public final CountDownTimer getCountDownTimerAutoPacking() {
        return this.countDownTimerAutoPacking;
    }

    public final void setCountDownTimerAutoPacking(CountDownTimer countDownTimer) {
        this.countDownTimerAutoPacking = countDownTimer;
    }

    /* JADX WARN: Type inference failed for: r0v38, types: [com.hzbhd.canbus.car._432.MsgMgr$set0x28AutoPacking$1] */
    private final void set0x28AutoPacking() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout._432_auto_park, (ViewGroup) null, true);
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(this.mContext).setView(viewInflate).create();
        }
        if (this.parking_content == null) {
            this.parking_content = (TextView) viewInflate.findViewById(R.id.parking_content);
        }
        if (this.park_1 == null) {
            this.park_1 = (Button) viewInflate.findViewById(R.id.park_1);
        }
        if (this.park_2 == null) {
            this.park_2 = (Button) viewInflate.findViewById(R.id.park_2);
        }
        if (this.park_3 == null) {
            this.park_3 = (Button) viewInflate.findViewById(R.id.park_3);
        }
        if (this.park_4 == null) {
            this.park_4 = (Button) viewInflate.findViewById(R.id.park_4);
        }
        TextView textView = this.parking_content;
        if (textView != null) {
            textView.setText(getStringIdByName(this.mContext, "_432_car_park_state_" + this.mCanBusInfoInt[3]));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 0) {
            Button button = this.park_1;
            if (button != null) {
                button.setBackgroundColor(R.color.orange);
            }
            Button button2 = this.park_2;
            if (button2 != null) {
                button2.setBackgroundColor(R.color.transparent);
            }
            Button button3 = this.park_3;
            if (button3 != null) {
                button3.setBackgroundColor(R.color.transparent);
            }
            Button button4 = this.park_4;
            if (button4 != null) {
                button4.setBackgroundColor(R.color.transparent);
            }
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 1) {
            Button button5 = this.park_1;
            if (button5 != null) {
                button5.setBackgroundColor(R.color.transparent);
            }
            Button button6 = this.park_2;
            if (button6 != null) {
                button6.setBackgroundColor(R.color.orange);
            }
            Button button7 = this.park_3;
            if (button7 != null) {
                button7.setBackgroundColor(R.color.transparent);
            }
            Button button8 = this.park_4;
            if (button8 != null) {
                button8.setBackgroundColor(R.color.transparent);
            }
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 2) {
            Button button9 = this.park_1;
            if (button9 != null) {
                button9.setBackgroundColor(R.color.transparent);
            }
            Button button10 = this.park_2;
            if (button10 != null) {
                button10.setBackgroundColor(R.color.transparent);
            }
            Button button11 = this.park_3;
            if (button11 != null) {
                button11.setBackgroundColor(R.color.orange);
            }
            Button button12 = this.park_4;
            if (button12 != null) {
                button12.setBackgroundColor(R.color.transparent);
            }
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) == 3) {
            Button button13 = this.park_1;
            if (button13 != null) {
                button13.setBackgroundColor(R.color.transparent);
            }
            Button button14 = this.park_2;
            if (button14 != null) {
                button14.setBackgroundColor(R.color.transparent);
            }
            Button button15 = this.park_3;
            if (button15 != null) {
                button15.setBackgroundColor(R.color.transparent);
            }
            Button button16 = this.park_4;
            if (button16 != null) {
                button16.setBackgroundColor(R.color.orange);
            }
        }
        AlertDialog alertDialog = this.dialog;
        Window window = alertDialog != null ? alertDialog.getWindow() : null;
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        AlertDialog alertDialog2 = this.dialog;
        Window window2 = alertDialog2 != null ? alertDialog2.getWindow() : null;
        Intrinsics.checkNotNull(window2);
        window2.setType(2003);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            AlertDialog alertDialog3 = this.dialog;
            if (alertDialog3 != null) {
                alertDialog3.show();
            }
        } else {
            AlertDialog alertDialog4 = this.dialog;
            if (alertDialog4 != null) {
                alertDialog4.dismiss();
            }
        }
        CountDownTimer countDownTimer = this.countDownTimerAutoPacking;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.countDownTimerAutoPacking = new CountDownTimer() { // from class: com.hzbhd.canbus.car._432.MsgMgr.set0x28AutoPacking.1
            @Override // android.os.CountDownTimer
            public void onTick(long l) {
            }

            {
                super(3000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                AlertDialog dialog = MsgMgr.this.getDialog();
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        }.start();
    }

    public final int getNowIndex() {
        return this.nowIndex;
    }

    public final void setNowIndex(int i) {
        this.nowIndex = i;
    }

    public final void updateSYNC() {
        GeneralSyncData.mIsShowSoftKey = true;
        GeneralSyncData.mIsSyncTimeChange = true;
        GeneralSyncData.mInfoLineShowAmount = 5;
        GeneralSyncData.mSelectedLineIndex = this.nowIndex;
        List<SyncListUpdateEntity> list = GeneralSyncData.mInfoUpdateEntityList;
        SyncListUpdateEntity syncListUpdateEntity = new SyncListUpdateEntity(0);
        syncListUpdateEntity.setLeftIconResId(R.drawable.ford_sync_icon_39);
        syncListUpdateEntity.setRightIconResId(R.drawable.ford_sync_null);
        syncListUpdateEntity.setSelected(true);
        syncListUpdateEntity.setInfo(this.content0x78WorkingMode);
        syncListUpdateEntity.setEnable(false);
        list.add(syncListUpdateEntity);
        SyncListUpdateEntity syncListUpdateEntity2 = new SyncListUpdateEntity(1);
        syncListUpdateEntity2.setLeftIconResId(R.drawable.ford_sync_icon_64);
        syncListUpdateEntity2.setRightIconResId(R.drawable.ford_sync_null);
        syncListUpdateEntity2.setSelected(true);
        syncListUpdateEntity2.setInfo(this.contentStr0x70);
        syncListUpdateEntity2.setEnable(false);
        list.add(syncListUpdateEntity2);
        SyncListUpdateEntity syncListUpdateEntity3 = new SyncListUpdateEntity(2);
        syncListUpdateEntity3.setLeftIconResId(R.drawable.ford_sync_icon_64);
        syncListUpdateEntity3.setRightIconResId(R.drawable.ford_sync_null);
        syncListUpdateEntity3.setSelected(true);
        syncListUpdateEntity3.setInfo(this.contentStr0x71);
        syncListUpdateEntity3.setEnable(false);
        list.add(syncListUpdateEntity3);
        SyncListUpdateEntity syncListUpdateEntity4 = new SyncListUpdateEntity(3);
        syncListUpdateEntity4.setLeftIconResId(R.drawable.ford_sync_icon_64);
        syncListUpdateEntity4.setRightIconResId(R.drawable.ford_sync_null);
        syncListUpdateEntity4.setSelected(true);
        syncListUpdateEntity4.setInfo(this.contentStr0x72);
        syncListUpdateEntity4.setEnable(false);
        list.add(syncListUpdateEntity4);
        GeneralSyncData.mSyncTopIconResIdArray = new int[]{this.icon1, this.icon2, this.icon3, this.icon4, this.icon5, this.icon6, this.icon7, this.icon8};
        List<SyncSoftKeyUpdateEntity> list2 = GeneralSyncData.mSoftKeyUpdateEntityList;
        SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity = new SyncSoftKeyUpdateEntity(0);
        syncSoftKeyUpdateEntity.setName(this.SoftKey0);
        syncSoftKeyUpdateEntity.setImageResId(this.image0);
        syncSoftKeyUpdateEntity.setSelected(this.isSelected0);
        syncSoftKeyUpdateEntity.setVisible(true);
        list2.add(syncSoftKeyUpdateEntity);
        List<SyncSoftKeyUpdateEntity> list3 = GeneralSyncData.mSoftKeyUpdateEntityList;
        SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity2 = new SyncSoftKeyUpdateEntity(1);
        syncSoftKeyUpdateEntity2.setName(this.SoftKey1);
        syncSoftKeyUpdateEntity2.setImageResId(this.image1);
        syncSoftKeyUpdateEntity2.setSelected(this.isSelected1);
        syncSoftKeyUpdateEntity2.setVisible(true);
        list3.add(syncSoftKeyUpdateEntity2);
        List<SyncSoftKeyUpdateEntity> list4 = GeneralSyncData.mSoftKeyUpdateEntityList;
        SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity3 = new SyncSoftKeyUpdateEntity(2);
        syncSoftKeyUpdateEntity3.setName(this.SoftKey2);
        syncSoftKeyUpdateEntity3.setImageResId(this.image2);
        syncSoftKeyUpdateEntity3.setSelected(this.isSelected2);
        syncSoftKeyUpdateEntity3.setVisible(true);
        list4.add(syncSoftKeyUpdateEntity3);
        List<SyncSoftKeyUpdateEntity> list5 = GeneralSyncData.mSoftKeyUpdateEntityList;
        SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity4 = new SyncSoftKeyUpdateEntity(3);
        syncSoftKeyUpdateEntity4.setName(this.SoftKey3);
        syncSoftKeyUpdateEntity4.setImageResId(this.image3);
        syncSoftKeyUpdateEntity4.setSelected(this.isSelected3);
        syncSoftKeyUpdateEntity4.setVisible(true);
        list5.add(syncSoftKeyUpdateEntity4);
        GeneralSyncData.mSyncTime = this.nowSyncTime;
        updateSyncActivity(null);
    }

    private final void set0x53SyncInfo() {
        this.nowSyncTime = this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[3])) + ':' + this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[4]));
        updateSYNC();
    }

    public final String getNowSyncTime() {
        return this.nowSyncTime;
    }

    public final void setNowSyncTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nowSyncTime = str;
    }

    private final void set0x52SyncInfo() {
        this.nowSyncTime = this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[2])) + ':' + this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[3])) + ':' + this.df_2Integer.format(Integer.valueOf(this.mCanBusInfoInt[4]));
        updateSYNC();
    }

    public final String getSoftKey0() {
        return this.SoftKey0;
    }

    public final void setSoftKey0(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.SoftKey0 = str;
    }

    public final String getSoftKey1() {
        return this.SoftKey1;
    }

    public final void setSoftKey1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.SoftKey1 = str;
    }

    public final String getSoftKey2() {
        return this.SoftKey2;
    }

    public final void setSoftKey2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.SoftKey2 = str;
    }

    public final String getSoftKey3() {
        return this.SoftKey3;
    }

    public final void setSoftKey3(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.SoftKey3 = str;
    }

    /* renamed from: isSelected0, reason: from getter */
    public final boolean getIsSelected0() {
        return this.isSelected0;
    }

    public final void setSelected0(boolean z) {
        this.isSelected0 = z;
    }

    /* renamed from: isSelected1, reason: from getter */
    public final boolean getIsSelected1() {
        return this.isSelected1;
    }

    public final void setSelected1(boolean z) {
        this.isSelected1 = z;
    }

    /* renamed from: isSelected2, reason: from getter */
    public final boolean getIsSelected2() {
        return this.isSelected2;
    }

    public final void setSelected2(boolean z) {
        this.isSelected2 = z;
    }

    /* renamed from: isSelected3, reason: from getter */
    public final boolean getIsSelected3() {
        return this.isSelected3;
    }

    public final void setSelected3(boolean z) {
        this.isSelected3 = z;
    }

    public final int getImage0() {
        return this.image0;
    }

    public final void setImage0(int i) {
        this.image0 = i;
    }

    public final int getImage1() {
        return this.image1;
    }

    public final void setImage1(int i) {
        this.image1 = i;
    }

    public final int getImage2() {
        return this.image2;
    }

    public final void setImage2(int i) {
        this.image2 = i;
    }

    public final int getImage3() {
        return this.image3;
    }

    public final void setImage3(int i) {
        this.image3 = i;
    }

    public final CountDownTimer getCountDownTimer() {
        return this.countDownTimer;
    }

    public final void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [com.hzbhd.canbus.car._432.MsgMgr$set0x79SyncAudio$1] */
    private final void set0x79SyncAudio() {
        if (is0x79DataNoChange()) {
            return;
        }
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            this.SoftKey1 = "NONE";
            this.SoftKey2 = "NONE";
            this.SoftKey3 = "NONE";
            this.image1 = R.drawable.ford_sync_null;
            this.image2 = R.drawable.ford_sync_null;
            this.image3 = R.drawable.ford_sync_null;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = false;
        } else if (i == 1) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = R.drawable.ford_sync_icon_41;
            this.image2 = R.drawable.ford_sync_null;
            this.image3 = R.drawable.ford_sync_null;
            this.isSelected1 = true;
            this.isSelected2 = false;
            this.isSelected3 = false;
        } else if (i == 2) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = R.drawable.ford_sync_icon_44;
            this.image2 = R.drawable.ford_sync_null;
            this.image3 = R.drawable.ford_sync_null;
            this.isSelected1 = true;
            this.isSelected2 = false;
            this.isSelected3 = false;
        } else if (i == 3) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = R.drawable.ford_sync_null;
            this.image2 = R.drawable.ford_sync_icon_76;
            this.image3 = R.drawable.ford_sync_null;
            this.isSelected1 = false;
            this.isSelected2 = true;
            this.isSelected3 = false;
        } else if (i == 4) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = R.drawable.ford_sync_null;
            this.image2 = R.drawable.ford_sync_null;
            this.image3 = R.drawable.ford_sync_icon_17;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = true;
        } else if (i == 5) {
            this.SoftKey1 = "";
            this.SoftKey2 = "";
            this.SoftKey3 = "";
            this.image1 = R.drawable.ford_sync_null;
            this.image2 = R.drawable.ford_sync_null;
            this.image3 = R.drawable.ford_sync_icon_36;
            this.isSelected1 = false;
            this.isSelected2 = false;
            this.isSelected3 = true;
        }
        if (!this.isMute) {
            Toast.makeText(this.mContext, "Auto mute for 3 seconds", 0);
            realKeyClick4(this.mContext, 3);
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.countDownTimer = new CountDownTimer() { // from class: com.hzbhd.canbus.car._432.MsgMgr.set0x79SyncAudio.1
                @Override // android.os.CountDownTimer
                public void onTick(long l) {
                }

                {
                    super(3000L, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.realKeyClick4(msgMgr.getMContext(), 3);
                }
            }.start();
        }
        updateSYNC();
    }

    public final String getContent0x78WorkingMode() {
        return this.content0x78WorkingMode;
    }

    public final void setContent0x78WorkingMode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.content0x78WorkingMode = str;
    }

    public final int getIcon1() {
        return this.icon1;
    }

    public final void setIcon1(int i) {
        this.icon1 = i;
    }

    public final int getIcon2() {
        return this.icon2;
    }

    public final void setIcon2(int i) {
        this.icon2 = i;
    }

    public final int getIcon3() {
        return this.icon3;
    }

    public final void setIcon3(int i) {
        this.icon3 = i;
    }

    public final int getIcon4() {
        return this.icon4;
    }

    public final void setIcon4(int i) {
        this.icon4 = i;
    }

    public final int getIcon5() {
        return this.icon5;
    }

    public final void setIcon5(int i) {
        this.icon5 = i;
    }

    public final int getIcon6() {
        return this.icon6;
    }

    public final void setIcon6(int i) {
        this.icon6 = i;
    }

    public final int getIcon7() {
        return this.icon7;
    }

    public final void setIcon7(int i) {
        this.icon7 = i;
    }

    public final int getIcon8() {
        return this.icon8;
    }

    public final void setIcon8(int i) {
        this.icon8 = i;
    }

    private final void set0x78SyncState() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            this.content0x78WorkingMode = "Working mode: OFF";
        } else if (i == 1) {
            this.content0x78WorkingMode = "Working mode: ON";
        } else if (i == 2) {
            this.content0x78WorkingMode = "Working mode: MEDIA";
        } else if (i == 3) {
            this.content0x78WorkingMode = "Working mode: PHONE";
        }
        if (DataHandleUtils.getBoolBit0(iArr[3])) {
            this.icon1 = R.drawable.ford_sync_icon;
        } else {
            this.icon1 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            this.icon2 = R.drawable.ford_sync_icon_204;
        } else {
            this.icon2 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            this.icon3 = R.drawable.ford_sync_icon_93;
        } else {
            this.icon3 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            this.icon4 = R.drawable.ford_sync_icon_17;
        } else {
            this.icon4 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            this.icon5 = R.drawable.ford_sync_icon_41;
        } else {
            this.icon5 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            this.icon6 = R.drawable.ford_sync_keyboard_info;
        } else {
            this.icon6 = R.drawable.ford_sync_null;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 0) {
            this.icon7 = R.drawable.ford_sync_icon_103;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 1) {
            this.icon7 = R.drawable.ford_sync_icon_104;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 2) {
            this.icon7 = R.drawable.ford_sync_icon_105;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 3) {
            this.icon7 = R.drawable.ford_sync_icon_106;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 4 || DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 5) {
            this.icon7 = R.drawable.ford_sync_icon_107;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 0) {
            this.icon8 = R.drawable.ford_sync_icon_97;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 1) {
            this.icon8 = R.drawable.ford_sync_icon_98;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 2) {
            this.icon8 = R.drawable.ford_sync_icon_99;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 3) {
            this.icon8 = R.drawable.ford_sync_icon_100;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 4 || DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 5) {
            this.icon8 = R.drawable.ford_sync_icon_101;
        }
        updateSYNC();
    }

    public final String getContentStr0x72() {
        return this.contentStr0x72;
    }

    public final void setContentStr0x72(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contentStr0x72 = str;
    }

    private final void set0x72SyncTimeInfo() {
        this.nowIndex = 3;
        this.contentStr0x72 = String.valueOf(getAsciiStr(this.mCanBusByte));
        updateSYNC();
    }

    public final String getContentStr0x71() {
        return this.contentStr0x71;
    }

    public final void setContentStr0x71(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contentStr0x71 = str;
    }

    private final void set0x71SyncNextStr() {
        this.nowIndex = 2;
        this.contentStr0x71 = String.valueOf(getAsciiStr(this.mCanBusByte));
        updateSYNC();
    }

    public final String getContentStr0x70() {
        return this.contentStr0x70;
    }

    public final void setContentStr0x70(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contentStr0x70 = str;
    }

    private final void set0x70SyncLastStr() {
        this.nowIndex = 1;
        this.contentStr0x70 = String.valueOf(getAsciiStr(this.mCanBusByte));
        updateSYNC();
    }

    private final void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusByte));
    }

    private final void set0x24BasicInfo() {
        if (isDoorInfoChange(this.mCanBusInfoInt[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
        updateReverseGear(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
        updateHandbrakeState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
        forceReverse(this.mContext, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
    }

    private final void set0x23FrontRadar() {
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private final void set0x22RearRadar() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private final void set0x21AirInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 4);
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + getTempUnitF(this.mContext));
        } else {
            updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + getTempUnitC(this.mContext));
        }
        this.mCanBusInfoInt[7] = 0;
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_temperature = getRearTemperature(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4));
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3);
        GeneralAirData.rear_power = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
        if (isRearAirChange(this.mCanBusInfoInt[8])) {
            updateAirActivity(this.mContext, 1002);
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private final void set0x20SwcInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 61) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_TIME, iArr[3]);
            return;
        }
        if (i == 63) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 111) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i == 134) {
            realKeyLongClick1(this.mContext, 95, iArr[3]);
            return;
        }
        if (i == 86) {
            realKeyLongClick1(this.mContext, 94, iArr[3]);
            return;
        }
        if (i != 87) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 136, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 128, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 14:
                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                            break;
                        case 15:
                            realKeyLongClick1(this.mContext, 46, iArr[3]);
                            break;
                        case 16:
                            realKeyLongClick1(this.mContext, 47, iArr[3]);
                            break;
                        case 17:
                            realKeyLongClick1(this.mContext, 48, iArr[3]);
                            break;
                        case 18:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                        default:
                            switch (i) {
                                case 32:
                                    realKeyLongClick1(this.mContext, 42, iArr[3]);
                                    break;
                                case 33:
                                    realKeyLongClick1(this.mContext, 33, iArr[3]);
                                    break;
                                case 34:
                                    realKeyLongClick1(this.mContext, 34, iArr[3]);
                                    break;
                                case 35:
                                    realKeyLongClick1(this.mContext, 35, iArr[3]);
                                    break;
                                case 36:
                                    realKeyLongClick1(this.mContext, 36, iArr[3]);
                                    break;
                                case 37:
                                    realKeyLongClick1(this.mContext, 37, iArr[3]);
                                    break;
                                case 38:
                                    realKeyLongClick1(this.mContext, 38, iArr[3]);
                                    break;
                                case 39:
                                    realKeyLongClick1(this.mContext, 39, iArr[3]);
                                    break;
                                case 40:
                                    realKeyLongClick1(this.mContext, 40, iArr[3]);
                                    break;
                                case 41:
                                    realKeyLongClick1(this.mContext, 41, iArr[3]);
                                    break;
                                case 42:
                                    realKeyLongClick1(this.mContext, 63, iArr[3]);
                                    break;
                                case 43:
                                    realKeyLongClick1(this.mContext, 13, iArr[3]);
                                    break;
                                default:
                                    switch (i) {
                                        case 51:
                                            realKeyLongClick1(this.mContext, HotKeyConstant.K_CARPLAY_SIRI, iArr[3]);
                                            break;
                                        case 52:
                                            realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_RADIO, iArr[3]);
                                            break;
                                        case 53:
                                            realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_MEDIA, iArr[3]);
                                            break;
                                        case 54:
                                            realKeyLongClick1(this.mContext, 141, iArr[3]);
                                            break;
                                        case 55:
                                            realKeyLongClick1(this.mContext, 151, iArr[3]);
                                            break;
                                        case 56:
                                            realKeyLongClick1(this.mContext, 3, iArr[3]);
                                            break;
                                        case 57:
                                            realKeyLongClick1(this.mContext, 15, iArr[3]);
                                            break;
                                        default:
                                            switch (i) {
                                                case 72:
                                                    realKeyLongClick1(this.mContext, 49, iArr[3]);
                                                    break;
                                                case 73:
                                                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                                                    break;
                                                case 74:
                                                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                                                    break;
                                                case 75:
                                                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                                                    break;
                                                case 76:
                                                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 82:
                                                            realKeyLongClick1(this.mContext, 206, iArr[3]);
                                                            break;
                                                        case 83:
                                                            realKeyLongClick1(this.mContext, HotKeyConstant.K_NEXT_HANGUP, iArr[3]);
                                                            break;
                                                        case 84:
                                                            realKeyLongClick1(this.mContext, 31, iArr[3]);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 89:
                                                                    realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                                                                    break;
                                                                case 90:
                                                                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                                                                    break;
                                                                case 91:
                                                                    realKeyLongClick1(this.mContext, 57, iArr[3]);
                                                                    break;
                                                                case 92:
                                                                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                                                                    break;
                                                                case 93:
                                                                    realKeyLongClick1(this.mContext, 94, iArr[3]);
                                                                    break;
                                                                case 94:
                                                                    realKeyLongClick1(this.mContext, 91, iArr[3]);
                                                                    break;
                                                                case 95:
                                                                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                                                                            realKeyLongClick1(this.mContext, 7, iArr[3]);
                                                                            break;
                                                                        case 241:
                                                                            realKeyLongClick1(this.mContext, 8, iArr[3]);
                                                                            break;
                                                                        case 242:
                                                                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                                                                            break;
                                                                        case 243:
                                                                            realKeyLongClick1(this.mContext, 46, iArr[3]);
                                                                            break;
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 58, iArr[3]);
    }

    private final void set0x14BackLightInfo() {
        if (isBacklightInfoChange()) {
            setBacklightLevel((this.mCanBusInfoInt[2] / 51) + 1);
        }
    }

    private final void set0x16SpeedInfo() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            DecimalFormat decimalFormat = this.df_2Integer;
            int[] iArr = this.mCanBusInfoInt;
            updateSpeedInfo(Integer.parseInt(decimalFormat.format(getMsbLsbResult(iArr[3], iArr[2]) * 1.6d)));
        } else {
            int[] iArr2 = this.mCanBusInfoInt;
            updateSpeedInfo(getMsbLsbResult(iArr2[3], iArr2[2]));
        }
    }

    private final String getTemperature(int tem) {
        if (tem == 0) {
            return "LOW";
        }
        if (tem == 127) {
            return "HI";
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            return (tem / 2.0f) + ' ' + getTempUnitF(this.mContext);
        }
        return (tem / 2.0f) + ' ' + getTempUnitC(this.mContext);
    }

    private final String getRearTemperature(int tem) {
        return tem != 0 ? tem != 4 ? tem != 8 ? tem + "LEVEL" : "HI" : "MID" : "LOW";
    }

    private final String getFrequencyState() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            DecimalFormat decimalFormat = this.df_2float;
            int[] iArr = this.mCanBusInfoInt;
            return sb.append(decimalFormat.format(Float.valueOf(getMsbLsbResult(iArr[4], iArr[5]) / 100.0f))).append("MHZ").toString();
        }
        if (i == 1) {
            StringBuilder sb2 = new StringBuilder();
            DecimalFormat decimalFormat2 = this.df_2float;
            int[] iArr2 = this.mCanBusInfoInt;
            return sb2.append(decimalFormat2.format(Float.valueOf(getMsbLsbResult(iArr2[4], iArr2[5]) / 100.0f))).append("MHZ").toString();
        }
        if (i == 2) {
            StringBuilder sb3 = new StringBuilder();
            DecimalFormat decimalFormat3 = this.df_2float;
            int[] iArr3 = this.mCanBusInfoInt;
            return sb3.append(decimalFormat3.format(Float.valueOf(getMsbLsbResult(iArr3[4], iArr3[5]) / 100.0f))).append("MHZ").toString();
        }
        if (i == 3) {
            StringBuilder sb4 = new StringBuilder();
            DecimalFormat decimalFormat4 = this.df_2Integer;
            int[] iArr4 = this.mCanBusInfoInt;
            return sb4.append(decimalFormat4.format(Integer.valueOf(getMsbLsbResult(iArr4[4], iArr4[5])))).append("KHZ").toString();
        }
        if (i != 4) {
            return "NONE";
        }
        StringBuilder sb5 = new StringBuilder();
        DecimalFormat decimalFormat5 = this.df_2Integer;
        int[] iArr5 = this.mCanBusInfoInt;
        return sb5.append(decimalFormat5.format(Integer.valueOf(getMsbLsbResult(iArr5[4], iArr5[5])))).append("KHZ").toString();
    }

    private final String getRunningState(int i) {
        if (i == 0) {
            Context context = this.mContext;
            if (context != null) {
                return context.getString(R.string._432_radio_State1);
            }
            return null;
        }
        if (i == 1) {
            Context context2 = this.mContext;
            if (context2 != null) {
                return context2.getString(R.string._432_radio_State2);
            }
            return null;
        }
        if (i != 2) {
            return "NONE";
        }
        Context context3 = this.mContext;
        if (context3 != null) {
            return context3.getString(R.string._432_radio_State3);
        }
        return null;
    }

    private final String getCdState(int i) {
        switch (i) {
            case 1:
                Context context = this.mContext;
                if (context != null) {
                    return context.getString(R.string._432_CD_state1);
                }
                return null;
            case 2:
                Context context2 = this.mContext;
                if (context2 != null) {
                    return context2.getString(R.string._432_CD_state2);
                }
                return null;
            case 3:
                Context context3 = this.mContext;
                if (context3 != null) {
                    return context3.getString(R.string._432_CD_state3);
                }
                return null;
            case 4:
                Context context4 = this.mContext;
                if (context4 != null) {
                    return context4.getString(R.string._432_CD_state4);
                }
                return null;
            case 5:
                Context context5 = this.mContext;
                if (context5 != null) {
                    return context5.getString(R.string._432_CD_state5);
                }
                return null;
            case 6:
                Context context6 = this.mContext;
                if (context6 != null) {
                    return context6.getString(R.string._432_CD_state6);
                }
                return null;
            default:
                return "NONE";
        }
    }

    private final String getCdPlayState(int i) {
        if (i == 1) {
            Context context = this.mContext;
            if (context != null) {
                return context.getString(R.string._432_CD_state7);
            }
            return null;
        }
        if (i == 2) {
            Context context2 = this.mContext;
            if (context2 != null) {
                return context2.getString(R.string._432_CD_state8);
            }
            return null;
        }
        if (i == 3) {
            Context context3 = this.mContext;
            if (context3 != null) {
                return context3.getString(R.string._432_CD_state9);
            }
            return null;
        }
        if (i == 4) {
            Context context4 = this.mContext;
            if (context4 != null) {
                return context4.getString(R.string._432_CD_state10);
            }
            return null;
        }
        if (i != 5) {
            return "NONE";
        }
        Context context5 = this.mContext;
        if (context5 != null) {
            return context5.getString(R.string._432_CD_state11);
        }
        return null;
    }

    private final String getPlayMode() {
        return (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]) ? "【Repeat：ON】" : "【Repeat：OFF】") + (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]) ? "【Random：ON】" : "【Random：OFF】") + (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]) ? "【Quick browse：ON】" : "【Quick browse：OFF】");
    }

    private final String getEqData5Bit7Info(boolean boolBit7) {
        if (boolBit7) {
            Context context = this.mContext;
            if (context != null) {
                return context.getString(R.string._432_EQ_state1);
            }
            return null;
        }
        Context context2 = this.mContext;
        if (context2 != null) {
            return context2.getString(R.string._432_EQ_state2);
        }
        return null;
    }

    public final DecimalFormat getDf_2Integer() {
        return this.df_2Integer;
    }

    public final DecimalFormat getDf_2float() {
        return this.df_2float;
    }

    public final int blockBit(int canBusInfo, int blockBit) {
        if (blockBit == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(canBusInfo, 1, 7) & 255) << 1;
        }
        if (blockBit == 7) {
            return DataHandleUtils.getIntFromByteWithBit(canBusInfo, 0, 7);
        }
        int i = blockBit + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(canBusInfo, i, 7 - blockBit) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(canBusInfo, 0, blockBit) & 255) & 255) ^ (intFromByteWithBit << i);
    }

    public final String getAsciiStr(byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        int length = canbusInfo.length - 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = canbusInfo[i + 2];
        }
        return new String(bArr, Charsets.UTF_8);
    }

    private final int getSyncIconResId(Context context, String name) {
        int imgIdByResId = CommUtil.getImgIdByResId(context, name);
        return imgIdByResId == R.drawable.music_photo_null ? R.drawable.ford_sync_icon_null : imgIdByResId;
    }

    private final int getStringIdByName(Context context, String name) {
        return CommUtil.getStrIdByResId(context, name);
    }

    public final int getNowData6() {
        return this.nowData6;
    }

    public final void setNowData6(int i) {
        this.nowData6 = i;
    }

    private final boolean isRearAirChange(int data6) {
        if (this.nowData6 == data6) {
            return false;
        }
        this.nowData6 = data6;
        return true;
    }

    public final int getNow0x24Data0() {
        return this.now0x24Data0;
    }

    public final void setNow0x24Data0(int i) {
        this.now0x24Data0 = i;
    }

    private final boolean isDoorInfoChange(int data0) {
        if (this.now0x24Data0 == data0) {
            return false;
        }
        this.now0x24Data0 = data0;
        return true;
    }

    public final int[] getMBacklightInfo() {
        return this.mBacklightInfo;
    }

    public final void setMBacklightInfo(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mBacklightInfo = iArr;
    }

    private final boolean isBacklightInfoChange() {
        if (Arrays.equals(this.mBacklightInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mBacklightInfo = iArrCopyOf;
        return true;
    }

    public final int[] getMAirData() {
        return this.mAirData;
    }

    public final void setMAirData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData = iArr;
    }

    private final boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    public final int[] getMRearRadarData() {
        return this.mRearRadarData;
    }

    public final void setMRearRadarData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mRearRadarData = iArr;
    }

    private final boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mRearRadarData = iArrCopyOf;
        return true;
    }

    public final int[] getMFrontRadarData() {
        return this.mFrontRadarData;
    }

    public final void setMFrontRadarData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mFrontRadarData = iArr;
    }

    private final boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mFrontRadarData = iArrCopyOf;
        return true;
    }

    public final int[] getM0x79Data() {
        return this.m0x79Data;
    }

    public final void setM0x79Data(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.m0x79Data = iArr;
    }

    private final boolean is0x79DataNoChange() {
        if (Arrays.equals(this.m0x79Data, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.m0x79Data = iArrCopyOf;
        return false;
    }

    public final byte[] getMTrackData() {
        return this.mTrackData;
    }

    public final void setMTrackData(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mTrackData = bArr;
    }

    private final boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusByte)) {
            return true;
        }
        byte[] bArr = this.mCanBusByte;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(mCanBusByte, mCanBusByte.size)");
        this.mTrackData = bArrCopyOf;
        return false;
    }
}
