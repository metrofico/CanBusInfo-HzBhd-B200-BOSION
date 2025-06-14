package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class WindSpeedView extends RelativeLayout {
    private ExecutorService executorService;
    private ImageView iv_wind_1;
    private ImageView iv_wind_2;
    private ImageView iv_wind_3;
    private ImageView iv_wind_4;
    private ImageView iv_wind_5;
    private ImageView iv_wind_6;
    private ImageView iv_wind_7;
    private List<ImageView> listImage;
    private View mView;

    public WindSpeedView(Context context) {
        this(context, null);
    }

    public WindSpeedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WindSpeedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listImage = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.mView = LayoutInflater.from(context).inflate(R.layout._291_wind_speed_view, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.iv_wind_1 = (ImageView) this.mView.findViewById(R.id.iv_wind_1);
        this.iv_wind_2 = (ImageView) this.mView.findViewById(R.id.iv_wind_2);
        this.iv_wind_3 = (ImageView) this.mView.findViewById(R.id.iv_wind_3);
        this.iv_wind_4 = (ImageView) this.mView.findViewById(R.id.iv_wind_4);
        this.iv_wind_5 = (ImageView) this.mView.findViewById(R.id.iv_wind_5);
        this.iv_wind_6 = (ImageView) this.mView.findViewById(R.id.iv_wind_6);
        this.iv_wind_7 = (ImageView) this.mView.findViewById(R.id.iv_wind_7);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.view.WindSpeedView.1
            @Override // java.lang.Runnable
            public void run() {
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_1);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_2);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_3);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_4);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_5);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_6);
                WindSpeedView.this.listImage.add(WindSpeedView.this.iv_wind_7);
            }
        });
    }

    public void setWindPower(int i) {
        if (i >= 0 && i <= 7) {
            for (int i2 = 0; i2 < this.listImage.size(); i2++) {
                if (i2 < i) {
                    this.listImage.get(i2).setVisibility(0);
                } else {
                    this.listImage.get(i2).setVisibility(8);
                }
            }
        }
    }
}
