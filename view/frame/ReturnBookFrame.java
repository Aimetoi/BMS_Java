package view.frame;

import entity.Book;
import entity.Borrow;
import model.BookTableModel;
import model.BorrowTableModel;
import service.BookService;
import service.BookTypeService;
import service.BorrowService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: ReturnBookFrame
 * @Version: 1.0
 * @Date: 2020-07-08 11:02
 * @Description:
 **/
public class ReturnBookFrame extends JFrame {

    //单例模式
    public static ReturnBookFrame instance = new ReturnBookFrame();

    //service层
    BorrowService service = new BorrowService();

    //创建数据源
    BorrowTableModel btm = new BorrowTableModel();
    JTable table;

    //创建按钮
    JButton searchButton;
    JButton returnButton;

    public ReturnBookFrame() {
        //设置窗体信息
        setTitle("BMS - 图书归还");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/归还.png")));


        //初始化面板
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(0, 30, 10, 30));


        //标签、输入框面板
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
        searchPanel.setPreferredSize(new Dimension(getWidth(), 70));
        contentPane.add(searchPanel);

        //创建图书名称标签
        JLabel labelBookName = new JLabel("点击查询借阅的图书");
        labelBookName.setFont(new Font("微软雅黑", Font.BOLD, 20));
        searchPanel.add(labelBookName);
        //创建按钮
        searchButton = new JButton("查询全部");
        searchButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/search-icon.png")));
        searchButton.setPreferredSize(new Dimension(150, 40));
        searchPanel.add(searchButton);


        //创建表格面板
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel);

        //创建表格
        table = new JTable(btm);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(750, 180));
        add(sp);





        //创建按钮面板
        JPanel returnPanel = new JPanel();
        contentPane.add(returnPanel);

        //创建确认归还按钮
        returnButton = new JButton("确认归还");
        returnButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/ADD.png")));
        returnButton.setPreferredSize(new Dimension(180, 40));
        returnPanel.add(returnButton);



        //添加归还按钮事件
        addReturnListener();


        //设置窗体可见性
        setVisible(true);
    }

    /**
     * 添加归还按钮事件
     */
    public void addReturnListener() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(ReturnBookFrame.this, "请先选择一条记录！");
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(ReturnBookFrame.this, "是否删除？");
                if(choice != JOptionPane.OK_OPTION) {
                    return;
                }

                //确认归还操作
                Borrow borrow = getSelectBook();

                service.delete(borrow);
                updateData();
            }
        });
    }

    /**
     * 获取表格选中当前行的数据
     * @return
     */
    public Borrow getSelectBook() {
        int index = table.getSelectedRow();
        return BorrowTableModel.cs.get(index);
    }

    /**
     * 更新数据
     */
    public void updateData() {
        BorrowTableModel.cs = new BorrowService().list();
        table.updateUI();
    }

    public static void main(String[] args) {

    }
}
