package com.mypro.tools;

import com.mypro.manager.*;

import java.util.HashMap;

public class ManagerFactory {

    private ManagerFactory() {
    }

    private static final HashMap<String, IManager> map = new HashMap<>();

    static {
        map.put(ImageManager.class.getName(), ImageManager.getInstance());
        map.put(SoundManager.class.getName(), SoundManager.getInstance());
        map.put(ParticleEffectManager.class.getName(), ParticleEffectManager.getInstance());
        map.put(ScoreManager.class.getName(), ScoreManager.getInstance());
        map.put(MusicManager.class.getName(), MusicManager.getInstance());
        map.put(CannonManager.class.getName(), CannonManager.getInstance());
        map.put(FishManager.class.getName(), FishManager.getInstance());
        map.put(GamePartManager.class.getName(), GamePartManager.getInstance());
    }

    @SuppressWarnings("unchecked")
    public static <T extends IManager> T getInstance(Class<T> clazz) {
        return (T) map.get(clazz.getName());
    }
}
