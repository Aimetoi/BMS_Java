package model;

import entity.Book;
import service.BookService;
import service.BookTypeService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookTableModel
 * @Version: 1.0
 * @Date: 2020-07-07 21:49
 * @Description:
 **/
public class BookTableModel implements TableModel {

    String[] columnNames = new String[]{"图书编号", "图书名称", "图书作者", "图书价格", "图书类别", "图书库存", "图书描述"};

    public static List<Book> cs = new BookService().list();

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
        Book b = cs.get(rowIndex);
        if(columnIndex == 0) {
            return b.getBookId();
        }
        if(columnIndex == 1) {
            return b.getBookName();
        }
        if(columnIndex == 2) {
            return b.getBookAuthor();
        }
        if(columnIndex == 3) {
            return b.getBookPrice();
        }
        if(columnIndex == 4) {
            return new BookTypeService().getById(b.getBookTypeId());
        }
        if(columnIndex == 5) {
            return b.getBookCount();
        }
        if(columnIndex == 6) {
            return b.getBookDesc();
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
