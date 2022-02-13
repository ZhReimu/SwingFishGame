package com.mypro.base.graphics;


/**
 * 矩阵接口
 */
public interface Matrix {
    /**
     * 设置平移
     */
    void setTranslate(float x, float y);

    /**
     * 重置矩阵
     */
    void reset();

    /**
     * 缩放
     */
    void preScale(float x, float y);

    /**
     * 旋转
     */
    void preRotate(float angle, float x, float y);
}
