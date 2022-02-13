package com.mypro.model;

/**
 * 某一种鱼的细节配置信息
 *
 * @author Xiloerfan
 */
public class FishInfo {
    private int actSpeed = 200;                    //动作速度
    private int maxRotate = 90;                    //最大旋转角度
    private int fishRunSpeed = 60;                //移动速度
    private int fishShoalMax = 0;                //最大的鱼群数
    private int fishInLayer = 1;                //所在图层
    private int catchProbability;                //捕捉概率
    private int worth;                            //价值

    public FishInfo() {
    }

    public FishInfo(int actSpeed, int maxRotate, int fishRunSpeed, int fishShoalMax, int fishInLayer) {
        this.actSpeed = actSpeed;
        this.maxRotate = maxRotate;
        this.fishRunSpeed = fishRunSpeed;
        this.fishShoalMax = fishShoalMax;
        this.fishInLayer = fishInLayer;
    }

    public void setActSpeed(int actSpeed) {
        this.actSpeed = actSpeed;
    }

    public void setMaxRotate(int maxRotate) {
        this.maxRotate = maxRotate;
    }

    public void setFishRunSpeed(int fishRunSpeed) {
        this.fishRunSpeed = fishRunSpeed;
    }

    public void setFishShoalMax(int fishShoalMax) {
        this.fishShoalMax = fishShoalMax;
    }

    public void setFishInLayer(int fishInLayer) {
        this.fishInLayer = fishInLayer;
    }

    /**
     * 获取鱼的最大可旋转角度
     */
    public int getMaxRotate() {
        return maxRotate;
    }

    /**
     * 获取鱼移动速度
     */
    public int getFishRunSpeed() {
        return fishRunSpeed;
    }

    /**
     * 获取鱼群最大个数
     */
    public int getFishShoalMax() {
        return fishShoalMax;
    }

    /**
     * 获取当前鱼所在图层号
     */
    public int getFishInLayer() {
        return fishInLayer;
    }


    public int getPicActSpeed() {
        return this.actSpeed;
    }

    public int getCatchProbability() {
        return catchProbability;
    }

    public void setCatchProbability(int catchProbability) {
        this.catchProbability = catchProbability;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

}
