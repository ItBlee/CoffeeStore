package GUI.common;

import Utils.FileHandler;

import java.util.*;

public class Language {
    public static final Language VIETNAMESE = new Language("vi");
    public static final Language ENGLISH = new Language("en");
    private static final ArrayList<Language> languageList = new ArrayList<Language>(Arrays.asList(VIETNAMESE, ENGLISH));
    private static Language systemLanguage;

    private String name;
    private String displayName;

    public Language(String displayName) {
        this.displayName = displayName;
    }

    public static void setup() {
        if (getSystemLanguage() == null)
            setSystemLanguage(VIETNAMESE);
        importLanguage(Language.systemLanguage);
    }

    public static void importLanguage(Language language) {
        String filePath = "bin/languages/" + language.getDisplayName() + ".language";
        Properties prop = FileHandler.importConfig(filePath);
        importMapper(prop);
    }

    public static Language getSystemLanguage() {
        return systemLanguage;
    }

    public static void setSystemLanguage(Language systemLanguage) {
        if (systemLanguage != null)
            Language.systemLanguage = systemLanguage;
    }

    public static ArrayList<Language> getLanguageList() {
        return languageList;
    }

    public static Language getLanguageByDisplayName(String displayName) {
        for (Language language : languageList) {
            if (language.getDisplayName().equals(displayName))
                return language;
        }
        return null;
    }

    public static Language getLanguageByName(String name) {
        for (Language language : languageList) {
            if (language.getName().equals(name))
                return language;
        }
        return null;
    }

