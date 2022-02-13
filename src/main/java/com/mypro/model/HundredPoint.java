package com.mypro.model;

import com.mypro.base.graphics.Bitmap;

/**
 * 高分显示
 *
 * @author Xiloerfan
 */
public class HundredPoint extends DrawableAdapter {
    private final Bitmap[] images;
    private int currentPicId;

    public HundredPoint(Bitmap[] images) {
        this.images = images;
    }

    public int getActPicLength() {
        return images.length;
    }

    public void setCurrentPicId(int currentPicId) {
        this.currentPicId = currentPicId;
    }

    @Override
    public Bitmap getCurrentPic() {
        return images[currentPicId];
    }

    @Override
    public int getPicWidth() {
        return getCurrentPic().getWidth();
    }

    @Override
    public int getPicHeight() {
        return getCurrentPic().getHeight();
    }

}
