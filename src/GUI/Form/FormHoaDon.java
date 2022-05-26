package GUI.Form;

import BUS.*;
import BUS.Interfaces.*;
import BUS.SearchMapper.HoaDonSearchMapper;
import BUS.SearchMapper.KhachHangSearchMapper;
import DTO.*;
import DTO.Interface.IEntity;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.FrameSelect;
import GUI.components.TableColumn;
import Utils.General;
import Utils.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FormHoaDon extends JTablePanel {
    public FormHoaDon() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        ArrayList<HoaDonDTO> list = new ArrayList<HoaDonDTO>();
        if (idList == null)
            list = hoaDonBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(hoaDonBUS.findByID(entity.getID()));

        for (HoaDonDTO dto: list) {
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "HD" + dto.getMaHD(), "KH" + dto.getMaKH(), "NV" + dto.getMaNV(),
                        dto.getNgayLap(), currencyVN.format(dto.getTongTien()), currencyVN.format(dto.getTienKhuyenMai()),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "HD" + dto.getMaHD(), "KH" + dto.getMaKH(), "NV" + dto.getMaNV(),
                        dto.getNgayLap(), currencyVN.format(dto.getTongTien()), currencyVN.format(dto.getTienKhuyenMai())};
            model.addRow(row);
        }
    }

    public void fillTableDetail(Integer id) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        DefaultTableModel model = (DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        if (id == null)
            return;
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();

        ArrayList<CT_HoaDonDTO> list = ctHoaDonBUS.findByMaHD(id);
        for (CT_HoaDonDTO dto: list) {
            Object[] row = new Object[] { "SP" + dto.getMaSP(), dto.getSoLuong(), currencyVN.format(dto.getTienKhuyenMai())};
            model.addRow(row);
        }
    }
    
    private void initComponents() {
        NumberFormat principleFormat = NumberFormat.getNumberInstance();
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
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnTimKiemListener();
            }
        });
        tablePanel.add(btnTimKiem);
        btnTimKiem.setBounds(410, 20, 170, 35);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        tablePanel.add(btnReset);
        btnReset.setBounds(590, 20, 40, 35);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.CURRENT_ROLE.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Khách hàng", "Nhân viên", "Ngày lập", "Tổng tiền", "Khuyến mãi", "Tình trạng"
            };
        else columnHeader = new String [] {
                    "Mã", "Khách hàng", "Nhân viên", "Ngày lập", "Tổng tiền", "Tiền khuyến mãi",
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
                onClickTableRow();
            }
        });
        tablePanel.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 630, 350);

        add(tablePanel);
        tablePanel.setBounds(10, 400, 650, 410);

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        detailCTHDPanel.setBackground(new Color(255, 255, 255));
        detailCTHDPanel.setLayout(null);

        lbThanhTienCTUnit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThanhTienCTUnit.setText("đồng");
        detailCTHDPanel.add(lbThanhTienCTUnit);
        lbThanhTienCTUnit.setBounds(390, 100, 50, 40);

        lbKhuyenMaiCTUnit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhuyenMaiCTUnit.setText("đồng");
        detailCTHDPanel.add(lbKhuyenMaiCTUnit);
        lbKhuyenMaiCTUnit.setBounds(390, 160, 50, 40);

        lbCTHDTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCTHDTitle.setForeground(new Color(37, 57, 111));
        lbCTHDTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbCTHDTitle.setText("Chi tiết hóa đơn");
        detailCTHDPanel.add(lbCTHDTitle);
        lbCTHDTitle.setBounds(110, 20, 270, 40);

        idHolderCT = new JLabel();
        idHolderCT.setEnabled(false);
        idHolderCT.setFocusable(false);
        idHolderCT.setVisible(false);
        detailCTHDPanel.add(idHolderCT);
        idHolderCT.setBounds(0, 0, 20, 20);

        txtMaHDCT.setEnabled(false);
        txtMaHDCT.setBackground(new Color(245, 245, 245));
        detailCTHDPanel.add(txtMaHDCT);
        txtMaHDCT.setBounds(30, 100, 70, 35);

        lbMaHDCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaHDCT.setText("Mã HD");
        detailCTHDPanel.add(lbMaHDCT);
        lbMaHDCT.setBounds(31, 80, 70, 20);

        txtSanPham.setEnabled(false);
        txtSanPham.setBackground(new Color(245, 245, 245));
        detailCTHDPanel.add(txtSanPham);
        txtSanPham.setBounds(30, 220, 180, 35);

        lbSanPham.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSanPham.setText("Sản phẩm");
        detailCTHDPanel.add(lbSanPham);
        lbSanPham.setBounds(31, 200, 120, 20);

        txtSoLuong.setBackground(new Color(255, 255, 255));
        txtSoLuong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalCTListener();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalCTListener();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        detailCTHDPanel.add(txtSoLuong);
        txtSoLuong.setBounds(220, 220, 80, 35);

        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSoLuong.setText("Số lượng");
        detailCTHDPanel.add(lbSoLuong);
        lbSoLuong.setBounds(221, 200, 80, 20);

        txtMaSP.setEnabled(false);
        txtMaSP.setBackground(new Color(245, 245, 245));
        txtMaSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fillSanPhamDetailListener();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillSanPhamDetailListener();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        detailCTHDPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 160, 70, 35);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã SP");
        detailCTHDPanel.add(lbMaSP);
        lbMaSP.setBounds(31, 140, 70, 20);

        txtThanhTienCT = new JFormattedTextField(principleFormat);
        txtThanhTienCT.setEnabled(false);
        txtThanhTienCT.setBackground(new Color(245, 245, 245));
        detailCTHDPanel.add(txtThanhTienCT);
        txtThanhTienCT.setBounds(155, 100, 285, 35);

        lbThanhTienCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbThanhTienCT.setText("Thành tiền");
        detailCTHDPanel.add(lbThanhTienCT);
        lbThanhTienCT.setBounds(156, 80, 130, 20);

        /*btnSelectHD.setText("jButton1");
        detailCTHDPanel.add(btnSelectHD);
        btnSelectHD.setBounds(110, 100, 35, 35);*/

        txtKhuyenMaiCT = new JFormattedTextField(principleFormat);
        txtKhuyenMaiCT.setEnabled(false);
        txtKhuyenMaiCT.setBackground(new Color(245, 245, 245));
        detailCTHDPanel.add(txtKhuyenMaiCT);
        txtKhuyenMaiCT.setBounds(155, 160, 285, 35);

        lbKhuyenMaiCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKhuyenMaiCT.setText("Khuyến mãi");
        detailCTHDPanel.add(lbKhuyenMaiCT);
        lbKhuyenMaiCT.setBounds(156, 140, 120, 20);

        lbDonGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDonGia.setText("Đơn giá");
        detailCTHDPanel.add(lbDonGia);
        lbDonGia.setBounds(311, 200, 100, 20);

        txtDonGia.setEnabled(false);
        txtDonGia.setBackground(new Color(245, 245, 245));
        detailCTHDPanel.add(txtDonGia);
        txtDonGia.setBounds(310, 220, 130, 35);

        btnSelectSP.setText("jButton1");
        btnSelectSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaSPListener();
            }
        });
        detailCTHDPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 35, 35);

        btnThemCT.setBackground(new Color(220, 247, 227));
        btnThemCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemCT.setForeground(new Color(47, 168, 79));
        btnThemCT.setText("Thêm");
        btnThemCT.setBorderPainted(false);
        btnThemCT.setFocusPainted(false);
        btnThemCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnThemCTListener();
            }
        });
        detailCTHDPanel.add(btnThemCT);
        btnThemCT.setBounds(30, 280, 410, 35);

        btnSuaCT.setBackground(new Color(252, 243, 215));
        btnSuaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaCT.setForeground(new Color(243, 170, 24));
        btnSuaCT.setText("Sửa");
        btnSuaCT.setBorderPainted(false);
        btnSuaCT.setFocusPainted(false);
        btnSuaCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSuaCTListener();
            }
        });
        detailCTHDPanel.add(btnSuaCT);
        btnSuaCT.setBounds(30, 330, 270, 40);

        btnXoaCT.setBackground(new Color(254, 228, 226));
        btnXoaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaCT.setForeground(new Color(234, 61, 47));
        btnXoaCT.setText("Xóa");
        btnXoaCT.setBorderPainted(false);
        btnXoaCT.setFocusPainted(false);
        btnXoaCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnXoaCTListener();
            }
        });
        detailCTHDPanel.add(btnXoaCT);
        btnXoaCT.setBounds(310, 330, 130, 40);

        pluginPanel.add(detailCTHDPanel);
        detailCTHDPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbKhuyenMaiUnit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhuyenMaiUnit.setText("đồng");
        infoPanel.add(lbKhuyenMaiUnit);
        lbKhuyenMaiUnit.setBounds(240, 220, 50, 40);

        lbThanhTienUnit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThanhTienUnit.setText("đồng");
        infoPanel.add(lbThanhTienUnit);
        lbThanhTienUnit.setBounds(240, 280, 50, 40);

        lbTongTienUnit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTongTienUnit.setText("đồng");
        infoPanel.add(lbTongTienUnit);
        lbTongTienUnit.setBounds(240, 160, 50, 40);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin hóa đơn");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaHD.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaHD.setText("Mã");
        infoPanel.add(lbMaHD);
        lbMaHD.setBounds(31, 80, 70, 20);

        lbMaKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaKH.setText("Khách hàng");
        infoPanel.add(lbMaKH);
        lbMaKH.setBounds(121, 80, 120, 20);

        txtMaHD.setEnabled(false);
        txtMaHD.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaHD);
        txtMaHD.setBounds(30, 100, 70, 35);

        txtMaKH.setEnabled(false);
        txtMaKH.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaKH);
        txtMaKH.setBounds(120, 100, 170, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equalsIgnoreCase("Thêm"))
                    onClickBtnThemListener();
                else onClickBtnKichHoatListener();
            }
        });
        infoPanel.add(btnThem);
        btnThem.setBounds(310, 280, 160, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSuaListener();
            }
        });
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 330, 260, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnXoaListener();
            }
        });
        infoPanel.add(btnXoa);
        btnXoa.setBounds(310, 330, 160, 40);

        txtTotal = new JFormattedTextField(principleFormat);
        txtTotal.setBackground(new Color(245, 245, 245));
        txtTotal.setEnabled(false);
        infoPanel.add(txtTotal);
        txtTotal.setBounds(30, 160, 260, 35);

        lbTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotal.setText("Tổng tiền");
        infoPanel.add(lbTotal);
        lbTotal.setBounds(31, 140, 130, 20);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(311, 140, 120, 20);

        txtMaNV.setText(new NhanVienBUS().findByID(General.CURRENT_USER.getMaNV()).getHoTen());
        txtMaNV.setEnabled(false);
        txtMaNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(310, 160, 160, 35);

        txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        txtNgayLap.setEnabled(false);
        txtNgayLap.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtNgayLap);
        txtNgayLap.setBounds(310, 220, 160, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày lập");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(311, 200, 150, 20);

        lbKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKhuyenMai.setText("Khuyến mãi");
        infoPanel.add(lbKhuyenMai);
        lbKhuyenMai.setBounds(31, 200, 130, 20);

        txtKhuyenMai = new JFormattedTextField(principleFormat);
        txtKhuyenMai.setBackground(new Color(245, 245, 245));
        txtKhuyenMai.setEnabled(false);
        infoPanel.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(30, 220, 260, 35);

        lbThanhTien.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbThanhTien.setText("Thành tiền");
        infoPanel.add(lbThanhTien);
        lbThanhTien.setBounds(31, 260, 130, 20);

        txtThanhTien = new JFormattedTextField(principleFormat);
        txtThanhTien.setEnabled(false);
        txtThanhTien.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtThanhTien);
        txtThanhTien.setBounds(30, 280, 260, 35);

        btnSelectKH.setText("jButton1");
        btnSelectKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaKHListener();
            }
        });
        infoPanel.add(btnSelectKH);
        btnSelectKH.setBounds(310, 100, 35, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        ctHDPanel.setBackground(new Color(255, 255, 255));
        ctHDPanel.setLayout(null);

        jScrollPaneDetail = new JScrollPane();
        jScrollPaneDetail.setBackground(Color.white);
        jScrollPaneDetail.setBorder(BorderFactory.createEmptyBorder());
        jScrollPaneDetail.setFocusable(false);

        tableDetail = new TableColumn();
        columnHeaderDetail = new String [] {
                "Mã SP", "Số lượng", "Khuyến mãi"
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
                onClickTableDetailRow();
            }
        });
        ctHDPanel.add(jScrollPaneDetail);
        jScrollPaneDetail.setBounds(22, 60, 280, 350);

        btnResetCT.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnResetCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetCTListener();
            }
        });
        ctHDPanel.add(btnResetCT);
        btnResetCT.setBounds(250, 20, 40, 35);

        add(ctHDPanel);
        ctHDPanel.setBounds(670, 400, 320, 410);
    }

    private void onClickBtnSelectMaKHListener() {
        try {
            JFrame frame = new FrameSelect("khách hàng", txtMaKH, new KhachHangSearchMapper(), FormKhachHang.class, FormHoaDon.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectMaSPListener() {
    }

    private void fillSanPhamDetailListener() {
        int idSP;
        try {
            idSP = Integer.parseInt(txtMaSP.getText().replace("SP", ""));
        } catch (Exception e) {
            txtMaSP.setText("Lỗi");
            return;
        }
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        ICT_KhuyenMaiBUS khuyenMaiBUS = new CT_KhuyenMaiBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(idSP);
        txtSanPham.setText(sanPhamDTO != null ? sanPhamDTO.getTenSP() : "Không tìm thấy");
        txtDonGia.setText(sanPhamDTO != null ?
                currencyVN.format(sanPhamDTO.getDonGia()).replace(" ₫", "") + "đ/" + sanPhamDTO.getDonVi()
                : currencyVN.format(sanPhamDTO.getDonGia()).replace(" ₫", "") + "đ");
        txtKhuyenMaiCT.setText(currencyVN.format(khuyenMaiBUS.findByMaSP(idSP).getGiamGia()).replace(" ₫", ""));
    }

    private void calculateTotalCTListener() {
        int dg, sl, km;
        try {
            dg = Integer.parseInt(StringUtils.removeLetter(txtDonGia.getText()));
            sl = Integer.parseInt(StringUtils.removeLetter(txtSoLuong.getText()));
            km = ((Number) txtKhuyenMaiCT.getValue()).intValue();
        } catch (Exception ignored) {
            return;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int total = (dg * sl) - km;
        txtThanhTienCT.setText(currencyVN.format(total).replace(" ₫", ""));
    }

    private HoaDonDTO getUserInput() {
        Integer idHD = null;
        Integer idNV = null;
        Integer idKH = null;
        try {
            idHD = Integer.valueOf(txtMaHD.getText().replace("HD", ""));
            idNV = Integer.valueOf(txtMaNV.getText().replace("NV", ""));
            idKH = Integer.valueOf(txtMaKH.getText().replace("KH", ""));
        } catch (NumberFormatException ignored) {}

        HoaDonDTO dto = new HoaDonDTO();
        dto.setMaHD(idHD);
        dto.setMaNV(idNV);
        dto.setMaKH(idKH);
        try {
            dto.setNgayLap(Timestamp.valueOf(txtNgayLap.getText()));
            dto.setTongTien(((Number) txtTotal.getValue()).intValue());
            dto.setTienKhuyenMai(((Number) txtKhuyenMai.getValue()).intValue());
            dto.setTienThanhToan(((Number) txtThanhTien.getValue()).intValue());
        } catch (Exception ignored) {}
        dto.setTinhTrang(1);
        return dto;
    }

    private CT_HoaDonDTO getUserInputCT() {
        Integer idHD = null;
        Integer idSP = null;
        try {
            idHD = Integer.valueOf(txtMaHD.getText().replace("HD", ""));
            idSP = Integer.valueOf(txtMaSP.getText().replace("SP", ""));
        } catch (NumberFormatException ignored) {}

        CT_HoaDonDTO dto = new CT_HoaDonDTO();
        dto.setMaHD(idHD);
        dto.setMaSP(idSP);
        try {
            dto.setMaCTHD(Integer.valueOf(idHolderCT.getText()));
            dto.setDonGia(Integer.valueOf(StringUtils.removeLetter(txtDonGia.getText())));
            dto.setSoLuong(Integer.valueOf(StringUtils.removeLetter(txtSoLuong.getText())));
            dto.setTienKhuyenMai(((Number) txtKhuyenMaiCT.getValue()).intValue());
            dto.setThanhTien(((Number) txtThanhTienCT.getValue()).intValue());
        } catch (Exception ignored) {}
        return dto;
    }

    private void onClickBtnThemListener() {
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            HoaDonDTO dto = getUserInput();
            if (dto.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            if (nhanVienBUS.findByID(dto.getMaNV()) == null)
                throw new Exception("Không tìm thấy nhân viên.");
            if (dto.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            if (khachHangBUS.findByID(dto.getMaKH()) == null)
                throw new Exception("Không tìm thấy khách hàng.");
            if (dto.getNgayLap() == null)
                dto.setNgayLap(new Timestamp(System.currentTimeMillis()));
            if (dto.getTongTien() == null || dto.getTongTien() < 0)
                dto.setTongTien(0);
            if (dto.getTienKhuyenMai() == null || dto.getTienKhuyenMai() < 0)
                dto.setTienKhuyenMai(0);
            if (dto.getTienThanhToan() == null || dto.getTienThanhToan() < 0)
                dto.setTienThanhToan(0);
            hoaDonBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Thêm hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Thêm hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnThemCTListener() {
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            CT_HoaDonDTO dto = getUserInputCT();
            if (dto.getMaHD() == null)
                throw new Exception("Vui lòng chọn hóa đơn.");
            if (hoaDonBUS.findByID(dto.getMaHD()) == null)
                throw new Exception("Không tìm thấy hóa đơn.");
            if (dto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamBUS.findByID(dto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (dto.getSoLuong() == null || dto.getSoLuong() <= 0)
                throw new Exception("Vui lòng nhập số lượng.");
            if (dto.getDonGia() == null || dto.getDonGia() <= 0)
                throw new Exception("Vui lòng nhập đơn giá.");
            if (dto.getTienKhuyenMai() == null || dto.getTienKhuyenMai() < 0)
                dto.setTienKhuyenMai(0);
            if (dto.getThanhTien() == null || dto.getThanhTien() < 0)
                dto.setThanhTien(0);
            ctHoaDonBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Thêm chi tiết hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Thêm chi tiết hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
        int newIndex = tableDetail.getRowCount()-1;
        tableDetail.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPaneDetail.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        try {
            HoaDonDTO newDto = getUserInput();
            HoaDonDTO oldDto = hoaDonBUS.findByID(newDto.getMaHD());
            if (oldDto == null)
                throw new Exception("Không tìm thấy hóa đơn.");
            oldDto.setTinhTrang(1);
            hoaDonBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Kích hoạt hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Kích hoạt hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            HoaDonDTO newDto = getUserInput();
            if (newDto.getMaHD() == null)
                throw new Exception("Vui lòng chọn hóa đơn.");
            HoaDonDTO oldDto = hoaDonBUS.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy hóa đơn.");
            if (newDto.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            if (khachHangBUS.findByID(newDto.getMaKH()) == null)
                throw new Exception("Không tìm thấy khách hàng.");
            oldDto.setMaKH(newDto.getMaKH());
            hoaDonBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Sửa hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Sửa hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnSuaCTListener() {
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            CT_HoaDonDTO newDto = getUserInputCT();
            if (newDto.getMaCTHD() == null)
                throw new Exception("Vui lòng chọn chi tiết hóa đơn.");
            if (newDto.getMaHD() == null)
                throw new Exception("Vui lòng chọn hóa đơn.");
            if (hoaDonBUS.findByID(newDto.getMaHD()) == null)
                throw new Exception("Không tìm thấy hóa đơn.");
            if (newDto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamBUS.findByID(newDto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (newDto.getSoLuong() == null || newDto.getSoLuong() <= 0)
                throw new Exception("Vui lòng nhập số lượng.");
            CT_HoaDonDTO oldDto = ctHoaDonBUS.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy chi tiết hóa đơn.");
            if (newDto.getDonGia() == null || newDto.getDonGia() <= 0)
                throw new Exception("Vui lòng nhập đơn giá.");
            if (newDto.getTienKhuyenMai() == null || newDto.getTienKhuyenMai() < 0)
                newDto.setTienKhuyenMai(0);
            if (newDto.getThanhTien() == null || newDto.getThanhTien() < 0)
                newDto.setThanhTien(0);
            oldDto = newDto;
            ctHoaDonBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Sửa chi tiết hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Sửa chi tiết hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        Integer idHD = null;
        try {
            idHD = Integer.valueOf(txtMaHD.getText());
        } catch (Exception ignored) {}
        fillTableDetail(idHD);
    }

    private void onClickBtnXoaListener() {
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        try {
            HoaDonDTO userInput = getUserInput();
            if (userInput.getMaHD() == null)
                throw new Exception("Vui lòng chọn hóa đơn.");
            HoaDonDTO dto = hoaDonBUS.findByID(userInput.getMaHD());
            if (dto == null)
                throw new Exception("Không tìm thấy hóa đơn.");
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                hoaDonBUS.delete(dto.getMaHD());
            else {
                dto.setTinhTrang(0);
                hoaDonBUS.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Xóa hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Xóa hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnXoaCTListener() {
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        try {
            CT_HoaDonDTO userInput = getUserInputCT();
            if (userInput.getMaCTHD() == null)
                throw new Exception("Vui lòng chọn chi tiết hóa đơn.");
            CT_HoaDonDTO dto = ctHoaDonBUS.findByID(userInput.getMaCTHD());
            if (dto == null)
                throw new Exception("Không tìm thấy chi tiết hóa đơn.");
            ctHoaDonBUS.delete(dto.getMaCTHD());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, "Xóa chi tiết hóa đơn thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormHoaDon.this, "Xóa chi tiết hóa đơn thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("hóa đơn", new HoaDonSearchMapper(), FormHoaDon.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormHoaDon.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        txtMaNV.setText(new NhanVienBUS().findByID(General.CURRENT_USER.getMaNV()).getHoTen());
        txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
        onClickBtnResetCTListener();
    }

    private void onClickBtnResetCTListener() {
        Integer idCTHD = null;
        try {
            idCTHD = Integer.parseInt(idHolderCT.getText());
        } catch (Exception ignored) {}
        fillTableDetail(idCTHD);
        for (Component component:detailCTHDPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        idHolderCT.setText("");
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("HD", ""));
        } catch (Exception e) {
            return;
        }
        HoaDonDTO dto = hoaDonBUS.findByID(selectedID);
        if (dto == null)
            return;
        NhanVienDTO worker = nhanVienBUS.findByID(dto.getMaNV());
        KhachHangDTO customer = khachHangBUS.findByID(dto.getMaKH());
        txtMaHD.setText("HD" + dto.getMaHD());
        txtMaHDCT.setText("HD" + dto.getMaHD());
        txtMaKH.setText(customer != null ? customer.getHoTen() : "Không xác định");
        txtMaNV.setText(worker != null ? worker.getHoTen() : "Không xác định");
        txtNgayLap.setText(dateFormat.format(dto.getNgayLap()));
        txtTotal.setText(currencyVN.format(dto.getTongTien()).replace(" ₫", ""));
        txtKhuyenMai.setText(currencyVN.format(dto.getTienKhuyenMai()).replace(" ₫", ""));
        txtThanhTien.setText(currencyVN.format(dto.getTienThanhToan()).replace(" ₫", ""));

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtThanhTien.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    private void onClickTableDetailRow() {
        int index = tableDetail.getSelectedRow();
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int selectedID;
        try {
            selectedID = Integer.parseInt(txtMaHD.getText());
        } catch (Exception e) {
            return;
        }
        ArrayList<CT_HoaDonDTO> dtoList = ctHoaDonBUS.findByMaHD(selectedID);
        if (dtoList == null)
            return;
        CT_HoaDonDTO dto = dtoList.get(index);
        SanPhamDTO product = sanPhamBUS.findByID(dto.getMaSP());
        idHolderCT.setText(String.valueOf(dto.getMaCTHD()));
        txtMaHDCT.setText("HD" + dto.getMaHD());
        txtMaSP.setText("SP" + dto.getMaSP());
        txtSanPham.setText(product != null ? product.getTenSP() : "Không tìm thấy");
        txtSoLuong.setText(String.valueOf(dto.getSoLuong()));
        txtDonGia.setText(product != null ?
                currencyVN.format(dto.getDonGia()).replace(" ₫", "") + "đ/" + product.getDonVi()
                : currencyVN.format(dto.getDonGia()).replace(" ₫", "") + "đ");
        txtThanhTienCT.setText(currencyVN.format(dto.getThanhTien()).replace(" ₫", ""));
        txtKhuyenMaiCT.setText(currencyVN.format(dto.getTienKhuyenMai()).replace(" ₫", ""));
    }

    private String[] columnHeaderDetail;
    private JScrollPane jScrollPaneDetail;
    private TableColumn tableDetail;

    private JLabel idHolderCT;
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JButton btnResetCT = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel detailCTHDPanel = new JPanel();
    private final JLabel lbCTHDTitle = new JLabel();
    private final JTextField txtMaHDCT = new JTextField();
    private final JLabel lbMaHDCT = new JLabel();
    private final JTextField txtSanPham = new JTextField();
    private final JLabel lbSanPham = new JLabel();
    private final JTextField txtSoLuong = new JTextField();
    private final JLabel lbSoLuong = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JLabel lbMaSP = new JLabel();
    private JFormattedTextField txtThanhTienCT;
    private final JLabel lbThanhTienCT = new JLabel();
    private JFormattedTextField txtKhuyenMaiCT;
    private final JLabel lbKhuyenMaiCT = new JLabel();
    private final JLabel lbDonGia = new JLabel();
    private final JTextField txtDonGia = new JTextField();
    private final JButton btnSelectSP = new JButton();
    private final JButton btnThemCT = new JButton();
    private final JButton btnSuaCT = new JButton();
    private final JButton btnXoaCT = new JButton();
    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaHD = new JLabel();
    private final JLabel lbMaKH = new JLabel();
    private final JTextField txtMaHD = new JTextField();
    private final JTextField txtMaKH = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private JFormattedTextField txtTotal;
    private final JLabel lbTotal = new JLabel();
    private final JLabel lbMaNV = new JLabel();
    private final JTextField txtMaNV = new JTextField();
    private final JTextField txtNgayLap = new JTextField();
    private final JLabel lbNgayLap = new JLabel();
    private final JLabel lbKhuyenMai = new JLabel();
    private JFormattedTextField txtKhuyenMai;
    private final JLabel lbThanhTien = new JLabel();
    private JFormattedTextField txtThanhTien;
    private final JButton btnSelectKH = new JButton();
    private final JPanel ctHDPanel = new JPanel();
    private final JLabel lbThanhTienCTUnit = new JLabel();
    private final JLabel lbKhuyenMaiCTUnit = new JLabel();
    private final JLabel lbKhuyenMaiUnit = new JLabel();
    private final JLabel lbThanhTienUnit = new JLabel();
    private final JLabel lbTongTienUnit = new JLabel();
}
