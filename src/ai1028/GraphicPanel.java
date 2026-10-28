package ai1028;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class GraphicPanel extends JFrame {

    public GraphicPanel(){
        add("Center", new GraphicPanel());
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.GREEN);
        g.drawLine(50,30, 250, 30);
        g.setColor(Color.ORANGE);
        g.fillRect(30, 50, 200, 250);
        g.drawRect(250,50, 200, 200);

        g.setColor(Color.MAGENTA);
        g.drawOval(30,50, 200,250);
        g.fillOval(250,50, 200,200);
        g.setColor(Color.BLUE);
        int[] xpoints = {350, 250, 450};
        int[] ypoints = {50, 150, 150};
        g.fillPolygon(xpoints, ypoints,xpoints.length);

        ypoints[0] = 250;
        g.fillPolygon(xpoints, ypoints, xpoints.length);
    }
}
