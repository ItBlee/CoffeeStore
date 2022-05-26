package GUI.Form;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ILichSuBUS;
import BUS.LichSuBUS;
import DTO.LichSuDTO;
import GUI.common.MyColor;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

public class FormLichSu extends JPanel {
    private int countBoard;

    public FormLichSu() {
        initComponents();
        countBoard = 0;
        loadBoards();
    }

    private void loadBoards() {
        ILichSuBUS bus = new LichSuBUS();
        ArrayList<LichSuDTO> list = bus.findAll();
        for (int i = 1; i <= 10; i++) {
            try {
                createHistoryBoard(list.get(countBoard));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 270 + (125 * countBoard) - (countBoard/10)*200));
        timeLine.setBounds(timeLine.getX(), timeLine.getY(), timeLine.getWidth(), 150 + (125 * countBoard) - (countBoard/10)*200);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void initComponents() {
        setLayout(null);

        //======== mainPanel ========
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        timeLine = new JPanel();
        timeLine.setBackground(new Color(198, 202, 206));
        timeLine.setLayout(null);
        mainPanel.add(timeLine);
        timeLine.setBounds(495, 100, 5, 680);

        JButton btnCurrent = new JButton();
        btnCurrent.setBackground(new Color(198, 202, 206));
        btnCurrent.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnCurrent.setForeground(Color.white);
        btnCurrent.setText("Hiện tại");
        btnCurrent.setBorderPainted(false);
        btnCurrent.setFocusable(false);
        mainPanel.add(btnCurrent);
        btnCurrent.setBounds(427, 54, 140, 50);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(mainPanel);
        add(jScrollPane);
        jScrollPane.setBounds(0, 0, 1000, 807);

        JScrollBar jScrollBar = jScrollPane.getVerticalScrollBar();
        jScrollBar.setUnitIncrement(20);
        jScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (jScrollBar.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount())
                    loadMoreEvent();
            }
        });
    }

    public void loadMoreEvent() {
        loadBoards();
    }

    public void createHistoryBoard(LichSuDTO dto) {
        final int RIGHT_ALIGN = 0;
        final int LEFT_ALIGN = 1;
        countBoard++;

        int alignment;
        if (countBoard % 2 == 0)
            alignment = RIGHT_ALIGN;
        else alignment = LEFT_ALIGN;

        JPanel timeBox = new JPanel();
        timeBox.setBackground(new Color(198, 202, 206));
        timeBox.setLayout(null);
        mainPanel.add(timeBox);
        timeBox.setBounds(485, 75 + (100 * countBoard), 25, 25);

        JPanel timePanel = new JPanel();
        timePanel.setBackground(new Color(56, 56, 56));
        timePanel.setForeground(new Color(37, 37, 37));
        timePanel.setLayout(null);

        JLabel lbIconTime = new JLabel();
        lbIconTime.setIcon(new ImageIcon("bin/images/FormLichSu/time.png"));
        timePanel.add(lbIconTime);
        lbIconTime.setBounds(10, 10, 50, 24);

        JLabel lbTime = new JLabel();
        lbTime.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTime.setForeground(new Color(198, 202, 206));
        Date date = new Date(dto.getThoiGian().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        lbTime.setText(dateFormat.format(date));
        timePanel.add(lbTime);
        lbTime.setBounds(40, 12, 50, 18);

        mainPanel.add(timePanel);
        if (alignment == LEFT_ALIGN)
            timePanel.setBounds(385, 70 + (100 * countBoard), 90, 40);
        else timePanel.setBounds(520, 70 + (100 * countBoard), 90, 40);

        JPanel historyPanel = new JPanel();
        historyPanel.setBackground(new Color(255, 255, 255));
        historyPanel.setLayout(null);

        JPanel coverTitlePanel = new JPanel();
        coverTitlePanel.setBackground(new Color(0, 0, 0));
        coverTitlePanel.setLayout(null);

        JPanel actionMark = new JPanel();
        switch (dto.getThaoTac()) {
            case AbstractHistoricBUS.SAVE_FLAG:
                actionMark.setBackground(MyColor.GREEN);
                break;

            case AbstractHistoricBUS.UPDATE_FLAG:
                actionMark.setBackground(MyColor.ORANGE);
                break;

            case AbstractHistoricBUS.DELETE_FLAG:
                actionMark.setBackground(MyColor.RED);
                break;

            default:
                actionMark.setBackground(MyColor.Blue);
                break;
        }

        actionMark.setLayout(null);
        coverTitlePanel.add(actionMark);
        if (alignment == LEFT_ALIGN)
            actionMark.setBounds(10, 10, 20, 20);
        else actionMark.setBounds(270, 10, 20, 20);

        JLabel lbTitleHistory = new JLabel();
        lbTitleHistory.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitleHistory.setForeground(Color.white);
        lbTitleHistory.setText(dto.getThaoTac());
        coverTitlePanel.add(lbTitleHistory);
        if (alignment == LEFT_ALIGN)
            lbTitleHistory.setBounds(40, 10, 170, 20);
        else {
            lbTitleHistory.setBounds(87, 10, 170, 20);
            lbTitleHistory.setHorizontalAlignment(SwingConstants.RIGHT);
        }

        historyPanel.add(coverTitlePanel);
        coverTitlePanel.setBounds(0, 0, 300, 40);

        JLabel lbIconNameNV = new JLabel();
        lbIconNameNV.setIcon(new ImageIcon("bin/images/FormLichSu/user.png"));
        historyPanel.add(lbIconNameNV);
        lbIconNameNV.setBounds(12, 50, 16, 16);

        /*INhanVienBUS nhanVienBUS = new NhanVienBUS();
        NhanVienDTO nhanVienDTO = nhanVienBUS.findByMaTK(dto.getNguoiThucHien());
        String MaNV = "NV" + nhanVienDTO.getMaNV();
        String tenNV = nhanVienDTO.getHoTen();*/
        JLabel lbNameNV = new JLabel();
        lbNameNV.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbNameNV.setForeground(new Color(198, 202, 206));
        lbNameNV.setText("Nhân viên: " + dto.getNguoiThucHien());
        historyPanel.add(lbNameNV);
        lbNameNV.setBounds(30, 50, 260, 18);

        JLabel lbIconDate = new JLabel();
        lbIconDate.setIcon(new ImageIcon("bin/images/FormLichSu/date.png"));
        historyPanel.add(lbIconDate);
        lbIconDate.setBounds(10, 110, 16, 16);

        JLabel lbDate = new JLabel();
        lbDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbDate.setForeground(new Color(198, 202, 206));
        SimpleDateFormat timeFormat = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm:ss");
        lbDate.setText("Thời điểm: " + timeFormat.format(dto.getThoiGian()));
        historyPanel.add(lbDate);
        lbDate.setBounds(30, 110, 260, 18);

        JLabel lbIconTarget = new JLabel();
        lbIconTarget.setIcon(new ImageIcon("bin/images/FormLichSu/target.png"));
        historyPanel.add(lbIconTarget);
        lbIconTarget.setBounds(10, 80, 16, 16);

        JLabel lbTarget = new JLabel();
        lbTarget.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbTarget.setForeground(new Color(198, 202, 206));
        String targetDetail;
        switch (dto.getTenDoiTuong()) {
            case "HoaDon":
                targetDetail = "Hóa đơn - " + "HD" + dto.getMaDoiTuong();
                break;

            case "SanPham":
                targetDetail = "Sản phẩm - " + "SP" + dto.getMaDoiTuong();
                break;

            case "PhieuNhap":
                targetDetail = "Phiếu nhập - " + "PN" + dto.getMaDoiTuong();
                break;

            case "NhaCungCap":
                targetDetail = "Nhà cung cấp - " + "NCC" + dto.getMaDoiTuong();
                break;

            case "KhachHang":
                targetDetail = "Khách hàng - " + "KH" + dto.getMaDoiTuong();
                break;

            case "KhuyenMai":
                targetDetail = "Khuyến mãi - " + "KM" + dto.getMaDoiTuong();
                break;

            case "NhanVien":
                targetDetail = "Nhân viên - " + "NV" + dto.getMaDoiTuong();
                break;

            case "TaiKhoan":
                targetDetail = "Tài khoản - " + "TK" + dto.getMaDoiTuong();
                break;

            case "PhanQuyen":
                targetDetail = "Phân quyền - " + "PQ" + dto.getMaDoiTuong();
                break;

            case "LoaiSP":
                targetDetail = "Loại sản phẩm - " + "LSP" + dto.getMaDoiTuong();
                break;

            case "CT_HoaDon":
                targetDetail = "Chi tiết hóa đơn - " + "CTHD" + dto.getMaDoiTuong();
                break;

            case "CT_PhieuNhap":
                targetDetail = "Chi tiết phiếu nhập - " + "CTPN" + dto.getMaDoiTuong();
                break;

            case "CT_KhuyenMai":
                targetDetail = "Chi tiết khuyến mãi - " + "CTKM" + dto.getMaDoiTuong();
                break;

            case "CT_PhanQuyen":
                targetDetail = "Chi tiết phân quyền - " + "CTPQ" + dto.getMaDoiTuong();
                break;

            default:
                targetDetail = "Không xác định";
                break;
        }
        lbTarget.setText("Đối tượng: " + targetDetail);
        historyPanel.add(lbTarget);
        lbTarget.setBounds(30, 80, 260, 18);

        mainPanel.add(historyPanel);
        if (alignment == LEFT_ALIGN)
            historyPanel.setBounds(165, 80 + (100 * countBoard), 300, 140);
        else historyPanel.setBounds(530, 80 + (100 * countBoard), 300, 140);
    }

    public void reload() {
        removeAll();
        initComponents();
        countBoard = 0;
        loadBoards();
        revalidate();
        repaint();
    }

    private JPanel mainPanel;
    private JPanel timeLine;
}
