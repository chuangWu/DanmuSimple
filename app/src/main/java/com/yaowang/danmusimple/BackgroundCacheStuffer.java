package com.yaowang.danmusimple;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

import com.yaowang.danmusimple.library.danmaku.model.BaseDanmaku;
import com.yaowang.danmusimple.library.danmaku.model.android.SpannedCacheStuffer;

/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2015-09-14 10:45
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class BackgroundCacheStuffer extends SpannedCacheStuffer {
    final Paint paint = new Paint();

    @Override
    public void measure(BaseDanmaku danmaku, TextPaint paint) {
        danmaku.padding = 10;
        super.measure(danmaku, paint);
    }

    @Override
    public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
        paint.setColor(0x8125309b);
        canvas.drawRect(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2, paint);
    }

    @Override
    public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
        // 禁用描边绘制
    }
}
