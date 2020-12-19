package model;

import entity.Book;
import entity.Borrow;
import service.BookTypeService;
import service.BorrowService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BorrowTableModel
 * @Version: 1.0
 * @Date: 2020-07-09 10:26
 * @Description:
 **/
public class BorrowTableModel implements TableModel {

    String[] columnNames = new String[]{"图书编号", "图书名称", "借阅时间","图书类别"};

    public static List<Borrow> cs = new BorrowService().list();

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
        Borrow b = cs.get(rowIndex);
        if(columnIndex == 0) {
            return b.getBookId();
        }
        if(columnIndex == 1) {
            return b.getBookName();
        }
        if(columnIndex == 2) {
            return b.getBookBorrowDate();
        }
        if(columnIndex == 3) {
            return new BookTypeService().getById(b.getBookTypeId()).getBookTypeName();
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
