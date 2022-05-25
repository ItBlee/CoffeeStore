package GUI.Form;

import GUI.Form.Abstract.JTablePanel;

import javax.swing.*;
import java.awt.*;

public class FormPhieuNhap extends JTablePanel {
    public FormPhieuNhap() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);

        tablePanel2.setBackground(new Color(255, 255, 255));
        tablePanel2.setLayout(null);

        lbTableTitle2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle2.setForeground(new Color(37, 57, 111));
        lbTableTitle2.setText("Danh sách");
        tablePanel2.add(lbTableTitle2);
        lbTableTitle2.setBounds(30, 10, 240, 40);

        btnTimKiem2.setBackground(new Color(229, 239, 255));
        btnTimKiem2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTimKiem2.setForeground(new Color(54, 123, 245));
        btnTimKiem2.setText("Tìm kiếm");
        btnTimKiem2.setBorderPainted(false);
        btnTimKiem2.setFocusPainted(false);
        tablePanel2.add(btnTimKiem2);
        btnTimKiem2.setBounds(410, 20, 170, 35);

        btnReset.setText("jButton3");
        tablePanel2.add(btnReset);
        btnReset.setBounds(590, 20, 40, 35);

        add(tablePanel2);
        tablePanel2.setBounds(10, 400, 650, 410);

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        detailPanel.setBackground(new Color(255, 255, 255));
        detailPanel.setLayout(null);

        lbThanhTienCTUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbThanhTienCTUnit.setText("đồng");
        detailPanel.add(lbThanhTienCTUnit);
        lbThanhTienCTUnit.setBounds(390, 100, 50, 40);

        lbPasswordTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbPasswordTitle.setForeground(new Color(37, 57, 111));
        lbPasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbPasswordTitle.setText("Chi tiết phiếu nhập");
        detailPanel.add(lbPasswordTitle);
        lbPasswordTitle.setBounds(110, 20, 270, 40);

        txtMaPNCT.setBackground(new Color(245, 245, 245));
        txtMaPNCT.setEnabled(false);
        detailPanel.add(txtMaPNCT);
        txtMaPNCT.setBounds(30, 100, 70, 35);

        lbMaPNCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaPNCT.setText("Mã PN");
        detailPanel.add(lbMaPNCT);
        lbMaPNCT.setBounds(30, 80, 70, 20);

        txtSanPham.setBackground(new Color(245, 245, 245));
        txtSanPham.setEnabled(false);
        detailPanel.add(txtSanPham);
        txtSanPham.setBounds(160, 160, 280, 35);

        lbSanPham.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSanPham.setText("Sản phẩm");
        detailPanel.add(lbSanPham);
        lbSanPham.setBounds(160, 140, 120, 20);

        txtSoLuong.setBackground(new Color(255, 255, 255));
        detailPanel.add(txtSoLuong);
        txtSoLuong.setBounds(160, 220, 80, 35);

        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSoLuong.setText("Số lượng");
        detailPanel.add(lbSoLuong);
        lbSoLuong.setBounds(160, 200, 80, 20);

        txtMaSP.setBackground(new Color(245, 245, 245));
        txtMaSP.setEnabled(false);
        detailPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 160, 70, 35);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã SP");
        detailPanel.add(lbMaSP);
        lbMaSP.setBounds(30, 140, 70, 20);

        txtThanhTienCT.setBackground(new Color(245, 245, 245));
        txtThanhTienCT.setEnabled(false);
        detailPanel.add(txtThanhTienCT);
        txtThanhTienCT.setBounds(160, 100, 280, 35);

        lbThanhTienCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbThanhTienCT.setText("Thành tiền");
        detailPanel.add(lbThanhTienCT);
        lbThanhTienCT.setBounds(160, 80, 130, 20);

        lbDonGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDonGia.setText("Đơn giá");
        detailPanel.add(lbDonGia);
        lbDonGia.setBounds(260, 200, 100, 20);

        txtDonGia.setBackground(new Color(245, 245, 245));
        txtDonGia.setEnabled(false);
        detailPanel.add(txtDonGia);
        txtDonGia.setBounds(260, 220, 180, 35);

        btnSelectSP.setText("jButton1");
        detailPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 40, 35);

        btnThemCT.setBackground(new Color(220, 247, 227));
        btnThemCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemCT.setForeground(new Color(47, 168, 79));
        btnThemCT.setText("Thêm");
        btnThemCT.setBorderPainted(false);
        btnThemCT.setFocusPainted(false);
        detailPanel.add(btnThemCT);
        btnThemCT.setBounds(160, 280, 280, 40);

        btnSuaCT.setBackground(new Color(252, 243, 215));
        btnSuaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaCT.setForeground(new Color(243, 170, 24));
        btnSuaCT.setText("Sửa");
        btnSuaCT.setBorderPainted(false);
        btnSuaCT.setFocusPainted(false);
        detailPanel.add(btnSuaCT);
        btnSuaCT.setBounds(260, 330, 180, 40);

        btnXoaCT.setBackground(new Color(254, 228, 226));
        btnXoaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaCT.setForeground(new Color(234, 61, 47));
        btnXoaCT.setText("Xóa");
        btnXoaCT.setBorderPainted(false);
        btnXoaCT.setFocusPainted(false);
        detailPanel.add(btnXoaCT);
        btnXoaCT.setBounds(160, 330, 80, 40);

        pluginPanel.add(detailPanel);
        detailPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbTongTienUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbTongTienUnit.setText("đồng");
        infoPanel.add(lbTongTienUnit);
        lbTongTienUnit.setBounds(380, 100, 50, 40);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin phiếu nhập");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaPN.setText("Mã");
        infoPanel.add(lbMaPN);
        lbMaPN.setBounds(80, 80, 70, 20);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Nhà cung cấp");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(80, 140, 120, 20);

        txtMaHD.setBackground(new Color(245, 245, 245));
        txtMaHD.setEnabled(false);
        infoPanel.add(txtMaHD);
        txtMaHD.setBounds(80, 100, 70, 35);

        txtMaNCC.setBackground(new Color(245, 245, 245));
        txtMaNCC.setEnabled(false);
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(80, 160, 290, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(80, 280, 350, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(80, 330, 170, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(270, 330, 160, 40);

        txtTotal.setBackground(new Color(245, 245, 245));
        txtTotal.setEnabled(false);
        infoPanel.add(txtTotal);
        txtTotal.setBounds(170, 100, 260, 35);

        lbTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotal.setText("Tổng tiền");
        infoPanel.add(lbTotal);
        lbTotal.setBounds(170, 80, 130, 20);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(80, 200, 120, 20);

        txtMaNV.setBackground(new Color(245, 245, 245));
        txtMaNV.setEnabled(false);
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(80, 220, 170, 35);

        txtNgayLap.setBackground(new Color(245, 245, 245));
        txtNgayLap.setEnabled(false);
        infoPanel.add(txtNgayLap);
        txtNgayLap.setBounds(270, 220, 160, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày lập");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(270, 200, 150, 20);

        btnSelectNCC.setText("jButton1");
        infoPanel.add(btnSelectNCC);
        btnSelectNCC.setBounds(390, 160, 40, 35);

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

    private final JPanel tablePanel2 = new JPanel();
    private final JLabel lbTableTitle2 = new JLabel();
    private final JButton btnTimKiem2 = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel detailPanel = new JPanel();
    private final JLabel lbThanhTienCTUnit = new JLabel();
    private final JLabel lbPasswordTitle = new JLabel();
    private final JTextField txtMaPNCT = new JTextField();
    private final JLabel lbMaPNCT = new JLabel();
    private final JTextField txtSanPham = new JTextField();
    private final JLabel lbSanPham = new JLabel();
    private final JTextField txtSoLuong = new JTextField();
    private final JLabel lbSoLuong = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JLabel lbMaSP = new JLabel();
    private final JTextField txtThanhTienCT = new JTextField();
    private final JLabel lbThanhTienCT = new JLabel();
    private final JLabel lbDonGia = new JLabel();
    private final JTextField txtDonGia = new JTextField();
    private final JButton btnSelectSP = new JButton();
    private final JButton btnThemCT = new JButton();
    private final JButton btnSuaCT = new JButton();
    private final JButton btnXoaCT = new JButton();
    private final JPanel infoPanel = new JPanel();
    private final JLabel lbTongTienUnit = new JLabel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaPN = new JLabel();
    private final JLabel lbMaNCC = new JLabel();
    private final JTextField txtMaHD = new JTextField();
    private final JTextField txtMaNCC = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JTextField txtTotal = new JTextField();
    private final JLabel lbTotal = new JLabel();
    private final JLabel lbMaNV = new JLabel();
    private final JTextField txtMaNV = new JTextField();
    private final JTextField txtNgayLap = new JTextField();
    private final JLabel lbNgayLap = new JLabel();
    private final JButton btnSelectNCC = new JButton();
    private final JPanel detailTablePanel = new JPanel();
    private final JButton btnReset1 = new JButton();
}
