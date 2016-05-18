package grafikak2;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Histogram extends JFrame {
    
    private int[] grayValue;

    public int[] getGrayValue() {
        return grayValue;
    }
    
    public Histogram(int [][]pixels){
        IntervalXYDataset ds = createDataSet(pixels);
        createHistogram(ds);
    }
    
    //Pixels should come from GRAY SCALE image!
    private IntervalXYDataset createDataSet(int [][]pixels){
        grayValue = new int[256];
        int pixelGrayValue;
        Color c;
        
        //Count gray values from given pixels array
        for (int[] pixel : pixels) {
            for (int j = 0; j < pixel.length; j++) {
                c = new Color(pixel[j]);
                pixelGrayValue = c.getRed();
                grayValue[pixelGrayValue]++;
            }
        }
        
        IntervalXYDataset dataset = convertGrayValues(grayValue);
        return dataset;
    }
    
    private IntervalXYDataset convertGrayValues(int []grayValues){
        final XYSeries series = new XYSeries("Image Data");
        
        for(int i=0;i<grayValues.length;i++){
            series.add(i, grayValues[i]);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
    
    private void createHistogram(IntervalXYDataset ds) {   
        JFreeChart chart = ChartFactory.createHistogram("Histogram",
                "Gray level", "Frequency", ds, PlotOrientation.VERTICAL, false, true,
                false);
        
        ChartPanel cp = new ChartPanel(chart);
        changeBarColor(chart.getXYPlot(), Color.blue);
        setHistogramXaxisBounds(0, 255, chart.getXYPlot());
        //Resizing
        this.getContentPane().add(cp);
        Dimension d = new Dimension(500,500);
        cp.setSize(d);
        this.setSize(d);
        this.setVisible(true);
    }
    
    private void changeBarColor(XYPlot plot, Color c){
        plot.getRenderer().setSeriesPaint(0, c);
    }
    
    private void setHistogramXaxisBounds(int minimum, int maximum,XYPlot plot){
        plot.getDomainAxis().setRange(minimum, maximum);
    }

}
