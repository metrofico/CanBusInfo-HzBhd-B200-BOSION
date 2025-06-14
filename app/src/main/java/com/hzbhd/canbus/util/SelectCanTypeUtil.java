package com.hzbhd.canbus.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgService;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.UPDProgressActivity;
import com.hzbhd.canbus.adapter.SelectedListAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.McuVehicleConfig;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.softwinner.SystemMix;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes2.dex */
public class SelectCanTypeUtil {
    private static int mSelectPosition;

    public static void showDialogToUpdate(final Activity activity, final CanTypeAllEntity canTypeAllEntity, String str) {
        String str2;
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.layout_dialog_switch_can, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_switch_can_tis);
        ListView listView = (ListView) viewInflate.findViewById(R.id.lv);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        } else {
            Locale locale = Locale.getDefault();
            if (com.hzbhd.util.LogUtil.log5()) {
                com.hzbhd.util.LogUtil.d("showDialogToUpdate(): ----" + locale);
            }
            if (locale.toString().equals("en_US")) {
                str2 = activity.getResources().getString(R.string.switch_to) + " " + canTypeAllEntity.getCan_type_id() + "  " + canTypeAllEntity.getEnglish_protocol_company() + "  " + canTypeAllEntity.getEnglish_car_category();
            } else {
                str2 = activity.getResources().getString(R.string.switch_to) + " " + canTypeAllEntity.getCan_type_id() + "  " + canTypeAllEntity.getProtocol_company() + "  " + canTypeAllEntity.getCar_category();
            }
            textView.setText(str2);
        }
        final ArrayList<CanTypeAllEntity> list = CanTypeUtil.INSTANCE.getCanType(canTypeAllEntity.getCan_type_id()).getList();
        if (list == null) {
            LogUtil.showLog("list from CanTypeDao is null, can type id:" + canTypeAllEntity.getCan_type_id());
            return;
        }
        if (canTypeAllEntity.getCan_type_id() == getCurrentCanTypeId(activity)) {
            mSelectPosition = CanbusConfig.INSTANCE.getSelectCarPosition();
        } else {
            mSelectPosition = 0;
        }
        final SelectedListAdapter selectedListAdapter = new SelectedListAdapter(activity, list);
        selectedListAdapter.setSelectPosition(mSelectPosition);
        listView.setAdapter((ListAdapter) selectedListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.hzbhd.canbus.util.SelectCanTypeUtil.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int unused = SelectCanTypeUtil.mSelectPosition = i;
                selectedListAdapter.setSelectPosition(i);
                selectedListAdapter.notifyDataSetChanged();
            }
        });
        new AlertDialog.Builder(activity, R.style.DialogTheme).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.hzbhd.canbus.util.SelectCanTypeUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean z = canTypeAllEntity.getCan_type_id() == SelectCanTypeUtil.getCurrentCanTypeId(activity);
                if (z && CanbusConfig.INSTANCE.getSelectCarPosition() == SelectCanTypeUtil.mSelectPosition) {
                    return;
                }
                CanTypeAllEntity canTypeAllEntity2 = (CanTypeAllEntity) list.get(SelectCanTypeUtil.mSelectPosition);
                CanbusConfig.INSTANCE.setEachId(canTypeAllEntity2.getEach_can_id());
                CanbusConfig.INSTANCE.setDifferentId(canTypeAllEntity2.getCan_different_id());
                CanbusConfig.INSTANCE.setSelectCarPosition(SelectCanTypeUtil.mSelectPosition);
                CanbusConfig.INSTANCE.setIsShowApp(canTypeAllEntity2.getIs_show_app() == 1);
                if (z) {
                    if (com.hzbhd.util.LogUtil.log5()) {
                        com.hzbhd.util.LogUtil.d("can type id equal");
                    }
                    Toast.makeText(activity, R.string.have_update, 1).show();
                    System.exit(0);
                    return;
                }
                if (com.hzbhd.util.LogUtil.log5()) {
                    com.hzbhd.util.LogUtil.d("is app handle key equal :" + canTypeAllEntity.getIs_app_handle_key());
                }
                if (canTypeAllEntity.getIs_app_handle_key() == 1) {
                    Log.i("SelectCanTypeUtil", "onClick: [" + McuVehicleConfig.INSTANCE.getBean() + "]");
                    McuVehicleConfig.INSTANCE.getBean().setBrand((canTypeAllEntity.getCan_type_id() >> 8) & 255);
                    McuVehicleConfig.INSTANCE.getBean().setModel(canTypeAllEntity.getCan_type_id() & 255);
                    McuVehicleConfig.INSTANCE.getBean().setBaud_rate(canTypeAllEntity.getBaud_rate());
                    McuVehicleConfig.INSTANCE.getBean().setCan_protocol(canTypeAllEntity.getPack_type());
                    CanbusConfig.INSTANCE.setBaud_Rate(canTypeAllEntity.getBaud_rate());
                    CanbusConfig.INSTANCE.setCanPackType(canTypeAllEntity.getPack_type());
                    CanbusConfig.INSTANCE.setCanType(canTypeAllEntity.getCan_type_id());
                    McuVehicleConfig.INSTANCE.setMcu();
                    SelectCanTypeUtil.setSpecifyCanTypeIdAndRestMpu(canTypeAllEntity.getCan_type_id());
                    Toast.makeText(activity, R.string.reboot_tips, 1).show();
                    return;
                }
                if (com.hzbhd.util.LogUtil.log5()) {
                    com.hzbhd.util.LogUtil.d("<confirm> " + canTypeAllEntity.getCan_type_id());
                }
                Toast.makeText(activity, "Unsupported ID", 0).show();
            }
        }).setView(viewInflate).create().show();
    }

    public static void resetApp(Application application) {
        LogUtil.showLog("resetApp");
        ((MyApplication) application).removeAllActivity();
        MsgMgrFactory.setThisNull();
        UiMgrFactory.setThisNull();
        MyApplication.IS_SET = false;
        FutureUtil.instance.initCanbusAmp(application);
        Settings.System.putString(application.getContentResolver(), BodaSysContant.Can.CanSystemVersion, "--");
        MsgMgrFactory.getCanMsgMgrUtil(application).initCommand(application);
        application.stopService(new Intent(application, (Class<?>) BackCameraUiService.class));
        LogUtil.showLog("stop BackCameraUiService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setSpecifyCanTypeIdAndRestMpu(int i) {
        LogUtil.showLog("切换can重启");
        if (setFactoryCanType(i)) {
            SystemMix.bhd_sync();
            SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
        }
    }

    public static void setCanTypeNotResetMpu(int i) {
        LogUtil.showLog("切换can不重启");
        setFactoryCanType(i);
        CanbusMsgService.mIsHaveDealWithReportCanTypeId = false;
        CanbusMsgService.mIsNeedSystemExit = true;
    }

    public static int getCurrentCanTypeId(Context context) {
        return CanbusConfig.INSTANCE.getCanType();
    }

    private static void goUpdProgress(Context context, int i, boolean z) {
        LogUtil.showLog("goUpdProgress");
        UPDProgressActivity.openActivity(context, i, z);
    }

    private static boolean setFactoryCanType(int i) {
        LogUtil.showLog("setFactoryCanType：" + i);
        CanbusConfig.INSTANCE.setCanType(i);
        return true;
    }

    private static void saveCanDiffId(Context context, int i) {
        CanBus.INSTANCE.setDifferentId(i);
        LogUtil.showLog("saveCanDiffId:" + i);
        SharePreUtil.setIntValue(context, Constant.SHARE_PRE_CAN_DIFFERENT_ID, i);
    }

    public static int getCurrentCanDiffId() {
        return CanbusConfig.INSTANCE.getDifferentId();
    }

    public static void disableApp(Context context, ComponentName componentName) {
        Log.i("CanbusInfo", "CANBUS unShow disableApp: " + componentName);
        try {
            context.getPackageManager().setComponentEnabledSetting(componentName, 2, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableApp(Context context, ComponentName componentName) {
        Log.i("CanbusInfo", "CANBUS show enableApp: " + componentName);
        try {
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
