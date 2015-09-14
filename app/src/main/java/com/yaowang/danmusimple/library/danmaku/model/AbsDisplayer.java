package com.yaowang.danmusimple.library.danmaku.model;

import com.yaowang.danmusimple.library.danmaku.model.IDisplayer;

public abstract class AbsDisplayer<T> implements IDisplayer {
    
    public abstract T getExtraData();
    
    public abstract void setExtraData(T data);

    @Override
    public boolean isHardwareAccelerated() {
        return false;
    }

}
