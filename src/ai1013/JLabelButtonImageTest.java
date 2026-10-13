package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JLabelButtonImageTest extends JFrame {
    ImageIcon lblIcon = new ImageIcon("images/dogpi.jpg");
    JLabel lbl = new JLabel();
    Boolean flag = false;

    public JLabelButtonImageTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon btnIcon = new ImageIcon("images/dogi.png");
        JButton btn = new JButton("Click Here!", btnIcon);
        btn.addActionListener(btnListener);

        add("North", btn);
        add("Center", lbl);

        int w = 450, h = 450;
        int[] location = CenterFrame.getLocation(w,h);
        setBounds(location[0],location[1],w,h); //setLocation(w,y) =+ setSize(w, h)
        setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(flag==false){
                lbl.setIcon(lblIcon);
                flag = true;
            }else {
                lbl.setIcon(new ImageIcon());
                flag = false;
            }
        }
    };

    public static void main(String[] args) {
        new JLabelButtonImageTest();
    }
}
