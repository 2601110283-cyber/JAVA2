package ai1013;

import center.CenterFrame;
import center.CenterFrame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlowLatoutTest extends JFrame {

    ArrayList<JButton> list = new ArrayList<JButton>(5);
    boolean flag = true;
    // 이미지에 맞춰 5개의 색상 배열 구성 (빨, 주, 노, 초, 파)
    Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

    public FlowLatoutTest() {
        setTitle("FlowLatout 연습");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        for (int i = 0; i < 5; i++) {
            JButton btn = new JButton("버튼"+(i+1));
            list.add(btn);
            btn.addActionListener(btnListener);
            add(btn);
        }

        int w = 250, h = 250;
        Point point = CenterFrame2.getLocation(w,h);
        setBounds(point.x, point.y, w, h);
        setVisible(true);
    }

    // 이미지에 나타난 코드 로직 반영
    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();

            if (flag) {
                // 클릭한 버튼의 인덱스를 찾아 colors 배열에서 해당 색상을 적용
                selectedBtn.setBackground(colors[list.indexOf(selectedBtn)]);
                flag = false;
            } else {
                selectedBtn.setBackground(Color.LIGHT_GRAY);
                flag = true;
            }
        }
    };

    public static void main(String[] args) {
        new FlowLatoutTest();
    }
}