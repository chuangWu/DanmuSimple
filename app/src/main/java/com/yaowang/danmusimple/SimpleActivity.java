package com.yaowang.danmusimple;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.yaowang.danmusimple.library.controller.IDanmakuView;


/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2015-09-14 10:38
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class SimpleActivity extends Activity implements View.OnClickListener {

    View mMediaController;
    VideoView mVideoView;
    Button mBtnRotate;
    Button mBtnHideDanmaku;
    Button mBtnShowDanmaku;

    Button mBtnPauseDanmaku;
    Button mBtnResumeDanmaku;
    Button mBtnSendDanmaku;
    Button mBtnSendDanmakuTextAndImage;
    Button mBtnSendDanmakus;
    IDanmakuView mDanmakuView;
    TimeDanmuController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initListener();
    }

    protected void initViews() {
        mMediaController = findViewById(R.id.media_controller);
        mBtnRotate = (Button) findViewById(R.id.rotate);
        mBtnHideDanmaku = (Button) findViewById(R.id.btn_hide);
        mBtnShowDanmaku = (Button) findViewById(R.id.btn_show);
        mBtnPauseDanmaku = (Button) findViewById(R.id.btn_pause);
        mBtnResumeDanmaku = (Button) findViewById(R.id.btn_resume);
        mBtnSendDanmaku = (Button) findViewById(R.id.btn_send);
        mBtnSendDanmakuTextAndImage = (Button) findViewById(R.id.btn_send_image_text);
        mBtnSendDanmakus = (Button) findViewById(R.id.btn_send_danmakus);
        mBtnRotate.setOnClickListener(this);
        mBtnHideDanmaku.setOnClickListener(this);
        mMediaController.setOnClickListener(this);
        mBtnShowDanmaku.setOnClickListener(this);
        mBtnPauseDanmaku.setOnClickListener(this);
        mBtnResumeDanmaku.setOnClickListener(this);
        mBtnSendDanmaku.setOnClickListener(this);
        mBtnSendDanmakuTextAndImage.setOnClickListener(this);
        mBtnSendDanmakus.setOnClickListener(this);
        mVideoView = (VideoView) findViewById(R.id.videoview);
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
    }

    protected void initData() {
        controller = new TimeDanmuController(this,mDanmakuView);
        controller.initData();


    }

    protected void initListener() {
        controller.initListener();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mVideoView.setVideoPath(Environment.getExternalStorageDirectory() + "/1.flv");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sv_danmaku:
                mMediaController.setVisibility(View.VISIBLE);
                break;
            case R.id.media_controller:
                mMediaController.setVisibility(View.GONE);
                break;

            case R.id.rotate:
                setRequestedOrientation(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;

            case R.id.btn_hide:
                mDanmakuView.hide();
                break;
            case R.id.btn_show:
                mDanmakuView.show();
                break;
            case R.id.btn_pause:
                mDanmakuView.pause();
                break;
            case R.id.btn_resume:
                mDanmakuView.resume();

                break;
            case R.id.btn_send:
                controller.addDanmaku(false);

                break;
            case R.id.btn_send_image_text:
                controller.addDanmaKuWithImage(false);

                break;
            case R.id.btn_send_danmakus:
                Boolean b = (Boolean) mBtnSendDanmakus.getTag();
                controller.cancel();
                if (b == null || !b) {
                    mBtnSendDanmakus.setText(R.string.cancel_sending_danmakus);
                    controller.schedule();
                    mBtnSendDanmakus.setTag(true);
                } else {
                    mBtnSendDanmakus.setText(R.string.send_danmakus);
                    mBtnSendDanmakus.setTag(false);
                }
                break;
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        controller.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.onDestory();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        controller.onDestory();
    }
}
