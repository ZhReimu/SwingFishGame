package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.constant.Constant;
import com.mypro.manager.ParticleEffectManager;
import com.mypro.tools.LogTools;
import com.mypro.tools.ManagerFactory;

/**
 * 渔网
 *
 * @author Xiloerfan
 */
public class FishingNet extends DrawableAdapter {
    private final Bitmap net;
    private final Ammo ammo;//对应的子弹

    public FishingNet(Bitmap net, Ammo ammo) {
        this.net = net;
        this.ammo = ammo;
    }

    @Override
    public Bitmap getCurrentPic() {
        return net;
    }

    @Override
    public int getPicWidth() {
        return net.getWidth();
    }

    @Override
    public int getPicHeight() {
        return net.getHeight();
    }

    /**
     * 播放渔网动画
     */
    public void playNetAct(float netX, float netY) {
        try {
            float scale = 0.2f;
            float picW = this.getPicWidth() / 2F, picH = this.getPicHeight() / 2F;
            this.getPicMatrix().setTranslate(netX - picW * scale, netY - picH * scale);
            this.getPicMatrix().preScale(scale, scale);
            //将渔网放置到绘图层中等待被绘制
            GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.FISH_NET_LAYER, this);
            Thread.sleep(25);
            for (int i = 2; i <= 6; i++) {
                scale = i * 0.2f;
                this.getPicMatrix().setTranslate(netX - picW * scale, netY - picH * scale);
                this.getPicMatrix().preScale(scale, scale);
                Thread.sleep(25);
            }
            this.getPicMatrix().setTranslate(netX - picW * 1.25f, netY - picH * 1.25f);
            this.getPicMatrix().preScale(1.25f, 1.25f);
            Thread.sleep(25);
            this.getPicMatrix().setTranslate(netX - picW * 1.10f, netY - picH * 1.10f);
            this.getPicMatrix().preScale(1.10f, 1.10f);
            Thread.sleep(25);
            this.getPicMatrix().setTranslate(netX - picW, netY - picH);
            NetParticleEffect npe = ManagerFactory.getInstance(ParticleEffectManager.class).getNetEffect();
            //播放渔网粒子
            npe.playEffect(netX, netY, ammo.getAmmoQuality());
            //渔网显示时间
            Thread.sleep(Constant.FISHING_NET_SHOW_TINE);
            npe.stopEffect();
            //将渔网清出绘图层
            GamingInfo.getGamingInfo().getSurface()
                    .removeDrawablePic(Constant.FISH_NET_LAYER, this);
        } catch (Exception e) {
            LogTools.doLogForException(e);
        }

    }
}
