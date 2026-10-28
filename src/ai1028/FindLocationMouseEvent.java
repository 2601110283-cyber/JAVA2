package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindLocationMouseEvent extends JFrame {
    JTextField textX,textY;

    public FindLocationMouseEvent(){
        JPanel panel = new JPanel();
        textX = new JTextField(5);
        textY = new JTextField(5);

        panel.add(textX);
        panel.add(textY);
        add("South",panel);
        addMouseListener(frameMouseListener);
        addMouseMotionListener(frameMouseMotionListener);

        SetFrame.setFrame(this,"마우스이벤트 발생 위치출력",500,500);
    }

    MouseListener frameMouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            textX.setText(e.getX()+"");
            textY.setText(e.getY()+"");
        }
    };

    MouseMotionListener frameMouseMotionListener = new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            textX.setText(e.getX()+"");
            textY.setText(e.getY()+"");
        }
    };

    public static void main(String[] args) {
        new FindLocationMouseEvent();
    }
}
