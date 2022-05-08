package GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMovableJFrame extends JFrame {
    public JMovableJFrame() {
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        setMovable(true);
    }

    public static class FrameDragListener extends MouseAdapter {
        private static boolean isEnable = true;
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            if (!isEnable)
                return;
            try {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            } catch (Exception ignored) {}
        }

        public static void setEnable(boolean b) {
            isEnable = b;
        }
    }

    public void setMovable(boolean movable) {
        FrameDragListener.setEnable(movable);
    }
}
