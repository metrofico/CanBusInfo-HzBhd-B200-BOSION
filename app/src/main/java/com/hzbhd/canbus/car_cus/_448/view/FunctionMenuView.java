package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.car_cus._448.window.AlertWindow;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class FunctionMenuView extends LinearLayout implements DvrDataInterface {
    private FunctionSelectionView fun1;
    private FunctionSeekBarView fun10;
    private FunctionSeekBarView fun11;
    private FunctionSelectionView fun2;
    private FunctionSelectionView fun3;
    private FunctionSelectionView fun4;
    private FunctionSelectionView fun5;
    private FunctionSelectionView fun6;
    private FunctionSelectionView fun7;
    private FunctionSelectionView fun8;
    private FunctionSeekBarView fun9;
    private View view;

    public FunctionMenuView(Context context) {
        this(context, null);
    }

    public FunctionMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunctionMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_function_menu, (ViewGroup) this, true);
        this.view = viewInflate;
        this.fun1 = (FunctionSelectionView) viewInflate.findViewById(R.id.fun1);
        this.fun2 = (FunctionSelectionView) this.view.findViewById(R.id.fun2);
        this.fun3 = (FunctionSelectionView) this.view.findViewById(R.id.fun3);
        this.fun4 = (FunctionSelectionView) this.view.findViewById(R.id.fun4);
        this.fun5 = (FunctionSelectionView) this.view.findViewById(R.id.fun5);
        this.fun6 = (FunctionSelectionView) this.view.findViewById(R.id.fun6);
        this.fun7 = (FunctionSelectionView) this.view.findViewById(R.id.fun7);
        this.fun8 = (FunctionSelectionView) this.view.findViewById(R.id.fun8);
        this.fun9 = (FunctionSeekBarView) this.view.findViewById(R.id.fun9);
        this.fun10 = (FunctionSeekBarView) this.view.findViewById(R.id.fun10);
        this.fun11 = (FunctionSeekBarView) this.view.findViewById(R.id.fun11);
        initData(context);
    }

    @Override // android.view.View
    protected void onFinishInflate() throws RemoteException {
        super.onFinishInflate();
        DvrObserver.getInstance().register(this);
        DvrSender.send(new byte[]{90, 0, 0, 0, 0});
    }

    private void initData(final Context context) {
        this.fun1.setTitle("分辨率");
        ArrayList arrayList = new ArrayList();
        arrayList.add("640x480/30帧");
        arrayList.add("1280x720/30帧");
        arrayList.add("1920x1080/30帧");
        this.fun1.setItems(arrayList);
        this.fun1.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (DvrData.fbl == 2) {
                    DvrSender.send(new byte[]{80, 0});
                } else if (DvrData.fbl == 0) {
                    DvrSender.send(new byte[]{80, 1});
                } else if (DvrData.fbl == 1) {
                    DvrSender.send(new byte[]{80, 2});
                }
                DvrSender.send(new byte[]{80, -1});
            }
        });
        this.fun2.setTitle("时间标志");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("关闭");
        arrayList2.add("日期");
        arrayList2.add("日期和时间");
        this.fun2.setItems(arrayList2);
        this.fun2.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.2
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (DvrData.sjbz == 2) {
                    DvrSender.send(new byte[]{81, 0});
                } else if (DvrData.sjbz == 0) {
                    DvrSender.send(new byte[]{81, 1});
                } else if (DvrData.sjbz == 1) {
                    DvrSender.send(new byte[]{81, 2});
                }
                DvrSender.send(new byte[]{81, -1});
            }
        });
        this.fun3.setTitle("循环录影");
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("1分钟");
        arrayList3.add("3分钟");
        arrayList3.add("5分钟");
        this.fun3.setItems(arrayList3);
        this.fun3.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.3
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (DvrData.xhly == 2) {
                    DvrSender.send(new byte[]{82, 1});
                } else if (DvrData.xhly == 0) {
                    DvrSender.send(new byte[]{82, 3});
                } else if (DvrData.xhly == 1) {
                    DvrSender.send(new byte[]{82, 5});
                }
                DvrSender.send(new byte[]{82, -1});
            }
        });
        this.fun4.setTitle("录像声音");
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("关闭");
        arrayList4.add("开启");
        this.fun4.setItems(arrayList4);
        this.fun4.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.4
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (DvrData.lxsy == 0) {
                    DvrSender.send(new byte[]{83, 1});
                } else if (DvrData.lxsy == 1) {
                    DvrSender.send(new byte[]{83, 0});
                }
                DvrSender.send(new byte[]{83, -1});
            }
        });
        this.fun5.setTitle("重力感应");
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add("关闭");
        arrayList5.add("高");
        arrayList5.add("普通");
        arrayList5.add("低");
        this.fun5.setItems(arrayList5);
        this.fun5.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.5
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (DvrData.zlgy == 3) {
                    DvrSender.send(new byte[]{85, 0});
                } else if (DvrData.zlgy == 0) {
                    DvrSender.send(new byte[]{85, 1});
                } else if (DvrData.zlgy == 1) {
                    DvrSender.send(new byte[]{85, 3});
                } else if (DvrData.zlgy == 2) {
                    DvrSender.send(new byte[]{85, 5});
                }
                DvrSender.send(new byte[]{85, -1});
            }
        });
        this.fun6.setTitle("重置系统");
        this.fun6.setNextPrevActionNone(true);
        this.fun6.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.6
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                if (obj.equals("ALL_ACTION")) {
                    new AlertWindow(context, "您确定要重置系统？", "确定", new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.6.1
                        @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
                        public void toDo(Object obj2) throws RemoteException {
                            DvrSender.send(new byte[]{92, 0});
                        }
                    }).show();
                }
            }
        });
        this.fun7.setTitle("格式化");
        this.fun7.setNextPrevActionNone(true);
        this.fun7.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.7
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                if (obj.equals("ALL_ACTION")) {
                    new AlertWindow(context, "您确定要格式化SD卡？", "确定", new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.7.1
                        @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
                        public void toDo(Object obj2) throws RemoteException {
                            DvrSender.send(new byte[]{91, 0});
                        }
                    }).show();
                }
            }
        });
        this.fun8.setTitle("软件版本");
        this.fun8.setNextPrevActionNone(true);
        this.fun9.setTitleStr("亮度");
        this.fun9.setMax(100);
        this.fun9.setValue(DvrData.ld);
        this.fun9.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.8
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
            }
        });
        this.fun10.setTitleStr("对比度");
        this.fun10.setMax(100);
        this.fun10.setValue(DvrData.dbd);
        this.fun10.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.9
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
            }
        });
        this.fun11.setTitleStr("色度");
        this.fun11.setMax(100);
        this.fun11.setValue(DvrData.sd);
        this.fun11.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionMenuView.10
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
            }
        });
    }

    public void updateUi() {
        this.fun1.selectItem(DvrData.fbl);
        this.fun2.selectItem(DvrData.sjbz);
        this.fun3.selectItem(DvrData.xhly);
        this.fun4.selectItem(DvrData.lxsy);
        this.fun5.selectItem(DvrData.zlgy);
        this.fun6.setValueStr(DvrData.czxt);
        this.fun7.setValueStr(DvrData.gsh);
        this.fun8.setValueStr(DvrData.rjbb);
    }

    @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
    public void dataChange(String str) {
        if (str.equals(DvrMode.FUNCTION_SETTINGS_MODE)) {
            updateUi();
        }
    }
}
