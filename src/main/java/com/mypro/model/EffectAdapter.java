package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.constant.Constant;
import com.mypro.tools.LogTools;
import com.mypro.tools.ThreadTool;

import java.util.ArrayList;

public abstract class EffectAdapter extends DrawableAdapter {
    protected static final byte ADD = 1;
    protected static final byte REMOVE = 2;
    protected static final byte UPDATE = 3;

    //粒子彩色图
    protected Bitmap[] effectImages = null;
    protected ArrayList<Particle> effects = new ArrayList<>();
    protected ArrayList<Particle> news = new ArrayList<>();
    protected ArrayList<Particle> removes = new ArrayList<>();
    protected boolean isPlay = false;//是否播放粒子效果

    //粒子图
    protected Bitmap effectImage;
    protected float targetOffsetX, targetOffsetY;//距离当前坐标的偏移量,这两个值加上currentX,currentY来得到粒子初始位置
    protected float currentX, currentY;

    /**
     * 播放一次粒子效果
     *
     * @param x     粒子的生成位置X
     * @param y     粒子的生成位置Y
     * @param level 粒子等级
     */
    protected void playEffect(float x, float y, int level) {
        try {
            isPlay = true;
            startCreateEffectThread(x, y, level);
            startSetEffectThread();
            GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.PARTICLE_EFFECT_LAYER, this);
        } catch (Exception e) {
            LogTools.doLogForException(e);
        }
    }

    /**
     * 播放一次粒子效果
     *
     * @param x    粒子的生成位置X
     * @param y    粒子的生成位置Y
     * @param offX 粒子偏移量X 这两个值是生成粒子时的行动路线，这个应该和给定的物体的偏移量相反
     * @param offY 粒子偏移量Y
     */

    public void playEffect(float targetOffsetX, float targetOffsetY, float x, float y, float offX, float offY) {
        try {
            isPlay = true;
            this.targetOffsetX = targetOffsetX;
            this.targetOffsetY = targetOffsetY;
            startCreateEffectThread(x, y, offX, offY);
            GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.PARTICLE_EFFECT_LAYER, this);
        } catch (Exception e) {
            LogTools.doLogForException(e);
        }
    }

    protected void updateEffect(byte mode, Particle p) {
        if (mode == ADD) {
            news.add(p);
        } else if (mode == REMOVE) {
            removes.add(p);
        } else if (mode == UPDATE) {
            if (news.size() > 0) {
                effects.addAll(news);
                news.clear();
            }
            if (removes.size() > 0) {
                effects.removeAll(removes);
                removes.clear();
            }
        }
    }

    /**
     * 启动产生粒子的线程
     */
    protected void startCreateEffectThread(final float x, final float y, final float offX, final float offY) {
        this.currentX = x;
        this.currentY = y;
        ThreadTool.pool.execute(() -> {
            try {
                if (GamingInfo.getGamingInfo().isGaming()) {
                    while (GamingInfo.getGamingInfo().isPause() && isPlay) {
                        updateEffect(ADD, new Particle(
                                currentX, currentY, offX + (float) (Math.random() * 5),
                                offY + (float) (Math.random() * 5), 0.5f, effectImage,
                                targetOffsetX, targetOffsetY
                        ));
                        Thread.sleep((long) (Math.random() * 51));
                    }
                }
            } catch (Exception e) {
                LogTools.doLogForException(e);
            }
        });
    }

    protected void startSetEffectThread() {
        ThreadTool.pool.execute(() -> {
            try {
                if (GamingInfo.getGamingInfo().isGaming()) {
                    while (GamingInfo.getGamingInfo().isPause() && isPlay) {
                        setEffectMatrix();
                        Thread.sleep(50);
                    }
                }
            } catch (Exception e) {
                LogTools.doLogForException(e);
            }
        });
    }

    /**
     * 启动产生粒子的线程
     */
    protected void startCreateEffectThread(final float x, final float y, final int level) {
        ThreadTool.pool.execute(() -> {
            try {
                byte sum = 0;
                float scale;
                if (GamingInfo.getGamingInfo().isGaming() && isPlay) {
                    while (GamingInfo.getGamingInfo().isPause() && isPlay) {
                        scale = (float) ((Math.random() * level + 1) / 10);
                        switch (sum) {
                            case 0:
                                updateEffect(ADD, new Particle(x, y, -(float) (Math.random() * 6 + 1), (float) (Math.random() * 6 + 1), scale, effectImages[(int) (Math.random() * effectImages.length)]));
                                break;
                            case 1:
                                updateEffect(ADD, new Particle(x, y, (float) (Math.random() * 6 + 1), -(float) (Math.random() * 6 + 1), scale, effectImages[(int) (Math.random() * effectImages.length)]));
                                break;
                            case 2:
                                updateEffect(ADD, new Particle(x, y, (float) (Math.random() * 6 + 1), (float) (Math.random() * 6 + 1), scale, effectImages[(int) (Math.random() * effectImages.length)]));
                                break;
                            case 3:
                                updateEffect(ADD, new Particle(x, y, -(float) (Math.random() * 6 + 1), -(float) (Math.random() * 6 + 1), scale, effectImages[(int) (Math.random() * effectImages.length)]));
                                break;
                        }
                        sum++;
                        if (sum > 3) {
                            sum = 0;
                        }
                        Thread.sleep((long) (Math.random() * 51));
                    }
                }
            } catch (Exception e) {
                LogTools.doLogForException(e);
            }
        });
    }

    /**
     * 停止播放粒子
     */
    public void stopEffect() {
        this.isPlay = false;
        GamingInfo.getGamingInfo().getSurface().removeDrawablePic(Constant.PARTICLE_EFFECT_LAYER, this);
    }

    /**
     * 设置粒子位置
     */
    protected void setEffectMatrix() {
        Particle particle;
        for (Particle effect : effects) {
            particle = effect;
            if (particle.currentLen >= particle.maxLen) {
                updateEffect(REMOVE, particle);
            }
            particle.currentX = particle.currentX + particle.offX;
            particle.currentY = particle.currentY + particle.offY;
            particle.matrix.setTranslate(particle.currentX, particle.currentY);
            particle.matrix.preScale(particle.scale, particle.scale);
            particle.currentLen++;
        }
    }

    /**
     * 设置粒子位置
     */
    public void setEffectMatrix(float currentX, float currentY) {
        this.currentX = currentX + targetOffsetX;
        this.currentY = currentY + targetOffsetY;
        Particle particle;
        for (Particle effect : effects) {
            particle = effect;
            particle.offX -= particle.offX * 0.1f;
            particle.offY -= particle.offY * 0.1f;
            particle.scale -= particle.scale * 0.1f;
            particle.currentX = particle.currentX - particle.offX;
            particle.currentY = particle.currentY - particle.offY;
            particle.matrix.setTranslate(particle.currentX, particle.currentY);
            particle.matrix.preScale(particle.scale, particle.scale);
            if (particle.scale < 0.1) {
                updateEffect(REMOVE, particle);
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        updateEffect(UPDATE, null);
        //这个值用于绘制方法循环使用
        int indexByDraw = 0;
        if (GamingInfo.getGamingInfo().isGaming()) {
            while (GamingInfo.getGamingInfo().isPause() && isPlay && indexByDraw < effects.size()) {
                //这个值用于绘制方法循环使用
                Particle particle = effects.get(indexByDraw);
                canvas.drawBitmap(particle.effect, particle.matrix, paint);
                indexByDraw++;
            }
        }
    }

}
