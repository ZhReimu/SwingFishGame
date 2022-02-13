package com.mypro.model;

import com.mypro.base.graphics.Bitmap;

/**
 * 百分显示
 *
 * @author Xiloerfan
 */
public class HighPoint extends DrawableAdapter {
    private final Bitmap[] images;
    private int currentPicId;

    public HighPoint(Bitmap[] images) {
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
