package GUI.Form;

import GUI.Form.Abstract.JTablePanel;

import javax.swing.*;
import java.awt.*;

public class FormKhuyenMai extends JTablePanel {

    public FormKhuyenMai() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        tablePanel.setBackground(new Color(255, 255, 255));
        tablePanel.setLayout(null);

        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle.setForeground(new Color(37, 57, 111));
        lbTableTitle.setText("Danh sách");
        tablePanel.add(lbTableTitle);
        lbTableTitle.setBounds(30, 10, 240, 40);

        btnTimKiem.setBackground(new Color(229, 239, 255));
        btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTimKiem.setForeground(new Color(54, 123, 245));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setFocusPainted(false);
        tablePanel.add(btnTimKiem);
        btnTimKiem.setBounds(410, 20, 170, 35);

        btnReset.setText("jButton3");
        tablePanel.add(btnReset);
        btnReset.setBounds(590, 20, 40, 35);

        add(tablePanel);
        tablePanel.setBounds(10, 400, 650, 410);

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        ctKMPanel.setBackground(new Color(255, 255, 255));
        ctKMPanel.setLayout(null);

        lbKhuyenMaiUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbKhuyenMaiUnit.setText("đồng");
        ctKMPanel.add(lbKhuyenMaiUnit);
        lbKhuyenMaiUnit.setBounds(390, 100, 50, 40);

        lbCTKMTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCTKMTitle.setForeground(new Color(37, 57, 111));
        lbCTKMTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbCTKMTitle.setText("Chi tiết khuyến mãi");
        ctKMPanel.add(lbCTKMTitle);
        lbCTKMTitle.setBounds(110, 20, 270, 40);

        txtMaTK1.setBackground(new Color(245, 245, 245));
        txtMaTK1.setEnabled(false);
        ctKMPanel.add(txtMaTK1);
        txtMaTK1.setBounds(30, 100, 70, 35);

        lbMaKM.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaKM.setText("Mã KM");
        ctKMPanel.add(lbMaKM);
        lbMaKM.setBounds(31, 80, 70, 20);

        txtSanPham.setBackground(new Color(245, 245, 245));
        txtSanPham.setEnabled(false);
        ctKMPanel.add(txtSanPham);
        txtSanPham.setBounds(155, 160, 285, 35);

        lbSanPham.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSanPham.setText("Sản phẩm");
        ctKMPanel.add(lbSanPham);
        lbSanPham.setBounds(156, 140, 120, 20);

