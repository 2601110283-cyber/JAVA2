package ai1103;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class MenuTestwork extends JFrame {
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame;
    // [추가] save 아이콘 추가 (toolbar/save.png)
    String[] iconFileNames =  {"new", "open", "save", "close"};
    Color[] colorNames = {Color.WHITE, Color.BLUE, Color.MAGENTA};
    JButton[] btns = new JButton[7]; // 아이콘 4개 + 색상 3개

    // [추가] 현재 열려 있는(또는 저장된) 파일을 기억 -> Save 할 때 사용
    File currentFile = null;

    public MenuTestwork() {
        frame = this;
        btns = new JButton[7];

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        JToolBar toolBar = new JToolBar();
        add("North", toolBar);

        for (int i = 0; i < btns.length; i++) {
            if (i < iconFileNames.length) {
                btns[i] = new JButton(new ImageIcon("toolbar/" + iconFileNames[i] + ".png"));
            } else {
                btns[i] = new JButton("  ");
                btns[i].setBackground(colorNames[i - iconFileNames.length]);
            }
            btns[i].addActionListener(btnListener);
            if (i < iconFileNames.length) btns[i].setToolTipText(iconFileNames[i]); // [추가] 마우스 올리면 이름 표시
            toolBar.add(btns[i]);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        // [추가] Save, Save As 메뉴 항목
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        JMenuItem closeMenuItem = new JMenuItem("Close");

        // [추가] 단축키 Ctrl+S 로 저장
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);     // [추가]
        fileMenu.add(saveAsMenuItem);   // [추가]
        fileMenu.addSeparator();        // [추가] 구분선
        fileMenu.add(closeMenuItem);

        newMenuItem.addActionListener(fileListener);
        openMenuItem.addActionListener(fileListener);
        saveMenuItem.addActionListener(fileListener);    // [추가]
        saveAsMenuItem.addActionListener(fileListener);  // [추가]
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
            if (eBtn == btns[0]) {
                newFile();
            } else if (eBtn == btns[1]) {
                openFile();
            } else if (eBtn == btns[2]) {   // [추가] 저장 버튼
                saveFile();
            } else if (eBtn == btns[3]) {
                System.exit(0);
            }
            for (int i = 0; i < colorNames.length; i++) {
                if (eBtn == btns[i + iconFileNames.length]) {
                    textArea.setBackground(colorNames[i]);
                }
            }
        }
    };

    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "New":
                    newFile();
                    break;
                case "Open":
                    openFile();
                    break;
                case "Save":        // [추가]
                    saveFile();
                    break;
                case "Save As":     // [추가]
                    saveAsFile();
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

    // ---------- 기능 메서드 ----------

    // 새 파일: 내용 지우고 현재 파일 정보 초기화
    public void newFile() {
        textArea.setText("");
        currentFile = null;
        setTitle("메뉴만들기");
    }

    // 열기: 취소 버튼 눌렀을 때는 아무것도 안 하도록 반환값 확인
    public void openFile() {
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textArea.setText(readFile(selectedFile));
            currentFile = selectedFile;
            setTitle("메뉴만들기 - " + selectedFile.getName());
        }
    }

    // [추가] 저장: 이미 열린/저장된 파일이 있으면 그 파일에 덮어쓰고, 없으면 Save As 실행
    public void saveFile() {
        if (currentFile == null) {
            saveAsFile();
        } else {
            writeFile(currentFile);
        }
    }

    // [추가] 다른 이름으로 저장: 저장 대화상자를 띄워 파일 이름을 받아 저장
    public void saveAsFile() {
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // 이미 있는 파일이면 덮어쓸지 확인
            if (selectedFile.exists()) {
                int answer = JOptionPane.showConfirmDialog(frame,
                        selectedFile.getName() + " 파일이 이미 있습니다. 덮어쓸까요?",
                        "저장 확인", JOptionPane.YES_NO_OPTION);
                if (answer != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            writeFile(selectedFile);
            currentFile = selectedFile;
            setTitle("메뉴만들기 - " + selectedFile.getName());
        }
    }

    // [추가] 텍스트 영역의 내용을 파일에 쓰기
    public void writeFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textArea.getText());
            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(frame, file.getName() + " 저장 완료");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "저장 실패: " + e.getMessage());
        }
    }

    public String readFile(File selectedFile){
        String totalLines = "";
        try {
            String line = "";
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null){
                totalLines += line + "\n";
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            totalLines = "File not found";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalLines;
    }

    public static void main(String[] args) {
        new MenuTestwork();
    }
}