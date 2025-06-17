package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.SelectCanTypeLastLvAdapter;
import com.hzbhd.canbus.adapter.SelectCarCateLvAdapter;
import com.hzbhd.canbus.adapter.SelectProtocolCompanyLvAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.util.AnimationUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.view.MarqueeTextView;
import com.hzbhd.cantype.CanTypeMap;
import com.hzbhd.cantype.CanTypeMapEng;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class SelectCanTypeActivity extends Activity implements View.OnClickListener {
    private static final long ANIMATION_PARAM_CAR_MODEL_DURATION = 500;
    private static final float ANIMATION_PARAM_CAR_MODEL_HIDE_WEIGHT = 0.0f;
    private static final float ANIMATION_PARAM_CAR_MODEL_SHOW_WEIGHT = 1.7f;
    private static final long ANIMATION_PARAM_COMPANY_DURATION = 500;
    private CanTypeMap mCanTypeMap;
    private SelectCarCateLvAdapter mCarCategoryAdapter;
    private List<String> mCarCategoryList;
    private RecyclerView mCarCategoryLv;
    private SelectCanTypeLastLvAdapter mCarModelAndYearsAdapter;
    private List<CanTypeAllEntity> mCarModelAndYearsList;
    private RecyclerView mCarModelAndYearsLv;
    private AnimationUtil mCarModelAnimationUtil;
    private AnimationUtil mCompanyAnimationUtil;
    private int mCompanyItemDotWidth;
    private int mCompanyLayoutWidth;
    private MarqueeTextView mCurrentSelectedTv;
    private Dialog mDialog;
    private StringBuffer mInputContentSb;
    private TextView mInputIdTv;
    private int mOrientation;
    private SelectProtocolCompanyLvAdapter mProtocolCompanyAdapter;
    private List<String> mProtocolCompanyList;
    private RecyclerView mProtocolCompanyLv;
    private String mSelectCarCategory;
    private String mSelectProtocolCompany;
    private Button update_canbus;
    // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.2
// com.hzbhd.canbus.adapter.SelectProtocolCompanyLvAdapter.ItemCallBackInterface
    private final SelectProtocolCompanyLvAdapter.ItemCallBackInterface mProtocolCompanyItemSelect = i -> {
        if (SelectCanTypeActivity.this.playAnimationOnCompanyClick()) {
            return;
        }
        SelectCanTypeActivity.this.mProtocolCompanyAdapter.setSelectItem(SelectCanTypeActivity.this.mProtocolCompanyList.get(i));
        SelectCanTypeActivity.this.mProtocolCompanyAdapter.notifyDataSetChanged();
        SelectCanTypeActivity selectCanTypeActivity = SelectCanTypeActivity.this;
        selectCanTypeActivity.mSelectProtocolCompany = selectCanTypeActivity.mProtocolCompanyList.get(i);
        if (TextUtils.isEmpty(SelectCanTypeActivity.this.mSelectProtocolCompany)) {
            return;
        }
        SelectCanTypeActivity.this.mCarCategoryList.clear();
        SelectCanTypeActivity.this.mCarCategoryList.addAll(SelectCanTypeActivity.this.getCantypeMap().getCanTypeMap().get(SelectCanTypeActivity.this.mProtocolCompanyList.get(i)).keySet());
        SelectCanTypeActivity.this.mCarCategoryAdapter.notifyDataSetChanged();
        SelectCanTypeActivity.this.mCarModelAndYearsList.clear();
        SelectCanTypeActivity.this.mCarModelAndYearsAdapter.notifyDataSetChanged();
    };
    private final SelectCarCateLvAdapter.ItemCallBackInterface mCarCategoryItemSelect = new SelectCarCateLvAdapter.ItemCallBackInterface() { // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.3
        @Override // com.hzbhd.canbus.adapter.SelectCarCateLvAdapter.ItemCallBackInterface
        public void select(int i) {
            SelectCanTypeActivity.this.playAnimationOnCateporyClick();
            SelectCanTypeActivity.this.mCarCategoryAdapter.setSelectItem(SelectCanTypeActivity.this.mCarCategoryList.get(i));
            SelectCanTypeActivity.this.mCarCategoryAdapter.notifyDataSetChanged();
            SelectCanTypeActivity selectCanTypeActivity = SelectCanTypeActivity.this;
            selectCanTypeActivity.mSelectCarCategory = selectCanTypeActivity.mCarCategoryList.get(i);
            if (TextUtils.isEmpty(SelectCanTypeActivity.this.mSelectCarCategory)) {
                return;
            }
            SelectCanTypeActivity.this.mCarModelAndYearsList.clear();
            for (Integer integer : SelectCanTypeActivity.this.getCantypeMap().getCanTypeMap().get(SelectCanTypeActivity.this.mSelectProtocolCompany).get(SelectCanTypeActivity.this.mSelectCarCategory)) {
                CanTypeAllEntity carCategoryList = SelectCanTypeActivity.this.getCarCategoryList(integer);
                if (carCategoryList != null) {
                    SelectCanTypeActivity.this.mCarModelAndYearsList.add(carCategoryList);
                }
            }
            SelectCanTypeActivity.this.mCarModelAndYearsAdapter.notifyDataSetChanged();
        }
    };
    private final SelectCanTypeLastLvAdapter.ItemCallBackInterface mCarModelItemSelect = new SelectCanTypeLastLvAdapter.ItemCallBackInterface() { // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.4
        @Override // com.hzbhd.canbus.adapter.SelectCanTypeLastLvAdapter.ItemCallBackInterface
        public void select(int i) {
            SelectCanTypeActivity.this.mCarModelAndYearsAdapter.notifyDataSetChanged();
            SelectCanTypeUtil.showDialogToUpdate(SelectCanTypeActivity.this, SelectCanTypeActivity.this.mCarModelAndYearsList.get(i), null);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public CanTypeMap getCantypeMap() {
        if (this.mCanTypeMap == null) {
            if (LogUtil.log5()) {
                LogUtil.d("getCantypeMap: " + getResources().getConfiguration().locale.getLanguage());
            }
            if (getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
                this.mCanTypeMap = new CanTypeMap();
            } else {
                this.mCanTypeMap = new CanTypeMapEng();
            }
        }
        return this.mCanTypeMap;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((MyApplication) getApplication()).addActivity(this);
        setContentView(R.layout.activity_select_can_type);
        findViews();
        initViews();
        initAnimationParams();
    }

    private void findViews() {
        this.mProtocolCompanyLv = findViewById(R.id.lv_0);
        this.mCarCategoryLv = findViewById(R.id.lv_1);
        this.mCarModelAndYearsLv = findViewById(R.id.lv_2);
        this.mCurrentSelectedTv = findViewById(R.id.tv_selected);
        Button button = findViewById(R.id.update_canbus);
        this.update_canbus = button;
        // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity$$ExternalSyntheticLambda0
// android.view.View.OnClickListener
        button.setOnClickListener(SelectCanTypeActivity.this::onClick);
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [com.hzbhd.canbus.activity.SelectCanTypeActivity$1] */
    private void initViews() {
        this.mProtocolCompanyList = new ArrayList();
        this.mProtocolCompanyAdapter = new SelectProtocolCompanyLvAdapter(this, this.mProtocolCompanyList, this.mProtocolCompanyItemSelect);
        this.mProtocolCompanyLv.setLayoutManager(new LinearLayoutManager(this));
        this.mProtocolCompanyLv.setAdapter(this.mProtocolCompanyAdapter);
        this.mCarCategoryList = new ArrayList();
        this.mCarCategoryAdapter = new SelectCarCateLvAdapter(this, this.mCarCategoryList, this.mCarCategoryItemSelect);
        this.mCarCategoryLv.setLayoutManager(new LinearLayoutManager(this));
        this.mCarCategoryLv.setAdapter(this.mCarCategoryAdapter);
        this.mCarModelAndYearsList = new ArrayList();
        this.mCarModelAndYearsAdapter = new SelectCanTypeLastLvAdapter(this, this.mCarModelAndYearsList, this.mCarModelItemSelect);
        this.mCarModelAndYearsLv.setLayoutManager(new LinearLayoutManager(this));
        this.mCarModelAndYearsLv.setAdapter(this.mCarModelAndYearsAdapter);
        new Thread() { // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SelectCanTypeActivity.this.mProtocolCompanyList.addAll(SelectCanTypeActivity.this.getCantypeMap().getCanTypeMap().keySet());
                SelectCanTypeActivity.this.runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SelectCanTypeActivity.this.mProtocolCompanyAdapter.notifyDataSetChanged();
                    }
                });
                final String currentSelectCanType = SelectCanTypeActivity.this.getCurrentSelectCanType();
                SelectCanTypeActivity.this.runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SelectCanTypeActivity.this.mCurrentSelectedTv.setText(currentSelectCanType);
                    }
                });
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentSelectCanType() {
        String str = " ";
        ArrayList<CanTypeAllEntity> list = CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(this)).getList();
        if (list == null || list.size() == 0) {
            com.hzbhd.canbus.util.LogUtil.showLog("list==null||list.size() ==0");
            return "";
        }
        int differentId = CanBus.INSTANCE.getDifferentId();
        try {
            if (getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
                str = list.get(differentId).getProtocol_company() + " " + list.get(differentId).getCar_category() + " " + list.get(differentId).getCan_type_id();
            } else {
                str = list.get(differentId).getEnglish_protocol_company() + " " + list.get(differentId).getEnglish_car_category() + " " + list.get(differentId).getCan_type_id();
            }
            return str;
        } catch (Exception unused) {
            if (getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
                return list.get(0).getProtocol_company() + str + list.get(0).getCar_category() + str + list.get(0).getCan_type_id();
            }
            return list.get(0).getEnglish_protocol_company() + str + list.get(0).getEnglish_car_category() + str + list.get(0).getCan_type_id();
        }
    }

    public CanTypeAllEntity getCarCategoryList(int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList<CanTypeAllEntity> canTypeAllEntityByCompanyAndCategory = CanTypeUtil.INSTANCE.getCanTypeAllEntityByCompanyAndCategory(this.mSelectProtocolCompany, this.mSelectCarCategory, i);
        if (canTypeAllEntityByCompanyAndCategory.isEmpty()) {
            return null;
        }
        for (CanTypeAllEntity canTypeAllEntity : canTypeAllEntityByCompanyAndCategory) {
            if (getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
                String str = canTypeAllEntity.getCar_model() + "  " + canTypeAllEntity.getYears() + "\n";
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            } else {
                String str2 = canTypeAllEntity.getEnglish_car_model() + "  " + canTypeAllEntity.getEnglish_years() + "\n";
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        CanTypeAllEntity canTypeAllEntity2 = new CanTypeAllEntity(canTypeAllEntityByCompanyAndCategory.get(0).getProtocol_company(), canTypeAllEntityByCompanyAndCategory.get(0).getCar_category(), canTypeAllEntityByCompanyAndCategory.get(0).getCar_model(), canTypeAllEntityByCompanyAndCategory.get(0).getYears(), canTypeAllEntityByCompanyAndCategory.get(0).getEnglish_protocol_company(), canTypeAllEntityByCompanyAndCategory.get(0).getEnglish_car_category(), canTypeAllEntityByCompanyAndCategory.get(0).getEnglish_car_model(), canTypeAllEntityByCompanyAndCategory.get(0).getEnglish_years(), canTypeAllEntityByCompanyAndCategory.get(0).getCan_type_id(), canTypeAllEntityByCompanyAndCategory.get(0).getCan_different_id(), canTypeAllEntityByCompanyAndCategory.get(0).getEach_can_id(), canTypeAllEntityByCompanyAndCategory.get(0).getBaud_rate(), canTypeAllEntityByCompanyAndCategory.get(0).getIs_app_handle_key(), canTypeAllEntityByCompanyAndCategory.get(0).getPack_type(), canTypeAllEntityByCompanyAndCategory.get(0).getIs_show_app(), canTypeAllEntityByCompanyAndCategory.get(0).getIs_use_new_camera(), canTypeAllEntityByCompanyAndCategory.get(0).getIs_use_new_app(), canTypeAllEntityByCompanyAndCategory.get(0).getDescription());
        canTypeAllEntity2.setDescription(getCanTypIdStr(arrayList));
        return canTypeAllEntity2;
    }

    private String getCanTypIdStr(List<String> list) {
        if (list == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            stringBuffer.append(" ");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_0) {
            input(0);
        } else if (id == R.id.btn_1) {
            input(1);
        } else if (id == R.id.btn_2) {
            input(2);
        } else if (id == R.id.btn_3) {
            input(3);
        } else if (id == R.id.btn_4) {
            input(4);
        } else if (id == R.id.btn_5) {
            input(5);
        } else if (id == R.id.btn_6) {
            input(6);
        } else if (id == R.id.btn_7) {
            input(7);
        } else if (id == R.id.btn_8) {
            input(8);
        } else if (id == R.id.btn_9) {
            input(9);
        } else if (id == R.id.btn_delete) {
            if (this.mInputContentSb != null) {
                deleteInputContent();
                refreshInputTv();
            }
        } else if (id == R.id.btn_input_id) {
            showInputIdDialog();
        } else if (id == R.id.btn_select_can_type_id) {
            startActivity(new Intent(this, SelectIdActivity.class));
        } else if (id == R.id.btn_switch) {
            StringBuffer stringBuffer = this.mInputContentSb;
            if (stringBuffer != null) {
                try {
                    int iIntValue = Integer.parseInt(stringBuffer.toString());
                    if (iIntValue == 709394) {
                        boolean boolValue = SharePreUtil.getBoolValue(this, Constant.SHARE_PRE_IS_DEBUG_MODEL, false);
                        String str = boolValue ? "Close Test Model" : "Open Test Model";
                        SharePreUtil.setBoolValue(this, Constant.SHARE_PRE_IS_DEBUG_MODEL, !boolValue);
                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                        this.mDialog.dismiss();
                    } else {
                        deleteInputContent();
                        searchInList(iIntValue);
                    }
                } catch (Exception unused) {
                    Toast.makeText(this, "Transform error", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (id == R.id.update_canbus) {
            SystemPropertiesUtils.set("com.hzbhd.upgrade.cloud", "1");
            Intent intent = new Intent();
            intent.setComponent(HzbhdComponentName.CanBusOnlineUpdateActivity);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private void searchInList(int i) {
        ArrayList<CanTypeAllEntity> list = CanTypeUtil.INSTANCE.getCanType(i).getList();
        if (list.isEmpty()) {
            Toast.makeText(this, "Id Not Found", Toast.LENGTH_SHORT).show();
        } else {
            SelectCanTypeUtil.showDialogToUpdate(this, list.get(0), null);
            this.mDialog.dismiss();
        }
    }

    private void input(int i) {
        if (this.mInputContentSb == null) {
            this.mInputContentSb = new StringBuffer();
        }
        this.mInputContentSb.append(i);
        refreshInputTv();
    }

    private void refreshInputTv() {
        this.mInputIdTv.setText(this.mInputContentSb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteInputContent() {
        StringBuffer stringBuffer = this.mInputContentSb;
        if (stringBuffer != null) {
            stringBuffer.delete(0, stringBuffer.length());
        }
    }

    public void showInputIdDialog() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_dialog_input_id, null);
        this.mInputIdTv = viewInflate.findViewById(R.id.tv_id);
        Button button = viewInflate.findViewById(R.id.btn_0);
        Button button2 = viewInflate.findViewById(R.id.btn_1);
        Button button3 = viewInflate.findViewById(R.id.btn_2);
        Button button4 = viewInflate.findViewById(R.id.btn_3);
        Button button5 = viewInflate.findViewById(R.id.btn_4);
        Button button6 = viewInflate.findViewById(R.id.btn_5);
        Button button7 = viewInflate.findViewById(R.id.btn_6);
        Button button8 = viewInflate.findViewById(R.id.btn_7);
        Button button9 = viewInflate.findViewById(R.id.btn_8);
        Button button10 = viewInflate.findViewById(R.id.btn_9);
        Button button11 = viewInflate.findViewById(R.id.btn_delete);
        Button button12 = viewInflate.findViewById(R.id.btn_switch);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        Dialog dialog = new Dialog(this, R.style.InputIdDialogTheme);
        this.mDialog = dialog;
        dialog.setContentView(viewInflate);
        this.mDialog.show();
        // from class: com.hzbhd.canbus.activity.SelectCanTypeActivity.5
// android.content.DialogInterface.OnDismissListener
        this.mDialog.setOnDismissListener(dialogInterface -> SelectCanTypeActivity.this.deleteInputContent());
    }

    private boolean isOrientationPort() {
        return this.mOrientation == 1;
    }

    private void initAnimationParams() {
        this.mOrientation = getResources().getConfiguration().orientation;
        if (isOrientationPort()) {
            this.mCompanyLayoutWidth = getResources().getDimensionPixelOffset(R.dimen.item_select_protocol_company_layout_width);
            this.mCompanyItemDotWidth = getResources().getDimensionPixelOffset(R.dimen.item_select_protocol_company_dot_width);
            AnimationUtil animationUtil = new AnimationUtil();
            this.mCompanyAnimationUtil = animationUtil;
            animationUtil.setView(this.mProtocolCompanyLv);
            AnimationUtil animationUtil2 = new AnimationUtil();
            this.mCarModelAnimationUtil = animationUtil2;
            animationUtil2.setView(this.mCarModelAndYearsLv);
            playAnimationOnCompanyClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean playAnimationOnCompanyClick() {
        if (!isOrientationPort()) {
            return false;
        }
        hideCarModel();
        return showProtocolCompany();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playAnimationOnCateporyClick() {
        if (isOrientationPort()) {
            hideProtocolCompany();
            showCarModel();
        }
    }

    private boolean showProtocolCompany() {
        return this.mCompanyAnimationUtil.playWidthAnimation(this.mCompanyLayoutWidth, ANIMATION_PARAM_COMPANY_DURATION);
    }

    private void hideProtocolCompany() {
        this.mCompanyAnimationUtil.playWidthAnimation(this.mCompanyItemDotWidth, ANIMATION_PARAM_COMPANY_DURATION);
    }

    private void showCarModel() {
        this.mCarModelAnimationUtil.playWeightAnimation(ANIMATION_PARAM_CAR_MODEL_SHOW_WEIGHT, ANIMATION_PARAM_CAR_MODEL_DURATION);
    }

    private void hideCarModel() {
        this.mCarModelAnimationUtil.playWeightAnimation(ANIMATION_PARAM_CAR_MODEL_HIDE_WEIGHT, ANIMATION_PARAM_CAR_MODEL_DURATION);
    }
}
