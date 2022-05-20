package GUI.Form;

import java.awt.*;
import javax.swing.*;

public class FormPhanQuyen extends JPanel {
    public FormPhanQuyen() {
        initComponents();
    }
    
    private void initComponents() {
        JComboBox<String> cbRole = new JComboBox<>();
        JPanel functionPanel = new JPanel();
        JLabel lbSP = new JLabel();
        JLabel lbPN = new JLabel();
        JLabel lbNCC = new JLabel();
        JLabel lbKH = new JLabel();
        JLabel lbKM = new JLabel();
        JLabel lbTK = new JLabel();
        JLabel lbExcel = new JLabel();
        JLabel lbNV = new JLabel();
        JLabel lbHD = new JLabel();
        JCheckBox btnXoaHD = new JCheckBox();
        JCheckBox btnThemHD = new JCheckBox();
        JCheckBox btnSuaHD = new JCheckBox();
        JCheckBox btnThemPN = new JCheckBox();
        JCheckBox btnSuaPN = new JCheckBox();
        JCheckBox btnXoaPN = new JCheckBox();
        JCheckBox btnThemSP = new JCheckBox();
        JCheckBox btnSuaSP = new JCheckBox();
        JCheckBox btnXoaSP = new JCheckBox();
        JCheckBox btnThemNCC = new JCheckBox();
        JCheckBox btnSuaNCC = new JCheckBox();
        JCheckBox btnXoaNCC = new JCheckBox();
        JCheckBox btnThemKH = new JCheckBox();
        JCheckBox btnSuaKH = new JCheckBox();
        JCheckBox btnXoaKH = new JCheckBox();
        JCheckBox btnThemKM = new JCheckBox();
        JCheckBox btnSuaKM = new JCheckBox();
        JCheckBox btnXoaKM = new JCheckBox();
        JCheckBox btnThemTK = new JCheckBox();
        JCheckBox btnSuaTK = new JCheckBox();
        JCheckBox btnXoaTK = new JCheckBox();
        JCheckBox btnThemExcel = new JCheckBox();
        JCheckBox btnSuaExcel = new JCheckBox();
        JCheckBox btnXoaExcel = new JCheckBox();
        JCheckBox btnThemNV = new JCheckBox();
        JCheckBox btnSuaNV = new JCheckBox();
        JCheckBox btnXoaNV = new JCheckBox();
        JPanel decorateFunctionPanel1 = new JPanel();
        JPanel decorateFunctionPanel2 = new JPanel();
        JPanel decorateFunctionPanel3 = new JPanel();
        JPanel decorateFunctionPanel4 = new JPanel();
        JPanel decorateFunctionPanel5 = new JPanel();
        JLabel lbSelectFunction = new JLabel();
        JButton btnReset = new JButton();
        JPanel detailPanel = new JPanel();
        JButton btnThem = new JButton();
        JButton btnXoa = new JButton();
        JButton btnSua = new JButton();
        JTextField txtTenPQ = new JTextField();
        JLabel lbTenPQ = new JLabel();
        JLabel lbMaPQ = new JLabel();
        JTextField txtMaPQ = new JTextField();
        JLabel lbStatus = new JLabel();
        JLabel lbCurrentStatus = new JLabel();
        JLabel lbSelectRole = new JLabel();
        JLabel lbCountNV = new JLabel();
        JButton btnRenew = new JButton();

        setLayout(null);

        cbRole.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cbRole);
        cbRole.setBounds(30, 50, 350, 40);

        functionPanel.setBackground(new Color(255, 255, 255));
        functionPanel.setLayout(null);

        lbSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSP.setText("Quản lý Sản phẩm");
        functionPanel.add(lbSP);
        lbSP.setBounds(90, 130, 160, 24);

        lbPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPN.setText("Quản lý Phiếu nhập");
        functionPanel.add(lbPN);
        lbPN.setBounds(90, 90, 160, 24);

        lbNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNCC.setText("Quản lý Nguồn cung");
        functionPanel.add(lbNCC);
        lbNCC.setBounds(90, 170, 160, 24);

        lbKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKH.setText("Quản lý Khách hàng");
        functionPanel.add(lbKH);
        lbKH.setBounds(90, 210, 160, 24);

        lbKM.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKM.setText("Quản lý Khuyến mãi");
        functionPanel.add(lbKM);
        lbKM.setBounds(90, 250, 160, 24);

        lbTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTK.setText("Quản lý Thống kê");
        functionPanel.add(lbTK);
        lbTK.setBounds(90, 290, 160, 24);

        lbExcel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbExcel.setText("Quản lý Tập tin");
        functionPanel.add(lbExcel);
        lbExcel.setBounds(90, 330, 160, 24);

        lbNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNV.setText("Quản lý Nhân sự");
        functionPanel.add(lbNV);
        lbNV.setBounds(90, 370, 160, 24);

