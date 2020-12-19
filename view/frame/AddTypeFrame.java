package view.frame;

import service.BookTypeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: AddTypePanel
 * @Version: 1.0
 * @Date: 2020-07-07 14:45
 * @Description:
 **/
public class AddTypeFrame extends JFrame {

    //单例模式
    public static AddTypeFrame instance = new AddTypeFrame();

    //service层
    BookTypeService service = new BookTypeService();

    //创建输入框
    JTextField typeNameField;
    JTextArea typeDescField;

    public AddTypeFrame() {
        //设置窗体信息
        setTitle("BMS - 图书类型添加");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/leibie-icon.png")));

        //初始化面板
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(0, 30, 10, 30));


        //标题面板
        JPanel titlePanel = new JPanel();
        contentPane.add(titlePanel);
        //内容标题
        JLabel labelAddType = new JLabel("图书类别添加");
        labelAddType.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookTypeAdd-icon.png")));
        labelAddType.setFont(new Font("微软雅黑", Font.BOLD, 27));
        titlePanel.add(labelAddType);


        //图书类别标签、输入框面板
        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.setLayout(new GridLayout(1, 2));
        contentPane.add(nameFieldPanel);

        //创建图书类别名称标签
        JLabel labelTypeName = new JLabel("图书类别名称：");
        labelTypeName.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookTypeAddmenu-icon.png")));
        labelTypeName.setFont(new Font("微软雅黑", Font.BOLD, 20));
        nameFieldPanel.add(labelTypeName);
        //创建图书类别名称输入框
        typeNameField = new JTextField();
        typeNameField.setPreferredSize(new Dimension(200, 30));
        typeNameField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        nameFieldPanel.add(typeNameField);


        //图书类别标签、输入框面板
        JPanel descFieldPanel = new JPanel();
        descFieldPanel.setLayout(new GridLayout(1, 2));
        contentPane.add(descFieldPanel);

        //创建图书类别描述标签
        JLabel labelTypeDesc = new JLabel("图书类别描述：");
        labelTypeDesc.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/bookTypeDesc-icon.png")));
        labelTypeDesc.setFont(new Font("微软雅黑", Font.BOLD, 20));
        descFieldPanel.add(labelTypeDesc);
        //创建图书类别描述输入框
        typeDescField = new JTextArea();
        //自动换行
        typeDescField.setLineWrap(true);
        typeDescField.setWrapStyleWord(true);

        typeDescField.setPreferredSize(new Dimension(200, 150));
        typeDescField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        descFieldPanel.add(typeDescField);


        //按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 80, 0));
        contentPane.add(buttonPanel);

        //创建添加按钮
        JButton add = new JButton("添加");
        add.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/add-icon.png")));
        buttonPanel.add(add);

        //创建重置按钮
        JButton reset = new JButton("重置");
        reset.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/refrsh.png")));
        buttonPanel.add(reset);



        //添加监听器
        //添加按钮
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(0 == typeNameField.getText().length() || 0 == typeDescField.getText().length()) {
                    JOptionPane.showMessageDialog(AddTypeFrame.this, "图书类型名称和描述不能为空！");
                    return;
                }

                //添加
                if(service.add(typeNameField.getText(), typeDescField.getText())) {
                    JOptionPane.showMessageDialog(AddTypeFrame.this, "添加成功！");
                    return;
                } else {
                    JOptionPane.showMessageDialog(AddTypeFrame.this, "添加失败！");
                }
            }
        });

        //重置按钮
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeNameField.setText("");
                typeDescField.setText("");
            }
        });

        //设置窗体可见性
        setVisible(true);
    }

    /**
     * 重置输入框
     */
    public void resetInput() {
        typeNameField.setText("");
        typeDescField.setText("");
    }


    public static void main(String[] args) {

    }
}
