package model;

import entity.BookType;
import service.BookTypeService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookTypeComboBoxModel
 * @Version: 1.0
 * @Date: 2020-07-08 13:12
 * @Description:
 **/
public class BookTypeComboBoxModel implements ComboBoxModel<BookType> {

    public List<BookType> cs = new BookTypeService().list();

    BookType c;

    /**
     * 通过数组内容查找下标
     * @param bookType
     * @return
     */
    public int indexOf(BookType bookType) {
        if(bookType == null) {
            return -1;
        } else {
            for(int i = 0; i < cs.size(); i++) {
                if(bookType.getId() == cs.get(i).getId()) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (BookType) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!cs.isEmpty()) {
            return c;
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public BookType getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
