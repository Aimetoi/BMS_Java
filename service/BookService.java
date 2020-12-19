package service;

import dao.BookDAO;
import entity.Book;
import entity.BookType;

import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookService
 * @Version: 1.0
 * @Date: 2020-07-08 18:39
 * @Description:
 **/
public class BookService {

    //dao层
    BookDAO dao = new BookDAO();

    public boolean add(String bookName, String author, String price,
                       BookType bookType, int bookCount, String bookDesc) {
        Book book = new Book();

        book.setBookName(bookName);
        book.setBookAuthor(author);
        book.setBookPrice(price);
        book.setBookTypeId(bookType.getId());
        book.setBookCount(bookCount);
        book.setBookDesc(bookDesc);

        if(dao.add(book)) {
            return true;
        }
        return false;
    }

    public List<Book> list() {
        return dao.list();
    }

    /**
     * 修改
     * @param id
     * @param bookName
     * @param author
     * @param price
     * @param bookType
     * @param bookCount
     * @param bookDesc
     */
    public void update(int id, String bookName, String author, String price,
                       BookType bookType, int bookCount, String bookDesc) {
        Book book = new Book();

        book.setBookId(id);
        book.setBookName(bookName);
        book.setBookAuthor(author);
        book.setBookPrice(price);
        book.setBookTypeId(bookType.getId());
        book.setBookCount(bookCount);
        book.setBookDesc(bookDesc);

        dao.update(book);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
