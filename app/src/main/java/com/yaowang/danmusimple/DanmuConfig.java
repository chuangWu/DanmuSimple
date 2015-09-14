package com.yaowang.danmusimple;

import java.io.InputStream;
import java.util.HashMap;

import com.yaowang.danmusimple.library.danmaku.loader.ILoader;
import com.yaowang.danmusimple.library.danmaku.loader.IllegalDataException;
import com.yaowang.danmusimple.library.danmaku.loader.android.DanmakuLoaderFactory;
import com.yaowang.danmusimple.library.danmaku.model.BaseDanmaku;
import com.yaowang.danmusimple.library.danmaku.model.android.DanmakuGlobalConfig;
import com.yaowang.danmusimple.library.danmaku.model.android.Danmakus;
import com.yaowang.danmusimple.library.danmaku.parser.BaseDanmakuParser;
import com.yaowang.danmusimple.library.danmaku.parser.android.AcFunDanmakuParser;

/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2015-09-14 10:58
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class DanmuConfig {

    public static void initConfig() {
        DanmakuGlobalConfig.DEFAULT.setDanmakuStyle(DanmakuGlobalConfig.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setMaximumVisibleSizeInScreen(0)
                .setCacheStuffer(new BackgroundCacheStuffer())
                .setMaximumLines(initMaxLines())
                .preventOverlapping(initOverlappingEnable());
    }


    /**
     * 设置最大显示行数
     *
     * @return
     */
    private static HashMap<Integer, Integer> initMaxLines() {
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3);
        return maxLinesPair;
    }

    /**
     * 设置是否禁止重叠
     *
     * @return
     */
    private static HashMap<Integer, Boolean> initOverlappingEnable() {
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        return overlappingEnablePair;
    }

    public static BaseDanmakuParser initParser(InputStream stream) {
        if (stream == null) {
            return new BaseDanmakuParser() {
                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }
        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_ACFUN);
        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        AcFunDanmakuParser parser = new AcFunDanmakuParser();
        parser.load(loader.getDataSource());
        return parser;
    }


}
