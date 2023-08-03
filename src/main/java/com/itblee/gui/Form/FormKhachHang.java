package com.itblee.gui.Form;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.gui.FrameSearch;
import com.itblee.gui.common.MyColor;
import com.itblee.gui.components.TableColumn;
import com.itblee.mapper.search.KhachHangSearchMapper;
import com.itblee.service.*;
import com.itblee.util.ValidateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class FormKhachHang extends JTablePanel {

    private final HoaDonService hoaDonService = Provider.get(HoaDonService.class);

    private final CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    private final LoaiSPService loaiSPService = Provider.get(LoaiSPService.class);

    private final KhachHangService khachHangService = Provider.get(KhachHangService.class);

    private final KhachHangSearchMapper khachHangSearchMapper = Provider.get(KhachHangSearchMapper.class);

    public FormKhachHang() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(List<BaseEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<KhachHang> list = new ArrayList<>();
        if (idList == null)
            list = khachHangService.findAll();
        else
            for (BaseEntity entity:idList)
                list.add(khachHangService.findByID(entity.getID()));

        for (KhachHang dto: list) {
            Object[] row;
            if (General.role.isAdmin())
                row = new Object[] { "KH" + dto.getMaKH(), dto.getHoTen(), dto.getSdt(), dto.getDiaChi(), dto.getEmail(),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "KH" + dto.getMaKH(), dto.getHoTen(), dto.getSdt(), dto.getDiaChi(), dto.getEmail() };
            model.addRow(row);
        }
    }
    
    private void initComponents() {
        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin khách hàng");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 260, 40);

        lbMaKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaKH.setText("Mã");
        infoPanel.add(lbMaKH);
        lbMaKH.setBounds(31, 80, 70, 20);

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
        lbSDT.setBounds(31, 140, 70, 20);

        lbEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbEmail.setText("Email");
        infoPanel.add(lbEmail);
        lbEmail.setBounds(231, 140, 100, 20);

        txtMaKH.setEnabled(false);
        txtMaKH.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaKH);
        txtMaKH.setBounds(30, 100, 170, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(30, 160, 170, 35);
        infoPanel.add(txtHo);
        txtHo.setBounds(230, 100, 160, 35);
        infoPanel.add(txtTen);
        txtTen.setBounds(400, 100, 70, 35);
        infoPanel.add(txtEmail);
        txtEmail.setBounds(230, 160, 240, 35);

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
        btnThem.setBounds(30, 320, 170, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(e -> onClickBtnSuaListener());
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 280, 440, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(e -> onClickBtnXoaListener());
        infoPanel.add(btnXoa);
        btnXoa.setBounds(230, 320, 240, 35);

        lbDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDiaChi.setText("Địa chỉ");
        infoPanel.add(lbDiaChi);
        lbDiaChi.setBounds(31, 200, 100, 20);
        infoPanel.add(txtDiaChi);
        txtDiaChi.setBounds(30, 220, 440, 35);

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
                    "Mã", "Họ tên", "Số điện thoại", "Địa chỉ", "Email", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Họ tên", "Số điện thoại", "Địa chỉ", "Email"
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
        lbTaskTitle.setText("Mua hàng");
        taskPanel.add(lbTaskTitle);
        lbTaskTitle.setBounds(120, 20, 240, 40);

        lbBought.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbBought.setText("Đã mua");
        taskPanel.add(lbBought);
        lbBought.setBounds(10, 100, 70, 30);

        progressBought.setLayout(null);

        progressBoughtText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressBoughtText.setHorizontalAlignment(SwingConstants.CENTER);
        progressBought.add(progressBoughtText);
        progressBoughtText.setBounds(0, 0, 370, 30);

        progressBoughtValue.setLayout(null);
        progressBought.add(progressBoughtValue);
        progressBoughtValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressBought);
        progressBought.setBounds(90, 100, 370, 30);

        lbPay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPay.setText("Đã chi");
        taskPanel.add(lbPay);
        lbPay.setBounds(10, 160, 70, 30);

        progressPay.setLayout(null);

        progressPayText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPayText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPay.add(progressPayText);
        progressPayText.setBounds(0, 0, 370, 30);

        progressPayValue.setLayout(null);
        progressPay.add(progressPayValue);
        progressPayValue.setBounds(0, 0, 0, 30);

        taskPanel.add(progressPay);
        progressPay.setBounds(90, 160, 370, 30);

        lbTime.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTime.setForeground(new Color(37, 57, 111));
        lbTime.setText("Thời gian mua:");
        taskPanel.add(lbTime);
        lbTime.setBounds(10, 220, 140, 30);

        lbFavoriteValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteValue.setForeground(new Color(155, 84, 225));
        taskPanel.add(lbFavoriteValue);
        lbFavoriteValue.setBounds(190, 280, 180, 30);

        lbFavorite.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavorite.setForeground(new Color(37, 57, 111));
        lbFavorite.setText("Sản phẩm yêu thích: ");
        taskPanel.add(lbFavorite);
        lbFavorite.setBounds(10, 280, 180, 30);

        lbTimeValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTimeValue.setForeground(new Color(155, 84, 225));
        taskPanel.add(lbTimeValue);
        lbTimeValue.setBounds(150, 220, 310, 30);

        lbFavoriteCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategory.setForeground(new Color(37, 57, 111));
        lbFavoriteCategory.setText("Danh mục yêu thích: ");
        taskPanel.add(lbFavoriteCategory);
        lbFavoriteCategory.setBounds(10, 320, 180, 30);

        lbFavoriteCategoryValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategoryValue.setForeground(new Color(155, 84, 225));
        taskPanel.add(lbFavoriteCategoryValue);
        lbFavoriteCategoryValue.setBounds(190, 320, 180, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }

    private KhachHang getUserInput() {
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(txtMaKH.getText().replace("KH", ""));
        } catch (NumberFormatException ignored) {}

        KhachHang dto = new KhachHang();
        dto.setMaKH(idNCC);
        dto.setHo(txtHo.getText());
        dto.setTen(txtTen.getText());
        dto.setSdt(txtSDT.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setEmail(txtEmail.getText());
        dto.setTinhTrang(1);
        return dto;
    }

    private void onClickBtnThemListener() {
        try {
            KhachHang dto = getUserInput();
            if (!ValidateUtil.isValidName(dto.getHoTen()))
                throw new Exception("Tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(dto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!ValidateUtil.isValidEmail(dto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            khachHangService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Thêm khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Thêm khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        try {
            KhachHang newDto = getUserInput();
            KhachHang oldDto = khachHangService.findByID(newDto.getMaKH());
            if (oldDto == null)
                throw new Exception("Không tìm thấy khách hàng." );
            oldDto.setTinhTrang(1);
            khachHangService.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Kích hoạt khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Kích hoạt khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        try {
            KhachHang newDto = getUserInput();
            if (newDto.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            if (khachHangService.findByID(newDto.getMaKH()) == null)
                throw new Exception("Không tìm thấy khách hàng." );
            if (!ValidateUtil.isValidName(newDto.getHoTen()))
                throw new Exception("Tên không hợp lệ.");
            if (!ValidateUtil.isValidPhone(newDto.getSdt()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!ValidateUtil.isValidEmail(newDto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            khachHangService.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Sửa khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Sửa khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        try {
            KhachHang userInput = getUserInput();
            if (userInput.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            KhachHang dto = khachHangService.findByID(userInput.getMaKH());
            if (dto == null)
                throw new Exception("Không tìm thấy khách hàng." );
            if (General.role.isAdmin() && dto.getTinhTrang() == 0)
                khachHangService.delete(dto.getMaKH());
            else {
                dto.setTinhTrang(0);
                khachHangService.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Xóa khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Xóa khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("khách hàng", khachHangSearchMapper, FormKhachHang.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
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
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("KH", ""));
        } catch (Exception e) {
            return;
        }
        KhachHang dto = khachHangService.findByID(selectedID);
        if (dto == null)
            return;

        txtMaKH.setText("KH" + dto.getMaKH());
        txtHo.setText(dto.getHo());
        txtTen.setText(dto.getTen());
        txtSDT.setText(dto.getSdt());
        txtEmail.setText(dto.getEmail());
        txtDiaChi.setText(dto.getDiaChi());

        if (General.role.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtDiaChi.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
        calculateWorkStat(dto.getMaKH());
    }

    private void calculateWorkStat(int MaKH) {
        double totalBought = 0;
        for (CT_HoaDon dto: ctHoaDonService.findAll())
            totalBought += dto.getSoLuong();
        double bought = 0;
        for (HoaDon dto: hoaDonService.findByKhachHang(MaKH))
            for (CT_HoaDon child : ctHoaDonService.findByMaHD(dto.getMaHD()))
                bought += child.getSoLuong();
        int percentBought = 0;
        try {
            percentBought = (int) ((bought/totalBought)*100);
        } catch (Exception ignored) {}

        double totalPay = 0;
        for (CT_HoaDon dto: ctHoaDonService.findAll())
            totalPay += dto.getThanhTien();
        double pay = 0;
        for (HoaDon dto: hoaDonService.findByKhachHang(MaKH))
            for (CT_HoaDon child : ctHoaDonService.findByMaHD(dto.getMaHD()))
                pay += child.getThanhTien();
        int percentPay = 0;
        try {
            percentPay = (int) ((pay / totalPay)*100);
        } catch (Exception ignored) {}
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String paySTR = currencyVN.format((int) pay).replace(" ₫", "").replace(".",",") + " đ";

        progressBoughtText.setText("");
        progressBoughtValue.setSize(0, progressBoughtValue.getHeight());
        progressPayText.setText("");
        progressPayValue.setSize(0, progressPayValue.getHeight());
        animatedProgress("sản phẩm", percentBought, String.valueOf((int) bought), progressBoughtText, progressBoughtValue);
        animatedProgress("", percentPay, paySTR, progressPayText, progressPayValue);

        Date min = null;
        Date max = null;
        Map<Integer, Integer> boughtMap = new HashMap<>();
        List<SanPham> boughtList = new ArrayList<>();
        for (HoaDon dto: hoaDonService.findByKhachHang(MaKH)) {
            if (min == null)
                min = new Date(dto.getNgayLap().getTime());
            else if (dto.getNgayLap().before(min))
                min = new Date(dto.getNgayLap().getTime());
            if (max == null)
                max = new Date(dto.getNgayLap().getTime());
            else if (dto.getNgayLap().after(max))
                max = new Date(dto.getNgayLap().getTime());
            for (CT_HoaDon child : ctHoaDonService.findByMaHD(dto.getMaHD()))
                boughtList.add(sanPhamService.findByID(child.getMaSP()));
        }
        for (SanPham dto:boughtList) {
            if (!boughtMap.containsKey(dto.getMaSP()))
                boughtMap.put(dto.getMaSP(), dto.getSoLuong());
            else
                boughtMap.replace(dto.getMaSP(), boughtMap.get(dto.getMaSP()) + dto.getSoLuong());
        }
        String bestSeller = "Chưa xác định";
        String bestSellerCategory = "Chưa xác định";
        Integer key = null;
        int bestSellerCount = 0;
        for (Map.Entry<Integer, Integer> entry :boughtMap.entrySet()) {
            if (entry.getValue() > bestSellerCount) {
                bestSellerCount = entry.getValue();
                key = entry.getKey();
            }
        }
        if (key != null) {
            SanPham dto = sanPhamService.findByID(key);
            bestSeller = dto.getTenSP();
            bestSellerCategory = loaiSPService.findByID(dto.getMaLoai()).getTenLoai();
        }

        lbFavoriteValue.setText(bestSeller);
        lbFavoriteCategoryValue.setText(bestSellerCategory);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String activeDate = "Chưa xác định";
        if (min != null) {
            if (min.equals(max))
                activeDate = dateFormat.format(max);
            if (min.before(max))
                activeDate = dateFormat.format(min) + " - " + dateFormat.format(max);
        }
        lbTimeValue.setText(activeDate);
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
    private final JLabel lbMaKH = new JLabel();
    private final JLabel lbHo = new JLabel();
    private final JLabel lbTen = new JLabel();
    private final JLabel lbSDT = new JLabel();
    private final JLabel lbEmail = new JLabel();
    private final JTextField txtMaKH = new JTextField();
    private final JTextField txtSDT = new JTextField();
    private final JTextField txtHo = new JTextField();
    private final JTextField txtTen = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JLabel lbDiaChi = new JLabel();
    private final JTextField txtDiaChi = new JTextField();
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel taskPanel = new JPanel();
    private final JLabel lbTaskTitle = new JLabel();
    private final JLabel lbBought = new JLabel();
    private final JPanel progressBought = new JPanel();
    private final JLabel progressBoughtText = new JLabel();
    private final JPanel progressBoughtValue = new JPanel();
    private final JLabel lbPay = new JLabel();
    private final JPanel progressPay = new JPanel();
    private final JLabel progressPayText = new JLabel();
    private final JPanel progressPayValue = new JPanel();
    private final JLabel lbTime = new JLabel();
    private final JLabel lbFavoriteValue = new JLabel();
    private final JLabel lbFavorite = new JLabel();
    private final JLabel lbTimeValue = new JLabel();
    private final JLabel lbFavoriteCategory = new JLabel();
    private final JLabel lbFavoriteCategoryValue = new JLabel();
}
