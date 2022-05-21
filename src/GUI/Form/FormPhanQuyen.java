package GUI.Form;

import BUS.CT_PhanQuyenBUS;
import BUS.Interfaces.ICT_PhanQuyenBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import BUS.PhanQuyenBUS;
import DTO.CT_PhanQuyenDTO;
import DTO.PhanQuyenDTO;
import DTO.Role;
import GUI.components.IEventSwitchSelected;
import GUI.components.SwitchJButton;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

public class FormPhanQuyen extends JPanel {
    public FormPhanQuyen() {
        initComponents();
        initRoleList();
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
        cbRole.setSelectedIndex(0);
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
        JPanel detailPanel = new JPanel();
        JButton btnThem = new JButton();
        JButton btnXoa = new JButton();
        JButton btnSua = new JButton();
        JLabel lbTenPQ = new JLabel();
        JLabel lbMaPQ = new JLabel();
        JLabel lbStatus = new JLabel();
        JLabel lbCurrentStatus = new JLabel();
        JLabel lbSelectRole = new JLabel();
        JLabel lbCountNV = new JLabel();
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
                if (!selected) {
                    btnThemSP.setSelected(false);
                    btnSuaSP.setSelected(false);
                    btnXoaSP.setSelected(false);
                }
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
                if (!selected) {
                    btnThemPN.setSelected(false);
                    btnSuaPN.setSelected(false);
                    btnXoaPN.setSelected(false);
                }
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
                if (!selected) {
                    btnThemNCC.setSelected(false);
                    btnSuaNCC.setSelected(false);
                    btnXoaNCC.setSelected(false);
                }
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
                if (!selected) {
                    btnThemKH.setSelected(false);
                    btnSuaKH.setSelected(false);
                    btnXoaKH.setSelected(false);
                }
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
                if (!selected) {
                    btnThemKM.setSelected(false);
                    btnSuaKM.setSelected(false);
                    btnXoaKM.setSelected(false);
                }
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
                if (!selected) {
                    btnThemTK.setSelected(false);
                    btnSuaTK.setSelected(false);
                    btnXoaTK.setSelected(false);
                }
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
                if (!selected) {
                    btnThemExcel.setSelected(false);
                    btnSuaExcel.setSelected(false);
                    btnXoaExcel.setSelected(false);
                }
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
                if (!selected) {
                    btnThemNV.setSelected(false);
                    btnSuaNV.setSelected(false);
                    btnXoaNV.setSelected(false);
                }
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
                if (!selected) {
                    btnThemHD.setSelected(false);
                    btnSuaHD.setSelected(false);
                    btnXoaHD.setSelected(false);
                }
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
        btnThemExcel.setText("Thêm");
        functionPanel.add(btnThemExcel);
        btnThemExcel.setBounds(350, 330, 60, 20);

        btnSuaExcel.setForeground(new Color(243, 170, 24));
        btnSuaExcel.setText("Sửa");
        functionPanel.add(btnSuaExcel);
        btnSuaExcel.setBounds(430, 330, 60, 20);

        btnXoaExcel.setForeground(new Color(234, 61, 47));
        btnXoaExcel.setText("Xóa");
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
        detailPanel.add(btnThem);
        btnThem.setBounds(10, 360, 170, 40);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa Quyền");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        detailPanel.add(btnXoa);
        btnXoa.setBounds(190, 360, 170, 40);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa Quyền");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        detailPanel.add(btnSua);
        btnSua.setBounds(10, 310, 350, 40);
        detailPanel.add(txtTenPQ);
        txtTenPQ.setBounds(100, 120, 260, 40);

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

        lbStatus.setText("Tình trạng:");
        detailPanel.add(lbStatus);
        lbStatus.setBounds(10, 210, 70, 18);

        lbCurrentStatus.setForeground(new Color(234, 61, 47));
        lbCurrentStatus.setText("Chưa lưu");
        detailPanel.add(lbCurrentStatus);
        lbCurrentStatus.setBounds(80, 210, 150, 18);

        lbSelectRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSelectRole.setText("Chọn quyền:");
        detailPanel.add(lbSelectRole);
        lbSelectRole.setBounds(10, 10, 310, 20);

        lbCountNV.setText("Số lượng nhân viên: 000");
        detailPanel.add(lbCountNV);
        lbCountNV.setBounds(10, 180, 240, 18);

        btnRenew.setBackground(new Color(229, 239, 255));
        btnRenew.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRenew.setForeground(new Color(54, 123, 245));
        btnRenew.setText("Làm mới");
        btnRenew.setBorderPainted(false);
        btnRenew.setFocusPainted(false);
        detailPanel.add(btnRenew);
        btnRenew.setBounds(270, 180, 90, 22);

        add(detailPanel);
        detailPanel.setBounds(20, 10, 370, 420);
    }

    private void onSelectedRoleListener() {
        int selectedIndex = cbRole.getSelectedIndex();
        if (selectedIndex == 0) {
            txtMaPQ.setText("thêm");
            txtTenPQ.setText("");
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
    }

    private void clearSelectedFunction() {
        for (Component component: functionPanel.getComponents()) {
            if (component instanceof SwitchJButton)
                ((SwitchJButton) component).setSelected(false);
        }
    }

    private Role getRole() {
        PhanQuyenDTO dto = new PhanQuyenDTO();
        dto.setMaPQ(Integer.valueOf(txtMaPQ.getText()));
        dto.setTenPQ(txtTenPQ.getText());
        Role role = new Role(dto);

        CT_PhanQuyenDTO ctHD = null;
        if (switchJButtonHD.isSelected()) {
            ctHD = new CT_PhanQuyenDTO();
            ctHD.setMaCTPQ();
            ctHD.setQuyenDoc();
            ctHD.setQuyenTao();
            ctHD.setQuyenSua();
            ctHD.setQuyenXoa();
        }
        role.setQuyenHD();
        role.setQuyenSP();
        role.setQuyenPN();
        role.setQuyenNCC();
        role.setQuyenKH();
        role.setQuyenKM();
        role.setQuyenTK();
        role.setQuyenExcel();
        role.setQuyenNV();
        return role;
    }

    private void onClickBtnThemListener() {

    }

    private void onClickBtnXoaListener() {

    }

    private void onClickBtnSuaListener() {

    }

    private void onClickBtnResetListener() {
        cbRole.setSelectedIndex(0);
    }

    JComboBox<String> cbRole;
    JPanel functionPanel = new JPanel();
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
