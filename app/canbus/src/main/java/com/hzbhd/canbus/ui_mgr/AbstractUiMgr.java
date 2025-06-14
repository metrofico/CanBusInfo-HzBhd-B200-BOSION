package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AbstractUiMgr implements UiMgrInterface {
    private Context mContext;
    private PageUiSet mPageUiSet;

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        return null;
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
    }

    private String getJsonContent(Context context) {
        return CommUtil.getAssetsString(context, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(context) + ".json");
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public PageUiSet getPageUiSet(Context context) {
        this.mContext = context;
        if (this.mPageUiSet == null) {
            this.mPageUiSet = (PageUiSet) new Gson().fromJson(getJsonContent(context), PageUiSet.class);
        }
        return this.mPageUiSet;
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public MainPageUiSet getMainUiSet(Context context) {
        return getPageUiSet(context).getMainPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public AirPageUiSet getAirUiSet(Context context) {
        return getPageUiSet(context).getAirPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public SettingPageUiSet getSettingUiSet(Context context) {
        return getPageUiSet(context).getSettingPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public TirePageUiSet getTireUiSet(Context context) {
        return getPageUiSet(context).getTirePageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public DriverDataPageUiSet getDriverDataPageUiSet(Context context) {
        return getPageUiSet(context).getDriverDataPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public OnStartPageUiSet getOnStartPageUiSet(Context context) {
        return getPageUiSet(context).getOnStartPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public HybirdPageUiSet getHybirdPageUiSet(Context context) {
        return getPageUiSet(context).getHybirdPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        return getPageUiSet(context).getOriginalCarDevicePageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public ParkPageUiSet getParkPageUiSet(Context context) {
        return getPageUiSet(context).getParkPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public SyncPageUiSet getSyncPageUiSet(Context context) {
        return getPageUiSet(context).getSyncPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public PanelKeyPageUiSet getPanelKeyPageUiSet(Context context) {
        return getPageUiSet(context).getPanelKeyPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public AmplifierPageUiSet getAmplifierPageUiSet(Context context) {
        return getPageUiSet(context).getAmplifierPageUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public BubbleUiSet getBubbleUiSet(Context context) {
        return getPageUiSet(context).getBubbleUiSet();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public WarningPageUiSet getWarningPageUiSet(Context context) {
        return getPageUiSet(context).getWarningPageUiSet();
    }

    protected void sendData(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    protected int getCurrentCarId() {
        return SelectCanTypeUtil.getCurrentCanDiffId();
    }

    protected int getEachId() {
        return CanbusConfig.INSTANCE.getEachId();
    }

    protected void showToast(int i) {
        Toast.makeText(this.mContext, i, 0).show();
    }

    protected void disableThisApp(Context context) {
        SelectCanTypeUtil.disableApp(context, HzbhdComponentName.CanbusCarInfoMainActivity);
    }

    protected void enableThisApp(Context context) {
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.CanbusCarInfoMainActivity);
    }

    protected void removeFrontAirButton(Context context, int i, int i2, int i3) {
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(lineBtnAction[i]));
        if (arrayList.size() > i3) {
            arrayList.remove(i2);
        }
        lineBtnAction[i] = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    protected void removeFrontAirButtonByName(Context context, String str) {
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        for (int i = 0; i < lineBtnAction.length; i++) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(lineBtnAction[i]));
            if (arrayList.remove(str)) {
                lineBtnAction[i] = (String[]) arrayList.toArray(new String[arrayList.size()]);
                return;
            }
        }
    }

    protected void removeRearAirButton(Context context, int i, int[] iArr) {
        String[][] lineBtnAction = getAirUiSet(context).getRearArea().getLineBtnAction();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(lineBtnAction[i]));
        for (int i2 = 0; i2 < iArr.length; i2++) {
            arrayList.remove(iArr[i2] - i2);
        }
        lineBtnAction[i] = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    protected void removeRearAirButtonByName(Context context, String str) {
        String[][] lineBtnAction = getAirUiSet(context).getRearArea().getLineBtnAction();
        for (int i = 0; i < lineBtnAction.length; i++) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(lineBtnAction[i]));
            if (arrayList.remove(str)) {
                lineBtnAction[i] = (String[]) arrayList.toArray(new String[arrayList.size()]);
                return;
            }
        }
    }

    protected void removeSettingLeftItem(Context context, int i, int i2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (list.size() > i2) {
                list.remove(i);
            }
        }
    }

    protected void removeSettingLeftItemByIndexList(Context context, int[] iArr) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        int length = iArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            for (SettingPageUiSet.ListBean listBean : list) {
                if (i2 == iArr[i]) {
                    strArr[i] = listBean.getTitleSrn();
                }
                i2++;
            }
        }
        removeSettingLeftItemByNameList(context, strArr);
    }

    protected void removeSettingLeftItemByIndexRange(Context context, int i, int i2) {
        int i3 = (i2 - i) + 1;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = i;
            i++;
        }
        removeSettingLeftItemByIndexList(context, iArr);
    }

    protected void removeSettingLeftItemByNameList(Context context, String[] strArr) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (String str : strArr) {
            Iterator<SettingPageUiSet.ListBean> it = list.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getTitleSrn())) {
                    it.remove();
                }
            }
        }
    }

    protected void removeSettingRightItemByNameList(Context context, String[] strArr) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (String str : strArr) {
            Iterator<SettingPageUiSet.ListBean> it = list.iterator();
            while (it.hasNext()) {
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = it.next().getItemList().iterator();
                while (it2.hasNext()) {
                    if (str.equals(it2.next().getTitleSrn())) {
                        it2.remove();
                    }
                }
            }
        }
    }

    protected void remvoeSettingLeftItemByName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getTitleSrn())) {
                list.remove(i);
            }
        }
    }

    protected void removeSettingRightItem(Context context, int i, int i2, int i3) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        for (int i4 = 0; i4 < list.get(i).getItemList().size(); i4++) {
            if (list.get(i).getItemList().size() > i3) {
                list.get(i).getItemList().remove(i2);
            }
        }
    }

    protected void removeDriveData(Context context, int i, int i2) {
        List<DriverDataPageUiSet.Page> list = getDriverDataPageUiSet(context).getList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (list.size() > i2) {
                list.remove(i);
            }
        }
    }

    protected void removeDriveData(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<DriverDataPageUiSet.Page> list = getDriverDataPageUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getHeadTitleSrn())) {
                list.remove(i);
            }
        }
    }

    protected void removeDrivigDateItem(Context context, int i, int i2, int i3) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i4 = 0; i4 < list.get(i).getItemList().size(); i4++) {
            if (list.get(i).getItemList().size() > i3) {
                list.get(i).getItemList().remove(i2);
            }
        }
    }

    protected void removeDriveDateItemForTitles(Context context, String[] strArr) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (String str : strArr) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                Iterator<DriverDataPageUiSet.Page.Item> it2 = it.next().getItemList().iterator();
                while (it2.hasNext()) {
                    if (str.equals(it2.next().getTitleSrn())) {
                        it2.remove();
                    }
                }
            }
        }
    }

    protected void removeDriveDataPagesByHeadTitles(Context context, String... strArr) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (String str : strArr) {
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                if (str.equals(list.get(i).getHeadTitleSrn())) {
                    list.remove(i);
                    break;
                }
                i++;
            }
        }
    }

    protected void removePanelBtnKeyByName(Context context, String str) {
        getPanelKeyPageUiSet(context).getList().remove(str);
    }

    protected void removeMainPrjBtn(Context context, int i, int i2) {
        List<String> btnAction = getMainUiSet(context).getBtnAction();
        if (btnAction.size() > i2) {
            btnAction.remove(i);
        }
    }

    protected void removeMainPrjBtnByName(Context context, String str) {
        getMainUiSet(context).getBtnAction().remove(str);
    }

    protected void playBeep() {
        CommUtil.playBeep();
    }
}
