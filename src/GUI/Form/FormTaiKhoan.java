package GUI.Form;

import GUI.components.TableColumn;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormTaiKhoan extends JPanel {
    public FormTaiKhoan() {
        initComponents();
    }
    
    private void initComponents() {
        JPanel tablePanel = new JPanel();
        JLabel lbTableTitle = new JLabel();
        JButton btnTimKiem = new JButton();
        JButton btnReset = new JButton();
        JPanel ownPanel = new JPanel();
        JLabel lbOwnTitle = new JLabel();
        JPanel infoPanel = new JPanel();
        JLabel lbDetailTitle = new JLabel();
        JLabel lbMaTK = new JLabel();
        JLabel lbPhanQuyen = new JLabel();
        JLabel lbNguoiTao = new JLabel();
        JTextField txtMaTK = new JTextField();
        JTextField txtNguoiTao = new JTextField();
        JButton btnThem = new JButton();
        JButton btnSua = new JButton();
        JButton btnXoa = new JButton();
        JTextField txtTenTenDangNhap = new JTextField();
        JLabel lbTenDangNhap = new JLabel();
        JComboBox<String> cbPhanQuyen = new JComboBox<>();
        JLabel lbMaNV = new JLabel();
        JTextField txtMaNV = new JTextField();
        JTextField txtHoTenNV = new JTextField();
        JLabel lbHoTenNV = new JLabel();

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
        btnTimKiem.setBounds(750, 20, 170, 40);

        btnReset.setText("jButton3");
        tablePanel.add(btnReset);
        btnReset.setBounds(923, 20, 40, 40);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        TableColumn table = new TableColumn();
        table.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã", "Họ tên", "Giới tính", "Tuổi", "Email", "Số điện thoại"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{1, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{2, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{3, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{4, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{5, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{6, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{7, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{8, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{9, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{10, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});
        model.addRow(new Object[]{11, "Trần Long Tuấn Vũ", "Nam", "22", "tranlongtuanvu@gmail.com", "+099 966 666 333"});

        tablePanel.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 940, 350);

        add(tablePanel);
        tablePanel.setBounds(10, 400, 980, 410);

        ownPanel.setBackground(new Color(255, 255, 255));
        ownPanel.setLayout(null);

        lbOwnTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbOwnTitle.setForeground(new Color(37, 57, 111));
        lbOwnTitle.setHorizontalAlignment(SwingConstants.CENTER);
        ownPanel.add(lbOwnTitle);
        lbOwnTitle.setBounds(120, 20, 240, 40);

        add(ownPanel);
        ownPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin tài khoản");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaTK.setText("Mã");
        infoPanel.add(lbMaTK);
        lbMaTK.setBounds(31, 80, 70, 20);

        lbPhanQuyen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPhanQuyen.setText("Phân quyền");
        infoPanel.add(lbPhanQuyen);
        lbPhanQuyen.setBounds(31, 200, 130, 20);

        lbNguoiTao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNguoiTao.setText("Người tạo");
        infoPanel.add(lbNguoiTao);
        lbNguoiTao.setBounds(171, 80, 90, 20);

        txtMaTK.setEnabled(false);
        txtMaTK.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaTK);
        txtMaTK.setBounds(30, 100, 120, 35);

        txtNguoiTao.setEnabled(false);
        txtNguoiTao.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtNguoiTao);
        txtNguoiTao.setBounds(170, 100, 120, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(320, 160, 150, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(320, 100, 150, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(320, 220, 150, 35);
        infoPanel.add(txtTenTenDangNhap);
        txtTenTenDangNhap.setBounds(30, 160, 260, 35);

        lbTenDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenDangNhap.setText("Tên đăng nhập");
        infoPanel.add(lbTenDangNhap);
        lbTenDangNhap.setBounds(31, 140, 130, 20);

        cbPhanQuyen.setModel(new DefaultComboBoxModel<>(new String[] { "Quản trị viên", "Item 2", "Item 3", "Item 4" }));
        infoPanel.add(cbPhanQuyen);
        cbPhanQuyen.setBounds(30, 220, 260, 35);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Mã nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(31, 280, 120, 20);

        txtMaNV.setEnabled(false);
        txtMaNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(30, 300, 120, 35);

        txtHoTenNV.setEnabled(false);
        txtHoTenNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtHoTenNV);
        txtHoTenNV.setBounds(170, 300, 300, 35);

        lbHoTenNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHoTenNV.setText("Họ tên nhân viên");
        infoPanel.add(lbHoTenNV);
        lbHoTenNV.setBounds(171, 280, 150, 20);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);
    }
}
