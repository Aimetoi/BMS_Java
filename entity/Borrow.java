package entity;

import java.util.Date;

/**
 * @Author: Aime-toi
 * @Project: Borrow
 * @Version: 1.0
 * @Date: 2020-07-08 13:48
 * @Description:
 **/
public class Borrow {

    private int userId;
    private int bookId;
    private String bookName;
    private String bookBorrowDate;
    private int bookTypeId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookBorrowDate() {
        return bookBorrowDate;
    }

    public void setBookBorrowDate(String bookBorrowDate) {
        this.bookBorrowDate = bookBorrowDate;
    }

    public int getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

}
