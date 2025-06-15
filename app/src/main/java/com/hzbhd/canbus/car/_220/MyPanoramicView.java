package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;


public class MyPanoramicView extends RelativeLayout {
    int camSt;
    private Context mContext;
    boolean mListBtns;
    private View.OnClickListener mRearCameraButton;
    private Button switch_view;
    private ImageButton trumpchi_cam_360_btn_1;
    private ImageButton trumpchi_cam_360_btn_2;
    private ImageButton trumpchi_cam_360_btn_3;
    private ImageButton trumpchi_cam_360_btn_4;
    private ImageButton trumpchi_cam_360_btn_5;
    private ImageButton trumpchi_cam_360_btn_6;

    public MyPanoramicView(Context context) {
        super(context);
        this.mListBtns = false;
        this.camSt = 0;
        this.mRearCameraButton = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._220.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    int id = view.getId();
                    if (id != R.id.switch_view) {
                        switch (id) {
                            case R.id.trumpchi_cam_360_btn_1 /* 2131363570 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 1});
                                break;
                            case R.id.trumpchi_cam_360_btn_2 /* 2131363571 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 2});
                                break;
                            case R.id.trumpchi_cam_360_btn_3 /* 2131363572 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 3});
                                break;
                            case R.id.trumpchi_cam_360_btn_4 /* 2131363573 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 4});
                                break;
                            case R.id.trumpchi_cam_360_btn_5 /* 2131363574 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 5});
                                break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.mContext = context;
        Log.i("MyPanoramicView", "MyPanoramicView--220");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_220_view, this);
        this.trumpchi_cam_360_btn_1 = (ImageButton) viewInflate.findViewById(R.id.trumpchi_cam_360_btn_1);
        this.trumpchi_cam_360_btn_2 = (ImageButton) viewInflate.findViewById(R.id.trumpchi_cam_360_btn_2);
        this.trumpchi_cam_360_btn_3 = (ImageButton) viewInflate.findViewById(R.id.trumpchi_cam_360_btn_3);
        this.trumpchi_cam_360_btn_4 = (ImageButton) viewInflate.findViewById(R.id.trumpchi_cam_360_btn_4);
        this.trumpchi_cam_360_btn_5 = (ImageButton) viewInflate.findViewById(R.id.trumpchi_cam_360_btn_5);
        if (this.switch_view == null) {
            this.switch_view = (Button) viewInflate.findViewById(R.id.switch_view);
        }
        ImageButton imageButton = this.trumpchi_cam_360_btn_1;
        if (imageButton != null) {
            imageButton.setOnClickListener(this.mRearCameraButton);
        }
        ImageButton imageButton2 = this.trumpchi_cam_360_btn_2;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this.mRearCameraButton);
        }
        ImageButton imageButton3 = this.trumpchi_cam_360_btn_3;
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this.mRearCameraButton);
        }
        ImageButton imageButton4 = this.trumpchi_cam_360_btn_4;
        if (imageButton4 != null) {
            imageButton4.setOnClickListener(this.mRearCameraButton);
        }
        ImageButton imageButton5 = this.trumpchi_cam_360_btn_5;
        if (imageButton5 != null) {
            imageButton5.setOnClickListener(this.mRearCameraButton);
        }
        Button button = this.switch_view;
        if (button != null) {
            button.setOnClickListener(this.mRearCameraButton);
        }
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListBtns = false;
        this.camSt = 0;
        this.mRearCameraButton = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._220.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    int id = view.getId();
                    if (id != R.id.switch_view) {
                        switch (id) {
                            case R.id.trumpchi_cam_360_btn_1 /* 2131363570 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 1});
                                break;
                            case R.id.trumpchi_cam_360_btn_2 /* 2131363571 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 2});
                                break;
                            case R.id.trumpchi_cam_360_btn_3 /* 2131363572 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 3});
                                break;
                            case R.id.trumpchi_cam_360_btn_4 /* 2131363573 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 4});
                                break;
                            case R.id.trumpchi_cam_360_btn_5 /* 2131363574 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 5});
                                break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mListBtns = false;
        this.camSt = 0;
        this.mRearCameraButton = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._220.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    int id = view.getId();
                    if (id != R.id.switch_view) {
                        switch (id) {
                            case R.id.trumpchi_cam_360_btn_1 /* 2131363570 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 1});
                                break;
                            case R.id.trumpchi_cam_360_btn_2 /* 2131363571 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 2});
                                break;
                            case R.id.trumpchi_cam_360_btn_3 /* 2131363572 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 3});
                                break;
                            case R.id.trumpchi_cam_360_btn_4 /* 2131363573 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 4});
                                break;
                            case R.id.trumpchi_cam_360_btn_5 /* 2131363574 */:
                                Util.reportCanbusInfo(new byte[]{22, -57, 5});
                                break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    void updateUi() {
        int i = this.camSt;
        if (i == 1) {
            ImageButton imageButton = this.trumpchi_cam_360_btn_1;
            if (imageButton != null) {
                imageButton.setBackgroundResource(R.drawable.shape_frame_disable);
            }
            ImageButton imageButton2 = this.trumpchi_cam_360_btn_2;
            if (imageButton2 != null) {
                imageButton2.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton3 = this.trumpchi_cam_360_btn_3;
            if (imageButton3 != null) {
                imageButton3.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton4 = this.trumpchi_cam_360_btn_4;
            if (imageButton4 != null) {
                imageButton4.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton5 = this.trumpchi_cam_360_btn_5;
            if (imageButton5 != null) {
                imageButton5.setBackgroundResource(R.color.transparent);
                return;
            }
            return;
        }
        if (i == 2) {
            ImageButton imageButton6 = this.trumpchi_cam_360_btn_1;
            if (imageButton6 != null) {
                imageButton6.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton7 = this.trumpchi_cam_360_btn_2;
            if (imageButton7 != null) {
                imageButton7.setBackgroundResource(R.drawable.shape_frame_disable);
            }
            ImageButton imageButton8 = this.trumpchi_cam_360_btn_3;
            if (imageButton8 != null) {
                imageButton8.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton9 = this.trumpchi_cam_360_btn_4;
            if (imageButton9 != null) {
                imageButton9.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton10 = this.trumpchi_cam_360_btn_5;
            if (imageButton10 != null) {
                imageButton10.setBackgroundResource(R.color.transparent);
                return;
            }
            return;
        }
        if (i == 3) {
            ImageButton imageButton11 = this.trumpchi_cam_360_btn_1;
            if (imageButton11 != null) {
                imageButton11.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton12 = this.trumpchi_cam_360_btn_2;
            if (imageButton12 != null) {
                imageButton12.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton13 = this.trumpchi_cam_360_btn_3;
            if (imageButton13 != null) {
                imageButton13.setBackgroundResource(R.drawable.shape_frame_disable);
            }
            ImageButton imageButton14 = this.trumpchi_cam_360_btn_4;
            if (imageButton14 != null) {
                imageButton14.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton15 = this.trumpchi_cam_360_btn_5;
            if (imageButton15 != null) {
                imageButton15.setBackgroundResource(R.color.transparent);
                return;
            }
            return;
        }
        if (i == 4) {
            ImageButton imageButton16 = this.trumpchi_cam_360_btn_1;
            if (imageButton16 != null) {
                imageButton16.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton17 = this.trumpchi_cam_360_btn_2;
            if (imageButton17 != null) {
                imageButton17.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton18 = this.trumpchi_cam_360_btn_3;
            if (imageButton18 != null) {
                imageButton18.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton19 = this.trumpchi_cam_360_btn_4;
            if (imageButton19 != null) {
                imageButton19.setBackgroundResource(R.drawable.shape_frame_disable);
            }
            ImageButton imageButton20 = this.trumpchi_cam_360_btn_5;
            if (imageButton20 != null) {
                imageButton20.setBackgroundResource(R.color.transparent);
                return;
            }
            return;
        }
        if (i == 5) {
            ImageButton imageButton21 = this.trumpchi_cam_360_btn_1;
            if (imageButton21 != null) {
                imageButton21.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton22 = this.trumpchi_cam_360_btn_2;
            if (imageButton22 != null) {
                imageButton22.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton23 = this.trumpchi_cam_360_btn_3;
            if (imageButton23 != null) {
                imageButton23.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton24 = this.trumpchi_cam_360_btn_4;
            if (imageButton24 != null) {
                imageButton24.setBackgroundResource(R.color.transparent);
            }
            ImageButton imageButton25 = this.trumpchi_cam_360_btn_5;
            if (imageButton25 != null) {
                imageButton25.setBackgroundResource(R.drawable.shape_frame_disable);
                return;
            }
            return;
        }
        ImageButton imageButton26 = this.trumpchi_cam_360_btn_1;
        if (imageButton26 != null) {
            imageButton26.setBackgroundResource(R.color.transparent);
        }
        ImageButton imageButton27 = this.trumpchi_cam_360_btn_2;
        if (imageButton27 != null) {
            imageButton27.setBackgroundResource(R.color.transparent);
        }
        ImageButton imageButton28 = this.trumpchi_cam_360_btn_3;
        if (imageButton28 != null) {
            imageButton28.setBackgroundResource(R.color.transparent);
        }
        ImageButton imageButton29 = this.trumpchi_cam_360_btn_4;
        if (imageButton29 != null) {
            imageButton29.setBackgroundResource(R.color.transparent);
        }
        ImageButton imageButton30 = this.trumpchi_cam_360_btn_5;
        if (imageButton30 != null) {
            imageButton30.setBackgroundResource(R.color.transparent);
        }
    }
}
