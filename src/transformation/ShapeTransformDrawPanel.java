package transformation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.List;

public class ShapeTransformDrawPanel extends JPanel {

    private final SimpleShape simpleShape = new SimpleShape();
    private int centerX = this.getWidth() / 2;
    private int centerY = this.getHeight() / 2;

    public ShapeTransformDrawPanel() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePressedAction(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedAction(evt);
            }
        });
    }

    //Add new point to list
    private void mousePressedAction(MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        Point2D clickPoint = new Point2D.Double(x, y);
        simpleShape.addPoint(clickPoint);
        repaint();
    }

    private void mouseReleasedAction(MouseEvent evt) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.removeAll();
        drawLines(g2d);
        drawXYAxis(g2d);
    }

    private void drawLines(Graphics2D g2d) {
        
        List<Point2D> pointList = simpleShape.getPointList();
        int pointListSize = pointList.size();
        Point2D currentPoint;
        Point2D nextPoint;
        for (int i = 0; i < pointListSize-1; i++) {
            currentPoint = pointList.get(i);
            nextPoint = pointList.get(i + 1);

            Line2D line = new Line2D.Double(currentPoint, nextPoint);
            g2d.draw(line);
            //System.out.println("Rysuje linie pomiedzy pktami (" + currentPoint.getX() + "," + currentPoint.getY() + ") do punktu (" + nextPoint.getX() + "," + nextPoint.getY() + ")");
        }
    }

    private void drawXYAxis(Graphics2D g2d){
        int width = this.getWidth();
        int height = this.getHeight();
        centerX = width / 2;
        centerY = height / 2;
        
        Point2D xStartPoint = new Point2D.Double(0,centerY);
        Point2D xEndPoint = new Point2D.Double(width,centerY);
        Line2D xAxis = new Line2D.Double(xStartPoint,xEndPoint);
        Point2D yStartPoint = new Point2D.Double(centerX,0);
        Point2D yEndPoint = new Point2D.Double(centerX,height);
        Line2D yAxis = new Line2D.Double(yStartPoint,yEndPoint);
        g2d.draw(xAxis);
        g2d.draw(yAxis);
    }
    
    public SimpleShape getSimpleShape() {
        return simpleShape;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
    
    
}
