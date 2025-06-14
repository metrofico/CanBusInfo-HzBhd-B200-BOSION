package com.hzbhd.canbus.car_cus._467.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._467.smartPanel.NotifyPanel;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelState;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelView;
import com.hzbhd.canbus.car_cus._467.smartPanel.RedoUtil;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class ID439PanelActivity extends Activity implements View.OnClickListener {
    private ActionListener actionListener;
    private RedoUtil addFun1RedoUtil;
    private PanelView p1;
    private PanelView p10;
    private PanelView p11;
    private RedoUtil p11RedoUtil;
    private PanelView p12;
    private RedoUtil p12RedoUtil;
    private PanelView p13;
    private RedoUtil p13RedoUtil;
    private PanelView p14;
    private PanelView p15;
    private PanelView p16;
    private PanelView p17;
    private PanelView p18;
    private RedoUtil p18RedoUtil;
    private PanelView p2;
    private PanelView p3;
    private PanelView p4;
    private PanelView p5;
    private RedoUtil p5RedoUtil;
    private PanelView p6;
    private RedoUtil p6RedoUtil;
    private PanelView p7;
    private RedoUtil p7RedoUtil;
    private PanelView p8;
    private PanelView p9;
    private PanelView pAbs;
    private PanelView pUltraVires;
    private PanelView retarder;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(get1024x600CofigLayout());
        initView();
    }

    private int get1024x600CofigLayout() {
        return PanelState.carConfig == 1 ? R.layout.activity_id467_panel1024x600_200l8hev : PanelState.carConfig == 2 ? R.layout.activity_id467_panel1024x600_160au : PanelState.carConfig == 3 ? R.layout.activity_id467_panel1024x600_60ev : PanelState.carConfig == 4 ? R.layout.activity_id467_panel1024x600_60au : PanelState.carConfig == 5 ? R.layout.activity_id467_panel1024x600_2600_1 : R.layout.activity_id467_panel1024x600;
    }

    private void initView() {
        this.p1 = (PanelView) findViewById(R.id.p1);
        this.p2 = (PanelView) findViewById(R.id.p2);
        this.p3 = (PanelView) findViewById(R.id.p3);
        this.p4 = (PanelView) findViewById(R.id.p4);
        this.p5 = (PanelView) findViewById(R.id.p5);
        this.p6 = (PanelView) findViewById(R.id.p6);
        this.p7 = (PanelView) findViewById(R.id.p7);
        this.p8 = (PanelView) findViewById(R.id.p8);
        this.p9 = (PanelView) findViewById(R.id.p9);
        this.p10 = (PanelView) findViewById(R.id.p10);
        this.p11 = (PanelView) findViewById(R.id.p11);
        this.p12 = (PanelView) findViewById(R.id.p12);
        this.p13 = (PanelView) findViewById(R.id.p13);
        this.p14 = (PanelView) findViewById(R.id.p14);
        this.p15 = (PanelView) findViewById(R.id.p15);
        this.p16 = (PanelView) findViewById(R.id.p16);
        this.p17 = (PanelView) findViewById(R.id.p17);
        this.p18 = (PanelView) findViewById(R.id.p18);
        this.pUltraVires = (PanelView) findViewById(R.id.p_ultra_vires);
        this.pAbs = (PanelView) findViewById(R.id.p_abs);
        this.retarder = (PanelView) findViewById(R.id.retarder);
        this.p1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p8.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p9.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p10.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p11.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p12.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p13.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p14.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p15.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p16.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p17.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p18.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.pAbs.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.retarder.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.p6.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP6Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP6Timer();
                return true;
            }
        });
        this.p7.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP7Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP7Timer();
                return true;
            }
        });
        this.p11.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP11Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP11Timer();
                return true;
            }
        });
        this.p12.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP12Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP12Timer();
                return true;
            }
        });
        this.p18.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (LogUtil.log5()) {
                        LogUtil.d("onTouch(): +++++++");
                    }
                    ID439PanelActivity.this.startP18Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                if (LogUtil.log5()) {
                    LogUtil.d("onTouch():--------");
                }
                ID439PanelActivity.this.stopP18Timer();
                return true;
            }
        });
        this.p13.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP13Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP13Timer();
                return true;
            }
        });
        this.p5.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startP5Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopP5Timer();
                return true;
            }
        });
        this.pUltraVires.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.8
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ID439PanelActivity.this.startAddFunP1Timer();
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                ID439PanelActivity.this.stopAddFunP1Timer();
                return true;
            }
        });
        refreshUi();
        this.actionListener = new ActionListener();
        NotifyPanel.getInstance().register(this.actionListener);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        NotifyPanel.getInstance().unregister(this.actionListener);
    }

    private class ActionListener implements ActionDo {
        private ActionListener() {
        }

        @Override // com.hzbhd.canbus.interfaces.ActionDo
        public void todo(List<Object> list) {
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.ActionListener.1
                @Override // kotlin.jvm.functions.Function0
                public Unit invoke() {
                    ID439PanelActivity.this.refreshUi();
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi() {
        this.p1.setSelect(this, PanelState.sp1);
        this.p2.setSelect(this, PanelState.sp2);
        this.p3.setSelect(this, PanelState.sp3);
        this.p4.setSelect(this, PanelState.sp4);
        this.p5.setSelect(this, PanelState.sp5);
        this.p6.setSelect(this, PanelState.sp6);
        this.p7.setSelect(this, PanelState.sp7);
        this.p8.setSelect(this, PanelState.sp8);
        this.p9.setSelect(this, PanelState.sp9);
        this.p10.setSelect(this, PanelState.sp10);
        this.p11.setSelect(this, PanelState.sp11);
        this.p12.setSelect(this, PanelState.sp12);
        this.p13.setSelect(this, PanelState.sp13);
        if (PanelState.sp14 == 1) {
            this.p14.setImg(ContextCompat.getDrawable(this, R.drawable._n8_439_zk_anjian4_p));
        } else if (PanelState.sp14 == 2) {
            this.p14.setImg(ContextCompat.getDrawable(this, R.drawable._n8_439_zk_anjian3_p));
        } else if (PanelState.sp14 == 3) {
            this.p14.setImg(ContextCompat.getDrawable(this, R.drawable._n8_439_zk_anjian2_p));
        } else if (PanelState.sp14 == 4) {
            this.p14.setImg(ContextCompat.getDrawable(this, R.drawable._n8_439_zk_anjian1_p));
        }
        this.p15.setSelect(this, PanelState.sp15);
        this.p16.setSelect(this, PanelState.sp16);
        this.p17.setSelect(this, PanelState.sp17);
        this.p18.setSelect(this, PanelState.sp18);
        this.pUltraVires.setSelect(this, PanelState.ultraVires);
        this.pAbs.setSelect(this, PanelState.abs);
        this.retarder.setSelect(this, PanelState.retarder);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.p1 /* 2131362934 */:
                button1();
                break;
            case R.id.p10 /* 2131362935 */:
                button10();
                break;
            case R.id.p14 /* 2131362939 */:
                button14();
                break;
            case R.id.p15 /* 2131362940 */:
                button15();
                break;
            case R.id.p16 /* 2131362941 */:
                button16();
                break;
            case R.id.p17 /* 2131362942 */:
                button17();
                break;
            case R.id.p2 /* 2131362944 */:
                button2();
                break;
            case R.id.p3 /* 2131362945 */:
                button3();
                break;
            case R.id.p4 /* 2131362946 */:
                button4();
                break;
            case R.id.p8 /* 2131362950 */:
                button8();
                break;
            case R.id.p9 /* 2131362951 */:
                button9();
                break;
            case R.id.p_abs /* 2131362952 */:
                buttonAbs();
                break;
            case R.id.retarder /* 2131363100 */:
                buttonRetarder();
                break;
        }
    }

    private void buttonRetarder() {
        if (PanelState.retarder) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 19, 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 19, 1});
        }
    }

    private void buttonAbs() {
        if (!PanelState.abs) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 20, (byte) (!PanelState.ultraVires ? 1 : 0)});
        } else {
            boolean z = PanelState.ultraVires;
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 20, (byte) 0});
        }
    }

    private void button1() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, (byte) (!PanelState.sp1 ? 1 : 0)});
    }

    private void button2() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, (byte) (!PanelState.sp2 ? 1 : 0)});
    }

    private void button3() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, (byte) (!PanelState.sp3 ? 1 : 0)});
    }

    private void button4() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, (byte) (!PanelState.sp4 ? 1 : 0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP5Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
        if (this.p5RedoUtil == null) {
            this.p5RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.9
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                    if (ID439PanelActivity.this.p5RedoUtil != null) {
                        ID439PanelActivity.this.p5RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p5RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP5Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        RedoUtil redoUtil = this.p5RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAddFunP1Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 1});
        if (this.addFun1RedoUtil == null) {
            this.addFun1RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.10
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 1});
                    if (ID439PanelActivity.this.addFun1RedoUtil != null) {
                        ID439PanelActivity.this.addFun1RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.addFun1RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAddFunP1Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 0});
        RedoUtil redoUtil = this.addFun1RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP6Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 1});
        if (this.p6RedoUtil == null) {
            this.p6RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.11
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 1});
                    if (ID439PanelActivity.this.p6RedoUtil != null) {
                        ID439PanelActivity.this.p6RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p6RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP6Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 0});
        RedoUtil redoUtil = this.p6RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP7Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 1});
        if (this.p7RedoUtil == null) {
            this.p7RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.12
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 1});
                    if (ID439PanelActivity.this.p7RedoUtil != null) {
                        ID439PanelActivity.this.p7RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p7RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP7Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 0});
        RedoUtil redoUtil = this.p7RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    private void button8() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (!PanelState.sp8 ? 1 : 0)});
    }

    private void button9() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 14, (byte) (!PanelState.sp9 ? 1 : 0)});
    }

    private void button10() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 11, (byte) (!PanelState.sp10 ? 1 : 0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP11Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 1});
        if (this.p11RedoUtil == null) {
            this.p11RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.13
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 1});
                    if (ID439PanelActivity.this.p11RedoUtil != null) {
                        ID439PanelActivity.this.p11RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p11RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP11Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 0});
        RedoUtil redoUtil = this.p11RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP12Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 1});
        if (this.p12RedoUtil == null) {
            this.p12RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.14
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 1});
                    if (ID439PanelActivity.this.p12RedoUtil != null) {
                        ID439PanelActivity.this.p12RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p12RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP12Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 0});
        RedoUtil redoUtil = this.p12RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP18Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 1});
        if (this.p18RedoUtil == null) {
            this.p18RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.15
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 1});
                    if (ID439PanelActivity.this.p18RedoUtil != null) {
                        ID439PanelActivity.this.p18RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p18RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP18Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 0});
        RedoUtil redoUtil = this.p18RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP13Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 1});
        if (this.p13RedoUtil == null) {
            this.p13RedoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._467.activity.ID439PanelActivity.16
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 1});
                    if (ID439PanelActivity.this.p13RedoUtil != null) {
                        ID439PanelActivity.this.p13RedoUtil.startTimer(200L);
                    }
                }
            });
        }
        this.p13RedoUtil.startTimer(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopP13Timer() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 0});
        RedoUtil redoUtil = this.p13RedoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
    }

    private void button14() {
        if (PanelState.sp14 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 2});
            return;
        }
        if (PanelState.sp14 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 3});
        } else if (PanelState.sp14 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 4});
        } else if (PanelState.sp14 == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 1});
        }
    }

    private void button15() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, (byte) (!PanelState.sp15 ? 1 : 0)});
    }

    private void button16() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 16, (byte) (!PanelState.sp16 ? 1 : 0)});
    }

    private void button17() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 17, (byte) (!PanelState.sp17 ? 1 : 0)});
    }
}
