package com.itblee.config;

import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import com.itblee.constant.SystemConstant;
import com.itblee.util.FileUtil;
import com.itblee.util.StringUtils;
import com.itblee.security.Encryptor;

import java.awt.*;
import java.util.Properties;

import static com.itblee.General.*;

public class Config {

    public static Properties save() {
        Properties prop = new Properties();

        if (isRemember) {
            prop.setProperty("user.username", username);
            prop.setProperty("user.password", Encryptor.encode(password));
            prop.setProperty("user.remember", String.valueOf(isRemember));
        }
        prop.setProperty("theme.name", Theme.getSystemThemeInfo().getName());
        if (Theme.getSystemThemeFont() != null) {
            prop.setProperty("theme.FONT.name", Theme.getSystemThemeFont().getName());
            prop.setProperty("theme.FONT.style", String.valueOf(Theme.getSystemThemeFont().getStyle()));
            prop.setProperty("theme.FONT.size", String.valueOf(Theme.getSystemThemeFont().getSize()));
        }
        if (Language.getSystemLanguage() != null)
            prop.setProperty("language.code", Language.getSystemLanguage().getDisplayName());
        FileUtil.saveConfig(prop);
        return prop;
    }

    public static void load() {
        load(FileUtil.loadConfig(SystemConstant.CONFIG_FILE_URL));
    }

    public static void load(Properties prop) {
        username = prop.getProperty("user.username");
        password = Encryptor.decode(prop.getProperty("user.password"));
        isRemember = Boolean.parseBoolean(prop.getProperty("user.remember"));

        String themeName = prop.getProperty("THEME.name");
        FlatAllIJThemes.FlatIJLookAndFeelInfo theme = Theme.getThemeInfoByName(themeName);
        if (theme != null)
            Theme.setSystemThemeInfo(theme);

        String fontName = prop.getProperty("theme.font.name");
        if (StringUtils.isNotBlank(fontName)) {
            int fontStyle, fontSize;
            try {
                fontStyle = Integer.parseInt(prop.getProperty("theme.font.style"));
            } catch (NumberFormatException e) {
                fontStyle = Font.PLAIN;
            }
            try {
                fontSize = Integer.parseInt(prop.getProperty("theme.font.size"));
            } catch (NumberFormatException e) {
                fontSize = 14;
            }
            Theme.setSystemThemeFont(new Font(fontName, fontStyle, fontSize));
        }
        String languageCode = prop.getProperty("language.code");
        if (languageCode != null)
            Language.setSystemLanguage(Language.getLanguageByDisplayName(languageCode));
    }

}
