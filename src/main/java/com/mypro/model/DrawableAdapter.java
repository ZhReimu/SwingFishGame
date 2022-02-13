package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Matrix;
import com.mypro.base.graphics.Paint;
import com.mypro.basecomponet.JMatrix;
import com.mypro.model.interfaces.Drawable;
import com.mypro.tools.IManager;

public abstract class DrawableAdapter extends IManager implements Drawable {
    private final Matrix matrix = new JMatrix();

    @Override
    public Matrix getPicMatrix() {
        return matrix;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getCurrentPic(),
                this.getPicMatrix(), paint);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    @Override
    public Bitmap getCurrentPic() {
        return null;
    }

    @Override
    public int getPicHeight() {
        return 0;
    }

    @Override
    public int getPicWidth() {
        return 0;
    }
}
