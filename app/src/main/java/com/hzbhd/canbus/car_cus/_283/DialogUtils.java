package com.hzbhd.canbus.car_cus._283;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter;
import com.hzbhd.canbus.car_cus._283.bean.AirSelectBean;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.bean.TmpsChooseBean;
import com.hzbhd.canbus.car_cus._283.utils.Utils;
import com.hzbhd.canbus.car_cus._283.view.CenterControlView;
import java.util.List;

/* loaded from: classes2.dex */
public class DialogUtils {
    private static PopupWindow centerControlPopupWindow;

    public static void chooseTmps(Context context, View view, List<TmpsChooseBean> list, final TmpsAdapter.OnItemClickListener onItemClickListener) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        final PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_choose_tmps, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, 0, iArr[0], iArr[1] - (view.getHeight() * 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TmpsAdapter tmpsAdapter = new TmpsAdapter(context, list);
        recyclerView.setAdapter(tmpsAdapter);
        tmpsAdapter.setOnItemClickListener(new TmpsAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter.OnItemClickListener
            public final void click(int i) {
                DialogUtils.lambda$chooseTmps$0(onItemClickListener, popupWindow, i);
            }
        });
    }

    static /* synthetic */ void lambda$chooseTmps$0(TmpsAdapter.OnItemClickListener onItemClickListener, PopupWindow popupWindow, int i) {
        if (onItemClickListener != null) {
            onItemClickListener.click(i);
        }
        popupWindow.dismiss();
    }

    public static void mainChoose(Context context, View view, List<MainChooseBean> list, final MainChooseAdapter.OnItemClickListener onItemClickListener) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        final PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_main_choose, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, 0, iArr[0], 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainChooseAdapter mainChooseAdapter = new MainChooseAdapter(context, list);
        recyclerView.setAdapter(mainChooseAdapter);
        mainChooseAdapter.setOnItemClickListener(new MainChooseAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter.OnItemClickListener
            public final void click(int i) {
                DialogUtils.lambda$mainChoose$1(onItemClickListener, popupWindow, i);
            }
        });
    }

    static /* synthetic */ void lambda$mainChoose$1(MainChooseAdapter.OnItemClickListener onItemClickListener, PopupWindow popupWindow, int i) {
        if (onItemClickListener != null) {
            onItemClickListener.click(i);
        }
        popupWindow.dismiss();
    }

    public static void mainMedia(Context context, View view, List<MainMediaBean> list, final MainMediaAdapter.OnItemClickListener onItemClickListener) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        final PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_main_media, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, 0, iArr[0], 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainMediaAdapter mainMediaAdapter = new MainMediaAdapter(context, list);
        recyclerView.setAdapter(mainMediaAdapter);
        mainMediaAdapter.setOnItemClickListener(new MainMediaAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter.OnItemClickListener
            public final void click(int i, ComponentName componentName) {
                DialogUtils.lambda$mainMedia$2(onItemClickListener, popupWindow, i, componentName);
            }
        });
    }

    static /* synthetic */ void lambda$mainMedia$2(MainMediaAdapter.OnItemClickListener onItemClickListener, PopupWindow popupWindow, int i, ComponentName componentName) {
        if (onItemClickListener != null) {
            onItemClickListener.click(i, componentName);
        }
        popupWindow.dismiss();
    }

    public static CenterControlView showCenterControl(Context context, View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        CenterControlView centerControlView = new CenterControlView(context);
        popupWindow.setContentView(centerControlView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        centerControlView.measure(0, 0);
        int measuredHeight = centerControlView.getMeasuredHeight();
        int measuredWidth = centerControlView.getMeasuredWidth();
        centerControlView.refreshUi();
        popupWindow.showAtLocation(view, 80, ((-measuredWidth) / 2) + 55, (iArr[1] - measuredHeight) + 30);
        centerControlPopupWindow = popupWindow;
        return centerControlView;
    }

    public static PopupWindow getCenterControlPopupWindow() {
        return centerControlPopupWindow;
    }

    public static void showPopupwindows(Context context, View view, List<AirSelectBean> list, String str, final AirSelectAdapter.OnItemClickListener onItemClickListener) {
        final PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_air_left, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title);
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        view.getMeasuredWidth();
        popupWindow.showAsDropDown(view, 0, -measuredHeight);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AirSelectAdapter airSelectAdapter = new AirSelectAdapter(context, list);
        recyclerView.setAdapter(airSelectAdapter);
        airSelectAdapter.setOnItemClickListener(new AirSelectAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter.OnItemClickListener
            public final void click(int i) {
                DialogUtils.lambda$showPopupwindows$3(onItemClickListener, popupWindow, i);
            }
        });
    }

    static /* synthetic */ void lambda$showPopupwindows$3(AirSelectAdapter.OnItemClickListener onItemClickListener, PopupWindow popupWindow, int i) {
        if (onItemClickListener != null) {
            onItemClickListener.click(i);
        }
        popupWindow.dismiss();
    }

    public static void showSettingDialog(Context context, View view, List<SettingDialogBean> list, int i, final SettingDialogAdapter.OnItemClickListener onItemClickListener) {
        final PopupWindow popupWindow = new PopupWindow(view, -2, -2);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_item_setting_dialog, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        int[] iArrCalculatePopWindowPos = Utils.calculatePopWindowPos(view, viewInflate);
        popupWindow.showAtLocation(viewInflate, 8388659, iArrCalculatePopWindowPos[0], iArrCalculatePopWindowPos[1]);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        SettingDialogAdapter settingDialogAdapter = new SettingDialogAdapter(context, list);
        recyclerView.setAdapter(settingDialogAdapter);
        recyclerView.scrollToPosition(i);
        settingDialogAdapter.setOnItemClickListener(new SettingDialogAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter.OnItemClickListener
            public final void onClick(int i2) {
                DialogUtils.lambda$showSettingDialog$4(onItemClickListener, popupWindow, i2);
            }
        });
    }

    static /* synthetic */ void lambda$showSettingDialog$4(SettingDialogAdapter.OnItemClickListener onItemClickListener, PopupWindow popupWindow, int i) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick(i);
        }
        popupWindow.dismiss();
    }

    public static void showTipsDialog(Context context, String str, final View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_dialog_tips, (ViewGroup) null);
        builder.setView(viewInflate);
        final AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
        Button button = (Button) viewInflate.findViewById(R.id.buttonConfirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.buttonCancel);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.DialogUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(view);
                }
                alertDialogCreate.dismiss();
            }
        });
    }
}
