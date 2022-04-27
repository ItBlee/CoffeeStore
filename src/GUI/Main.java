package GUI;

import Utils.FileHandler;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame frame;

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        FileHandler.importConfig();

        frame = new LoginForm();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
