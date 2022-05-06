import GUI.FrameLogin;
import GUI.components.Language;
import Utils.FileHandler;
import GUI.components.Theme;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FileHandler.importConfig();
        Language.setup();
        Theme.setupDefault();
        Theme.setupComponentStyle();

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
