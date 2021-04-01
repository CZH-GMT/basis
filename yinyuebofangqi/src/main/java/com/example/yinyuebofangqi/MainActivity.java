package com.example.yinyuebofangqi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private SeekBar seek;
    private Timer timer;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<Bean> beans = new ArrayList<>();
        beans.add(new Bean(R.raw.m3, "猪"));
        beans.add(new Bean(R.raw.m8, "猪"));
        beans.add(new Bean(R.raw.m3, "猪"));
        beans.add(new Bean(R.raw.m8, "猪"));
        beans.add(new Bean(R.raw.m3, "猪"));
        myAdapter.additem(beans);
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        seek = (SeekBar) findViewById(R.id.seek);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Bean music) {
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer=null;
                }
                final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, music.getMusic());
                mediaPlayer.start();
                int duration = mediaPlayer.getDuration();
                seek.setMax(duration);
                if (timer!=null){
                    timer.cancel();
                    timer=null;
                }
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int currentPosition = mediaPlayer.getCurrentPosition();
                        seek.setProgress(currentPosition);
                    }
                },1000,1000);
            }
        });


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private MediaPlayer mediaPlayer;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.m8);
                    mediaPlayer.start();
                    int duration = mediaPlayer.getDuration();
                    seek.setMax(duration);
                    if (timer!=null){
                        timer.cancel();
                        timer=null;
                    }
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            int currentPosition = mediaPlayer.getCurrentPosition();
                            seek.setProgress(currentPosition);

                        }
                    }, 1000, 1000);

                } else {
                    mediaPlayer.start();
                }
                break;
            case R.id.btn2:
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }

                break;
            case R.id.btn3:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    timer.cancel();
                    seek.setProgress(0);

                }

                break;
        }
    }
}
