package view.frame;

import entity.Book;
import entity.BookType;
import model.BookTableModel;
import model.BookTypeComboBoxModel;
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
 * @Project: BorrowBookFrame
 * @Version: 1.0
 * @Date: 2020-07-08 10:47
 * @Description:
 **/
public class BorrowBookFrame extends JFrame {

    //单例模式
    public static BorrowBookFrame instance = new BorrowBookFrame();

    //service层
    BorrowService service = new BorrowService();

    //创建表格数据源
    BookTableModel btm;

    //创建表格
    JTable table;

    //创建搜索按钮
    JButton searchButton;

    //创建确认借阅按钮
    JButton borrowButton;

    //创建comboBox
    BookTypeComboBoxModel bcm = new BookTypeComboBoxModel();
    JComboBox<BookType> bookTypeField;





    public BorrowBookFrame() {
        //设置窗体信息
        setTitle("BMS - 图书借阅");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/book-BM.png")));


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
        JLabel labelBookName = new JLabel("图书名称：");
        searchPanel.add(labelBookName);
        //创建图书名称输入框
        JTextField bookNameField = new JTextField();
        bookNameField.setPreferredSize(new Dimension(120, 30));
        searchPanel.add(bookNameField);

        //创建图书作者标签
        JLabel labelAuthor = new JLabel("图书作者：");
        searchPanel.add(labelAuthor);
        //创建图书作者输入框
        JTextField authorField = new JTextField();
        authorField.setPreferredSize(new Dimension(120, 30));
        searchPanel.add(authorField);

        //创建图书类别标签
        JLabel labelBookType = new JLabel("图书类别：");
        searchPanel.add(labelBookType);
        //创建图书类别输入框
        bookTypeField = new JComboBox<>(bcm);
        bookTypeField.setPreferredSize(new Dimension(120, 30));
        searchPanel.add(bookTypeField);

        //创建按钮
        searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/search-icon.png")));
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchPanel.add(searchButton);


        //创建表格面板
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel);

        //创建表格
        btm = new BookTableModel();
        table = new JTable(btm);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(750, 180));
        add(sp);



        //创建按钮面板
        JPanel borrowPanel = new JPanel();
        contentPane.add(borrowPanel);

        //创建确认借阅按钮
        borrowButton = new JButton("确认借阅");
        borrowButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/ADD.png")));
        borrowButton.setPreferredSize(new Dimension(180, 40));
        borrowPanel.add(borrowButton);


        //添加搜索按钮事件
        addSearchListener();

        //添加确认按钮事件
        addBorrowListener();


        //设置窗体可见性
        setVisible(true);
    }

    /**
     * 获取表格选中当前行的数据
     * @return
     */
    public Book getSelectBook() {
        int index = table.getSelectedRow();
        return BookTableModel.cs.get(index);
    }

    /**
     * 添加搜索按钮事件
     */
    public void addSearchListener() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更改表格的源数据
                //btm.cs = new BookTypeService().list(bookTypeField.getSelectedItem());
                table.updateUI();
            }
        });
    }

    /**
     * 添加确认借阅按钮事件
     */
    public void addBorrowListener() {
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(BorrowBookFrame.this, "请先选择一条记录！");
                    return;
                }

                //确认借阅操作
                Book book = getSelectBook();

                boolean bool = service.add(book);
                if(bool == true) {
                    JOptionPane.showMessageDialog(BorrowBookFrame.this, "借阅成功！");
                } else {
                    JOptionPane.showMessageDialog(BorrowBookFrame.this, "借阅失败！");
                }
                updateData();
            }
        });
    }

    /**
     * 更新数据
     */
    public void updateData() {
        BookTableModel.cs = new BookService().list();
        table.updateUI();
        bcm.cs = new BookTypeService().list();
        bookTypeField.updateUI();
    }

    public static void main(String[] args) {

    }
}
