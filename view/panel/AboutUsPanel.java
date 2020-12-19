package view.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: Aime-toi
 * @Project: AboutUsPanel
 * @Version: 1.0
 * @Date: 2020-07-07 10:43
 * @Description:
 **/
public class AboutUsPanel extends JPanel {

    //单例模式
    public static AboutUsPanel instance = new AboutUsPanel();

    public AboutUsPanel() {
        JLabel aboutUs = new JLabel();
        aboutUs.setIcon(new ImageIcon(AboutUsPanel.class.getResource("/images/us-icon.png")));
        //aboutUs.setSize(icon.getIconWidth(), icon.getIconHeight());
        //aboutUs.setBackground(Color.darkGray);
        add(aboutUs, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.add(AboutUsPanel.instance);
        frame.setVisible(true);
    }
}
