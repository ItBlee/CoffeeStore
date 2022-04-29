package Utils;

import DTO.TaiKhoanDTO;

import static Utils.SystemConstant.*;

public class General {
    //USER
    public static TaiKhoanDTO CURRENT_USER = null;
    public static String USER_USERNAME = null;
    public static String USER_PASSWORD = null;
    public static boolean USER_IS_REMEMBER = false;

    //DATABASE CONFIG
    public static String DB_HOST = DEFAULT_DB_HOST;
    public static String DB_NAME = DEFAULT_DB_NAME;
    public static String DB_USERNAME = DEFAULT_DB_USERNAME;
    public static String DB_PASSWORD = DEFAULT_DB_PASSWORD;

    //Themes
    public static String THEME_INFO_NAME = DEFAULT_THEME_NAME;
}
