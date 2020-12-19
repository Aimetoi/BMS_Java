package view.frame;

import dao.UserDAO;
import entity.User;
import util.CenterPanel;
import view.listener.AdminMenuBarListener;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: AdminFrame
 * @Version: 1.0
 * @Date: 2020-07-06 23:41
 * @Description:
 **/
public class AdminFrame extends JFrame {

    //单例模式
    public static AdminFrame instance = new AdminFrame();

    //菜单栏
    public JMenuBar menuBar = new JMenuBar();

    //图书类别管理 菜单
    public JMenu typeMenu = new JMenu("图书类别管理");
    public JMenuItem addTypeItem = new JMenuItem("图书类别添加");
    public JMenuItem editTypeItem = new JMenuItem("图书类别维护");

    //图书管理 菜单
    public JMenu bookManageMenu = new JMenu("图书管理");
    public JMenuItem addBookItem = new JMenuItem("图书添加");
    public JMenuItem editBookItem = new JMenuItem("图书维护");

    //统计分析 菜单
    public JMenu statisticMenu = new JMenu("统计分析");
    public JMenuItem borrowStatisticItem = new JMenuItem("借阅分析");

    //系统管理 菜单
    public JMenu sysManageMenu = new JMenu("系统管理");
    public JMenuItem registerItem = new JMenuItem("用户注册");
    public JMenuItem userManageItem = new JMenuItem("用户管理");

    //关于我们 菜单
    public JMenu aboutMenu = new JMenu("关于我们");
    //public JMenuItem weChatItem = new JMenuItem("微信公众号");
    public JMenuItem sysDescItem = new JMenuItem("系统介绍");
    public JMenuItem aboutUsItem = new JMenuItem("关于我们");

    //退出系统 菜单
    public JMenuItem exitMenu = new JMenuItem("退出系统");


    //工作面板
    public CenterPanel workingPanel;


    public AdminFrame() {
        //设置窗体信息
        setTitle("BMS-管理员  当前管理员：" + LoginFrame.user.getId());
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
        typeMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/leibie-icon.png")));
        menuBar.add(typeMenu);
        bookManageMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/book-BM.png")));
        menuBar.add(bookManageMenu);
        statisticMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/tongji-icon.png")));
        menuBar.add(statisticMenu);
        sysManageMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/SystemSetup-icon.png")));
        menuBar.add(sysManageMenu);
        aboutMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Cancellation-icon.png")));
        menuBar.add(aboutMenu);
        exitMenu.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Safe exit-icon.png")));
        menuBar.add(exitMenu);

        //添加子菜单项
        //图书类别
        addTypeItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/add-icon.png")));
        typeMenu.add(addTypeItem);
        editTypeItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/weihu-icon.png")));
        typeMenu.add(editTypeItem);

        //图书添加
        addBookItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/add-icon.png")));
        bookManageMenu.add(addBookItem);
        editBookItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/weihu-icon.png")));
        bookManageMenu.add(editBookItem);

        //借阅分析
        borrowStatisticItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Borrowing statistics-icon.png")));
        statisticMenu.add(borrowStatisticItem);

        //系统管理
        registerItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/register-icon.png")));
        sysManageMenu.add(registerItem);
        userManageItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Personnel management-icon.png")));
        sysManageMenu.add(userManageItem);

        //关于我们
        sysDescItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/Graduation Project-icon.png")));
        aboutMenu.add(sysDescItem);
        aboutUsItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/images/aboutUs-icon.png")));
        aboutMenu.add(aboutUsItem);

        //退出系统
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(AdminFrame.this, "是否退出系统？");
                if(choice == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        //添加监听器
        addListener();

        //设置可见性
        setVisible(true);
    }

    /**
     * 添加监听器
     */
    public void addListener() {
        AdminMenuBarListener listener = new AdminMenuBarListener();

        addTypeItem.addActionListener(listener);
        editTypeItem.addActionListener(listener);
        addBookItem.addActionListener(listener);
        editBookItem.addActionListener(listener);
        borrowStatisticItem.addActionListener(listener);
        registerItem.addActionListener(listener);
        userManageItem.addActionListener(listener);
        sysDescItem.addActionListener(listener);
        aboutUsItem.addActionListener(listener);
    }

    public static void main(String[] args) {

    }
}
