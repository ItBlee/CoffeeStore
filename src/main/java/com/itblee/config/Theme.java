package com.itblee.config;

import com.itblee.util.FileUtil;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes.FlatIJLookAndFeelInfo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.itblee.constant.SystemConstant.*;

public class Theme {
    private static FlatIJLookAndFeelInfo systemThemeInfo;
    private static Font systemThemeFont;

    public static void setupDefault() {
        if (systemThemeInfo == null)
            systemThemeInfo = new FlatIJLookAndFeelInfo(
                    DEFAULT_THEME_NAME, DEFAULT_THEME_CLASS, DEFAULT_THEME_DARK_MODE
            );
        FlatDarkLaf.setup();
        registerFont("bin/font/segoeui.ttf");
        registerFont("bin/font/seguibl.ttf");
    }

    public static FlatIJLookAndFeelInfo getSystemThemeInfo() {
        return systemThemeInfo;
    }

    public static void setSystemThemeInfo(FlatIJLookAndFeelInfo systemThemeInfo) {
        if (getIndexOf(systemThemeInfo) != -1)
            Theme.systemThemeInfo = systemThemeInfo;
    }

    public static Font getSystemThemeFont() {
        return systemThemeFont;
    }

    public static void setSystemThemeFont(Font systemThemeFont) {
        Theme.systemThemeFont = systemThemeFont;
    }

    public static FlatIJLookAndFeelInfo[] getThemeInfoList() {
        return FlatAllIJThemes.INFOS;
    }

    public static int getIndexOf(FlatIJLookAndFeelInfo themeInfo) {
        FlatIJLookAndFeelInfo[] themeInfoList = Theme.getThemeInfoList();
        int currentThemeIndex = -1;
        for (int i = 0; i < themeInfoList.length; i++) {
            if (themeInfo.getName().equals(themeInfoList[i].getName())
                    && themeInfo.getClassName().equals(themeInfoList[i].getClassName())
                    && themeInfo.isDark() == themeInfoList[i].isDark())
                currentThemeIndex = i;
        }
        return currentThemeIndex;
    }

    public static FlatIJLookAndFeelInfo getThemeInfoByName(String themeName) {
        for (FlatIJLookAndFeelInfo laf : getThemeInfoList())
            if (laf.getName().equalsIgnoreCase(themeName))
                return laf;
        return null;
    }

    public static void setupThemeByInfo(FlatIJLookAndFeelInfo info) {
        try {
            Class<?> c = Class.forName(info.getClassName());
            Method m = c.getMethod("setup");
            m.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupComponentStyle() {
        UIManager.put( "Component.arrowType", "triangle" );
        UIManager.put( "ScrollBar.showButtons", true );
        UIManager.put( "ScrollBar.trackArc", 999 );
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.trackInsets", new Insets( 2, 4, 2, 4 ) );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put( "ScrollBar.track", new Color( 0xe0e0e0 ) );
        UIManager.put( "TabbedPane.tabWidthMode", "compact" );
        UIManager.put( "TabbedPane.showTabSeparators", true );
    }

    public static void registerFont(String fontFilePath) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, FileUtil.getFile(fontFilePath));
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            environment.registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
