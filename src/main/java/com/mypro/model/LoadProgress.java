package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.base.tools.Log;
import com.mypro.tools.LogTools;

import java.awt.*;

/**
 * 加载进度条
 *
 * @author Xiloer
 */
public class LoadProgress extends DrawableAdapter {
    private static LoadProgress obj;
    /**
     * 背景图
     */
    private Bitmap progressBg;
    /**
     * 加载框
     */
    private Bitmap loadProgress;
    /**
     * 进度条
     */
    private Bitmap load;
    /**
     * 当前进度条
     */
    private Bitmap currentLoad;
    /**
     * 加载框所在的位置
     */
    private int progress_x;
    private int progress_y;

    private LoadProgress() {
        try {
            while (this.loadProgress == null) {
                this.loadProgress = imageManager.getBitmapByAssets("progress/login_bg.png");
            }
            while (this.load == null) {
                this.load = imageManager.getBitmapByAssets("progress/login_jd.png");
            }
            while (this.progressBg == null) {
                this.progressBg = imageManager.sacleImageByWidthAndHeight(
                        imageManager.getBitmapByAssets("progress/progress_bg.jpg"),
                        GamingInfo.getGamingInfo().getScreenWidth(),
                        GamingInfo.getGamingInfo().getScreenHeight()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取进度框
     */
    public static LoadProgress getLoadProgress() {
        try {
            if (obj == null) {
                obj = new LoadProgress();
                obj.setProgress(0);
                obj.progress_x = GamingInfo.getGamingInfo().getScreenWidth() / 2 - obj.currentLoad.getWidth() / 2;
                obj.progress_y = GamingInfo.getGamingInfo().getScreenHeight() / 2 - obj.currentLoad.getHeight() / 2;
            }
        } catch (Exception e) {
            Log.e("Loadrogress", e.toString());
        }
        return obj;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(progressBg, 0, 0, paint);
        canvas.drawBitmap(currentLoad, progress_x, progress_y, paint);

    }

    /**
     * 设置当前进度值，最大值为100%
     *
     * @param current 0-100之间的整数
     */
    public void setProgress(int current) {
        try {
            Bitmap currentLoadFlag = loadProgress.copy();
            Graphics g = currentLoadFlag.getImage().getGraphics();
            //透明的起始X坐标
            int startX = load.getWidth() * current / 100;
            Bitmap flag = load.copy();
            for (int r = 0; r < flag.getWidth(); r++) {
                for (int c = 0; c < flag.getHeight(); c++) {
                    if (r > startX) {
                        flag.setPixel(r, c, 0x00000000);
                    }
                }
            }
            int load_y = 46;
            // 进度条所在的位置
            int load_x = 203;
            g.drawImage(flag.getImage(), load_x, load_y, null);
            currentLoad = imageManager.scaleImageByScreen(currentLoadFlag);
        } catch (Exception e) {
            LogTools.doLogForException(e);
            e.printStackTrace();
        }
    }

    /**
     * 注销当前进度条
     * 这个方法一半情况下不建议使用，是有退出程序时才用
     */
    public void destroy() {
        obj = null;
    }

}
