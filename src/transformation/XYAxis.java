/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYAxis extends javax.swing.JFrame {

    int x1, x2, y1, y2;
    boolean drawLine;

    public XYAxis() {
        super();
        XYSeries Goals = new XYSeries("Goals Scored");
        Goals.add(1, 1.0);
        Goals.add(2, 3.0);
        Goals.add(3, 2.0);
        Goals.add(4, 0.0);
        Goals.add(5, 3.0);
        XYDataset xyDataset = new XYSeriesCollection(Goals);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Goals Scored Over Time", "Fixture Number", "Goals",
                null, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(chart);
        cp.setMouseWheelEnabled(true);
        add(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        setXY(100,100,250,500);
    }

    @Override
    public void paint(Graphics g) {
        g = (Graphics2D) g;
        if (drawLine) {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void setXY(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        drawLine = true;
        this.repaint();
    }
}
