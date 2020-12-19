package view.frame;

import dao.UserDAO;
import entity.User;
import util.CenterPanel;
import view.listener.AdminMenuBarListener;
import view.listener.UserMenuBarListener;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: UserFrame
 * @Version: 1.0
 * @Date: 2020-07-08 09:01
 * @Description:
 **/
public class UserFrame extends JFrame {

    //单例模式
    public static UserFrame instance = new UserFrame();

    //菜单栏
    public JMenuBar menuBar = new JMenuBar();

    //图书借阅 菜单
    public JMenu borrowMenu = new JMenu("图书借阅");
    public JMenuItem borrowItem = new JMenuItem("图书借阅");

    //图书归还 菜单
    public JMenu returnMenu = new JMenu("图书归还");
    public JMenuItem returnItem = new JMenuItem("图书归还");

    //个人设置 菜单
    public JMenu personalMenu = new JMenu("个人设置");
    public JMenuItem editPassItem = new JMenuItem("密码修改");

    //关于我们 菜单
    public JMenu aboutMenu = new JMenu("关于我们");
    //public JMenuItem weChatItem = new JMenuItem("微信公众号");
    public JMenuItem sysDescItem = new JMenuItem("系统介绍");
    public JMenuItem aboutUsItem = new JMenuItem("关于我们");

    //退出系统 菜单
    public JMenuItem exitMenu = new JMenuItem("退出系统");


    //工作面板
    public CenterPanel workingPanel;


    public UserFrame() {
        //设置窗体信息
        setTitle("BMS - 读者  当前读者：" + LoginFrame.user.getId());
        setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置工作面板
        workingPanel = new CenterPanel(0.85, false);
        setContentPane(workingPanel);
        workingPanel.setSize(this.getWidth(), this.getHeight() - 50);
        workingPanel.show(AboutUsPanel.instance);

        //设置图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/book.png")));
        //设置菜单栏
        setJMenuBar(menuBar);

        //添加菜单项
        borrowMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/book-BM.png")));
        menuBar.add(borrowMenu);
        returnMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/归还.png")));
        menuBar.add(returnMenu);
        personalMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/user-icon.png")));
        menuBar.add(personalMenu);
        aboutMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Cancellation-icon.png")));
        menuBar.add(aboutMenu);
        exitMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Safe exit-icon.png")));
        menuBar.add(exitMenu);

        //添加子菜单
        //图书借阅
        borrowItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/book-BM.png")));
        borrowMenu.add(borrowItem);

        //图书归还
        returnItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/归还.png")));
        returnMenu.add(returnItem);

        //个人设置
        editPassItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/密码.png")));
        personalMenu.add(editPassItem);

        //关于我们
        sysDescItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Graduation Project-icon.png")));
        aboutMenu.add(sysDescItem);
        aboutUsItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/aboutUs-icon.png")));
        aboutMenu.add(aboutUsItem);

        //退出系统
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(UserFrame.this, "是否退出系统？");
                if(choice == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        //添加监听器
        addListener();


        //设置窗体信息
        setVisible(true);
    }

    /**
     * 添加监听器
     */
    public void addListener() {
        UserMenuBarListener listener = new UserMenuBarListener();

        borrowItem.addActionListener(listener);
        returnItem.addActionListener(listener);
        editPassItem.addActionListener(listener);
        sysDescItem.addActionListener(listener);
        aboutUsItem.addActionListener(listener);
    }

    public static void main(String[] args) {

    }
}
