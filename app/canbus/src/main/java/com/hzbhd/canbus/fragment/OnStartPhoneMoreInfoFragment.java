package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class OnStartPhoneMoreInfoFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
    private TextView gm_onstar_call_time;
    private TextView gm_onstar_call_type;
    private TextView gm_onstar_effect_time;
    private TextView gm_onstar_input;
    private TextView gm_onstar_input_del;
    private TextView gm_onstar_kb_dial;
    private TextView gm_onstar_kb_hang;
    private TextView gm_onstar_kb_mute;
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
    private TextView gm_onstar_left_icon;
    private TextView gm_onstar_left_num;
    private TextView gm_onstar_mute_icon;
    private TextView gm_onstar_my_num;
    private TextView gm_onstar_remain_time;
    private OnStarActivity mActivity;
    private String mInputNumStr = "";
    private OnOnStarPhoneMoreInfoClickListener mOnOnStarPhoneMoreInfoClickListener;
    private View mView;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_on_star_phone_more_info, viewGroup, false);
            this.mActivity = (OnStarActivity) getActivity();
            findViews();
            initViews();
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mView);
        }
        return this.mView;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener = this.mOnOnStarPhoneMoreInfoClickListener;
        if (onOnStarPhoneMoreInfoClickListener != null) {
            onOnStarPhoneMoreInfoClickListener.exit();
        }
        Log.e("onDestroyViewonDestroyViewonDestroyView", "ssonDestroyViewss");
    }

    private void findViews() {
        this.gm_onstar_left_icon = (TextView) this.mView.findViewById(R.id.gm_onstar_left_phone);
        this.gm_onstar_left_num = (TextView) this.mView.findViewById(R.id.gm_onstar_left_num);
        this.gm_onstar_input = (TextView) this.mView.findViewById(R.id.gm_onstar_input);
        this.gm_onstar_input_del = (TextView) this.mView.findViewById(R.id.gm_onstar_input_del);
        this.gm_onstar_kb_num_1 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_1);
        this.gm_onstar_kb_num_2 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_2);
        this.gm_onstar_kb_num_3 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_3);
        this.gm_onstar_kb_num_4 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_4);
        this.gm_onstar_kb_num_5 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_5);
        this.gm_onstar_kb_num_6 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_6);
        this.gm_onstar_kb_num_7 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_7);
        this.gm_onstar_kb_num_8 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_8);
        this.gm_onstar_kb_num_9 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_9);
        this.gm_onstar_kb_num_star = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_star);
        this.gm_onstar_kb_num_0 = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_0);
        this.gm_onstar_kb_num_well = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_num_well);
        this.gm_onstar_kb_dial = (TextView) this.mView.findViewById(R.id.tv_onstar_phone_hand_on);
        this.gm_onstar_kb_hang = (TextView) this.mView.findViewById(R.id.tv_onstar_phone_hand_off);
        this.gm_onstar_kb_mute = (TextView) this.mView.findViewById(R.id.gm_onstar_kb_mute);
        this.gm_onstar_call_type = (TextView) this.mView.findViewById(R.id.gm_onstar_call_type);
        this.gm_onstar_call_time = (TextView) this.mView.findViewById(R.id.gm_onstar_call_time);
        this.gm_onstar_mute_icon = (TextView) this.mView.findViewById(R.id.gm_onstar_mute_icon);
        this.gm_onstar_remain_time = (TextView) this.mView.findViewById(R.id.gm_onstar_remain_time);
        this.gm_onstar_effect_time = (TextView) this.mView.findViewById(R.id.gm_onstar_effect_time);
        this.gm_onstar_my_num = (TextView) this.mView.findViewById(R.id.gm_onstar_my_num);
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
        this.gm_onstar_kb_dial.setOnLongClickListener(this);
        this.gm_onstar_kb_hang.setOnClickListener(this);
        this.gm_onstar_kb_mute.setOnClickListener(this);
        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener = this.mActivity.getUiMgrInterface(getActivity()).getOnStartPageUiSet(getActivity()).getOnOnStarPhoneMoreInfoClickListener();
        this.mOnOnStarPhoneMoreInfoClickListener = onOnStarPhoneMoreInfoClickListener;
        if (onOnStarPhoneMoreInfoClickListener != null) {
            onOnStarPhoneMoreInfoClickListener.init();
        }
    }

    public void refreshUi(Bundle bundle) {
        this.gm_onstar_call_type.setText(GeneralOnStartData.mOnStarCallType);
        this.gm_onstar_call_time.setText(GeneralOnStartData.mOnStarCallTime);
        if (GeneralOnStartData.mOnStarCallMuteSt == 1) {
            this.gm_onstar_mute_icon.setBackgroundResource(R.drawable.on_star_icon_mute);
        } else {
            this.gm_onstar_mute_icon.setBackgroundColor(0);
        }
        int i = GeneralOnStartData.mOnStarStatus;
        if (i == 1) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_come);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
        } else if (i == 2) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_dial);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
        } else if (i == 3) {
            this.gm_onstar_left_icon.setBackgroundResource(R.drawable.gm_onstar_left_talk);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
        } else {
            this.gm_onstar_left_icon.setBackgroundColor(0);
            this.gm_onstar_left_num.setText(" ");
            this.gm_onstar_call_type.setText("");
            this.gm_onstar_call_time.setText("");
            this.gm_onstar_mute_icon.setBackgroundColor(0);
        }
        this.gm_onstar_remain_time.setText(GeneralOnStartData.mOnStarRemainTime);
        this.gm_onstar_effect_time.setText(GeneralOnStartData.mOnStarEffectTime);
        this.gm_onstar_my_num.setText(GeneralOnStartData.mOnStarMyNumber);
    }

    private boolean isOnCall() {
        return GeneralOnStartData.mOnStarStatus >= 1 && GeneralOnStartData.mOnStarStatus <= 3;
    }

    private boolean isPhoneIn() {
        return GeneralOnStartData.mOnStarStatus == 1;
    }

    private boolean isPhoneOutOrCalling() {
        return GeneralOnStartData.mOnStarStatus == 1 || GeneralOnStartData.mOnStarStatus == 3;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.gm_onstar_input_del /* 2131362318 */:
                String str = this.mInputNumStr;
                if (str != null && str.length() > 0) {
                    String str2 = this.mInputNumStr;
                    String strSubstring = str2.substring(0, str2.length() - 1);
                    this.mInputNumStr = strSubstring;
                    this.gm_onstar_input.setText(strSubstring);
                    break;
                }
                break;
            case R.id.gm_onstar_kb_mute /* 2131362319 */:
                OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener = this.mOnOnStarPhoneMoreInfoClickListener;
                if (onOnStarPhoneMoreInfoClickListener != null) {
                    onOnStarPhoneMoreInfoClickListener.mute();
                    break;
                }
                break;
            case R.id.gm_onstar_kb_num_0 /* 2131362320 */:
                keyEventHandler("0");
                numberClick(0);
                break;
            case R.id.gm_onstar_kb_num_1 /* 2131362321 */:
                keyEventHandler("1");
                numberClick(1);
                break;
            case R.id.gm_onstar_kb_num_2 /* 2131362322 */:
                keyEventHandler("2");
                numberClick(2);
                break;
            case R.id.gm_onstar_kb_num_3 /* 2131362323 */:
                keyEventHandler("3");
                numberClick(3);
                break;
            case R.id.gm_onstar_kb_num_4 /* 2131362324 */:
                keyEventHandler("4");
                numberClick(4);
                break;
            case R.id.gm_onstar_kb_num_5 /* 2131362325 */:
                keyEventHandler("5");
                numberClick(5);
                break;
            case R.id.gm_onstar_kb_num_6 /* 2131362326 */:
                keyEventHandler("6");
                numberClick(6);
                break;
            case R.id.gm_onstar_kb_num_7 /* 2131362327 */:
                keyEventHandler("7");
                numberClick(7);
                break;
            case R.id.gm_onstar_kb_num_8 /* 2131362328 */:
                keyEventHandler("8");
                numberClick(8);
                break;
            case R.id.gm_onstar_kb_num_9 /* 2131362329 */:
                keyEventHandler("9");
                numberClick(9);
                break;
            case R.id.gm_onstar_kb_num_star /* 2131362330 */:
                keyEventHandler("*");
                numberClick(10);
                break;
            case R.id.gm_onstar_kb_num_well /* 2131362331 */:
                keyEventHandler("#");
                numberClick(11);
                break;
            default:
                switch (id) {
                    case R.id.tv_onstar_phone_hand_off /* 2131363660 */:
                        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener2 = this.mOnOnStarPhoneMoreInfoClickListener;
                        if (onOnStarPhoneMoreInfoClickListener2 != null) {
                            onOnStarPhoneMoreInfoClickListener2.handOff();
                            break;
                        }
                        break;
                    case R.id.tv_onstar_phone_hand_on /* 2131363661 */:
                        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener3 = this.mOnOnStarPhoneMoreInfoClickListener;
                        if (onOnStarPhoneMoreInfoClickListener3 != null) {
                            onOnStarPhoneMoreInfoClickListener3.handOn(this.mInputNumStr);
                            break;
                        }
                        break;
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
        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener = this.mOnOnStarPhoneMoreInfoClickListener;
        if (onOnStarPhoneMoreInfoClickListener != null) {
            onOnStarPhoneMoreInfoClickListener.numberClick(i);
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener;
        int id = view.getId();
        if (id == R.id.gm_onstar_input_del) {
            TextView textView = this.gm_onstar_input;
            if (textView != null) {
                textView.setText("");
            }
            this.mInputNumStr = "";
        } else if (id == R.id.tv_onstar_phone_hand_on && (onOnStarPhoneMoreInfoClickListener = this.mOnOnStarPhoneMoreInfoClickListener) != null) {
            onOnStarPhoneMoreInfoClickListener.reDial();
        }
        CommUtil.playBeep();
        return true;
    }
}
