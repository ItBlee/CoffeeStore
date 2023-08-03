package com.itblee.gui.Form;

import com.itblee.dto.BaseEntity;
import com.itblee.gui.components.TableColumn;

import javax.swing.*;
import java.util.List;

public abstract class JTablePanel extends JPanel {
    protected String[] columnHeader;
    protected JScrollPane jScrollPane;
    protected TableColumn table;

    public void fillTable() {}
    public void fillTable(List<BaseEntity> idList) {}

    public JTable getTable() {
        return table;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }
}
