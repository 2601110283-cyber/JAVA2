package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListComboBoximageTest extends JFrame {
    JLabel lbl = new JLabel();

    public ListComboBoximageTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] items = { "고양이","코알라", "토끼", "미어캣", "고릴라"};;

        JComboBox comboBox = new JComboBox(items);

        comboBox.addItemListener(comboListener);

        add("North", comboBox);
        add("Center", lbl);


        int w = 250, h = 250;
        int[] location = CenterFrame.getLocation(w,h);
        setBounds(location[0],location[1],w,h); //setLocation(w,y) =+ setSize(w, h)
        setVisible(true);
    }

    ItemListener comboListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            ImageIcon icon = null;
            int imgPath = 0;
            String selectedItem = (String) e.getItem();
            switch (selectedItem){
                case "고양이":
                    imgPath = 1;
                    break;
                case "코알라":
                    imgPath = 2;
                    break;
                case "토끼":
                    imgPath = 3;
                    break;
                case "미어캣":
                    imgPath = 4;
                    break;
                case "고릴라":
                    imgPath = 5;
                    break;
            }
            icon = new ImageIcon("pet/pet0"+ imgPath+".jpg");
            lbl.setIcon(icon);

        }
    };

    static void main(String[] args) {
        new ListComboBoximageTest();
    }
}
