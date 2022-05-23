package GUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrameSearch extends JFrame {
    private Class<?> clazz;
    private String[] props;
    private int conditionCount;
    private ArrayList<JComboBox<String>> cbOperatorList = new ArrayList<JComboBox<String>>();
    private ArrayList<JComboBox<String>> cbConditionList = new ArrayList<JComboBox<String>>();
    private ArrayList<JTextField> txtSearchList = new ArrayList<JTextField>();

    public FrameSearch(String target, String[] props, Class<?> clazz) {
        this.clazz = clazz;
        this.props = props;
        this.conditionCount = 1;
        initFrame("Tìm kiếm " + target);
        initComponents();
    }

    private void initFrame(String title) {
        setSize(1080, 575);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
        mainPanel.add(cbCondition);
        cbCondition.setBounds(350, 50, 150, 24);
        cbConditionList.add(cbCondition);

        lbRemoveSearch.setForeground(new Color(102, 102, 102));
        lbRemoveSearch.setText("Xóa điều kiện");
        lbRemoveSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLbRemoveListener();
            }
        });
        mainPanel.add(lbRemoveSearch);
        lbRemoveSearch.setBounds(200, 115, 100, 18);

        lbAddSearch.setForeground(new Color(102, 153, 255));
        lbAddSearch.setText("Thêm điều kiện  |");
        lbAddSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLbAddListener();
            }
        });
        mainPanel.add(lbAddSearch);
        lbAddSearch.setBounds(90, 115, 110, 18);

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
        mainPanel.add(rbOnDay);
        rbOnDay.setBounds(90, 180, 70, 22);

        rbBeforeDay.setText("Trước");
        mainPanel.add(rbBeforeDay);
        rbBeforeDay.setBounds(390, 180, 70, 22);

        rbAfterDay.setText("Sau");
        mainPanel.add(rbAfterDay);
        rbAfterDay.setBounds(320, 180, 70, 22);

        rbFromDay.setText("Từ:");
        mainPanel.add(rbFromDay);
        rbFromDay.setBounds(90, 220, 70, 22);

        lbToDay.setText("Đến:");
        mainPanel.add(lbToDay);
        lbToDay.setBounds(320, 220, 40, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(rbOnDay);
        group.add(rbBeforeDay);
        group.add(rbAfterDay);
        group.add(rbFromDay);

        btnReset.setBackground(new Color(229, 239, 255));
        btnReset.setForeground(new Color(54, 123, 245));
        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        mainPanel.add(btnReset);
        btnReset.setBounds(450, 110, 50, 30);

        btnSearch.setBackground(new Color(229, 239, 255));
        btnSearch.setForeground(new Color(54, 123, 245));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        mainPanel.add(btnSearch);
        btnSearch.setBounds(350, 110, 90, 30);

        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 530, 270);

        pack();
    }

    private void onClickLbAddListener() {
        if (conditionCount == cbCondition.getItemCount())
            return;
        conditionCount++;

        JComboBox<String> newCbOperator = new JComboBox<>();
        newCbOperator.setModel(new DefaultComboBoxModel<String>(new String[] { "Và", "Hoặc" }));
        mainPanel.add(newCbOperator);
        newCbOperator.setBounds(10, 50+(30*conditionCount), 72, 24);
        cbOperatorList.add(newCbOperator);

        JTextField newTxtSearch = new JTextField();
        mainPanel.add(newTxtSearch);
        newTxtSearch.setBounds(90, 50+(30*conditionCount), 250, 24);
        txtSearchList.add(newTxtSearch);

        JComboBox<String> newCbCondition = new JComboBox<>();
        newCbCondition.setModel(new DefaultComboBoxModel<>(props));
        mainPanel.add(newCbCondition);
        newCbCondition.setBounds(350, 50+(30*conditionCount), 150, 24);
        cbConditionList.add(newCbCondition);

        lbAddSearch.setBounds(lbAddSearch.getX(), lbAddSearch.getY(), lbAddSearch.getWidth(), lbAddSearch.getHeight());
        lbRemoveSearch.setBounds(lbRemoveSearch.getX(), lbRemoveSearch.getY(), lbRemoveSearch.getWidth(), lbRemoveSearch.getHeight());
        lbDateTitle.setBounds(lbDateTitle.getX(), lbDateTitle.getY(), lbDateTitle.getWidth(), lbDateTitle.getHeight());
        txtToDay.setBounds(txtToDay.getX(), txtToDay.getY(), txtToDay.getWidth(), txtToDay.getHeight());
        txtDate.setBounds(txtDate.getX(), txtDate.getY(), txtDate.getWidth(), txtDate.getHeight());
        txtFromDate.setBounds(txtFromDate.getX(), txtFromDate.getY(), txtFromDate.getWidth(), txtFromDate.getHeight());
        rbBeforeDay.setBounds(rbBeforeDay.getX(), rbBeforeDay.getY(), rbBeforeDay.getWidth(), rbBeforeDay.getHeight());
        rbFromDay.setBounds(rbFromDay.getX(), rbFromDay.getY(), rbFromDay.getWidth(), rbFromDay.getHeight());
        rbAfterDay.setBounds(rbAfterDay.getX(), rbAfterDay.getY(), rbAfterDay.getWidth(), rbAfterDay.getHeight());
        lbToDay.setBounds(lbToDay.getX(), lbToDay.getY(), lbToDay.getWidth(), lbToDay.getHeight());
        rbOnDay.setBounds(rbOnDay.getX(), rbOnDay.getY(), rbOnDay.getWidth(), rbOnDay.getHeight());
        btnReset.setBounds(btnReset.getX(), btnReset.getY(), btnReset.getWidth(), btnReset.getHeight());
        btnSearch.setBounds(btnSearch.getX(), btnSearch.getY(), btnSearch.getWidth(), btnSearch.getHeight());

        revalidate();
        repaint();
    }

    private void onClickLbRemoveListener() {
        if (conditionCount == 1)
            return;
        conditionCount--;
    }

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
