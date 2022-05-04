package Utils;

import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes.FlatIJLookAndFeelInfo;

import javax.swing.*;
import java.awt.*;

import static Utils.SystemConstant.*;

public class General {
    //USER
    public static TaiKhoanDTO CURRENT_USER = null;
    public static NhanVienDTO CURRENT_EMPLOYEE = null;
    public static String USER_USERNAME = null;
    public static String USER_PASSWORD = null;
    public static boolean USER_IS_REMEMBER = false;

    //DATABASE CONFIG
    public static String DB_HOST = DEFAULT_DB_HOST;
    public static String DB_NAME = DEFAULT_DB_NAME;
    public static String DB_USERNAME = DEFAULT_DB_USERNAME;
    public static String DB_PASSWORD = DEFAULT_DB_PASSWORD;

    //Themes
    public static FlatIJLookAndFeelInfo THEME_INFO = new FlatIJLookAndFeelInfo(DEFAULT_THEME_NAME, DEFAULT_THEME_CLASS, DEFAULT_THEME_DARK_MODE);
    public static Font THEME_FONT = UIManager.getDefaults().getFont("TabbedPane.font");
}
