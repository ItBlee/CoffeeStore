import GUI.FrameLayout;
import GUI.FrameLogin;
import GUI.components.Language;
import GUI.components.Theme;
import Utils.FileHandler;
import Utils.General;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        int i;
        Properties config = FileHandler.importConfig();
        General.importMapper(config);
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
