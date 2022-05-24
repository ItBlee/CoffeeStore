package GUI.Form;

import BUS.Interfaces.INhanVienBUS;
import BUS.Interfaces.ITaiKhoanBUS;
import BUS.NhanVienBUS;
import BUS.SearchMapper.NhanVienSearchMapper;
import BUS.SearchMapper.TaiKhoanSearchMapper;
import BUS.TaiKhoanBUS;
import DTO.Interface.IEntity;
import DTO.NhanVienDTO;
import DTO.Role;
import DTO.TaiKhoanDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.FrameSelect;
import GUI.components.TableColumn;
import Utils.General;
import Utils.Validator;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FormNhanVien extends JTablePanel {
    public FormNhanVien() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
        if (idList == null)
            list = nhanVienBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(nhanVienBUS.findByID(entity.getID()));

        for (NhanVienDTO dto: list) {
            if (dto.getMaTK() == Role.DEFAULT_ADMIN_ROLE_ID && !General.CURRENT_ROLE.isAdmin())
                continue;
            TaiKhoanDTO account = taiKhoanBUS.findByID(dto.getMaTK());
            String accountName = account != null ? account.getTenDangNhap() : "Chưa có tài khoản";
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "NV" + dto.getMaNV(), accountName, dto.getHoTen(), dateFormat.format(dto.getNgaySinh()),
                        dto.getSDT(), dto.getEmail(), dto.getGioiTinh() == 1 ? "Nam" : "Nữ", dto.getLuong(),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "NV" + dto.getMaNV(), accountName, dto.getHoTen(), dateFormat.format(dto.getNgaySinh()),
                    dto.getSDT(), dto.getEmail(), dto.getGioiTinh() == 1 ? "Nam" : "Nữ", dto.getLuong()};
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
        lbLuongUnit.setBounds(421, 280, 50, 30);

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        DecimalFormat dFormat = new DecimalFormat ("#0", dfs);
        txtLuong = new JFormattedTextField(dFormat);
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
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equalsIgnoreCase("Thêm"))
                    onClickBtnThemListener();
                else onClickBtnKichHoatListener();
            }
        });
        infoPanel.add(btnThem);
        btnThem.setBounds(230, 330, 160, 35);

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
        btnSua.setBounds(30, 330, 130, 35);

        btnSelectMaTK.setText("jButton1");
        btnSelectMaTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSelectMaTKListener();
            }
        });
        infoPanel.add(btnSelectMaTK);
        btnSelectMaTK.setBounds(160, 160, 35, 35);

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

        table = new TableColumn();
        if (General.CURRENT_ROLE.isAdmin())
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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                onClickTableRow();
            }
        });

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
        progressHDText.setForeground(new Color(47, 168, 79));
        progressHDText.setHorizontalAlignment(SwingConstants.CENTER);
        progressHDText.setText("15 hóa đơn (80%)");
        progressHD.add(progressHDText);
        progressHDText.setBounds(0, 0, 370, 30);

        progressHDValue.setBackground(new Color(153, 255, 153));
        progressHDValue.setLayout(null);
        progressHD.add(progressHDValue);
        progressHDValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressHD);
        progressHD.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Lập phiếu");
        taskPanel.add(lbCountPN);
        lbCountPN.setBounds(10, 160, 70, 30);

        progressPN.setLayout(null);

        progressPNText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPNText.setForeground(new Color(243, 170, 24));
        progressPNText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPNText.setText("8 phiếu nhập (40%)");
        progressPN.add(progressPNText);
        progressPNText.setBounds(0, 0, 370, 30);

        progressPNValue.setBackground(new Color(255, 231, 153));
        progressPNValue.setLayout(null);
        progressPN.add(progressPNValue);
        progressPNValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPN);
        progressPN.setBounds(90, 160, 370, 30);

        lbCountKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountKH.setText("Hỗ trợ");
        taskPanel.add(lbCountKH);
        lbCountKH.setBounds(10, 220, 70, 30);

        progressKH.setLayout(null);

        progressKHText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressKHText.setForeground(new Color(47, 168, 79));
        progressKHText.setHorizontalAlignment(SwingConstants.CENTER);
        progressKHText.setText("37 khách hàng (65%)");
        progressKH.add(progressKHText);
        progressKHText.setBounds(0, 0, 370, 30);

        progressKHValue.setBackground(new Color(153, 255, 153));
        progressKHValue.setLayout(null);
        progressKH.add(progressKHValue);
        progressKHValue.setBounds(0, 0, 230, 30);

        taskPanel.add(progressKH);
        progressKH.setBounds(90, 220, 370, 30);

        progressLS.setLayout(null);

        progressLSText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressLSText.setForeground(new Color(234, 61, 47));
        progressLSText.setHorizontalAlignment(SwingConstants.CENTER);
        progressLSText.setText("2 thao tác (20%)");
        progressLS.add(progressLSText);
        progressLSText.setBounds(0, 0, 370, 30);

        progressLSValue.setBackground(new Color(255, 153, 153));
        progressLSValue.setLayout(null);
        progressLS.add(progressLSValue);
        progressLSValue.setBounds(0, 0, 50, 30);

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

    private NhanVienDTO getUserInput() {
        Integer idNV = null;
        Integer idTK = null;
        try {
            idNV = Integer.valueOf(txtMaNV.getText().replace("NV", ""));
            idTK = Integer.valueOf(txtMaTK.getText().replace("TK", ""));
        } catch (NumberFormatException ignored) {}

        NhanVienDTO dto = new NhanVienDTO();
        dto.setMaNV(idNV);
        dto.setMaTK(idTK);
        dto.setHo(txtHo.getText());
        dto.setTen(txtTen.getText());
        dto.setSDT(txtSDT.getText());
        dto.setEmail(txtEmail.getText());
        dto.setGioiTinh(cbGioiTinh.getSelectedIndex() == 0 ? 1 : 0);
        try {
            dto.setNgaySinh(new java.sql.Date(txtNgaySinh.getDate().getTime()));
            dto.setLuong(Integer.valueOf(txtLuong.getText()));
        } catch (NumberFormatException ignored) {}
        return dto;
    }

    private void onClickBtnThemListener() {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        try {
            NhanVienDTO dto = getUserInput();
            if (!Validator.isValidName(dto.getHoTen()))
                throw new Exception("Họ tên không hợp lệ.");
            if (!Validator.isValidPhone(dto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!Validator.isValidEmail(dto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            if (dto.getNgaySinh() == null)
                throw new Exception("Ngày sinh không hợp lệ.");
            if (dto.getLuong() == null)
                throw new Exception("Lương không hợp lệ.");
            for (NhanVienDTO nhanVienDTO:nhanVienBUS.findAll()) {
                if (nhanVienDTO.getMaTK().equals(dto.getMaTK()) && !nhanVienDTO.getMaNV().equals(dto.getMaNV())) {
                    throw new Exception("Tài khoản đã dùng.");
                }
            }
            nhanVienBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Thêm nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Thêm nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        //jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum());
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
    }

    private void onClickBtnKichHoatListener() {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        try {
            NhanVienDTO newDto = getUserInput();
            NhanVienDTO oldDto = nhanVienBUS.findByID(newDto.getMaNV());
            if (oldDto == null)
                throw new Exception("Không tìm thấy tài khoản." );
            oldDto.setTinhTrang(1);
            nhanVienBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Kích hoạt nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Kích hoạt nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        try {
            NhanVienDTO newDto = getUserInput();
            if (newDto.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            if (nhanVienBUS.findByID(newDto.getMaNV()) == null)
                throw new Exception("Không tìm thấy nhân viên." );
            if (!Validator.isValidName(newDto.getHoTen()))
                throw new Exception("Họ tên không hợp lệ.");
            if (!Validator.isValidPhone(newDto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!Validator.isValidEmail(newDto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            if (newDto.getNgaySinh() == null)
                throw new Exception("Ngày sinh không hợp lệ.");
            if (newDto.getLuong() == null)
                throw new Exception("Lương không hợp lệ.");
            for (NhanVienDTO nhanVienDTO:nhanVienBUS.findAll()) {
                if (nhanVienDTO.getMaTK().equals(newDto.getMaTK()) && !nhanVienDTO.getMaNV().equals(newDto.getMaNV())) {
                    throw new Exception("Tài khoản đã dùng.");
                }
            }
            nhanVienBUS.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNhanVien.this, "Sửa nhân viên thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNhanVien.this, "Sửa nhân viên thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        try {
            NhanVienDTO userInput = getUserInput();
            if (userInput.getMaNV() == null)
                throw new Exception("Vui lòng chọn nhân viên.");
            NhanVienDTO dto = nhanVienBUS.findByID(userInput.getMaNV());
            if (dto == null)
                throw new Exception("Không tìm thấy nhân viên." );
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                nhanVienBUS.delete(dto.getMaTK());
            else {
                dto.setMaTK(null);
                dto.setTinhTrang(0);
                nhanVienBUS.update(dto);
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
            JFrame frame = new FrameSearch("nhân viên", new NhanVienSearchMapper(), FormNhanVien.class);
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
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("NV", ""));
        } catch (Exception e) {
            return;
        }
        NhanVienDTO dto = nhanVienBUS.findByID(selectedID);
        if (dto == null)
            return;
        txtMaNV.setText(String.valueOf(dto.getMaNV()));
        txtHo.setText(dto.getHo());
        txtTen.setText(dto.getTen());
        txtMaTK.setText(dto.getMaTK() != null ? "TK" + dto.getMaTK() : "Chưa có");
        txtSDT.setText(dto.getSDT());
        txtEmail.setText(dto.getEmail());
        txtLuong.setText(String.valueOf(dto.getLuong()));
        txtNgaySinh.setDate(dto.getNgaySinh());
        cbGioiTinh.setSelectedIndex(dto.getGioiTinh() == 1 ? 0 : 1);

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtLuong.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    JScrollPane jScrollPane;
    TableColumn table;

    JPanel infoPanel = new JPanel();
    JLabel lbDetailTitle = new JLabel();
    JDateChooser txtNgaySinh = new JDateChooser();
    JLabel lbLuong = new JLabel();
    JLabel lbMaNV = new JLabel();
    JLabel lbHo = new JLabel();
    JLabel lbTen = new JLabel();
    JLabel lbSDT = new JLabel();
    JLabel lbNgaySinh = new JLabel();
    JLabel lbGioiTinh = new JLabel();
    JLabel lbMaTK = new JLabel();
    JLabel lbEmail = new JLabel();
    JTextField txtMaNV = new JTextField();
    JTextField txtMaTK = new JTextField();
    JLabel lbLuongUnit = new JLabel();
    JFormattedTextField txtLuong;
    JTextField txtSDT = new JTextField();
    JTextField txtHo = new JTextField();
    JTextField txtTen = new JTextField();
    JTextField txtEmail = new JTextField();
    JComboBox<String> cbGioiTinh = new JComboBox<>();
    JButton btnThem = new JButton();
    JButton btnSua = new JButton();
    JButton btnSelectMaTK = new JButton();
    JButton btnXoa = new JButton();
    JPanel tablePanel = new JPanel();
    JLabel lbTableTitle = new JLabel();
    JButton btnTimKiem = new JButton();
    JButton btnReset = new JButton();
    JPanel taskPanel = new JPanel();
    JLabel lbTaskTitle = new JLabel();
    JLabel lbCountHD = new JLabel();
    JPanel progressHD = new JPanel();
    JLabel progressHDText = new JLabel();
    JPanel progressHDValue = new JPanel();
    JLabel lbCountPN = new JLabel();
    JPanel progressPN = new JPanel();
    JLabel progressPNText = new JLabel();
    JPanel progressPNValue = new JPanel();
    JLabel lbCountKH = new JLabel();
    JPanel progressKH = new JPanel();
    JLabel progressKHText = new JLabel();
    JPanel progressKHValue = new JPanel();
    JPanel progressLS = new JPanel();
    JLabel progressLSText = new JLabel();
    JPanel progressLSValue = new JPanel();
    JLabel lbCountLichSu = new JLabel();
}
