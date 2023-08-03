package com.itblee.gui.Form;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.gui.FrameSearch;
import com.itblee.gui.FrameSelect;
import com.itblee.gui.common.MyColor;
import com.itblee.gui.components.TableColumn;
import com.itblee.mapper.search.NhanVienSearchMapper;
import com.itblee.mapper.search.TaiKhoanSearchMapper;
import com.itblee.service.*;
import com.itblee.util.ValidateUtil;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class FormNhanVien extends JTablePanel {

    private final HoaDonService hoaDonService = Provider.get(HoaDonService.class);

    private final PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);

    private final KhachHangService khachHangService = Provider.get(KhachHangService.class);

    private final LichSuService lichSuService = Provider.get(LichSuService.class);

    private final TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);

    private final NhanVienService nhanVienService = Provider.get(NhanVienService.class);

    private final TaiKhoanSearchMapper taiKhoanSearchMapper = Provider.get(TaiKhoanSearchMapper.class);

    private final NhanVienSearchMapper nhanVienSearchMapper = Provider.get(NhanVienSearchMapper.class);

    public FormNhanVien() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<BaseEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        List<NhanVien> list = new ArrayList<>();
        if (idList == null)
            list = nhanVienService.findAll();
        else
            for (BaseEntity entity:idList)
                list.add(nhanVienService.findByID(entity.getID()));

        for (NhanVien dto: list) {
            if (dto.getMaTK() == Role.DEFAULT_ADMIN_ROLE_ID && !General.role.isAdmin())
                continue;
            TaiKhoan account = taiKhoanService.findByID(dto.getMaTK());
            String accountName = account != null ? account.getTenDangNhap() : "Chưa có";
            Object[] row;
            if (General.role.isAdmin())
                row = new Object[] { "NV" + dto.getMaNV(), accountName, dto.getHoTen(), dateFormat.format(dto.getNgaySinh()),
                        dto.getSdt(), dto.getEmail(), dto.getGioiTinh() == 1 ? "Nam" : "Nữ", currencyVN.format(dto.getLuong()),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "NV" + dto.getMaNV(), accountName, dto.getHoTen(), dateFormat.format(dto.getNgaySinh()),
                    dto.getSdt(), dto.getEmail(), dto.getGioiTinh() == 1 ? "Nam" : "Nữ", currencyVN.format(dto.getLuong())};
            model.addRow(row);
        }
    }

    private void initComponents() {
        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin nhân viên");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(131, 20, 240, 40);

        txtNgaySinh.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        txtNgaySinh.setDateFormatString("dd/MM/yyyy");
        infoPanel.add(txtNgaySinh);
        txtNgaySinh.setBounds(30, 220, 152, 35);

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
        lbLuongUnit.setBounds(421, 282, 50, 30);

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        txtLuong = new JFormattedTextField(principleFormat);
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
        cbGioiTinh.setBounds(30, 280, 130, 35);

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
        btnThem.setBounds(230, 330, 160, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(e -> onClickBtnSuaListener());
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 330, 130, 35);

        btnSelectMaTK.setText("jButton1");
        btnSelectMaTK.addActionListener(e -> onClickBtnSelectMaTKListener());
        infoPanel.add(btnSelectMaTK);
        btnSelectMaTK.setBounds(160, 160, 35, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(e -> onClickBtnXoaListener());
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
        btnTimKiem.addActionListener(e -> onClickBtnTimKiemListener());
        tablePanel.add(btnTimKiem);
        btnTimKiem.setBounds(750, 20, 170, 40);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(e -> onClickBtnResetListener());
        tablePanel.add(btnReset);
        btnReset.setBounds(923, 20, 40, 40);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.role.isAdmin())
            columnHeader = new String [] {
                "Mã", "Tài khoản", "Họ tên", "Ngày sinh", "Số điện thoại", "Email", "Giới tính", "Lương", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Tài khoản", "Họ tên", "Ngày sinh", "Số điện thoại", "Email", "Giới tính", "Lương"
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
        progressHDText.setHorizontalAlignment(SwingConstants.CENTER);
        progressHD.add(progressHDText);
        progressHDText.setBounds(0, 0, 370, 30);

        progressHDValue.setLayout(null);
        progressHD.add(progressHDValue);
        progressHDValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressHD);
        progressHD.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Lập phiếu");
        taskPanel.add(lbCountPN);
        lbCountPN.setBounds(10, 160, 70, 30);

        progressPN.setLayout(null);

        progressPNText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPNText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPN.add(progressPNText);
        progressPNText.setBounds(0, 0, 370, 30);

        progressPNValue.setLayout(null);
        progressPN.add(progressPNValue);
        progressPNValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressPN);
        progressPN.setBounds(90, 160, 370, 30);

        lbCountKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountKH.setText("Hỗ trợ");
        taskPanel.add(lbCountKH);
        lbCountKH.setBounds(10, 220, 70, 30);

        progressKH.setLayout(null);

        progressKHText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressKHText.setHorizontalAlignment(SwingConstants.CENTER);
        progressKH.add(progressKHText);
        progressKHText.setBounds(0, 0, 370, 30);

        progressKHValue.setLayout(null);
        progressKH.add(progressKHValue);
        progressKHValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressKH);
        progressKH.setBounds(90, 220, 370, 30);

        progressLS.setLayout(null);

        progressLSText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressLSText.setHorizontalAlignment(SwingConstants.CENTER);
        progressLS.add(progressLSText);
        progressLSText.setBounds(0, 0, 370, 30);

        progressLSValue.setLayout(null);
        progressLS.add(progressLSValue);
        progressLSValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressLS);
        progressLS.setBounds(90, 280, 370, 30);

        lbCountLichSu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountLichSu.setText("Thao tác");
        taskPanel.add(lbCountLichSu);
        lbCountLichSu.setBounds(10, 280, 70, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }

    private void onClickBtnSelectMaTKListener() {
        try {
            JFrame frame = new FrameSelect("tài khoản", txtMaTK, taiKhoanSearchMapper, FormTaiKhoan.class, FormNhanVien.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private NhanVien getUserInput() {
        Integer idNV = null;
        Integer idTK = null;
        try {
            idTK = Integer.valueOf(txtMaTK.getText().replace("TK", ""));
            idNV = Integer.valueOf(txtMaNV.getText().replace("NV", ""));
        } catch (NumberFormatException ignored) {}

        NhanVien dto = new NhanVien();
        dto.setMaNV(idNV);
        dto.setMaTK(idTK);
        dto.setHo(txtHo.getText());
        dto.setTen(txtTen.getText());
        dto.setSdt(txtSDT.getText());
        dto.setEmail(txtEmail.getText());
        dto.setGioiTinh(cbGioiTinh.getSelectedIndex() == 0 ? 1 : 0);
        dto.setTinhTrang(1);
        try {
            dto.setNgaySinh(new java.sql.Date(txtNgaySinh.getDate().getTime()));
            dto.setLuong(((Number) txtLuong.getValue()).intValue());
        } catch (Exception ignored) {}
        return dto;
    }

    private void onClickBtnThemListener() {
        try {
            NhanVien dto = getUserInput();
            if (!ValidateUtil.isValidName(dto.getHoTen()))
                throw new Exception("Họ tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(dto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!ValidateUtil.isValidEmail(dto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            if (!ValidateUtil.isValidBirthday(dto.getNgaySinh()))
                throw new Exception("Ngày sinh không hợp lệ.");
            if (dto.getLuong() == null || dto.getLuong() <=0)
                throw new Exception("Lương không hợp lệ.");
            for (NhanVien nhanVien : nhanVienService.findAll()) {
                if (nhanVien.getMaTK().equals(dto.getMaTK()) && !nhanVien.getMaNV().equals(dto.getMaNV())) {
                    throw new Exception("Tài khoản đã dùng.");
                }
            }
            nhanVienService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Thêm nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Thêm nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        try {
            NhanVien newDto = getUserInput();
            NhanVien oldDto = nhanVienService.findByID(newDto.getMaNV());
            if (oldDto == null)
                throw new Exception("Không tìm thấy nhân viên." );
            if (oldDto.getMaTK() == 0)
                oldDto.setMaTK(null);
            oldDto.setTinhTrang(1);
            nhanVienService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Kích hoạt nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Kích hoạt nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        try {
            NhanVien newDto = getUserInput();
            if (newDto.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            if (nhanVienService.findByID(newDto.getMaNV()) == null)
                throw new Exception("Không tìm thấy nhân viên." );
            if (!ValidateUtil.isValidName(newDto.getHoTen()))
                throw new Exception("Họ tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(newDto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!ValidateUtil.isValidEmail(newDto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            if (!ValidateUtil.isValidBirthday(newDto.getNgaySinh()))
                throw new Exception("Ngày sinh không hợp lệ.");
            if (newDto.getLuong() == null || newDto.getLuong() <=0)
                throw new Exception("Lương không hợp lệ.");
            if (newDto.getMaTK() != null) {
                for (NhanVien nhanVien : nhanVienService.findAll()) {
                    if (nhanVien.getMaTK().equals(newDto.getMaTK()) && !nhanVien.getMaNV().equals(newDto.getMaNV())) {
                        throw new Exception("Tài khoản đã dùng.");
                    }
                }
            }
            if (newDto.getMaTK() != null && newDto.getMaTK() == 0)
                newDto.setMaTK(null);
            nhanVienService.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Sửa nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Sửa nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        try {
            NhanVien userInput = getUserInput();
            if (userInput.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            NhanVien dto = nhanVienService.findByID(userInput.getMaNV());
            if (dto == null)
                throw new Exception("Không tìm thấy nhân viên." );
            if (General.role.isAdmin() && dto.getTinhTrang() == 0)
                nhanVienService.delete(dto.getMaNV());
            else {
                dto.setMaTK(null);
                dto.setTinhTrang(0);
                nhanVienService.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Xóa nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Xóa nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("nhân viên", nhanVienSearchMapper, FormNhanVien.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        txtNgaySinh.setCalendar(null);
        cbGioiTinh.setSelectedIndex(0);
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");

        progressHDText.setText("");
        progressHDValue.setSize(0, progressHDValue.getHeight());
        progressPNText.setText("");
        progressPNValue.setSize(0, progressPNValue.getHeight());
        progressKHText.setText("");
        progressKHValue.setSize(0, progressKHValue.getHeight());
        progressLSText.setText("");
        progressLSValue.setSize(0, progressLSValue.getHeight());
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("NV", ""));
        } catch (Exception e) {
            return;
        }
        NhanVien dto = nhanVienService.findByID(selectedID);
        if (dto == null)
            return;
        txtMaNV.setText(String.valueOf(dto.getMaNV()));
        txtHo.setText(dto.getHo());
        txtTen.setText(dto.getTen());
        txtMaTK.setText(dto.getMaTK() != 0 ? "TK" + dto.getMaTK() : "Chưa có");
        txtSDT.setText(dto.getSdt());
        txtEmail.setText(dto.getEmail());
        txtLuong.setText(currencyVN.format(dto.getLuong()).replace(" ₫", "").replace(".",","));
        txtNgaySinh.setDate(dto.getNgaySinh());
        cbGioiTinh.setSelectedIndex(dto.getGioiTinh() == 1 ? 0 : 1);

        if (General.role.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtLuong.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
        calculateWorkStat(dto.getMaNV());
    }

    private void calculateWorkStat(int MaNV) {
        double totalHD = hoaDonService.getTotalCount();
        double workedHD = hoaDonService.findByNhanVien(MaNV).size();
        int percentHD = 0;
        try {
            percentHD = (int) ((workedHD/totalHD)*100);
        } catch (Exception ignored) {}

        double totalPN = phieuNhapService.getTotalCount();
        double workedPN = phieuNhapService.findByNhanVien(MaNV).size();
        int percentPN = 0;
        try {
            percentPN = (int) ((workedPN/totalPN)*100);
        } catch (Exception ignored) {}

        Set<Integer> supported = new HashSet<>();
        for (HoaDon dto: hoaDonService.findByNhanVien(MaNV))
            supported.add(dto.getMaKH());
        double totalKH = khachHangService.getTotalCount();
        double supportedKH = supported.size();
        int percentKH = 0;
        try {
            percentKH = (int) ((supportedKH/totalKH)*100);
        } catch (Exception ignored) {}

        double totalLS = lichSuService.getTotalCount();
        double workedLS = lichSuService.findByNguoiThucHien(MaNV).size();
        int percentLS = 0;
        try {
            percentLS = (int) ((workedLS/totalLS)*100);
        } catch (Exception ignored) {}

        progressHDText.setText("");
        progressHDValue.setSize(0, progressHDValue.getHeight());
        progressPNText.setText("");
        progressPNValue.setSize(0, progressPNValue.getHeight());
        progressKHText.setText("");
        progressKHValue.setSize(0, progressKHValue.getHeight());
        progressLSText.setText("");
        progressLSValue.setSize(0, progressLSValue.getHeight());
        animatedProgress("hóa đơn", percentHD, (int) workedHD, progressHDText, progressHDValue);
        animatedProgress("phiếu nhập", percentPN, (int) workedPN, progressPNText, progressPNValue);
        animatedProgress("khách hàng", percentKH, (int) supportedKH, progressKHText, progressKHValue);
        animatedProgress("thao tác", percentLS, (int) workedLS, progressLSText, progressLSValue);
    }

    private void animatedProgress(String target, int percent, int worked, JLabel lbText, JPanel progress) {
        int lowState = 20;
        int mediumState = 50;
        if (percent < lowState) {
            lbText.setForeground(MyColor.RED);
            progress.setBackground(new Color(255, 153, 153));
        } else if (percent < mediumState) {
            lbText.setForeground(MyColor.ORANGE);
            progress.setBackground(new Color(255, 231, 153));
        } else {
            lbText.setForeground(MyColor.GREEN);
            progress.setBackground(new Color(153, 255, 153));
        }
        lbText.setText(worked + " " + target + " (" + percent + "%)");
        progress.setSize(4 * percent, progress.getHeight());
    }

    private JScrollPane jScrollPane;
    private TableColumn table;

    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JDateChooser txtNgaySinh = new JDateChooser();
    private final JLabel lbLuong = new JLabel();
    private final JLabel lbMaNV = new JLabel();
    private final JLabel lbHo = new JLabel();
    private final JLabel lbTen = new JLabel();
    private final JLabel lbSDT = new JLabel();
    private final JLabel lbNgaySinh = new JLabel();
    private final JLabel lbGioiTinh = new JLabel();
    private final JLabel lbMaTK = new JLabel();
    private final JLabel lbEmail = new JLabel();
    private final JTextField txtMaNV = new JTextField();
    private final JTextField txtMaTK = new JTextField();
    private final JLabel lbLuongUnit = new JLabel();
    private JFormattedTextField txtLuong;
    private final JTextField txtSDT = new JTextField();
    private final JTextField txtHo = new JTextField();
    private final JTextField txtTen = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JComboBox<String> cbGioiTinh = new JComboBox<>();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnSelectMaTK = new JButton();
    private final JButton btnXoa = new JButton();
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel taskPanel = new JPanel();
    private final JLabel lbTaskTitle = new JLabel();
    private final JLabel lbCountHD = new JLabel();
    private final JPanel progressHD = new JPanel();
    private final JLabel progressHDText = new JLabel();
    private final JPanel progressHDValue = new JPanel();
    private final JLabel lbCountPN = new JLabel();
    private final JPanel progressPN = new JPanel();
    private final JLabel progressPNText = new JLabel();
    private final JPanel progressPNValue = new JPanel();
    private final JLabel lbCountKH = new JLabel();
    private final JPanel progressKH = new JPanel();
    private final JLabel progressKHText = new JLabel();
    private final JPanel progressKHValue = new JPanel();
    private final JPanel progressLS = new JPanel();
    private final JLabel progressLSText = new JLabel();
    private final JPanel progressLSValue = new JPanel();
    private final JLabel lbCountLichSu = new JLabel();
}
