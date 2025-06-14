package com.hzbhd.canbus.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.PanelKeyLvAdapter;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PanelKeyActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0016R\"\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/hzbhd/canbus/activity/PanelKeyActivity;", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemClickInterface;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemLongClickInterface;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemTouchInterface;", "()V", "mLeftList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "mPanelKeyLvAdapter", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter;", "mSet", "Lcom/hzbhd/canbus/ui_set/PanelKeyPageUiSet;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "position", "", "onItemLongClick", "onItemTouch", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "refreshUi", "bundle", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PanelKeyActivity extends AbstractBaseActivity implements PanelKeyLvAdapter.ItemClickInterface, PanelKeyLvAdapter.ItemLongClickInterface, PanelKeyLvAdapter.ItemTouchInterface {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<String> mLeftList;
    private PanelKeyLvAdapter mPanelKeyLvAdapter;
    private PanelKeyPageUiSet mSet;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_key);
        initView();
    }

    private final void initView() {
        List<String> list;
        PanelKeyActivity panelKeyActivity = this;
        this.mSet = getUiMgrInterface(panelKeyActivity).getPanelKeyPageUiSet(panelKeyActivity);
        this.mLeftList = new ArrayList<>();
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet != null && (list = panelKeyPageUiSet.getList()) != null) {
            ArrayList<String> arrayList = this.mLeftList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.addAll(list);
        }
        this.mPanelKeyLvAdapter = new PanelKeyLvAdapter(panelKeyActivity, this.mLeftList, this, this, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_list);
        PanelKeyPageUiSet panelKeyPageUiSet2 = this.mSet;
        Intrinsics.checkNotNull(panelKeyPageUiSet2);
        recyclerView.setLayoutManager(new GridLayoutManager((Context) panelKeyActivity, panelKeyPageUiSet2.getCount(), 1, false));
        ((RecyclerView) _$_findCachedViewById(R.id.rv_list)).setAdapter(this.mPanelKeyLvAdapter);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemClickInterface
    public void onItemClick(int position) {
        OnPanelKeyPositionListener onPanelKeyPositionListener;
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelKeyPositionListener = panelKeyPageUiSet.getOnPanelKeyPositionListener()) == null) {
            return;
        }
        onPanelKeyPositionListener.click(position);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemLongClickInterface
    public void onItemLongClick(int position) {
        OnPanelLongKeyPositionListener onPanelLongKeyPositionListener;
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelLongKeyPositionListener = panelKeyPageUiSet.getOnPanelLongKeyPositionListener()) == null) {
            return;
        }
        onPanelLongKeyPositionListener.longClick(position);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemTouchInterface
    public void onItemTouch(int position, MotionEvent event) {
        OnPanelKeyPositionTouchListener onPanelKeyPositionTouchListener;
        Intrinsics.checkNotNullParameter(event, "event");
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelKeyPositionTouchListener = panelKeyPageUiSet.getOnPanelKeyPositionTouchListener()) == null) {
            return;
        }
        onPanelKeyPositionTouchListener.onTouch(position, event);
    }
}
