package view.listener;

import jdk.nashorn.internal.ir.ReturnNode;
import view.frame.*;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Aime-toi
 * @Project: UserMenuBarListener
 * @Version: 1.0
 * @Date: 2020-07-08 10:22
 * @Description:
 **/
public class UserMenuBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        UserFrame f = UserFrame.instance;
        JMenuItem i =(JMenuItem)e.getSource();

        //图书借阅
        if(i == f.borrowItem) {
            BorrowBookFrame.instance.setVisible(true);
            BorrowBookFrame.instance.updateData();
            return;
        }

        //图书归还
        if(i == f.returnItem) {
            ReturnBookFrame.instance.setVisible(true);
            ReturnBookFrame.instance.updateData();
            return;
        }

        //修改密码
        if(i == f.editPassItem) {
            JOptionPane.showMessageDialog(f, "修改密码");
        }

        //系统介绍
        if(i == f.sysDescItem) {
            JOptionPane.showMessageDialog(f, "系统介绍");
        }

        //关于我们
        if(i == f.aboutUsItem) {
            f.workingPanel.show(AboutUsPanel.instance);
        }
    }
}