        txtMaSP.setBackground(new Color(245, 245, 245));
        txtMaSP.setEnabled(false);
        ctKMPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 160, 70, 35);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã SP");
        ctKMPanel.add(lbMaSP);
        lbMaSP.setBounds(31, 140, 70, 20);

        txtKhuyenMai.setBackground(new Color(245, 245, 245));
        txtKhuyenMai.setEnabled(false);
        ctKMPanel.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(110, 100, 330, 35);

        lbKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKhuyenMai.setText("Khuyến mãi");
        ctKMPanel.add(lbKhuyenMai);
        lbKhuyenMai.setBounds(111, 80, 120, 20);

        btnSelectSP.setText("jButton1");
        ctKMPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 35, 35);

        btnThemCT.setBackground(new Color(220, 247, 227));
        btnThemCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemCT.setForeground(new Color(47, 168, 79));
        btnThemCT.setText("Thêm");
        btnThemCT.setBorderPainted(false);
        btnThemCT.setFocusPainted(false);
        ctKMPanel.add(btnThemCT);
        btnThemCT.setBounds(30, 275, 410, 40);

        btnSuaCT.setBackground(new Color(252, 243, 215));
        btnSuaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaCT.setForeground(new Color(243, 170, 24));
        btnSuaCT.setText("Sửa");
        btnSuaCT.setBorderPainted(false);
        btnSuaCT.setFocusPainted(false);
        ctKMPanel.add(btnSuaCT);
        btnSuaCT.setBounds(30, 330, 200, 40);

        btnXoaCT.setBackground(new Color(254, 228, 226));
        btnXoaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaCT.setForeground(new Color(234, 61, 47));
        btnXoaCT.setText("Xóa");
        btnXoaCT.setBorderPainted(false);
        btnXoaCT.setFocusPainted(false);
        ctKMPanel.add(btnXoaCT);
        btnXoaCT.setBounds(240, 330, 200, 40);

        pluginPanel.add(ctKMPanel);
        ctKMPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin khuyến mãi");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMakM.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMakM.setText("Mã");
        infoPanel.add(lbMakM);
        lbMakM.setBounds(31, 80, 70, 20);

        lbTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTieuDe.setText("Tiêu đề");
        infoPanel.add(lbTieuDe);
        lbTieuDe.setBounds(121, 80, 120, 20);

        txtMaKM.setBackground(new Color(245, 245, 245));
        txtMaKM.setEnabled(false);
        infoPanel.add(txtMaKM);
        txtMaKM.setBounds(30, 100, 70, 35);
        infoPanel.add(txtTieuDe);
        txtTieuDe.setBounds(120, 100, 350, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(310, 275, 160, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(310, 220, 160, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(310, 330, 160, 40);

        lbNoiDung.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNoiDung.setText("Nội dung");
        infoPanel.add(lbNoiDung);
        lbNoiDung.setBounds(31, 200, 130, 20);

        lbNgayBD.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayBD.setText("Ngày BD");
        infoPanel.add(lbNgayBD);
        lbNgayBD.setBounds(31, 140, 120, 20);
        infoPanel.add(txtNgayBD);
        txtNgayBD.setBounds(30, 160, 210, 35);
        infoPanel.add(txtNgayKT);
        txtNgayKT.setBounds(260, 160, 210, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày KT");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(261, 140, 150, 20);

        taNoiDung.setColumns(20);
        taNoiDung.setRows(5);
        jScrollPane1.setViewportView(taNoiDung);

        infoPanel.add(jScrollPane1);
        jScrollPane1.setBounds(30, 220, 260, 150);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        detailTablePanel.setBackground(new Color(255, 255, 255));
        detailTablePanel.setLayout(null);

        btnReset1.setText("jButton3");
        detailTablePanel.add(btnReset1);
        btnReset1.setBounds(250, 20, 40, 35);

        add(detailTablePanel);
        detailTablePanel.setBounds(670, 400, 320, 410);
    }

    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel ctKMPanel = new JPanel();
    private final JLabel lbKhuyenMaiUnit = new JLabel();
    private final JLabel lbCTKMTitle = new JLabel();
    private final JTextField txtMaTK1 = new JTextField();
    private final JLabel lbMaKM = new JLabel();
    private final JTextField txtSanPham = new JTextField();
    private final JLabel lbSanPham = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JLabel lbMaSP = new JLabel();
    private final JTextField txtKhuyenMai = new JTextField();
    private final JLabel lbKhuyenMai = new JLabel();
    private final JButton btnSelectSP = new JButton();
    private final JButton btnThemCT = new JButton();
    private final JButton btnSuaCT = new JButton();
    private final JButton btnXoaCT = new JButton();
    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMakM = new JLabel();
    private final JLabel lbTieuDe = new JLabel();
    private final JTextField txtMaKM = new JTextField();
    private final JTextField txtTieuDe = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JLabel lbNoiDung = new JLabel();
    private final JLabel lbNgayBD = new JLabel();
    private final JTextField txtNgayBD = new JTextField();
    private final JTextField txtNgayKT = new JTextField();
    private final JLabel lbNgayLap = new JLabel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JTextArea taNoiDung = new JTextArea();
    private final JPanel detailTablePanel = new JPanel();
    private final JButton btnReset1 = new JButton();
}
