package GUI.Form;

import GUI.components.TableColumn;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormNCC extends JPanel {
    public FormNCC() {
        initComponents();
    }
    
    private void initComponents() {
        JPanel infoPanel = new JPanel();
        JLabel lbDetailTitle = new JLabel();
        JLabel lbMaNCC = new JLabel();
        JLabel lbTenNCC = new JLabel();
        JLabel lbSDT = new JLabel();
        JLabel lbSTK = new JLabel();
        JLabel lbDiaChi = new JLabel();
        JTextField txtMaNCC = new JTextField();
        JTextField txtSDT = new JTextField();
        JTextField txtSTK = new JTextField();
        JTextField txtDiaChi = new JTextField();
        JButton btnThem = new JButton();
        JButton btnSua = new JButton();
        JButton btnXoa = new JButton();
        JTextField txtTenNCC = new JTextField();
        JPanel tablePanel = new JPanel();
        JLabel lbTableTitle = new JLabel();
        JButton btnTimKiem = new JButton();
        JButton btnReset = new JButton();
        JPanel taskPanel = new JPanel();
        JLabel lbCoopTitle = new JLabel();
        JLabel lbCountSPIn = new JLabel();
        JPanel progressSPIn = new JPanel();
        JLabel progressSPInText = new JLabel();
        JPanel progressSPInValue = new JPanel();
        JLabel lbCountPN = new JLabel();
        JPanel progressPN = new JPanel();
        JLabel progressPNText = new JLabel();
        JPanel progressPNValue = new JPanel();
        JLabel lbCountExpenses = new JLabel();
        JPanel progressExpenses = new JPanel();
        JLabel progressExpensesText = new JLabel();
        JPanel progressExpensesValue = new JPanel();
        JPanel progressSold = new JPanel();
        JLabel progressSoldText = new JLabel();
        JPanel progressSoldValue = new JPanel();
        JLabel lbCountSold = new JLabel();

        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin nhà cung cấp");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(121, 20, 290, 40);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Mã");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(31, 80, 70, 20);

        lbTenNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenNCC.setText("Tên");
        infoPanel.add(lbTenNCC);
        lbTenNCC.setBounds(171, 80, 70, 20);

        lbSDT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSDT.setText("Số Đt");
        infoPanel.add(lbSDT);
        lbSDT.setBounds(261, 140, 70, 20);

        lbSTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSTK.setText("Số tài khoản");
        infoPanel.add(lbSTK);
        lbSTK.setBounds(31, 140, 90, 20);

        lbDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDiaChi.setText("Địa chỉ");
        infoPanel.add(lbDiaChi);
        lbDiaChi.setBounds(31, 200, 100, 20);

        txtMaNCC.setEditable(false);
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(30, 100, 120, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(260, 160, 210, 35);
        infoPanel.add(txtSTK);
        txtSTK.setBounds(30, 160, 210, 35);
        infoPanel.add(txtDiaChi);
        txtDiaChi.setBounds(30, 220, 440, 35);
        infoPanel.add(txtTenNCC);
        txtTenNCC.setBounds(170, 100, 300, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(30, 325, 210, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 280, 440, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(260, 325, 210, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        tablePanel.setBackground(Color.white);
        tablePanel.setLayout(null);

        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle.setForeground(new Color(37, 57, 111));
        lbTableTitle.setText("Danh sách");
        tablePanel.add(lbTableTitle);
        lbTableTitle.setBounds(22, 10, 240, 40);

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

        taskPanel.setBackground(new Color(255, 255, 255));
        taskPanel.setLayout(null);

        lbCoopTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCoopTitle.setForeground(new Color(37, 57, 111));
        lbCoopTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbCoopTitle.setText("Hợp tác");
        taskPanel.add(lbCoopTitle);
        lbCoopTitle.setBounds(120, 20, 240, 40);

        lbCountSPIn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountSPIn.setText("Nhập SP");
        taskPanel.add(lbCountSPIn);
        lbCountSPIn.setBounds(10, 100, 70, 30);

        progressSPIn.setLayout(null);

        progressSPInText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressSPInText.setForeground(new Color(47, 168, 79));
        progressSPInText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSPInText.setText("120 sản phẩm (80%)");
        progressSPIn.add(progressSPInText);
        progressSPInText.setBounds(0, 0, 370, 30);

        progressSPInValue.setBackground(new Color(153, 255, 153));
        progressSPInValue.setLayout(null);

        progressSPIn.add(progressSPInValue);
        progressSPInValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressSPIn);
        progressSPIn.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Số lần");
        taskPanel.add(lbCountPN);
        lbCountPN.setBounds(10, 160, 70, 30);

        progressPN.setLayout(null);

        progressPNText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPNText.setForeground(new Color(243, 170, 24));
        progressPNText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPNText.setText("17 lần nhập (40%)");
        progressPN.add(progressPNText);
        progressPNText.setBounds(0, 0, 370, 30);

        progressPNValue.setBackground(new Color(255, 231, 153));
        progressPNValue.setLayout(null);
        progressPN.add(progressPNValue);
        progressPNValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPN);
        progressPN.setBounds(90, 160, 370, 30);

        lbCountExpenses.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountExpenses.setText("Tổng Chi");
        taskPanel.add(lbCountExpenses);
        lbCountExpenses.setBounds(10, 220, 70, 30);

        progressExpenses.setLayout(null);

        progressExpensesText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressExpensesText.setForeground(new Color(47, 168, 79));
        progressExpensesText.setHorizontalAlignment(SwingConstants.CENTER);
        progressExpensesText.setText("134.522.000 đ (65%)");
        progressExpenses.add(progressExpensesText);
        progressExpensesText.setBounds(0, 0, 370, 30);

        progressExpensesValue.setBackground(new Color(153, 255, 153));
        progressExpensesValue.setLayout(null);

        progressExpenses.add(progressExpensesValue);
        progressExpensesValue.setBounds(0, 0, 222, 30);
        taskPanel.add(progressExpenses);
        progressExpenses.setBounds(90, 220, 370, 30);

        progressSold.setLayout(null);

        progressSoldText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressSoldText.setForeground(new Color(234, 61, 47));
        progressSoldText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSoldText.setText("27 SP đã bán (20%)");
        progressSold.add(progressSoldText);
        progressSoldText.setBounds(0, 0, 370, 30);

        progressSoldValue.setBackground(new Color(255, 153, 153));
        progressSoldValue.setLayout(null);
        progressSold.add(progressSoldValue);
        progressSoldValue.setBounds(0, 0, 50, 30);

        taskPanel.add(progressSold);
        progressSold.setBounds(90, 280, 370, 30);

        lbCountSold.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountSold.setText("Tiêu thụ");
        taskPanel.add(lbCountSold);
        lbCountSold.setBounds(10, 280, 70, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }
}
