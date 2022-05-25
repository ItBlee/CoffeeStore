package GUI.Form;

import BUS.Interfaces.INhanVienBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import BUS.Interfaces.ITaiKhoanBUS;
import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.SearchMapper.TaiKhoanSearchMapper;
import BUS.TaiKhoanBUS;
import DTO.Interface.IEntity;
import DTO.NhanVienDTO;
import DTO.PhanQuyenDTO;
import DTO.Role;
import DTO.TaiKhoanDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.components.TableColumn;
import Utils.General;
import Utils.Security;
import Utils.Validator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FormTaiKhoan extends JTablePanel {
    public FormTaiKhoan() {
        initComponents();
        fillTable();
        fillRoleBox();
        cbPhanQuyen.setSelectedItem("Nhân viên");
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        ArrayList<TaiKhoanDTO> list = new ArrayList<TaiKhoanDTO>();
        if (idList == null)
            list = taiKhoanBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(taiKhoanBUS.findByID(entity.getID()));

        for (TaiKhoanDTO dto: list) {
            if (dto.getMaPQ() == Role.DEFAULT_ADMIN_ROLE_ID && !General.CURRENT_ROLE.isAdmin())
                continue;
            NhanVienDTO owner = nhanVienBUS.findByTaiKhoan(dto.getMaTK());
            String ownName = owner != null ? owner.getHoTen() : "Chưa sở hữu";
            String creatorName = nhanVienBUS.findByID(dto.getNguoiTao()).getHoTen();
            String roleName = phanQuyenBUS.findByID(dto.getMaPQ()).getTenPQ();
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "TK" + dto.getMaTK(), dto.getTenDangNhap(),
                        roleName, ownName, dateFormat.format(dto.getNgayTao()), creatorName,
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "TK" + dto.getMaTK(), dto.getTenDangNhap(),
                    roleName, ownName, dateFormat.format(dto.getNgayTao()), creatorName};
            model.addRow(row);
        }
    }

    private void fillRoleBox() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ArrayList<PhanQuyenDTO> roleList = phanQuyenBUS.findAll();
        String[] nameList = new String[roleList.size()];
        for (int i = 0; i < nameList.length; i++) {
            if (roleList.get(i).getMaPQ() == Role.DEFAULT_ADMIN_ROLE_ID && !General.CURRENT_ROLE.isAdmin())
                continue;
            nameList[i] = roleList.get(i).getTenPQ();
        }
        cbPhanQuyen.setModel(new DefaultComboBoxModel<String>(nameList));
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
        btnTimKiem.setBounds(750, 20, 170, 40);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        tablePanel.add(btnReset);
        btnReset.setBounds(923, 20, 40, 40);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn() {};
        if (General.CURRENT_ROLE.isAdmin())
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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                onClickTableRow();
            }
        });

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
        btnDoiMatKhau.setEnabled(General.CURRENT_ROLE.isAdmin());
        btnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnDoiMatKhauListener();
            }
        });
        passwordPanel.add(btnDoiMatKhau);
        btnDoiMatKhau.setBounds(110, 255, 260, 40);

        btnDatLaiMatKhau.setBackground(new Color(252, 243, 215));
        btnDatLaiMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDatLaiMatKhau.setForeground(new Color(243, 170, 24));
        btnDatLaiMatKhau.setText("Đặt lại mật khẩu");
        btnDatLaiMatKhau.setBorderPainted(false);
        btnDatLaiMatKhau.setFocusPainted(false);
        btnDatLaiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnDatLaiMatKhauListener();
            }
        });
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
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equalsIgnoreCase("Thêm"))
                    onClickBtnThemListener();
                else onClickBtnKichHoatListener();
            }
        });
        infoPanel.add(btnThem);
        btnThem.setBounds(320, 160, 150, 35);

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
        btnSua.setBounds(320, 100, 150, 35);

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
        btnXoa.setBounds(320, 220, 150, 35);

        lbTenDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenDangNhap.setText("Tên đăng nhập");
        infoPanel.add(lbTenDangNhap);
        lbTenDangNhap.setBounds(31, 140, 130, 20);

        infoPanel.add(txtTenTenDangNhap);
        txtTenTenDangNhap.setBounds(30, 160, 260, 35);

        cbPhanQuyen.setModel(new DefaultComboBoxModel<String>());
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

    private TaiKhoanDTO getUserInput() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        Integer id = null;
        try {
            id = Integer.valueOf(txtMaTK.getText().replace("TK", ""));
        } catch (NumberFormatException ignored) {}
        Integer roleID = phanQuyenBUS.findByTenPhanQuyen(String.valueOf(cbPhanQuyen.getSelectedItem())).getMaPQ();

        TaiKhoanDTO dto = new TaiKhoanDTO();
        dto.setMaTK(id);
        dto.setTenDangNhap(txtTenTenDangNhap.getText());
        dto.setMaPQ(roleID);
        dto.setMatKhauHash(String.valueOf(txtNewPassword.getPassword()));
        dto.setMatKhauSalt(String.valueOf(txtNewPassword2.getPassword()));
        return dto;
    }

    private void onClickBtnThemListener() {
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            String initPassword = "nhanvien";
            String passwordSalt = Security.getSalt();
            String passwordHash = Security.applySha256(initPassword, passwordSalt);
            TaiKhoanDTO dto = getUserInput();
            dto.setMatKhauHash(passwordHash);
            dto.setMatKhauSalt(passwordSalt);
            dto.setNguoiTao(General.CURRENT_USER.getMaNV());

            if (!Validator.isValidUsername(dto.getTenDangNhap()))
                throw new Exception("Tên đăng nhập không hợp lệ.");
            if (dto.getMaPQ() == null)
                throw new Exception("Vui lòng phân quyền cho tài khoản");

            taiKhoanBUS.save(dto);
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
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            TaiKhoanDTO newDto = getUserInput();
            TaiKhoanDTO oldDto = taiKhoanBUS.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            oldDto.setTinhTrang(1);
            taiKhoanBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Kích hoạt tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Kích hoạt tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            TaiKhoanDTO newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản.");
            TaiKhoanDTO oldDto = taiKhoanBUS.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            if (!Validator.isValidUsername(newDto.getTenDangNhap()))
                throw new Exception("Tên đăng nhập không hợp lệ.");
            if (newDto.getMaPQ() == null)
                throw new Exception("Vui lòng phân quyền cho tài khoản");
            oldDto.setTenDangNhap(newDto.getTenDangNhap());
            oldDto.setMaPQ(newDto.getMaPQ());
            taiKhoanBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Sửa tài khoản thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Sửa tài khoản thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            TaiKhoanDTO userInput = getUserInput();
            if (userInput.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản.");
            TaiKhoanDTO dto = taiKhoanBUS.findByID(userInput.getMaTK());
            if (dto == null)
                throw new Exception("Không tìm thấy tài khoản.");
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                taiKhoanBUS.delete(dto.getMaTK());
            else {
                dto.setTinhTrang(0);
                taiKhoanBUS.update(dto);
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
            JFrame frame = new FrameSearch("tài khoản", new TaiKhoanSearchMapper(), FormTaiKhoan.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
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
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            TaiKhoanDTO newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản." );
            TaiKhoanDTO oldDto = taiKhoanBUS.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            if (!newDto.getMatKhauHash().equals(newDto.getMatKhauSalt()))
                throw new Exception("Mật khẩu không trùng khớp." );
            if (!Validator.isValidPassword(newDto.getMatKhauHash())
                    || !Validator.isValidPassword(newDto.getMatKhauSalt()))
                throw new Exception("Mật khẩu không hợp lệ." );
            String initPassword = newDto.getMatKhauHash();
            String passwordSalt = Security.getSalt();
            String passwordHash = Security.applySha256(initPassword, passwordSalt);
            oldDto.setMatKhauHash(passwordHash);
            oldDto.setMatKhauSalt(passwordSalt);
            taiKhoanBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đổi mật khẩu thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đổi mật khẩu thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onClickBtnDatLaiMatKhauListener() {
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        try {
            TaiKhoanDTO newDto = getUserInput();
            if (newDto.getMaTK() == null)
                throw new Exception("Vui lòng chọn tài khoản." );
            TaiKhoanDTO oldDto = taiKhoanBUS.findByID(newDto.getMaTK());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản.." );
            String initPassword = "nhanvien";
            String passwordSalt = Security.getSalt();
            String passwordHash = Security.applySha256(initPassword, passwordSalt);
            oldDto.setMatKhauHash(passwordHash);
            oldDto.setMatKhauSalt(passwordSalt);
            taiKhoanBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đặt lại mật khẩu thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormTaiKhoan.this, "Đặt lại mật khẩu thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("TK", ""));
        } catch (Exception e) {
            return;
        }
        TaiKhoanDTO dto = taiKhoanBUS.findByID(selectedID);
        if (dto == null)
            return;
        NhanVienDTO owner = nhanVienBUS.findByTaiKhoan(dto.getMaTK());
        txtMaTK.setText("TK" + dto.getMaTK());
        txtTenTenDangNhap.setText(dto.getTenDangNhap());
        txtNguoiTao.setText("NV" + dto.getNguoiTao());
        txtMaNV.setText(owner != null ? "NV" + owner.getMaNV() : "");
        txtHoTenNV.setText(owner != null ? owner.getHoTen() : "Chưa sở hữu");
        cbPhanQuyen.setSelectedItem(table.getValueAt(index, 2));

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
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
