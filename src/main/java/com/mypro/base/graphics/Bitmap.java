package com.mypro.base.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Bitmap implements Serializable {
    private final BufferedImage image;

    public Bitmap(BufferedImage image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    /**
     * 获取图片宽度
     */
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * 获取图片高度
     */
    public int getHeight() {
        return image.getHeight(null);
    }

    /**
     * 缩放图片
     */
    public static Bitmap createScaledBitmap(Bitmap src, int width, int height, boolean tf) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = img.getGraphics();
        g.drawImage(src.image, 0, 0, width, height, 0, 0, src.getWidth(), src.getHeight(), null);
        return new Bitmap(img);
    }

    /**
     * 复制图片
     */
    public static Bitmap createBitmap(Bitmap src) {
        return src.copy();
    }

    public Bitmap copy() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.drawImage(this.image, 0, 0, null);
        return new Bitmap(image);
    }

    /**
     * 返回像素颜色
     */
    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

    /**
     * 设置像素颜色
     */
    public void setPixel(int x, int y, int color) {
        image.setRGB(x, y, color);
    }
}



