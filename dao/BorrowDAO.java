package dao;

import entity.Book;
import entity.Borrow;
import util.DBUtil;
import view.frame.LoginFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BorrowDAO
 * @Version: 1.0
 * @Date: 2020-07-09 11:26
 * @Description:
 **/
public class BorrowDAO {

    /**
     * 添加到借阅列表
     *
     * @param borrow
     */
    public boolean add(Borrow borrow) {
        String sql = "insert into borrow values(?, ?, ?, ?, ?)";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, borrow.getUserId());
            ps.setInt(2, borrow.getBookId());
            ps.setString(3, borrow.getBookName());
            ps.setString(4, borrow.getBookBorrowDate());
            ps.setInt(5, borrow.getBookTypeId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return true;
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 显示全部
     *
     * @return
     */
    public List<Borrow> list() {
        return list("");
    }


    public List<Borrow> list(String search) {
        List<Borrow> bs = new ArrayList<Borrow>();

        String sql = "select * from borrow";

        //判断输入框是否为空，不为空则追加sql语句
        if (0 != search.length()) {
            sql += " where bookTypeId like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Borrow borrow = new Borrow();

                int userId = LoginFrame.user.getId();
                int bookId = rs.getInt(2);
                String bookName = rs.getString(3);
                String borrowDate = rs.getString(4);
                int bookTypeId = rs.getInt(5);

                borrow.setUserId(userId);
                borrow.setBookId(bookId);
                borrow.setBookName(bookName);
                borrow.setBookBorrowDate(borrowDate);
                borrow.setBookTypeId(bookTypeId);

                bs.add(borrow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bs;
    }


    /**
     * 删除
     * @param borrow
     */
    public void delete(Borrow borrow) {
        String sql = "delete from borrow where user_id = ? and book_id = ? and bookBorrowTime = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, borrow.getUserId());
            ps.setInt(2, borrow.getBookId());
            ps.setString(3, borrow.getBookBorrowDate());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
