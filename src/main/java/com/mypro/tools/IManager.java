package com.mypro.tools;

import com.mypro.manager.ImageManager;

public abstract class IManager {
    protected ImageManager imageManager = ManagerFactory.getInstance(ImageManager.class);
}
