package com.mypro.tools;

import com.mypro.manager.ImageManager;

public abstract class IManager {
    protected final ImageManager imageManager = ManagerFactory.getInstance(ImageManager.class);
}
