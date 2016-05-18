/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafikak2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PpmPanel extends JPanel {

    private BufferedImage image;
    private boolean isOryginalImageBackedUp;
    private String imageType;
    private int maxColor;
    private int oryginalWidth;
    private int oryginalHeight;
    private int scaledWidth;
    private int scaledHeight;
    private int[][] pixels;
    private int[][] oryginalPixels;
    private int readedPixelNumber;
    boolean isImageScaled;

    public PpmPanel() {
        isImageScaled = false;
        imageType = "";
        maxColor = 1;
        readedPixelNumber = 0;
        isOryginalImageBackedUp = false;
    }

    public int getOryginalWidth() {
        return oryginalWidth;
    }

    public int getOryginalHeight() {
        return oryginalHeight;
    }

    //Loads file header to variables and pixels to pixels array
    public boolean loadImage(String location) {
        if (readFileHeader(location)) {
            if (imageType.compareTo("P3") == 0) {
                readedPixelNumber = loadP3(location);
            } else if (imageType.compareTo("P6") == 0) {
                readedPixelNumber = loadP6v2(location);
            }
            makeOriginalImageBackup(pixels);
            return true;
        } else {
            return false;
        }

    }

    private boolean readFileHeader(String location) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(location)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Plik z podanej ścieżki nie istnieje");
            return false;
        }

        imageType = scanner.nextLine();
        scanner.nextLine();
        String size = scanner.nextLine();
        int spaceIndex = size.indexOf(' ');
        oryginalWidth = Integer.parseInt(size.substring(0, spaceIndex));
        oryginalHeight = Integer.parseInt(size.substring(spaceIndex + 1, size.length()));
        pixels = new int[oryginalHeight][oryginalWidth];
        maxColor = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return true;
    }

    //load image, returns readed pixel number
    private int loadP3(String location) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(location)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Skip header lines 
        int skipHeaderLines = 4;
        while (scanner.hasNextLine() && skipHeaderLines > 0) {
            scanner.nextLine();
            skipHeaderLines--;
        }

        int pixelNumber = 0;
        int counter = 0;
        int r = 0, g = 0, b = 0, rgb = 0;
        int row = 0, column = 0;
        if (maxColor <= 255) {
            while (scanner.hasNextLine()) {
                int value = Integer.parseInt(scanner.nextLine());
                switch (counter) {
                    case 0:
                        r = value;
                        break;
                    case 1:
                        g = value;
                        break;
                    case 2:
                        b = value;
                        rgb = 65536 * r + 256 * g + b;
                        //pixelList.add(rgb);
                        pixels[row][column] = rgb;
                        //System.out.println("Oryginal RGB from pos("+row+","+column+") = " + r + "," + g + "," + b + "(" + rgb + ")");
                        pixelNumber++;
                        column++;
                        if (column == oryginalWidth) {
                            column = 0;
                            row++;
                        }
                        break;
                }

                counter++;
                if (counter > 2) {
                    counter = 0;
                }
            }
        } else {
            while (scanner.hasNextLine()) {

                int value = Integer.parseInt(scanner.nextLine());
                switch (counter) {
                    case 0:
                        r = value;
                        break;
                    case 1:
                        r *= value;
                        break;
                    case 2:
                        g = value;
                        break;
                    case 3:
                        g *= value;
                        break;
                    case 4:
                        b = value;
                        break;
                    case 5:
                        b *= value;
                        r = ((r * 255) + (maxColor)) / maxColor;
                        g = ((g * 255) + (maxColor)) / maxColor;
                        b = ((b * 255) + (maxColor)) / maxColor;
                        rgb = 65536 * r + 256 * g + b;
                        //pixelList.add(rgb);
                        pixels[row][column] = rgb;
                        pixelNumber++;
                        column++;
                        if (column == oryginalWidth) {
                            column = 0;
                            row++;
                        }
                        break;
                }

                counter++;
                if (counter > 5) {
                    counter = 0;
                }
            }
        }
        scanner.close();
        return pixelNumber;
    }

    //load image, returns readed pixel number
    private int loadP6(String location) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(location);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int pixelNumber = 0;
        int counter = 0;
        int r = 0, g = 0, b = 0, rgb = 0;
        boolean skipHeaderDataDone = false;
        int newLineCounter = 0;
        int row = 0, column = 0;
        //Skips header data. 
        while (!skipHeaderDataDone) {
            try {
                int tmpValue = inputStream.read();

                if (tmpValue == 10) {
                    newLineCounter++;
                }

                if (newLineCounter == 4) {
                    skipHeaderDataDone = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (maxColor <= 255) {
                while (inputStream.available() != 0) {
                    int value = inputStream.read();
                    //System.out.println(value);
                    if (counter == 0) {
                        r = value;
                    } else if (counter == 1) {
                        g = value;
                    } else if (counter == 2) {
                        b = value;
                        rgb = 65536 * r + 256 * g + b;
                        pixels[row][column] = rgb;
                        pixelNumber++;
                        column++;
                        if (column == oryginalWidth) {
                            column = 0;
                            row++;
                        }
                    }
                    counter++;
                    if (counter > 2) {
                        counter = 0;
                    }
                }
            } else {
                while (inputStream.available() != 0) {
                    int value = inputStream.read();
                    //System.out.println(value);
                    if (counter == 0) {
                        r = value;
                    } else if (counter == 1) {
                        r *= value;
                    } else if (counter == 2) {
                        g = value;
                    } else if (counter == 3) {
                        g *= value;
                    } else if (counter == 4) {
                        b = value;
                    } else if (counter == 5) {
                        b *= value;
                        r = ((r * 255) + (maxColor)) / maxColor;
                        g = ((g * 255) + (maxColor)) / maxColor;
                        b = ((b * 255) + (maxColor)) / maxColor;
                        rgb = 65536 * r + 256 * g + b;
                        pixels[row][column] = rgb;
                        pixelNumber++;
                        column++;
                        if (column == oryginalWidth) {
                            column = 0;
                            row++;
                        }
                    }
                    counter++;
                    if (counter > 5) {
                        counter = 0;
                    }
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pixelNumber;
    }

    //load image, returns readed pixel number
    private int loadP6v2(String location) {
        //Create file object
        File file = new File(location);

        //Get file channel in readonly mode
        FileChannel fileChannel = null;
        try {
            fileChannel = new RandomAccessFile(file, "r").getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Get direct byte buffer access using channel.map() operation
        MappedByteBuffer buffer = null;
        try {
            buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        while (!buffer.isLoaded()) {
//            System.out.println("Buffer not loaded! Waiting...");
//        }
        ArrayList<Integer> rgbList = new ArrayList();
        int value;
        for (int i = 0; i < buffer.limit(); i++) {
            value = (int) buffer.get();
            rgbList.add(value);
        }

        int colorCounter = 0;
        int r = 0, g = 0, b = 0, rgb;
        int row = 0, column = 0;
        int pixelNumber = 0;

        boolean skippedHeader = false;
        int newLineCounter = 0;
        for (Integer i : rgbList) {
            if (!skippedHeader) {
                if (i == 10) {
                    newLineCounter++;
                }

                if (newLineCounter == 4) {
                    skippedHeader = true;
                }
            } else {

                if (colorCounter == 0) {
                    r = i;
                } else if (colorCounter == 1) {
                    g = i;
                } else if (colorCounter == 2) {
                    b = i;
                    rgb = 65536 * r + 256 * g + b;
                    pixels[row][column] = rgb;
                    pixelNumber++;
                    column++;
                    if (column == oryginalWidth) {
                        column = 0;
                        row++;
                    }
                }
                colorCounter++;
                if (colorCounter > 2) {
                    colorCounter = 0;
                }
            }

        }

        return pixelNumber;
    }

    //Displays loaded image, also checks for corruption
    public boolean displayImage() {
        if (!checkFileCorruption()) {
            int scaleIndex = checkForScaling();

            if (scaleIndex == 0) {
                resizePanel(oryginalWidth, oryginalHeight);
                displayOryginalImage();
            } else {
                resizePanel(scaledWidth, scaledHeight);
                displayScaledImge(scaleIndex);
            }
        } else {
            return false;
        }

        return true;
    }

    //Checks for img corruption true - corrupted
    private boolean checkFileCorruption() {
        int expectedPixelNumber = oryginalWidth * oryginalHeight;
        return readedPixelNumber != expectedPixelNumber; //Readed all pixels, not corrupted
        //Corrupted
    }

    //Checks if image is too big for current monitor resolution
    //If img is too big then returns 1st pixel number to skip, else 0.
    private int checkForScaling() {
        int skipPixelNumber = 0;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int monitorWidth = (int) screenSize.getWidth();
        int monitorHeight = (int) screenSize.getHeight();
        double skipPixNumberD = 0;
        if (monitorWidth < oryginalWidth) {
            skipPixNumberD = (double) monitorWidth / (double) (oryginalWidth - monitorWidth);
        } else if (monitorHeight < oryginalHeight) {
            skipPixNumberD = (double) monitorHeight / (double) (oryginalHeight - monitorHeight);
        }

        skipPixelNumber = (int) Math.ceil(skipPixNumberD);
        if (skipPixelNumber > 0) {
            scaledWidth = oryginalWidth / skipPixelNumber + oryginalWidth % skipPixelNumber;
            scaledHeight = oryginalHeight / skipPixelNumber + oryginalHeight % skipPixelNumber;
        }
        return skipPixelNumber;
    }

    //display oryginal image from pixels array
    private void displayOryginalImage() {
        isImageScaled = false;
        this.setSize(oryginalWidth, oryginalHeight);
        image = new BufferedImage(oryginalWidth, oryginalHeight, BufferedImage.TYPE_INT_RGB);
        int row = 0, column = 0;

        for (int h = 0; h < oryginalHeight; h++) {
            for (int w = 0; w < oryginalWidth; w++) {
                image.setRGB(w, h, pixels[row][column]);
                //System.out.println("Zapisuje kolor " + "" + " w " + w + ' ' + h);
                column++;
                if (column == oryginalWidth) {
                    column = 0;
                    row++;
                }
            }
        }

        this.repaint();
    }

    //display scaled image from pixels
    private void displayScaledImge(int scaleIndex) {
        isImageScaled = true;
        this.setSize(scaledWidth, scaledHeight);
        image = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        int row = 0, column = 0;

        for (int h = 0; h < scaledHeight; h++) {
            for (int w = 0; w < scaledWidth; w++) {
                image.setRGB(w, h, pixels[row][column]);
                //System.out.println("Zapisuje kolor " + "" + " w " + w + ' ' + h);
                column += scaleIndex;
                if (column >= oryginalWidth) {
                    column = 0;
                    row += scaleIndex;
                }
            }
        }
    }

    private void resizePanel(int width, int height) {
        this.setSize(width, height);
    }

    //Returns image width. Returns scaled width if displayed imaged is scaled. Else return oryginal width.
    public int getImageWidth() {
        if (image != null) {
            if(isImageScaled){
                return scaledWidth;
            }else{
                return image.getWidth();
            }
        } else {
            return 0;
        }

    }

    //Returns image heigth. Returns scaled heigth if displayed imaged is scaled. Else return oryginal heigthh.
    public int getImageHeight() {
        if (image != null) {
            if(isImageScaled){
                return scaledHeight;
            }else{
                return image.getHeight();
            } 
        } else {
            return 0;
        }
    }

    public void loadAndDisplayJPG(String location) {
        image = null;
        pixels = null;
        try {
            image = ImageIO.read(new File(location));
            //image = ImageIO.read(file);
            resizePanel(getImageWidth(), getImageWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(String location) throws FileAlreadyExistsException, IOException {
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(0.99f);

        File out = new File(location + ".jpg");
        FileImageOutputStream fios = new FileImageOutputStream(out);
        ImageOutputStream outputStream = fios;
        jpgWriter.setOutput(outputStream);
        IIOImage outputImage = new IIOImage(image, null, null);
        jpgWriter.write(null, outputImage, jpgWriteParam);
        jpgWriter.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resizePanel(getImageWidth(), getImageHeight());
        g.drawImage(image, 0, 0, this);
    }

    //Returns pixels array
    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] newPixels) {
        pixels = newPixels;
    }

    private void makeOriginalImageBackup(int[][] pixels) {
        oryginalPixels = new int[oryginalHeight][oryginalWidth];
        if (pixels != null) {
            for (int i = 0; i < oryginalHeight; i++) {
                System.arraycopy(pixels[i], 0, oryginalPixels[i], 0, oryginalWidth);
            }
        } else {
            System.out.println("Podana tablica pixeli jest NULLem! Coś poszło nie tak.");
        }
    }

    public void setOryginalImageForDisplay() {
        for (int i = 0; i < oryginalHeight; i++) {
            System.arraycopy(oryginalPixels[i], 0, pixels[i], 0, oryginalWidth);
        }
    }

    //Compares oryginal pixel array with currently displayed one array
    //False - arrays are different
    public boolean comparePixelArrays() {
        boolean arraysEqual = true;

        for (int i = 0; i < oryginalHeight; i++) {
            for (int j = 0; j < oryginalWidth; j++) {
                if (oryginalPixels[i][j] != pixels[i][j]) {
                    //System.out.println("Pixel z pozycji " + i + " " + j + " sa rozne. " + oryginalPixels[i][j] + " != " + pixels[i][j]);
                    arraysEqual = false;
                }
            }
        }
        return arraysEqual;
    }

    public void displayCurrentlyDisplayedPixels() {
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        for (int i = 0; i < oryginalHeight; i++) {
            for (int j = 0; j < oryginalWidth; j++) {
                int rgb = pixels[i][j];
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                System.out.println("Current RGB from pos(" + i + "," + j + ") = " + r + "," + g + "," + b + "(" + rgb + ")");
            }
        }

    }
}
