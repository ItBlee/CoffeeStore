package GUI;

import Utils.FileHandler;
import Utils.General;
import Utils.Themes;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Themes.setupThemeByName(General.THEME_INFO_NAME);
        FileHandler.importConfig();

        JFrame frame = new FormLogin();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
                frame.requestFocusInWindow();
            }
        });
    }
}
