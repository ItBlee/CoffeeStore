package com.itblee.gui.Form;

import javax.swing.*;

public class FormNhanSu extends JPanel {
    private final JTabbedPane jTabbedPane = new JTabbedPane();
    private final JPanel employeePanel = new FormNhanVien();
    private final JPanel accountPanel = new FormTaiKhoan();
    private final JPanel rolePanel = new FormPhanQuyen();
    private final JPanel historyPanel = new FormLichSu();

    public FormNhanSu() {
        initComponents();
    }

    public JTabbedPane getJTabbedPane() {
        return jTabbedPane;
    }

    public JPanel getEmployeePanel() {
        return employeePanel;
    }

    public JPanel getAccountPanel() {
        return accountPanel;
    }

    public JPanel getRolePanel() {
        return rolePanel;
    }

    public JPanel getHistoryPanel() {
        return historyPanel;
    }

    private void initComponents() {
        setLayout(null);

        employeePanel.setLayout(null);
        jTabbedPane.addTab("Nhân viên", employeePanel);

        accountPanel.setLayout(null);
        jTabbedPane.addTab("Tài khoản", accountPanel);

        rolePanel.setLayout(null);
        jTabbedPane.addTab("Phân quyền", rolePanel);

        historyPanel.setLayout(null);
        jTabbedPane.addTab("Lịch sử", historyPanel);

        jTabbedPane.addChangeListener(e -> {
            if (jTabbedPane.getSelectedComponent() instanceof FormLichSu) {
                ((FormLichSu) jTabbedPane.getSelectedComponent()).reload();
            }
        });

        add(jTabbedPane);
        jTabbedPane.setBounds(0, 0, 1000, 807);
    }
}
