package Utils;

import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes.FlatIJLookAndFeelInfo;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class Themes {
    public static FlatIJLookAndFeelInfo[] getThemeInfoList() {
        return FlatAllIJThemes.INFOS;
    }

    public static int getIndexOfTheme(FlatIJLookAndFeelInfo themeInfo) {
        FlatIJLookAndFeelInfo[] themeInfoList = Themes.getThemeInfoList();
        int currentThemeIndex = 0;
        for (int i = 0; i < themeInfoList.length; i++) {
            if (themeInfo.getName().equals(themeInfoList[i].getName())
                    && themeInfo.getClassName().equals(themeInfoList[i].getClassName())
                    && themeInfo.isDark() == themeInfoList[i].isDark())
                currentThemeIndex = i;
        }
        return currentThemeIndex;
    }

    public static FlatIJLookAndFeelInfo getThemeInfoByName(String themeName) {
        for (FlatIJLookAndFeelInfo laf : getThemeInfoList()) {
            if (laf.getName().equalsIgnoreCase(themeName)) {
                return laf;
            }
        }
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
}
