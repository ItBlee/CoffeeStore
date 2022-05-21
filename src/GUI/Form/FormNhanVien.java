package GUI.Form;

import GUI.components.TableColumn;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormNhanVien extends JPanel {
    public FormNhanVien() {
        initComponents();
    }

    private void initComponents() {
        JPanel infoPanel = new JPanel();
        JLabel lbDetailTitle = new JLabel();
        JTextField txtNgaySinh = new JTextField();
        JLabel lbLuong = new JLabel();
        JLabel lbMaNV = new JLabel();
        JLabel lbHo = new JLabel();
        JLabel lbTen = new JLabel();
        JLabel lbSDT = new JLabel();
        JLabel lbNgaySinh = new JLabel();
        JLabel lbGioiTinh = new JLabel();
        JLabel lbMaTK = new JLabel();
        JLabel lbEmail = new JLabel();
        JTextField txtMaNV = new JTextField();
        JTextField txtMaTK = new JTextField();
        JLabel lbLuongUnit = new JLabel();
        JTextField txtLuong = new JTextField();
        JTextField txtSDT = new JTextField();
        JTextField txtHo = new JTextField();
        JTextField txtTen = new JTextField();
        JTextField txtEmail = new JTextField();
        JComboBox<String> cbGioiTinh = new JComboBox<>();
        JButton btnSelectNgaySinh = new JButton();
        JButton btnThem = new JButton();
        JButton btnSua = new JButton();
        JButton btnSelectMaTK = new JButton();
        JButton btnXoa = new JButton();
        JPanel tablePanel = new JPanel();
        JLabel lbTableTitle = new JLabel();
        JButton btnTimKiem = new JButton();
        JButton btnReset = new JButton();
        JPanel taskPanel = new JPanel();
        JLabel lbTaskTitle = new JLabel();
        JLabel lbCountHD = new JLabel();
        JPanel progressHD = new JPanel();
        JLabel progressHDText = new JLabel();
        JPanel progressHDValue = new JPanel();
        JLabel lbCountPN = new JLabel();
        JPanel progressPN = new JPanel();
        JLabel progressPNText = new JLabel();
        JPanel progressPNValue = new JPanel();
        JLabel lbCountKH = new JLabel();
        JPanel progressKH = new JPanel();
        JLabel progressKHText = new JLabel();
        JPanel progressKHValue = new JPanel();
        JPanel progressLS = new JPanel();
        JLabel progressLSText = new JLabel();
        JPanel progressLSValue = new JPanel();
        JLabel lbCountLichSu = new JLabel();

        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin nhân viên");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(131, 20, 240, 40);
        infoPanel.add(txtNgaySinh);
        txtNgaySinh.setBounds(30, 220, 130, 35);

        lbLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbLuong.setText("Lương");
        infoPanel.add(lbLuong);
        lbLuong.setBounds(231, 260, 100, 20);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Mã");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(31, 80, 70, 20);

        lbHo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHo.setText("Họ");
        infoPanel.add(lbHo);
        lbHo.setBounds(231, 80, 70, 20);

        lbTen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTen.setText("Tên");
        infoPanel.add(lbTen);
        lbTen.setBounds(401, 80, 70, 20);

        lbSDT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSDT.setText("Số Đt");
        infoPanel.add(lbSDT);
        lbSDT.setBounds(231, 140, 70, 20);

        lbNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgaySinh.setText("Ngày sinh");
        infoPanel.add(lbNgaySinh);
        lbNgaySinh.setBounds(31, 200, 70, 20);

        lbGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbGioiTinh.setText("Giới tính");
        infoPanel.add(lbGioiTinh);
        lbGioiTinh.setBounds(31, 260, 70, 20);

        lbMaTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaTK.setText("Tài khoản");
        infoPanel.add(lbMaTK);
        lbMaTK.setBounds(31, 140, 90, 20);

        lbEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbEmail.setText("Email");
        infoPanel.add(lbEmail);
        lbEmail.setBounds(231, 200, 100, 20);

        txtMaNV.setEnabled(false);
        txtMaNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(31, 100, 130, 30);

        txtMaTK.setEnabled(false);
        txtMaTK.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaTK);
        txtMaTK.setBounds(30, 160, 130, 35);

        lbLuongUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbLuongUnit.setText("đồng");
        infoPanel.add(lbLuongUnit);
        lbLuongUnit.setBounds(421, 280, 50, 30);
        infoPanel.add(txtLuong);
        txtLuong.setBounds(230, 280, 240, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(230, 160, 240, 35);
        infoPanel.add(txtHo);
        txtHo.setBounds(230, 100, 160, 35);
        infoPanel.add(txtTen);
        txtTen.setBounds(400, 100, 70, 35);
        infoPanel.add(txtEmail);
        txtEmail.setBounds(230, 220, 240, 35);

        cbGioiTinh.setModel(new DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        infoPanel.add(cbGioiTinh);
        cbGioiTinh.setBounds(30, 280, 130, 30);

        btnSelectNgaySinh.setText("jButton1");
        infoPanel.add(btnSelectNgaySinh);
        btnSelectNgaySinh.setBounds(170, 220, 35, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(230, 330, 160, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 330, 130, 35);

        btnSelectMaTK.setText("jButton1");
        infoPanel.add(btnSelectMaTK);
        btnSelectMaTK.setBounds(170, 160, 35, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(400, 330, 70, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

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

        taskPanel.setBackground(new Color(255, 255, 255));
        taskPanel.setLayout(null);

        lbTaskTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTaskTitle.setForeground(new Color(37, 57, 111));
        lbTaskTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTaskTitle.setText("Hoạt động");
        taskPanel.add(lbTaskTitle);
        lbTaskTitle.setBounds(120, 20, 240, 40);

        lbCountHD.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountHD.setText("Lập đơn");
        taskPanel.add(lbCountHD);
        lbCountHD.setBounds(10, 100, 70, 30);

        progressHD.setLayout(null);

        progressHDText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressHDText.setForeground(new Color(47, 168, 79));
        progressHDText.setHorizontalAlignment(SwingConstants.CENTER);
        progressHDText.setText("15 hóa đơn (80%)");
        progressHD.add(progressHDText);
        progressHDText.setBounds(0, 0, 370, 30);

        progressHDValue.setBackground(new Color(153, 255, 153));
        progressHDValue.setLayout(null);
        progressHD.add(progressHDValue);
        progressHDValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressHD);
        progressHD.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Lập phiếu");
        taskPanel.add(lbCountPN);
        lbCountPN.setBounds(10, 160, 70, 30);

        progressPN.setLayout(null);

        progressPNText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPNText.setForeground(new Color(243, 170, 24));
        progressPNText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPNText.setText("8 phiếu nhập (40%)");
        progressPN.add(progressPNText);
        progressPNText.setBounds(0, 0, 370, 30);

        progressPNValue.setBackground(new Color(255, 231, 153));
        progressPNValue.setLayout(null);
        progressPN.add(progressPNValue);
        progressPNValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPN);
        progressPN.setBounds(90, 160, 370, 30);

        lbCountKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountKH.setText("Hỗ trợ");
        taskPanel.add(lbCountKH);
        lbCountKH.setBounds(10, 220, 70, 30);

        progressKH.setLayout(null);

        progressKHText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressKHText.setForeground(new Color(47, 168, 79));
        progressKHText.setHorizontalAlignment(SwingConstants.CENTER);
        progressKHText.setText("37 khách hàng (65%)");
        progressKH.add(progressKHText);
        progressKHText.setBounds(0, 0, 370, 30);

        progressKHValue.setBackground(new Color(153, 255, 153));
        progressKHValue.setLayout(null);
        progressKH.add(progressKHValue);
        progressKHValue.setBounds(0, 0, 230, 30);

        taskPanel.add(progressKH);
        progressKH.setBounds(90, 220, 370, 30);

        progressLS.setLayout(null);

        progressLSText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressLSText.setForeground(new Color(234, 61, 47));
        progressLSText.setHorizontalAlignment(SwingConstants.CENTER);
        progressLSText.setText("2 thao tác (20%)");
        progressLS.add(progressLSText);
        progressLSText.setBounds(0, 0, 370, 30);

        progressLSValue.setBackground(new Color(255, 153, 153));
        progressLSValue.setLayout(null);
        progressLS.add(progressLSValue);
        progressLSValue.setBounds(0, 0, 50, 30);

        taskPanel.add(progressLS);
        progressLS.setBounds(90, 280, 370, 30);

        lbCountLichSu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountLichSu.setText("Thao tác");
        taskPanel.add(lbCountLichSu);
        lbCountLichSu.setBounds(10, 280, 70, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }
}
