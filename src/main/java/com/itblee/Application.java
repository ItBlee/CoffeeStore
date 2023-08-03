package com.itblee;

import com.itblee.config.Config;
import com.itblee.gui.FrameLogin;
import com.itblee.config.Language;
import com.itblee.config.Theme;

import java.awt.*;

public class Application {

    public static void main(String[] args) {
        Config.load();
        Language.setup();
        Theme.setupDefault();
        Theme.setupComponentStyle();

        General.frame = new FrameLogin();
        EventQueue.invokeLater(() -> {
            General.frame.setVisible(true);
            General.frame.requestFocusInWindow();
        });
    }

}
