package com.yaowang.danmusimple.library.danmaku.model;

import com.yaowang.danmusimple.library.danmaku.model.BaseDanmaku;

public interface IDanmakuIterator {

    public BaseDanmaku next();
    
    public boolean hasNext();
    
    public void reset();

    public void remove();
    
}
