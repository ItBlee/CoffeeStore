package Utils;

import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;

import java.lang.reflect.Method;

public class Themes {
    public static FlatAllIJThemes.FlatIJLookAndFeelInfo[] getThemeInfoList() {
        return FlatAllIJThemes.INFOS;
    }

    public static boolean isExist(String themeName) {
        try {
            getThemeClassByName(themeName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Class<?> getThemeClassByName(String name) throws ClassNotFoundException {
        String className = "";
        for (FlatAllIJThemes.FlatIJLookAndFeelInfo laf : getThemeInfoList()) {
            if (laf.getName().equalsIgnoreCase(name)) {
                className = laf.getClassName();
                break;
            }
        }
        return Class.forName(className);
    }

    public static void setupThemeByName(String name) {
        try {
            Class<?> c = getThemeClassByName(name);
            Method m = c.getMethod("setup");
            m.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
