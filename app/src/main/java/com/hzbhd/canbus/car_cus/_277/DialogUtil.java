package com.hzbhd.canbus.car_cus._277;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._277.adapter.DiagnosisLvAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class DialogUtil {
    private static DialogUtil mDialogUtil = null;
    public static boolean mHasAdded = false;
    private AlertDialog mAlertDialog;
    private boolean mCanCancel;
    private List<DiagnosisEntity> mDiagnosisList;
    private DiagnosisLvAdapter mDiagnosisLvAdapter;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private View mView;
    private WindowManager mWindowManager;

    public interface ListDialogCallBak {
        void callBack(int i);
    }

    public static DialogUtil getInstance() {
        if (mDialogUtil == null) {
            mDialogUtil = new DialogUtil();
        }
        return mDialogUtil;
    }

    public void showListDialog(Context context, String[] strArr, final ListDialogCallBak listDialogCallBak) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._277_layout_dialog_list_select, (ViewGroup) null);
        ListView listView = (ListView) viewInflate.findViewById(R.id.lv);
        listView.setAdapter((ListAdapter) new ArrayAdapter(context, R.layout._277_layout_dialog_list_item, strArr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._277.DialogUtil.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ListDialogCallBak listDialogCallBak2 = listDialogCallBak;
                if (listDialogCallBak2 != null) {
                    listDialogCallBak2.callBack(i);
                }
                DialogUtil.this.mAlertDialog.dismiss();
            }
        });
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.DialogTheme).setView(viewInflate).create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    @SuppressLint("WrongConstant")
    public void showDiagnosisWindow(Context context, List<DiagnosisEntity> list, boolean z) {
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        layoutParams.flags = 16777512;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (this.mDiagnosisList == null) {
            this.mDiagnosisList = new ArrayList();
        }
        this.mDiagnosisList.clear();
        this.mDiagnosisList.addAll(list);
        if (mHasAdded) {
            if (z) {
                startDiagnosisCheck();
            }
            this.mDiagnosisLvAdapter.notifyDataSetChanged();
        } else {
            if (this.mView == null) {
                this.mView = LayoutInflater.from(context).inflate(R.layout._277_layout_diagnosis_alert, (ViewGroup) null);
                this.mView.findViewById(R.id.close_alert).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._277.DialogUtil.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        int id = view.getId();
                        if (id == R.id.close_alert || id == R.id.rl_diagnosis_alert) {
                            DialogUtil.this.finishTimer();
                            DialogUtil.this.mWindowManager.removeView(DialogUtil.this.mView);
                            DialogUtil.mHasAdded = false;
                        }
                    }
                });
            }
            RecyclerView recyclerView = (RecyclerView) this.mView.findViewById(R.id.rv_diagnosis_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            DiagnosisLvAdapter diagnosisLvAdapter = new DiagnosisLvAdapter(context, this.mDiagnosisList);
            this.mDiagnosisLvAdapter = diagnosisLvAdapter;
            recyclerView.setAdapter(diagnosisLvAdapter);
            this.mWindowManager.addView(this.mView, layoutParams);
            startDiagnosisCheck();
            mHasAdded = true;
        }
        if (list.size() == 0 && mHasAdded) {
            this.mWindowManager.removeView(this.mView);
            mHasAdded = false;
        }
    }

    private void startTimer(long j) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j);
        Log.i("ljq", "startTimer: ");
    }

    private void startTiemr(long j, int i) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
        Log.i("ljq", "finishTimer: ");
    }

    private void startDiagnosisCheck() {
        finishTimer();
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car_cus._277.DialogUtil.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (DialogUtil.mHasAdded) {
                    DialogUtil.this.mWindowManager.removeView(DialogUtil.this.mView);
                    DialogUtil.mHasAdded = false;
                }
                DialogUtil.this.finishTimer();
            }
        };
        startTimer(5000L);
    }
}
