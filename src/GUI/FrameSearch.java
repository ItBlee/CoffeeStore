package GUI;

import BUS.SearchMapper.Interfaces.ISearchMapper;
import DTO.Interface.IEntity;
import GUI.Form.Abstract.JTablePanel;
import GUI.Form.FormNhanSu;
import GUI.components.Category;
import GUI.components.MovableJFrame;
import Utils.General;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class FrameSearch extends MovableJFrame {
    private final Class<?> clazz;
    private final ISearchMapper mapper;
    private final String[] props;
    private int conditionCount;
    private final String target;
    private final Stack<JComboBox<String>> cbOperatorList = new Stack<JComboBox<String>>();
    private final Stack<JComboBox<String>> cbConditionList = new Stack<JComboBox<String>>();
    private final Stack<JTextField> txtSearchList = new Stack<JTextField>();

    public FrameSearch(String target, ISearchMapper mapper, Class<?> clazz) throws Exception {
        Object parent = Class.forName(clazz.getName()).getConstructor().newInstance();
        if (!(parent instanceof JTablePanel))
            throw new Exception("Form tìm kiếm không hợp lệ");
        this.clazz = clazz;
        this.mapper = mapper;
        this.props = getProps();
        this.conditionCount = 1;
        this.target = target;
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

    private String[] getProps() {
        ArrayList<Category> formList = ((FrameLayout) General.frame).getCategories();
        JTablePanel parentPanel = null;
        for (Category category:formList) {
            if (category.getForm() instanceof JTablePanel && category.getForm().getClass().equals(clazz)) {
                parentPanel = (JTablePanel) category.getForm();
            }
            else if (category.getForm() instanceof FormNhanSu) {
                int index = ((FormNhanSu) category.getForm()).getJTabbedPane().getSelectedIndex();
                if (index == 0)
                    parentPanel = (JTablePanel) ((FormNhanSu) category.getForm()).getEmployeePanel();
                else if (index == 1)
                    parentPanel = (JTablePanel) ((FormNhanSu) category.getForm()).getAccountPanel();
            }
        }
        if (parentPanel != null)
            return parentPanel.getColumnHeader();
        return new String[0];
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
        txtSearch.setBounds(10, 50, 330, 30);
        txtSearchList.add(txtSearch);

        cbCondition.setModel(new DefaultComboBoxModel<String>(props));
        cbCondition.setBackground(Color.white);
        mainPanel.add(cbCondition);
        cbCondition.setBounds(350, 50, 150, 30);
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
        lbRemoveSearch.setBounds(200, 90, 100, 18);

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
        lbAddSearch.setBounds(90, 90, 110, 18);

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
        btnReset.setBounds(450, 85, 50, 30);

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
        btnSearch.setBounds(350, 85, 90, 30);

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
        final int OR_OPERATOR = 1;
        ArrayList<IEntity> searchList = new ArrayList<>();

        if (!(General.frame instanceof FrameLayout))
            return;
        JPanel currentForm = ((FrameLayout) General.frame).getCurrentItem().getForm();
        if (currentForm instanceof FormNhanSu) {
            int index = ((FormNhanSu) currentForm).getJTabbedPane().getSelectedIndex();
            if (index == 0)
                currentForm = ((FormNhanSu) currentForm).getEmployeePanel();
            else if (index == 1)
                currentForm = ((FormNhanSu) currentForm).getAccountPanel();
        }
        if (!currentForm.getClass().equals(clazz)) {
            JOptionPane.showMessageDialog(FrameSearch.this, "Vui lòng mở " + target + " để tìm kiếm", "Không phù hợp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cursor = 0;
        while (cursor < txtSearchList.size()) {
            String value = txtSearchList.get(cursor).getText();
            Integer conditionIndex = cbConditionList.get(cursor).getSelectedIndex();
            ArrayList<IEntity> tempSearch = mapper.searchByIndex(conditionIndex, value);
            if (cursor > 0 && !cbOperatorList.empty()) {
                int operator = cbOperatorList.get(cursor-1).getSelectedIndex();
                if (operator == OR_OPERATOR) {
                    cursor++;
                    if (cursor < txtSearchList.size()) {
                        int nextOperator = cbOperatorList.get(cursor-1).getSelectedIndex();
                        while (nextOperator != OR_OPERATOR) {
                            String nextValue = txtSearchList.get(cursor).getText();
                            Integer nextConditionIndex = cbConditionList.get(cursor).getSelectedIndex();
                            ArrayList<IEntity> nextTempSearch = mapper.searchByIndex(nextConditionIndex, nextValue);
                            tempSearch = intersection(tempSearch, nextTempSearch);
                            cursor++;
                            if (cursor == txtSearchList.size())
                                break;
                            nextOperator = cbOperatorList.get(cursor-1).getSelectedIndex();
                        }
                        searchList = union(searchList, tempSearch);
                        continue;
                    }
                    else cursor--;
                }
            }
            searchList = intersection(searchList, tempSearch);
            cursor++;
        }

        if (group.getSelection() != null) {
            ArrayList<IEntity> searchDateList = new ArrayList<>();
            if (rbOnDay.isSelected()) {
                searchDateList = mapper.searchByDate(txtDate.getDate(), txtDate.getDate());
            } else if (rbBeforeDay.isSelected()) {
                searchDateList = mapper.searchByDate(null, txtDate.getDate());
            } else if (rbAfterDay.isSelected()) {
                searchDateList = mapper.searchByDate(txtDate.getDate(), null);
            } else if (rbFromDay.isSelected()) {
                searchDateList = mapper.searchByDate(txtFromDate.getDate(), txtToDay.getDate());
            }
            searchList = intersection(searchList, searchDateList);
        }

        searchList.removeIf(entity -> entity == null || entity.getID() == null);

        if (currentForm instanceof JTablePanel)
            ((JTablePanel) currentForm).fillTable(searchList);
        else {
            JOptionPane.showMessageDialog(FrameSearch.this, "Không có bảng để tra cứu", "Không phù hợp", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList<IEntity> union(ArrayList<IEntity> list1, ArrayList<IEntity> list2) {
        Set<IEntity> set = new HashSet<IEntity>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<IEntity>(set);
    }

    private ArrayList<IEntity> intersection(ArrayList<IEntity> list1, ArrayList<IEntity> list2) {
        ArrayList<IEntity> list = new ArrayList<IEntity>();
        for (IEntity t : list1)
            if(list2.contains(t))
                list.add(t);
        return list;
    }

    private void onClickLbAddListener() {
        if (conditionCount == cbCondition.getItemCount())
            return;
        conditionCount++;

        JComboBox<String> newCbOperator = new JComboBox<>();
        newCbOperator.setBackground(Color.white);
        newCbOperator.setModel(new DefaultComboBoxModel<String>(new String[] { "Và", "Hoặc" }));
        mainPanel.add(newCbOperator);
        newCbOperator.setBounds(10, 20+(35*conditionCount), 72, 30);
        cbOperatorList.push(newCbOperator);

        JTextField newTxtSearch = new JTextField();
        mainPanel.add(newTxtSearch);
        newTxtSearch.setBounds(90, 20+(35*conditionCount), 250, 30);
        txtSearchList.push(newTxtSearch);

        JComboBox<String> newCbCondition = new JComboBox<>();
        newCbCondition.setBackground(Color.white);
        newCbCondition.setModel(new DefaultComboBoxModel<>(props));
        mainPanel.add(newCbCondition);
        newCbCondition.setBounds(350, 20+(35*conditionCount), 150, 30);
        cbConditionList.push(newCbCondition);

        repaintComponents();
    }

    private void onClickLbRemoveListener() {
        if (conditionCount == 1)
            return;
        conditionCount--;

        mainPanel.remove(cbOperatorList.pop());
        mainPanel.remove(txtSearchList.pop());
        mainPanel.remove(cbConditionList.pop());

        repaintComponents();
    }

    private void repaintComponents() {
        setSize(getWidth(), 280+(35*conditionCount));
        mainPanel.setSize(getWidth(), getHeight());
        lbAddSearch.setBounds(lbAddSearch.getX(), 55+(35*conditionCount), lbAddSearch.getWidth(), lbAddSearch.getHeight());
        lbRemoveSearch.setBounds(lbRemoveSearch.getX(), 55+(35*conditionCount), lbRemoveSearch.getWidth(), lbRemoveSearch.getHeight());
        lbDateTitle.setBounds(lbDateTitle.getX(), 150+(35*conditionCount), lbDateTitle.getWidth(), lbDateTitle.getHeight());
        txtToDay.setBounds(txtToDay.getX(), 190+(35*conditionCount), txtToDay.getWidth(), txtToDay.getHeight());
        txtDate.setBounds(txtDate.getX(), 150+(35*conditionCount), txtDate.getWidth(), txtDate.getHeight());
        txtFromDate.setBounds(txtFromDate.getX(), 190+(35*conditionCount), txtFromDate.getWidth(), txtFromDate.getHeight());
        rbBeforeDay.setBounds(rbBeforeDay.getX(), 150+(35*conditionCount), rbBeforeDay.getWidth(), rbBeforeDay.getHeight());
        rbFromDay.setBounds(rbFromDay.getX(), 190+(35*conditionCount), rbFromDay.getWidth(), rbFromDay.getHeight());
        rbAfterDay.setBounds(rbAfterDay.getX(), 150+(35*conditionCount), rbAfterDay.getWidth(), rbAfterDay.getHeight());
        lbToDay.setBounds(lbToDay.getX(), 190+(35*conditionCount), lbToDay.getWidth(), lbToDay.getHeight());
        rbOnDay.setBounds(rbOnDay.getX(), 150+(35*conditionCount), rbOnDay.getWidth(), rbOnDay.getHeight());
        btnReset.setBounds(btnReset.getX(), 50+(35*conditionCount), btnReset.getWidth(), btnReset.getHeight());
        btnSearch.setBounds(btnSearch.getX(), 50+(35*conditionCount), btnSearch.getWidth(), btnSearch.getHeight());
        revalidate();
        repaint();
    }

    private ButtonGroup group;
    private final JPanel mainPanel = new JPanel();
    private final JLabel lbTitle = new JLabel();
    private final JTextField txtSearch = new JTextField();
    private final JComboBox<String> cbCondition = new JComboBox<>();
    private final JLabel lbRemoveSearch = new JLabel();
    private final JLabel lbAddSearch = new JLabel();
    private final JLabel lbDateTitle = new JLabel();
    private final JDateChooser txtToDay = new JDateChooser();
    private final JDateChooser txtDate = new JDateChooser();
    private final JDateChooser txtFromDate = new JDateChooser();
    private final JRadioButton rbBeforeDay = new JRadioButton();
    private final JRadioButton rbFromDay = new JRadioButton();
    private final JRadioButton rbAfterDay = new JRadioButton();
    private final JLabel lbToDay = new JLabel();
    private final JRadioButton rbOnDay = new JRadioButton();
    private final JButton btnReset = new JButton();
    private final JButton btnSearch = new JButton();
}
