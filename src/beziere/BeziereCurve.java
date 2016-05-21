package beziere;

import other.Math2;
import java.awt.Point;
import java.util.ArrayList;

public class BeziereCurve {

    public static ArrayList<Point> calculateBeziereCurve(ArrayList<Point> controlPointList) {
        int numberOfControlPoints = controlPointList.size();
        ArrayList<Point> pointList = null;

        switch (numberOfControlPoints) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                pointList = linearCurve(controlPointList.get(0), controlPointList.get(1));
                break;
            case 3:
                pointList = squareCurve(controlPointList.get(0), controlPointList.get(1), controlPointList.get(2));
                break;
            case 4:
                pointList = cubicCurve(controlPointList.get(0), controlPointList.get(1), controlPointList.get(2), controlPointList.get(3));
                break;
            default:
                pointList = biggerCurve(controlPointList);
                break;
        }

        return pointList;
    }

    private static ArrayList<Point> linearCurve(Point p0, Point p1) {
        //B(t) = P0 + t(P1 - P0) = (1 - t)P0 + tP1 , t ∈ [0,1]
        ArrayList<Point> curvePoints = new ArrayList();
        float startT = 0.0f;
        float endT = 1.0f;

        double x0 = p0.getX();
        double y0 = p0.getY();
        double x1 = p1.getX();
        double y1 = p1.getY();

        for (float t = startT; t <= endT; t += 0.01) {
            int x = (int) ((1 - t) * x0 + t * x1);
            int y = (int) ((1 - t) * y0 + t * y1);
            Point newP = new Point(x, y);
            curvePoints.add(newP);
        }

        return curvePoints;
    }

    private static ArrayList<Point> squareCurve(Point p0, Point p1, Point p2) {
        //B(t) = (1 -t)2P0 + 2(1 - t)tP1 + t2P2 , t ∈ [0,1]
        ArrayList<Point> curvePoints = new ArrayList();
        float startT = 0.0f;
        float endT = 1.0f;

        double x0 = p0.getX();
        double y0 = p0.getY();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        for (float t = startT; t <= endT; t += 0.01) {
            int x = (int) ((1 - t) * (1 - t) * x0 + 2 * (1 - t) * t * x1 + t * t * x2);
            int y = (int) ((1 - t) * (1 - t) * y0 + 2 * (1 - t) * t * y1 + t * t * y2);
            Point newP = new Point(x, y);
            curvePoints.add(newP);
        }

        return curvePoints;
    }

    private static ArrayList<Point> cubicCurve(Point p0, Point p1, Point p2, Point p3) {
        //B(t) = (1 -t)3P0 + 3(1 - t)2tP1 + 3(1 - t)t2P2 + t3P3
        ArrayList<Point> curvePoints = new ArrayList();
        float startT = 0.0f;
        float endT = 1.0f;

        double x0 = p0.getX();
        double y0 = p0.getY();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double x3 = p3.getX();
        double y3 = p3.getY();

        for (float t = startT; t <= endT; t += 0.01) {
            float oneMinT = 1 - t;
            float oneMinTPow3 = (float) Math.pow((double) oneMinT, 3);
            float oneMinTPow2 = (float) Math.pow((double) oneMinT, 2);
            float tPow3 = (float) Math.pow((double) t, 3);
            float tPow2 = (float) Math.pow((double) t, 2);

            int x = (int) (oneMinTPow3 * x0 + 3 * oneMinTPow2 * t * x1 + 3 * oneMinT * tPow2 * x2 + tPow3 * x3);
            int y = (int) (oneMinTPow3 * y0 + 3 * oneMinTPow2 * t * y1 + 3 * oneMinT * tPow2 * y2 + tPow3 * y3);
            Point newP = new Point(x, y);
            curvePoints.add(newP);
        }

        return curvePoints;

    }

    private static ArrayList<Point> biggerCurve(ArrayList<Point> controlPointList) {
        ArrayList<Point> curvePoints = new ArrayList();
        int n = controlPointList.size() - 1;
        float startT = 0.0f;
        float endT = 1.0f;
        
        //Calculates all Newton binomial values
        ArrayList<Integer> newtonBinomialList = new ArrayList(controlPointList.size());
        for (int i = 0; i <= n; i++) {
            int newtonsBinomial = Math2.newtonsBinomial(n, i);
            newtonBinomialList.add(newtonsBinomial);
        }

        //Calculates points
        for (float t = startT; t <= endT; t += 0.01) {
            float x = 0, y = 0;
            for (int i = 0; i <= n; i++) {
                float newtonsBinomial = newtonBinomialList.get(i);
                float oneMinT = (1 - t);
                float oneMinTPowNi = (float) Math.pow(oneMinT, n - i);
                float tPowI = (float) Math.pow(t, i);
                x += (float) (newtonsBinomial * oneMinTPowNi * tPowI * controlPointList.get(i).getX());
                y += (float) (newtonsBinomial * oneMinTPowNi * tPowI * controlPointList.get(i).getY());
            }

            System.out.println("Dodaje pkt o wsp. (" + x + "," + y + ")");
            curvePoints.add(new Point((int) x, (int) y));

        }
        return curvePoints;
    }
}
