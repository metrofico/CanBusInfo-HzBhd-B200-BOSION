package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class OnStartPhoneFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
    public RelativeLayout dialog;
    private TextView gm_onstar_call_type;
    private TextView gm_onstar_input;
    private TextView gm_onstar_input_del;
    private TextView gm_onstar_kb_dial;
    private TextView gm_onstar_kb_hang;
    private TextView gm_onstar_kb_num_0;
    private TextView gm_onstar_kb_num_1;
    private TextView gm_onstar_kb_num_2;
    private TextView gm_onstar_kb_num_3;
    private TextView gm_onstar_kb_num_4;
    private TextView gm_onstar_kb_num_5;
    private TextView gm_onstar_kb_num_6;
    private TextView gm_onstar_kb_num_7;
    private TextView gm_onstar_kb_num_8;
    private TextView gm_onstar_kb_num_9;
    private TextView gm_onstar_kb_num_star;
    private TextView gm_onstar_kb_num_well;
    private TextView gm_onstar_kb_power;
    private TextView gm_onstar_left_icon;
    private TextView gm_onstar_left_num;
    private TextView gm_onstar_mute_icon;
    public RelativeLayout input;
    private OnStarActivity mActivity;
    private String mInputNumStr = "";
    private OnOnStarClickListener mOnOnStarClickListener;
    private View mView;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_on_star_phone, viewGroup, false);
            this.mActivity = (OnStarActivity) getActivity();
            findViews();
            initViews();
        }
        initData();
        ViewGroup viewGroup2 = (ViewGroup) this.mView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mView);
        }
        return this.mView;
    }

    private void findViews() {
        this.gm_onstar_left_icon = this.mView.findViewById(R.id.gm_onstar_left_phone);
        this.gm_onstar_left_num = this.mView.findViewById(R.id.gm_onstar_left_num);
        this.gm_onstar_input = this.mView.findViewById(R.id.gm_onstar_input);
        this.gm_onstar_input_del = this.mView.findViewById(R.id.gm_onstar_input_del);
        this.gm_onstar_kb_num_1 = this.mView.findViewById(R.id.gm_onstar_kb_num_1);
        this.gm_onstar_kb_num_2 = this.mView.findViewById(R.id.gm_onstar_kb_num_2);
        this.gm_onstar_kb_num_3 = this.mView.findViewById(R.id.gm_onstar_kb_num_3);
        this.gm_onstar_kb_num_4 = this.mView.findViewById(R.id.gm_onstar_kb_num_4);
        this.gm_onstar_kb_num_5 = this.mView.findViewById(R.id.gm_onstar_kb_num_5);
        this.gm_onstar_kb_num_6 = this.mView.findViewById(R.id.gm_onstar_kb_num_6);
        this.gm_onstar_kb_num_7 = this.mView.findViewById(R.id.gm_onstar_kb_num_7);
        this.gm_onstar_kb_num_8 = this.mView.findViewById(R.id.gm_onstar_kb_num_8);
        this.gm_onstar_kb_num_9 = this.mView.findViewById(R.id.gm_onstar_kb_num_9);
        this.gm_onstar_kb_num_star = this.mView.findViewById(R.id.gm_onstar_kb_num_star);
        this.gm_onstar_kb_num_0 = this.mView.findViewById(R.id.gm_onstar_kb_num_0);
        this.gm_onstar_kb_num_well = this.mView.findViewById(R.id.gm_onstar_kb_num_well);
        this.gm_onstar_kb_dial = this.mView.findViewById(R.id.tv_onstar_phone_hand_on);
        this.gm_onstar_kb_hang = this.mView.findViewById(R.id.tv_onstar_phone_hand_off);
        this.gm_onstar_kb_power = this.mView.findViewById(R.id.tv_onstar_exit);
        this.gm_onstar_call_type = this.mView.findViewById(R.id.gm_onstar_call_type);
        this.gm_onstar_mute_icon = this.mView.findViewById(R.id.gm_onstar_mute_icon);
        this.input = this.mView.findViewById(R.id.gm_onstar_input_bg);
        this.dialog = this.mView.findViewById(R.id.gm_onstar_keyboard_bg);
    }

    private void initViews() {
        this.gm_onstar_input_del.setOnClickListener(this);
        this.gm_onstar_input_del.setOnLongClickListener(this);
        this.gm_onstar_kb_num_1.setOnClickListener(this);
        this.gm_onstar_kb_num_2.setOnClickListener(this);
        this.gm_onstar_kb_num_3.setOnClickListener(this);
        this.gm_onstar_kb_num_4.setOnClickListener(this);
        this.gm_onstar_kb_num_5.setOnClickListener(this);
        this.gm_onstar_kb_num_6.setOnClickListener(this);
        this.gm_onstar_kb_num_7.setOnClickListener(this);
        this.gm_onstar_kb_num_8.setOnClickListener(this);
        this.gm_onstar_kb_num_9.setOnClickListener(this);
        this.gm_onstar_kb_num_star.setOnClickListener(this);
        this.gm_onstar_kb_num_0.setOnClickListener(this);
        this.gm_onstar_kb_num_well.setOnClickListener(this);
        this.gm_onstar_kb_dial.setOnClickListener(this);
        this.gm_onstar_kb_hang.setOnClickListener(this);
        this.gm_onstar_kb_power.setOnClickListener(this);
    }

    private void initData() {
        OnOnStarClickListener onOnStarClickListener = this.mActivity.getUiMgrInterface(getActivity()).getOnStartPageUiSet(getActivity()).getOnOnStarClickListener();
        this.mOnOnStarClickListener = onOnStarClickListener;
        if (onOnStarClickListener != null) {
            onOnStarClickListener.init();
        }
    }

    public void refreshUi(Bundle bundle) {
        this.gm_onstar_call_type.setText(GeneralOnStartData.mOnStarCallType);
        if (GeneralOnStartData.mOnStarCallMuteSt == 1) {
            this.gm_onstar_mute_icon.setBackgroundResource(R.drawable.on_star_icon_mute);
        } else {
            this.gm_onstar_mute_icon.setBackgroundColor(0);
        }
        int i = GeneralOnStartData.mOnStarStatus;
        if (i == 2) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_come);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
            return;
        }
        if (i == 3) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_dial);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
        } else if (i == 4) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_talk);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
        } else {
            this.gm_onstar_left_icon.setBackgroundColor(0);
            this.gm_onstar_left_num.setText(" ");
            this.gm_onstar_call_type.setText("");
            this.gm_onstar_mute_icon.setBackgroundColor(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.gm_onstar_input_del) {
            if (this.mInputNumStr != null && !this.mInputNumStr.isEmpty()) {
                this.mInputNumStr = this.mInputNumStr.substring(0, this.mInputNumStr.length() - 1);
                this.gm_onstar_input.setText(this.mInputNumStr);
            }
        } else if (id == R.id.gm_onstar_kb_num_0) {
            keyEventHandler("0");
            numberClick(0);
        } else if (id == R.id.gm_onstar_kb_num_1) {
            keyEventHandler("1");
            numberClick(1);
        } else if (id == R.id.gm_onstar_kb_num_2) {
            keyEventHandler("2");
            numberClick(2);
        } else if (id == R.id.gm_onstar_kb_num_3) {
            keyEventHandler("3");
            numberClick(3);
        } else if (id == R.id.gm_onstar_kb_num_4) {
            keyEventHandler("4");
            numberClick(4);
        } else if (id == R.id.gm_onstar_kb_num_5) {
            keyEventHandler("5");
            numberClick(5);
        } else if (id == R.id.gm_onstar_kb_num_6) {
            keyEventHandler("6");
            numberClick(6);
        } else if (id == R.id.gm_onstar_kb_num_7) {
            keyEventHandler("7");
            numberClick(7);
        } else if (id == R.id.gm_onstar_kb_num_8) {
            keyEventHandler("8");
            numberClick(8);
        } else if (id == R.id.gm_onstar_kb_num_9) {
            keyEventHandler("9");
            numberClick(9);
        } else if (id == R.id.gm_onstar_kb_num_star) {
            keyEventHandler("*");
            numberClick(10);
        } else if (id == R.id.gm_onstar_kb_num_well) {
            keyEventHandler("#");
            numberClick(11);
        } else if (id == R.id.tv_onstar_exit) {
            if (this.mOnOnStarClickListener != null) {
                this.mOnOnStarClickListener.exit();
            }
        } else if (id == R.id.tv_onstar_phone_hand_off) {
            if (this.mOnOnStarClickListener != null) {
                this.mOnOnStarClickListener.handOff();
            }
        } else if (id == R.id.tv_onstar_phone_hand_on) {
            if (this.mOnOnStarClickListener != null) {
                this.mOnOnStarClickListener.handOn(this.mInputNumStr);
            }
        }
    }

    private void keyEventHandler(String str) {
        String str2 = this.mInputNumStr;
        if (str2 != null && str2.length() > 19) {
            String str3 = this.mInputNumStr;
            this.mInputNumStr = str3.substring(str3.length() - 19, this.mInputNumStr.length());
        }
        String str4 = this.mInputNumStr + str;
        this.mInputNumStr = str4;
        this.gm_onstar_input.setText(str4);
    }

    private void numberClick(int i) {
        OnOnStarClickListener onOnStarClickListener = this.mOnOnStarClickListener;
        if (onOnStarClickListener != null) {
            onOnStarClickListener.numberClick(i);
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.gm_onstar_input_del) {
            TextView textView = this.gm_onstar_input;
            if (textView != null) {
                textView.setText("");
            }
            this.mInputNumStr = "";
        }
        CommUtil.playBeep();
        return true;
    }
}
