package com.itblee.gui.Form;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.dto.CT_KhuyenMai;
import com.itblee.dto.KhuyenMai;
import com.itblee.dto.SanPham;
import com.itblee.gui.FrameSearch;
import com.itblee.gui.FrameSelect;
import com.itblee.gui.components.TableColumn;
import com.itblee.mapper.search.KhuyenMaiSearchMapper;
import com.itblee.mapper.search.SanPhamSearchMapper;
import com.itblee.service.CT_KhuyenMaiService;
import com.itblee.service.KhuyenMaiService;
import com.itblee.service.SanPhamService;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormKhuyenMai extends JTablePanel {

    private final KhuyenMaiService khuyenMaiService = Provider.get(KhuyenMaiService.class);

    private final CT_KhuyenMaiService ctKhuyenMaiService = Provider.get(CT_KhuyenMaiService.class);

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    private final SanPhamSearchMapper sanPhamSearchMapper = Provider.get(SanPhamSearchMapper.class);

    private final KhuyenMaiSearchMapper khuyenMaiSearchMapper = Provider.get(KhuyenMaiSearchMapper.class);

    public FormKhuyenMai() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(List<BaseEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<KhuyenMai> list = new ArrayList<>();
        if (idList == null)
            list = khuyenMaiService.findAll();
        else
            for (BaseEntity entity:idList)
                list.add(khuyenMaiService.findByID(entity.getID()));

        for (KhuyenMai dto: list) {
            Object[] row;
            if (General.role.isAdmin())
                row = new Object[] { "KM" + dto.getMaKM(), dto.getTieuDe(), dateFormat.format(dto.getNgayBD()),
                        dateFormat.format(dto.getNgayKT()), dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "KM" + dto.getMaKM(), dto.getTieuDe(), dateFormat.format(dto.getNgayBD()),
                    dateFormat.format(dto.getNgayKT())};
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
        List<CT_KhuyenMai> list = ctKhuyenMaiService.findByMaKM(id);
        for (CT_KhuyenMai dto: list) {
            Object[] row = new Object[] { "SP" + dto.getMaSP(), currencyVN.format(dto.getGiamGia())};
            model.addRow(row);
        }
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
        btnTimKiem.addActionListener(e -> onClickBtnTimKiemListener());
        tablePanel.add(btnTimKiem);
        btnTimKiem.setBounds(410, 20, 170, 35);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(e -> onClickBtnResetListener());
        tablePanel.add(btnReset);
        btnReset.setBounds(590, 20, 40, 35);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.role.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Tiêu đề", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Tiêu đề", "Ngày bắt đầu", "Ngày kết thúc"
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

        table.getSelectionModel().addListSelectionListener(event -> onClickTableRow());
        tablePanel.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 630, 350);

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

        txtMaKMCT.setBackground(new Color(245, 245, 245));
        txtMaKMCT.setEnabled(false);
        ctKMPanel.add(txtMaKMCT);
        txtMaKMCT.setBounds(30, 100, 70, 35);

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
        ctKMPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 160, 70, 35);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã SP");
        ctKMPanel.add(lbMaSP);
        lbMaSP.setBounds(31, 140, 70, 20);

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        txtKhuyenMai = new JFormattedTextField(principleFormat);
        txtKhuyenMai.setBackground(Color.white);
        ctKMPanel.add(txtKhuyenMai);
        txtKhuyenMai.setBounds(110, 100, 330, 35);

        lbKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKhuyenMai.setText("Khuyến mãi");
        ctKMPanel.add(lbKhuyenMai);
        lbKhuyenMai.setBounds(111, 80, 120, 20);

        btnSelectSP.setText("jButton1");
        btnSelectSP.addActionListener(e -> onClickBtnSelectMaSPListener());
        ctKMPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 35, 35);

        btnThemCT.setBackground(new Color(220, 247, 227));
        btnThemCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThemCT.setForeground(new Color(47, 168, 79));
        btnThemCT.setText("Thêm");
        btnThemCT.setBorderPainted(false);
        btnThemCT.setFocusPainted(false);
        btnThemCT.addActionListener(e -> onClickBtnThemCTListener());
        ctKMPanel.add(btnThemCT);
        btnThemCT.setBounds(30, 275, 410, 40);

        btnSuaCT.setBackground(new Color(252, 243, 215));
        btnSuaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaCT.setForeground(new Color(243, 170, 24));
        btnSuaCT.setText("Sửa");
        btnSuaCT.setBorderPainted(false);
        btnSuaCT.setFocusPainted(false);
        btnSuaCT.addActionListener(e -> onClickBtnSuaCTListener());
        ctKMPanel.add(btnSuaCT);
        btnSuaCT.setBounds(30, 330, 200, 40);

        btnXoaCT.setBackground(new Color(254, 228, 226));
        btnXoaCT.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaCT.setForeground(new Color(234, 61, 47));
        btnXoaCT.setText("Xóa");
        btnXoaCT.setBorderPainted(false);
        btnXoaCT.setFocusPainted(false);
        btnXoaCT.addActionListener(e -> onClickBtnXoaCTListener());
        ctKMPanel.add(btnXoaCT);
        btnXoaCT.setBounds(240, 330, 200, 40);


        idHolderCT = new JLabel();
        idHolderCT.setEnabled(false);
        idHolderCT.setFocusable(false);
        idHolderCT.setVisible(false);
        ctKMPanel.add(idHolderCT);
        idHolderCT.setBounds(0, 0, 20, 20);

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
        btnThem.addActionListener(e -> {
            if (btnThem.getText().equalsIgnoreCase("Thêm"))
                onClickBtnThemListener();
            else onClickBtnKichHoatListener();
        });
        infoPanel.add(btnThem);
        btnThem.setBounds(310, 275, 160, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(e -> onClickBtnSuaListener());
        infoPanel.add(btnSua);
        btnSua.setBounds(310, 220, 160, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(e -> onClickBtnXoaListener());
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

        txtNgayBD.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        txtNgayBD.setDateFormatString("dd/MM/yyyy");
        infoPanel.add(txtNgayBD);
        txtNgayBD.setBounds(30, 160, 210, 35);

        txtNgayKT.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        txtNgayKT.setDateFormatString("dd/MM/yyyy");
        infoPanel.add(txtNgayKT);
        txtNgayKT.setBounds(260, 160, 210, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày KT");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(261, 140, 150, 20);

        taNoiDung.setColumns(20);
        taNoiDung.setRows(5);
        taNoiDung.setWrapStyleWord(true);
        taNoiDung.setLineWrap(true);
        taNoiDung.setBorder(new EmptyBorder(5, 5, 5, 5));
        jScrollPane1.setViewportView(taNoiDung);

        infoPanel.add(jScrollPane1);
        jScrollPane1.setBounds(30, 220, 260, 150);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        detailTablePanel.setBackground(new Color(255, 255, 255));
        detailTablePanel.setLayout(null);

        btnReset1.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset1.addActionListener(e -> onClickBtnResetCTListener());
        detailTablePanel.add(btnReset1);
        btnReset1.setBounds(250, 20, 40, 35);

        jScrollPaneDetail = new JScrollPane();
        jScrollPaneDetail.setBackground(Color.white);
        jScrollPaneDetail.setBorder(BorderFactory.createEmptyBorder());
        jScrollPaneDetail.setFocusable(false);

        tableDetail = new TableColumn();
        columnHeaderDetail = new String [] {
                "Mã SP", "Giảm giá"
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

        tableDetail.getSelectionModel().addListSelectionListener(event -> onClickTableDetailRow());
        detailTablePanel.add(jScrollPaneDetail);
        jScrollPaneDetail.setBounds(22, 60, 280, 350);

        add(detailTablePanel);
        detailTablePanel.setBounds(670, 400, 320, 410);
    }

    private void onClickBtnSelectMaSPListener() {
        try {
            JFrame frame = new FrameSelect("sản phẩm", txtMaSP, sanPhamSearchMapper, FormSanPham.class, FormKhuyenMai.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillSanPhamDetailListener() {
        int idSP;
        try {
            idSP = Integer.parseInt(txtMaSP.getText().replace("SP", ""));
        } catch (Exception e) {
            return;
        }
        SanPham sanPham = sanPhamService.findByID(idSP);
        txtSanPham.setText(sanPham != null ? sanPham.getTenSP() : "Không tìm thấy");
    }

    private KhuyenMai getUserInput() {
        Integer idKM = null;
        try {
            idKM = Integer.valueOf(txtMaKM.getText().replace("KM", ""));
        } catch (NumberFormatException ignored) {}

        KhuyenMai dto = new KhuyenMai();
        dto.setMaKM(idKM);
        dto.setTieuDe(txtTieuDe.getText());
        dto.setNoiDung(taNoiDung.getText());
        try {
            dto.setNgayBD(new Timestamp(txtNgayBD.getDate().getTime()));
            dto.setNgayKT(new Timestamp(txtNgayKT.getDate().getTime()));
        } catch (Exception ignored) {}
        dto.setTinhTrang(1);
        return dto;
    }

    private CT_KhuyenMai getUserInputCT() {
        Integer idCTKM = null;
        Integer idSP = null;
        try {
            idSP = Integer.valueOf(txtMaSP.getText().replace("SP", ""));
            idCTKM = Integer.valueOf(txtMaKM.getText().replace("KM", ""));
        } catch (NumberFormatException ignored) {}

        CT_KhuyenMai dto = new CT_KhuyenMai();
        dto.setMaKM(idCTKM);
        dto.setMaSP(idSP);
        try {
            dto.setGiamGia(((Number) txtKhuyenMai.getValue()).intValue());
            dto.setMaCTKM(Integer.valueOf(idHolderCT.getText()));
        } catch (Exception ignored) {}
        return dto;
    }

    private void onClickBtnThemListener() {
        try {
            KhuyenMai dto = getUserInput();
            if (dto.getNgayBD() == null || dto.getNgayKT() == null)
                throw new Exception("Vui lòng chọn thời gian khuyến mãi.");
            if (dto.getNgayBD().after(dto.getNgayKT()))
                throw new Exception("Thời gian khuyến mãi không hợp lệ.");
            khuyenMaiService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Thêm khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Thêm khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnThemCTListener() {
        try {
            CT_KhuyenMai dto = getUserInputCT();
            if (dto.getMaKM() == null)
                throw new Exception("Vui lòng chọn khuyến mãi.");
            if (khuyenMaiService.findByID(dto.getMaKM()) == null)
                throw new Exception("Không tìm thấy khuyến mãi.");
            if (dto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamService.findByID(dto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (dto.getGiamGia() == null || dto.getGiamGia() <= 0)
                throw new Exception("Tiền giảm không hợp lệ.");
            ctKhuyenMaiService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Thêm chi tiết khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Thêm chi tiết khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
        int newIndex = tableDetail.getRowCount()-1;
        tableDetail.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPaneDetail.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        try {
            KhuyenMai newDto = getUserInput();
            KhuyenMai oldDto = khuyenMaiService.findByID(newDto.getMaKM());
            if (oldDto == null)
                throw new Exception("Không tìm thấy khuyến mãi.");
            oldDto.setTinhTrang(1);
            khuyenMaiService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Kích hoạt khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Kích hoạt khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        try {
            KhuyenMai newDto = getUserInput();
            if (newDto.getMaKM() == null)
                throw new Exception("Vui lòng chọn khuyến mãi.");
            if (newDto.getNgayBD() == null || newDto.getNgayKT() == null)
                throw new Exception("Vui lòng chọn thời gian khuyến mãi.");
            if (newDto.getNgayBD().after(newDto.getNgayKT()))
                throw new Exception("Thời gian khuyến mãi không hợp lệ.");
            KhuyenMai oldDto = khuyenMaiService.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy khuyến mãi.");
            oldDto = newDto;
            khuyenMaiService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Sửa khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Sửa khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnSuaCTListener() {
        try {
            CT_KhuyenMai newDto = getUserInputCT();
            if (newDto.getMaCTKM() == null)
                throw new Exception("Vui lòng chọn chi tiết khuyến mãi.");
            if (newDto.getMaKM() == null)
                throw new Exception("Vui lòng chọn chương trình khuyến mãi.");
            if (khuyenMaiService.findByID(newDto.getMaKM()) == null)
                throw new Exception("Không tìm thấy chương trình khuyến mãi.");
            if (newDto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamService.findByID(newDto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (newDto.getGiamGia() == null || newDto.getGiamGia() <= 0)
                throw new Exception("Tiền giảm không hợp lệ.");
            CT_KhuyenMai oldDto = ctKhuyenMaiService.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy chi tiết khuyến mãi.");
            oldDto = newDto;
            ctKhuyenMaiService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Sửa chi tiết khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Sửa chi tiết khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        Integer idKM = null;
        try {
            idKM = Integer.valueOf(txtMaKM.getText());
        } catch (Exception ignored) {}
        fillTableDetail(idKM);
    }

    private void onClickBtnXoaListener() {
        try {
            KhuyenMai userInput = getUserInput();
            if (userInput.getMaKM() == null)
                throw new Exception("Vui lòng chọn khuyến mãi.");
            KhuyenMai dto = khuyenMaiService.findByID(userInput.getMaKM());
            if (dto == null)
                throw new Exception("Không tìm thấy khuyến mãi.");
            if (General.role.isAdmin() && dto.getTinhTrang() == 0)
                khuyenMaiService.delete(dto.getMaKM());
            else {
                dto.setTinhTrang(0);
                khuyenMaiService.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Xóa khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Xóa khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnXoaCTListener() {
        try {
            CT_KhuyenMai userInput = getUserInputCT();
            if (userInput.getMaCTKM() == null)
                throw new Exception("Vui lòng chọn chi tiết khuyến mãi.");
            CT_KhuyenMai dto = ctKhuyenMaiService.findByID(userInput.getMaCTKM());
            if (dto == null)
                throw new Exception("Không tìm thấy chi tiết khuyến mãi.");
            ctKhuyenMaiService.delete(dto.getMaCTKM());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, "Xóa chi tiết khuyến mãi thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhuyenMai.this, "Xóa chi tiết khuyến mãi thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("khuyến mãi", khuyenMaiSearchMapper, FormKhuyenMai.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhuyenMai.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        taNoiDung.setText("");
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
        onClickBtnResetCTListener();
    }

    private void onClickBtnResetCTListener() {
        Integer idKM = null;
        try {
            idKM = Integer.parseInt(txtMaKM.getText().replace("KM", ""));
        } catch (Exception ignored) {}
        fillTableDetail(idKM);
        for (Component component:ctKMPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        idHolderCT.setText("");
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("KM", ""));
        } catch (Exception e) {
            return;
        }
        KhuyenMai dto = khuyenMaiService.findByID(selectedID);
        if (dto == null)
            return;
        txtMaKM.setText("KM" + dto.getMaKM());
        txtMaKMCT.setText("KM" + dto.getMaKM());
        txtTieuDe.setText(dto.getTieuDe());
        txtNgayBD.setDate(dto.getNgayBD());
        txtNgayKT.setDate(dto.getNgayKT());
        taNoiDung.setText(dto.getNoiDung());
        fillTableDetail(dto.getMaKM());

        if (General.role.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            taNoiDung.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    private void onClickTableDetailRow() {
        int index = tableDetail.getSelectedRow();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int selectedID;
        try {
            selectedID = Integer.parseInt(txtMaKM.getText().replace("KM", ""));
        } catch (Exception e) {
            return;
        }
        List<CT_KhuyenMai> dtoList = ctKhuyenMaiService.findByMaKM(selectedID);
        if (dtoList == null || index == -1)
            return;
        CT_KhuyenMai dto = dtoList.get(index);
        SanPham product = sanPhamService.findByID(dto.getMaSP());
        idHolderCT.setText(String.valueOf(dto.getMaCTKM()));
        txtMaKMCT.setText("KM" + dto.getMaKM());
        txtMaSP.setText("SP" + dto.getMaSP());
        txtSanPham.setText(product != null ? product.getTenSP() : "Không tìm thấy");
        txtKhuyenMai.setText(currencyVN.format(dto.getGiamGia()).replace(" ₫", "").replace(".",","));
    }

    private String[] columnHeaderDetail;
    private JScrollPane jScrollPaneDetail;
    private TableColumn tableDetail;

    private JLabel idHolderCT;
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel ctKMPanel = new JPanel();
    private final JLabel lbKhuyenMaiUnit = new JLabel();
    private final JLabel lbCTKMTitle = new JLabel();
    private final JTextField txtMaKMCT = new JTextField();
    private final JLabel lbMaKM = new JLabel();
    private final JTextField txtSanPham = new JTextField();
    private final JLabel lbSanPham = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JLabel lbMaSP = new JLabel();
    private JFormattedTextField txtKhuyenMai;
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
    private final JDateChooser txtNgayBD = new JDateChooser();
    private final JDateChooser txtNgayKT = new JDateChooser();
    private final JLabel lbNgayLap = new JLabel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JTextArea taNoiDung = new JTextArea();
    private final JPanel detailTablePanel = new JPanel();
    private final JButton btnReset1 = new JButton();
}
