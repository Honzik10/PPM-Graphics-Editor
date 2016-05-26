/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfology;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import other.MyArray;
import other.PpmPanel;

/**
 *
 * @author honzik
 */
public class MorfologyJFrame extends javax.swing.JFrame {

    Morfology morfology;
    PpmPanel imgPanel;
    StructElement structElement;
    
    
    public MorfologyJFrame(PpmPanel imgPanel) {
        initComponents();
        this.imgPanel = imgPanel;
        morfology = new Morfology(imgPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField6 = new javax.swing.JTextField();
        dilationButton = new javax.swing.JButton();
        erosionButton = new javax.swing.JButton();
        openingButton = new javax.swing.JButton();
        closingButton = new javax.swing.JButton();
        thinningButton = new javax.swing.JButton();
        thickeningButton = new javax.swing.JButton();
        structElPanel = new javax.swing.JPanel();
        rowNTextField = new javax.swing.JTextField();
        colNTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        centerRowTextField = new javax.swing.JTextField();
        centerColTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField6.setBackground(new java.awt.Color(0, 0, 0));
        jTextField6.setColumns(5);

        dilationButton.setText("Dilation - dylacja");
        dilationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dilationButtonActionPerformed(evt);
            }
        });

        erosionButton.setText("Erosion");
        erosionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erosionButtonActionPerformed(evt);
            }
        });

        openingButton.setText("Opening");
        openingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openingButtonActionPerformed(evt);
            }
        });

        closingButton.setText("Closing");
        closingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closingButtonActionPerformed(evt);
            }
        });

        thinningButton.setText("Thinning - pocienianie");
        thinningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thinningButtonActionPerformed(evt);
            }
        });

        thickeningButton.setText("Thickening - pogrubianie");
        thickeningButton.setToolTipText("");
        thickeningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thickeningButtonActionPerformed(evt);
            }
        });

        structElPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout structElPanelLayout = new javax.swing.GroupLayout(structElPanel);
        structElPanel.setLayout(structElPanelLayout);
        structElPanelLayout.setHorizontalGroup(
            structElPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );
        structElPanelLayout.setVerticalGroup(
            structElPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        rowNTextField.setText("3");

        colNTextField.setText("3");

        jLabel1.setText("Rozmiar struct el:");

        jButton1.setText("Wyświetl");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ustaw struct el");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        centerRowTextField.setText("1");
        centerRowTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                centerRowTextFieldMouseClicked(evt);
            }
        });

        centerColTextField.setText("1");

        jLabel2.setText("Lokalizacja centrum struct el");

        jLabel3.setText("Legenda:");

        jLabel4.setText("Czarny");

        jTextField5.setBackground(new java.awt.Color(0, 0, 0));
        jTextField5.setColumns(5);

        jLabel5.setText("Biały");

        jTextField7.setColumns(5);

        jLabel6.setText("Czerwony - piksel nie uwzględniony");

        jTextField8.setBackground(new java.awt.Color(255, 0, 0));
        jTextField8.setColumns(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dilationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(erosionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thinningButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thickeningButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(centerRowTextField)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(centerColTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rowNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(structElPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dilationButton)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(erosionButton)
                            .addComponent(rowNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(openingButton)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closingButton)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(thinningButton)
                            .addComponent(centerRowTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(centerColTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(thickeningButton)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(19, 19, 19)))
                        .addComponent(structElPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dilationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dilationButtonActionPerformed
        //int[][] structEl = morfology.getStructEl(3, 3);
        System.out.println("Calculating dilation with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        morfology.checkForBlackImg(pixelsCopy);
        int[][] newPixels = morfology.dilation(pixelsCopy, structElement);
        checkEquality(imgPanel.getPixels(), newPixels);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);

    }//GEN-LAST:event_dilationButtonActionPerformed

    private void erosionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erosionButtonActionPerformed
        //int[][] structEl = morfology.getStructEl(3, 3);
        System.out.println("Calculating erosion with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        int[][] newPixels = morfology.erosion(pixelsCopy, structElement);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);
    }//GEN-LAST:event_erosionButtonActionPerformed

    private void openingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openingButtonActionPerformed
        //int[][] structEl = morfology.getStructEl(3, 3);
        System.out.println("Calculating opening with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        int[][] newPixels = morfology.opening(pixelsCopy, structElement);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);
    }//GEN-LAST:event_openingButtonActionPerformed

    private void closingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closingButtonActionPerformed
        //int[][] structEl = morfology.getStructEl(3, 3);
        System.out.println("Calculating closing with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        int[][] newPixels = morfology.closing(pixelsCopy, structElement);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);
    }//GEN-LAST:event_closingButtonActionPerformed

    //Pocienianie
    private void thinningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thinningButtonActionPerformed
        //int[][] structEl = {{2, 0, 2}, {-1, 3, 0}, {-1, -1, 3}};
        //int[][] structEl = {{2, 0, 2}, {-1, 0, 0}, {-1, -1, 2}};
        //int[][] structEl2 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println("Calculating thinning with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        int[][] newPixels = morfology.thinning(pixelsCopy, structElement);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);
    }//GEN-LAST:event_thinningButtonActionPerformed

    //Pogrubianie
    private void thickeningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thickeningButtonActionPerformed
        //int[][] structEl = {{2, 0, 2}, {-1, 0, 0}, {-1, -1, 2}};
        //int[][] structEl = {{0, 0, 2}, {0, 3, 2}, {0, 2, -1}};
        System.out.println("Calculating thickness with " + structElement.toString());
        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        int[][] newPixels = morfology.thickening(pixelsCopy, structElement);
        setNewPixelsToDisplay(newPixels);
        morfology.checkForBlackImg(newPixels);
    }//GEN-LAST:event_thickeningButtonActionPerformed

    private void centerRowTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_centerRowTextFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_centerRowTextFieldMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addTextFieldsToPanel();
        structElPanel.validate();
        repaint();
        structElPanel.repaint();
        structElPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int centerRow = Integer.parseInt(centerRowTextField.getText());
        int centerCol = Integer.parseInt(centerColTextField.getText());
        int rowN = Integer.parseInt(rowNTextField.getText());
        int colN = Integer.parseInt(colNTextField.getText());
        StructElement structEl = new StructElement(rowN, colN, centerRow, centerCol);
        
        Component[] componentArray = structElPanel.getComponents();
        structEl.fillStructElement(componentArray);
        
        structElement = structEl;
    }//GEN-LAST:event_jButton2ActionPerformed

    public void setNewPixelsToDisplay(int[][] newPixels) {
        imgPanel.setPixels(newPixels);
        imgPanel.displayImage();
    }

    private void checkEquality(int[][] arr1, int[][] arr2) {
        boolean equal = MyArray.equals(arr1, arr2);
        System.out.println("Arrays are equal? " + equal);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(5);
        textField.setBackground(Color.BLACK);
        textField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField field = (JTextField) e.getSource();
                Color backgroundColor = field.getBackground();

                if (backgroundColor == Color.WHITE) {
                    field.setBackground(Color.BLACK);
                } else if (backgroundColor == Color.BLACK) {
                    field.setBackground(Color.RED);
                } else {
                    field.setBackground(Color.WHITE);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }
        });
        return textField;
    }
    
    private void addTextFieldsToPanel(){
        int rowNumber = Integer.parseInt(rowNTextField.getText());
        int colNumber = Integer.parseInt(colNTextField.getText());
        
        structElPanel.removeAll();
        GridLayout gridLayout = new GridLayout(rowNumber, colNumber);
        structElPanel.setLayout(gridLayout);
        JTextField textField;
        for(int i=0;i<rowNumber*colNumber;i++){
            textField = createTextField();
            structElPanel.add(textField);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField centerColTextField;
    private javax.swing.JTextField centerRowTextField;
    private javax.swing.JButton closingButton;
    private javax.swing.JTextField colNTextField;
    private javax.swing.JButton dilationButton;
    private javax.swing.JButton erosionButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton openingButton;
    private javax.swing.JTextField rowNTextField;
    private javax.swing.JPanel structElPanel;
    private javax.swing.JButton thickeningButton;
    private javax.swing.JButton thinningButton;
    // End of variables declaration//GEN-END:variables
}
