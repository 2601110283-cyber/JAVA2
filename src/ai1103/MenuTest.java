package ai1103;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MenuTest extends JFrame {
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame;
    String[] iconFileNames =  {"new", "open", "close"};
    Color[] colorNames = {Color.WHITE, Color.BLUE, Color.MAGENTA};
    JButton[] btns = new JButton[6];

    public MenuTest() {
        frame = this;
        // 수정 2: 중복 선언(JButton[]) 제거
        btns = new JButton[6];

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        JToolBar toolBar = new JToolBar();
        add("North", toolBar);

        // 수정 1: 배열 인덱스 초과 에러 해결 및 리스너 일괄 등록
        for (int i = 0; i < btns.length; i++) {
            if (i < 3) {
                btns[i] = new JButton(new ImageIcon("toolbar/" + iconFileNames[i] + ".png"));
            } else {
                btns[i] = new JButton("  ");
                btns[i].setBackground(colorNames[i - 3]);
            }
            btns[i].addActionListener(btnListener); // 툴바 버튼들에 리스너 연결
            toolBar.add(btns[i]);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // 수정 3: 리스너의 case "New": 와 대소문자 맞춤
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem closeMenuItem = new JMenuItem("Close");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(closeMenuItem);

        newMenuItem.addActionListener(fileListener);
        openMenuItem.addActionListener(fileListener);
        closeMenuItem.addActionListener(fileListener);

        JMenuItem whiteMenuItem = new JMenuItem("White");
        JMenuItem blueMenuItem = new JMenuItem("Blue");
        JMenuItem magentaMenuItem = new JMenuItem("Magenta");
        editMenu.add(whiteMenuItem);
        editMenu.add(blueMenuItem);
        editMenu.add(magentaMenuItem);

        whiteMenuItem.addActionListener(backgroundListener);
        blueMenuItem.addActionListener(backgroundListener);
        magentaMenuItem.addActionListener(backgroundListener);

        SetFrame.setFrame(this, "메뉴만들기", 500, 500);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton eBtn = (JButton) e.getSource();
            File selectedFile;
            if (eBtn == btns[0]) {
                textArea.setText("");
            } else if (eBtn == btns[1]) {
                fileChooser.showOpenDialog(frame);
                selectedFile = fileChooser.getSelectedFile();
                if(selectedFile != null) { // 파일 선택 취소 시 에러 방지
                    textArea.setText(readFile(selectedFile));
                }
                // 수정 3: btns[1]이 중복되어 있던 것을 btns[2]로 수정
            } else if (eBtn == btns[2]) {
                System.exit(0);
            }
            for (int i = 0; i < colorNames.length; i++) {
                if (eBtn == btns[i + 3]) {
                    textArea.setBackground(colorNames[i]);
                }
            }
        }
    };

    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile;
            switch (e.getActionCommand()){
                case "New":
                    textArea.setText("");
                    break;
                case "Open":
                    fileChooser.showOpenDialog(frame);
                    selectedFile = fileChooser.getSelectedFile();
                    if(selectedFile != null) {
                        textArea.setText(readFile(selectedFile));
                    }
                    break;
                case "Close":
                    System.exit(0);
                    break;
            }
        }
    };

    ActionListener backgroundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = null;
            switch (e.getActionCommand()){
                // 수정 3: 메뉴 아이템 생성 시 "White"로 만들었으므로 대문자 W로 수정
                case "White":
                    color = Color.WHITE;
                    break;
                case "Blue":
                    color = Color.BLUE;
                    break;
                case "Magenta":
                    color = Color.MAGENTA;
                    break;
            }
            textArea.setBackground(color);
        }
    };

    public String readFile(File selectedFile){
        String totalLines = "";
        try {
            String line = "";
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null){
                totalLines += line + "\n"; // 줄바꿈 기호 추가
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            textArea.setText("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalLines;
    }

    public static void main(String[] args) {
        new MenuTest();
    }
}