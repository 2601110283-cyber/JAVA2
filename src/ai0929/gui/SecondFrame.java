package ai0929.gui;

import javax.swing.*;
import java.awt.*;

public class SecondFrame extends JFrame{

    public SecondFrame() {
        setLayout(new FlowLayout());
        setTitle("두번째 만든 윈도우창");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labl1 = new JLabel("오늘은 화요일입니다.");
        add(labl1);

        JLabel labl2 = new JLabel("한국폴리텍대학 인공지능 소프트웨어과");
        Font font = new Font("맑은 고딕", Font.BOLD, 25);
        labl2.setFont(font);
        labl2.setForeground(Color.MAGENTA);
        add(labl2);

        JLabel labl3 = new JLabel("1학년 재학중 김민석  바보");
        font = new Font("궁서", Font.BOLD+Font.ITALIC, 20);
        labl3.setFont(font);
        labl3.setForeground(Color.GREEN);
        labl3.setOpaque(true);
        labl3.setBackground(Color.RED);
        add(labl3);


        setSize(500,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SecondFrame();
    }
}
