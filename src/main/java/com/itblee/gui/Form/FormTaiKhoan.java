package com.itblee.gui.Form;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.gui.FrameSearch;
import com.itblee.gui.components.TableColumn;
import com.itblee.mapper.search.TaiKhoanSearchMapper;
import com.itblee.security.Encryptor;
import com.itblee.service.NhanVienService;
import com.itblee.service.PhanQuyenService;
import com.itblee.service.TaiKhoanService;
import com.itblee.util.ValidateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FormTaiKhoan extends JTablePanel {

    private TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);

    private PhanQuyenService phanQuyenService = Provider.get(PhanQuyenService.class);

    private NhanVienService nhanVienService = Provider.get(NhanVienService.class);

    private TaiKhoanSearchMapper taiKhoanSearchMapper = Provider.get(TaiKhoanSearchMapper.class);

    public FormTaiKhoan() {
        initComponents();
        fillTable();
        fillRoleBox();
        cbPhanQuyen.setSelectedItem("Nhân viên");
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(List<BaseEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        List<TaiKhoan> list = new ArrayList<>();
        if (idList == null)
            list = taiKhoanService.findAll();
        else
            for (BaseEntity entity:idList)
                list.add(taiKhoanService.findByID(entity.getID()));

        for (TaiKhoan dto: list) {
            if (dto.getMaPQ() == Role.DEFAULT_ADMIN_ROLE_ID && !General.role.isAdmin())
                continue;
            NhanVien owner = nhanVienService.findByTaiKhoan(dto.getMaTK());
            String ownName = owner != null ? owner.getHoTen() : "Chưa sở hữu";
            String creatorName = nhanVienService.findByID(dto.getNguoiTao()).getHoTen();
            String roleName = phanQuyenService.findByID(dto.getMaPQ()).getTenPQ();
            Object[] row;
            if (General.role.isAdmin())
                row = new Object[] { "TK" + dto.getMaTK(), dto.getTenDangNhap(),
                        roleName, ownName, dateFormat.format(dto.getNgayTao()), creatorName,
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "TK" + dto.getMaTK(), dto.getTenDangNhap(),
                    roleName, ownName, dateFormat.format(dto.getNgayTao()), creatorName};
            model.addRow(row);
        }
    }

    private void fillRoleBox() {
        List<PhanQuyen> roleList = phanQuyenService.findAll();
        String[] nameList = new String[roleList.size()];
        for (int i = 0; i < nameList.length; i++) {
            if (roleList.get(i).getMaPQ() == Role.DEFAULT_ADMIN_ROLE_ID && !General.role.isAdmin())
                continue;
            nameList[i] = roleList.get(i).getTenPQ();
        }
        cbPhanQuyen.setModel(new DefaultComboBoxModel<>(nameList));
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
        btnTimKiem.setBounds(750, 20, 170, 40);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(e -> onClickBtnResetListener());
        tablePanel.add(btnReset);
        btnReset.setBounds(923, 20, 40, 40);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn() {};
        if (General.role.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Tên đăng nhập", "Chức vụ", "Sở hữu", "Ngày tạo", "Người tạo", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Tên đăng nhập", "Chức vụ", "Sở hữu", "Ngày tạo", "Người tạo"
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

        pluginPanel.setBackground(new Color(255, 255, 255));
        pluginPanel.setLayout(null);

        passwordPanel.setBackground(new Color(255, 255, 255));
        passwordPanel.setLayout(null);
        /*passwordPanel.add(txtOldPassword);
        txtOldPassword.setBounds(110, 100, 260, 30);

        lbOldPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbOldPassword.setText("Nhập mật khẩu cũ (nếu có)");
        passwordPanel.add(lbOldPassword);
        lbOldPassword.setBounds(110, 80, 190, 20);*/

        lbNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNewPassword.setText("Nhập mật khẩu mới");
        passwordPanel.add(lbNewPassword);
        lbNewPassword.setBounds(110, 80, 190, 20);
        passwordPanel.add(txtNewPassword);
        txtNewPassword.setBounds(110, 100, 260, 35);

        lbNewPassword2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNewPassword2.setText("Nhập lại mật khẩu mới");
        passwordPanel.add(lbNewPassword2);
        lbNewPassword2.setBounds(110, 140, 190, 20);
        passwordPanel.add(txtNewPassword2);
        txtNewPassword2.setBounds(110, 160, 260, 35);

        btnDoiMatKhau.setBackground(new Color(252, 243, 215));
        btnDoiMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDoiMatKhau.setForeground(new Color(243, 170, 24));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setBorderPainted(false);
        btnDoiMatKhau.setFocusPainted(false);
        btnDoiMatKhau.setEnabled(General.role.isAdmin());
        btnDoiMatKhau.addActionListener(e -> onClickBtnDoiMatKhauListener());
        passwordPanel.add(btnDoiMatKhau);
        btnDoiMatKhau.setBounds(110, 255, 260, 40);

        btnDatLaiMatKhau.setBackground(new Color(252, 243, 215));
        btnDatLaiMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDatLaiMatKhau.setForeground(new Color(243, 170, 24));
        btnDatLaiMatKhau.setText("Đặt lại mật khẩu");
        btnDatLaiMatKhau.setBorderPainted(false);
        btnDatLaiMatKhau.setFocusPainted(false);
        btnDatLaiMatKhau.addActionListener(e -> onClickBtnDatLaiMatKhauListener());
        passwordPanel.add(btnDatLaiMatKhau);
        btnDatLaiMatKhau.setBounds(110, 300, 260, 40);

        lbPasswordTitle.setFont(new Font("Segoe UI", 1, 24));
        lbPasswordTitle.setForeground(new Color(37, 57, 111));
        lbPasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbPasswordTitle.setText("Mật khẩu");
        passwordPanel.add(lbPasswordTitle);
        lbPasswordTitle.setBounds(110, 20, 240, 40);

        pluginPanel.add(passwordPanel);
        passwordPanel.setBounds(0, 0, 470, 380);

        add(pluginPanel);
        pluginPanel.setBounds(520, 10, 470, 380);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin tài khoản");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 290, 40);

        lbMaTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaTK.setText("Mã");
        infoPanel.add(lbMaTK);
        lbMaTK.setBounds(31, 80, 70, 20);

        lbPhanQuyen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPhanQuyen.setText("Phân quyền");
        infoPanel.add(lbPhanQuyen);
        lbPhanQuyen.setBounds(31, 200, 130, 20);

        lbNguoiTao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNguoiTao.setText("Người tạo");
        infoPanel.add(lbNguoiTao);
        lbNguoiTao.setBounds(171, 80, 90, 20);

        txtMaTK.setEnabled(false);
        txtMaTK.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaTK);
        txtMaTK.setBounds(30, 100, 120, 35);

        txtNguoiTao.setEnabled(false);
        txtNguoiTao.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtNguoiTao);
        txtNguoiTao.setBounds(170, 100, 120, 35);

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
        btnThem.setBounds(320, 160, 150, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(e -> onClickBtnSuaListener());
        infoPanel.add(btnSua);
        btnSua.setBounds(320, 100, 150, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(e -> onClickBtnXoaListener());
        infoPanel.add(btnXoa);
        btnXoa.setBounds(320, 220, 150, 35);

        lbTenDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenDangNhap.setText("Tên đăng nhập");
        infoPanel.add(lbTenDangNhap);
        lbTenDangNhap.setBounds(31, 140, 130, 20);

        infoPanel.add(txtTenTenDangNhap);
        txtTenTenDangNhap.setBounds(30, 160, 260, 35);

        cbPhanQuyen.setModel(new DefaultComboBoxModel<>());
        infoPanel.add(cbPhanQuyen);
        cbPhanQuyen.setBounds(30, 220, 260, 35);

        lbMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNV.setText("Mã nhân viên");
        infoPanel.add(lbMaNV);
        lbMaNV.setBounds(31, 280, 120, 20);

        txtMaNV.setEnabled(false);
        txtMaNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNV);
        txtMaNV.setBounds(30, 300, 120, 35);

        txtHoTenNV.setEnabled(false);
        txtHoTenNV.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtHoTenNV);
        txtHoTenNV.setBounds(170, 300, 300, 35);

        lbHoTenNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHoTenNV.setText("Họ tên nhân viên");
        infoPanel.add(lbHoTenNV);
        lbHoTenNV.setBounds(171, 280, 150, 20);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);
    }

    private TaiKhoan getUserInput() {
        Integer id = null;
        try {
            id = Integer.valueOf(txtMaTK.getText().replace("TK", ""));
        } catch (NumberFormatException ignored) {}
        Integer roleID = phanQuyenService.findByTenPhanQuyen(String.valueOf(cbPhanQuyen.getSelectedItem())).getMaPQ();

        TaiKhoan dto = new TaiKhoan();
        dto.setMaTK(id);
        dto.setTenDangNhap(txtTenTenDangNhap.getText());
        dto.setMaPQ(roleID);
        dto.setMatKhauHash(String.valueOf(txtNewPassword.getPassword()));
        dto.setMatKhauSalt(String.valueOf(txtNewPassword2.getPassword()));
        return dto;
    }

    private void onClickBtnThemListener() {
        try {
            String initPassword = "nhanvien";
            String passwordSalt = Encryptor.getSalt();
            String passwordHash = Encryptor.applySha256(initPassword, passwordSalt);
            TaiKhoan dto = getUserInput();
            dto.setMatKhauHash(passwordHash);
            dto.setMatKhauSalt(passwordSalt);
            dto.setNguoiTao(General.user.getMaNV());

            if (!ValidateUtil.isValidUsername(dto.getTenDangNhap()))
                throw new Exception("Tên đăng nhập không hợp lệ.");
            if (dto.getMaPQ() == null)
                throw new Exception("Vui lòng phân quyền cho tài khoản.");
            if (phanQuyenService.findByID(dto.getMaPQ()) == null)
                throw new Exception("Không tìm thấy phân quyền này.");

            taiKhoanService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Thêm tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Thêm tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        try {
            TaiKhoan newDto = getUserInput();
            TaiKhoan oldDto = taiKhoanService.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            oldDto.setTinhTrang(1);
            taiKhoanService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Kích hoạt tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Kích hoạt tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        try {
            TaiKhoan newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản.");
            TaiKhoan oldDto = taiKhoanService.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            if (!ValidateUtil.isValidUsername(newDto.getTenDangNhap()))
                throw new Exception("Tên đăng nhập không hợp lệ.");
            if (newDto.getMaPQ() == null)
                throw new Exception("Vui lòng phân quyền cho tài khoản");
            oldDto.setTenDangNhap(newDto.getTenDangNhap());
            oldDto.setMaPQ(newDto.getMaPQ());
            taiKhoanService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Sửa tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Sửa tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        try {
            TaiKhoan userInput = getUserInput();
            if (userInput.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản.");
            TaiKhoan dto = taiKhoanService.findByID(userInput.getMaTK());
            if (dto == null)
                throw new Exception("Không tìm thấy tài khoản.");
            if (General.role.isAdmin() && dto.getTinhTrang() == 0)
                taiKhoanService.delete(dto.getMaTK());
            else {
                dto.setTinhTrang(0);
                taiKhoanService.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Xóa tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Xóa tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("tài khoản", taiKhoanSearchMapper, FormTaiKhoan.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        fillRoleBox();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        txtNewPassword.setText("");
        txtNewPassword2.setText("");
        cbPhanQuyen.setSelectedItem("Nhân viên");
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
    }

    private void onClickBtnDoiMatKhauListener() {
        try {
            TaiKhoan newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản." );
            TaiKhoan oldDto = taiKhoanService.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            if (!newDto.getMatKhauHash().equals(newDto.getMatKhauSalt()))
                throw new Exception("Mật khẩu không trùng khớp." );
            if (!ValidateUtil.isValidPassword(newDto.getMatKhauHash())
                    || !ValidateUtil.isValidPassword(newDto.getMatKhauSalt()))
                throw new Exception("Mật khẩu không hợp lệ." );
            String initPassword = newDto.getMatKhauHash();
            String passwordSalt = Encryptor.getSalt();
            String passwordHash = Encryptor.applySha256(initPassword, passwordSalt);
            oldDto.setMatKhauHash(passwordHash);
            oldDto.setMatKhauSalt(passwordSalt);
            taiKhoanService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đổi mật khẩu thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đổi mật khẩu thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onClickBtnDatLaiMatKhauListener() {
        try {
            TaiKhoan newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản." );
            TaiKhoan oldDto = taiKhoanService.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản.." );
            String initPassword = "nhanvien";
            String passwordSalt = Encryptor.getSalt();
            String passwordHash = Encryptor.applySha256(initPassword, passwordSalt);
            oldDto.setMatKhauHash(passwordHash);
            oldDto.setMatKhauSalt(passwordSalt);
            taiKhoanService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đặt lại mật khẩu thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đặt lại mật khẩu thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("TK", ""));
        } catch (Exception e) {
            return;
        }
        TaiKhoan dto = taiKhoanService.findByID(selectedID);
        if (dto == null)
            return;
        NhanVien owner = nhanVienService.findByTaiKhoan(dto.getMaTK());
        txtMaTK.setText("TK" + dto.getMaTK());
        txtTenTenDangNhap.setText(dto.getTenDangNhap());
        txtNguoiTao.setText("NV" + dto.getNguoiTao());
        txtMaNV.setText(owner != null ? "NV" + owner.getMaNV() : "");
        txtHoTenNV.setText(owner != null ? owner.getHoTen() : "Chưa sở hữu");
        cbPhanQuyen.setSelectedItem(table.getValueAt(index, 2));

        if (General.role.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa hoàn toàn");
            txtHoTenNV.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    JPanel tablePanel = new JPanel();
    JLabel lbTableTitle = new JLabel();
    JButton btnTimKiem = new JButton();
    JButton btnReset = new JButton();
    JPanel pluginPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JLabel lbDetailTitle = new JLabel();
    JLabel lbMaTK = new JLabel();
    JLabel lbPhanQuyen = new JLabel();
    JLabel lbNguoiTao = new JLabel();
    JTextField txtMaTK = new JTextField();
    JTextField txtNguoiTao = new JTextField();
    JButton btnThem = new JButton();
    JButton btnSua = new JButton();
    JButton btnXoa = new JButton();
    JTextField txtTenTenDangNhap = new JTextField();
    JLabel lbTenDangNhap = new JLabel();
    JComboBox<String> cbPhanQuyen = new JComboBox<>();
    JLabel lbMaNV = new JLabel();
    JTextField txtMaNV = new JTextField();
    JTextField txtHoTenNV = new JTextField();
    JLabel lbHoTenNV = new JLabel();
    JPanel passwordPanel = new JPanel();
    JPasswordField txtNewPassword = new JPasswordField();
    JLabel lbNewPassword = new JLabel();
    JPasswordField txtNewPassword2 = new JPasswordField();
    JLabel lbNewPassword2 = new JLabel();
    JButton btnDoiMatKhau = new JButton();
    JButton btnDatLaiMatKhau = new JButton();
    JLabel lbPasswordTitle = new JLabel();
}
