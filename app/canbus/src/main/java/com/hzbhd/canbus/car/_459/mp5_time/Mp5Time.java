package com.hzbhd.canbus.car._459.mp5_time;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public class Mp5Time {
    private int MP5_TIME_RE_SEAND;
    private Calendar calendar;
    private LocalDateTime dateTime;
    private Handler handler;
    private boolean reDoTag;
    private SimpleDateFormat sdf;

    private static class Holder {
        private static final Mp5Time INSTANCE = new Mp5Time();

        private Holder() {
        }
    }

    private Mp5Time() {
        this.MP5_TIME_RE_SEAND = 222;
        this.reDoTag = false;
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        this.calendar = Calendar.getInstance();
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._459.mp5_time.Mp5Time.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (Mp5Time.this.reDoTag && message.what == Mp5Time.this.MP5_TIME_RE_SEAND) {
                    Mp5Time.this.calendar.setTime(new Date(System.currentTimeMillis()));
                    int i = Mp5Time.this.calendar.get(1);
                    int i2 = Mp5Time.this.calendar.get(2) + 1;
                    int i3 = Mp5Time.this.calendar.get(5);
                    int i4 = Mp5Time.this.calendar.get(11);
                    int i5 = Mp5Time.this.calendar.get(12);
                    int i6 = Mp5Time.this.calendar.get(13);
                    Mp5Time.this.calendar.get(14);
                    CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, -26, 40, (byte) (i6 * 4), (byte) i5, (byte) i4, (byte) i2, (byte) (i3 * 4), (byte) (i - 1985), 0, 0, 1});
                    Mp5Time.this.reDo();
                }
            }
        };
    }

    public static Mp5Time getInstance() {
        return Holder.INSTANCE;
    }

    public void start() {
        if (this.reDoTag) {
            return;
        }
        this.reDoTag = true;
        reDo();
    }

    public void stop() {
        this.reDoTag = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reDo() {
        this.handler.sendEmptyMessageDelayed(this.MP5_TIME_RE_SEAND, 1000L);
    }
}
