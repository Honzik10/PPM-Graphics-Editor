package transformation;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SimpleShape {

    private List<Point2D> pointList = new ArrayList();

    public SimpleShape() {

    }

    public void addPoint(Point2D point) {
        pointList.add(point);
    }

    public void addPoint(double x, double y) {
        pointList.add(new Point2D.Double(x, y));
    }

    public void removePoints() {
        pointList.clear();
    }

    public List<Point2D> getPointList() {
        return pointList;
    }
}
