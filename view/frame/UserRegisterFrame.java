package view.frame;

import javax.swing.*;

/**
 * @Author: Aime-toi
 * @Project: UserRegisterFrame
 * @Version: 1.0
 * @Date: 2020-07-08 08:26
 * @Description:
 **/
public class UserRegisterFrame extends JFrame {

    //单例模式
    public static UserRegisterFrame instance = new UserRegisterFrame();

    public UserRegisterFrame() {
        //设置窗体信息
        setTitle("BMS - 用户注册");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);






        //设置窗体可见性
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}
