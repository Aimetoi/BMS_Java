package view.frame;



import dao.UserDAO;
import entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: LogInFrame
 * @Version: 1.0
 * @Date: 2020-07-06 09:44
 * @Description: 创建登录界面
 **/
public class LoginFrame extends JFrame{

    //单例模式
    public static LoginFrame instance = new LoginFrame();

    //当前用户
    public static User user;

    //创建组件
    private JPanel contentPane;
    private JTextField userField;
    private JPasswordField passwordField;

    public LoginFrame() {
        //设置窗口基本信息
        setTitle("BMS - ①组_19计科三");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/Bookicon.png")));

        //初始化面板
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(20, 100, 10, 100));


        //标题面板
        JPanel titlePanel = new JPanel();
        contentPane.add(titlePanel, BorderLayout.NORTH);
        //内容标题
        JLabel labelBMS = new JLabel("BookManagementSystem");
        labelBMS.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book.png")));
        labelBMS.setFont(new Font("微软雅黑", Font.BOLD, 27));
        titlePanel.add(labelBMS);


        //标签、输入框面板
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 2, 0, 30));
        contentPane.add(fieldPanel, BorderLayout.CENTER);

        //创建用户名标签
        JLabel idLabel = new JLabel("用户名：");
        idLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/user.png")));
        idLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        fieldPanel.add(idLabel);
        //创建用户名输入框
        JTextField idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));
        idField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        fieldPanel.add(idField);

        //创建密码标签
        JLabel passwordLabel = new JLabel("密　码：");
        passwordLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        fieldPanel.add(passwordLabel);
        //创建密码输入框
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        fieldPanel.add(passwordField);


        //按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 80, 0));
        contentPane.add(buttonPanel);

        //创建登录按钮
        JButton login = new JButton("登录");
        login.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));
        buttonPanel.add(login);

        //创建重置按钮
        JButton reset = new JButton("重置");
        reset.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/refrsh.png")));
        buttonPanel.add(reset);


        //点击登录按钮进入管理员界面
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == idField.getText().length() || 0 == passwordField.getPassword().length) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名与密码不能为空！");
                    return;
                }

                //判断登录是否成功
                if(UserDAO.login(idField.getText(), new String(passwordField.getPassword()))) {
                    //判断是否为管理员
                    if(UserDAO.isAdmin(idField.getText())) {
                        user = UserDAO.getUser(Integer.parseInt(idField.getText()));
                        AdminFrame.instance.setVisible(true);
                        LoginFrame.instance.setVisible(false);
                    } else {
                        user = UserDAO.getUser(Integer.parseInt(idField.getText()));
                        UserFrame.instance.setVisible(true);
                        LoginFrame.instance.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "账号或密码错误，请重新输入！");
                    return;
                }
            }
        });

        //点击重置清空输入框内容
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                passwordField.setText("");
            }
        });


        //设置窗口可见性
        setVisible(true);
    }

    public static void main(String[] args) {

    }
}
