package com.hzbhd.canbus.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.R;
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
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;

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
        this.mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        this.mRlTimeProgress = (RelativeLayout) findViewById(R.id.rl_time_progress);
        this.mBottomBtnRv = (RecyclerView) findViewById(R.id.rv_bottom_btn);
        this.mRv = (RecyclerView) findViewById(R.id.rv_info);
        this.mPlayTimeTv = (TextView) findViewById(R.id.tv_play_time);
        this.mPb = (ProgressBar) findViewById(R.id.pb_play_time_progress);
        this.mTotalTimeTv = (TextView) findViewById(R.id.tv_total_time);
        this.mCdStatusTv = (TextView) findViewById(R.id.tv_cd_status_data);
        this.mDiscStatusTv = (TextView) findViewById(R.id.tv_cd_status_data2);
        this.mRunStateTv = (TextView) findViewById(R.id.tv_running_state_data);
        this.mCoverIv = (ImageView) findViewById(R.id.iv_cover);
        this.mSongListRv = (RecyclerView) findViewById(R.id.rv_song_list);
        this.mShowSongListIv = (ImageView) findViewById(R.id.iv_show_list);
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
            viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_original_car_device_top, (ViewGroup) null);
        } else {
            viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_original_car_device_top_n, (ViewGroup) null);
        }
        this.mTopLayout.addView(viewInflate);
        this.mTopRbv = (RowTopBtnView) findViewById(R.id.ll_top);
        this.mCoverIv.setVisibility(8);
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
                int i = 0;
                while (true) {
                    String[] strArr = this.mRowBtnAction;
                    if (i < strArr.length) {
                        String str = strArr[i];
                        str.hashCode();
                        char c = 65535;
                        switch (str.hashCode()) {
                            case -1715975548:
                                if (str.equals(OriginalBtnAction.SELECT_CD)) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case -1415634215:
                                if (str.equals(OriginalBtnAction.RDM_DISC)) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case -1415569083:
                                if (str.equals(OriginalBtnAction.RDM_FOLD)) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -1406322208:
                                if (str.equals(OriginalBtnAction.AUTO_P)) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case -1315390686:
                                if (str.equals(OriginalBtnAction.EXIT_CD)) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case -1268966290:
                                if (str.equals(OriginalBtnAction.FOLDER)) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case -1183792455:
                                if (str.equals(OriginalBtnAction.INSERT)) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case -854288633:
                                if (str.equals(OriginalBtnAction.REAR_CDC)) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case -841905119:
                                if (str.equals(OriginalBtnAction.PREV_DISC)) {
                                    c = '\b';
                                    break;
                                }
                                break;
                            case -136075545:
                                if (str.equals(OriginalBtnAction.DISC_SCAN)) {
                                    c = '\t';
                                    break;
                                }
                                break;
                            case 3169:
                                if (str.equals(OriginalBtnAction.CD)) {
                                    c = '\n';
                                    break;
                                }
                                break;
                            case 3681:
                                if (str.equals(OriginalBtnAction.ST)) {
                                    c = 11;
                                    break;
                                }
                                break;
                            case 99858:
                                if (str.equals(OriginalBtnAction.DVD)) {
                                    c = '\f';
                                    break;
                                }
                                break;
                            case 108272:
                                if (str.equals(OriginalBtnAction.MP3)) {
                                    c = '\r';
                                    break;
                                }
                                break;
                            case 112763:
                                if (str.equals(OriginalBtnAction.RDM)) {
                                    c = 14;
                                    break;
                                }
                                break;
                            case 112769:
                                if (str.equals(OriginalBtnAction.RDS)) {
                                    c = 15;
                                    break;
                                }
                                break;
                            case 113142:
                                if (str.equals(OriginalBtnAction.RPT)) {
                                    c = 16;
                                    break;
                                }
                                break;
                            case 117835:
                                if (str.equals(OriginalBtnAction.WMA)) {
                                    c = 17;
                                    break;
                                }
                                break;
                            case 3327275:
                                if (str.equals("lock")) {
                                    c = 18;
                                    break;
                                }
                                break;
                            case 3524221:
                                if (str.equals(OriginalBtnAction.SCAN)) {
                                    c = 19;
                                    break;
                                }
                                break;
                            case 92923732:
                                if (str.equals(OriginalBtnAction.AM_ST)) {
                                    c = 20;
                                    break;
                                }
                                break;
                            case 106858757:
                                if (str.equals("power")) {
                                    c = 21;
                                    break;
                                }
                                break;
                            case 108270587:
                                if (str.equals("radio")) {
                                    c = 22;
                                    break;
                                }
                                break;
                            case 109250952:
                                if (str.equals(OriginalBtnAction.SCANE)) {
                                    c = 23;
                                    break;
                                }
                                break;
                            case 293915491:
                                if (str.equals(OriginalBtnAction.FOLD_ADD)) {
                                    c = 24;
                                    break;
                                }
                                break;
                            case 293933314:
                                if (str.equals(OriginalBtnAction.FOLD_SUB)) {
                                    c = 25;
                                    break;
                                }
                                break;
                            case 436485570:
                                if (str.equals(OriginalBtnAction.RPT_TRACK)) {
                                    c = 26;
                                    break;
                                }
                                break;
                            case 844879422:
                                if (str.equals(OriginalBtnAction.RPT_DISC)) {
                                    c = 27;
                                    break;
                                }
                                break;
                            case 844944554:
                                if (str.equals(OriginalBtnAction.RPT_FOLD)) {
                                    c = 28;
                                    break;
                                }
                                break;
                            case 1042163292:
                                if (str.equals(OriginalBtnAction.PRESET_SELECT)) {
                                    c = 29;
                                    break;
                                }
                                break;
                            case 1062723499:
                                if (str.equals(OriginalBtnAction.RDM_OFF)) {
                                    c = 30;
                                    break;
                                }
                                break;
                            case 1085444827:
                                if (str.equals(OriginalBtnAction.REFRESH)) {
                                    c = 31;
                                    break;
                                }
                                break;
                            case 1142446977:
                                if (str.equals(OriginalBtnAction.PRESET_STORE)) {
                                    c = ' ';
                                    break;
                                }
                                break;
                            case 1216748385:
                                if (str.equals(OriginalBtnAction.NEXT_DISC)) {
                                    c = '!';
                                    break;
                                }
                                break;
                            case 1412737958:
                                if (str.equals(OriginalBtnAction.RPT_OFF)) {
                                    c = Typography.quote;
                                    break;
                                }
                                break;
                            case 1602773140:
                                if (str.equals(OriginalBtnAction.AUX_INSERT)) {
                                    c = '#';
                                    break;
                                }
                                break;
                            case 1922620715:
                                if (str.equals(OriginalBtnAction.PLAY_PAUSE)) {
                                    c = Typography.dollar;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
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
                            case '\b':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.prev_disc);
                                break;
                            case '\t':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.disc_scan);
                                break;
                            case '\n':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.cd);
                                break;
                            case 11:
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.st);
                                break;
                            case '\f':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.dvd);
                                break;
                            case '\r':
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
                            case ' ':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.preset_store);
                                break;
                            case '!':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.next_disc);
                                break;
                            case '\"':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.rpt_off);
                                break;
                            case '#':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.aux_insert);
                                break;
                            case '$':
                                getBtnTopItemView(i).turn(GeneralOriginalCarDeviceData.play_pause);
                                break;
                        }
                        i++;
                    }
                }
            }
            List<OriginalCarDeviceUpdateEntity> list2 = GeneralOriginalCarDeviceData.mList;
            if (list2 != null) {
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    this.mList.get(list2.get(i2).getIndex()).setValue(list2.get(i2).getValue());
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
        if (this.mSongListRv.getVisibility() == 8) {
            this.mSongListRv.setVisibility(0);
        } else {
            this.mSongListRv.setVisibility(8);
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
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                arrayList.remove((String) it.next());
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
        if (ArrayUtils.isEmpty(rowTopBtnAction)) {
            this.mTopLayout.setVisibility(8);
        } else {
            this.mTopLayout.setVisibility(0);
            this.mTopRbv.clean();
            this.mTopRbv.initButton(this, this.mRowBtnAction, this.mSet.isTopBtnCanScroll(), this.mSet.getOnOriginalTopBtnClickListeners());
        }
        String[] rowBottomBtnAction = this.mSet.getRowBottomBtnAction();
        this.mRowBottomBtnAction = rowBottomBtnAction;
        if (ArrayUtils.isEmpty(rowBottomBtnAction)) {
            this.mBottomBtnRv.setVisibility(8);
        } else {
            this.mBottomBtnRv.setVisibility(0);
            String[] strArr = this.mRowBottomBtnAction;
            this.mSpanCount = strArr.length;
            List<String> listAsList = Arrays.asList(strArr);
            if (getResources().getConfiguration().orientation == 1) {
                listAsList = getDisplayList();
            }
            OriginalBottomBtnLvAdapter originalBottomBtnLvAdapter = new OriginalBottomBtnLvAdapter(this, this.mRowBottomBtnAction, listAsList, this.mSet.getOnOriginalBottomBtnClickListeners());
            this.mOriginalBottomBtnLvAdapter = originalBottomBtnLvAdapter;
            this.mBottomBtnRv.setAdapter(originalBottomBtnLvAdapter);
            this.mBottomBtnRv.setLayoutManager(new GridLayoutManager((Context) this, this.mSpanCount, 1, false));
            this.mOriginalBottomBtnLvAdapter.notifyDataSetChanged();
        }
        if (this.mSet.isHaveSongList()) {
            this.mShowSongListIv.setVisibility(0);
            this.mSongList = new ArrayList();
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
            this.mShowSongListIv.setVisibility(4);
            this.mSongListRv.setVisibility(4);
        }
        if (this.mSet.isHavePlayTimeSeekBar()) {
            this.mRlTimeProgress.setVisibility(0);
        } else {
            this.mRlTimeProgress.setVisibility(4);
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
