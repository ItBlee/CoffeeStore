package GUI.Form;

import BUS.Interfaces.ILoaiSPBUS;
import BUS.Interfaces.INhaCungCapBUS;
import BUS.Interfaces.ISanPhamBUS;
import BUS.LoaiSPBUS;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import BUS.SearchMapper.NhaCungCapSearchMapper;
import BUS.SearchMapper.SanPhamSearchMapper;
import DTO.Interface.IEntity;
import DTO.LoaiSPDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.FrameSelect;
import GUI.components.ChooserJDialog;
import GUI.components.TableColumn;
import Utils.General;
import Utils.StringUtils;
import Utils.Validator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FormSanPham extends JTablePanel {
    public FormSanPham() {
        initComponents();
        fillTable();
        fillTableDetail();
        fillLoaiSPBox();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        ArrayList<SanPhamDTO> list = new ArrayList<SanPhamDTO>();
        if (idList == null)
            list = sanPhamBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(sanPhamBUS.findByID(entity.getID()));

        for (SanPhamDTO dto: list) {
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "SP" + dto.getMaSP(), dto.getTenSP(), currencyVN.format(dto.getDonGia()), dto.getSoLuong(),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "SP" + dto.getMaSP(), dto.getTenSP(), currencyVN.format(dto.getDonGia()), dto.getSoLuong()};
            model.addRow(row);
        }
    }

    public void fillTableDetail() {
        DefaultTableModel model = (DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        ILoaiSPBUS iLoaiSPBUS = new LoaiSPBUS();
        ArrayList<LoaiSPDTO> list = iLoaiSPBUS.findAll();
        for (LoaiSPDTO dto: list) {
            Object[] row = new Object[] { "LSP" + dto.getMaLoai(), dto.getTenLoai()};
            model.addRow(row);
        }
    }

    public void fillLoaiSPBox() {
        int oldIndex = cbLoaiSP.getSelectedIndex();
        ILoaiSPBUS loaiSPBUS = new LoaiSPBUS();
        ArrayList<LoaiSPDTO> list = loaiSPBUS.findAll();
        String[] categories = new String[list.size()+1];
        categories[0] = "Chọn loại";
        for (int i = 1; i <= list.size(); i++)
            categories[i] = list.get(i-1).getTenLoai();
        cbLoaiSP.setModel(new DefaultComboBoxModel<String>(categories));
        cbLoaiSP.setSelectedIndex(oldIndex);
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
                    "Mã", "Tên", "Đơn giá", "Số lượng", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Tên", "Đơn giá", "Số lượng"
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
        btnThemLoaiSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnThemCTListener();
            }
        });
        CategoryPanel.add(btnThemLoaiSP);
        btnThemLoaiSP.setBounds(30, 280, 410, 35);

        btnSuaLoaiSP.setBackground(new Color(252, 243, 215));
        btnSuaLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSuaLoaiSP.setForeground(new Color(243, 170, 24));
        btnSuaLoaiSP.setText("Sửa");
        btnSuaLoaiSP.setBorderPainted(false);
        btnSuaLoaiSP.setFocusPainted(false);
        btnSuaLoaiSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSuaCTListener();
            }
        });
        CategoryPanel.add(btnSuaLoaiSP);
        btnSuaLoaiSP.setBounds(30, 330, 270, 40);

        btnXoaLoaiSP.setBackground(new Color(254, 228, 226));
        btnXoaLoaiSP.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaLoaiSP.setForeground(new Color(234, 61, 47));
        btnXoaLoaiSP.setText("Xóa");
        btnXoaLoaiSP.setBorderPainted(false);
        btnXoaLoaiSP.setFocusPainted(false);
        btnXoaLoaiSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnXoaCTListener();
            }
        });
        CategoryPanel.add(btnXoaLoaiSP);
        btnXoaLoaiSP.setBounds(310, 330, 130, 40);

        taMota.setEditable(false);
        taMota.setFocusable(false);
        taMota.setLineWrap(true);
        taMota.setWrapStyleWord(true);
        jScrollPane2.setBackground(Color.white);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setFocusable(false);
        jScrollPane2.setViewportView(taMota);

        CategoryPanel.add(jScrollPane2);
        jScrollPane2.setBounds(30, 170, 410, 96);

        pluginPanel.add(CategoryPanel);
        CategoryPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        detailSPPanel.setVisible(false);
        detailSPPanel.setBackground(new Color(255, 255, 255));
        detailSPPanel.setLayout(null);

        lbDetailSPTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailSPTitle.setForeground(new Color(37, 57, 111));
        lbDetailSPTitle.setText("Nội dung sản phẩm");
        detailSPPanel.add(lbDetailSPTitle);
        lbDetailSPTitle.setBounds(130, 20, 290, 40);

        imagePanel.setLayout(null);
        imagePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        imgFileNameHolder = new JLabel();
        imgFileNameHolder.setFocusable(false);
        imgFileNameHolder.setEnabled(false);
        imgFileNameHolder.setVisible(false);
        imagePanel.add(imgFileNameHolder);
        imgFileNameHolder.setBounds(0, 0, 20, 20);

        imgSP.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imgSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickImageListener();
            }
        });
        imagePanel.add(imgSP);
        imgSP.setBounds(0, 0, 170, 170);

        detailSPPanel.add(imagePanel);
        imagePanel.setBounds(30, 100, 170, 170);

        taMotaSP.setEditable(false);
        taMotaSP.setFocusable(false);
        taMotaSP.setLineWrap(true);
        taMotaSP.setWrapStyleWord(true);
        jScrollPane1.setBackground(Color.white);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.setFocusable(false);
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
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnLuuListener();
            }
        });
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
        lbLoaiSP.setBounds(31, 140, 80, 20);

        txtMaSP.setBackground(new Color(245, 245, 245));
        txtMaSP.setEnabled(false);
        infoPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 100, 90, 35);

        cbLoaiSP = new JComboBox<String>(new String[]{"Chọn loại"});
        cbLoaiSP.setSelectedIndex(0);
        infoPanel.add(cbLoaiSP);
        cbLoaiSP.setBounds(30, 160, 170, 35);

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
        btnThem.setBounds(310, 280, 160, 40);

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
        btnSua.setBounds(30, 280, 260, 40);

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

        infoPanel.add(txtTenSP);
        txtTenSP.setBounds(140, 100, 290, 35);

        lbTenSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenSP.setText("Tên");
        infoPanel.add(lbTenSP);
        lbTenSP.setBounds(141, 80, 80, 20);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Nhà cung cấp");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(221, 140, 120, 20);

        txtMaNCC.setBackground(new Color(245, 245, 245));
        txtMaNCC.setEnabled(false);
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(220, 160, 210, 35);

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

        txtSoLuong.setBackground(Color.white);
        infoPanel.add(txtSoLuong);
        txtSoLuong.setBounds(310, 220, 120, 35);

        /*btnSelectKH.setText("jButton1");
        infoPanel.add(btnSelectKH);
        btnSelectKH.setBounds(440, 100, 35, 35);*/

        btnSelectNCC.setText("jButton1");
        btnSelectNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaNCCListener();
            }
        });
        infoPanel.add(btnSelectNCC);
        btnSelectNCC.setBounds(440, 160, 35, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        tableLoaiSPPanel.setBackground(new Color(255, 255, 255));
        tableLoaiSPPanel.setLayout(null);

        btnResetCategory.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnResetCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetCTListener();
            }
        });
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
                onClickTableDetailRow();
            }
        });
        tableLoaiSPPanel.add(jScrollPaneDetail);
        jScrollPaneDetail.setBounds(22, 60, 280, 350);

        add(tableLoaiSPPanel);
        tableLoaiSPPanel.setBounds(670, 400, 320, 410);
    }

    private void onClickBtnSelectMaNCCListener() {
        try {
            JFrame frame = new FrameSelect("nhà cung cấp", txtMaNCC, new NhaCungCapSearchMapper(), FormNCC.class, FormSanPham.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private SanPhamDTO getUserInput() {
        ILoaiSPBUS loaiSPBUS = new LoaiSPBUS();
        Integer idSP = null;
        Integer idLoaiSP = null;
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(StringUtils.removeLetter(txtMaNCC.getText()));
            idLoaiSP = loaiSPBUS.findByTenLoai(String.valueOf(cbLoaiSP.getSelectedItem())).getMaLoai();
            idSP = Integer.valueOf(txtMaSP.getText().replace("SP", ""));
        } catch (NumberFormatException ignored) {}

        SanPhamDTO dto = new SanPhamDTO();
        dto.setMaSP(idSP);
        dto.setMaLoai(idLoaiSP);
        dto.setMaNCC(idNCC);
        dto.setTenSP(txtTenSP.getText());
        dto.setDonVi(txtDonVi.getText());
        try {
            dto.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
            dto.setDonGia(((Number) txtDonGia.getValue()).intValue());
        } catch (Exception ignored) {}
        dto.setTinhTrang(1);
        return dto;
    }

    private LoaiSPDTO getUserInputCT() {
        Integer idLoaiSP = null;
        try {
            idLoaiSP = Integer.valueOf(txtMaSP.getText().replace("LSP", ""));
        } catch (NumberFormatException ignored) {}

        LoaiSPDTO dto = new LoaiSPDTO();
        dto.setMaLoai(idLoaiSP);
        dto.setTenLoai(txtTenLoaiSP.getText());
        dto.setMoTa(taMotaSP.getText());
        return dto;
    }

    private void onClickBtnThemListener() {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        ILoaiSPBUS LoaiSPBUS = new LoaiSPBUS();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            SanPhamDTO dto = getUserInput();
            if (dto.getMaLoai() == null)
                throw new Exception("Vui lòng chọn loại sản phẩm.");
            if (LoaiSPBUS.findByID(dto.getMaLoai()) == null)
                throw new Exception("Không tìm thấy loại sản phẩm.");
            if (dto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapBUS.findByID(dto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp.");
            if (!Validator.isValidName(dto.getTenSP()))
                throw new Exception("Tên sản phẩm không hợp lệ.");
            if (dto.getSoLuong() == null || dto.getSoLuong() <= 0)
                throw new Exception("Số lượng sản phẩm không hợp lệ.");
            if (dto.getDonVi() == null)
                throw new Exception("Đơn vị sản phẩm không hợp lệ.");
            if (dto.getDonGia() == null || dto.getDonGia() <= 0)
                throw new Exception("Giá sản phẩm không hợp lệ.");
            sanPhamBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Thêm sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Thêm sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnThemCTListener() {
        ILoaiSPBUS iLoaiSPBUS = new LoaiSPBUS();
        try {
            LoaiSPDTO dto = getUserInputCT();
            if (!Validator.isValidName(dto.getTenLoai()))
                throw new Exception("Tên loại sản phẩm không hợp lệ.");
            iLoaiSPBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Thêm loại sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Thêm loại sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
        fillLoaiSPBox();
        int newIndex = tableDetail.getRowCount()-1;
        tableDetail.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPaneDetail.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            SanPhamDTO newDto = getUserInput();
            SanPhamDTO oldDto = sanPhamBUS.findByID(newDto.getMaSP());
            if (oldDto == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            oldDto.setTinhTrang(1);
            sanPhamBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Kích hoạt sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Kích hoạt sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        ILoaiSPBUS LoaiSPBUS = new LoaiSPBUS();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            SanPhamDTO newDto = getUserInput();
            if (newDto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (newDto.getMaLoai() == null)
                throw new Exception("Vui lòng chọn loại sản phẩm.");
            if (LoaiSPBUS.findByID(newDto.getMaLoai()) == null)
                throw new Exception("Không tìm thấy loại sản phẩm.");
            if (newDto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapBUS.findByID(newDto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp.");
            if (!Validator.isValidName(newDto.getTenSP()))
                throw new Exception("Tên sản phẩm không hợp lệ.");
            if (newDto.getSoLuong() == null || newDto.getSoLuong() <= 0)
                throw new Exception("Số lượng sản phẩm không hợp lệ.");
            if (newDto.getDonVi() == null)
                throw new Exception("Đơn vị sản phẩm không hợp lệ.");
            if (newDto.getDonGia() == null || newDto.getDonGia() <= 0)
                throw new Exception("Giá sản phẩm không hợp lệ.");
            SanPhamDTO oldDto = sanPhamBUS.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            oldDto = newDto;
            sanPhamBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Sửa sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Sửa sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnSuaCTListener() {
        ILoaiSPBUS iLoaiSPBUS = new LoaiSPBUS();
        try {
            LoaiSPDTO newDto = getUserInputCT();
            if (!Validator.isValidName(newDto.getTenLoai()))
                throw new Exception("Tên loại sản phẩm không hợp lệ.");
            iLoaiSPBUS.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Sửa loại sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Sửa loại sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTableDetail();
        fillLoaiSPBox();
    }

    private void onClickBtnLuuListener() {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            int id = Integer.parseInt(txtMaSP.getText().replace("SP", ""));
            SanPhamDTO oldDto = sanPhamBUS.findByID(id);
            String imgName = imgFileNameHolder.getText();
            if (imgName != null && !imgName.isEmpty())
                oldDto.setHinhAnh(imgName);
            oldDto.setMoTa(taMota.getText());
            sanPhamBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Sửa chi tiết sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Sửa chi tiết sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onClickBtnXoaListener() {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            SanPhamDTO userInput = getUserInput();
            if (userInput.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            SanPhamDTO dto = sanPhamBUS.findByID(userInput.getMaSP());
            if (dto == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                sanPhamBUS.delete(dto.getMaSP());
            else {
                dto.setTinhTrang(0);
                sanPhamBUS.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Xóa sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Xóa sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnXoaCTListener() {
        ILoaiSPBUS iLoaiSPBUS = new LoaiSPBUS();
        try {
            LoaiSPDTO userInput = getUserInputCT();
            if (userInput.getMaLoai() == null)
                throw new Exception("Vui lòng chọn loại sản phẩm.");
            LoaiSPDTO dto = iLoaiSPBUS.findByID(userInput.getMaLoai());
            if (dto == null)
                throw new Exception("Không tìm thấy loại sản phẩm.");
            iLoaiSPBUS.delete(dto.getMaLoai());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, "Xóa loại sản phẩm thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormSanPham.this, "Xóa loại sản phẩm thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
        fillLoaiSPBox();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("sản phẩm", new SanPhamSearchMapper(), FormSanPham.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormSanPham.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
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
        imgFileNameHolder.setText("");
        taMotaSP.setText("");
        imgSP.setIcon(null);
        cbLoaiSP.setSelectedIndex(0);
    }

    private void onClickBtnResetCTListener() {
        fillTableDetail();
        for (Component component:CategoryPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
    }

    private void onClickImageListener() {
        File file = ChooserJDialog.showImageChooser("bin/images/SanPham/");
        if (file != null && file.isFile()) {
            try {
                Image productImg = new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
                imgSP.setIcon(new ImageIcon(productImg));
                imgFileNameHolder.setText(file.getName());
            } catch (Exception ignored) {}
        }
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        ILoaiSPBUS loaiSPBUS = new LoaiSPBUS();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("SP", ""));
        } catch (Exception e) {
            return;
        }
        SanPhamDTO dto = sanPhamBUS.findByID(selectedID);
        if (dto == null)
            return;
        LoaiSPDTO category = loaiSPBUS.findByID(dto.getMaLoai());
        NhaCungCapDTO supplier = nhaCungCapBUS.findByID(dto.getMaNCC());
        txtMaSP.setText("SP" + dto.getMaSP());
        cbLoaiSP.setSelectedItem(category != null ? category.getTenLoai() : "Chọn lọai");
        txtMaNCC.setText(supplier != null ? "NCC" + supplier.getMaNCC() + " - " + supplier.getTenNCC() : "Không xác định");
        txtTenSP.setText(dto.getTenSP());
        txtDonGia.setText(currencyVN.format(dto.getDonGia()).replace(" ₫", "").replace(".",","));
        txtDonVi.setText(dto.getDonVi());
        txtSoLuong.setText(String.valueOf(dto.getSoLuong()));

        taMotaSP.setText(dto.getMoTa());
        taMotaSP.setCaretPosition(0);
        try {
            Image productImg = new ImageIcon("bin/images/SanPham/" + dto.getHinhAnh()).getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            imgSP.setIcon(new ImageIcon(productImg));
            imgFileNameHolder.setText(dto.getHinhAnh());
        } catch (Exception ignored) {}
        detailSPPanel.setVisible(true);
        pluginPanel.setVisible(false);

        if(category != null) {
            txtMaLoaiSP.setText("LSP" + category.getMaLoai());
            txtTenLoaiSP.setText(category.getTenLoai());
            taMota.setText(category.getMoTa());
            taMota.setCaretPosition(0);
        }

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtSoLuong.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }

        onClickBtnResetCTListener();
    }

    private void onClickTableDetailRow() {
        int index = tableDetail.getSelectedRow();
        ILoaiSPBUS iLoaiSPBUS = new LoaiSPBUS();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) tableDetail.getValueAt(index, 0)).replace("LSP", ""));
        } catch (Exception e) {
            return;
        }
        LoaiSPDTO dto = iLoaiSPBUS.findByID(selectedID);
        if (dto == null)
            return;
        txtMaLoaiSP.setText("LSP" + dto.getMaLoai());
        txtTenLoaiSP.setText(dto.getTenLoai());
        taMota.setText(dto.getMoTa());
        taMota.setCaretPosition(0);
        detailSPPanel.setVisible(false);
        pluginPanel.setVisible(true);

        int DEFAULT_LSP_ID = 2;
        btnXoaLoaiSP.setEnabled(!(dto.getMaLoai() == DEFAULT_LSP_ID));

        onClickBtnResetListener();
    }

    private String[] columnHeaderDetail;
    private JScrollPane jScrollPaneDetail;
    private TableColumn tableDetail;

    private JLabel imgFileNameHolder;
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
    private JComboBox<String> cbLoaiSP;
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
    private final JButton btnSelectNCC = new JButton();
    private final JPanel tableLoaiSPPanel = new JPanel();
    private final JButton btnResetCategory = new JButton();
    private final JLabel lbCategoryTableTitle = new JLabel();
}
