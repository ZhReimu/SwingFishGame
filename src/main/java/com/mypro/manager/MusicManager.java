package com.mypro.manager;

import com.mypro.tools.IManager;
import com.mypro.tools.MediaTool;


public class MusicManager extends IManager {

    private static MusicManager manager;

    public static MusicManager getInstance() {
        if (manager == null) {
            manager = new MusicManager();
        }
        return manager;
    }

    private MusicManager() {

    }

    public void playMusicByR(String resId, boolean isLoop) {
        try {
            MediaTool.playInNewThread(resId, isLoop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void release() {
        manager = null;
    }
}
