package GUI.components;

import GUI.common.Language;
import GUI.common.ThumbnailFileChooser;
import org.drjekyll.fontchooser.FontDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class ChooserJDialog {
    /**
     * Mở Dialog để người dùng chọn file muốn thao tác
     * @param path đường dẫn mặc định khi mở.
     * @return String - đường dẫn File, trả về NULL nếu hủy/thoát Dialog
     */
    public static File showFileChooser(String path) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls");
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setDialogTitle(Language.DIALOG_FILE_TITLE);
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile();
        return null;
    }

    /**
     * Mở Dialog để người dùng chọn file muốn thao tác
     * @param path đường dẫn mặc định khi mở.
     * @return String - đường dẫn File, trả về NULL nếu hủy/thoát Dialog
     */
    public static File showImageChooser(String path) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image Files", "png", "jpg", "JPEG");
        ThumbnailFileChooser imageChooser = new ThumbnailFileChooser(path);
        imageChooser.setDialogTitle(Language.DIALOG_FILE_TITLE);
        imageChooser.setFileFilter(filter);
        if (imageChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return imageChooser.getSelectedFile();
        return null;
    }

    /**
     * Mở Dialog để người dùng chọn font muốn thao tác
     * @return Font
     */
    public static Font showFontChooser(Frame parent) {
        FontDialog dialog = new FontDialog(parent, Language.DIALOG_FONT_TITLE, true);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (!dialog.isCancelSelected())
            return dialog.getSelectedFont();
        return null;
    }
}
