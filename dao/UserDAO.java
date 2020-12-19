package dao;

import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Aime-toi
 * @Project: UserDAO
 * @Version: 1.0
 * @Date: 2020-07-08 14:08
 * @Description:
 **/
public class UserDAO {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public static boolean login(String userName, String password) {
        String sql = "select * from user where id = ? and password = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

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
     * 判断是否为管理员
     * @param userName
     * @return
     */
    public static boolean isAdmin(String userName) {
        String sql = "select * from user where id = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int isAdmin = rs.getInt("admin");
                if(isAdmin == 1) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public static User getUser(int id) {
        String sql = "select * from user where id = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                User user = new User();

                user.setId(id);
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAdmin((rs.getInt(4)));

                return user;
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
