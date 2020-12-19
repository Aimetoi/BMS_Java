package model;

import dao.BookTypeDAO;
import entity.BookType;
import service.BookTypeService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookTypeTableModel
 * @Version: 1.0
 * @Date: 2020-07-07 17:21
 * @Description:
 **/
public class BookTypeTableModel implements TableModel {

    String[] columnNames = new String[]{"编号", "图书类型编号", "图书类型描述"};

    public static List<BookType> cs = new BookTypeService().list();

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookType b = cs.get(rowIndex);
        if(columnIndex == 0) {
            return b.getId();
        }
        if(columnIndex == 1) {
            return b.getBookTypeName();
        }
        if(columnIndex == 2) {
            return b.getBookTypeDesc();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
