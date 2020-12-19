package entity;

/**
 * @Author: Aime-toi
 * @Project: BookType
 * @Version: 1.0
 * @Date: 2020-07-08 13:47
 * @Description:
 **/
public class BookType {

    private int id;
    private String bookTypeName;
    private String bookTypeDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeDesc() {
        return bookTypeDesc;
    }

    public void setBookTypeDesc(String bookTypeDesc) {
        this.bookTypeDesc = bookTypeDesc;
    }

    /**
     * 重写toString方法，显示名字
     * @return
     */
    @Override
    public String toString() {
        return getBookTypeName();
    }
}
