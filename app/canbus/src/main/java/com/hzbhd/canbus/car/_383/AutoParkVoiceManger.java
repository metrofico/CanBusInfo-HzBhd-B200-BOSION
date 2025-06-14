package com.hzbhd.canbus.car._383;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;

/* loaded from: classes2.dex */
public class AutoParkVoiceManger {
    private final String TAG = "AutoParkVoiceManger";
    private MediaPlayer mMediaPlayer;

    public AutoParkVoiceManger() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.hzbhd.canbus.car._383.AutoParkVoiceManger$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) throws IllegalStateException {
                this.f$0.m808xf77111de(mediaPlayer2);
            }
        });
        this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.hzbhd.canbus.car._383.AutoParkVoiceManger$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer2) throws IllegalStateException {
                this.f$0.m809x6ceb381f(mediaPlayer2);
            }
        });
    }

    /* renamed from: lambda$initMediaPlayer$0$com-hzbhd-canbus-car-_383-AutoParkVoiceManger, reason: not valid java name */
    /* synthetic */ void m808xf77111de(MediaPlayer mediaPlayer) throws IllegalStateException {
        Log.i("AutoParkVoiceManger", "onCompletion: mp " + mediaPlayer);
        this.mMediaPlayer.stop();
        Log.i("AutoParkVoiceManger", "onCompletion: stop");
    }

    /* renamed from: lambda$initMediaPlayer$1$com-hzbhd-canbus-car-_383-AutoParkVoiceManger, reason: not valid java name */
    /* synthetic */ void m809x6ceb381f(MediaPlayer mediaPlayer) throws IllegalStateException {
        Log.i("AutoParkVoiceManger", "onPrepared: mp -> " + mediaPlayer);
        this.mMediaPlayer.start();
        Log.i("AutoParkVoiceManger", "onPrepared: start");
    }

    void play(Context context, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.mMediaPlayer.reset();
            Log.i("AutoParkVoiceManger", "play: reset");
            AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(str);
            this.mMediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            Log.i("AutoParkVoiceManger", "play: setDataSource \"" + str + "\"");
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.prepareAsync();
            Log.i("AutoParkVoiceManger", "play: prepareAsync");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("AutoParkVoiceManger", "play: " + e.toString());
        }
    }
}
