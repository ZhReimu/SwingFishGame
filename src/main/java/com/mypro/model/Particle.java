package com.mypro.model;


import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Matrix;
import com.mypro.basecomponet.JMatrix;

/**
 * 粒子对象
 *
 * @author Xiloer
 */
public class Particle {
    final Bitmap effect;
    /**
     * 当前粒子坐在坐标X
     */
    public float currentX;
    /**
     * 当前粒子坐在坐标Y
     */
    public float currentY;
    /**
     * 偏移量X
     */
    public float offX;
    /**
     * 偏移量Y
     */
    public float offY;
    /**
     * 缩放
     */
    public float scale;//缩放基数
    /**
     * 粒子矩阵
     */
    public final Matrix matrix = new JMatrix();

    /**
     * 最大行走次数
     */
    public final int maxLen = (int) (Math.random() * 20);

    /**
     * 当前行走次数
     */
    public int currentLen;

    /**
     * 构造一个 粒子 对象
     */
    public Particle(float currentX, float currentY, float offX, float offY, float scale, Bitmap effect, float targetOffsetX, float targetOffsetY) {
        this.offX = offX;
        this.offY = offY;
        this.scale = scale;
        this.currentX = currentX - effect.getWidth() / 2F * scale + targetOffsetX;
        this.currentY = currentY - effect.getHeight() / 2F * scale + targetOffsetY;
        this.matrix.setTranslate(this.currentX, this.currentY);
        this.matrix.preScale(scale, scale);
        this.effect = effect;
    }

    public Particle(float currentX, float currentY, float offX, float offY, float scale, Bitmap effect) {
        this.offX = offX * scale;
        this.offY = offY * scale;
        this.scale = scale;
        this.currentX = currentX - effect.getWidth() / 2F * scale;
        this.currentY = currentY - effect.getHeight() / 2F * scale;
        this.matrix.setTranslate(this.currentX, this.currentY);
        this.matrix.preScale(scale, scale);
        this.effect = effect;
    }
}