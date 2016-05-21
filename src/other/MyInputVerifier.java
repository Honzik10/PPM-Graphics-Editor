/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.awt.Color;
import java.awt.Component;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author honzik
 */
public class MyInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        Component parent = input.getParent();
        if(!validationCriteria(text)){
            input.setBackground(Color.red);
            return false;
        }else{
            input.setBackground(Color.white);
            return true;
        }
    }

    private boolean validationCriteria(String text) {
        try {
            int inputVal = Integer.parseInt(text);
            return inputVal > -1 && inputVal < 256;
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            return false;
        }
    }
}
