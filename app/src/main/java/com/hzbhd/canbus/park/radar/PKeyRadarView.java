package com.hzbhd.canbus.park.radar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class PKeyRadarView extends RelativeLayout {
    private final ImageView back_radar_left;
    private final ImageView back_radar_left_mid;
    private final ImageView back_radar_right;
    private final ImageView back_radar_right_mid;
    private final ImageView front_radar_left;
    private final ImageView front_radar_left_mid;
    private final ImageView front_radar_right;
    private final ImageView front_radar_right_mid;
    private final View view;

    public PKeyRadarView(Context context) {
        this(context, null);
    }

    public PKeyRadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PKeyRadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_p_key_radar, this, true);
        this.view = viewInflate;
        this.front_radar_right = viewInflate.findViewById(R.id.front_radar_right);
        this.front_radar_right_mid = this.view.findViewById(R.id.front_radar_right_mid);
        this.front_radar_left_mid = this.view.findViewById(R.id.front_radar_left_mid);
        this.front_radar_left = this.view.findViewById(R.id.front_radar_left);
        this.back_radar_right = this.view.findViewById(R.id.back_radar_right);
        this.back_radar_right_mid = this.view.findViewById(R.id.back_radar_right_mid);
        this.back_radar_left_mid = this.view.findViewById(R.id.back_radar_left_mid);
        this.back_radar_left = this.view.findViewById(R.id.back_radar_left);
    }

    public void refreshFrontRadar(int i, int i2, int i3, int i4, int i5) {
        if (i < 10) {
            switch (i) {
                case 3:
                case 4:
                    setFrontLeftData(i2 * 3);
                    setFrontLeftMidData(i3 * 3);
                    setFrontRightMidData(i4 * 3);
                    setFrontRightData(i5 * 3);
                    return;
                case 5:
                    setFrontLeftData(i2 * 2);
                    setFrontLeftMidData(i3 * 2);
                    setFrontRightMidData(i4 * 2);
                    setFrontRightData(i5 * 2);
                    return;
                case 6:
                    setFrontLeftData(10);
                    setFrontLeftMidData(10);
                    setFrontRightMidData(10);
                    setFrontRightData(10);
                    return;
                case 7:
                    setFrontLeftData(10);
                    setFrontLeftMidData(10);
                    setFrontRightMidData(10);
                    setFrontRightData(10);
                    return;
                default:
                    setFrontLeftData(i2);
                    setFrontLeftMidData(i3);
                    setFrontRightMidData(i4);
                    setFrontRightData(i5);
                    return;
            }
        }

        // Lógica cuando i >= 10
        double factor = i / 10.0;
        double[] thresholds = new double[11];
        for (int j = 1; j <= 10; j++) {
            thresholds[j] = j * factor;
        }

        setFrontLeftData(calculateRadarLevel(i2, thresholds));
        setFrontLeftMidData(calculateRadarLevel(i3, thresholds));
        setFrontRightMidData(calculateRadarLevel(i4, thresholds));
        setFrontRightData(calculateRadarLevel(i5, thresholds));
    }

    private int calculateRadarLevel(int value, double[] thresholds) {
        if (value <= 0) return 0;
        for (int j = 1; j < thresholds.length; j++) {
            if (value <= thresholds[j]) return j;
        }
        return 10;
    }

   /* public void refreshFrontRadar(int i, int i2, int i3, int i4, int i5) {
        if (i <= 10) {
            if (i >= 10) {
                setFrontLeftData(i2);
                setFrontLeftMidData(i3);
                setFrontRightMidData(i4);
                setFrontRightData(i5);
                return;
            }
            if (i == 3) {
                setFrontLeftData(i2 * 3);
                setFrontLeftMidData(i3 * 3);
                setFrontRightMidData(i4 * 3);
                setFrontRightData(i5 * 3);
                return;
            }
            if (i == 4) {
                setFrontLeftData(i2 * 3);
                setFrontLeftMidData(i3 * 3);
                setFrontRightMidData(i4 * 3);
                setFrontRightData(i5 * 3);
                return;
            }
            if (i == 5) {
                setFrontLeftData(i2 * 2);
                setFrontLeftMidData(i3 * 2);
                setFrontRightMidData(i4 * 2);
                setFrontRightData(i5 * 2);
                return;
            }
            if (i == 6) {
                if (i < 3) {
                    setFrontLeftData(i2 * 2);
                } else if (i == 4) {
                    setFrontLeftData(7);
                } else if (i == 5) {
                    setFrontLeftData(9);
                } else if (i == 6) {
                    setFrontLeftData(10);
                }
                if (i < 3) {
                    setFrontLeftMidData(i3 * 2);
                } else if (i == 4) {
                    setFrontLeftMidData(7);
                } else if (i == 5) {
                    setFrontLeftMidData(9);
                } else if (i == 6) {
                    setFrontLeftMidData(10);
                }
                if (i < 3) {
                    setFrontRightMidData(i4 * 2);
                } else if (i == 4) {
                    setFrontRightMidData(7);
                } else if (i == 5) {
                    setFrontRightMidData(9);
                } else if (i == 6) {
                    setFrontRightMidData(10);
                }
                if (i < 3) {
                    setFrontRightData(i5 * 2);
                    return;
                }
                if (i == 4) {
                    setFrontRightData(7);
                    return;
                } else if (i == 5) {
                    setFrontRightData(9);
                    return;
                } else {
                    if (i == 6) {
                        setFrontRightData(10);
                        return;
                    }
                    return;
                }
            }
            if (i != 7) {
                setFrontLeftData(i2);
                setFrontLeftMidData(i3);
                setFrontRightMidData(i4);
                setFrontRightData(i5);
                return;
            }
            if (i < 2) {
                setFrontLeftData(i2 * 2);
            } else if (i == 4) {
                setFrontLeftData(5);
            } else if (i == 5) {
                setFrontLeftData(7);
            } else if (i == 6) {
                setFrontLeftData(9);
            } else if (i == 7) {
                setFrontLeftData(10);
            }
            if (i < 2) {
                setFrontLeftMidData(i3 * 2);
            } else if (i == 4) {
                setFrontLeftMidData(5);
            } else if (i == 5) {
                setFrontLeftMidData(7);
            } else if (i == 6) {
                setFrontLeftMidData(9);
            } else if (i == 7) {
                setFrontLeftData(10);
            }
            if (i < 2) {
                setFrontRightMidData(i4 * 2);
            } else if (i == 4) {
                setFrontRightMidData(5);
            } else if (i == 5) {
                setFrontRightMidData(7);
            } else if (i == 6) {
                setFrontRightMidData(9);
            } else if (i == 7) {
                setFrontLeftData(10);
            }
            if (i < 2) {
                setFrontRightData(i5 * 2);
                return;
            }
            if (i == 4) {
                setFrontRightData(5);
                return;
            }
            if (i == 5) {
                setFrontRightData(7);
                return;
            } else if (i == 6) {
                setFrontRightData(9);
                return;
            } else {
                if (i == 7) {
                    setFrontLeftData(10);
                    return;
                }
                return;
            }
        }
        double d = i / 10.0f;
        double d2 = 2.0d * d;
        double d3 = 3.0d * d;
        double d4 = 4.0d * d;
        double d5 = 5.0d * d;
        double d6 = 6.0d * d;
        double d7 = 7.0d * d;
        double d8 = 8.0d * d;
        double d9 = 9.0d * d;
        double d10 = 10.0d * d;
        if (i2 <= 0 || i2 > d) {
            double d11 = i2;
            if (d11 > d && d11 <= d2) {
                setFrontLeftData(2);
            } else if (d11 > d2 && d11 <= d3) {
                setFrontLeftData(3);
            } else if (d11 > d3 && d11 <= d4) {
                setFrontLeftData(4);
            } else if (d11 > d4 && d11 <= d5) {
                setFrontLeftData(5);
            } else if (d11 > d5 && d11 <= d6) {
                setFrontLeftData(6);
            } else if (d11 > d6 && d11 <= d7) {
                setFrontLeftData(7);
            } else if (d11 > d7 && d11 <= d8) {
                setFrontLeftData(8);
            } else if (d11 > d8 && d11 <= d9) {
                setFrontLeftData(9);
            } else if (d11 > d9 && d11 <= d10) {
                setFrontLeftData(10);
            }
        } else {
            setFrontLeftData(1);
        }
        if (i3 <= 0 || i3 > d) {
            double d12 = i3;
            if (d12 > d && d12 <= d2) {
                setFrontLeftMidData(2);
            } else if (d12 > d2 && d12 <= d3) {
                setFrontLeftMidData(3);
            } else if (d12 > d3 && d12 <= d4) {
                setFrontLeftMidData(4);
            } else if (d12 > d4 && d12 <= d5) {
                setFrontLeftMidData(5);
            } else if (d12 > d5 && d12 <= d6) {
                setFrontLeftMidData(6);
            } else if (d12 > d6 && d12 <= d7) {
                setFrontLeftMidData(7);
            } else if (d12 > d7 && d12 <= d8) {
                setFrontLeftMidData(8);
            } else if (d12 > d8 && d12 <= d9) {
                setFrontLeftMidData(9);
            } else if (d12 > d9 && d12 <= d10) {
                setFrontLeftMidData(10);
            }
        } else {
            setFrontLeftMidData(1);
        }
        if (i4 <= 0 || i4 > d) {
            double d13 = i4;
            if (d13 > d && d13 <= d2) {
                setFrontRightMidData(2);
            } else if (d13 > d2 && d13 <= d3) {
                setFrontRightMidData(3);
            } else if (d13 > d3 && d13 <= d4) {
                setFrontRightMidData(4);
            } else if (d13 > d4 && d13 <= d5) {
                setFrontRightMidData(5);
            } else if (d13 > d5 && d13 <= d6) {
                setFrontRightMidData(6);
            } else if (d13 > d6 && d13 <= d7) {
                setFrontRightMidData(7);
            } else if (d13 > d7 && d13 <= d8) {
                setFrontRightMidData(8);
            } else if (d13 > d8 && d13 <= d9) {
                setFrontRightMidData(9);
            } else if (d13 > d9 && d13 <= d10) {
                setFrontRightMidData(10);
            }
        } else {
            setFrontRightMidData(1);
        }
        if (i5 > 0 && i5 <= d) {
            setFrontRightData(1);
            return;
        }
        double d14 = i5;
        if (d14 > d && d14 <= d2) {
            setFrontRightData(2);
            return;
        }
        if (d14 > d2 && d14 <= d3) {
            setFrontRightData(3);
            return;
        }
        if (d14 > d3 && d14 <= d4) {
            setFrontRightData(4);
            return;
        }
        if (d14 > d4 && d14 <= d5) {
            setFrontRightData(5);
            return;
        }
        if (d14 > d5 && d14 <= d6) {
            setFrontRightData(6);
            return;
        }
        if (d14 > d6 && d14 <= d7) {
            setFrontRightData(7);
            return;
        }
        if (d14 > d7 && d14 <= d8) {
            setFrontRightData(8);
            return;
        }
        if (d14 > d8 && d14 <= d9) {
            setFrontRightData(9);
        } else {
            if (d14 <= d9 || d14 > d10) {
                return;
            }
            setFrontRightData(10);
        }
    }*/

    private void setFrontRightData(int i) {
        if (i == 1) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_1);
            return;
        }
        if (i == 2) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_2);
            return;
        }
        if (i == 3) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_3);
            return;
        }
        if (i == 4) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_4);
            return;
        }
        if (i == 5) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_5);
            return;
        }
        if (i == 6) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_6);
            return;
        }
        if (i == 7) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_7);
            return;
        }
        if (i == 8) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_8);
            return;
        }
        if (i == 9) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_9);
        } else if (i >= 10) {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_10);
        } else {
            this.front_radar_right.setBackgroundResource(R.drawable.front_radar_right_0);
        }
    }

    private void setFrontRightMidData(int i) {
        if (i == 1) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_1);
            return;
        }
        if (i == 2) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_2);
            return;
        }
        if (i == 3) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_3);
            return;
        }
        if (i == 4) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_4);
            return;
        }
        if (i == 5) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_5);
            return;
        }
        if (i == 6) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_6);
            return;
        }
        if (i == 7) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_7);
            return;
        }
        if (i == 8) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_8);
            return;
        }
        if (i == 9) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_9);
        } else if (i >= 10) {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_10);
        } else {
            this.front_radar_right_mid.setBackgroundResource(R.drawable.front_radar_right_mid_0);
        }
    }

    private void setFrontLeftMidData(int i) {
        if (i == 1) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_1);
            return;
        }
        if (i == 2) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_2);
            return;
        }
        if (i == 3) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_3);
            return;
        }
        if (i == 4) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_4);
            return;
        }
        if (i == 5) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_5);
            return;
        }
        if (i == 6) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_6);
            return;
        }
        if (i == 7) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_7);
            return;
        }
        if (i == 8) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_8);
            return;
        }
        if (i == 9) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_9);
        } else if (i >= 10) {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_10);
        } else {
            this.front_radar_left_mid.setBackgroundResource(R.drawable.front_radar_left_mid_0);
        }
    }

    private void setFrontLeftData(int i) {
        if (i == 1) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_1);
            return;
        }
        if (i == 2) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_2);
            return;
        }
        if (i == 3) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_3);
            return;
        }
        if (i == 4) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_4);
            return;
        }
        if (i == 5) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_5);
            return;
        }
        if (i == 6) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_6);
            return;
        }
        if (i == 7) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_7);
            return;
        }
        if (i == 8) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_8);
            return;
        }
        if (i == 9) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_9);
        } else if (i >= 10) {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_10);
        } else {
            this.front_radar_left.setBackgroundResource(R.drawable.front_radar_left_0);
        }
    }

    public void refreshRearRadar(int i, int i2, int i3, int i4, int i5) {
        if (i <= 10) {
            if (i >= 10) {
                setRearLeftData(i2);
                setRearLeftMidData(i3);
                setRearRightMidData(i4);
                setRearRightData(i5);
                return;
            }
            if (i == 3) {
                setRearLeftData(i2 * 3);
                setRearLeftMidData(i3 * 3);
                setRearRightMidData(i4 * 3);
                setRearRightData(i5 * 3);
                return;
            }
            if (i == 4) {
                setRearLeftData(i2 * 3);
                setRearLeftMidData(i3 * 3);
                setRearRightMidData(i4 * 3);
                setRearRightData(i5 * 3);
                return;
            }
            if (i == 5) {
                setRearLeftData(i2 * 2);
                setRearLeftMidData(i3 * 2);
                setRearRightMidData(i4 * 2);
                setRearRightData(i5 * 2);
                return;
            }
            if (i == 6) {
                if (i < 3) {
                    setRearLeftData(i2 * 2);
                } else if (i == 4) {
                    setRearLeftData(7);
                } else if (i == 5) {
                    setRearLeftData(9);
                } else if (i == 6) {
                    setRearLeftData(10);
                }
                if (i < 3) {
                    setRearLeftMidData(i3 * 2);
                } else if (i == 4) {
                    setRearLeftMidData(7);
                } else if (i == 5) {
                    setRearLeftMidData(9);
                } else if (i == 6) {
                    setRearLeftMidData(10);
                }
                if (i < 3) {
                    setRearRightMidData(i4 * 2);
                } else if (i == 4) {
                    setRearRightMidData(7);
                } else if (i == 5) {
                    setRearRightMidData(9);
                } else if (i == 6) {
                    setRearRightMidData(10);
                }
                if (i < 3) {
                    setRearRightData(i5 * 2);
                    return;
                }
                if (i == 4) {
                    setRearRightData(7);
                    return;
                } else if (i == 5) {
                    setRearRightData(9);
                    return;
                } else {
                    if (i == 6) {
                        setRearRightData(10);
                        return;
                    }
                    return;
                }
            }
            if (i != 7) {
                setRearLeftData(i2);
                setRearLeftMidData(i3);
                setRearRightMidData(i4);
                setRearRightData(i5);
                return;
            }
            if (i < 2) {
                setRearLeftData(i2 * 2);
            } else if (i == 4) {
                setRearLeftData(5);
            } else if (i == 5) {
                setRearLeftData(7);
            } else if (i == 6) {
                setRearLeftData(9);
            } else if (i == 7) {
                setRearLeftData(10);
            }
            if (i < 2) {
                setRearLeftMidData(i3 * 2);
            } else if (i == 4) {
                setRearLeftMidData(5);
            } else if (i == 5) {
                setRearLeftMidData(7);
            } else if (i == 6) {
                setRearLeftMidData(9);
            } else if (i == 7) {
                setRearLeftData(10);
            }
            if (i < 2) {
                setRearRightMidData(i4 * 2);
            } else if (i == 4) {
                setRearRightMidData(5);
            } else if (i == 5) {
                setRearRightMidData(7);
            } else if (i == 6) {
                setRearRightMidData(9);
            } else if (i == 7) {
                setRearLeftData(10);
            }
            if (i < 2) {
                setRearRightData(i5 * 2);
                return;
            }
            if (i == 4) {
                setRearRightData(5);
                return;
            }
            if (i == 5) {
                setRearRightData(7);
                return;
            } else if (i == 6) {
                setRearRightData(9);
                return;
            } else {
                if (i == 7) {
                    setRearLeftData(10);
                    return;
                }
                return;
            }
        }
        double d = i / 10.0f;
        double d2 = 2.0d * d;
        double d3 = 3.0d * d;
        double d4 = 4.0d * d;
        double d5 = 5.0d * d;
        double d6 = 6.0d * d;
        double d7 = 7.0d * d;
        double d8 = 8.0d * d;
        double d9 = 9.0d * d;
        double d10 = 10.0d * d;
        if (i2 <= 0 || i2 > d) {
            double d11 = i2;
            if (d11 > d && d11 <= d2) {
                setRearLeftData(2);
            } else if (d11 > d2 && d11 <= d3) {
                setRearLeftData(3);
            } else if (d11 > d3 && d11 <= d4) {
                setRearLeftData(4);
            } else if (d11 > d4 && d11 <= d5) {
                setRearLeftData(5);
            } else if (d11 > d5 && d11 <= d6) {
                setRearLeftData(6);
            } else if (d11 > d6 && d11 <= d7) {
                setRearLeftData(7);
            } else if (d11 > d7 && d11 <= d8) {
                setRearLeftData(8);
            } else if (d11 > d8 && d11 <= d9) {
                setRearLeftData(9);
            } else if (d11 > d9 && d11 <= d10) {
                setRearLeftData(10);
            }
        } else {
            setRearLeftData(1);
        }
        if (i3 <= 0 || i3 > d) {
            double d12 = i3;
            if (d12 > d && d12 <= d2) {
                setRearLeftMidData(2);
            } else if (d12 > d2 && d12 <= d3) {
                setRearLeftMidData(3);
            } else if (d12 > d3 && d12 <= d4) {
                setRearLeftMidData(4);
            } else if (d12 > d4 && d12 <= d5) {
                setRearLeftMidData(5);
            } else if (d12 > d5 && d12 <= d6) {
                setRearLeftMidData(6);
            } else if (d12 > d6 && d12 <= d7) {
                setRearLeftMidData(7);
            } else if (d12 > d7 && d12 <= d8) {
                setRearLeftMidData(8);
            } else if (d12 > d8 && d12 <= d9) {
                setRearLeftMidData(9);
            } else if (d12 > d9 && d12 <= d10) {
                setRearLeftMidData(10);
            }
        } else {
            setRearLeftMidData(1);
        }
        if (i4 <= 0 || i4 > d) {
            double d13 = i4;
            if (d13 > d && d13 <= d2) {
                setRearRightMidData(2);
            } else if (d13 > d2 && d13 <= d3) {
                setRearRightMidData(3);
            } else if (d13 > d3 && d13 <= d4) {
                setRearRightMidData(4);
            } else if (d13 > d4 && d13 <= d5) {
                setRearRightMidData(5);
            } else if (d13 > d5 && d13 <= d6) {
                setRearRightMidData(6);
            } else if (d13 > d6 && d13 <= d7) {
                setRearRightMidData(7);
            } else if (d13 > d7 && d13 <= d8) {
                setRearRightMidData(8);
            } else if (d13 > d8 && d13 <= d9) {
                setRearRightMidData(9);
            } else if (d13 > d9 && d13 <= d10) {
                setRearRightMidData(10);
            }
        } else {
            setRearRightMidData(1);
        }
        if (i5 > 0 && i5 <= d) {
            setRearRightData(1);
            return;
        }
        double d14 = i5;
        if (d14 > d && d14 <= d2) {
            setRearRightData(2);
            return;
        }
        if (d14 > d2 && d14 <= d3) {
            setRearRightData(3);
            return;
        }
        if (d14 > d3 && d14 <= d4) {
            setRearRightData(4);
            return;
        }
        if (d14 > d4 && d14 <= d5) {
            setRearRightData(5);
            return;
        }
        if (d14 > d5 && d14 <= d6) {
            setRearRightData(6);
            return;
        }
        if (d14 > d6 && d14 <= d7) {
            setRearRightData(7);
            return;
        }
        if (d14 > d7 && d14 <= d8) {
            setRearRightData(8);
            return;
        }
        if (d14 > d8 && d14 <= d9) {
            setRearRightData(9);
        } else {
            if (d14 <= d9 || d14 > d10) {
                return;
            }
            setRearRightData(10);
        }
    }

    private void setRearRightData(int i) {
        if (i == 1) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_1);
            return;
        }
        if (i == 2) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_2);
            return;
        }
        if (i == 3) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_3);
            return;
        }
        if (i == 4) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_4);
            return;
        }
        if (i == 5) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_5);
            return;
        }
        if (i == 6) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_6);
            return;
        }
        if (i == 7) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_7);
            return;
        }
        if (i == 8) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_8);
            return;
        }
        if (i == 9) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_9);
        } else if (i >= 10) {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_10);
        } else {
            this.back_radar_right.setBackgroundResource(R.drawable.back_radar_right_0);
        }
    }

    private void setRearRightMidData(int i) {
        if (i == 1) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_1);
            return;
        }
        if (i == 2) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_2);
            return;
        }
        if (i == 3) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_3);
            return;
        }
        if (i == 4) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_4);
            return;
        }
        if (i == 5) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_5);
            return;
        }
        if (i == 6) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_6);
            return;
        }
        if (i == 7) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_7);
            return;
        }
        if (i == 8) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_8);
            return;
        }
        if (i == 9) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_9);
        } else if (i >= 10) {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_10);
        } else {
            this.back_radar_right_mid.setBackgroundResource(R.drawable.back_radar_right_mid_0);
        }
    }

    private void setRearLeftMidData(int i) {
        if (i == 1) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_1);
            return;
        }
        if (i == 2) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_2);
            return;
        }
        if (i == 3) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_3);
            return;
        }
        if (i == 4) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_4);
            return;
        }
        if (i == 5) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_5);
            return;
        }
        if (i == 6) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_6);
            return;
        }
        if (i == 7) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_7);
            return;
        }
        if (i == 8) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_8);
            return;
        }
        if (i == 9) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_9);
        } else if (i >= 10) {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_10);
        } else {
            this.back_radar_left_mid.setBackgroundResource(R.drawable.back_radar_left_mid_0);
        }
    }

    private void setRearLeftData(int i) {
        if (i == 1) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_1);
            return;
        }
        if (i == 2) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_2);
            return;
        }
        if (i == 3) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_3);
            return;
        }
        if (i == 4) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_4);
            return;
        }
        if (i == 5) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_5);
            return;
        }
        if (i == 6) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_6);
            return;
        }
        if (i == 7) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_7);
            return;
        }
        if (i == 8) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_8);
            return;
        }
        if (i == 9) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_9);
        } else if (i >= 10) {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_10);
        } else {
            this.back_radar_left.setBackgroundResource(R.drawable.back_radar_left_0);
        }
    }
}
