package com.mypro.model.componets;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.tools.Log;
import com.mypro.model.GamingInfo;

/**
 * 大炮底座
 *
 * @author Xiloerfan
 */
public class Bottom extends Componet {
    private Bitmap pic;

    public Bottom() {
        try {
            pic = imageManager.getscaleImageByScreenFromAssets("componet/bottom.png");
            this.setLayout_x(GamingInfo.getGamingInfo().getScreenWidth() / 2F - getPicWidth() / 2F);
            this.setLayout_y(GamingInfo.getGamingInfo().getScreenHeight() - getPicHeight());
            this.getPicMatrix().setTranslate(this.getLayout_x(), this.getLayout_y());
        } catch (Exception e) {
            Log.e("Bottom", e.toString());
        }
    }

    public Bitmap getCurrentPic() {
        return pic;
    }

    public int getPicWidth() {
        return pic.getWidth();
    }

    public int getPicHeight() {
        return pic.getHeight();
    }

}
