package view.frame;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.xml.internal.ws.resources.DispatchMessages;
import entity.BookType;
import model.BookTypeTableModel;
import service.BookTypeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Book;

/**
 * @Author: Aime-toi
 * @Project: EditTypeFrame
 * @Version: 1.0
 * @Date: 2020-07-07 16:20
 * @Description:
 **/
public class EditTypeFrame extends JFrame {

    //单例模式
    public static EditTypeFrame instance = new EditTypeFrame();

    //创建service层
    BookTypeService service = new BookTypeService();

    //创建表格
    public JTable table;

    //创建搜索域文本框
    public JTextField editTypeField;

    //创建显示区域文本框
    public JTextField idField;
    public JTextField typeNameField;
    public JTextArea descField;

    //创建按钮
    JButton edit;
    JButton delete;

    public EditTypeFrame() {
        //设置窗体信息
        setTitle("BMS - 图书类别维护");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/leibie-icon.png")));

        //初始化面板
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(20, 30, 10, 30));


        //标题、输入框、按钮面板
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        contentPane.add(titlePanel);

        //创建内容标签
        JLabel labelEditType = new JLabel("图书类别名称：");
        labelEditType.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/BookiconMax.png")));
        labelEditType.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.add(labelEditType);
        //创建输入框
        editTypeField = new JTextField();
        editTypeField.setPreferredSize(new Dimension(200, 40));
        editTypeField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.add(editTypeField);
        //创建按钮
        JButton searchButton = new JButton("查询");
        searchButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/search-icon.png")));
        searchButton.setPreferredSize(new Dimension(130, 40));
        titlePanel.add(searchButton);


        //创建表格面板
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel);

        //创建表格
        BookTypeTableModel btm = new BookTypeTableModel();
        table = new JTable(btm);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 200));
        add(sp);


        //数据显示区域面板
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        contentPane.add(editPanel);

        //创建编号标签、文本框
        JLabel labelId = new JLabel("编号：");
        editPanel.add(labelId);
        //创建编号文本框
        idField = new JTextField();
        idField.setEditable(false);
        idField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(idField);

        //创建图书类别名称标签、文本框
        JLabel labelTypeName = new JLabel("图书类别名称：");
        editPanel.add(labelTypeName);
        //创建图书类别名称文本框
        typeNameField = new JTextField();
        typeNameField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(typeNameField);


        //创建描述面板
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        contentPane.add(descPanel);

        //创建描述标签、文本框
        JLabel labelDesc = new JLabel("描述：");
        descPanel.add(labelDesc);
        //创建图书类别名称文本框
        descField = new JTextArea();
        descField.setPreferredSize(new Dimension(530, 60));
        descPanel.add(descField);


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


        //添加监听器
        //搜索按钮
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更改表格的源数据
                btm.cs = new BookTypeService().list(editTypeField.getText());
                table.updateUI();
            }
        });

        //表格点击事件
        addTableListener();

        //修改按钮事件
        addModifyListener();

        //删除按钮事件
        addDeleteListener();




        //设置窗体可见性
        setVisible(true);
    }

    /**
     * 更新数据
     */
    public void updateData() {
        BookTypeTableModel.cs = service.list();
        resetInput();
        table.updateUI();
    }

    /**
     * 获取表格选中当前行的数据
     * @return
     */
    public BookType getSelectBookType() {
        int index = table.getSelectedRow();
        return BookTypeTableModel.cs.get(index);
    }

    /**
     * 修改按钮事件
     */
    public void addModifyListener() {
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == idField.getText().length()) {
                    JOptionPane.showMessageDialog(EditTypeFrame.this, "请先选择一条记录！");
                    return;
                }

                //修改操作
                int id = Integer.parseInt(idField.getText());
                String typeName = typeNameField.getText();
                String typeDesc = descField.getText();
                service.update(id, typeName, typeDesc);
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
                if(0 == idField.getText().length()) {
                    JOptionPane.showMessageDialog(EditTypeFrame.this, "请先选择一条记录！");
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(EditTypeFrame.this, "是否删除？");
                if(choice != JOptionPane.OK_OPTION) {
                    return;
                }

                //删除操作
                int id = Integer.parseInt(idField.getText());
                service.delete(id);

                //更新表格
                updateData();
            }
        });
    }

    /**
     * 表格点击事件
     */
    public void addTableListener() {
        //表格点击事件
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookType bookType = getSelectBookType();

                //将数据放入到表格中
                idField.setText(String.valueOf(bookType.getId()));
                typeNameField.setText(bookType.getBookTypeName());
                descField.setText(bookType.getBookTypeDesc());
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

    /**
     * 重置输入框
     */
    public void resetInput() {
        editTypeField.setText("");
        idField.setText("");
        typeNameField.setText("");
        descField.setText("");
    }

    public static void main(String[] args) {

    }

}
