package service;

import dao.BorrowDAO;
import entity.Book;
import entity.Borrow;
import view.frame.LoginFrame;

import java.util.Date;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BorrowService
 * @Version: 1.0
 * @Date: 2020-07-09 11:26
 * @Description:
 **/
public class BorrowService {

    //dao层
    BorrowDAO dao = new BorrowDAO();

    /**
     * 添加借阅记录
     * @param book
     * @return
     */
    public boolean add(Book book) {
        Borrow borrow = new Borrow();

        borrow.setUserId(LoginFrame.user.getId());
        borrow.setBookId(book.getBookId());
        borrow.setBookName(book.getBookName());
        borrow.setBookBorrowDate(new Date().toLocaleString());
        borrow.setBookTypeId(book.getBookTypeId());

        return dao.add(borrow);
    }

    public List<Borrow> list() {
        return dao.list();
    }

    /**
     * 删除
     */
    public void delete(Borrow borrow) {
        //int bookCount = Integer.parseInt(borrow.get)
        dao.delete(borrow);
    }
}
