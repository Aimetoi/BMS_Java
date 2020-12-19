package view.frame;

import dao.BookTypeDAO;
import entity.BookType;
import model.BookTypeComboBoxModel;
import model.BookTypeTableModel;
import service.BookService;
import service.BookTypeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: AddBookFrame
 * @Version: 1.0
 * @Date: 2020-07-07 19:23
 * @Description:
 **/
public class AddBookFrame extends JFrame {

    //单例模式
    public static AddBookFrame instance = new AddBookFrame();

    //service层
    BookService service = new BookService();

    //创建输入框
    JTextField bookNameField;
    JTextField authorField;
    JTextField priceField;
    BookTypeComboBoxModel bcm = new BookTypeComboBoxModel();
    JComboBox<BookType> bookTypeComboBox;
    JTextField bookCountField;
    JTextArea bookDescField;

    //创建按钮
    JButton add;
    JButton reset;


    public AddBookFrame() {
        //设置窗体信息
        setTitle("BMS - 图书添加");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/book-BM.png")));

        //初始化面板
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(0, 30, 10, 30));


        //标题面板
        JPanel titlePanel = new JPanel();
        contentPane.add(titlePanel);
        //内容标题
        JLabel labelAddBook = new JLabel("图书添加");
        labelAddBook.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/ADD.png")));
        labelAddBook.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.setPreferredSize(new Dimension(this.getWidth(), 70));
        titlePanel.add(labelAddBook);


        //标签、输入框面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        mainPanel.setPreferredSize(new Dimension(getWidth(), 280));
        contentPane.add(mainPanel);

        //创建图书编号标签
        JLabel labelBookId = new JLabel("图书编号：");
        labelBookId.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book-id-icon.png")));
        mainPanel.add(labelBookId);
        //创建图书编号输入框
        JTextField bookIdField = new JTextField();
        bookIdField.setPreferredSize(new Dimension(150, 30));
        bookIdField.setEditable(false);
        mainPanel.add(bookIdField);

        //创建图书名称标签
        JLabel labelBookName = new JLabel("图书名称：");
        labelBookName.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book-name-icon.png")));
        mainPanel.add(labelBookName);
        //创建图书编号输入框
        bookNameField = new JTextField();
        bookNameField.setPreferredSize(new Dimension(150, 30));
        mainPanel.add(bookNameField);

        //创建图书作者标签
        JLabel labelAuthor = new JLabel("图书作者：");
        labelAuthor.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book-author-icon.png")));
        mainPanel.add(labelAuthor);
        //创建图书作者输入框
        authorField = new JTextField();
        authorField.setPreferredSize(new Dimension(150, 30));
        mainPanel.add(authorField);

        //创建图书价格标签
        JLabel labelPrice = new JLabel("图书价格：");
        labelPrice.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book-price-icon.png")));
        mainPanel.add(labelPrice);
        //创建图书价格输入框
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(150, 30));
        mainPanel.add(priceField);

        //创建图书类别标签
        JLabel labelBookType = new JLabel("图书类别：");
        labelBookType.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookTypeAddmenu-icon.png")));
        mainPanel.add(labelBookType);
        //创建图书类别输入框
        bookTypeComboBox = new JComboBox<>(bcm);
        bookTypeComboBox.setPreferredSize(new Dimension(150, 30));
        mainPanel.add(bookTypeComboBox);

        //创建图书库存标签
        JLabel labelBookCount = new JLabel("图书库存：");
        labelBookCount.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/book-borrow-time-icon.png")));
        mainPanel.add(labelBookCount);
        //创建图书库存输入框
        bookCountField = new JTextField();
        bookCountField.setPreferredSize(new Dimension(150, 30));
        mainPanel.add(bookCountField);

        //创建图书描述标签
        JLabel labelBookDesc = new JLabel("图书描述：");
        labelBookDesc.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookTypeDesc-icon.png")));
        mainPanel.add(labelBookDesc);
        //创建图书描述输入框
        bookDescField = new JTextArea();
        bookDescField.setPreferredSize(new Dimension(450, 80));
        bookDescField.setLineWrap(true);
        mainPanel.add(bookDescField);


        //按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 80, 0));
        contentPane.add(buttonPanel);

        //创建添加按钮
        add = new JButton("添加");
        add.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/add-icon.png")));
        buttonPanel.add(add);

        //创建重置按钮
        reset = new JButton("重置");
        reset.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/refrsh.png")));
        buttonPanel.add(reset);



        //添加按钮事件
        addListener();

        //重置按钮事件
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInput();
            }
        });

        //设置窗体可见性
        setVisible(true);
    }

    /**
     * 添加按钮事件
     */
    public void addListener() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == bookNameField.getText().length() ||
                0 == authorField.getText().length() ||
                0 == priceField.getText().length() ||
                null == bookTypeComboBox.getSelectedItem() ||
                0 == bookCountField.getText().length() ||
                0 == bookDescField.getText().length()) {
                    JOptionPane.showMessageDialog(AddBookFrame.this, "选项不能为空！");
                    return;
                }

                //添加操作
                BookType bookType = (BookType)bookTypeComboBox.getSelectedItem();
                int bookCount = Integer.parseInt(bookCountField.getText());


                if(service.add(bookNameField.getText(), authorField.getText(),
                        priceField.getText(), bookType, bookCount, bookDescField.getText())) {
                    JOptionPane.showMessageDialog(AddBookFrame.this, "添加成功！");
                    return;
                }
            }
        });
    }

    /**
     * 重置输入框内容
     */
    public void resetInput() {
        bookNameField.setText("");
        authorField.setText("");
        priceField.setText("");
        bookCountField.setText("");
        bookDescField.setText("");
    }

    /**
     * 更新数据
     */
    public void updateData() {
        resetInput();

        bcm.cs = new BookTypeService().list();
        bookTypeComboBox.updateUI();
    }

    public static void main(String[] args) {

    }
}
