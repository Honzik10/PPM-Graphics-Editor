/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Reading file methods compared - http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly
package grafikak2;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author honzik
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form ImageFrame
     */
    final JFileChooser fChooser;
    EditPictureFrame editPicFrame;
    ImageFilterFrame imgFilterFrame;
    BinaryzationFrame binaryFrame;
    PpmPanel imagePanel;
    Histogram hist;
    HistogramEdit he;
    BeziereCurveFrame beziereCurveFrame;
    OpenImageFrame openImageFrame;

    public MainFrame() {
        initComponents();
        setTitle("Projekt Grafika Komputerowa");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String userPictureDirPath = System.getProperty("user.home") + "/Pictures";
        fChooser = new JFileChooser(userPictureDirPath);
        JMenuItem openMI = new JMenuItem("Otwórz");
        JMenuItem saveMI = new JMenuItem("Zapisz");
        JMenuItem exitMI = new JMenuItem("Wyjdz");
        JMenuItem ppMI = new JMenuItem("Przekształcenia punktowe");
        JMenuItem filterMI = new JMenuItem("Filtry obrazu");
        JMenuItem histogramMI = new JMenuItem("Histogram");
        JMenuItem changeHistMI = new JMenuItem("Przekształcenia histogramu");
        JMenuItem binaryzationMI = new JMenuItem("Binaryzacja");
        JMenuItem beziereCurveMI = new JMenuItem("Krzywe Beziere");
        JMenuItem shapeDrawMI = new JMenuItem("Prymitywy graficzne oraz kanwa");

        openMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        saveMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        exitMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ppMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditPicFrame();
            }
        });
        filterMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFilterFrame();
            }
        });
        histogramMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHistogramFrame();
            }
        });
        changeHistMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHistogramEditFrame();
            }
        });
        binaryzationMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBinaryzationFrame();
            }
        });
        beziereCurveMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBeziereCurveDrawPanel();
            }
        });
        beziereCurveMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShapeDrawFrame();
            }
        });

        fileMenu.add(openMI);
        fileMenu.add(saveMI);
        fileMenu.add(exitMI);
        pointCalculationMenu.add(ppMI);
        filterMenu.add(filterMI);
        histogramMenu.add(histogramMI);
        histogramMenu.add(changeHistMI);
        binaryzationMenu.add(binaryzationMI);
        otherMenu.add(beziereCurveMI);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new PpmPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        pointCalculationMenu = new javax.swing.JMenu();
        filterMenu = new javax.swing.JMenu();
        histogramMenu = new javax.swing.JMenu();
        binaryzationMenu = new javax.swing.JMenu();
        otherMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 33));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 702, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);

        fileMenu.setText("Plik");
        jMenuBar1.add(fileMenu);

        pointCalculationMenu.setText("Przekształcenia punktowe");
        jMenuBar1.add(pointCalculationMenu);

        filterMenu.setText("Filtry obrazu");
        jMenuBar1.add(filterMenu);

        histogramMenu.setText("Histogram");
        jMenuBar1.add(histogramMenu);

        binaryzationMenu.setText("Binaryzacja");
        jMenuBar1.add(binaryzationMenu);

        otherMenu.setText("Inne");
        jMenuBar1.add(otherMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        editPicFrame.dispose();
        imgFilterFrame.dispose();
        binaryFrame.dispose();
        he.dispose();
    }//GEN-LAST:event_formWindowClosed

    private boolean isImageLoaded() {
        if (imagePanel != null && imagePanel.getPixels() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void openSaveGui() {
        if (isImageLoaded()) {
            String location = JOptionPane.showInputDialog("Podaj ścieżke do zapisu:");
            PpmPanel imagePanel = ((PpmPanel) jPanel1);
            try {
                imagePanel.saveImage(location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void openOpenImageFrame() {
        if (openImageFrame != null) {
            openImageFrame.setVisible(true);
        } else {
            openImageFrame = new OpenImageFrame();
            openImageFrame.setVisible(true);
        }
    }

    private void openEditPicFrame() {
        if (isImageLoaded()) {
            editPicFrame.setVisible(true);
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openFilterFrame() {
        if (isImageLoaded()) {
            imgFilterFrame.setVisible(true);
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openHistogramFrame() {
        if (isImageLoaded()) {
            createHistogram();
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createHistogram() {
        int[][] pixels = imagePanel.getPixels();
        hist = new Histogram(pixels); //Creates and display histogram for currently displayed image
    }

    public Histogram getHistogram(boolean isVisible) {
        if (hist == null) {
            createHistogram();
            hist.setVisible(isVisible);
        }

        return hist;
    }

    private void openHistogramEditFrame() {
        if (isImageLoaded()) {
            createHistogramEditFrame();
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createHistogramEditFrame() {
        if (hist != null) {
            he = new HistogramEdit(imagePanel, hist);
            he.setVisible(true);
        }
    }

    private void openBinaryzationFrame() {
        if (isImageLoaded()) {
            if (binaryFrame == null) {
                binaryFrame = new BinaryzationFrame(imagePanel, this);
                binaryFrame.setVisible(true);
            } else {
                binaryFrame.setVisible(true);
            }
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openBeziereCurveDrawPanel() {
        if (beziereCurveFrame == null) {
            beziereCurveFrame = new BeziereCurveFrame();
            beziereCurveFrame.setVisible(true);
        } else {
            beziereCurveFrame.setVisible(true);
        }
    }
    
    private void openShapeDrawFrame(){
        
    }

    public void loadAndDisplayImage(String location) {
        imagePanel = ((PpmPanel) jPanel1);

        if (imagePanel.loadImage(location)) {
            if (imagePanel.displayImage()) {
                System.out.println("Displaying image of size:" + imagePanel.getImageWidth() + "x" + imagePanel.getImageHeigth());
                this.setSize(imagePanel.getImageWidth() + 70, imagePanel.getImageHeigth() + 30);
                setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Plik jest uszkodzony.", "Błąd", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }
        editPicFrame = new EditPictureFrame(imagePanel);
        //editPicFrame.setVisible(true);
        imgFilterFrame = new ImageFilterFrame(imagePanel);
        this.resize2();
        //imgFilterFrame.setVisible(true);
    }

    public void loadAndDisplayJPGImage(String location) {
        PpmPanel imagePanel = ((PpmPanel) jPanel1);
        imagePanel.loadAndDisplayJPG(location);
        //System.out.println("Displaying image of size:" + width + "x" + height);

        //this.setSize(imagePanel.getImageWidth(), imagePanel.getImageHeigth());
        this.resize2();
        setVisible(true);
    }

    private void resize() {
        int width = 0;
        int height = 100;
        Component[] component = this.getComponents();

        for (Component component1 : component) {
            width += component1.getWidth();
            height += component1.getHeight();
        }

        this.setSize(width, height);
    }

    private void resize2() {
        int width = ((PpmPanel) jPanel1).getWidth();
        int height = ((PpmPanel) jPanel1).getHeight() + jMenuBar1.getHeight() + 80;
        this.setSize(width, height);
    }

    private void displayGuiMessage(String message, String windowTitle, int jOptionPaneMessageType) {
        JOptionPane.showMessageDialog(this, message, windowTitle, jOptionPaneMessageType);
    }

    private void openFile() {
        int openState = fChooser.showOpenDialog(this);

        if (openState == JFileChooser.APPROVE_OPTION) {
            File file = fChooser.getSelectedFile();
            String location = file.getAbsolutePath();
            loadAndDisplayImage(location);
        }
    }

    private void saveFile() {
        if (isImageLoaded()) {
            int saveState = fChooser.showSaveDialog(this);

            if (saveState == JFileChooser.APPROVE_OPTION) {
                File file = fChooser.getSelectedFile();
                String location = file.getAbsolutePath();
                try {
                    imagePanel.saveImage(location);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            displayGuiMessage("Musisz otworzyć obraz z menu Plik -> Otworz", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OpenImageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenImageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenImageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenImageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu binaryzationMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu filterMenu;
    private javax.swing.JMenu histogramMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu otherMenu;
    private javax.swing.JMenu pointCalculationMenu;
    // End of variables declaration//GEN-END:variables

}
