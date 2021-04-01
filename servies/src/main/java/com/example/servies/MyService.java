package com.example.servies;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private MyService() {

    }

    public class Mybinder extends Binder {
        public MyService getservice() {
            return MyService.this;
        }
    }

    private String string = "123";

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Mybinder();
    }

    private MediaPlayer mediaPlayer;

    public void start() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        } else {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.m4);
            mediaPlayer.start();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private int index = 0;
    private int[] arr={
            R.raw.m1,
            R.raw.m2,
            R.raw.m3,
            R.raw.m4

    };
    public void up() {
        if (index > 0) {
            index--;
        } else if (index==0){
            index=arr.length-1;
        }
        stop();
        start();
    }
    public void next(){
        if (index<4){
            index++;
        }else if (index==8){
            index=0;
        }
        stop();
        start();
    }
}
