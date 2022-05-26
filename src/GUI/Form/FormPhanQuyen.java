package GUI.Form;

import BUS.CT_PhanQuyenBUS;
import BUS.Interfaces.ICT_PhanQuyenBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import BUS.Interfaces.ITaiKhoanBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.CT_PhanQuyenDTO;
import DTO.PhanQuyenDTO;
import DTO.Role;
import DTO.TaiKhoanDTO;
import GUI.components.IEventSwitchSelected;
import GUI.common.MyColor;
import GUI.components.SwitchJButton;
import Utils.Validator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class FormPhanQuyen extends JPanel {
    public FormPhanQuyen() {
        initComponents();
        initRoleList();
        cbRole.setSelectedIndex(0);
    }

    private void initRoleList() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ArrayList<PhanQuyenDTO> roleList = phanQuyenBUS.findAll();
        String[] nameList = new String[roleList.size()+1];
        nameList[0] = "Chọn quyền";
        for (int i = 0; i < roleList.size(); i++)
            nameList[i+1] = "PQ" + roleList.get(i).getMaPQ() + " - " + roleList.get(i).getTenPQ();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nameList);
        cbRole.setModel(model);
    }
    
    private void initComponents() {
        cbRole = new JComboBox<>();
        JLabel lbSP = new JLabel();
        JLabel lbPN = new JLabel();
        JLabel lbNCC = new JLabel();
        JLabel lbKH = new JLabel();
        JLabel lbKM = new JLabel();
        JLabel lbTK = new JLabel();
        JLabel lbExcel = new JLabel();
        JLabel lbNV = new JLabel();
        JLabel lbHD = new JLabel();
        JPanel decorateFunctionPanel1 = new JPanel();
        JPanel decorateFunctionPanel2 = new JPanel();
        JPanel decorateFunctionPanel3 = new JPanel();
        JPanel decorateFunctionPanel4 = new JPanel();
        JPanel decorateFunctionPanel5 = new JPanel();
        JLabel lbSelectFunction = new JLabel();
        JButton btnReset = new JButton();
        JLabel lbTenPQ = new JLabel();
        JLabel lbMaPQ = new JLabel();
        JLabel lbStatus = new JLabel();
        JLabel lbSelectRole = new JLabel();
        JButton btnRenew = new JButton();

        setLayout(null);

        cbRole.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                onSelectedRoleListener();
            }
        });
        add(cbRole);
        cbRole.setBounds(30, 50, 350, 40);

        functionPanel.setBackground(new Color(255, 255, 255));
        functionPanel.setLayout(null);

        switchJButtonSP = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonSP.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemSP.setEnabled(selected);
                btnSuaSP.setEnabled(selected);
                btnXoaSP.setEnabled(selected);
                btnThemSP.setSelected(selected);
                btnSuaSP.setSelected(selected);
                btnXoaSP.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonSP);
        switchJButtonSP.setBounds(30, 130, 40, 20);

        lbSP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSP.setText("Quản lý Sản phẩm");
        functionPanel.add(lbSP);
        lbSP.setBounds(90, 130, 160, 24);

        switchJButtonPN = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonPN.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemPN.setEnabled(selected);
                btnSuaPN.setEnabled(selected);
                btnXoaPN.setEnabled(selected);
                btnThemPN.setSelected(selected);
                btnSuaPN.setSelected(selected);
                btnXoaPN.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonPN);
        switchJButtonPN.setBounds(30, 90, 40, 20);

        lbPN.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPN.setText("Quản lý Phiếu nhập");
        functionPanel.add(lbPN);
        lbPN.setBounds(90, 90, 160, 24);

        switchJButtonNCC = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonNCC.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemNCC.setEnabled(selected);
                btnSuaNCC.setEnabled(selected);
                btnXoaNCC.setEnabled(selected);
                btnThemNCC.setSelected(selected);
                btnSuaNCC.setSelected(selected);
                btnXoaNCC.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonNCC);
        switchJButtonNCC.setBounds(30, 170, 40, 20);

        lbNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNCC.setText("Quản lý Nguồn cung");
        functionPanel.add(lbNCC);
        lbNCC.setBounds(90, 170, 160, 24);

        switchJButtonKH = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonKH.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemKH.setEnabled(selected);
                btnSuaKH.setEnabled(selected);
                btnXoaKH.setEnabled(selected);
                btnThemKH.setSelected(selected);
                btnSuaKH.setSelected(selected);
                btnXoaKH.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonKH);
        switchJButtonKH.setBounds(30, 210, 40, 20);

        lbKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKH.setText("Quản lý Khách hàng");
        functionPanel.add(lbKH);
        lbKH.setBounds(90, 210, 160, 24);

        switchJButtonKM = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonKM.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemKM.setEnabled(selected);
                btnSuaKM.setEnabled(selected);
                btnXoaKM.setEnabled(selected);
                btnThemKM.setSelected(selected);
                btnSuaKM.setSelected(selected);
                btnXoaKM.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonKM);
        switchJButtonKM.setBounds(30, 250, 40, 20);

        lbKM.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbKM.setText("Quản lý Khuyến mãi");
        functionPanel.add(lbKM);
        lbKM.setBounds(90, 250, 160, 24);

        switchJButtonTK = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonTK.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemTK.setEnabled(selected);
                btnSuaTK.setEnabled(selected);
                btnXoaTK.setEnabled(selected);
                btnThemTK.setSelected(selected);
                btnSuaTK.setSelected(selected);
                btnXoaTK.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonTK);
        switchJButtonTK.setBounds(30, 290, 40, 20);

        lbTK.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTK.setText("Quản lý Thống kê");
        functionPanel.add(lbTK);
        lbTK.setBounds(90, 290, 160, 24);

        switchJButtonExcel = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonExcel.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemExcel.setEnabled(selected);
                btnSuaExcel.setEnabled(selected);
                btnXoaExcel.setEnabled(selected);
                btnThemExcel.setSelected(selected);
                btnSuaExcel.setSelected(selected);
                btnXoaExcel.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonExcel);
        switchJButtonExcel.setBounds(30, 330, 40, 20);

        lbExcel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbExcel.setText("Quản lý Tập tin");
        functionPanel.add(lbExcel);
        lbExcel.setBounds(90, 330, 160, 24);

        switchJButtonNV = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonNV.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemNV.setEnabled(selected);
                btnSuaNV.setEnabled(selected);
                btnXoaNV.setEnabled(selected);
                btnThemNV.setSelected(selected);
                btnSuaNV.setSelected(selected);
                btnXoaNV.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonNV);
        switchJButtonNV.setBounds(30, 370, 40, 20);

        lbNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbNV.setText("Quản lý Nhân sự");
        functionPanel.add(lbNV);
        lbNV.setBounds(90, 370, 160, 24);

        switchJButtonHD = new SwitchJButton(new Color(47, 168, 79));
        switchJButtonHD.addEventSelected(new IEventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                btnThemHD.setEnabled(selected);
                btnSuaHD.setEnabled(selected);
                btnXoaHD.setEnabled(selected);
                btnThemHD.setSelected(selected);
                btnSuaHD.setSelected(selected);
                btnXoaHD.setSelected(selected);
            }
        });
        functionPanel.add(switchJButtonHD);
        switchJButtonHD.setBounds(30, 50, 40, 20);

        lbHD.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHD.setText("Quản lý Hóa đơn");
        functionPanel.add(lbHD);
        lbHD.setBounds(90, 50, 160, 20);

        btnXoaHD.setForeground(new Color(234, 61, 47));
        btnXoaHD.setText("Xóa");
        functionPanel.add(btnXoaHD);
        btnXoaHD.setBounds(510, 50, 60, 20);

        btnThemHD.setForeground(new Color(47, 168, 79));
        btnThemHD.setText("Thêm");
        functionPanel.add(btnThemHD);
        btnThemHD.setBounds(350, 50, 60, 20);

        btnSuaHD.setForeground(new Color(243, 170, 24));
        btnSuaHD.setText("Sửa");
        functionPanel.add(btnSuaHD);
        btnSuaHD.setBounds(430, 50, 60, 20);

        btnThemPN.setForeground(new Color(47, 168, 79));
        btnThemPN.setText("Thêm");
        functionPanel.add(btnThemPN);
        btnThemPN.setBounds(350, 90, 60, 20);

        btnSuaPN.setForeground(new Color(243, 170, 24));
        btnSuaPN.setText("Sửa");
        functionPanel.add(btnSuaPN);
        btnSuaPN.setBounds(430, 90, 60, 20);

        btnXoaPN.setForeground(new Color(234, 61, 47));
        btnXoaPN.setText("Xóa");
        functionPanel.add(btnXoaPN);
        btnXoaPN.setBounds(510, 90, 60, 20);

        btnThemSP.setForeground(new Color(47, 168, 79));
        btnThemSP.setText("Thêm");
        functionPanel.add(btnThemSP);
        btnThemSP.setBounds(350, 130, 60, 20);

        btnSuaSP.setForeground(new Color(243, 170, 24));
        btnSuaSP.setText("Sửa");
        functionPanel.add(btnSuaSP);
        btnSuaSP.setBounds(430, 130, 60, 20);

        btnXoaSP.setForeground(new Color(234, 61, 47));
        btnXoaSP.setText("Xóa");
        functionPanel.add(btnXoaSP);
        btnXoaSP.setBounds(510, 130, 60, 20);

        btnThemNCC.setForeground(new Color(47, 168, 79));
        btnThemNCC.setText("Thêm");
        functionPanel.add(btnThemNCC);
        btnThemNCC.setBounds(350, 170, 60, 20);

        btnSuaNCC.setForeground(new Color(243, 170, 24));
        btnSuaNCC.setText("Sửa");
        functionPanel.add(btnSuaNCC);
        btnSuaNCC.setBounds(430, 170, 60, 20);

        btnXoaNCC.setForeground(new Color(234, 61, 47));
        btnXoaNCC.setText("Xóa");
        functionPanel.add(btnXoaNCC);
        btnXoaNCC.setBounds(510, 170, 60, 20);

        btnThemKH.setForeground(new Color(47, 168, 79));
        btnThemKH.setText("Thêm");
        functionPanel.add(btnThemKH);
        btnThemKH.setBounds(350, 210, 60, 20);

        btnSuaKH.setForeground(new Color(243, 170, 24));
        btnSuaKH.setText("Sửa");
        functionPanel.add(btnSuaKH);
        btnSuaKH.setBounds(430, 210, 60, 20);

        btnXoaKH.setForeground(new Color(234, 61, 47));
        btnXoaKH.setText("Xóa");
        functionPanel.add(btnXoaKH);
        btnXoaKH.setBounds(510, 210, 60, 20);

        btnThemKM.setForeground(new Color(47, 168, 79));
        btnThemKM.setText("Thêm");
        functionPanel.add(btnThemKM);
        btnThemKM.setBounds(350, 250, 60, 20);

        btnSuaKM.setForeground(new Color(243, 170, 24));
        btnSuaKM.setText("Sửa");
        functionPanel.add(btnSuaKM);
        btnSuaKM.setBounds(430, 250, 60, 20);

        btnXoaKM.setForeground(new Color(234, 61, 47));
        btnXoaKM.setText("Xóa");
        functionPanel.add(btnXoaKM);
        btnXoaKM.setBounds(510, 250, 60, 20);

        btnThemTK.setForeground(new Color(47, 168, 79));
        btnThemTK.setText("Thêm");
        functionPanel.add(btnThemTK);
        btnThemTK.setBounds(350, 290, 60, 20);

        btnSuaTK.setForeground(new Color(243, 170, 24));
        btnSuaTK.setText("Sửa");
        functionPanel.add(btnSuaTK);
        btnSuaTK.setBounds(430, 290, 60, 20);

        btnXoaTK.setForeground(new Color(234, 61, 47));
        btnXoaTK.setText("Xóa");
        functionPanel.add(btnXoaTK);
        btnXoaTK.setBounds(510, 290, 60, 20);

        btnThemExcel.setForeground(new Color(47, 168, 79));
        btnThemExcel.setText("Nhập");
        functionPanel.add(btnThemExcel);
        btnThemExcel.setBounds(350, 330, 60, 20);

        btnSuaExcel.setForeground(new Color(243, 170, 24));
        btnSuaExcel.setText("Xuất");
        functionPanel.add(btnSuaExcel);
        btnSuaExcel.setBounds(430, 330, 60, 20);

        btnXoaExcel.setForeground(new Color(234, 61, 47));
        btnXoaExcel.setText("PDF");
        functionPanel.add(btnXoaExcel);
        btnXoaExcel.setBounds(510, 330, 60, 20);

        btnThemNV.setForeground(new Color(47, 168, 79));
        btnThemNV.setText("Thêm");
        functionPanel.add(btnThemNV);
        btnThemNV.setBounds(350, 370, 60, 20);

        btnSuaNV.setForeground(new Color(243, 170, 24));
        btnSuaNV.setText("Sửa");
        functionPanel.add(btnSuaNV);
        btnSuaNV.setBounds(430, 370, 60, 20);

        btnXoaNV.setForeground(new Color(234, 61, 47));
        btnXoaNV.setText("Xóa");
        functionPanel.add(btnXoaNV);
        btnXoaNV.setBounds(510, 370, 60, 20);
        functionPanel.add(decorateFunctionPanel1);
        decorateFunctionPanel1.setBounds(10, 360, 560, 40);
        functionPanel.add(decorateFunctionPanel2);
        decorateFunctionPanel2.setBounds(10, 40, 560, 40);
        functionPanel.add(decorateFunctionPanel3);
        decorateFunctionPanel3.setBounds(10, 120, 560, 40);
        functionPanel.add(decorateFunctionPanel4);
        decorateFunctionPanel4.setBounds(10, 200, 560, 40);
        functionPanel.add(decorateFunctionPanel5);
        decorateFunctionPanel5.setBounds(10, 280, 560, 40);

        lbSelectFunction.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSelectFunction.setText("Chọn chức năng:");
        functionPanel.add(lbSelectFunction);
        lbSelectFunction.setBounds(10, 10, 210, 20);

        btnReset.setBackground(new Color(229, 239, 255));
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReset.setForeground(new Color(54, 123, 245));
        btnReset.setText("Tải lại");
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        functionPanel.add(btnReset);
        btnReset.setBounds(490, 10, 72, 22);

        add(functionPanel);
        functionPanel.setBounds(400, 10, 580, 420);

        detailPanel.setBackground(new Color(255, 255, 255));
        detailPanel.setLayout(null);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm Quyền");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnThemListener();
            }
        });
        detailPanel.add(btnThem);
        btnThem.setBounds(10, 360, 170, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa Quyền");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnXoaListener();
            }
        });
        detailPanel.add(btnXoa);
        btnXoa.setBounds(190, 360, 170, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa Quyền");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSuaListener();
            }
        });
        detailPanel.add(btnSua);
        btnSua.setBounds(10, 310, 350, 40);

        lbTenPQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbTenPQ.setText("Tên quyền:");
        detailPanel.add(lbTenPQ);
        lbTenPQ.setBounds(100, 100, 240, 20);

        lbMaPQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbMaPQ.setText("Mã:");
        detailPanel.add(lbMaPQ);
        lbMaPQ.setBounds(10, 100, 60, 20);

        txtMaPQ.setEnabled(false);
        txtMaPQ.setBackground(new Color(245, 245, 245));
        txtMaPQ.setAutoscrolls(false);
        txtMaPQ.setFocusable(false);
        detailPanel.add(txtMaPQ);
        txtMaPQ.setBounds(10, 120, 80, 40);

        detailPanel.add(txtTenPQ);
        txtTenPQ.setBounds(100, 120, 260, 40);

        lbStatus.setText("Tình trạng:");
        detailPanel.add(lbStatus);
        lbStatus.setBounds(10, 210, 70, 18);

        lbCurrentStatus.setForeground(MyColor.ORANGE);
        lbCurrentStatus.setText("nguyên gốc");
        detailPanel.add(lbCurrentStatus);
        lbCurrentStatus.setBounds(80, 210, 150, 18);

        lbSelectRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSelectRole.setText("Chọn quyền:");
        detailPanel.add(lbSelectRole);
        lbSelectRole.setBounds(10, 10, 310, 20);

        lbCountNV.setText("Số lượng tài khoản: 0");
        detailPanel.add(lbCountNV);
        lbCountNV.setBounds(10, 180, 240, 18);

        btnRenew.setBackground(new Color(229, 239, 255));
        btnRenew.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRenew.setForeground(new Color(54, 123, 245));
        btnRenew.setText("Làm mới");
        btnRenew.setBorderPainted(false);
        btnRenew.setFocusPainted(false);
        btnRenew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnRenewListener();
            }
        });
        detailPanel.add(btnRenew);
        btnRenew.setBounds(270, 180, 90, 22);

        add(detailPanel);
        detailPanel.setBounds(20, 10, 370, 420);

        txtTenPQ.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setSaveStatus(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setSaveStatus(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setSaveStatus(false);
            }
        });

        for (Component component: functionPanel.getComponents()) {
            if (component instanceof SwitchJButton)
                ((SwitchJButton) component).setSelected(false);
        }

        for (Component component: functionPanel.getComponents()) {
            if (component instanceof JCheckBox)
                ((JCheckBox)component).addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setSaveStatus(false);
                    }
                });
        }
    }

    private void onSelectedRoleListener() {
        int selectedIndex = cbRole.getSelectedIndex();
        if (selectedIndex == 0) {
            txtMaPQ.setText("");
            txtTenPQ.setText("");
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            setSaveStatus(null);
            clearSelectedFunction();
            return;
        }
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ArrayList<PhanQuyenDTO> roleList = phanQuyenBUS.findAll();
        PhanQuyenDTO selectedRole = roleList.get(selectedIndex-1);

        txtMaPQ.setText("PQ" + selectedRole.getMaPQ());
        txtTenPQ.setText(selectedRole.getTenPQ());

        ICT_PhanQuyenBUS ctPhanQuyenBUS = new CT_PhanQuyenBUS();
        CT_PhanQuyenDTO getFunctionHD = ctPhanQuyenBUS.findByID(selectedRole.getQuyenHD());
        CT_PhanQuyenDTO getFunctionSP = ctPhanQuyenBUS.findByID(selectedRole.getQuyenSP());
        CT_PhanQuyenDTO getFunctionPN = ctPhanQuyenBUS.findByID(selectedRole.getQuyenPN());
        CT_PhanQuyenDTO getFunctionNCC = ctPhanQuyenBUS.findByID(selectedRole.getQuyenNCC());
        CT_PhanQuyenDTO getFunctionKH = ctPhanQuyenBUS.findByID(selectedRole.getQuyenKH());
        CT_PhanQuyenDTO getFunctionKM = ctPhanQuyenBUS.findByID(selectedRole.getQuyenKM());
        CT_PhanQuyenDTO getFunctionTK = ctPhanQuyenBUS.findByID(selectedRole.getQuyenTK());
        CT_PhanQuyenDTO getFunctionExcel = ctPhanQuyenBUS.findByID(selectedRole.getQuyenExcel());
        CT_PhanQuyenDTO getFunctionNV = ctPhanQuyenBUS.findByID(selectedRole.getQuyenNV());

        switchJButtonHD.setSelected(getFunctionHD != null && getFunctionHD.getQuyenDoc() == 1);
        switchJButtonSP.setSelected(getFunctionSP != null && getFunctionSP.getQuyenDoc() == 1);
        switchJButtonPN.setSelected(getFunctionPN != null && getFunctionPN.getQuyenDoc() == 1);
        switchJButtonNCC.setSelected(getFunctionNCC != null && getFunctionNCC.getQuyenDoc() == 1);
        switchJButtonKH.setSelected(getFunctionKH != null && getFunctionKH.getQuyenDoc() == 1);
        switchJButtonKM.setSelected(getFunctionKM != null && getFunctionKM.getQuyenDoc() == 1);
        switchJButtonTK.setSelected(getFunctionTK != null && getFunctionTK.getQuyenDoc() == 1);
        switchJButtonExcel.setSelected(getFunctionExcel != null && getFunctionExcel.getQuyenDoc() == 1);
        switchJButtonNV.setSelected(getFunctionNV != null && getFunctionNV.getQuyenDoc() == 1);

        if (switchJButtonHD.isSelected()) {
            btnThemHD.setSelected(getFunctionHD.getQuyenTao() == 1);
            btnSuaHD.setSelected(getFunctionHD.getQuyenSua() == 1);
            btnXoaHD.setSelected(getFunctionHD.getQuyenXoa() == 1);
        }
        if (switchJButtonSP.isSelected()) {
            btnThemSP.setSelected(getFunctionSP.getQuyenTao() == 1);
            btnSuaSP.setSelected(getFunctionSP.getQuyenSua() == 1);
            btnXoaSP.setSelected(getFunctionSP.getQuyenXoa() == 1);
        }
        if (switchJButtonPN.isSelected()) {
            btnThemPN.setSelected(getFunctionPN.getQuyenTao() == 1);
            btnSuaPN.setSelected(getFunctionPN.getQuyenSua() == 1);
            btnXoaPN.setSelected(getFunctionPN.getQuyenXoa() == 1);
        }
        if (switchJButtonNCC.isSelected()) {
            btnThemNCC.setSelected(getFunctionNCC.getQuyenTao() == 1);
            btnSuaNCC.setSelected(getFunctionNCC.getQuyenSua() == 1);
            btnXoaNCC.setSelected(getFunctionNCC.getQuyenXoa() == 1);
        }
        if (switchJButtonKH.isSelected()) {
            btnThemKH.setSelected(getFunctionKH.getQuyenTao() == 1);
            btnSuaKH.setSelected(getFunctionKH.getQuyenSua() == 1);
            btnXoaKH.setSelected(getFunctionKH.getQuyenXoa() == 1);
        }
        if (switchJButtonKM.isSelected()) {
            btnThemKM.setSelected(getFunctionKM.getQuyenTao() == 1);
            btnSuaKM.setSelected(getFunctionKM.getQuyenSua() == 1);
            btnXoaKM.setSelected(getFunctionKM.getQuyenXoa() == 1);
        }
        if (switchJButtonTK.isSelected()) {
            btnThemTK.setSelected(getFunctionTK.getQuyenTao() == 1);
            btnSuaTK.setSelected(getFunctionTK.getQuyenSua() == 1);
            btnXoaTK.setSelected(getFunctionTK.getQuyenXoa() == 1);
        }
        if (switchJButtonExcel.isSelected()) {
            btnThemExcel.setSelected(getFunctionExcel.getQuyenTao() == 1);
            btnSuaExcel.setSelected(getFunctionExcel.getQuyenSua() == 1);
            btnXoaExcel.setSelected(getFunctionExcel.getQuyenXoa() == 1);
        }
        if (switchJButtonNV.isSelected()) {
            btnThemNV.setSelected(getFunctionNV.getQuyenTao() == 1);
            btnSuaNV.setSelected(getFunctionNV.getQuyenSua() == 1);
            btnXoaNV.setSelected(getFunctionNV.getQuyenXoa() == 1);
        }

        int count = 0;
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        for (TaiKhoanDTO dto : taiKhoanBUS.findAll()) {
            if (dto.getMaPQ().equals(selectedRole.getMaPQ()))
                count++;
        }
        lbCountNV.setText("Số lượng tài khoản: " + count);
        setSaveStatus(null);

        int ADMIN_DEFAULT_ID = 1;
        int EMPLOYEE_DEFAULT_ID = 2;
        boolean isDefault = selectedRole.getMaPQ() == ADMIN_DEFAULT_ID || selectedRole.getMaPQ() == EMPLOYEE_DEFAULT_ID;
        btnSua.setEnabled(!isDefault);
        btnXoa.setEnabled(!isDefault);
    }

    private void clearSelectedFunction() {
        for (Component component: functionPanel.getComponents()) {
            if (component instanceof SwitchJButton)
                ((SwitchJButton) component).setSelected(false);
        }
    }

    private Role getRole() {
        PhanQuyenDTO dto = new PhanQuyenDTO();
        try {
            Integer MaPQ = Integer.valueOf(txtMaPQ.getText().replace("PQ",""));
            dto.setMaPQ(MaPQ);
        } catch (NumberFormatException ignored) {}

        Role role = new Role(dto);
        if (role.getPhanQuyen().getMaPQ() != null) {
            IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
            role.setPhanQuyen(phanQuyenBUS.findByID(dto.getMaPQ()));
        }

        role.getPhanQuyen().setTenPQ(txtTenPQ.getText());

        CT_PhanQuyenDTO ctHD = new CT_PhanQuyenDTO();
        ctHD.setMaCTPQ(role.getPhanQuyen().getQuyenHD());
        ctHD.setQuyenDoc(switchJButtonHD.isSelected() ? 1 : 0);
        ctHD.setQuyenTao(btnThemHD.isSelected() ? 1 : 0);
        ctHD.setQuyenSua(btnSuaHD.isSelected() ? 1 : 0);
        ctHD.setQuyenXoa(btnXoaHD.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctSP = new CT_PhanQuyenDTO();
        ctSP.setMaCTPQ(role.getPhanQuyen().getQuyenSP());
        ctSP.setQuyenDoc(switchJButtonSP.isSelected() ? 1 : 0);
        ctSP.setQuyenTao(btnThemSP.isSelected() ? 1 : 0);
        ctSP.setQuyenSua(btnSuaSP.isSelected() ? 1 : 0);
        ctSP.setQuyenXoa(btnXoaSP.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctPN = new CT_PhanQuyenDTO();
        ctPN.setMaCTPQ(role.getPhanQuyen().getQuyenPN());
        ctPN.setQuyenDoc(switchJButtonPN.isSelected() ? 1 : 0);
        ctPN.setQuyenTao(btnThemPN.isSelected() ? 1 : 0);
        ctPN.setQuyenSua(btnSuaPN.isSelected() ? 1 : 0);
        ctPN.setQuyenXoa(btnXoaPN.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctNCC = new CT_PhanQuyenDTO();
        ctNCC.setMaCTPQ(role.getPhanQuyen().getQuyenNCC());
        ctNCC.setQuyenDoc(switchJButtonNCC.isSelected() ? 1 : 0);
        ctNCC.setQuyenTao(btnThemNCC.isSelected() ? 1 : 0);
        ctNCC.setQuyenSua(btnSuaNCC.isSelected() ? 1 : 0);
        ctNCC.setQuyenXoa(btnXoaNCC.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctKH = new CT_PhanQuyenDTO();
        ctKH.setMaCTPQ(role.getPhanQuyen().getQuyenKH());
        ctKH.setQuyenDoc(switchJButtonKH.isSelected() ? 1 : 0);
        ctKH.setQuyenTao(btnThemKH.isSelected() ? 1 : 0);
        ctKH.setQuyenSua(btnSuaKH.isSelected() ? 1 : 0);
        ctKH.setQuyenXoa(btnXoaKH.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctKM = new CT_PhanQuyenDTO();
        ctKM.setMaCTPQ(role.getPhanQuyen().getQuyenKM());
        ctKM.setQuyenDoc(switchJButtonKM.isSelected() ? 1 : 0);
        ctKM.setQuyenTao(btnThemKM.isSelected() ? 1 : 0);
        ctKM.setQuyenSua(btnSuaKM.isSelected() ? 1 : 0);
        ctKM.setQuyenXoa(btnXoaKM.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctTK = new CT_PhanQuyenDTO();
        ctTK.setMaCTPQ(role.getPhanQuyen().getQuyenTK());
        ctTK.setQuyenDoc(switchJButtonTK.isSelected() ? 1 : 0);
        ctTK.setQuyenTao(btnThemTK.isSelected() ? 1 : 0);
        ctTK.setQuyenSua(btnSuaTK.isSelected() ? 1 : 0);
        ctTK.setQuyenXoa(btnXoaTK.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctExcel = new CT_PhanQuyenDTO();
        ctExcel.setMaCTPQ(role.getPhanQuyen().getQuyenExcel());
        ctExcel.setQuyenDoc(switchJButtonExcel.isSelected() ? 1 : 0);
        ctExcel.setQuyenTao(btnThemExcel.isSelected() ? 1 : 0);
        ctExcel.setQuyenSua(btnSuaExcel.isSelected() ? 1 : 0);
        ctExcel.setQuyenXoa(btnXoaExcel.isSelected() ? 1 : 0);

        CT_PhanQuyenDTO ctNV = new CT_PhanQuyenDTO();
        ctNV.setMaCTPQ(role.getPhanQuyen().getQuyenNV());
        ctNV.setQuyenDoc(switchJButtonNV.isSelected() ? 1 : 0);
        ctNV.setQuyenTao(btnThemNV.isSelected() ? 1 : 0);
        ctNV.setQuyenSua(btnSuaNV.isSelected() ? 1 : 0);
        ctNV.setQuyenXoa(btnXoaNV.isSelected() ? 1 : 0);

        role.setQuyenHD(ctHD);
        role.setQuyenSP(ctSP);
        role.setQuyenPN(ctPN);
        role.setQuyenNCC(ctNCC);
        role.setQuyenKH(ctKH);
        role.setQuyenKM(ctKM);
        role.setQuyenTK(ctTK);
        role.setQuyenExcel(ctExcel);
        role.setQuyenNV(ctNV);
        return role;
    }

    private void onClickBtnThemListener() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ICT_PhanQuyenBUS ctPhanQuyenBUS = new CT_PhanQuyenBUS();
        try {
            Role role = getRole();
            if (!Validator.isValidName(role.getPhanQuyen().getTenPQ()))
                throw new Exception("Tên quyền không hợp lệ.");
            role.getPhanQuyen().setQuyenHD(ctPhanQuyenBUS.save(role.getQuyenHD()));
            role.getPhanQuyen().setQuyenSP(ctPhanQuyenBUS.save(role.getQuyenSP()));
            role.getPhanQuyen().setQuyenPN(ctPhanQuyenBUS.save(role.getQuyenPN()));
            role.getPhanQuyen().setQuyenNCC(ctPhanQuyenBUS.save(role.getQuyenNCC()));
            role.getPhanQuyen().setQuyenKH(ctPhanQuyenBUS.save(role.getQuyenKH()));
            role.getPhanQuyen().setQuyenKM(ctPhanQuyenBUS.save(role.getQuyenKM()));
            role.getPhanQuyen().setQuyenTK(ctPhanQuyenBUS.save(role.getQuyenTK()));
            role.getPhanQuyen().setQuyenExcel(ctPhanQuyenBUS.save(role.getQuyenExcel()));
            role.getPhanQuyen().setQuyenNV(ctPhanQuyenBUS.save(role.getQuyenNV()));
            phanQuyenBUS.save(role.getPhanQuyen());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhanQuyen.this, "Thêm quyền thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhanQuyen.this, "Thêm quyền thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        initRoleList();
        cbRole.setSelectedIndex(cbRole.getItemCount()-1);
    }

    private void onClickBtnXoaListener() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        try {
            Role role = getRole();
            if (role.getPhanQuyen().getMaPQ() == null)
                throw new Exception("Không tìm thấy quyền." );
            phanQuyenBUS.delete(role.getPhanQuyen().getMaPQ());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhanQuyen.this, "Xóa quyền thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhanQuyen.this, "Xóa quyền thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        initRoleList();
        onClickBtnRenewListener();
    }

    private void onClickBtnSuaListener() {
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ICT_PhanQuyenBUS ctPhanQuyenBUS = new CT_PhanQuyenBUS();
        try {
            Role role = getRole();
            if (role.getPhanQuyen().getMaPQ() == null)
                throw new Exception("Không tìm thấy quyền." );
            if (!Validator.isValidName(role.getPhanQuyen().getTenPQ()))
                throw new Exception("Tên quyền không hợp lệ.");
            phanQuyenBUS.update(role.getPhanQuyen());
            ctPhanQuyenBUS.update(role.getQuyenHD());
            ctPhanQuyenBUS.update(role.getQuyenSP());
            ctPhanQuyenBUS.update(role.getQuyenPN());
            ctPhanQuyenBUS.update(role.getQuyenNCC());
            ctPhanQuyenBUS.update(role.getQuyenKH());
            ctPhanQuyenBUS.update(role.getQuyenKM());
            ctPhanQuyenBUS.update(role.getQuyenTK());
            ctPhanQuyenBUS.update(role.getQuyenExcel());
            ctPhanQuyenBUS.update(role.getQuyenNV());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormPhanQuyen.this, "Sửa quyền thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormPhanQuyen.this, "Sửa quyền thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        int roleIndex = cbRole.getSelectedIndex();
        initRoleList();
        cbRole.setSelectedIndex(roleIndex);
    }

    private void onClickBtnRenewListener() {
        cbRole.setSelectedIndex(0);
    }

    private void onClickBtnResetListener() {
        onSelectedRoleListener();
    }

    private void setSaveStatus(Boolean b) {
        if (b == null) {
            lbCurrentStatus.setForeground(MyColor.ORANGE);
            lbCurrentStatus.setText("nguyên gốc");
        }
        else if (b) {
            lbCurrentStatus.setForeground(MyColor.GREEN);
            lbCurrentStatus.setText("đã lưu");
        } else {
            lbCurrentStatus.setForeground(MyColor.RED);
            lbCurrentStatus.setText("chưa lưu");
        }
    }

    JComboBox<String> cbRole;
    JPanel detailPanel = new JPanel();
    JPanel functionPanel = new JPanel();
    JButton btnThem = new JButton();
    JButton btnXoa = new JButton();
    JButton btnSua = new JButton();
    JCheckBox btnXoaHD = new JCheckBox();
    JCheckBox btnThemHD = new JCheckBox();
    JCheckBox btnSuaHD = new JCheckBox();
    JCheckBox btnThemPN = new JCheckBox();
    JCheckBox btnSuaPN = new JCheckBox();
    JCheckBox btnXoaPN = new JCheckBox();
    JCheckBox btnThemSP = new JCheckBox();
    JCheckBox btnSuaSP = new JCheckBox();
    JCheckBox btnXoaSP = new JCheckBox();
    JCheckBox btnThemNCC = new JCheckBox();
    JCheckBox btnSuaNCC = new JCheckBox();
    JCheckBox btnXoaNCC = new JCheckBox();
    JCheckBox btnThemKH = new JCheckBox();
    JCheckBox btnSuaKH = new JCheckBox();
    JCheckBox btnXoaKH = new JCheckBox();
    JCheckBox btnThemKM = new JCheckBox();
    JCheckBox btnSuaKM = new JCheckBox();
    JCheckBox btnXoaKM = new JCheckBox();
    JCheckBox btnThemTK = new JCheckBox();
    JCheckBox btnSuaTK = new JCheckBox();
    JCheckBox btnXoaTK = new JCheckBox();
    JCheckBox btnThemExcel = new JCheckBox();
    JCheckBox btnSuaExcel = new JCheckBox();
    JCheckBox btnXoaExcel = new JCheckBox();
    JCheckBox btnThemNV = new JCheckBox();
    JCheckBox btnSuaNV = new JCheckBox();
    JCheckBox btnXoaNV = new JCheckBox();
    JTextField txtTenPQ = new JTextField();
    JTextField txtMaPQ = new JTextField();
    JLabel lbCountNV = new JLabel();
    JLabel lbCurrentStatus = new JLabel();

    SwitchJButton switchJButtonSP;
    SwitchJButton switchJButtonPN;
    SwitchJButton switchJButtonNCC;
    SwitchJButton switchJButtonKH;
    SwitchJButton switchJButtonKM;
    SwitchJButton switchJButtonTK;
    SwitchJButton switchJButtonExcel;
    SwitchJButton switchJButtonNV;
    SwitchJButton switchJButtonHD;
}
