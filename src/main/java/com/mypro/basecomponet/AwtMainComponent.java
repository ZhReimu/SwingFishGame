package com.mypro.basecomponet;

import com.mypro.mainsurface.MainSurface;
import com.mypro.manager.CannonManager;
import com.mypro.manager.GameInitManager;
import com.mypro.manager.LayoutManager;
import com.mypro.model.GamingInfo;
import com.mypro.tools.ThreadTool;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AwtMainComponent {

    public static void main(String[] args) {

        GamingInfo.getGamingInfo().setGaming(true);
        GamingInfo.getGamingInfo().setScreenWidth(900);
        GamingInfo.getGamingInfo().setScreenHeight(600 + 40);

        JFrame frame = new JFrame();
        frame.setSize(GamingInfo.getGamingInfo().getScreenWidth(),
                GamingInfo.getGamingInfo().getScreenHeight() + 38);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("捕鱼达人");
        MainSurface pane = new MainSurface();
        GamingInfo.getGamingInfo().setSurface(pane);
        frame.setContentPane(pane);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // 发射子弹
                CannonManager.getInstance().shot(x, y);
            }
        });

        frame.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (GameInitManager.getInstance().isIniting()) {
                    return;
                }
                // 屏幕被触摸
                // 先看布局管理器是否有响应
                int x = e.getX();
                int y = e.getY();
                if (!LayoutManager.getInstance().onClick(x, y)) {
                    // 发射子弹
                    CannonManager.getInstance().shot(x, y);
                }
            }
        });

        pane.action();
        ThreadTool.pool.execute(() -> {
            // 使用游戏初始化管理器初始化游戏
            GameInitManager.getInstance().init();
        });
    }

}