        lbHD.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHD.setText("Quản lý Hóa đơn");
        functionPanel.add(lbHD);
        lbHD.setBounds(90, 50, 160, 20);

        btnXoaHD.setForeground(new Color(234, 61, 47));
        btnXoaHD.setText("Xóa");
        functionPanel.add(btnXoaHD);
        btnXoaHD.setBounds(510, 50, 60, 20);

        btnThemHD.setForeground(new Color(47, 168, 79));
        btnThemHD.setText("Thêm");
        functionPanel.add(btnThemHD);
        btnThemHD.setBounds(350, 50, 60, 20);

        btnSuaHD.setForeground(new Color(243, 170, 24));
        btnSuaHD.setText("Sửa");
        functionPanel.add(btnSuaHD);
        btnSuaHD.setBounds(430, 50, 60, 20);

        btnThemPN.setForeground(new Color(47, 168, 79));
        btnThemPN.setText("Thêm");
        functionPanel.add(btnThemPN);
        btnThemPN.setBounds(350, 90, 60, 20);

        btnSuaPN.setForeground(new Color(243, 170, 24));
        btnSuaPN.setText("Sửa");
        functionPanel.add(btnSuaPN);
        btnSuaPN.setBounds(430, 90, 60, 20);

        btnXoaPN.setForeground(new Color(234, 61, 47));
        btnXoaPN.setText("Xóa");
        functionPanel.add(btnXoaPN);
        btnXoaPN.setBounds(510, 90, 60, 20);

        btnThemSP.setForeground(new Color(47, 168, 79));
        btnThemSP.setText("Thêm");
        functionPanel.add(btnThemSP);
        btnThemSP.setBounds(350, 130, 60, 20);

        btnSuaSP.setForeground(new Color(243, 170, 24));
        btnSuaSP.setText("Sửa");
        functionPanel.add(btnSuaSP);
        btnSuaSP.setBounds(430, 130, 60, 20);

        btnXoaSP.setForeground(new Color(234, 61, 47));
        btnXoaSP.setText("Xóa");
        functionPanel.add(btnXoaSP);
        btnXoaSP.setBounds(510, 130, 60, 20);

        btnThemNCC.setForeground(new Color(47, 168, 79));
        btnThemNCC.setText("Thêm");
        functionPanel.add(btnThemNCC);
        btnThemNCC.setBounds(350, 170, 60, 20);

        btnSuaNCC.setForeground(new Color(243, 170, 24));
        btnSuaNCC.setText("Sửa");
        functionPanel.add(btnSuaNCC);
        btnSuaNCC.setBounds(430, 170, 60, 20);

        btnXoaNCC.setForeground(new Color(234, 61, 47));
        btnXoaNCC.setText("Xóa");
        functionPanel.add(btnXoaNCC);
        btnXoaNCC.setBounds(510, 170, 60, 20);

        btnThemKH.setForeground(new Color(47, 168, 79));
        btnThemKH.setText("Thêm");
        functionPanel.add(btnThemKH);
        btnThemKH.setBounds(350, 210, 60, 20);

        btnSuaKH.setForeground(new Color(243, 170, 24));
        btnSuaKH.setText("Sửa");
        functionPanel.add(btnSuaKH);
        btnSuaKH.setBounds(430, 210, 60, 20);

        btnXoaKH.setForeground(new Color(234, 61, 47));
        btnXoaKH.setText("Xóa");
        functionPanel.add(btnXoaKH);
        btnXoaKH.setBounds(510, 210, 60, 20);

        btnThemKM.setForeground(new Color(47, 168, 79));
        btnThemKM.setText("Thêm");
        functionPanel.add(btnThemKM);
        btnThemKM.setBounds(350, 250, 60, 20);

        btnSuaKM.setForeground(new Color(243, 170, 24));
        btnSuaKM.setText("Sửa");
        functionPanel.add(btnSuaKM);
        btnSuaKM.setBounds(430, 250, 60, 20);

        btnXoaKM.setForeground(new Color(234, 61, 47));
        btnXoaKM.setText("Xóa");
        functionPanel.add(btnXoaKM);
        btnXoaKM.setBounds(510, 250, 60, 20);

        btnThemTK.setForeground(new Color(47, 168, 79));
        btnThemTK.setText("Thêm");
        functionPanel.add(btnThemTK);
        btnThemTK.setBounds(350, 290, 60, 20);

        btnSuaTK.setForeground(new Color(243, 170, 24));
        btnSuaTK.setText("Sửa");
        functionPanel.add(btnSuaTK);
        btnSuaTK.setBounds(430, 290, 60, 20);

        btnXoaTK.setForeground(new Color(234, 61, 47));
        btnXoaTK.setText("Xóa");
        functionPanel.add(btnXoaTK);
        btnXoaTK.setBounds(510, 290, 60, 20);

