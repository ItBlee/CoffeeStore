package com.itblee.gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableColumn extends JTable {
    public TableColumn() {
        setBackground(Color.white);
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                return new TableCell(o);
            }
        });
    }

    @Override
    public Component prepareRenderer(TableCellRenderer tcr, int i, int i1) {
        TableCell.CellType celLType = TableCell.CellType.CENTER;
        if (i1 == 0) {
            celLType = TableCell.CellType.LEFT;
        } else if (i1 == getColumnCount() - 1) {
            celLType = TableCell.CellType.RIGHT;
        }
        return new TableCell(getValueAt(i, i1), isCellSelected(i, i1), celLType);
    }
}
