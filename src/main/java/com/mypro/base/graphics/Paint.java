package com.mypro.base.graphics;

/**
 * 画笔属性
 */
public interface Paint {
    /***
     * 设置Paint的字体
     * @param obj
     */
    void setTypeface(Object obj);

    void setAntiAlias(boolean tf);

    void setFilterBitmap(boolean tf);

    void setDither(boolean tf);

    /**
     * 根据不同分辨率设置字体大小
     *
     * @param size
     */
    void setTextSize(int size);

    void setColor(int color);
}
