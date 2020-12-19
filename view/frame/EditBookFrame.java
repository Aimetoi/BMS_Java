package view.frame;

import dao.BookTypeDAO;
import entity.Book;
import entity.BookType;
import model.BookTableModel;
import model.BookTypeComboBoxModel;
import model.BookTypeTableModel;
import service.BookService;
import service.BookTypeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * @Author: Aime-toi
 * @Project: EditBookFrame
 * @Version: 1.0
 * @Date: 2020-07-07 21:30
 * @Description:
 **/
public class EditBookFrame extends JFrame {

    //单例模式
    public static EditBookFrame instance = new EditBookFrame();

    //service层
    BookService service = new BookService();

    //创建comboBox
    BookTypeComboBoxModel bcm = new BookTypeComboBoxModel();

    //创建表格
    JTable table;

    //创建输入框
    JTextField bookIdField;
    JTextField editBookNameField;
    JTextField editAuthorField;
    JTextField editPriceField;
    JComboBox<BookType> editBookTypeField;
    JTextField editCountField;
    JTextArea bookDescField;

    //创建按钮
    JButton edit;
    JButton delete;

    public EditBookFrame() {
        //设置窗体信息
        setTitle("BMS - 图书维护");
        setSize(800, 700);
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
        JComboBox bookTypeField = new JComboBox(bcm);
        bookTypeField.setPreferredSize(new Dimension(120, 30));
        searchPanel.add(bookTypeField);

        //创建按钮
        JButton searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/search-icon.png")));
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchPanel.add(searchButton);



        //创建表格面板
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel);

        //创建表格
        BookTableModel btm = new BookTableModel();
        table = new JTable(btm);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(750, 180));
        add(sp);


        //数据显示区域面板
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        editPanel.setPreferredSize(new Dimension(getWidth(), 280));
        contentPane.add(editPanel);

        //创建编号标签、文本框
        JLabel labelBookId = new JLabel("图书编号：");
        editPanel.add(labelBookId);
        //创建编号文本框
        bookIdField = new JTextField();
        bookIdField.setPreferredSize(new Dimension(200, 30));
        bookIdField.setEditable(false);
        editPanel.add(bookIdField);

        //创建名称标签、文本框
        JLabel labelEditBookName = new JLabel("图书名称：");
        editPanel.add(labelEditBookName);
        //创建名称文本框
        editBookNameField = new JTextField();
        editBookNameField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(editBookNameField);

        //创建作者标签、文本框
        JLabel labelEditAuthor = new JLabel("图书作者：");
        editPanel.add(labelEditAuthor);
        //创建作者文本框
        editAuthorField = new JTextField();
        editAuthorField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(editAuthorField);

        //创建价格标签、文本框
        JLabel labelPrice = new JLabel("图书价格：");
        editPanel.add(labelPrice);
        //创建价格文本框
        editPriceField = new JTextField();
        editPriceField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(editPriceField);

        //创建类别标签、文本框
        JLabel labelEditBookType = new JLabel("图书类别：");
        editPanel.add(labelEditBookType);
        //创建类别文本框

        editBookTypeField = new JComboBox<>(bcm);
        editBookTypeField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(editBookTypeField);

        //创建库存标签、文本框
        JLabel labelCount = new JLabel("图书库存：");
        editPanel.add(labelCount);
        //创建库存文本框
        editCountField = new JTextField();
        editCountField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(editCountField);

        //创建图书描述标签
        JLabel labelBookDesc = new JLabel("图书描述：");
        editPanel.add(labelBookDesc);
        //创建图书描述输入框
        bookDescField = new JTextArea();
        bookDescField.setPreferredSize(new Dimension(560, 80));
        bookDescField.setLineWrap(true);
        editPanel.add(bookDescField);


        //创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 80, 0));
        contentPane.add(buttonPanel);

        //创建登录按钮
        edit = new JButton("修改");
        edit.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/modify-max-icon.png")));
        buttonPanel.add(edit);

        //创建重置按钮
        delete = new JButton("删除");
        delete.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookDelect-icon.png")));
        buttonPanel.add(delete);


        //添加表格监听器
        addTableListener();

        //修改按钮事件
        addModifyListener();

        //删除按钮事件
        addDeleteListener();


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
     * 修改按钮事件
     */
    public void addModifyListener() {
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == bookIdField.getText().length()) {
                    JOptionPane.showMessageDialog(EditBookFrame.this, "请先选择一条记录！");
                    return;
                }

                //修改操作
                int id = Integer.parseInt(bookIdField.getText());
                String bookName = editBookNameField.getText();
                String author = editAuthorField.getText();
                String price = editPriceField.getText();
                //图书类别id
                BookType bookType = (BookType)editBookTypeField.getSelectedItem();
                int bookCount = Integer.parseInt(editCountField.getText());
                String bookDesc = bookDescField.getText();

                service.update(id, bookName, author, price, bookType, bookCount, bookDesc);
                //更新表格
                updateData();
            }
        });
    }

    /**
     * 删除按钮事件
     */
    public void addDeleteListener() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == bookIdField.getText().length()) {
                    JOptionPane.showMessageDialog(EditBookFrame.this, "请先选择一条记录！");
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(EditBookFrame.this, "是否删除？");
                if(choice != JOptionPane.OK_OPTION) {
                    return;
                }

                //删除操作
                int id = Integer.parseInt(bookIdField.getText());
                service.delete(id);
                //更新表格
                updateData();
            }
        });
    }

    /**
     * 更新数据
     */
    public void updateData() {
        BookTableModel.cs = service.list();
        table.updateUI();
        bcm.cs = new BookTypeService().list();
        editBookTypeField.updateUI();
        bookIdField.updateUI();
    }

    /**
     * 表格点击事件
     */
    public void addTableListener() {
        //表格点击事件
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Book book = getSelectBook();

                //将数据放入到表格中
                String id = String.valueOf(book.getBookId());
                bookIdField.setText(id);
                editBookNameField.setText(book.getBookName());
                editAuthorField.setText(book.getBookAuthor());
                editPriceField.setText(book.getBookPrice());
                //图书类别
                int bookTypeId = book.getBookTypeId();
                BookType bookType = new BookTypeService().getById(bookTypeId);
                int index = new BookTypeComboBoxModel().indexOf(bookType);
                System.out.println(index);
                editBookTypeField.setSelectedIndex(index);
                editBookTypeField.updateUI();

                String count = String.valueOf(book.getBookCount());
                editCountField.setText(count);
                bookDescField.setText(book.getBookDesc());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public static void main(String[] args) {

    }
}
