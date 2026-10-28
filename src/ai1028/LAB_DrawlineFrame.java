package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;

public class LAB_DrawlineFrame extends JFrame {
    LAB_ColorPaper colorPaper;

    public LAB_DrawlineFrame() {
        colorPaper = new LAB_ColorPaper();
        add("Center",colorPaper);

        SetFrame.setFrame(this,"마우스로 선그리기",500,500);
    }

    public static void main(String[] args) {
        new LAB_DrawlineFrame();
    }
}
