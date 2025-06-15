package com.hzbhd.canbus.car._161;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car._161.Popup;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;




public final class Popup {
    private static final String TAG = "_161_Popup";
    private static final long WARNING_LOOP_INTERVAL = 3000;
    private boolean mIsShowing;
    private final WindowManager.LayoutParams mLayoutParams;
    private final ArrayList<Ops.Update> mPlayList;
    private View mView;
    private final WindowManager mWindowManager;

    /* JADX WARN: Type inference failed for: r0v7, types: [com.hzbhd.canbus.car._161.Popup$1] */
    public Popup(Context context) {

        Object systemService = context.getSystemService("window");

        this.mWindowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2002;
        layoutParams.gravity = 17;
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.mLayoutParams = layoutParams;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._161_layout_popup, (ViewGroup) null);

        this.mView = viewInflate;
        this.mPlayList = new ArrayList<>();
        final ?? r0 = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._161.Popup.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {

                super.handleMessage(msg);
                Object obj = msg.obj;
                if (Intrinsics.areEqual(obj, Ops.Show.INSTANCE)) {
                    if (Popup.this.mIsShowing) {
                        return;
                    }
                    Popup.this.mWindowManager.addView(Popup.this.mView, Popup.this.mLayoutParams);
                    Popup.this.mIsShowing = true;
                    return;
                }
                if (Intrinsics.areEqual(obj, Ops.Hide.INSTANCE)) {
                    if (Popup.this.mIsShowing) {
                        Popup.this.mWindowManager.removeView(Popup.this.mView);
                        Popup.this.mIsShowing = false;
                        return;
                    }
                    return;
                }
                if (obj instanceof Ops.Update) {
                    Object obj2 = msg.obj;

                    InfoBean bean = ((Ops.Update) obj2).getBean();
                    Popup popup = Popup.this;
                    ((ImageView) popup.mView.findViewById(R.id.iv_alert_icon)).setImageResource(bean.getImageResId());
                    ((TextView) popup.mView.findViewById(R.id.tv_alert_message)).setText(bean.getMessageResId());
                }
            }
        };
        ((ImageButton) this.mView.findViewById(R.id.ib_close)).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._161.Popup$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Popup.m206lambda3$lambda2(r0, this, view);
            }
        });
        new TimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._161.Popup$2$2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.i("_161_Popup", "run: play:" + this.this$0.mPlayList.size());
                if (this.this$0.mPlayList.size() != 0) {
                    ArrayList arrayList = this.this$0.mPlayList;
                    Popup.AnonymousClass1 anonymousClass1 = r0;
                    Message messageObtainMessage = anonymousClass1.obtainMessage();
                    ArrayList arrayList2 = arrayList;
                    messageObtainMessage.obj = CollectionsKt.first((List) arrayList2);
                    anonymousClass1.sendMessage(messageObtainMessage);
                    if (CollectionsKt.first((List) arrayList2) instanceof Popup.Ops.WarningBean) {
                        arrayList.add(CollectionsKt.first((List) arrayList2));
                    }
                    arrayList.remove(CollectionsKt.first((List) arrayList2));
                    Popup.AnonymousClass1 anonymousClass12 = r0;
                    Message messageObtainMessage2 = anonymousClass12.obtainMessage();
                    messageObtainMessage2.obj = Popup.Ops.Show.INSTANCE;
                    anonymousClass12.sendMessage(messageObtainMessage2);
                    return;
                }
                Popup.AnonymousClass1 anonymousClass13 = r0;
                Message messageObtainMessage3 = anonymousClass13.obtainMessage();
                messageObtainMessage3.obj = Popup.Ops.Hide.INSTANCE;
                anonymousClass13.sendMessage(messageObtainMessage3);
            }
        }, 0L, WARNING_LOOP_INTERVAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-3$lambda-2, reason: not valid java name */
    public static final void m206lambda3$lambda2(AnonymousClass1 this_run, Popup this$0, View view) {


        Message messageObtainMessage = this_run.obtainMessage();
        messageObtainMessage.obj = Ops.Hide.INSTANCE;
        this_run.sendMessage(messageObtainMessage);
        this$0.mPlayList.clear();
    }

    /* compiled from: Popup.kt */
    
    public static final /* data */ class InfoBean {
        private final int imageResId;
        private int isNeedShow;
        private final int messageResId;

        public static /* synthetic */ InfoBean copy$default(InfoBean infoBean, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = infoBean.imageResId;
            }
            if ((i4 & 2) != 0) {
                i2 = infoBean.messageResId;
            }
            if ((i4 & 4) != 0) {
                i3 = infoBean.isNeedShow;
            }
            return infoBean.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getImageResId() {
            return this.imageResId;
        }

        /* renamed from: component2, reason: from getter */
        public final int getMessageResId() {
            return this.messageResId;
        }

        /* renamed from: component3, reason: from getter */
        public final int getIsNeedShow() {
            return this.isNeedShow;
        }

        public final InfoBean copy(int imageResId, int messageResId, int isNeedShow) {
            return new InfoBean(imageResId, messageResId, isNeedShow);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InfoBean)) {
                return false;
            }
            InfoBean infoBean = (InfoBean) other;
            return this.imageResId == infoBean.imageResId && this.messageResId == infoBean.messageResId && this.isNeedShow == infoBean.isNeedShow;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.imageResId) * 31) + Integer.hashCode(this.messageResId)) * 31) + Integer.hashCode(this.isNeedShow);
        }

        public String toString() {
            return "InfoBean(imageResId=" + this.imageResId + ", messageResId=" + this.messageResId + ", isNeedShow=" + this.isNeedShow + ')';
        }

        public InfoBean(int i, int i2, int i3) {
            this.imageResId = i;
            this.messageResId = i2;
            this.isNeedShow = i3;
        }

        public /* synthetic */ InfoBean(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? R.drawable.drive_infor : i, i2, (i4 & 4) != 0 ? 0 : i3);
        }

        public final int getImageResId() {
            return this.imageResId;
        }

        public final int getMessageResId() {
            return this.messageResId;
        }

        public final int isNeedShow() {
            return this.isNeedShow;
        }

        public final void setNeedShow(int i) {
            this.isNeedShow = i;
        }
    }

    /* compiled from: Popup.kt */
    
    public static abstract class Ops {
        public /* synthetic */ Ops(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: Popup.kt */
        
        public static final class Show extends Ops {
            public static final Show INSTANCE = new Show();

            private Show() {
                super(null);
            }
        }

        private Ops() {
        }

        /* compiled from: Popup.kt */
        
        public static final class Hide extends Ops {
            public static final Hide INSTANCE = new Hide();

            private Hide() {
                super(null);
            }
        }

        /* compiled from: Popup.kt */
        
        public static class Update extends Ops {
            private final InfoBean bean;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Update(InfoBean bean) {
                super(null);

                this.bean = bean;
            }

            public final InfoBean getBean() {
                return this.bean;
            }
        }

        /* compiled from: Popup.kt */
        
        public static final class WarningBean extends Update {
            private final int messageResId;

            public WarningBean(int i) {
                super(new InfoBean(0, i, 0, 5, null));
                this.messageResId = i;
            }

            public final int getMessageResId() {
                return this.messageResId;
            }
        }

        /* compiled from: Popup.kt */
        
        public static final class FunctionBean extends Update {
            private final int imageResId;
            private final int messageResId;

            public FunctionBean(int i, int i2) {
                super(new InfoBean(i, i2, 0, 4, null));
                this.imageResId = i;
                this.messageResId = i2;
            }

            public final int getImageResId() {
                return this.imageResId;
            }

            public final int getMessageResId() {
                return this.messageResId;
            }
        }
    }

    public final void plus(Ops.Update bean) {

        this.mPlayList.add(0, bean);
        Log.i(TAG, "plus: " + this.mPlayList.size());
    }

    public final void minus(Ops.Update bean) {

        this.mPlayList.remove(bean);
        Log.i(TAG, "minus: " + this.mPlayList.size());
    }
}
