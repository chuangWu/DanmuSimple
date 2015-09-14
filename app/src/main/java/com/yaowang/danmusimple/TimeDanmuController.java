package com.yaowang.danmusimple;

import android.content.Context;
import android.os.SystemClock;

import com.yaowang.danmusimple.library.controller.IDanmakuView;

import java.util.Timer;
import java.util.TimerTask;



/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2015-09-14 11:45
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class TimeDanmuController extends DanmuController {

    protected Timer timer = new Timer();

    public TimeDanmuController(Context context) {
        super(context);
    }

    public TimeDanmuController(Context context, IDanmakuView danmakuView) {
        super(context, danmakuView);
    }


    public void cancel() {
        timer.cancel();
    }

    public void schedule() {
        timer = new Timer();
        timer.schedule(new AsyncAddTask(), 0, 1000);
    }


    class AsyncAddTask extends TimerTask {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                addDanmaku(true);
                SystemClock.sleep(20);
            }
        }
    }

}
