package GUI;

import Utils.FileHandler;
import GUI.components.Themes;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FileHandler.importConfig();
        FlatDarkLaf.setup();
        Themes.setupComponentStyle();

        JFrame frame = new FrameLogin();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
                frame.requestFocusInWindow();
            }
        });
    }
}
