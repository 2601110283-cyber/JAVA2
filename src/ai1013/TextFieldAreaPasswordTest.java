package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;

public class TextFieldAreaPasswordTest extends JFrame {
    public TextFieldAreaPasswordTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

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
        new TextFieldAreaPasswordTest();
    }
}
