package GUI.components;

import javax.swing.*;
import java.awt.*;

public class MenuItem {
    public static final int ICON_SIZE = 36;
    public static final int ITEM_BUTTON_SIZE = 70;
    public static final int DEFAULT_X = 110;
    public static final int START_Y = 120;
    private String code;
    private JButton button;
    private String toolTipText;
    private ImageIcon icon;
    private ImageIcon iconHover;
    private int keyBlind;
    private JPanel form;

    public MenuItem() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public String getToolTipText() {
        return toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(String imagePath) {
        this.icon = createImageIcon(imagePath);
    }

    public ImageIcon getIconHover() {
        return iconHover;
    }

    public void setIconHover(String imgHoverPath) {
        this.iconHover = createImageIcon(imgHoverPath);
    }

    public int getKeyBlind() {
        return keyBlind;
    }

    public void setKeyBlind(int keyBlind) {
        this.keyBlind = keyBlind;
    }

    public JPanel getForm() {
        return form;
    }

    public void setForm(JPanel form) {
        this.form = form;
    }

    private ImageIcon createImageIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconHeight() != MenuItem.ICON_SIZE || icon.getIconWidth() != MenuItem.ICON_SIZE) {
            Image scale = icon.getImage().getScaledInstance(MenuItem.ICON_SIZE, MenuItem.ICON_SIZE, Image.SCALE_DEFAULT);
            icon = new ImageIcon(scale);
        }
        return icon;
    }
}
