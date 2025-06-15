package com.hzbhd.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class HandlerThreadPoolExecutor {
    private boolean task1Running;
    private boolean task2Running;
    private boolean task3Running;
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);

    private final HandlerThread handlerThread1;
    private final HandlerThread handlerThread2;
    private final HandlerThread handlerThread3;

    private final Handler threadHandler1;
    private final Handler threadHandler2;
    private final Handler threadHandler3;

    public HandlerThreadPoolExecutor() {
        this.handlerThread1 = new HandlerThread("back.1");
        this.handlerThread2 = new HandlerThread("back.2");
        this.handlerThread3 = new HandlerThread("back.3");

        this.handlerThread1.setPriority(8);
        this.handlerThread2.setPriority(8);
        this.handlerThread3.setPriority(8);

        this.handlerThread1.start();
        this.handlerThread2.start();
        this.handlerThread3.start();

        this.threadHandler1 = new Handler(handlerThread1.getLooper());
        this.threadHandler2 = new Handler(handlerThread2.getLooper());
        this.threadHandler3 = new Handler(handlerThread3.getLooper());
    }

    public void execute(Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        try {
            this.workQueue.put(action);
            runHandler();
        } catch (InterruptedException e) {
            Log.e("HandlerThreadPoolExecutor", "Error adding task to the queue", e);
        }
    }

    private void runHandler() {
        if (!this.task1Running) {
            this.task1Running = true;
            this.threadHandler1.post(new Runnable() {
                @Override
                public void run() {
                    runHandlerTask1();
                }
            });
        }

        if (!this.task2Running && this.workQueue.size() > 1) {
            this.task2Running = true;
            this.threadHandler2.post(new Runnable() {
                @Override
                public void run() {
                    runHandlerTask2();
                }
            });
        }

        if (this.task3Running || this.workQueue.size() <= 2) {
            return;
        }

        this.task3Running = true;
        this.threadHandler3.post(new Runnable() {
            @Override
            public void run() {
                runHandlerTask3();
            }
        });
    }

    private void runHandlerTask1() {
        try {
            Runnable task = workQueue.poll(1, TimeUnit.SECONDS);
            while (task != null) {
                task.run();
                task = workQueue.poll(1, TimeUnit.SECONDS);
            }
            this.task1Running = false;
        } catch (InterruptedException e) {
            Log.e("HandlerThreadPoolExecutor", "Error in task 1", e);
        }
    }

    private void runHandlerTask2() {
        try {
            Runnable task = workQueue.poll(1, TimeUnit.SECONDS);
            while (task != null) {
                task.run();
                task = workQueue.poll(1, TimeUnit.SECONDS);
            }
            this.task2Running = false;
        } catch (InterruptedException e) {
            Log.e("HandlerThreadPoolExecutor", "Error in task 2", e);
        }
    }

    private void runHandlerTask3() {
        try {
            Runnable task = workQueue.poll(1, TimeUnit.SECONDS);
            while (task != null) {
                task.run();
                task = workQueue.poll(1, TimeUnit.SECONDS);
            }
            this.task3Running = false;
        } catch (InterruptedException e) {
            Log.e("HandlerThreadPoolExecutor", "Error in task 3", e);
        }
    }
}
