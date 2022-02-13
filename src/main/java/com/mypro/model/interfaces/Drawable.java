package com.mypro.model.interfaces;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Matrix;
import com.mypro.base.graphics.Paint;

public interface Drawable {
    Matrix getPicMatrix();//获取图片旋转的矩阵表示

    Bitmap getCurrentPic();//获取当前动作图片的资源

    int getPicWidth();//返回图片的宽度

    int getPicHeight();//返回图片的高度

    void onDraw(Canvas canvas, Paint paint);//绘制的回调方法
}
