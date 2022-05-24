package GUI.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableCell extends JLabel {
    private final CellType cellType;
    private boolean selected;

    public TableCell(Object obj) {
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.cellType = CellType.EMPTY;
        if (obj != null) {
            setText(obj.toString());
        }
        setBorder(new EmptyBorder(10, 10, 10, 10));
        if (getText().equalsIgnoreCase("Hoạt động"))
            setForeground(MyColor.GREEN);
        else if (getText().equalsIgnoreCase("Vô hiệu"))
            setForeground(MyColor.RED);
        else setForeground(new Color(80, 80, 80));
    }

    public TableCell(Object obj, boolean selected, CellType cellType) {
        this.selected = selected;
        this.cellType = cellType;
        if (obj != null) {
            setText(obj.toString());
        }
        setBorder(new EmptyBorder(10, 10, 10, 10));
        if (getText().equalsIgnoreCase("Hoạt động"))
            setForeground(MyColor.GREEN);
        else if (getText().equalsIgnoreCase("Vô hiệu")
                || getText().equalsIgnoreCase("Chưa sở hữu")
                || getText().equalsIgnoreCase("Chưa có tài khoản"))
            setForeground(MyColor.RED);
        else setForeground(new Color(80, 80, 80));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth() + 5;
        int height = getHeight() - 4;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(245, 245, 245));
        if (cellType == CellType.LEFT) {
            g2.fillRoundRect(0, 2, width, height, 6, 6);
            if (selected) {
                g2.setColor(new Color(137, 215, 255));
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 2, width, height, 6, 6);
            }
        } else if (cellType == CellType.RIGHT) {
            g2.fillRoundRect(-5, 2, width, height, 6, 6);
            if (selected) {
                g2.setColor(new Color(137, 215, 255));
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(-5, 2, width - 2, height, 6, 6);
            }
        } else if (cellType == CellType.CENTER) {
            g2.fillRect(0, 2, width, height);
            if (selected) {
                g2.setColor(new Color(137, 215, 255));
                g2.setStroke(new BasicStroke(2f));
                g2.drawRect(-5, 2, width + 2, height);
            }
        } else {
            g2.setColor(Color.white);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(grphcs);
    }

    public static enum CellType {
        LEFT, RIGHT, CENTER, EMPTY
    }
}
