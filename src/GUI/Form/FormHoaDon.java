package GUI.Form;

import BUS.*;
import BUS.Interfaces.*;
import BUS.SearchMapper.NhaCungCapSearchMapper;
import BUS.SearchMapper.TaiKhoanSearchMapper;
import DTO.CT_HoaDonDTO;
import DTO.HoaDonDTO;
import DTO.Interface.IEntity;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.FrameSelect;
import GUI.components.TableColumn;
import Utils.General;
import Utils.Validator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                        dto.getNgayLap(), dto.getTongTien(), dto.getTienKhuyenMai(),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "HD" + dto.getMaHD(), "KH" + dto.getMaKH(), "NV" + dto.getMaNV(),
                        dto.getNgayLap(), dto.getTongTien(), dto.getTienKhuyenMai()};
            model.addRow(row);
        }
    }

    public void fillTableDetail(int id) {
        DefaultTableModel model = (DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();

        ArrayList<CT_HoaDonDTO> list = ctHoaDonBUS.findByMaHD(id);
        for (CT_HoaDonDTO dto: list) {
            Object[] row = new Object[] { "SP" + dto.getMaSP(), dto.getSoLuong(), dto.getTienKhuyenMai()};
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
                    "Mã", "Khách hàng", "Nhân viên", "Ngày lập", "Tổng tiền", "Tiền khuyến mãi", "Tình trạng"
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

        txtSoLuong.setEnabled(false);
        txtSoLuong.setBackground(new Color(255, 255, 255));
        detailCTHDPanel.add(txtSoLuong);
        txtSoLuong.setBounds(220, 220, 80, 35);

        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSoLuong.setText("Số lượng");
        detailCTHDPanel.add(lbSoLuong);
        lbSoLuong.setBounds(221, 200, 80, 20);

        txtMaSP.setEnabled(false);
        txtMaSP.setBackground(new Color(255, 255, 255));
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
        detailCTHDPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 35, 35);

        btnThemCT.setBackground(new Color(220, 247, 227));
        btnThemCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemCT.setForeground(new Color(47, 168, 79));
        btnThemCT.setText("Thêm");
        btnThemCT.setBorderPainted(false);
        btnThemCT.setFocusPainted(false);
        detailCTHDPanel.add(btnThemCT);
        btnThemCT.setBounds(30, 280, 410, 35);

        btnSuaCT.setBackground(new Color(252, 243, 215));
        btnSuaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaCT.setForeground(new Color(243, 170, 24));
        btnSuaCT.setText("Sửa");
        btnSuaCT.setBorderPainted(false);
        btnSuaCT.setFocusPainted(false);
        detailCTHDPanel.add(btnSuaCT);
        btnSuaCT.setBounds(30, 330, 270, 40);

        btnXoaCT.setBackground(new Color(254, 228, 226));
        btnXoaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaCT.setForeground(new Color(234, 61, 47));
        btnXoaCT.setText("Xóa");
        btnXoaCT.setBorderPainted(false);
        btnXoaCT.setFocusPainted(false);
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
        txtMaKH.setBounds(120, 100, 130, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        infoPanel.add(btnThem);
        btnThem.setBounds(310, 280, 160, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 330, 260, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        infoPanel.add(btnXoa);
        btnXoa.setBounds(310, 330, 160, 40);

        txtTotal = new JFormattedTextField(principleFormat);
        txtTotal.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtTotal);
        txtTotal.setBounds(30, 160, 260, 35);

        lbTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotal.setText("Tổng tiền");
        infoPanel.add(lbTotal);
        lbTotal.setBounds(31, 140, 130, 20);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(311, 80, 120, 20);

        txtMaNV.setEnabled(false);
        txtMaNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(310, 100, 120, 35);

        txtNgayLap.setEnabled(false);
        txtNgayLap.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtNgayLap);
        txtNgayLap.setBounds(310, 160, 160, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày lập");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(311, 140, 150, 20);

        lbKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKhuyenMai.setText("Khuyến mãi");
        infoPanel.add(lbKhuyenMai);
        lbKhuyenMai.setBounds(31, 200, 130, 20);

        txtKhuyenMai = new JFormattedTextField(principleFormat);
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

        btnSelectNV.setText("jButton1");
        infoPanel.add(btnSelectNV);
        btnSelectNV.setBounds(440, 100, 35, 35);

        btnSelectKH.setText("jButton1");
        infoPanel.add(btnSelectKH);
        btnSelectKH.setBounds(260, 100, 35, 35);

        btnSelectKhuyenMai.setText("jButton1");
        infoPanel.add(btnSelectKhuyenMai);
        btnSelectKhuyenMai.setBounds(310, 220, 35, 35);

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

        idHolder = new JLabel("0");
        idHolder.setEnabled(false);
        idHolder.setFocusable(false);
        idHolder.setVisible(false);
        ctHDPanel.add(idHolder);
        idHolder.setBounds(0, 0, 50, 20);

        btnResetCT.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnResetCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetCTListener();
            }
        });
        tablePanel.add(btnResetCT);
        btnResetCT.setBounds(250, 20, 40, 35);

        add(ctHDPanel);
        ctHDPanel.setBounds(670, 400, 320, 410);
    }

    private void onClickBtnSelectMaKMListener() {
        try {
            JFrame frame = new FrameSelect("tài khoản", txtMaTK, new TaiKhoanSearchMapper(), FormTaiKhoan.class, FormNhanVien.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectMaKHListener() {
        try {
            JFrame frame = new FrameSelect("tài khoản", txtMaTK, new TaiKhoanSearchMapper(), FormTaiKhoan.class, FormNhanVien.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectMaNVListener() {
        try {
            JFrame frame = new FrameSelect("tài khoản", txtMaTK, new TaiKhoanSearchMapper(), FormTaiKhoan.class, FormNhanVien.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectMaSPListener() {
        try {
            JFrame frame = new FrameSelect("tài khoản", txtMaTK, new TaiKhoanSearchMapper(), FormTaiKhoan.class, FormNhanVien.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private NhaCungCapDTO getUserInput() {
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(txtMaNCC.getText().replace("NCC", ""));
        } catch (NumberFormatException ignored) {}

        NhaCungCapDTO dto = new NhaCungCapDTO();
        dto.setMaNCC(idNCC);
        dto.setTenNCC(txtTenNCC.getText());
        dto.setSDT(txtSDT.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setSoTaiKhoan(txtSTK.getText());
        dto.setTinhTrang(1);
        return dto;
    }

    private NhaCungCapDTO getUserInputCT() {
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(txtMaNCC.getText().replace("NCC", ""));
        } catch (NumberFormatException ignored) {}

        NhaCungCapDTO dto = new NhaCungCapDTO();
        dto.setMaNCC(idNCC);
        dto.setTenNCC(txtTenNCC.getText());
        dto.setSDT(txtSDT.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setSoTaiKhoan(txtSTK.getText());
        dto.setTinhTrang(1);
        return dto;
    }

    private void onClickBtnThemListener() {
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            NhaCungCapDTO dto = getUserInput();
            if (!Validator.isValidName(dto.getTenNCC()))
                throw new Exception("Tên không hợp lệ.");
            if (!Validator.isValidPhone(dto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            nhaCungCapBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Thêm nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Thêm nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            NhaCungCapDTO newDto = getUserInput();
            NhaCungCapDTO oldDto = nhaCungCapBUS.findByID(newDto.getMaNCC());
            if (oldDto == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            oldDto.setTinhTrang(1);
            nhaCungCapBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Kích hoạt nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Kích hoạt nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            NhaCungCapDTO newDto = getUserInput();
            if (newDto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapBUS.findByID(newDto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            if (!Validator.isValidName(newDto.getTenNCC()))
                throw new Exception("Tên không hợp lệ.");
            if (!Validator.isValidPhone(newDto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            nhaCungCapBUS.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Sửa nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Sửa nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            NhaCungCapDTO userInput = getUserInput();
            if (userInput.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            NhaCungCapDTO dto = nhaCungCapBUS.findByID(userInput.getMaNCC());
            if (dto == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                nhaCungCapBUS.delete(dto.getMaNCC());
            else {
                dto.setTinhTrang(0);
                nhaCungCapBUS.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Xóa nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Xóa nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("nhà cung cấp", new NhaCungCapSearchMapper(), FormNCC.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
    }

    private void onClickBtnResetCTListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
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
        txtMaHD.setText("HD" + dto.getMaHD());
        txtMaKH.setText("KH" + dto.getMaKH());
        txtMaNV.setText("NV" + dto.getMaNV());
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
            selectedID = Integer.parseInt(idHolder.getText());
        } catch (Exception e) {
            return;
        }
        ArrayList<CT_HoaDonDTO> dtoList = ctHoaDonBUS.findByMaHD(selectedID);
        if (dtoList == null)
            return;
        CT_HoaDonDTO dto = dtoList.get(index);
        SanPhamDTO product = sanPhamBUS.findByID(dto.getMaSP());
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

    private JLabel idHolder;
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
    private final JButton btnSelectHD = new JButton();
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
    private final JButton btnSelectNV = new JButton();
    private final JButton btnSelectKH = new JButton();
    private final JButton btnSelectKhuyenMai = new JButton();
    private final JPanel ctHDPanel = new JPanel();
    private final JLabel lbThanhTienCTUnit = new JLabel();
    private final JLabel lbKhuyenMaiCTUnit = new JLabel();
    private final JLabel lbKhuyenMaiUnit = new JLabel();
    private final JLabel lbThanhTienUnit = new JLabel();
    private final JLabel lbTongTienUnit = new JLabel();
}
