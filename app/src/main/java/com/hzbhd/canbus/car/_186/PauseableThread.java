package com.hzbhd.canbus.car._186;

import com.hzbhd.canbus.CanbusMsgSender;


class PauseableThread extends Thread {
    private boolean isPaused;
    private int selectPos;

    PauseableThread() {
    }

    public synchronized void pauseThread() {
        this.isPaused = true;
    }

    public synchronized void resumeThread() {
        this.isPaused = false;
        notify();
    }

    public synchronized void setSelectPos(int i) {
        this.selectPos = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    if (this.isPaused) {
                        wait();
                    }
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte) this.selectPos});
                sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
