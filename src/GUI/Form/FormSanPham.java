package GUI.Form;

import GUI.Form.Abstract.JTablePanel;
import GUI.components.TableColumn;
import Utils.General;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;

public class FormSanPham extends JTablePanel {
    public FormSanPham() {
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

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.CURRENT_ROLE.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Tên", "Loại", "Nguồn", "Đơn giá", "Đơn vị", "Số lượng", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Tên", "Loại", "Nguồn", "Đơn giá", "Đơn vị", "Số lượng"
        };
        table.setModel(new DefaultTableModel(
                new Object [][] {},
                columnHeader
        ) {
            final boolean[] canEdit = new boolean [columnHeader.length];

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                //onClickTableRow();
            }
        });
        tablePanel.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 630, 350);

        add(tablePanel);
        tablePanel.setBounds(10, 400, 650, 410);

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        CategoryPanel.setBackground(new Color(255, 255, 255));
        CategoryPanel.setLayout(null);

        lbCategoryTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCategoryTitle.setForeground(new Color(37, 57, 111));
        lbCategoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbCategoryTitle.setText("Loại sản phẩm");
        CategoryPanel.add(lbCategoryTitle);
        lbCategoryTitle.setBounds(110, 20, 270, 40);

        txtMaLoaiSP.setBackground(new Color(245, 245, 245));
        txtMaLoaiSP.setEnabled(false);
        CategoryPanel.add(txtMaLoaiSP);
        txtMaLoaiSP.setBounds(30, 100, 70, 35);

        lbMaLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaLoaiSP.setText("Mã");
        CategoryPanel.add(lbMaLoaiSP);
        lbMaLoaiSP.setBounds(31, 80, 70, 20);

        lbMota.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMota.setText("Mô tả");
        CategoryPanel.add(lbMota);
        lbMota.setBounds(31, 150, 120, 20);

        txtTenLoaiSP.setBackground(new Color(255, 255, 255));
        CategoryPanel.add(txtTenLoaiSP);
        txtTenLoaiSP.setBounds(110, 100, 330, 35);

        lbTenLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenLoaiSP.setText("Tên");
        CategoryPanel.add(lbTenLoaiSP);
        lbTenLoaiSP.setBounds(111, 80, 130, 20);

        btnThemLoaiSP.setBackground(new Color(220, 247, 227));
        btnThemLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemLoaiSP.setForeground(new Color(47, 168, 79));
        btnThemLoaiSP.setText("Thêm");
        btnThemLoaiSP.setBorderPainted(false);
        btnThemLoaiSP.setFocusPainted(false);
        CategoryPanel.add(btnThemLoaiSP);
        btnThemLoaiSP.setBounds(30, 280, 410, 35);

        btnSuaLoaiSP.setBackground(new Color(252, 243, 215));
        btnSuaLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaLoaiSP.setForeground(new Color(243, 170, 24));
        btnSuaLoaiSP.setText("Sửa");
        btnSuaLoaiSP.setBorderPainted(false);
        btnSuaLoaiSP.setFocusPainted(false);
        CategoryPanel.add(btnSuaLoaiSP);
        btnSuaLoaiSP.setBounds(30, 330, 270, 40);

        btnXoaLoaiSP.setBackground(new Color(254, 228, 226));
        btnXoaLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaLoaiSP.setForeground(new Color(234, 61, 47));
        btnXoaLoaiSP.setText("Xóa");
        btnXoaLoaiSP.setBorderPainted(false);
        btnXoaLoaiSP.setFocusPainted(false);
        CategoryPanel.add(btnXoaLoaiSP);
        btnXoaLoaiSP.setBounds(310, 330, 130, 40);

        taMota.setColumns(20);
        taMota.setRows(5);
        jScrollPane2.setViewportView(taMota);

        CategoryPanel.add(jScrollPane2);
        jScrollPane2.setBounds(30, 170, 410, 96);

        pluginPanel.add(CategoryPanel);
        CategoryPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        detailSPPanel.setBackground(new Color(255, 255, 255));
        detailSPPanel.setLayout(null);

        lbDetailSPTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailSPTitle.setForeground(new Color(37, 57, 111));
        lbDetailSPTitle.setText("Nội dung sản phẩm");
        detailSPPanel.add(lbDetailSPTitle);
        lbDetailSPTitle.setBounds(130, 20, 290, 40);

        imagePanel.setLayout(null);
        imagePanel.add(imgSP);
        imgSP.setBounds(0, 0, 170, 170);

        detailSPPanel.add(imagePanel);
        imagePanel.setBounds(30, 100, 170, 170);

        taMotaSP.setColumns(20);
        taMotaSP.setRows(5);
        jScrollPane1.setViewportView(taMotaSP);

        detailSPPanel.add(jScrollPane1);
        jScrollPane1.setBounds(210, 100, 240, 170);

        lbMotaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMotaSP.setText("Mô tả");
        detailSPPanel.add(lbMotaSP);
        lbMotaSP.setBounds(211, 80, 70, 20);

        btnLuu.setBackground(new Color(252, 243, 215));
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLuu.setForeground(new Color(243, 170, 24));
        btnLuu.setText("Lưu");
        btnLuu.setBorderPainted(false);
        btnLuu.setFocusPainted(false);
        detailSPPanel.add(btnLuu);
        btnLuu.setBounds(30, 280, 420, 40);

        add(detailSPPanel);
        detailSPPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDonGiaUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbDonGiaUnit.setText("đồng");
        infoPanel.add(lbDonGiaUnit);
        lbDonGiaUnit.setBounds(150, 220, 50, 40);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin sản phẩm");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã");
        infoPanel.add(lbMaSP);
        lbMaSP.setBounds(31, 80, 70, 20);

        lbLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbLoaiSP.setText("Loại");
        infoPanel.add(lbLoaiSP);
        lbLoaiSP.setBounds(311, 80, 120, 20);

        txtMaSP.setBackground(new Color(245, 245, 245));
        txtMaSP.setEnabled(false);
        infoPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 100, 70, 35);

        txtLoaiPS.setBackground(new Color(245, 245, 245));
        txtLoaiPS.setEnabled(false);
        infoPanel.add(txtLoaiPS);
        txtLoaiPS.setBounds(310, 100, 120, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(310, 280, 160, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 280, 260, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(310, 330, 160, 40);
        infoPanel.add(txtTenSP);
        txtTenSP.setBounds(30, 160, 260, 35);

        lbTenSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenSP.setText("Tên");
        infoPanel.add(lbTenSP);
        lbTenSP.setBounds(31, 140, 80, 20);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Nhà cung cấp");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(311, 140, 120, 20);

        txtMaNCC.setBackground(new Color(245, 245, 245));
        txtMaNCC.setEnabled(false);
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(310, 160, 120, 35);

        txtDonVi.setBackground(new Color(255, 255, 255));
        infoPanel.add(txtDonVi);
        txtDonVi.setBounds(220, 220, 70, 35);

        lbDonVi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDonVi.setText("Đơn vị");
        infoPanel.add(lbDonVi);
        lbDonVi.setBounds(221, 200, 60, 20);

        lbDonGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDonGia.setText("Đơn giá");
        infoPanel.add(lbDonGia);
        lbDonGia.setBounds(31, 200, 130, 20);

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        txtDonGia = new JFormattedTextField(principleFormat);
        infoPanel.add(txtDonGia);
        txtDonGia.setBounds(30, 220, 170, 35);

        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSoLuong.setText("Số lượng");
        infoPanel.add(lbSoLuong);
        lbSoLuong.setBounds(311, 200, 90, 20);

        txtSoLuong.setBackground(new Color(245, 245, 245));
        txtSoLuong.setEnabled(false);
        infoPanel.add(txtSoLuong);
        txtSoLuong.setBounds(310, 220, 120, 35);

        btnSelectKH.setText("jButton1");
        infoPanel.add(btnSelectKH);
        btnSelectKH.setBounds(440, 100, 35, 35);

        btnSelectKH1.setText("jButton1");
        infoPanel.add(btnSelectKH1);
        btnSelectKH1.setBounds(440, 160, 35, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        tableLoaiSPPanel.setBackground(new Color(255, 255, 255));
        tableLoaiSPPanel.setLayout(null);

        btnResetCategory.setText("jButton3");
        tableLoaiSPPanel.add(btnResetCategory);
        btnResetCategory.setBounds(250, 20, 40, 35);

        lbCategoryTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCategoryTableTitle.setForeground(new Color(37, 57, 111));
        lbCategoryTableTitle.setText("Loại");
        tableLoaiSPPanel.add(lbCategoryTableTitle);
        lbCategoryTableTitle.setBounds(30, 10, 130, 40);

        jScrollPaneDetail = new JScrollPane();
        jScrollPaneDetail.setBackground(Color.white);
        jScrollPaneDetail.setBorder(BorderFactory.createEmptyBorder());
        jScrollPaneDetail.setFocusable(false);

        tableDetail = new TableColumn();
        columnHeaderDetail = new String [] {
                "Mã", "Tên"
        };
        tableDetail.setModel(new DefaultTableModel(
                new Object [][] {},
                columnHeaderDetail
        ) {
            final boolean[] canEdit = new boolean [columnHeaderDetail.length];

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPaneDetail.setViewportView(tableDetail);
        if (tableDetail.getColumnModel().getColumnCount() > 0) {
            tableDetail.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        tableDetail.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                //onClickTableDetailRow();
            }
        });
        tableLoaiSPPanel.add(jScrollPaneDetail);
        jScrollPaneDetail.setBounds(22, 60, 280, 350);

        add(tableLoaiSPPanel);
        tableLoaiSPPanel.setBounds(670, 400, 320, 410);
    }

    private String[] columnHeaderDetail;
    private JScrollPane jScrollPaneDetail;
    private TableColumn tableDetail;

    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel CategoryPanel = new JPanel();
    private final JLabel lbCategoryTitle = new JLabel();
    private final JTextField txtMaLoaiSP = new JTextField();
    private final JLabel lbMaLoaiSP = new JLabel();
    private final JLabel lbMota = new JLabel();
    private final JTextField txtTenLoaiSP = new JTextField();
    private final JLabel lbTenLoaiSP = new JLabel();
    private final JButton btnThemLoaiSP = new JButton();
    private final JButton btnSuaLoaiSP = new JButton();
    private final JButton btnXoaLoaiSP = new JButton();
    private final JScrollPane jScrollPane2 = new JScrollPane();
    private final JTextArea taMota = new JTextArea();
    private final JPanel detailSPPanel = new JPanel();
    private final JLabel lbDetailSPTitle = new JLabel();
    private final JPanel imagePanel = new JPanel();
    private final JLabel imgSP = new JLabel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JTextArea taMotaSP = new JTextArea();
    private final JLabel lbMotaSP = new JLabel();
    private final JButton btnLuu = new JButton();
    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDonGiaUnit = new JLabel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaSP = new JLabel();
    private final JLabel lbLoaiSP = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JTextField txtLoaiPS = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JTextField txtTenSP = new JTextField();
    private final JLabel lbTenSP = new JLabel();
    private final JLabel lbMaNCC = new JLabel();
    private final JTextField txtMaNCC = new JTextField();
    private final JTextField txtDonVi = new JTextField();
    private final JLabel lbDonVi = new JLabel();
    private final JLabel lbDonGia = new JLabel();
    private JFormattedTextField txtDonGia;
    private final JLabel lbSoLuong = new JLabel();
    private final JTextField txtSoLuong = new JTextField();
    private final JButton btnSelectKH = new JButton();
    private final JButton btnSelectKH1 = new JButton();
    private final JPanel tableLoaiSPPanel = new JPanel();
    private final JButton btnResetCategory = new JButton();
    private final JLabel lbCategoryTableTitle = new JLabel();
}
