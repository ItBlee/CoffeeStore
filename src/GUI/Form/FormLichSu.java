package GUI.Form;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.*;

public class FormLichSu extends JPanel {
    public FormLichSu() {
        initComponents();
    }
    
    private void initComponents() {
        JScrollPane jScrollPane = new JScrollPane();
        JPanel timeBox1 = new JPanel();
        JPanel timePanel1 = new JPanel();
        JLabel lbTime1 = new JLabel();
        JPanel historyPanel1 = new JPanel();
        JPanel coverTitlePanel1 = new JPanel();
        JPanel actionMark1 = new JPanel();
        JLabel lbTitleHistory1 = new JLabel();
        JLabel lbNameNV1 = new JLabel();
        JLabel lbDate1 = new JLabel();
        JLabel lbTarget1 = new JLabel();
        JPanel timeBox3 = new JPanel();
        JPanel timePanel3 = new JPanel();
        JLabel lbTime3 = new JLabel();
        JPanel historyPanel3 = new JPanel();
        JPanel coverTitlePanel3 = new JPanel();
        JPanel actionMark3 = new JPanel();
        JLabel lbTitleHistory3 = new JLabel();
        JLabel lbNameNV3 = new JLabel();
        JLabel lbTarget3 = new JLabel();
        JLabel lbDate3 = new JLabel();
        JPanel timeBox2 = new JPanel();
        JPanel timePanel2 = new JPanel();
        JLabel lbTime2 = new JLabel();
        JPanel historyPanel2 = new JPanel();
        JPanel coverTitlePanel2 = new JPanel();
        JPanel actionMark2 = new JPanel();
        JLabel lbTitleHistory2 = new JLabel();
        JLabel lbNameNV2 = new JLabel();
        JLabel lbTarget2 = new JLabel();
        JLabel lbDate2 = new JLabel();
        JPanel timeBox4 = new JPanel();
        JPanel timePanel4 = new JPanel();
        JLabel lbTime4 = new JLabel();
        JPanel historyPanel4 = new JPanel();
        JPanel coverTitlePanel4 = new JPanel();
        JPanel actionMark4 = new JPanel();
        JLabel lbTitleHistory4 = new JLabel();
        JLabel lbNameNV4 = new JLabel();
        JLabel lbTarget4 = new JLabel();
        JLabel lbDate4 = new JLabel();
        JButton btnCurrent = new JButton();

        setLayout(null);

        mainPanel.setLayout(null);

        timeBox1.setBackground(new Color(198, 202, 206));
        timeBox1.setLayout(null);
        mainPanel.add(timeBox1);
        timeBox1.setBounds(465, 175, 25, 25);

        timePanel1.setBackground(new Color(56, 56, 56));
        timePanel1.setForeground(new Color(37, 37, 37));
        timePanel1.setLayout(null);

        lbTime1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTime1.setForeground(new Color(198, 202, 206));
        lbTime1.setText("09:00");
        timePanel1.add(lbTime1);
        lbTime1.setBounds(30, 10, 50, 18);

        mainPanel.add(timePanel1);
        timePanel1.setBounds(365, 170, 90, 40);

        historyPanel1.setBackground(new Color(255, 255, 255));
        historyPanel1.setLayout(null);

        coverTitlePanel1.setBackground(new Color(0, 0, 0));
        coverTitlePanel1.setLayout(null);

        actionMark1.setBackground(new Color(47, 168, 79));
        actionMark1.setLayout(null);

        coverTitlePanel1.add(actionMark1);
        actionMark1.setBounds(10, 10, 20, 20);

        lbTitleHistory1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitleHistory1.setForeground(Color.white);
        lbTitleHistory1.setText("Thêm");
        coverTitlePanel1.add(lbTitleHistory1);
        lbTitleHistory1.setBounds(40, 10, 170, 20);

        historyPanel1.add(coverTitlePanel1);
        coverTitlePanel1.setBounds(0, 0, 300, 40);

        lbNameNV1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbNameNV1.setForeground(new Color(198, 202, 206));
        lbNameNV1.setText("Nhân viên: ");
        historyPanel1.add(lbNameNV1);
        lbNameNV1.setBounds(30, 50, 260, 18);

        lbDate1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbDate1.setForeground(new Color(198, 202, 206));
        lbDate1.setText("Thời điểm: ");
        historyPanel1.add(lbDate1);
        lbDate1.setBounds(30, 110, 260, 18);

        lbTarget1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbTarget1.setForeground(new Color(198, 202, 206));
        lbTarget1.setText("Đối tượng: ");
        historyPanel1.add(lbTarget1);
        lbTarget1.setBounds(30, 80, 260, 18);

        mainPanel.add(historyPanel1);
        historyPanel1.setBounds(145, 180, 300, 140);

        timeBox3.setBackground(new Color(198, 202, 206));
        timeBox3.setLayout(null);
        mainPanel.add(timeBox3);
        timeBox3.setBounds(465, 375, 25, 25);

        timePanel3.setBackground(new Color(56, 56, 56));
        timePanel3.setForeground(new Color(37, 37, 37));
        timePanel3.setLayout(null);

        lbTime3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTime3.setForeground(new Color(198, 202, 206));
        lbTime3.setText("09:00");
        timePanel3.add(lbTime3);
        lbTime3.setBounds(30, 10, 41, 18);

        mainPanel.add(timePanel3);
        timePanel3.setBounds(365, 370, 90, 40);

        historyPanel3.setBackground(new Color(255, 255, 255));
        historyPanel3.setLayout(null);

        coverTitlePanel3.setBackground(new Color(0, 0, 0));
        coverTitlePanel3.setLayout(null);

        actionMark3.setBackground(new Color(243, 170, 24));
        actionMark3.setLayout(null);

        coverTitlePanel3.add(actionMark3);
        actionMark3.setBounds(10, 10, 20, 20);

        lbTitleHistory3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitleHistory3.setForeground(Color.white);
        lbTitleHistory3.setText("Sửa");
        coverTitlePanel3.add(lbTitleHistory3);
        lbTitleHistory3.setBounds(40, 10, 170, 20);

        historyPanel3.add(coverTitlePanel3);
        coverTitlePanel3.setBounds(0, 0, 300, 40);

        lbNameNV3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbNameNV3.setForeground(new Color(198, 202, 206));
        lbNameNV3.setText("Nhân viên: ");
        historyPanel3.add(lbNameNV3);
        lbNameNV3.setBounds(30, 50, 260, 18);

        lbTarget3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbTarget3.setForeground(new Color(198, 202, 206));
        lbTarget3.setText("Đối tượng: ");
        historyPanel3.add(lbTarget3);
        lbTarget3.setBounds(30, 80, 260, 18);

        lbDate3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbDate3.setForeground(new Color(198, 202, 206));
        lbDate3.setText("Thời điểm: ");
        historyPanel3.add(lbDate3);
        lbDate3.setBounds(30, 110, 260, 18);

        mainPanel.add(historyPanel3);
        historyPanel3.setBounds(145, 380, 300, 140);

        timeBox2.setBackground(new Color(198, 202, 206));
        timeBox2.setLayout(null);
        mainPanel.add(timeBox2);
        timeBox2.setBounds(465, 275, 25, 25);

        timePanel2.setBackground(new Color(56, 56, 56));
        timePanel2.setForeground(new Color(37, 37, 37));
        timePanel2.setLayout(null);

        lbTime2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTime2.setForeground(new Color(198, 202, 206));
        lbTime2.setText("09:00");
        timePanel2.add(lbTime2);
        lbTime2.setBounds(30, 10, 50, 18);

        mainPanel.add(timePanel2);
        timePanel2.setBounds(500, 270, 90, 40);

        historyPanel2.setBackground(new Color(255, 255, 255));
        historyPanel2.setLayout(null);

        coverTitlePanel2.setBackground(new Color(0, 0, 0));
        coverTitlePanel2.setLayout(null);

        actionMark2.setBackground(new Color(234, 61, 47));
        actionMark2.setLayout(null);

        coverTitlePanel2.add(actionMark2);
        actionMark2.setBounds(270, 10, 20, 20);

        lbTitleHistory2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitleHistory2.setForeground(Color.white);
        lbTitleHistory2.setHorizontalAlignment(SwingConstants.RIGHT);
        lbTitleHistory2.setText("Xóa");
        coverTitlePanel2.add(lbTitleHistory2);
        lbTitleHistory2.setBounds(87, 10, 170, 20);

        historyPanel2.add(coverTitlePanel2);
        coverTitlePanel2.setBounds(0, 0, 300, 40);

        lbNameNV2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbNameNV2.setForeground(new Color(198, 202, 206));
        lbNameNV2.setText("Nhân viên: ");
        historyPanel2.add(lbNameNV2);
        lbNameNV2.setBounds(30, 50, 260, 18);

        lbTarget2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbTarget2.setForeground(new Color(198, 202, 206));
        lbTarget2.setText("Đối tượng: ");
        historyPanel2.add(lbTarget2);
        lbTarget2.setBounds(30, 80, 260, 18);

        lbDate2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbDate2.setForeground(new Color(198, 202, 206));
        lbDate2.setText("Thời điểm: ");
        historyPanel2.add(lbDate2);
        lbDate2.setBounds(30, 110, 260, 18);

        mainPanel.add(historyPanel2);
        historyPanel2.setBounds(510, 280, 300, 140);

        timeBox4.setBackground(new Color(198, 202, 206));
        timeBox4.setLayout(null);
        mainPanel.add(timeBox4);
        timeBox4.setBounds(465, 475, 25, 25);

        timePanel4.setBackground(new Color(56, 56, 56));
        timePanel4.setForeground(new Color(37, 37, 37));
        timePanel4.setLayout(null);

        lbTime4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTime4.setForeground(new Color(198, 202, 206));
        lbTime4.setText("09:00");
        timePanel4.add(lbTime4);
        lbTime4.setBounds(30, 10, 50, 18);

        mainPanel.add(timePanel4);
        timePanel4.setBounds(500, 470, 90, 40);

        historyPanel4.setBackground(new Color(255, 255, 255));
        historyPanel4.setLayout(null);

        coverTitlePanel4.setBackground(new Color(0, 0, 0));
        coverTitlePanel4.setLayout(null);

        actionMark4.setBackground(new Color(54, 123, 245));
        actionMark4.setLayout(null);

        coverTitlePanel4.add(actionMark4);
        actionMark4.setBounds(270, 10, 20, 20);

        lbTitleHistory4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTitleHistory4.setForeground(Color.white);
        lbTitleHistory4.setHorizontalAlignment(SwingConstants.RIGHT);
        lbTitleHistory4.setText("Ngoại lệ");
        coverTitlePanel4.add(lbTitleHistory4);
        lbTitleHistory4.setBounds(86, 10, 160, 20);

        historyPanel4.add(coverTitlePanel4);
        coverTitlePanel4.setBounds(0, 0, 300, 40);

        lbNameNV4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbNameNV4.setForeground(new Color(198, 202, 206));
        lbNameNV4.setText("Nhân viên: ");
        historyPanel4.add(lbNameNV4);
        lbNameNV4.setBounds(30, 50, 260, 18);

        lbTarget4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbTarget4.setForeground(new Color(198, 202, 206));
        lbTarget4.setText("Đối tượng: ");
        historyPanel4.add(lbTarget4);
        lbTarget4.setBounds(30, 80, 260, 18);

        lbDate4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbDate4.setForeground(new Color(198, 202, 206));
        lbDate4.setText("Thời điểm: ");
        historyPanel4.add(lbDate4);
        lbDate4.setBounds(30, 110, 260, 18);

        mainPanel.add(historyPanel4);
        historyPanel4.setBounds(510, 480, 300, 140);

        timeLine.setBackground(new Color(198, 202, 206));
        timeLine.setLayout(null);

        mainPanel.add(timeLine);
        timeLine.setBounds(475, 100, 5, 680);

        btnCurrent.setBackground(new Color(198, 202, 206));
        btnCurrent.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnCurrent.setForeground(Color.white);
        btnCurrent.setText("Hiện tại");
        btnCurrent.setBorderPainted(false);
        btnCurrent.setFocusable(false);
        mainPanel.add(btnCurrent);
        btnCurrent.setBounds(407, 54, 140, 50);

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane);
        jScrollPane.setBounds(0, 0, 960, 750);

        JScrollBar jScrollBar = jScrollPane.getVerticalScrollBar();
        jScrollBar.setUnitIncrement(20);
        jScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (jScrollBar.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount())
                    loadMoreEvent();
            }
        });
    }

    public void loadMoreEvent() {
        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight() + 1000));
        timeLine.setBounds(timeLine.getX(), timeLine.getY(), timeLine.getWidth(), timeLine.getHeight() + 1000);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    JPanel mainPanel = new JPanel();
    JPanel timeLine = new JPanel();
}
