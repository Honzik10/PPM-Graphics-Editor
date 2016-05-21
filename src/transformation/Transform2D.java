package transformation;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Transform2D {

    ShapeTransformDrawPanel drawPanel;

    public Transform2D(ShapeTransformDrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public Matrix2D createTranslationMatrix(double tx, double ty) {
        double[] myMatrix = new double[]{1, 0, tx, 0, 1, ty, 0, 0, 1};
        Matrix2D transM = new Matrix2D(myMatrix);
        transM.print();
        return transM;
    }

    public Matrix2D createRotationMatrix(int angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRadians);
        double sin = Math.sin(angleRadians);
        double[] myMatrix = new double[]{cos, -sin, 0, sin, cos, 0, 0, 0, 1};
        Matrix2D transM = new Matrix2D(myMatrix);
        //transM.print();
        return transM;
    }

    public Matrix2D createScaleMatrix(double sx, double sy) {
        double[] myMatrix = new double[]{sx, 0, 0, 0, sy, 0, 0, 0, 1};
        Matrix2D transM = new Matrix2D(myMatrix);
        transM.print();
        return transM;
    }

    private Vector2D calculateVector(double orgX, double orgY, Matrix2D matrix) {
        Vector2D myVector = new Vector2D(orgX, orgY, 1);
        Vector2D newVector = matrix.vec_postmultiply(myVector);
        //System.out.println("New vector");
        //newVector.print();
        return newVector;
    }

    public void rotatePointList(List<Point2D> pointList, int angle) {
        Point2D rotatedPoint, currentPoint;
        List<Point2D> rotatedPointList = new ArrayList();

        for (int i = 0; i < pointList.size(); i++) {
            currentPoint = pointList.get(i);
            rotatedPoint = rotatePoint(currentPoint, angle);
            rotatedPointList.add(i, rotatedPoint);
            //System.out.println("Rotacja pktu (" + currentPoint.getX() + "," + currentPoint.getY() + ") do punktu ("  + rotatedPoint.getX() + "," + rotatedPoint.getY() + ")");
        }

        for (int i = 0; i < rotatedPointList.size(); i++) {
            Point2D p1 = pointList.get(i);
            Point2D p2 = rotatedPointList.get(i);
            System.out.println("Rotacja pktu (" + p1.getX() + "," + p1.getY() + ") do punktu (" + p2.getX() + "," + p2.getY() + ")");
        }

        pointList.clear();
        pointList.addAll(rotatedPointList);
        rotatedPointList.clear();
    }

    public Point2D rotatePoint(Point2D point, int angle) {
        double xOffset = drawPanel.getCenterX();
        double yOffset = drawPanel.getCenterY();

        double orgX = point.getX() - xOffset;
        double orgY = point.getY() - yOffset;
        Matrix2D rotateMatrix = createRotationMatrix(angle);
        Vector2D translatedVector = calculateVector(orgX, orgY, rotateMatrix);
        return new Point2D.Double(translatedVector.get_x() + xOffset, translatedVector.get_y() + yOffset);
    }

    public void translatePointList(List<Point2D> pointList, double transX, double transY) {
        Point2D translatedPoint, currentPoint;
        List<Point2D> translatedPointList = new ArrayList();

        for (int i = 0; i < pointList.size(); i++) {
            currentPoint = pointList.get(i);
            translatedPoint = translatePoint(currentPoint, transX, transY);
            translatedPointList.add(translatedPoint);
            //System.out.println("Rotacja pktu (" + currentPoint.getX() + "," + currentPoint.getY() + ") do punktu ("  + rotatedPoint.getX() + "," + rotatedPoint.getY() + ")");
        }

        for (int i = 0; i < translatedPointList.size(); i++) {
            Point2D p1 = pointList.get(i);
            Point2D p2 = translatedPointList.get(i);
            //System.out.println("Translacja pktu (" + p1.getX() + "," + p1.getY() + ") do punktu (" + p2.getX() + "," + p2.getY() + ")");
        }

        pointList.clear();
        pointList.addAll(translatedPointList);
        translatedPointList.clear();
    }

    public Point2D translatePoint(Point2D point, double transX, double transY) {
        double pointX = point.getX();
        double pointY = point.getY();
        Matrix2D translateMatrix = createTranslationMatrix(transX, transY);
        Vector2D translatedVector = calculateVector(pointX, pointY, translateMatrix);
        return new Point2D.Double(translatedVector.get_x(), translatedVector.get_y());
    }

    public void scalePointList(List<Point2D> pointList, double scaleValue) {
        Point2D scaledPoint, currentPoint;
        List<Point2D> scaledPointList = new ArrayList();

        for (int i = 0; i < pointList.size(); i++) {
            currentPoint = pointList.get(i);
            scaledPoint = scalePoint(currentPoint, scaleValue);
            scaledPointList.add(scaledPoint);
            //System.out.println("Rotacja pktu (" + currentPoint.getX() + "," + currentPoint.getY() + ") do punktu ("  + rotatedPoint.getX() + "," + rotatedPoint.getY() + ")");
        }

        for (int i = 0; i < scaledPointList.size(); i++) {
            Point2D p1 = pointList.get(i);
            Point2D p2 = scaledPointList.get(i);
            //System.out.println("Translacja pktu (" + p1.getX() + "," + p1.getY() + ") do punktu (" + p2.getX() + "," + p2.getY() + ")");
        }

        pointList.clear();
        pointList.addAll(scaledPointList);
        scaledPointList.clear();
    }
    
    public Point2D scalePoint(Point2D point, double scaleValue){
        double newX = point.getX()*scaleValue + (1.0d - scaleValue) * drawPanel.getCenterX();
        double newY = point.getY()*scaleValue + (1.0d - scaleValue) * drawPanel.getCenterY();
        return new Point2D.Double(newX,newY);
    }
    
}
