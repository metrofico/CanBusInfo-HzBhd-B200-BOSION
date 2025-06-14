package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import java.util.List;

/* loaded from: classes2.dex */
public class FileListView extends LinearLayout implements DvrDataInterface {
    private ActionCallback actionCallback;
    private FileItemView file_1;
    private FileItemView file_2;
    private FileItemView file_3;
    private FileItemView file_4;
    private FileItemView file_5;
    private FileItemView file_6;
    private KeyButton lock_btn;
    private KeyButton next_btn;
    private KeyButton prev_btn;
    private List<Integer> selectedFiles;
    private View view;

    public FileListView(Context context) {
        this(context, null);
    }

    public FileListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FileListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_file_list, (ViewGroup) this, true);
        this.view = viewInflate;
        this.prev_btn = (KeyButton) viewInflate.findViewById(R.id.prev_btn);
        this.lock_btn = (KeyButton) this.view.findViewById(R.id.lock_btn);
        this.next_btn = (KeyButton) this.view.findViewById(R.id.next_btn);
        this.prev_btn.setTextValue("上一页");
        this.lock_btn.setTextValue("加锁/解锁");
        this.next_btn.setTextValue("下一页");
        initFileView();
    }

    public void getAction(final ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
        this.prev_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("PREV");
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
            }
        });
        this.lock_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.2
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("ADD_LOCK");
            }
        });
        this.next_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.3
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("NEXT");
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
            }
        });
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateUi();
        DvrObserver.getInstance().register(this);
    }

    private void initFileView() {
        this.file_1 = (FileItemView) this.view.findViewById(R.id.file_1);
        this.file_2 = (FileItemView) this.view.findViewById(R.id.file_2);
        this.file_3 = (FileItemView) this.view.findViewById(R.id.file_3);
        this.file_4 = (FileItemView) this.view.findViewById(R.id.file_4);
        this.file_5 = (FileItemView) this.view.findViewById(R.id.file_5);
        this.file_6 = (FileItemView) this.view.findViewById(R.id.file_6);
        this.file_1.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.4
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file1NameList));
                FileListView.this.saveNowFile(DvrData.file1NameList, true);
                FileListView.this.file_1.setSelect(true);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
        this.file_2.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.5
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file2NameList));
                FileListView.this.saveNowFile(DvrData.file2NameList, true);
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(true);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
        this.file_3.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.6
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file3NameList));
                FileListView.this.saveNowFile(DvrData.file3NameList, true);
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(true);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
        this.file_4.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.7
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file4NameList));
                FileListView.this.saveNowFile(DvrData.file4NameList, true);
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(true);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(false);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
        this.file_5.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.8
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file5NameList));
                FileListView.this.saveNowFile(DvrData.file5NameList, true);
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(true);
                FileListView.this.file_6.setSelect(false);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
        this.file_6.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FileListView.9
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                DvrSender.send(FileListView.this.getBytes(DvrData.file6NameList));
                FileListView.this.saveNowFile(DvrData.file6NameList, true);
                FileListView.this.file_1.setSelect(false);
                FileListView.this.file_2.setSelect(false);
                FileListView.this.file_3.setSelect(false);
                FileListView.this.file_4.setSelect(false);
                FileListView.this.file_5.setSelect(false);
                FileListView.this.file_6.setSelect(true);
                FileListView.this.actionCallback.toDo("PLAY");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getBytes(List<Integer> list) {
        return new byte[]{114, -1, (byte) list.get(0).intValue(), (byte) list.get(1).intValue(), (byte) list.get(2).intValue(), (byte) list.get(3).intValue(), (byte) list.get(4).intValue(), (byte) list.get(5).intValue(), (byte) list.get(6).intValue(), (byte) list.get(7).intValue(), (byte) list.get(8).intValue(), (byte) list.get(9).intValue(), (byte) list.get(10).intValue(), (byte) list.get(11).intValue(), (byte) list.get(12).intValue(), (byte) list.get(13).intValue(), (byte) list.get(14).intValue(), (byte) list.get(15).intValue(), (byte) list.get(16).intValue(), (byte) list.get(17).intValue(), (byte) list.get(18).intValue()};
    }

    public void saveNowFile(List<Integer> list, boolean z) {
        if (z) {
            this.selectedFiles = list;
            return;
        }
        this.selectedFiles = null;
        this.file_1.setSelect(false);
        this.file_2.setSelect(false);
        this.file_3.setSelect(false);
        this.file_4.setSelect(false);
        this.file_5.setSelect(false);
        this.file_6.setSelect(false);
    }

    private void updateUi() {
        Log.d("fxHouError", "刷新数据BBBBBBBBBB");
        boolean zEquals = DvrData.file1Type.equals("VIDEO");
        int i = R.drawable.__448___ph8_dvr_video;
        int i2 = zEquals ? R.drawable.__448___ph8_dvr_video : R.drawable.__448___ph8_dvr_photo;
        boolean zEquals2 = DvrData.file1Lock.equals("UNLOCK");
        int i3 = R.drawable.__448___ph8_dvr_lock;
        this.file_1.setData(i2, makeFileName(DvrData.file1NameList, 1), zEquals2 ? R.drawable.__448___ph8_dvr_lock : R.color.transparent);
        this.file_2.setData(DvrData.file2Type.equals("VIDEO") ? R.drawable.__448___ph8_dvr_video : R.drawable.__448___ph8_dvr_photo, makeFileName(DvrData.file2NameList, 2), DvrData.file2Lock.equals("UNLOCK") ? R.drawable.__448___ph8_dvr_lock : R.color.transparent);
        this.file_3.setData(DvrData.file3Type.equals("VIDEO") ? R.drawable.__448___ph8_dvr_video : R.drawable.__448___ph8_dvr_photo, makeFileName(DvrData.file3NameList, 3), DvrData.file3Lock.equals("UNLOCK") ? R.drawable.__448___ph8_dvr_lock : R.color.transparent);
        this.file_4.setData(DvrData.file4Type.equals("VIDEO") ? R.drawable.__448___ph8_dvr_video : R.drawable.__448___ph8_dvr_photo, makeFileName(DvrData.file4NameList, 4), DvrData.file4Lock.equals("UNLOCK") ? R.drawable.__448___ph8_dvr_lock : R.color.transparent);
        this.file_5.setData(DvrData.file5Type.equals("VIDEO") ? R.drawable.__448___ph8_dvr_video : R.drawable.__448___ph8_dvr_photo, makeFileName(DvrData.file5NameList, 5), DvrData.file5Lock.equals("UNLOCK") ? R.drawable.__448___ph8_dvr_lock : R.color.transparent);
        if (!DvrData.file6Type.equals("VIDEO")) {
            i = R.drawable.__448___ph8_dvr_photo;
        }
        if (!DvrData.file6Lock.equals("UNLOCK")) {
            i3 = R.color.transparent;
        }
        this.file_6.setData(i, makeFileName(DvrData.file6NameList, 6), i3);
        if (DvrData.numberOfFiles == 0) {
            this.file_1.setVisibility(8);
            this.file_2.setVisibility(8);
            this.file_3.setVisibility(8);
            this.file_4.setVisibility(8);
            this.file_5.setVisibility(8);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 1) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(8);
            this.file_3.setVisibility(8);
            this.file_4.setVisibility(8);
            this.file_5.setVisibility(8);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 2) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(0);
            this.file_3.setVisibility(8);
            this.file_4.setVisibility(8);
            this.file_5.setVisibility(8);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 3) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(0);
            this.file_3.setVisibility(0);
            this.file_4.setVisibility(8);
            this.file_5.setVisibility(8);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 4) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(0);
            this.file_3.setVisibility(0);
            this.file_4.setVisibility(0);
            this.file_5.setVisibility(8);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 5) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(0);
            this.file_3.setVisibility(0);
            this.file_4.setVisibility(0);
            this.file_5.setVisibility(0);
            this.file_6.setVisibility(8);
        } else if (DvrData.numberOfFiles == 6) {
            this.file_1.setVisibility(0);
            this.file_2.setVisibility(0);
            this.file_3.setVisibility(0);
            this.file_4.setVisibility(0);
            this.file_5.setVisibility(0);
            this.file_6.setVisibility(0);
        }
        this.file_1.setPlaying(isPlaying(DvrData.file1NameList, DvrData.filePlayingList));
        this.file_2.setPlaying(isPlaying(DvrData.file2NameList, DvrData.filePlayingList));
        this.file_3.setPlaying(isPlaying(DvrData.file3NameList, DvrData.filePlayingList));
        this.file_4.setPlaying(isPlaying(DvrData.file4NameList, DvrData.filePlayingList));
        this.file_5.setPlaying(isPlaying(DvrData.file5NameList, DvrData.filePlayingList));
        this.file_6.setPlaying(isPlaying(DvrData.file6NameList, DvrData.filePlayingList));
    }

    private boolean isPlaying(List<Integer> list, List<Integer> list2) {
        if (list == null || list2 == null || list2.size() != list.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e A[PHI: r8
      0x002e: PHI (r8v12 java.lang.String) = 
      (r8v1 java.lang.String)
      (r8v3 java.lang.String)
      (r8v5 java.lang.String)
      (r8v7 java.lang.String)
      (r8v9 java.lang.String)
      (r8v13 java.lang.String)
     binds: [B:48:0x0094, B:41:0x0080, B:34:0x006c, B:27:0x0058, B:20:0x0044, B:12:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String makeFileName(java.util.List<java.lang.Integer> r13, int r14) {
        /*
            Method dump skipped, instructions count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car_cus._448.view.FileListView.makeFileName(java.util.List, int):java.lang.String");
    }

    @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
    public void dataChange(String str) {
        if (str.equals(DvrMode.PLAY_BACK_MODE)) {
            updateUi();
        }
    }
}
