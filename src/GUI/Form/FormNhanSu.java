package GUI.Form;

import javax.swing.*;

public class FormNhanSu extends JPanel {
    public FormNhanSu() {
        initComponents();
    }
    
    private void initComponents() {
        JTabbedPane jTabbedPane = new JTabbedPane();
        JPanel employeePanel = new FormNhanVien();
        JPanel accountPanel = new FormTaiKhoan();
        JPanel rolePanel = new FormPhanQuyen();
        JPanel historyPanel = new FormLichSu();

        setLayout(null);

        employeePanel.setLayout(null);
        jTabbedPane.addTab("Nhân viên", employeePanel);

        accountPanel.setLayout(null);
        jTabbedPane.addTab("Tài khoản", accountPanel);

        rolePanel.setLayout(null);
        jTabbedPane.addTab("Phân quyền", rolePanel);

        historyPanel.setLayout(null);
        jTabbedPane.addTab("Lịch sử", historyPanel);

        add(jTabbedPane);
        jTabbedPane.setBounds(0, 0, 1000, 807);
    }
}
