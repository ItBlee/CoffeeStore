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
    private String formClassName;

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

    public JPanel renewForm() {
        if (form == null)
            return null;
        try {
            form = form.getClass().getConstructor().newInstance();
        } catch (Exception ignored) {}
        return form;
    }

    public JPanel getRootForm() {
        return form;
    }

    public JPanel getForm() {
        if (form == null && formClassName != null) {
            try {
                form = (JPanel) Class.forName(formClassName).getConstructor().newInstance();
            } catch (Exception ignored) {}
        }
        return form;
    }

    public void setForm(JPanel form) {
        this.form = form;
        this.formClassName = this.form.getClass().getName();
    }

    public String getFormClassName() {
        if (form != null)
            return form.getClass().getName();
        return formClassName;
    }

    public void setFormClassName(String formClassName) {
        if (form == null || formClassName.equals(form.getClass().getName()))
            this.formClassName = formClassName;
    }
}
