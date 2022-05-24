package GUI.Form.Abstract;

import DTO.Interface.IEntity;
import GUI.components.TableColumn;

import javax.swing.*;
import java.util.ArrayList;

public abstract class JTablePanel extends JPanel {
    protected String[] columnHeader;
    protected JScrollPane jScrollPane;
    protected TableColumn table;

    public void fillTable() {}
    public void fillTable(ArrayList<IEntity> idList) {}

    public JTable getTable() {
        return table;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }
}
