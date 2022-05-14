package Utils;

import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.common.Language;
import GUI.common.Theme;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;

import java.awt.*;
import java.util.Properties;

import static Utils.SystemConstant.*;

public class General {
    //USER
    public static NhanVienDTO CURRENT_USER = null;
    public static Role CURRENT_ROLE = null;
    public static String USER_USERNAME = null;
    public static String USER_PASSWORD = null;
    public static boolean USER_IS_REMEMBER = false;

    //DATABASE CONFIG
    public static String DB_HOST = DEFAULT_DB_HOST;
    public static String DB_NAME = DEFAULT_DB_NAME;
    public static String DB_USERNAME = DEFAULT_DB_USERNAME;
    public static String DB_PASSWORD = DEFAULT_DB_PASSWORD;

    public static Properties exportMapper() {
        Properties prop = new Properties();

        if (USER_IS_REMEMBER) {
            prop.setProperty(CONFIG_PROP_USER_USERNAME, USER_USERNAME);
            prop.setProperty(CONFIG_PROP_USER_PASSWORD, Security.encode(USER_PASSWORD));
            prop.setProperty(CONFIG_PROP_USER_REMEMBER, String.valueOf(USER_IS_REMEMBER));
        }
        prop.setProperty(CONFIG_PROP_DB_HOST, DB_HOST);
        prop.setProperty(CONFIG_PROP_DB_NAME, DB_NAME);
        prop.setProperty(CONFIG_PROP_DB_USERNAME, DB_USERNAME);
        if (!DB_PASSWORD.isBlank())
            prop.setProperty(CONFIG_PROP_DB_PASSWORD, Security.encode(DB_PASSWORD));
        prop.setProperty(CONFIG_PROP_THEME_NAME, Theme.getSystemThemeInfo().getName());
        if (Theme.getSystemThemeFont() != null) {
            prop.setProperty(CONFIG_PROP_THEME_FONT_NAME, Theme.getSystemThemeFont().getName());
            prop.setProperty(CONFIG_PROP_THEME_FONT_STYLE, String.valueOf(Theme.getSystemThemeFont().getStyle()));
            prop.setProperty(CONFIG_PROP_THEME_FONT_SIZE, String.valueOf(Theme.getSystemThemeFont().getSize()));
        }
        if (Language.getSystemLanguage() != null)
            prop.setProperty(CONFIG_PROP_LANGUAGE_CODE, Language.getSystemLanguage().getDisplayName());
        return prop;
    }

    public static void importMapper(Properties prop) {
        USER_USERNAME = prop.getProperty(CONFIG_PROP_USER_USERNAME);
        USER_PASSWORD = Security.decode(prop.getProperty(CONFIG_PROP_USER_PASSWORD));
        USER_IS_REMEMBER = Boolean.parseBoolean(prop.getProperty(CONFIG_PROP_USER_REMEMBER));

        String dbHost = prop.getProperty(CONFIG_PROP_DB_HOST);
        String dbName = prop.getProperty(CONFIG_PROP_DB_NAME);
        String dbUsername = prop.getProperty(CONFIG_PROP_DB_USERNAME);
        String dbPassword = prop.getProperty(CONFIG_PROP_DB_PASSWORD);
        if (dbHost != null && !dbHost.isBlank())
            DB_HOST = dbHost;
        if (dbName != null && !dbName.isBlank())
            DB_NAME = dbName;
        if (dbUsername != null && !dbUsername.isBlank())
            DB_USERNAME = dbUsername;
        if (dbPassword != null && !dbPassword.isBlank())
            DB_PASSWORD = Security.decode(dbPassword);

        String themeName = prop.getProperty(CONFIG_PROP_THEME_NAME);
        FlatAllIJThemes.FlatIJLookAndFeelInfo theme = Theme.getThemeInfoByName(themeName);
        if (theme != null)
            Theme.setSystemThemeInfo(theme);

        String fontName = prop.getProperty(CONFIG_PROP_THEME_FONT_NAME);
        if (fontName != null && !fontName.isBlank()) {
            int fontStyle, fontSize;
            try {
                fontStyle = Integer.parseInt(prop.getProperty(CONFIG_PROP_THEME_FONT_STYLE));
            } catch (NumberFormatException e) {
                fontStyle = Font.PLAIN;
            }
            try {
                fontSize = Integer.parseInt(prop.getProperty(CONFIG_PROP_THEME_FONT_SIZE));
            } catch (NumberFormatException e) {
                fontSize = 14;
            }
            Theme.setSystemThemeFont(new java.awt.Font(fontName, fontStyle, fontSize));
        }
        String languageCode = prop.getProperty(CONFIG_PROP_LANGUAGE_CODE);
        if (languageCode != null)
            Language.setSystemLanguage(Language.getLanguageByDisplayName(languageCode));
    }
}
