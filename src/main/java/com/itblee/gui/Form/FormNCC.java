package com.itblee.gui.Form;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.gui.FrameSearch;
import com.itblee.gui.common.MyColor;
import com.itblee.gui.components.TableColumn;
import com.itblee.mapper.search.NhaCungCapSearchMapper;
import com.itblee.service.CT_PhieuNhapService;
import com.itblee.service.NhaCungCapService;
import com.itblee.service.PhieuNhapService;
import com.itblee.service.SanPhamService;
import com.itblee.util.ValidateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormNCC extends JTablePanel {

    private final NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);

    private final PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);

    private final CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    private final NhaCungCapSearchMapper nhaCungCapSearchMapper = Provider.get(NhaCungCapSearchMapper.class);

    public FormNCC() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<BaseEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<NhaCungCap> list = new ArrayList<>();
        if (idList == null)
            list = nhaCungCapService.findAll();
        else
            for (BaseEntity entity:idList)
                list.add(nhaCungCapService.findByID(entity.getID()));

        for (NhaCungCap dto: list) {
            Object[] row;
            if (General.role.isAdmin())
                row = new Object[] { "NCC" + dto.getMaNCC(), dto.getTenNCC(), dto.getSdt(),
                        dto.getDiaChi(), dto.getSoTaiKhoan(), dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] {  "NCC" + dto.getMaNCC(), dto.getTenNCC(), dto.getSdt(), dto.getDiaChi(), dto.getSoTaiKhoan()};
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
        btnXoa.addActionListener(e -> onClickBtnXoaListener());
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

        table.getSelectionModel().addListSelectionListener(event -> onClickTableRow());
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
        progressSPInText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSPIn.add(progressSPInText);
        progressSPInText.setBounds(0, 0, 370, 30);

        progressSPInValue.setBackground(new Color(153, 255, 153));
        progressSPInValue.setLayout(null);
        progressSPIn.add(progressSPInValue);
        progressSPInValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressSPIn);
        progressSPIn.setBounds(90, 100, 370, 30);

        lbCountPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountPN.setText("Số lần");
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

        lbCountExpenses.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountExpenses.setText("Tổng Chi");
        taskPanel.add(lbCountExpenses);
        lbCountExpenses.setBounds(10, 220, 70, 30);

        progressExpenses.setLayout(null);

        progressExpensesText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressExpensesText.setHorizontalAlignment(SwingConstants.CENTER);
        progressExpenses.add(progressExpensesText);
        progressExpensesText.setBounds(0, 0, 370, 30);

        progressExpensesValue.setLayout(null);
        progressExpenses.add(progressExpensesValue);
        progressExpensesValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressExpenses);
        progressExpenses.setBounds(90, 220, 370, 30);

        progressSold.setLayout(null);

        progressSoldText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressSoldText.setHorizontalAlignment(SwingConstants.CENTER);
        progressSold.add(progressSoldText);
        progressSoldText.setBounds(0, 0, 370, 30);

        progressSoldValue.setLayout(null);
        progressSold.add(progressSoldValue);
        progressSoldValue.setBounds(0, 0, 0, 30);

        //taskPanel.add(progressSold);
        //progressSold.setBounds(90, 280, 370, 30);

        lbCountSold.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbCountSold.setText("Tiêu thụ");
        //taskPanel.add(lbCountSold);
        //lbCountSold.setBounds(10, 280, 70, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }

    private NhaCungCap getUserInput() {
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(txtMaNCC.getText().replace("NCC", ""));
        } catch (NumberFormatException ignored) {}

        NhaCungCap dto = new NhaCungCap();
        dto.setMaNCC(idNCC);
        dto.setTenNCC(txtTenNCC.getText());
        dto.setSdt(txtSDT.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setSoTaiKhoan(txtSTK.getText());
        dto.setTinhTrang(1);
        return dto;
    }

    private void onClickBtnThemListener() {
        try {
            NhaCungCap dto = getUserInput();
            if (!ValidateUtil.isValidName(dto.getTenNCC()))
                throw new Exception("Tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(dto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            nhaCungCapService.save(dto);
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
        try {
            NhaCungCap newDto = getUserInput();
            NhaCungCap oldDto = nhaCungCapService.findByID(newDto.getMaNCC());
            if (oldDto == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            oldDto.setTinhTrang(1);
            nhaCungCapService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Kích hoạt nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Kích hoạt nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        try {
            NhaCungCap newDto = getUserInput();
            if (newDto.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            if (nhaCungCapService.findByID(newDto.getMaNCC()) == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            if (!ValidateUtil.isValidName(newDto.getTenNCC()))
                throw new Exception("Tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(newDto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            nhaCungCapService.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormNCC.this, "Sửa nhà cung cấp thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormNCC.this, "Sửa nhà cung cấp thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        try {
            NhaCungCap userInput = getUserInput();
            if (userInput.getMaNCC() == null)
                throw new Exception("Vui lòng chọn nhà cung cấp.");
            NhaCungCap dto = nhaCungCapService.findByID(userInput.getMaNCC());
            if (dto == null)
                throw new Exception("Không tìm thấy nhà cung cấp." );
            if (General.role.isAdmin() && dto.getTinhTrang() == 0)
                nhaCungCapService.delete(dto.getMaNCC());
            else {
                dto.setTinhTrang(0);
                nhaCungCapService.update(dto);
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
            JFrame frame = new FrameSearch("nhà cung cấp", nhaCungCapSearchMapper, FormNCC.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
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

        progressSPInText.setText("");
        progressSPInValue.setSize(0, progressSPInValue.getHeight());
        progressPNText.setText("");
        progressPNValue.setSize(0, progressPNValue.getHeight());
        progressExpensesText.setText("");
        progressExpensesValue.setSize(0, progressExpensesValue.getHeight());
        progressSoldText.setText("");
        progressSoldValue.setSize(0, progressSoldValue.getHeight());
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("NCC", ""));
        } catch (Exception e) {
            return;
        }
        NhaCungCap dto = nhaCungCapService.findByID(selectedID);
        if (dto == null)
            return;
        txtMaNCC.setText("NCC" + dto.getMaNCC());
        txtTenNCC.setText(dto.getTenNCC());
        txtSDT.setText(dto.getSdt());
        txtDiaChi.setText(dto.getDiaChi());
        txtSTK.setText(dto.getSoTaiKhoan());

        if (General.role.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtSTK.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
        calculateWorkStat(dto.getMaNCC());
    }

    private void calculateWorkStat(int MaNCC) {
        double totalSP = 0;
        for (SanPham dto: sanPhamService.findAll()) {
            totalSP += dto.getSoLuong();
        }
        double supplySP = 0;
        for (PhieuNhap dto: phieuNhapService.findByNCC(MaNCC))
            for (CT_PhieuNhap child: ctPhieuNhapService.findByMaPN(dto.getMaPN()))
                supplySP += child.getSoLuong();
        int percentSP = 0;
        try {
            percentSP = (int) ((supplySP/totalSP)*100);
        } catch (Exception ignored) {}

        double totalPN = phieuNhapService.getTotalCount();
        double workedPN = phieuNhapService.findByNCC(MaNCC).size();
        int percentPN = 0;
        try {
            percentPN = (int) ((workedPN/totalPN)*100);
        } catch (Exception ignored) {}

        double totalExpenses = 0;
        for (PhieuNhap dto: phieuNhapService.findAll())
            totalExpenses += dto.getTongTien();
        double thisExpenses = 0;
        for (PhieuNhap dto: phieuNhapService.findByNCC(MaNCC))
            thisExpenses += dto.getTongTien();
        int percentExpenses = 0;
        try {
            percentExpenses = (int) ((thisExpenses/totalExpenses)*100);
        } catch (Exception ignored) {}
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String thisExpensesSTR = currencyVN.format(thisExpenses).replace(" ₫", "").replace(".",",") + " đ";

        progressSPInText.setText("");
        progressSPInValue.setSize(0, progressSPInValue.getHeight());
        progressPNText.setText("");
        progressPNValue.setSize(0, progressPNValue.getHeight());
        progressExpensesText.setText("");
        progressExpensesValue.setSize(0, progressExpensesValue.getHeight());
        animatedProgress("sản phẩm", percentSP, String.valueOf((int) supplySP), progressSPInText, progressSPInValue);
        animatedProgress("lần nhập", percentPN, String.valueOf((int) workedPN), progressPNText, progressPNValue);
        animatedProgress("", percentExpenses, thisExpensesSTR, progressExpensesText, progressExpensesValue);
    }

    private void animatedProgress(String target, int percent, String worked, JLabel lbText, JPanel progress) {
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
