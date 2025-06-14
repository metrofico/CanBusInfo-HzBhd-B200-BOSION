package com.hzbhd.canbus.car._469;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.hzbhd.canbus.car._469.impl.MsAbstractMsgMgr;
import java.io.IOException;

/* loaded from: classes2.dex */
public class VoiceManger {
    private final int MAX_VOLUME = 100;
    private MediaPlayer mMediaPlayer;

    public VoiceManger() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.hzbhd.canbus.car._469.VoiceManger$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) throws IllegalStateException {
                this.f$0.m886lambda$initMediaPlayer$0$comhzbhdcanbuscar_469VoiceManger(mediaPlayer2);
            }
        });
        this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.hzbhd.canbus.car._469.VoiceManger$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer2) throws IllegalStateException {
                this.f$0.m887lambda$initMediaPlayer$1$comhzbhdcanbuscar_469VoiceManger(mediaPlayer2);
            }
        });
    }

    /* renamed from: lambda$initMediaPlayer$0$com-hzbhd-canbus-car-_469-VoiceManger, reason: not valid java name */
    /* synthetic */ void m886lambda$initMediaPlayer$0$comhzbhdcanbuscar_469VoiceManger(MediaPlayer mediaPlayer) throws IllegalStateException {
        MediaPlayer mediaPlayer2 = this.mMediaPlayer;
        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            this.mMediaPlayer.stop();
        }
        MsAbstractMsgMgr.releaseVoiceSource();
    }

    /* renamed from: lambda$initMediaPlayer$1$com-hzbhd-canbus-car-_469-VoiceManger, reason: not valid java name */
    /* synthetic */ void m887lambda$initMediaPlayer$1$comhzbhdcanbuscar_469VoiceManger(MediaPlayer mediaPlayer) throws IllegalStateException {
        MediaPlayer mediaPlayer2 = this.mMediaPlayer;
        if (mediaPlayer2 != null) {
            mediaPlayer2.start();
        }
    }

    public void setVolume(int i) {
        float fLog = (float) (1.0d - (Math.log(100 - i) / Math.log(100.0d)));
        this.mMediaPlayer.setVolume(fLog, fLog);
    }

    void play(Context context, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.mMediaPlayer.reset();
            AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(str);
            this.mMediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
