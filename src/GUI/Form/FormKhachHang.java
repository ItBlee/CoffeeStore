package GUI.Form;

import GUI.Form.Abstract.JTablePanel;
import GUI.components.TableColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormKhachHang extends JTablePanel {
    public FormKhachHang() {
        initComponents();
    }
    
    private void initComponents() {
        JPanel infoPanel = new JPanel();
        JLabel lbDetailTitle = new JLabel();
        JLabel lbMaKH = new JLabel();
        JLabel lbHo = new JLabel();
        JLabel lbTen = new JLabel();
        JLabel lbSDT = new JLabel();
        JLabel lbEmail = new JLabel();
        JTextField txtMaKH = new JTextField();
        JTextField txtSDT = new JTextField();
        JTextField txtHo = new JTextField();
        JTextField txtTen = new JTextField();
        JTextField txtEmail = new JTextField();
        JButton btnThem = new JButton();
        JButton btnSua = new JButton();
        JButton btnXoa = new JButton();
        JLabel lbDiaChi = new JLabel();
        JTextField txtDiaChi = new JTextField();
        JPanel tablePanel = new JPanel();
        JLabel lbTableTitle = new JLabel();
        JButton btnTimKiem = new JButton();
        JButton btnReset = new JButton();
        JPanel taskPanel = new JPanel();
        JLabel lbTaskTitle = new JLabel();
        JLabel lbBought = new JLabel();
        JPanel progressBought = new JPanel();
        JLabel progressBoughtText = new JLabel();
        JPanel progressBoughtValue = new JPanel();
        JLabel lbPay = new JLabel();
        JPanel progressPay = new JPanel();
        JLabel progressPayText = new JLabel();
        JPanel progressPayValue = new JPanel();
        JLabel lbTime = new JLabel();
        JLabel lbFavoriteValue = new JLabel();
        JLabel lbFavorite = new JLabel();
        JLabel lbTimeValue = new JLabel();
        JLabel lbFavoriteCategory = new JLabel();
        JLabel lbFavoriteCategoryValue = new JLabel();

        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin khách hàng");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 260, 40);

        lbMaKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaKH.setText("Mã");
        infoPanel.add(lbMaKH);
        lbMaKH.setBounds(31, 80, 70, 20);

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
        lbSDT.setBounds(31, 140, 70, 20);

        lbEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbEmail.setText("Email");
        infoPanel.add(lbEmail);
        lbEmail.setBounds(231, 140, 100, 20);

        txtMaKH.setEnabled(false);
        txtMaKH.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaKH);
        txtMaKH.setBounds(30, 100, 170, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(30, 160, 170, 35);
        infoPanel.add(txtHo);
        txtHo.setBounds(230, 100, 160, 35);
        infoPanel.add(txtTen);
        txtTen.setBounds(400, 100, 70, 35);
        infoPanel.add(txtEmail);
        txtEmail.setBounds(230, 160, 240, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(30, 320, 170, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 280, 440, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(230, 320, 240, 35);

        lbDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDiaChi.setText("Địa chỉ");
        infoPanel.add(lbDiaChi);
        lbDiaChi.setBounds(31, 200, 100, 20);
        infoPanel.add(txtDiaChi);
        txtDiaChi.setBounds(30, 220, 440, 35);

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

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
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
        lbTaskTitle.setText("Mua hàng");
        taskPanel.add(lbTaskTitle);
        lbTaskTitle.setBounds(120, 20, 240, 40);

        lbBought.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbBought.setText("Đã mua");
        taskPanel.add(lbBought);
        lbBought.setBounds(10, 100, 70, 30);

        progressBought.setLayout(null);

        progressBoughtText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressBoughtText.setForeground(new Color(47, 168, 79));
        progressBoughtText.setHorizontalAlignment(SwingConstants.CENTER);
        progressBoughtText.setText("64 sản phẩm (80%)");
        progressBought.add(progressBoughtText);
        progressBoughtText.setBounds(0, 0, 370, 30);

        progressBoughtValue.setBackground(new Color(153, 255, 153));
        progressBoughtValue.setLayout(null);

        progressBought.add(progressBoughtValue);
        progressBoughtValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressBought);
        progressBought.setBounds(90, 100, 370, 30);

        lbPay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPay.setText("Đã chi");
        taskPanel.add(lbPay);
        lbPay.setBounds(10, 160, 70, 30);

        progressPay.setLayout(null);

        progressPayText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPayText.setForeground(new Color(243, 170, 24));
        progressPayText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPayText.setText("23.000.000 đ (40%)");
        progressPay.add(progressPayText);
        progressPayText.setBounds(0, 0, 370, 30);

        progressPayValue.setBackground(new Color(255, 231, 153));
        progressPayValue.setLayout(null);

        progressPay.add(progressPayValue);
        progressPayValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPay);
        progressPay.setBounds(90, 160, 370, 30);

        lbTime.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTime.setForeground(new Color(37, 57, 111));
        lbTime.setText("Thời gian mua:");
        taskPanel.add(lbTime);
        lbTime.setBounds(10, 220, 140, 30);

        lbFavoriteValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteValue.setForeground(new Color(155, 84, 225));
        lbFavoriteValue.setText("Cafe Trung Nguyên");
        taskPanel.add(lbFavoriteValue);
        lbFavoriteValue.setBounds(190, 280, 180, 30);

        lbFavorite.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavorite.setForeground(new Color(37, 57, 111));
        lbFavorite.setText("Sản phẩm yêu thích: ");
        taskPanel.add(lbFavorite);
        lbFavorite.setBounds(10, 280, 180, 30);

        lbTimeValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTimeValue.setForeground(new Color(155, 84, 225));
        lbTimeValue.setText("10/09/2021 - 23/05/2022");
        taskPanel.add(lbTimeValue);
        lbTimeValue.setBounds(150, 220, 310, 30);

        lbFavoriteCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategory.setForeground(new Color(37, 57, 111));
        lbFavoriteCategory.setText("Danh mục yêu thích: ");
        taskPanel.add(lbFavoriteCategory);
        lbFavoriteCategory.setBounds(10, 320, 180, 30);

        lbFavoriteCategoryValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategoryValue.setForeground(new Color(155, 84, 225));
        lbFavoriteCategoryValue.setText("Cafe đen");
        taskPanel.add(lbFavoriteCategoryValue);
        lbFavoriteCategoryValue.setBounds(190, 320, 180, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }
}
