package GUI;

import DTO.TaiKhoanDTO;
import GUI.components.MovableJFrame;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class FrameSearch extends MovableJFrame {
    private Class<?> clazz;
    private String[] props;
    private int conditionCount;
    private Stack<JComboBox<String>> cbOperatorList = new Stack<JComboBox<String>>();
    private Stack<JComboBox<String>> cbConditionList = new Stack<JComboBox<String>>();
    private Stack<JTextField> txtSearchList = new Stack<JTextField>();

    public FrameSearch(String target, String[] props, Class<?> clazz) {
        this.clazz = clazz;
        this.props = props;
        this.conditionCount = 1;
        initFrame("Tìm kiếm " + target);
        initComponents();
    }

    private void initFrame(String title) {
        setSize(530, 310);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle(title);
        setIconImage(new ImageIcon("bin/images/logo.png").getImage());
    }

    private void initComponents() {
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(null);

        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTitle.setForeground(new Color(54, 123, 245));
        lbTitle.setText("Tìm kiếm nâng cao");
        mainPanel.add(lbTitle);
        lbTitle.setBounds(10, 10, 180, 30);

        mainPanel.add(txtSearch);
        txtSearch.setBounds(10, 50, 330, 24);
        txtSearchList.add(txtSearch);

        cbCondition.setModel(new DefaultComboBoxModel<String>(props));
        cbCondition.setBackground(Color.white);
        mainPanel.add(cbCondition);
        cbCondition.setBounds(350, 50, 150, 24);
        cbConditionList.add(cbCondition);

        lbRemoveSearch.setForeground(new Color(102, 102, 102));
        lbRemoveSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbRemoveSearch.setText("Xóa điều kiện");
        lbRemoveSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLbRemoveListener();
            }
        });
        mainPanel.add(lbRemoveSearch);
        lbRemoveSearch.setBounds(200, 85, 100, 18);

        lbAddSearch.setForeground(new Color(102, 153, 255));
        lbAddSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbAddSearch.setText("Thêm điều kiện  |");
        lbAddSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLbAddListener();
            }
        });
        mainPanel.add(lbAddSearch);
        lbAddSearch.setBounds(90, 85, 110, 18);

        lbDateTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDateTitle.setText("Theo ngày:");
        mainPanel.add(lbDateTitle);
        lbDateTitle.setBounds(10, 180, 80, 20);

        mainPanel.add(txtToDay);
        txtToDay.setBounds(350, 220, 150, 25);

        mainPanel.add(txtDate);
        txtDate.setBounds(160, 180, 150, 25);

        mainPanel.add(txtFromDate);
        txtFromDate.setBounds(160, 220, 150, 25);

        rbOnDay.setText("Trong");
        rbOnDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rbOnDay.setOpaque(false);
        mainPanel.add(rbOnDay);
        rbOnDay.setBounds(90, 180, 70, 22);

        rbBeforeDay.setText("Trước");
        rbBeforeDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rbBeforeDay.setOpaque(false);
        mainPanel.add(rbBeforeDay);
        rbBeforeDay.setBounds(390, 180, 70, 22);

        rbAfterDay.setText("Sau");
        rbAfterDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rbAfterDay.setOpaque(false);
        mainPanel.add(rbAfterDay);
        rbAfterDay.setBounds(320, 180, 70, 22);

        rbFromDay.setText("Từ:");
        rbFromDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rbFromDay.setOpaque(false);
        mainPanel.add(rbFromDay);
        rbFromDay.setBounds(90, 220, 70, 22);

        lbToDay.setText("Đến:");
        mainPanel.add(lbToDay);
        lbToDay.setBounds(320, 220, 40, 25);

        group = new ButtonGroup();
        group.add(rbOnDay);
        group.add(rbBeforeDay);
        group.add(rbAfterDay);
        group.add(rbFromDay);

        btnReset.setBackground(new Color(229, 239, 255));
        btnReset.setForeground(new Color(54, 123, 245));
        btnReset.setIcon(new ImageIcon("bin/images/components/reset-blue.png"));
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        mainPanel.add(btnReset);
        btnReset.setBounds(450, 80, 50, 30);

        btnSearch.setBackground(new Color(229, 239, 255));
        btnSearch.setForeground(new Color(54, 123, 245));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSearchListener();
            }
        });
        mainPanel.add(btnSearch);
        btnSearch.setBounds(350, 80, 90, 30);

        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 530, 270);
    }

    private void onClickBtnResetListener() {
        for (Component component:mainPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
            if (component instanceof JDateChooser)
                ((JDateChooser) component).setCalendar(null);
        }
        group.clearSelection();
    }

    private void onClickBtnSearchListener() {

    }

    private void onClickLbAddListener() {
        if (conditionCount == cbCondition.getItemCount())
            return;
        conditionCount++;

        JComboBox<String> newCbOperator = new JComboBox<>();
        newCbOperator.setBackground(Color.white);
        newCbOperator.setModel(new DefaultComboBoxModel<String>(new String[] { "Và", "Hoặc" }));
        mainPanel.add(newCbOperator);
        newCbOperator.setBounds(10, 20+(30*conditionCount), 72, 24);
        cbOperatorList.push(newCbOperator);

        JTextField newTxtSearch = new JTextField();
        mainPanel.add(newTxtSearch);
        newTxtSearch.setBounds(90, 20+(30*conditionCount), 250, 24);
        txtSearchList.push(newTxtSearch);

        JComboBox<String> newCbCondition = new JComboBox<>();
        newCbCondition.setBackground(Color.white);
        newCbCondition.setModel(new DefaultComboBoxModel<>(props));
        mainPanel.add(newCbCondition);
        newCbCondition.setBounds(350, 20+(30*conditionCount), 150, 24);
        cbConditionList.push(newCbCondition);

        //setLocation(getX(), getY()-30);
        repaintComponents();
    }

    private void onClickLbRemoveListener() {
        if (conditionCount == 1)
            return;
        conditionCount--;

        mainPanel.remove(cbOperatorList.pop());
        mainPanel.remove(txtSearchList.pop());
        mainPanel.remove(cbConditionList.pop());

        //setLocation(getX(), getY()+30);
        repaintComponents();
    }

    private void repaintComponents() {
        setSize(getWidth(), 280+(30*conditionCount));
        mainPanel.setSize(getWidth(), getHeight());
        lbAddSearch.setBounds(lbAddSearch.getX(), 55+(30*conditionCount), lbAddSearch.getWidth(), lbAddSearch.getHeight());
        lbRemoveSearch.setBounds(lbRemoveSearch.getX(), 55+(30*conditionCount), lbRemoveSearch.getWidth(), lbRemoveSearch.getHeight());
        lbDateTitle.setBounds(lbDateTitle.getX(), 150+(30*conditionCount), lbDateTitle.getWidth(), lbDateTitle.getHeight());
        txtToDay.setBounds(txtToDay.getX(), 190+(30*conditionCount), txtToDay.getWidth(), txtToDay.getHeight());
        txtDate.setBounds(txtDate.getX(), 150+(30*conditionCount), txtDate.getWidth(), txtDate.getHeight());
        txtFromDate.setBounds(txtFromDate.getX(), 190+(30*conditionCount), txtFromDate.getWidth(), txtFromDate.getHeight());
        rbBeforeDay.setBounds(rbBeforeDay.getX(), 150+(30*conditionCount), rbBeforeDay.getWidth(), rbBeforeDay.getHeight());
        rbFromDay.setBounds(rbFromDay.getX(), 190+(30*conditionCount), rbFromDay.getWidth(), rbFromDay.getHeight());
        rbAfterDay.setBounds(rbAfterDay.getX(), 150+(30*conditionCount), rbAfterDay.getWidth(), rbAfterDay.getHeight());
        lbToDay.setBounds(lbToDay.getX(), 190+(30*conditionCount), lbToDay.getWidth(), lbToDay.getHeight());
        rbOnDay.setBounds(rbOnDay.getX(), 150+(30*conditionCount), rbOnDay.getWidth(), rbOnDay.getHeight());
        btnReset.setBounds(btnReset.getX(), 50+(30*conditionCount), btnReset.getWidth(), btnReset.getHeight());
        btnSearch.setBounds(btnSearch.getX(), 50+(30*conditionCount), btnSearch.getWidth(), btnSearch.getHeight());
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        FlatLightFlatIJTheme.setup();
        String[] list = new String [] {
                "Mã", "Tên đăng nhập", "Chức vụ", "Sở hữu", "Ngày tạo", "Người tạo", "Tình trạng"};
        JFrame jFrame = new FrameSearch("Tài khoản", list, TaiKhoanDTO.class);
        jFrame.setVisible(true);
    }

    ButtonGroup group;
    JPanel mainPanel = new JPanel();
    JLabel lbTitle = new JLabel();
    JTextField txtSearch = new JTextField();
    JComboBox<String> cbCondition = new JComboBox<>();
    JLabel lbRemoveSearch = new JLabel();
    JLabel lbAddSearch = new JLabel();
    JLabel lbDateTitle = new JLabel();
    JDateChooser txtToDay = new JDateChooser();
    JDateChooser txtDate = new JDateChooser();
    JDateChooser txtFromDate = new JDateChooser();
    JRadioButton rbBeforeDay = new JRadioButton();
    JRadioButton rbFromDay = new JRadioButton();
    JRadioButton rbAfterDay = new JRadioButton();
    JLabel lbToDay = new JLabel();
    JRadioButton rbOnDay = new JRadioButton();
    JButton btnReset = new JButton();
    JButton btnSearch = new JButton();
}
