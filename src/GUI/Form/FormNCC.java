package GUI.Form;

import BUS.Interfaces.INhaCungCapBUS;
import BUS.NhaCungCapBUS;
import BUS.SearchMapper.NhaCungCapSearchMapper;
import DTO.Interface.IEntity;
import DTO.NhaCungCapDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
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
import java.util.ArrayList;

public class FormNCC extends JTablePanel {
    public FormNCC() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();

        ArrayList<NhaCungCapDTO> list = new ArrayList<NhaCungCapDTO>();
        if (idList == null)
            list = nhaCungCapBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(nhaCungCapBUS.findByID(entity.getID()));

        for (NhaCungCapDTO dto: list) {
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "NCC" + dto.getMaNCC(), dto.getTenNCC(), dto.getSDT(),
                        dto.getDiaChi(), dto.getSoTaiKhoan(), dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] {  "NCC" + dto.getMaNCC(), dto.getTenNCC(), dto.getSDT(), dto.getDiaChi(), dto.getSoTaiKhoan()};
            model.addRow(row);
        }
    }
    
    private void initComponents() {
        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin nhà cung cấp");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(121, 20, 290, 40);

        lbMaNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaNCC.setText("Mã");
        infoPanel.add(lbMaNCC);
        lbMaNCC.setBounds(31, 80, 70, 20);

        lbTenNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTenNCC.setText("Tên");
        infoPanel.add(lbTenNCC);
        lbTenNCC.setBounds(171, 80, 70, 20);

        lbSDT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSDT.setText("Số Đt");
        infoPanel.add(lbSDT);
        lbSDT.setBounds(261, 140, 70, 20);

        lbSTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSTK.setText("Số tài khoản");
        infoPanel.add(lbSTK);
        lbSTK.setBounds(31, 140, 90, 20);

        lbDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDiaChi.setText("Địa chỉ");
        infoPanel.add(lbDiaChi);
        lbDiaChi.setBounds(31, 200, 100, 20);

        txtMaNCC.setEnabled(false);
        txtMaNCC.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaNCC);
        txtMaNCC.setBounds(30, 100, 120, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(260, 160, 210, 35);
        infoPanel.add(txtSTK);
        txtSTK.setBounds(30, 160, 210, 35);
        infoPanel.add(txtDiaChi);
        txtDiaChi.setBounds(30, 220, 440, 35);
        infoPanel.add(txtTenNCC);
        txtTenNCC.setBounds(170, 100, 300, 35);

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
        btnThem.setBounds(30, 325, 210, 35);

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
        btnSua.setBounds(30, 280, 440, 40);

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
        btnXoa.setBounds(260, 325, 210, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        tablePanel.setBackground(Color.white);
        tablePanel.setLayout(null);

        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle.setForeground(new Color(37, 57, 111));
        lbTableTitle.setText("Danh sách");
        tablePanel.add(lbTableTitle);
        lbTableTitle.setBounds(22, 10, 240, 40);

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
                    "Mã", "Thương hiệu", "Số điện thoại", "Địa chỉ", "Số tài khoản", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Thương hiệu", "Số điện thoại", "Địa chỉ", "Số tài khoản", "Tình trạng"
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

        lbCoopTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbCoopTitle.setForeground(new Color(37, 57, 111));
        lbCoopTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbCoopTitle.setText("Hợp tác");
        taskPanel.add(lbCoopTitle);
        lbCoopTitle.setBounds(120, 20, 240, 40);

        lbCountSPIn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountSPIn.setText("Nhập SP");
        taskPanel.add(lbCountSPIn);
        lbCountSPIn.setBounds(10, 100, 70, 30);

        progressSPIn.setLayout(null);

        progressSPInText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressSPInText.setForeground(new Color(47, 168, 79));
        progressSPInText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSPInText.setText("120 sản phẩm (80%)");
        progressSPIn.add(progressSPInText);
        progressSPInText.setBounds(0, 0, 370, 30);

        progressSPInValue.setBackground(new Color(153, 255, 153));
        progressSPInValue.setLayout(null);

        progressSPIn.add(progressSPInValue);
        progressSPInValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressSPIn);
        progressSPIn.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Số lần");
        taskPanel.add(lbCountPN);
        lbCountPN.setBounds(10, 160, 70, 30);

        progressPN.setLayout(null);

        progressPNText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPNText.setForeground(new Color(243, 170, 24));
        progressPNText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPNText.setText("17 lần nhập (40%)");
        progressPN.add(progressPNText);
        progressPNText.setBounds(0, 0, 370, 30);

        progressPNValue.setBackground(new Color(255, 231, 153));
        progressPNValue.setLayout(null);
        progressPN.add(progressPNValue);
        progressPNValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPN);
        progressPN.setBounds(90, 160, 370, 30);

        lbCountExpenses.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountExpenses.setText("Tổng Chi");
        taskPanel.add(lbCountExpenses);
        lbCountExpenses.setBounds(10, 220, 70, 30);

        progressExpenses.setLayout(null);

        progressExpensesText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressExpensesText.setForeground(new Color(47, 168, 79));
        progressExpensesText.setHorizontalAlignment(SwingConstants.CENTER);
        progressExpensesText.setText("134.522.000 đ (65%)");
        progressExpenses.add(progressExpensesText);
        progressExpensesText.setBounds(0, 0, 370, 30);

        progressExpensesValue.setBackground(new Color(153, 255, 153));
        progressExpensesValue.setLayout(null);

        progressExpenses.add(progressExpensesValue);
        progressExpensesValue.setBounds(0, 0, 222, 30);
        taskPanel.add(progressExpenses);
        progressExpenses.setBounds(90, 220, 370, 30);

        progressSold.setLayout(null);

        progressSoldText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressSoldText.setForeground(new Color(234, 61, 47));
        progressSoldText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSoldText.setText("27 SP đã bán (20%)");
        progressSold.add(progressSoldText);
        progressSoldText.setBounds(0, 0, 370, 30);

        progressSoldValue.setBackground(new Color(255, 153, 153));
        progressSoldValue.setLayout(null);
        progressSold.add(progressSoldValue);
        progressSoldValue.setBounds(0, 0, 50, 30);

        taskPanel.add(progressSold);
        progressSold.setBounds(90, 280, 370, 30);

        lbCountSold.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountSold.setText("Tiêu thụ");
        taskPanel.add(lbCountSold);
        lbCountSold.setBounds(10, 280, 70, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
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

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("NCC", ""));
        } catch (Exception e) {
            return;
        }
        NhaCungCapDTO dto = nhaCungCapBUS.findByID(selectedID);
        if (dto == null)
            return;
        txtMaNCC.setText("NCC" + dto.getMaNCC());
        txtTenNCC.setText(dto.getTenNCC());
        txtSDT.setText(dto.getSDT());
        txtDiaChi.setText(dto.getDiaChi());
        txtSTK.setText(dto.getSoTaiKhoan());

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtSTK.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaNCC = new JLabel();
    private final JLabel lbTenNCC = new JLabel();
    private final JLabel lbSDT = new JLabel();
    private final JLabel lbSTK = new JLabel();
    private final JLabel lbDiaChi = new JLabel();
    private final JTextField txtMaNCC = new JTextField();
    private final JTextField txtSDT = new JTextField();
    private final JTextField txtSTK = new JTextField();
    private final JTextField txtDiaChi = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JTextField txtTenNCC = new JTextField();
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel taskPanel = new JPanel();
    private final JLabel lbCoopTitle = new JLabel();
    private final JLabel lbCountSPIn = new JLabel();
    private final JPanel progressSPIn = new JPanel();
    private final JLabel progressSPInText = new JLabel();
    private final JPanel progressSPInValue = new JPanel();
    private final JLabel lbCountPN = new JLabel();
    private final JPanel progressPN = new JPanel();
    private final JLabel progressPNText = new JLabel();
    private final JPanel progressPNValue = new JPanel();
    private final JLabel lbCountExpenses = new JLabel();
    private final JPanel progressExpenses = new JPanel();
    private final JLabel progressExpensesText = new JLabel();
    private final JPanel progressExpensesValue = new JPanel();
    private final JPanel progressSold = new JPanel();
    private final JLabel progressSoldText = new JLabel();
    private final JPanel progressSoldValue = new JPanel();
    private final JLabel lbCountSold = new JLabel();
}