    public String getName() {
        if (name == null)
            name = "UNKNOWN LANGUAGE";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private static void importMapper(Properties prop) {
        VIETNAMESE.setName(prop.getProperty("LANGUAGE_VI"));
        ENGLISH.setName(prop.getProperty("LANGUAGE_EN"));
        DIALOG_FILE_TITLE = prop.getProperty("DIALOG_FILE_TITLE");
        DIALOG_FONT_TITLE = prop.getProperty("DIALOG_FONT_TITLE");
        LOGIN_LABEL_USERNAME = prop.getProperty("LOGIN_LABEL_USERNAME");
        LOGIN_LABEL_PASSWORD = prop.getProperty("LOGIN_LABEL_PASSWORD");
        LOGIN_BUTTON_TEXT = prop.getProperty("LOGIN_BUTTON_TEXT");
        LOGIN_USERNAME_PLACEHOLDER = prop.getProperty("LOGIN_USERNAME_PLACEHOLDER");
        LOGIN_PASSWORD_PLACEHOLDER = prop.getProperty("LOGIN_PASSWORD_PLACEHOLDER");
        LOGIN_CHECKBOX_REMEMBER_ME = prop.getProperty("LOGIN_CHECKBOX_REMEMBER_ME");
        LOGIN_INVALID_INPUT = prop.getProperty("LOGIN_INVALID_INPUT");
        LOGIN_ERROR = prop.getProperty("LOGIN_ERROR");
        LAYOUT_BUTTON_HD = prop.getProperty("LAYOUT_BUTTON_HD");
        LAYOUT_BUTTON_SP = prop.getProperty("LAYOUT_BUTTON_SP");
        LAYOUT_BUTTON_PN = prop.getProperty("LAYOUT_BUTTON_PN");
        LAYOUT_BUTTON_NCC = prop.getProperty("LAYOUT_BUTTON_NCC");
        LAYOUT_BUTTON_KH = prop.getProperty("LAYOUT_BUTTON_KH");
        LAYOUT_BUTTON_KM = prop.getProperty("LAYOUT_BUTTON_KM");
        LAYOUT_BUTTON_TK = prop.getProperty("LAYOUT_BUTTON_TK");
        LAYOUT_BUTTON_EXCEL = prop.getProperty("LAYOUT_BUTTON_EXCEL");
        LAYOUT_BUTTON_NV = prop.getProperty("LAYOUT_BUTTON_NV");
        LAYOUT_SETTING_TITLE = prop.getProperty("LAYOUT_SETTING_TITLE");
        LAYOUT_SETTING_SAVE = prop.getProperty("LAYOUT_SETTING_SAVE");
        LAYOUT_SETTING_SAVE_CANCEL = prop.getProperty("LAYOUT_SETTING_SAVE_CANCEL");
        LAYOUT_LABEL_THEME_CHOOSER = prop.getProperty("LAYOUT_LABEL_THEME_CHOOSER");
        LAYOUT_LABEL_LANGUAGE_CHOOSER = prop.getProperty("LAYOUT_LABEL_LANGUAGE_CHOOSER");
        LAYOUT_LABEL_FONT_CHOOSER = prop.getProperty("LAYOUT_LABEL_FONT_CHOOSER");
        LAYOUT_BUTTON_FONT_CHOOSER = prop.getProperty("LAYOUT_BUTTON_FONT_CHOOSER");
        LAYOUT_INFO_TITLE = prop.getProperty("LAYOUT_INFO_TITLE");
        LAYOUT_BUTTON_INFO_BACK = prop.getProperty("LAYOUT_BUTTON_INFO_BACK");
        LAYOUT_BUTTON_CHANGE_PASSWORD = prop.getProperty("LAYOUT_BUTTON_CHANGE_PASSWORD");
        LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_OLD = prop.getProperty("LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_OLD");
        LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_NEW = prop.getProperty("LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_NEW");
        LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_CONFIRM = prop.getProperty("LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_CONFIRM");
        LAYOUT_LABEL_CONTACT_TOOL_TIP_TEXT = prop.getProperty("LAYOUT_LABEL_CONTACT_TOOL_TIP_TEXT");
        LAYOUT_MESSAGE_CONTACT = prop.getProperty("LAYOUT_MESSAGE_CONTACT");
        LAYOUT_LABEL_LOGOUT_TOOL_TIP_TEXT = prop.getProperty("LAYOUT_LABEL_LOGOUT_TOOL_TIP_TEXT");
        LAYOUT_MESSAGE_LOGOUT = prop.getProperty("LAYOUT_MESSAGE_LOGOUT");
        LAYOUT_LABEL_SETTING_TOOL_TIP_TEXT = prop.getProperty("LAYOUT_LABEL_SETTING_TOOL_TIP_TEXT");
        LAYOUT_MESSAGE_SETTING_QUESTION = prop.getProperty("LAYOUT_MESSAGE_SETTING_QUESTION");
        LAYOUT_SETTING_FINISH_TITLE = prop.getProperty("LAYOUT_SETTING_FINISH_TITLE");
        LAYOUT_MESSAGE_SETTING_FINISH = prop.getProperty("LAYOUT_MESSAGE_SETTING_FINISH");
    }

    public static String DIALOG_FILE_TITLE;
    public static String DIALOG_FONT_TITLE;
    public static String LOGIN_LABEL_USERNAME;
    public static String LOGIN_LABEL_PASSWORD;
    public static String LOGIN_BUTTON_TEXT;
    public static String LOGIN_USERNAME_PLACEHOLDER;
    public static String LOGIN_PASSWORD_PLACEHOLDER;
    public static String LOGIN_CHECKBOX_REMEMBER_ME;
    public static String LOGIN_INVALID_INPUT;
    public static String LOGIN_ERROR;
    public static String LAYOUT_BUTTON_HD;
    public static String LAYOUT_BUTTON_SP;
    public static String LAYOUT_BUTTON_PN;
    public static String LAYOUT_BUTTON_NCC;
    public static String LAYOUT_BUTTON_KH;
    public static String LAYOUT_BUTTON_KM;
    public static String LAYOUT_BUTTON_TK;
    public static String LAYOUT_BUTTON_EXCEL;
    public static String LAYOUT_BUTTON_NV;
    public static String LAYOUT_SETTING_TITLE;
    public static String LAYOUT_SETTING_SAVE;
    public static String LAYOUT_SETTING_SAVE_CANCEL;
    public static String LAYOUT_LABEL_THEME_CHOOSER;
    public static String LAYOUT_LABEL_LANGUAGE_CHOOSER;
    public static String LAYOUT_LABEL_FONT_CHOOSER;
    public static String LAYOUT_BUTTON_FONT_CHOOSER;
    public static String LAYOUT_INFO_TITLE;
    public static String LAYOUT_BUTTON_INFO_BACK;
    public static String LAYOUT_BUTTON_CHANGE_PASSWORD;
    public static String LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_OLD;
    public static String LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_NEW;
    public static String LAYOUT_BUTTON_CHANGE_PASSWORD_LABEL_CONFIRM;
    public static String LAYOUT_LABEL_CONTACT_TOOL_TIP_TEXT;
    public static String LAYOUT_MESSAGE_CONTACT;
    public static String LAYOUT_LABEL_LOGOUT_TOOL_TIP_TEXT;
    public static String LAYOUT_MESSAGE_LOGOUT;
    public static String LAYOUT_LABEL_SETTING_TOOL_TIP_TEXT;
    public static String LAYOUT_MESSAGE_SETTING_QUESTION;
    public static String LAYOUT_SETTING_FINISH_TITLE;
    public static String LAYOUT_MESSAGE_SETTING_FINISH;
}