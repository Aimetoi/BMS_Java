package service;

import dao.BookTypeDAO;
import entity.BookType;
import view.frame.EditTypeFrame;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookTypeService
 * @Version: 1.0
 * @Date: 2020-07-08 14:51
 * @Description:
 **/
public class BookTypeService {

    //dao层
    BookTypeDAO dao = new BookTypeDAO();

    /**
     * 添加图书类别记录
     * @param bookTypeName
     * @param bookTypeDesc
     * @return
     */
    public boolean add(String bookTypeName, String bookTypeDesc) {
        BookType bookType = new BookType();
        bookType.setBookTypeName(bookTypeName);
        bookType.setBookTypeDesc(bookTypeDesc);
        if(dao.add(bookType)) {
            return true;
        }
        return false;
    }

    /**
     * 显示全部
     * @return
     */
    public List<BookType> list() {
        List<BookType> bs = dao.list();
        Collections.sort(bs, (c1, c2)->c1.getId() - c2.getId());
        return bs;
    }

    /**
     * 部分搜索
     * @param search
     * @return
     */
    public List<BookType> list(String search) {
        return dao.list(search);
    }

    /**
     * 修改数据
     * @param typeName
     * @param typeDesc
     */
    public void update(int id, String typeName, String typeDesc) {
        BookType bookType = new BookType();

        bookType.setId(id);
        bookType.setBookTypeName(typeName);
        bookType.setBookTypeDesc(typeDesc);

        dao.update(bookType);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * 根据类型id获取bookType对象
     * @param id
     */
    public BookType getById(int id) {
        return dao.getById(id);
    }
}
