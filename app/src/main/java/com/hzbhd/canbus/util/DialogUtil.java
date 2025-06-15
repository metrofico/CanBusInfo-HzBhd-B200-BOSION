package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;

/* loaded from: classes2.dex */
public class DialogUtil {
    private static DialogUtil mDialogUtil;
    private final String TAG = "DialogUtil";
    private AlertDialog mAlertDialog;

    public interface ListDialogCallBak {
        void callBack(int i);
    }

    public interface SeekbarDialogCallBak {
        void callBack(int i);
    }

    public interface SeekbarSetTextListener {
        String onSetText(int i);
    }

    public static DialogUtil getInstance() {
        if (mDialogUtil == null) {
            mDialogUtil = new DialogUtil();
        }
        return mDialogUtil;
    }

    public AlertDialog getAlertDialog() {
        return this.mAlertDialog;
    }

    public void showConfirmDialog(Context context, String str, final int i, final int i2, final OnConfirmDialogListener onConfirmDialogListener) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_dialog_confirm, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.tv)).setText(CommUtil.getStrByResId(context, str));
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.DialogTheme).setView(viewInflate).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.hzbhd.canbus.util.DialogUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                OnConfirmDialogListener onConfirmDialogListener2 = onConfirmDialogListener;
                if (onConfirmDialogListener2 != null) {
                    onConfirmDialogListener2.onConfirmClick(i, i2);
                }
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    public void showListDialog(Context context, String[] strArr, final int i, final ListDialogCallBak listDialogCallBak) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_dialog_list_select, (ViewGroup) null);
        final DialogListAdapter dialogListAdapter = new DialogListAdapter(context, strArr);
        ListView listView = (ListView) viewInflate.findViewById(R.id.lv);
        listView.setAdapter((ListAdapter) dialogListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.hzbhd.canbus.util.DialogUtil.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                ListDialogCallBak listDialogCallBak2 = listDialogCallBak;
                if (listDialogCallBak2 != null) {
                    listDialogCallBak2.callBack(i2);
                }
                DialogUtil.this.mAlertDialog.dismiss();
            }
        });
        listView.post(new Runnable() { // from class: com.hzbhd.canbus.util.DialogUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DialogUtil.lambda$showListDialog$0(dialogListAdapter, i);
            }
        });
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.DialogTheme).setView(viewInflate).create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    static /* synthetic */ void lambda$showListDialog$0(DialogListAdapter dialogListAdapter, int i) {
        dialogListAdapter.setSelectedIndex(i);
        dialogListAdapter.notifyDataSetChanged();
    }

    public void showSeekDialog(Context context, final int i, int i2, int i3, final boolean z, final SeekbarDialogCallBak seekbarDialogCallBak, final SeekbarSetTextListener seekbarSetTextListener) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_dialog_seekbar, (ViewGroup) null);
        final TextView textView = (TextView) viewInflate.findViewById(R.id.tv_value);
        textView.setText(seekbarSetTextListener.onSetText(i3 + i));
        final SeekBar seekBar = (SeekBar) viewInflate.findViewById(R.id.seekbar);
        seekBar.setMax(i2 - i);
        seekBar.setProgress(i3);
        seekBar.setEnabled(z);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.util.DialogUtil.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i4, boolean z2) {
                SeekbarDialogCallBak seekbarDialogCallBak2;
                textView.setText(seekbarSetTextListener.onSetText(i4 + i));
                if (z || (seekbarDialogCallBak2 = seekbarDialogCallBak) == null) {
                    return;
                }
                seekbarDialogCallBak2.callBack(seekBar2.getProgress() + i);
            }
        });
        viewInflate.findViewById(R.id.ib_incs).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.DialogUtil$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                seekBar.setProgress(seekBar.getProgress() + 1);
            }
        });
        viewInflate.findViewById(R.id.ib_decs).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.DialogUtil$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                seekBar.setProgress(seekBar.getProgress() - 1);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setView(viewInflate);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.hzbhd.canbus.util.DialogUtil$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i4) {
                if (seekbarDialogCallBak != null) {
                    seekbarDialogCallBak.callBack(seekBar.getProgress() + i);
                }
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

}
