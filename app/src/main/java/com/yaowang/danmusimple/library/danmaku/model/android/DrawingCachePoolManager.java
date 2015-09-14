
package com.yaowang.danmusimple.library.danmaku.model.android;

import com.yaowang.danmusimple.library.danmaku.model.android.DrawingCache;
import com.yaowang.danmusimple.library.danmaku.model.objectpool.PoolableManager;

public class DrawingCachePoolManager implements PoolableManager<DrawingCache> {

    @Override
    public DrawingCache newInstance() {
        return null;
    }

    @Override
    public void onAcquired(DrawingCache element) {

    }

    @Override
    public void onReleased(DrawingCache element) {

    }

}
