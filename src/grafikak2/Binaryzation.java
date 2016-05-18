package grafikak2;

import java.awt.Color;
import java.util.ArrayList;

public class Binaryzation {

    PpmPanel imgPanel;
    MainFrame imgFrame;

    public Binaryzation(PpmPanel imagePanel, MainFrame imgFrame) {
        imgPanel = imagePanel;
        this.imgFrame = imgFrame;
    }

    public void binaryze(int threshold) {
        int[][] pixels = imgPanel.getPixels();
        Color white = new Color(255, 255, 255);

        for (int pixel[] : pixels) {
            for (int i = 0; i < pixel.length; i++) {
                int currPixelValue = pixel[i];
                Color c = new Color(currPixelValue);
                int grayValue = c.getRed();
                if (grayValue <= threshold) {
                    pixel[i] = 0;
                } else {
                    pixel[i] = white.getRGB();
                }
            }
        }

        imgPanel.displayImage();
    }

    public int calculateBlackPercentageThreshold(int percent) {
        Histogram h = imgFrame.getHistogram(false);
        int[] hist = h.getGrayValue();
        int sum = 0;
        int numberOfPix = (int) ((imgPanel.getImageHeigth() * imgPanel.getImageWidth()) * percent / 100);

        int histIndex = 0;

        while (sum < numberOfPix) {
            sum += hist[histIndex];
            histIndex++;
        }

        return histIndex;
    }

    public int calculateMeanIterativeSelection() {
        Histogram h = imgFrame.getHistogram(false);
        int[] hist = h.getGrayValue();

        boolean tEqual = false; //T(B) T(W)
        int lastTk = 128;
        while (!tEqual) {

            int sum1 = innerMeanItSelectionSum(0, lastTk - 1, hist);
            int sum2 = innerMeanItSelectionSum2(0, lastTk - 1, hist);
            int sum3 = innerMeanItSelectionSum(lastTk, 255, hist);
            int sum4 = innerMeanItSelectionSum2(lastTk, 255, hist);

            int tk = (sum1 / (2 * sum2)) + (sum3 / (2 * sum4));

            if (tk == lastTk) {
                tEqual = true;
                lastTk = tk;
            } else {
                lastTk = tk;
            }
        }

        return lastTk;
    }

    private int innerMeanItSelectionSum(int sumStartIt, int sumEndIt, int[] hist) {
        int sum = 0;

        for (int i = sumStartIt; i <= sumEndIt; i++) {
            sum += i * hist[i];
        }

        return sum;
    }

    private int innerMeanItSelectionSum2(int sumStartIt, int sumEndIt, int[] hist) {
        int sum = 0;

        for (int i = sumStartIt; i <= sumEndIt; i++) {
            sum += hist[i];
        }

        return sum;
    }

    public int calculateEntropySelection() {
        return 0;
    }
    
    public int calculateMimimumErrorThreshold(){
        return 0;
    }
}
