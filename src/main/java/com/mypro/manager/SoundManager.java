package com.mypro.manager;

import com.mypro.tools.IManager;
import com.mypro.tools.MediaTool;

import java.util.HashMap;

public class SoundManager extends IManager {

    public static final int SOUND_BGM_FIRE = 1;
    public static final int SOUND_BGM_NET = SOUND_BGM_FIRE + 1;
    public static final int SOUND_BGM_CHANGE_CANNON = SOUND_BGM_NET + 1;
    public static final int SOUND_BGM_GOLD = SOUND_BGM_CHANGE_CANNON + 1;
    public static final int SOUND_BGM_HIGH_POINT = SOUND_BGM_GOLD + 1;
    public static final int SOUND_BGM_HUNDRED_POINT = SOUND_BGM_HIGH_POINT + 1;
    public static final int SOUND_BGM_NO_GOLD = SOUND_BGM_HUNDRED_POINT + 1;

    private static SoundManager soundManager;
    private static final HashMap<Integer, String> fileMap = new HashMap<>();

    private SoundManager() {
        try {
            initSoundData(SOUND_BGM_FIRE, "bgm_fire.ogg");
            initSoundData(SOUND_BGM_CHANGE_CANNON, "firechange.ogg");
            initSoundData(SOUND_BGM_NET, "bgm_net.ogg");
            initSoundData(SOUND_BGM_GOLD, "coinanimate.ogg");
            initSoundData(SOUND_BGM_HIGH_POINT, "highpoints.ogg");
            initSoundData(SOUND_BGM_HUNDRED_POINT, "hundredpoints.mp3");
            initSoundData(SOUND_BGM_NO_GOLD, "coinsnone.ogg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSoundData(int key, String soundFile) {
        fileMap.put(key, soundFile);
    }

    public static SoundManager getInstance() {
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    public static void playSound(final int soundID) {
        // MediaTool.play(lineMap.get(soundID), soundMap.get(soundID));
        MediaTool.playInNewThread(fileMap.get(soundID), false);
    }

    public static void release() {
        soundManager = null;
    }
}
