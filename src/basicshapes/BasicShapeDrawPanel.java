package basicshapes;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

/* Learning log
Nieprymitywne typy danych, przekazywane jako parametry sa referencja do nich, w przeciwienstwie to typow prostych, ktore sa przekazywane przez wartosc.
Przy przesunieciu myszki event mouseClicked zostaje anulowany!
 */
public class BasicShapeDrawPanel extends JPanel {

    private String shapeToPaint;
    private String mouseMode;
    double diameter;
    

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setMouseMode(String mouseMode) {
        this.mouseMode = mouseMode;
    }

    public String getMouseMode() {
        return mouseMode;
    }

    List<Point> drawPoints;
    Ellipse2D circle;
    Rectangle2D rect;
    Line2D line;

    public List<Point> getDrawPoints() {
        return drawPoints;
    }

    public String getShapeToPaint() {
        return shapeToPaint;
    }

    public Ellipse2D getCircle() {
        return circle;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public Line2D getLine() {
        return line;
    }

    public BasicShapeDrawPanel() {
        super();
        shapeToPaint = "Brak";
        mouseMode = "Paint";
        drawPoints = new ArrayList();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        /*
        mouseMode = Paint - mouse painting
        mouseMode = Drag - drag painiting, paint from current shape
        mouseMode = TextFieldInputPaint - paint using values from text fields
         */
        if (drawPoints.size() == 2 || drawPoints.size() == 1) {
            if (mouseMode.compareTo("Paint") == 0) {
                if (shapeToPaint.compareTo("Linia") == 0) {
                    drawLine(g2d);
                } else if (shapeToPaint.compareTo("Koło") == 0) {
                    drawCircleFromPoints(g2d);
                } else if (shapeToPaint.compareTo("Prostokąt") == 0) {
                    drawRectangle(g2d);
                }
            } else if (mouseMode.compareTo("Drag") == 0) {
                if (shapeToPaint.compareTo("Linia") == 0) {
                    drawLine(g2d);
                } else if (shapeToPaint.compareTo("Koło") == 0) {
                    redrawCircle(g2d);
                } else if (shapeToPaint.compareTo("Prostokąt") == 0) {
                    redrawRectangle(g2d);
                }
            } else if (mouseMode.compareTo("TextFieldInputPaint") == 0) {
                if (shapeToPaint.compareTo("Linia") == 0) {
                    drawLine(g2d);
                } else if (shapeToPaint.compareTo("Koło") == 0) {
                    drawCircleWithDiameter(g2d);
                } else if (shapeToPaint.compareTo("Prostokąt") == 0) {
                    drawRectangle(g2d);
                }
                setMouseMode("Paint");
            }
        }
    }

    //Draw a line using drawPoints list
    public void drawLine(Graphics2D g2d) {

        line = new Line2D.Double(drawPoints.get(0), drawPoints.get(1));
        g2d.draw(line);
    }

    //Draw rectangle using drawPoints list
    public void drawRectangle(Graphics2D g2d) {
        double x1, y1, x2, y2, width, height;
        Point point1 = drawPoints.get(0);
        Point point2 = drawPoints.get(1);
        x1 = point1.getX();
        y1 = point1.getY();
        x2 = point2.getX();
        y2 = point2.getY();

        width = x2 - x1;
        height = y2 - y1;

        rect = new Rectangle.Double(x1, y1, width, height);

        g2d.draw(rect);
        System.out.println("Narysowano prostokat o wspolrzednych:(" + x1 + ',' + y1 + ") (" + x2 + ',' + y2 + ')');
    }

    //Draw circle using drawPoints list
    public void drawCircle(Graphics2D g2d) {
        double x, y, diameterX;
        Point point1 = drawPoints.get(0);
        x = point1.getX();
        y = point1.getY();

        diameterX = point1.distance(drawPoints.get(1));
        circle = new Ellipse2D.Double(x, y, diameterX, diameterX);
        this.diameter = 0;
        g2d.draw(circle);
    }

    //Draw circle using pre-setted variable diameter and using 1st point from drawPoints list
    public void drawCircleWithDiameter(Graphics2D g2d) {
        double x, y;
        Point point1 = drawPoints.get(0);
        x = point1.getX();
        y = point1.getY();

        circle = new Ellipse2D.Double(x, y, diameter, diameter);
        this.diameter = 0;
        g2d.draw(circle);
    }

    //Draw rectangle for mouse dragging function - It takes new points and current rectangle width, height
    public void redrawRectangle(Graphics2D g2d) {
        if (rect != null) {
            Point point1 = drawPoints.get(0);
            double x1 = point1.getX();
            double y1 = point1.getY();
            double width = rect.getWidth();
            double height = rect.getHeight();
            rect = new Rectangle.Double(x1, y1, width, height);
            g2d.draw(rect);
        }
    }

    //Draw circle for mouse dragging function - It takes new point and current circle width, height
    public void redrawCircle(Graphics2D g2d) {
        if (circle != null) {
            Point point1 = drawPoints.get(0);
            double x1 = point1.getX();
            double y1 = point1.getY();
            double width = circle.getWidth();
            double height = circle.getHeight();
            circle = new Ellipse2D.Double(x1, y1, width, height);
            g2d.draw(circle);
        }
    }

    //Draw a circle for mouse drawing function - Takes 2 points from drawPoints list
    public void drawCircleFromPoints(Graphics2D g2d) {
        Point point1 = drawPoints.get(0);
        Point point2 = drawPoints.get(1);
        double x1 = point1.getX();
        double y1 = point1.getY();

        double diameterX = point1.distance(point2);
        circle = new Ellipse2D.Double(x1, y1, diameterX, diameterX);
        g2d.draw(circle);
    }

    public void setShapeToPaint(String shapeValue) {
        shapeToPaint = shapeValue;
    }

}
