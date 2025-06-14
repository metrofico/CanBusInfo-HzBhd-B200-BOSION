package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.OriginalBottomBtnLvAdapter;
import com.hzbhd.canbus.adapter.OriginalDataLvItemAdapter;
import com.hzbhd.canbus.adapter.SongListLvAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.view.OriginalTopBtnItemView;
import com.hzbhd.canbus.view.RowTopBtnView;
import com.hzbhd.commontools.SourceConstantsDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class OriginalCarDeviceActivity extends AbstractBaseActivity implements View.OnClickListener, SongListLvAdapter.SongItemClickInterface {
    private static final String TAG = "OriginalCarDeviceActivity";
    private RecyclerView mBottomBtnRv;
    private TextView mCdStatusTv;
    private ImageView mCoverIv;
    private TextView mDiscStatusTv;
    private DividerItemDecoration mDividerItemDecoration;
    private List<String> mIdealList;
    private List<OriginalCarDevicePageUiSet.Item> mList;
    private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener;
    private OriginalBottomBtnLvAdapter mOriginalBottomBtnLvAdapter;
    private OriginalDataLvItemAdapter mOriginalDataLvItemAdapter;
    private ProgressBar mPb;
    private TextView mPlayTimeTv;
    private RelativeLayout mRlTimeProgress;
    private String[] mRowBottomBtnAction;
    private String[] mRowBtnAction;
    private TextView mRunStateTv;
    private RecyclerView mRv;
    private List<String> mSecondaryList;
    private OriginalCarDevicePageUiSet mSet;
    private ImageView mShowSongListIv;
    private List<SongListEntity> mSongList;
    private SongListLvAdapter mSongListLvAdapter;
    private RecyclerView mSongListRv;
    private int mSpanCount;
    private RelativeLayout mTopLayout;
    private RowTopBtnView mTopRbv;
    private TextView mTotalTimeTv;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_original_car_device);
        findViews();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestAudioChannel();
        initData();
        initViews();
        refreshUi(null);
    }

    private void findViews() {
        this.mTopLayout = findViewById(R.id.top_layout);
        this.mRlTimeProgress = findViewById(R.id.rl_time_progress);
        this.mBottomBtnRv = findViewById(R.id.rv_bottom_btn);
        this.mRv = findViewById(R.id.rv_info);
        this.mPlayTimeTv = findViewById(R.id.tv_play_time);
        this.mPb = findViewById(R.id.pb_play_time_progress);
        this.mTotalTimeTv = findViewById(R.id.tv_total_time);
        this.mCdStatusTv = findViewById(R.id.tv_cd_status_data);
        this.mDiscStatusTv = findViewById(R.id.tv_cd_status_data2);
        this.mRunStateTv = findViewById(R.id.tv_running_state_data);
        this.mCoverIv = findViewById(R.id.iv_cover);
        this.mSongListRv = findViewById(R.id.rv_song_list);
        this.mShowSongListIv = findViewById(R.id.iv_show_list);
    }

    private void initData() {
        this.mIdealList = Arrays.asList("up", "left", OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, OriginalBtnAction.STOP, OriginalBtnAction.NEXT_DISC, "right", "down");
        this.mSecondaryList = Arrays.asList(OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, OriginalBtnAction.STOP, OriginalBtnAction.NEXT_DISC);
    }

    private void initViews() {
        View viewInflate;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgrInterface(this).getOriginalCarDevicePageUiSet(this);
        this.mSet = originalCarDevicePageUiSet;
        if (originalCarDevicePageUiSet == null) {
            runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.activity.OriginalCarDeviceActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    OriginalCarDeviceActivity.this.finish();
                }
            });
            return;
        }
        if (originalCarDevicePageUiSet.isTopBtnCanScroll()) {
            viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_original_car_device_top, null);
        } else {
            viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_original_car_device_top_n, null);
        }
        this.mTopLayout.addView(viewInflate);
        this.mTopRbv = findViewById(R.id.ll_top);
        this.mCoverIv.setVisibility(View.GONE);
        OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener = this.mSet.getOnOriginalCarDevicePageStatusListener();
        if (onOriginalCarDevicePageStatusListener != null) {
            onOriginalCarDevicePageStatusListener.onStatusChange(0);
        }
        this.mOnOriginalCarDeviceBackPressedListener = this.mSet.getOnOriginalCarDeviceBackPressedListener();
        buildMayChangeViews();
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.adapter.SongListLvAdapter.SongItemClickInterface
    public void onSongItemClick(int i) {
        OnOriginalSongItemClickListener onOriginalSongItemClickListener = this.mSet.getOnOriginalSongItemClickListener();
        if (onOriginalSongItemClickListener != null) {
            onOriginalSongItemClickListener.onSongItemClick(i);
        }
    }

    @Override // com.hzbhd.canbus.adapter.SongListLvAdapter.SongItemClickInterface
    public void onSongItemLongClick(int i) {
        OnOriginalSongItemClickListener onOriginalSongItemClickListener = this.mSet.getOnOriginalSongItemClickListener();
        if (onOriginalSongItemClickListener != null) {
            onOriginalSongItemClickListener.onSongItemLongClick(i);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        List<SongListEntity> list;
        if (isShouldRefreshUi()) {
            if (bundle != null && bundle.containsKey(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW) && bundle.getBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW)) {
                buildMayChangeViews();
            }
            if (this.mRowBtnAction != null && GeneralOriginalCarDeviceData.isTopBtnChange) {
                // Procesar cada acción de botón
                for (int i = 0; i < this.mRowBtnAction.length; i++) {
                    String str = this.mRowBtnAction[i];
                    processBtnAction(str, i);  // Se pasa el índice 'i' a la función
                }
            }
            List<OriginalCarDeviceUpdateEntity> deviceList = GeneralOriginalCarDeviceData.mList;
            if (deviceList != null) {
                for (OriginalCarDeviceUpdateEntity device : deviceList) {
                    this.mList.get(device.getIndex()).setValue(device.getValue());
                }
            }
            this.mPlayTimeTv.setText(GeneralOriginalCarDeviceData.startTime);
            this.mTotalTimeTv.setText(GeneralOriginalCarDeviceData.endTime);
            this.mCdStatusTv.setText(GeneralOriginalCarDeviceData.cdStatus);
            this.mRunStateTv.setText(GeneralOriginalCarDeviceData.runningState);
            this.mDiscStatusTv.setText(GeneralOriginalCarDeviceData.discStatus);
            this.mPb.setProgress(GeneralOriginalCarDeviceData.progress);
            this.mOriginalDataLvItemAdapter.notifyDataSetChanged();

            if (this.mSet.isHaveSongList() && (list = GeneralOriginalCarDeviceData.songList) != null && GeneralOriginalCarDeviceData.isSongListChange) {
                this.mSongList.clear();
                this.mSongList.addAll(list);
                this.mSongListLvAdapter.notifyDataSetChanged();
            }
        }
    }

    // Método para procesar las acciones de los botones
    private void processBtnAction(String str, int i) {
        int actionId = getActionIdFromString(str);
        switch (actionId) {
            case 0:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.select_cd);
                break;
            case 1:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rdm_disc);
                break;
            case 2:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rdm_fold);
                break;
            case 3:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.auto_p);
                break;
            case 4:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.exit_cd);
                break;
            case 5:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.folder);
                break;
            case 6:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.insert);
                break;
            case 7:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rear_cdc);
                break;
            case 8:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.prev_disc);
                break;
            case 9:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.disc_scan);
                break;
            case 10:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.cd);
                break;
            case 11:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.st);
                break;
            case 12:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.dvd);
                break;
            case 13:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.mp3);
                break;
            case 14:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rdm);
                break;
            case 15:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rds);
                break;
            case 16:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt);
                break;
            case 17:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.wma);
                break;
            case 18:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.lock);
                break;
            case 19:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.scan);
                break;
            case 20:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.am_st);
                break;
            case 21:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.power);
                break;
            case 22:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.radio);
                break;
            case 23:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.scane);
                break;
            case 24:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.fold_add);
                break;
            case 25:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.fold_sub);
                break;
            case 26:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt_track);
                break;
            case 27:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt_disc);
                break;
            case 28:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt_fold);
                break;
            case 29:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.preset_select);
                break;
            case 30:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rdm_off);
                break;
            case 31:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.refresh);
                break;
            case 32:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.preset_store);
                break;
            case 33:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.next_disc);
                break;
            case 34:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt_off);
                break;
            case 35:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.aux_insert);
                break;
            case 36:
                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.play_pause);
                break;
            default:
                // Acción desconocida, no hacer nada
                break;
        }
    }

    // Método que asigna un ID de acción basado en la cadena
    private int getActionIdFromString(String action) {
        switch (action) {
            case OriginalBtnAction.SELECT_CD:
                return 0;
            case OriginalBtnAction.RDM_DISC:
                return 1;
            case OriginalBtnAction.RDM_FOLD:
                return 2;
            case OriginalBtnAction.AUTO_P:
                return 3;
            case OriginalBtnAction.EXIT_CD:
                return 4;
            case OriginalBtnAction.FOLDER:
                return 5;
            case OriginalBtnAction.INSERT:
                return 6;
            case OriginalBtnAction.REAR_CDC:
                return 7;
            case OriginalBtnAction.PREV_DISC:
                return 8;
            case OriginalBtnAction.DISC_SCAN:
                return 9;
            case OriginalBtnAction.CD:
                return 10;
            case OriginalBtnAction.ST:
                return 11;
            case OriginalBtnAction.DVD:
                return 12;
            case OriginalBtnAction.MP3:
                return 13;
            case OriginalBtnAction.RDM:
                return 14;
            case OriginalBtnAction.RDS:
                return 15;
            case OriginalBtnAction.RPT:
                return 16;
            case OriginalBtnAction.WMA:
                return 17;
            case "lock":
                return 18;
            case OriginalBtnAction.SCAN:
                return 19;
            case OriginalBtnAction.AM_ST:
                return 20;
            case "power":
                return 21;
            case "radio":
                return 22;
            case OriginalBtnAction.SCANE:
                return 23;
            case OriginalBtnAction.FOLD_ADD:
                return 24;
            case OriginalBtnAction.FOLD_SUB:
                return 25;
            case OriginalBtnAction.RPT_TRACK:
                return 26;
            case OriginalBtnAction.RPT_DISC:
                return 27;
            case OriginalBtnAction.RPT_FOLD:
                return 28;
            case OriginalBtnAction.PRESET_SELECT:
                return 29;
            case OriginalBtnAction.RDM_OFF:
                return 30;
            case OriginalBtnAction.REFRESH:
                return 31;
            case OriginalBtnAction.PRESET_STORE:
                return 32;
            case OriginalBtnAction.NEXT_DISC:
                return 33;
            case OriginalBtnAction.RPT_OFF:
                return 34;
            case OriginalBtnAction.AUX_INSERT:
                return 35;
            case OriginalBtnAction.PLAY_PAUSE:
                return 36;
            default:
                return -1;  // Acción desconocida
        }
    }

    private OriginalTopBtnItemView getBtnTopItemView(int i) {
        return this.mTopRbv.getBtnItemView(i);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        releaseAudioChannel();
        OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener = this.mOnOriginalCarDeviceBackPressedListener;
        if (onOriginalCarDeviceBackPressedListener != null) {
            onOriginalCarDeviceBackPressedListener.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.iv_show_list) {
            return;
        }
        if (this.mSongListRv.getVisibility() == View.GONE) {
            this.mSongListRv.setVisibility(View.VISIBLE);
        } else {
            this.mSongListRv.setVisibility(View.GONE);
        }
    }

    private List<String> getDisplayList() {
        ArrayList<String> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : this.mRowBottomBtnAction) {
            if (this.mIdealList.contains(str)) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        if (arrayList.size() > 6) {
            ArrayList arrayList3 = new ArrayList();
            for (String str2 : arrayList) {
                if (this.mSecondaryList.contains(str2)) {
                    arrayList3.add(str2);
                }
            }
            for (Object o : arrayList3) {
                arrayList.remove((String) o);
            }
            arrayList2.addAll(arrayList3);
        }
        this.mSpanCount = arrayList.size();
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    private void buildMayChangeViews() {
        String[] rowTopBtnAction = this.mSet.getRowTopBtnAction();
        this.mRowBtnAction = rowTopBtnAction;
        if (rowTopBtnAction.length == 0) {
            this.mTopLayout.setVisibility(View.GONE);
        } else {
            this.mTopLayout.setVisibility(View.VISIBLE);
            this.mTopRbv.clean();
            this.mTopRbv.initButton(this, this.mRowBtnAction, this.mSet.isTopBtnCanScroll(), this.mSet.getOnOriginalTopBtnClickListeners());
        }
        String[] rowBottomBtnAction = this.mSet.getRowBottomBtnAction();
        this.mRowBottomBtnAction = rowBottomBtnAction;
        if (rowBottomBtnAction.length == 0) {
            this.mBottomBtnRv.setVisibility(View.GONE);
        } else {
            this.mBottomBtnRv.setVisibility(View.VISIBLE);
            String[] strArr = this.mRowBottomBtnAction;
            this.mSpanCount = strArr.length;
            List<String> listAsList = Arrays.asList(strArr);
            if (getResources().getConfiguration().orientation == 1) {
                listAsList = getDisplayList();
            }
            OriginalBottomBtnLvAdapter originalBottomBtnLvAdapter = new OriginalBottomBtnLvAdapter(this, this.mRowBottomBtnAction, listAsList, this.mSet.getOnOriginalBottomBtnClickListeners());
            this.mOriginalBottomBtnLvAdapter = originalBottomBtnLvAdapter;
            this.mBottomBtnRv.setAdapter(originalBottomBtnLvAdapter);
            this.mBottomBtnRv.setLayoutManager(new GridLayoutManager(this, this.mSpanCount, RecyclerView.VERTICAL, false));
            this.mOriginalBottomBtnLvAdapter.notifyDataSetChanged();
        }
        if (this.mSet.isHaveSongList()) {
            this.mShowSongListIv.setVisibility(View.VISIBLE);
            this.mSongList = new ArrayList<>();
            this.mSongListLvAdapter = new SongListLvAdapter(this.mSongList, this, this.mSet.isSongListShowIndex());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            if (this.mSongListRv.getLayoutManager() == null) {
                this.mSongListRv.setLayoutManager(linearLayoutManager);
            }
            if (this.mDividerItemDecoration == null) {
                this.mDividerItemDecoration = new DividerItemDecoration(this, 1);
            }
            this.mDividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.setting_divider, null));
            if (this.mSongListRv.getItemDecorationCount() == 0) {
                this.mSongListRv.addItemDecoration(this.mDividerItemDecoration);
            }
            this.mSongListRv.setAdapter(this.mSongListLvAdapter);
        } else {
            this.mShowSongListIv.setVisibility(View.INVISIBLE);
            this.mSongListRv.setVisibility(View.INVISIBLE);
        }
        if (this.mSet.isHavePlayTimeSeekBar()) {
            this.mRlTimeProgress.setVisibility(View.VISIBLE);
        } else {
            this.mRlTimeProgress.setVisibility(View.INVISIBLE);
        }
        this.mList = this.mSet.getItems();
        this.mOriginalDataLvItemAdapter = new OriginalDataLvItemAdapter(this, this.mList);
        this.mRv.setLayoutManager(new LinearLayoutManager(this));
        this.mRv.setAdapter(this.mOriginalDataLvItemAdapter);
    }

    private void requestAudioChannel() {
        CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, null);
    }

    private void releaseAudioChannel() {
        CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
    }
}
