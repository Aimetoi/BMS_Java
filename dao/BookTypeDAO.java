package dao;

import entity.Book;
import entity.BookType;
import util.DBUtil;
import view.frame.EditTypeFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aime-toi
 * @Project: BookTypeDAO
 * @Version: 1.0
 * @Date: 2020-07-08 14:39
 * @Description:
 **/
public class BookTypeDAO {

    /**
     * 添加
     * @param bookType
     * @return
     */
    public boolean add(BookType bookType) {
        String sql = "insert into booktype values(null, ?, ?)";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, bookType.getBookTypeName());
            ps.setString(2, bookType.getBookTypeDesc());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()) {
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 显示全部
     * @return
     */
    public List<BookType> list() {
        return list("");
    }

    /**
     * 部分搜索
     * @param search
     * @return
     */
    public List<BookType> list(String search) {
        List<BookType> bs = new ArrayList<BookType>();

        String sql = "select * from booktype";

        //判断输入框是否为空，不为空则追加sql语句
        if(0 != search.length()) {
            sql += " where bookTypeName like '%" + search + "%'";
        }

        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {


            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                BookType bookType = new BookType();
                int id = rs.getInt(1);
                String typeName = rs.getString(2);
                String typeDesc = rs.getString(3);
                bookType.setId(id);
                bookType.setBookTypeName(typeName);
                bookType.setBookTypeDesc(typeDesc);
                bs.add(bookType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bs;
    }

    /**
     * 修改
     */
    public void update(BookType bookType) {
        String sql = "update booktype set bookTypeName = ?, bookTypeDesc = ? where id = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, bookType.getBookTypeName());
            ps.setString(2, bookType.getBookTypeDesc());
            ps.setInt(3, bookType.getId());

            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(int id) {
        String sql = "delete from booktype where id = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            //判断要删除的类别是否有对应图书
            try {
                ps.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(EditTypeFrame.instance, "删除失败，或该类别还有对应图书");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据分类名称返回BookType对象
     * @param name
     * @return
     */
    public BookType getByName(String name) {

        String sql = "select * from booktype where bookTypeName = ?";

        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                BookType bookType = new BookType();
                int id = rs.getInt(1);
                String typeName = rs.getString(2);
                String typeDesc = rs.getString(3);
                bookType.setId(id);
                bookType.setBookTypeName(typeName);
                bookType.setBookTypeDesc(typeDesc);

                return bookType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id返回对象
     * @param id
     * @return
     */
    public BookType getById(int id) {

        String sql = "select * from booktype where id = ?";

        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                BookType bookType = new BookType();
                int tempId = rs.getInt(1);
                String typeName = rs.getString(2);
                String typeDesc = rs.getString(3);
                bookType.setId(tempId);
                bookType.setBookTypeName(typeName);
                bookType.setBookTypeDesc(typeDesc);

                return bookType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
