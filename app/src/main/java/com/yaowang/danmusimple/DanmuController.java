package com.yaowang.danmusimple;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;

import java.io.IOException;
import java.io.InputStream;

import com.yaowang.danmusimple.library.controller.DrawHandler;
import com.yaowang.danmusimple.library.controller.IDanmakuView;
import com.yaowang.danmusimple.library.danmaku.model.BaseDanmaku;
import com.yaowang.danmusimple.library.danmaku.model.DanmakuTimer;
import com.yaowang.danmusimple.library.danmaku.parser.BaseDanmakuParser;
import com.yaowang.danmusimple.library.danmaku.parser.DanmakuFactory;

/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2015-09-14 10:39
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class DanmuController extends BaseController {

    protected IDanmakuView danmakuView;
    protected BaseDanmakuParser parser;

    public DanmuController(Context context) {
        super(context);
    }

    public DanmuController(Context context, IDanmakuView danmakuView) {
        super(context);
        this.danmakuView = danmakuView;
    }

    @Override
    public void initData() {
        super.initData();
        DanmuConfig.initConfig();
        parser = DanmuConfig.initParser(getInputStream());
        danmakuView.prepare(parser);
        danmakuView.showFPS(true);
        danmakuView.enableDanmakuDrawingCache(true);

    }

    @Override
    public void initListener() {
        super.initListener();
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void updateTimer(DanmakuTimer timer) {
            }

            @Override
            public void prepared() {
                danmakuView.start();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }


    @Override
    public void onDestory() {
        super.onDestory();
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

    public void addDanmaku(boolean islive) {
        BaseDanmaku danmaku = DanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || danmakuView == null) {
            return;
        }
        danmaku.text = "这是一条弹幕" + System.nanoTime();
        danmaku.padding = 5;
        danmaku.priority = 1;
        danmaku.isLive = islive;
        danmaku.time = danmakuView.getCurrentTime() + 1200;
        danmaku.textSize = 25f * (parser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = Color.GREEN;
        danmakuView.addDanmaku(danmaku);

    }

    public void addDanmaKuWithImage(boolean islive) {
        BaseDanmaku danmaku = DanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 100, 100);
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        danmaku.text = spannableStringBuilder;
        danmaku.padding = 5;
        danmaku.priority = 1;
        danmaku.isLive = islive;
        danmaku.time = danmakuView.getCurrentTime() + 1200;
        danmaku.textSize = 25f * (parser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.underlineColor = Color.GREEN;
        danmakuView.addDanmaku(danmaku);
    }

    public InputStream getInputStream() {
        try {
            return context.getResources().getAssets().open("comment.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
