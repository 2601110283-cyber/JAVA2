package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ListComboBoxTest extends JFrame {
    public ListComboBoxTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        String[] items = {"강아지", "고양이", "토끼", "미어캣", "코알라"};
        JList list = new JList(items);
        JComboBox comboBox = new JComboBox(items);

        add(comboBox);
        add(list);

    // 한줄 입력
        JTextField textField = new JTextField("", 20);

        JTextArea area = new JTextArea("", 5, 20);

        JPasswordField passwordField = new JPasswordField(20);

        add(textField);
        add(area);
        add(passwordField);

        int w = 250, h = 250;
        int[] location = CenterFrame.getLocation(w,h);
        setBounds(location[0],location[1],w,h); //setLocation(w,y) =+ setSize(w, h)
        setVisible(true);
    }

    static void main(String[] args) {
        new ListComboBoxTest();
    }
}
