package com.mypro.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTool {
    public static final ExecutorService pool = Executors.newCachedThreadPool();
}
