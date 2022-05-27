package GUI.Form;

import BUS.*;
import BUS.Interfaces.*;
import BUS.SearchMapper.NhaCungCapSearchMapper;
import BUS.SearchMapper.PhieuNhapSearchMapper;
import BUS.SearchMapper.SanPhamSearchMapper;
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

public class FormPhieuNhap extends JTablePanel {
    public FormPhieuNhap() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<PhieuNhapDTO> list = new ArrayList<PhieuNhapDTO>();
        if (idList == null)
            list = phieuNhapBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(phieuNhapBUS.findByID(entity.getID()));

        for (PhieuNhapDTO dto: list) {
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "PN" + dto.getMaPN(), "NCC" + dto.getMaNCC(), "NV" + dto.getMaNV(),
                        dateFormat.format(dto.getNgayTao()), currencyVN.format(dto.getTongTien()),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "PN" + dto.getMaPN(), "NCC" + dto.getMaNCC(), "NV" + dto.getMaNV(),
                        dateFormat.format(dto.getNgayTao()), currencyVN.format(dto.getTongTien()),};
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
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();

        ArrayList<CT_PhieuNhapDTO> list = ctPhieuNhapBUS.findByMaPN(id);
        for (CT_PhieuNhapDTO dto: list) {
            Object[] row = new Object[] { "SP" + dto.getMaSP(), dto.getSoLuong(), currencyVN.format(dto.getDonGia())};
            model.addRow(row);
        }
    }
    
    private void initComponents() {
        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        setLayout(null);

        tablePanel2.setBackground(new Color(255, 255, 255));
        tablePanel2.setLayout(null);

        lbTableTitle2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle2.setForeground(new Color(37, 57, 111));
        lbTableTitle2.setText("Danh sách");
        tablePanel2.add(lbTableTitle2);
        lbTableTitle2.setBounds(30, 10, 240, 40);

        btnTimKiem2.setBackground(new Color(229, 239, 255));
        btnTimKiem2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTimKiem2.setForeground(new Color(54, 123, 245));
        btnTimKiem2.setText("Tìm kiếm");
        btnTimKiem2.setBorderPainted(false);
        btnTimKiem2.setFocusPainted(false);
        btnTimKiem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnTimKiemListener();
            }
        });
        tablePanel2.add(btnTimKiem2);
        btnTimKiem2.setBounds(410, 20, 170, 35);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        tablePanel2.add(btnReset);
        btnReset.setBounds(590, 20, 40, 35);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.CURRENT_ROLE.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Nguồn cung", "Nhân viên", "Ngày lập", "Tổng tiền", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Nguồn cung", "Nhân viên", "Ngày lập", "Tổng tiền"
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
        tablePanel2.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 630, 350);

        add(tablePanel2);
        tablePanel2.setBounds(10, 400, 650, 410);

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        detailPanel.setBackground(new Color(255, 255, 255));
        detailPanel.setLayout(null);

        lbThanhTienCTUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbThanhTienCTUnit.setText("đồng");
        detailPanel.add(lbThanhTienCTUnit);
        lbThanhTienCTUnit.setBounds(390, 100, 50, 40);

        lbPasswordTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbPasswordTitle.setForeground(new Color(37, 57, 111));
        lbPasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbPasswordTitle.setText("Chi tiết phiếu nhập");
        detailPanel.add(lbPasswordTitle);
        lbPasswordTitle.setBounds(110, 20, 270, 40);

        txtMaPNCT.setBackground(new Color(245, 245, 245));
        txtMaPNCT.setEnabled(false);
        detailPanel.add(txtMaPNCT);
        txtMaPNCT.setBounds(30, 100, 70, 35);

        lbMaPNCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaPNCT.setText("Mã PN");
        detailPanel.add(lbMaPNCT);
        lbMaPNCT.setBounds(30, 80, 70, 20);

        txtSanPham.setBackground(new Color(245, 245, 245));
        txtSanPham.setEnabled(false);
        detailPanel.add(txtSanPham);
        txtSanPham.setBounds(160, 160, 280, 35);

        lbSanPham.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSanPham.setText("Sản phẩm");
        detailPanel.add(lbSanPham);
        lbSanPham.setBounds(160, 140, 120, 20);

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
        detailPanel.add(txtSoLuong);
        txtSoLuong.setBounds(160, 220, 80, 35);

        lbSoLuong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSoLuong.setText("Số lượng");
        detailPanel.add(lbSoLuong);
        lbSoLuong.setBounds(160, 200, 80, 20);

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
        detailPanel.add(txtMaSP);
        txtMaSP.setBounds(30, 160, 70, 35);

        lbMaSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaSP.setText("Mã SP");
        detailPanel.add(lbMaSP);
        lbMaSP.setBounds(30, 140, 70, 20);

        txtThanhTienCT = new JFormattedTextField(principleFormat);
        txtThanhTienCT.setBackground(new Color(245, 245, 245));
        txtThanhTienCT.setEnabled(false);
        detailPanel.add(txtThanhTienCT);
        txtThanhTienCT.setBounds(160, 100, 280, 35);

        lbThanhTienCT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbThanhTienCT.setText("Thành tiền");
        detailPanel.add(lbThanhTienCT);
        lbThanhTienCT.setBounds(160, 80, 130, 20);

        lbDonGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDonGia.setText("Đơn giá");
        detailPanel.add(lbDonGia);
        lbDonGia.setBounds(260, 200, 100, 20);

        txtDonGia = new JFormattedTextField(principleFormat);
        txtDonGia.setBackground(new Color(245, 245, 245));
        txtDonGia.setEnabled(false);
        detailPanel.add(txtDonGia);
        txtDonGia.setBounds(260, 220, 180, 35);

        btnSelectSP.setText("jButton1");
        btnSelectSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaSPListener();
            }
        });
        detailPanel.add(btnSelectSP);
        btnSelectSP.setBounds(110, 160, 40, 35);

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
        detailPanel.add(btnThemCT);
        btnThemCT.setBounds(160, 280, 280, 40);

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
        detailPanel.add(btnSuaCT);
        btnSuaCT.setBounds(260, 330, 180, 40);

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
        detailPanel.add(btnXoaCT);
        btnXoaCT.setBounds(160, 330, 80, 40);

        idHolderCT = new JLabel();
        idHolderCT.setEnabled(false);
        idHolderCT.setFocusable(false);
        idHolderCT.setVisible(false);
        detailPanel.add(idHolderCT);
        idHolderCT.setBounds(0, 0, 20, 20);

        pluginPanel.add(detailPanel);
        detailPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbTongTienUnit.setHorizontalAlignment(SwingConstants.CENTER);
        lbTongTienUnit.setText("đồng");
        infoPanel.add(lbTongTienUnit);
        lbTongTienUnit.setBounds(380, 100, 50, 40);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin phiếu nhập");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaPN.setText("Mã");
        infoPanel.add(lbMaPN);
        lbMaPN.setBounds(80, 80, 70, 20);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Nhà cung cấp");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(80, 140, 120, 20);

        txtMaPN.setBackground(new Color(245, 245, 245));
        txtMaPN.setEnabled(false);
        infoPanel.add(txtMaPN);
        txtMaPN.setBounds(80, 100, 70, 35);

        txtMaNCC.setBackground(new Color(245, 245, 245));
        txtMaNCC.setEnabled(false);
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(80, 160, 290, 35);

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
        btnThem.setBounds(80, 280, 350, 40);

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
        btnSua.setBounds(80, 330, 170, 40);

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
        btnXoa.setBounds(270, 330, 160, 40);

        txtTotal = new JFormattedTextField(principleFormat);
        txtTotal.setBackground(new Color(245, 245, 245));
        txtTotal.setEnabled(false);
        infoPanel.add(txtTotal);
        txtTotal.setBounds(170, 100, 260, 35);

        lbTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotal.setText("Tổng tiền");
        infoPanel.add(lbTotal);
        lbTotal.setBounds(170, 80, 130, 20);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(80, 200, 120, 20);

        txtMaNV.setText("NV" + General.CURRENT_USER.getMaNV());
        txtMaNV.setBackground(new Color(245, 245, 245));
        txtMaNV.setEnabled(false);
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(80, 220, 170, 35);

        txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        txtNgayLap.setBackground(new Color(245, 245, 245));
        txtNgayLap.setEnabled(false);
        infoPanel.add(txtNgayLap);
        txtNgayLap.setBounds(270, 220, 160, 35);

        lbNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNgayLap.setText("Ngày lập");
        infoPanel.add(lbNgayLap);
        lbNgayLap.setBounds(270, 200, 150, 20);

        btnSelectNCC.setText("jButton1");
        btnSelectNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaNCCListener();
            }
        });
        infoPanel.add(btnSelectNCC);
        btnSelectNCC.setBounds(390, 160, 40, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        detailTablePanel.setBackground(new Color(255, 255, 255));
        detailTablePanel.setLayout(null);

        btnReset1.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetCTListener();
            }
        });
        detailTablePanel.add(btnReset1);
        btnReset1.setBounds(250, 20, 40, 35);

        jScrollPaneDetail = new JScrollPane();
        jScrollPaneDetail.setBackground(Color.white);
        jScrollPaneDetail.setBorder(BorderFactory.createEmptyBorder());
        jScrollPaneDetail.setFocusable(false);

        tableDetail = new TableColumn();
        columnHeaderDetail = new String [] {
                "Mã SP", "Số lượng", "Đơn giá"
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
        detailTablePanel.add(jScrollPaneDetail);
        jScrollPaneDetail.setBounds(22, 60, 280, 350);

        add(detailTablePanel);
        detailTablePanel.setBounds(670, 400, 320, 410);
    }

    private void onClickBtnSelectMaNCCListener() {
        try {
            JFrame frame = new FrameSelect("nhà cung cấp", txtMaNCC, new NhaCungCapSearchMapper(), FormNCC.class, FormPhieuNhap.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectMaSPListener() {
        try {
            JFrame frame = new FrameSelect("sản phẩm", txtMaSP, new SanPhamSearchMapper(), FormSanPham.class, FormPhieuNhap.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillSanPhamDetailListener() {
        int idSP;
        try {
            idSP = Integer.parseInt(txtMaSP.getText().replace("SP", ""));
        } catch (Exception e) {
            return;
        }
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        ICT_KhuyenMaiBUS khuyenMaiBUS = new CT_KhuyenMaiBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(idSP);
        txtSanPham.setText(sanPhamDTO != null ? sanPhamDTO.getTenSP() : "Không tìm thấy");
        Integer dg = sanPhamDTO.getDonGia() - ((sanPhamDTO.getDonGia()*20)/100);
        txtDonGia.setText(sanPhamDTO != null ?
                currencyVN.format(dg).replace(" ₫", "").replace(".",",") + "đ/" + sanPhamDTO.getDonVi()
                : currencyVN.format(dg).replace(" ₫", "").replace(".",",") + "đ");
    }

    private void calculateTotalCTListener() {
        int dg, sl;
        try {
            dg = Integer.parseInt(StringUtils.removeLetter(txtDonGia.getText()));
            sl = Integer.parseInt(StringUtils.removeLetter(txtSoLuong.getText()));
        } catch (Exception ignored) {
            return;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int total = dg * sl;
        if (total < 0)
            total = 0;
        txtThanhTienCT.setText(currencyVN.format(total).replace(" ₫", "").replace(".",","));
    }

    private PhieuNhapDTO getUserInput() {
        Integer idPN = null;
        Integer idNV = null;
        Integer idNCC = null;
        try {
            idNV = Integer.valueOf(txtMaNV.getText().replace("NV", ""));
            idNCC = Integer.valueOf(txtMaNCC.getText().replace("NCC", ""));
            idPN = Integer.valueOf(txtMaPN.getText().replace("PN", ""));
        } catch (NumberFormatException ignored) {}

        PhieuNhapDTO dto = new PhieuNhapDTO();
        dto.setMaPN(idPN);
        dto.setMaNV(idNV);
        dto.setMaNCC(idNCC);
        try {
            dto.setNgayTao(Timestamp.valueOf(txtNgayLap.getText()));
            dto.setTongTien(Integer.parseInt(txtTotal.getText().replace(",","")));
        } catch (Exception ignored) {}
        dto.setTinhTrang(1);
        return dto;
    }

    private CT_PhieuNhapDTO getUserInputCT() {
        Integer idCTPN = null;
        Integer idSP = null;
        try {
            idSP = Integer.valueOf(txtMaSP.getText().replace("SP", ""));
            idCTPN = Integer.valueOf(txtMaPN.getText().replace("PN", ""));
        } catch (NumberFormatException ignored) {}

        CT_PhieuNhapDTO dto = new CT_PhieuNhapDTO();
        dto.setMaPN(idCTPN);
        dto.setMaSP(idSP);
        try {
            dto.setDonGia(Integer.valueOf(StringUtils.removeLetter(txtDonGia.getText())));
            dto.setSoLuong(Integer.valueOf(StringUtils.removeLetter(txtSoLuong.getText())));
            dto.setThanhTien(Integer.parseInt(txtThanhTienCT.getText().replace(",","")));
            dto.setMaCTPN(Integer.valueOf(idHolderCT.getText()));
        } catch (Exception ignored) {}
        return dto;
    }

    private void onClickBtnThemListener() {
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            PhieuNhapDTO dto = getUserInput();
            if (dto.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            if (nhanVienBUS.findByID(dto.getMaNV()) == null)
                throw new Exception("Không tìm thấy nhân viên.");
            if (dto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapBUS.findByID(dto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp.");
            if (dto.getNgayTao() == null)
                dto.setNgayTao(new Timestamp(System.currentTimeMillis()));
            if (dto.getTongTien() == null || dto.getTongTien() < 0)
                dto.setTongTien(0);
            phieuNhapBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Thêm phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Thêm phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnThemCTListener() {
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            CT_PhieuNhapDTO dto = getUserInputCT();
            if (dto.getMaPN() == null)
                throw new Exception("Vui lòng chọn phiếu nhập.");
            if (phieuNhapBUS.findByID(dto.getMaPN()) == null)
                throw new Exception("Không tìm thấy phiếu nhập.");
            if (dto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamBUS.findByID(dto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (dto.getSoLuong() == null)
                throw new Exception("Vui lòng nhập số lượng.");
            if (dto.getDonGia() == null || dto.getDonGia() <= 0)
                throw new Exception("Vui lòng nhập đơn giá.");
            if (dto.getThanhTien() == null || dto.getThanhTien() < 0)
                dto.setThanhTien(0);
            ctPhieuNhapBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Thêm chi tiết phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Thêm chi tiết phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
        int newIndex = tableDetail.getRowCount()-1;
        tableDetail.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPaneDetail.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        try {
            PhieuNhapDTO newDto = getUserInput();
            PhieuNhapDTO oldDto = phieuNhapBUS.findByID(newDto.getMaPN());
            if (oldDto == null)
                throw new Exception("Không tìm thấy phiếu nhập.");
            oldDto.setTinhTrang(3);
            phieuNhapBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Kích hoạt phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Kích hoạt phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        try {
            PhieuNhapDTO newDto = getUserInput();
            if (newDto.getMaPN() == null)
                throw new Exception("Vui lòng chọn phiếu nhập.");
            PhieuNhapDTO oldDto = phieuNhapBUS.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy phiếu nhập.");
            if (newDto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapBUS.findByID(newDto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp.");
            oldDto.setMaNCC(newDto.getMaNCC());
            phieuNhapBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Sửa phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Sửa phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnSuaCTListener() {
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        try {
            CT_PhieuNhapDTO newDto = getUserInputCT();
            if (newDto.getMaCTPN() == null)
                throw new Exception("Vui lòng chọn chi tiết phiếu nhập.");
            if (newDto.getMaPN() == null)
                throw new Exception("Vui lòng chọn phiếu nhập.");
            if (phieuNhapBUS.findByID(newDto.getMaPN()) == null)
                throw new Exception("Không tìm thấy phiếu nhập.");
            if (newDto.getMaSP() == null)
                throw new Exception("Vui lòng chọn sản phẩm.");
            if (sanPhamBUS.findByID(newDto.getMaSP()) == null)
                throw new Exception("Không tìm thấy sản phẩm.");
            if (newDto.getSoLuong() == null || newDto.getSoLuong() <= 0)
                throw new Exception("Vui lòng nhập số lượng.");
            CT_PhieuNhapDTO oldDto = ctPhieuNhapBUS.findByID(newDto.getID());
            if (oldDto == null)
                throw new Exception("Không tìm thấy chi tiết phiếu nhập.");
            if (newDto.getDonGia() == null || newDto.getDonGia() <= 0)
                throw new Exception("Vui lòng nhập đơn giá.");
            if (newDto.getThanhTien() == null || newDto.getSoLuong() < 0)
                newDto.setThanhTien(0);
            oldDto = newDto;
            ctPhieuNhapBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Sửa chi tiết phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Sửa chi tiết phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        Integer idPN = null;
        try {
            idPN = Integer.valueOf(txtMaPN.getText().replace("PN", ""));
        } catch (Exception ignored) {}
        fillTableDetail(idPN);
    }

    private void onClickBtnXoaListener() {
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        try {
            PhieuNhapDTO userInput = getUserInput();
            if (userInput.getMaPN() == null)
                throw new Exception("Vui lòng chọn phiếu nhập.");
            PhieuNhapDTO dto = phieuNhapBUS.findByID(userInput.getMaPN());
            if (dto == null)
                throw new Exception("Không tìm thấy phiếu nhập.");
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                phieuNhapBUS.delete(dto.getMaPN());
            else {
                dto.setTinhTrang(2);
                phieuNhapBUS.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Xóa phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Xóa phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnXoaCTListener() {
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        try {
            CT_PhieuNhapDTO userInput = getUserInputCT();
            if (userInput.getMaCTPN() == null)
                throw new Exception("Vui lòng chọn chi tiết phiếu nhập.");
            CT_PhieuNhapDTO dto = ctPhieuNhapBUS.findByID(userInput.getMaCTPN());
            if (dto == null)
                throw new Exception("Không tìm thấy chi tiết phiếu nhập.");
            ctPhieuNhapBUS.delete(dto.getMaCTPN());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, "Xóa chi tiết phiếu nhập thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhieuNhap.this, "Xóa chi tiết phiếu nhập thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetCTListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("phiếu nhập", new PhieuNhapSearchMapper(), FormPhieuNhap.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhieuNhap.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        txtMaNV.setText("NV" + General.CURRENT_USER.getMaNV());
        txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
        onClickBtnResetCTListener();
    }

    private void onClickBtnResetCTListener() {
        Integer idPN = null;
        try {
            idPN = Integer.parseInt(txtMaPN.getText().replace("PN", ""));
        } catch (Exception ignored) {}
        fillTableDetail(idPN);
        for (Component component:detailPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        txtMaPNCT.setText("PN" + idPN);
        idHolderCT.setText("");
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("PN", ""));
        } catch (Exception e) {
            return;
        }
        PhieuNhapDTO dto = phieuNhapBUS.findByID(selectedID);
        if (dto == null)
            return;
        txtMaPN.setText("PN" + dto.getMaPN());
        txtMaPNCT.setText("PN" + dto.getMaPN());
        txtMaNCC.setText("NCC" + dto.getMaNCC());
        txtMaNV.setText("NV" + dto.getMaNV());
        txtNgayLap.setText(dateFormat.format(dto.getNgayTao()));
        txtTotal.setText(currencyVN.format(dto.getTongTien()).replace(" ₫", "").replace(".",","));
        fillTableDetail(dto.getMaPN());

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtTotal.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    private void onClickTableDetailRow() {
        int index = tableDetail.getSelectedRow();
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int selectedID;
        try {
            selectedID = Integer.parseInt(txtMaPN.getText().replace("PN", ""));
        } catch (Exception e) {
            return;
        }
        ArrayList<CT_PhieuNhapDTO> dtoList = ctPhieuNhapBUS.findByMaPN(selectedID);
        if (dtoList == null || index == -1)
            return;
        CT_PhieuNhapDTO dto = dtoList.get(index);
        SanPhamDTO product = sanPhamBUS.findByID(dto.getMaSP());
        idHolderCT.setText(String.valueOf(dto.getMaCTPN()));
        txtMaPNCT.setText("PN" + dto.getMaPN());
        txtMaSP.setText("SP" + dto.getMaSP());
        txtSanPham.setText(product != null ? product.getTenSP() : "Không tìm thấy");
        txtSoLuong.setText(String.valueOf(dto.getSoLuong()));
        txtDonGia.setText(product != null ?
                currencyVN.format(dto.getDonGia()).replace(" ₫", "").replace(".",",") + "đ/" + product.getDonVi()
                : currencyVN.format(dto.getDonGia()).replace(" ₫", "").replace(".",",") + "đ");
        txtThanhTienCT.setText(currencyVN.format(dto.getThanhTien()).replace(" ₫", "").replace(".",","));
    }

    private String[] columnHeaderDetail;
    private JScrollPane jScrollPaneDetail;
    private TableColumn tableDetail;

    private JLabel idHolderCT;
    private final JPanel tablePanel2 = new JPanel();
    private final JLabel lbTableTitle2 = new JLabel();
    private final JButton btnTimKiem2 = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel pluginPanel = new JPanel();
    private final JPanel detailPanel = new JPanel();
    private final JLabel lbThanhTienCTUnit = new JLabel();
    private final JLabel lbPasswordTitle = new JLabel();
    private final JTextField txtMaPNCT = new JTextField();
    private final JLabel lbMaPNCT = new JLabel();
    private final JTextField txtSanPham = new JTextField();
    private final JLabel lbSanPham = new JLabel();
    private final JTextField txtSoLuong = new JTextField();
    private final JLabel lbSoLuong = new JLabel();
    private final JTextField txtMaSP = new JTextField();
    private final JLabel lbMaSP = new JLabel();
    private JFormattedTextField txtThanhTienCT;
    private final JLabel lbThanhTienCT = new JLabel();
    private final JLabel lbDonGia = new JLabel();
    private JFormattedTextField txtDonGia;
    private final JButton btnSelectSP = new JButton();
    private final JButton btnThemCT = new JButton();
    private final JButton btnSuaCT = new JButton();
    private final JButton btnXoaCT = new JButton();
    private final JPanel infoPanel = new JPanel();
    private final JLabel lbTongTienUnit = new JLabel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaPN = new JLabel();
    private final JLabel lbMaNCC = new JLabel();
    private final JTextField txtMaPN = new JTextField();
    private final JTextField txtMaNCC = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private JFormattedTextField txtTotal;
    private final JLabel lbTotal = new JLabel();
    private final JLabel lbMaNV = new JLabel();
    private final JTextField txtMaNV = new JTextField();
    private final JTextField txtNgayLap = new JTextField();
    private final JLabel lbNgayLap = new JLabel();
    private final JButton btnSelectNCC = new JButton();
    private final JPanel detailTablePanel = new JPanel();
    private final JButton btnReset1 = new JButton();
}