        btnThemExcel.setForeground(new Color(47, 168, 79));
        btnThemExcel.setText("Thêm");
        functionPanel.add(btnThemExcel);
        btnThemExcel.setBounds(350, 330, 60, 20);

        btnSuaExcel.setForeground(new Color(243, 170, 24));
        btnSuaExcel.setText("Sửa");
        functionPanel.add(btnSuaExcel);
        btnSuaExcel.setBounds(430, 330, 60, 20);

        btnXoaExcel.setForeground(new Color(234, 61, 47));
        btnXoaExcel.setText("Xóa");
        functionPanel.add(btnXoaExcel);
        btnXoaExcel.setBounds(510, 330, 60, 20);

        btnThemNV.setForeground(new Color(47, 168, 79));
        btnThemNV.setText("Thêm");
        functionPanel.add(btnThemNV);
        btnThemNV.setBounds(350, 370, 60, 20);

        btnSuaNV.setForeground(new Color(243, 170, 24));
        btnSuaNV.setText("Sửa");
        functionPanel.add(btnSuaNV);
        btnSuaNV.setBounds(430, 370, 60, 20);

        btnXoaNV.setForeground(new Color(234, 61, 47));
        btnXoaNV.setText("Xóa");
        functionPanel.add(btnXoaNV);
        btnXoaNV.setBounds(510, 370, 60, 20);
        functionPanel.add(decorateFunctionPanel1);
        decorateFunctionPanel1.setBounds(10, 360, 560, 40);
        functionPanel.add(decorateFunctionPanel2);
        decorateFunctionPanel2.setBounds(10, 40, 560, 40);
        functionPanel.add(decorateFunctionPanel3);
        decorateFunctionPanel3.setBounds(10, 120, 560, 40);
        functionPanel.add(decorateFunctionPanel4);
        decorateFunctionPanel4.setBounds(10, 200, 560, 40);
        functionPanel.add(decorateFunctionPanel5);
        decorateFunctionPanel5.setBounds(10, 280, 560, 40);

        lbSelectFunction.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSelectFunction.setText("Chọn chức năng:");
        functionPanel.add(lbSelectFunction);
        lbSelectFunction.setBounds(10, 10, 210, 20);

        btnReset.setBackground(new Color(229, 239, 255));
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReset.setForeground(new Color(54, 123, 245));
        btnReset.setText("Tải lại");
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        functionPanel.add(btnReset);
        btnReset.setBounds(490, 10, 72, 22);

        add(functionPanel);
        functionPanel.setBounds(400, 10, 580, 420);

        detailPanel.setBackground(new Color(255, 255, 255));
        detailPanel.setLayout(null);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm Quyền");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        detailPanel.add(btnThem);
        btnThem.setBounds(10, 360, 170, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa Quyền");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        detailPanel.add(btnXoa);
        btnXoa.setBounds(190, 360, 170, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa Quyền");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        detailPanel.add(btnSua);
        btnSua.setBounds(10, 310, 350, 40);
        detailPanel.add(txtTenPQ);
        txtTenPQ.setBounds(100, 120, 260, 40);

        lbTenPQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbTenPQ.setText("Tên quyền:");
        detailPanel.add(lbTenPQ);
        lbTenPQ.setBounds(100, 100, 240, 20);

        lbMaPQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbMaPQ.setText("Mã:");
        detailPanel.add(lbMaPQ);
        lbMaPQ.setBounds(10, 100, 60, 20);

        txtMaPQ.setEditable(false);
        txtMaPQ.setAutoscrolls(false);
        txtMaPQ.setFocusable(false);
        detailPanel.add(txtMaPQ);
        txtMaPQ.setBounds(10, 120, 80, 40);

        lbStatus.setText("Tình trạng:");
        detailPanel.add(lbStatus);
        lbStatus.setBounds(10, 210, 70, 18);

        lbCurrentStatus.setForeground(new Color(234, 61, 47));
        lbCurrentStatus.setText("Chưa lưu");
        detailPanel.add(lbCurrentStatus);
        lbCurrentStatus.setBounds(80, 210, 150, 18);

        lbSelectRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSelectRole.setText("Chọn quyền:");
        detailPanel.add(lbSelectRole);
        lbSelectRole.setBounds(10, 10, 310, 20);

        lbCountNV.setText("Số lượng nhân viên: 000");
        detailPanel.add(lbCountNV);
        lbCountNV.setBounds(10, 180, 240, 18);

        btnRenew.setBackground(new Color(229, 239, 255));
        btnRenew.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRenew.setForeground(new Color(54, 123, 245));
        btnRenew.setText("Làm mới");
        btnRenew.setBorderPainted(false);
        btnRenew.setFocusPainted(false);
        detailPanel.add(btnRenew);
        btnRenew.setBounds(270, 180, 90, 22);

        add(detailPanel);
        detailPanel.setBounds(20, 10, 370, 420);
    }
}
