package GUI.components;

import javax.swing.*;
import static Utils.FileHandler.createImageIcon;

public class Category {
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

    public Category() {
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
        this.icon = createImageIcon(imagePath, ICON_SIZE, ICON_SIZE);
    }

    public ImageIcon getIconHover() {
        return iconHover;
    }

    public void setIconHover(String imgHoverPath) {
        this.iconHover = createImageIcon(imgHoverPath, ICON_SIZE, ICON_SIZE);
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
}
