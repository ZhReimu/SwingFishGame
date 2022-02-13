package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.tools.LogTools;

/**
 * 子弹粒子效果
 *
 * @author Xiloer
 */
public class AmmoParticleEffect extends EffectAdapter {

    public AmmoParticleEffect(Bitmap[] effectImages) {
        this.effectImages = effectImages;
    }

    /**
     * 启动产生粒子的线程
     */
    @Override
    protected void startCreateEffectThread(final float x, final float y, final float offX, final float offY) {
        this.currentX = x;
        this.currentY = y;
        new Thread(() -> {
            try {
                if (GamingInfo.getGamingInfo().isGaming()) {
                    while (GamingInfo.getGamingInfo().isPause() && isPlay) {
                        updateEffect(ADD, new Particle(
                                currentX, currentY, offX, offY,
                                0.5f, effectImages[(int) (Math.random() * effectImages.length)],
                                targetOffsetX, targetOffsetY
                        ));
                        Thread.sleep((long) (Math.random() * 201));
                    }
                }
            } catch (Exception e) {
                LogTools.doLogForException(e);
            }
        }).start();
    }

}
