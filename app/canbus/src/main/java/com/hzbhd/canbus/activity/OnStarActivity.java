package com.hzbhd.canbus.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.fragment.OnStartMainFragment;
import com.hzbhd.canbus.fragment.OnStartNavigationFragment;
import com.hzbhd.canbus.fragment.OnStartPhoneFragment;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.fragment.OnStartWirelessFragment;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.commontools.SourceConstantsDef;

/* loaded from: classes.dex */
public class OnStarActivity extends AbstractBaseActivity {
    public static final String BUNDLE_ONSTAR_WHAT = "bundle_onstar_what";
    public static final String BUNDLE_OPEN_FRAGMENT = "bundle_open_fragment";
    public static final int ON_STAR_NAVIGATION_WHAT = 1003;
    public static final int ON_STAR_PHONE_MORE_INFO_WHAT = 1004;
    public static final int ON_STAR_PHONE_WHAT = 1001;
    public static final int ON_STAR_SIMPLE_WHAT = 1005;
    public static final int ON_STAR_WIRELESS_WHAT = 1002;
    private static final String TAG = "OnStarActivity";
    private Class mClass;
    private FragmentManager mFragmentManager;
    private FrameLayout mFrameLayout;
    private OnStartMainFragment mOnStartMainFragment;
    private OnStartNavigationFragment mOnStartNavigationFragment;
    private OnStartPhoneFragment mOnStartPhoneFragment;
    private OnStartPhoneMoreInfoFragment mOnStartPhoneMoreInfoFragment;
    private OnStartWirelessFragment mOnStartWirelessFragment;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_on_star);
        findViews();
        initViews();
        openFragment();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestAudioChannel();
    }

    private void findViews() {
        this.mFrameLayout = (FrameLayout) findViewById(R.id.fl_content);
    }

    private void initViews() {
        this.mFragmentManager = getFragmentManager();
        this.mOnStartMainFragment = new OnStartMainFragment();
        showFragment(OnStartMainFragment.class);
    }

    public void showFragment(Class cls) {
        Fragment fragment;
        this.mClass = cls;
        if (cls == OnStartMainFragment.class) {
            if (this.mOnStartMainFragment == null) {
                this.mOnStartMainFragment = new OnStartMainFragment();
            }
            fragment = this.mOnStartMainFragment;
        } else if (cls == OnStartPhoneFragment.class) {
            if (this.mOnStartPhoneFragment == null) {
                this.mOnStartPhoneFragment = new OnStartPhoneFragment();
            }
            fragment = this.mOnStartPhoneFragment;
        } else if (cls == OnStartNavigationFragment.class) {
            if (this.mOnStartNavigationFragment == null) {
                this.mOnStartNavigationFragment = new OnStartNavigationFragment();
            }
            fragment = this.mOnStartNavigationFragment;
        } else if (cls == OnStartWirelessFragment.class) {
            if (this.mOnStartWirelessFragment == null) {
                this.mOnStartWirelessFragment = new OnStartWirelessFragment();
            }
            fragment = this.mOnStartWirelessFragment;
        } else if (cls == OnStartPhoneMoreInfoFragment.class) {
            if (this.mOnStartPhoneMoreInfoFragment == null) {
                this.mOnStartPhoneMoreInfoFragment = new OnStartPhoneMoreInfoFragment();
            }
            fragment = this.mOnStartPhoneMoreInfoFragment;
        } else {
            fragment = null;
        }
        if (fragment == null) {
            LogUtil.showLog("no match fragment to show");
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.fl_content, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(cls.getName());
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        releaseAudioChannel();
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            if (fragmentManager.getBackStackEntryCount() == 1) {
                finish();
                return;
            } else {
                super.onBackPressed();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        try {
            switch (bundle.getInt(BUNDLE_ONSTAR_WHAT)) {
                case 1001:
                    OnStartPhoneFragment onStartPhoneFragment = this.mOnStartPhoneFragment;
                    if (onStartPhoneFragment != null) {
                        onStartPhoneFragment.refreshUi(bundle);
                        break;
                    }
                    break;
                case 1002:
                    OnStartWirelessFragment onStartWirelessFragment = this.mOnStartWirelessFragment;
                    if (onStartWirelessFragment != null) {
                        onStartWirelessFragment.refreshUi(bundle);
                        break;
                    }
                    break;
                case 1003:
                    OnStartNavigationFragment onStartNavigationFragment = this.mOnStartNavigationFragment;
                    if (onStartNavigationFragment != null) {
                        onStartNavigationFragment.refreshUi(bundle);
                        break;
                    }
                    break;
                case 1004:
                    OnStartPhoneMoreInfoFragment onStartPhoneMoreInfoFragment = this.mOnStartPhoneMoreInfoFragment;
                    if (onStartPhoneMoreInfoFragment != null) {
                        onStartPhoneMoreInfoFragment.refreshUi(bundle);
                        break;
                    }
                    break;
                case 1005:
                    hide();
                    this.mOnStartPhoneFragment.refreshUi(bundle);
                    break;
            }
        } catch (Exception unused) {
        }
    }

    public void hide() {
        this.mOnStartPhoneFragment.dialog.setVisibility(4);
        this.mOnStartPhoneFragment.input.setVisibility(4);
    }

    private void openFragment() {
        Class cls;
        if (getIntent() == null || (cls = (Class) getIntent().getSerializableExtra(BUNDLE_OPEN_FRAGMENT)) == null) {
            return;
        }
        showFragment(cls);
    }

    private void requestAudioChannel() {
        CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, null);
    }

    private void releaseAudioChannel() {
        CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
    }
}
