/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SMMV.Component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author user
 */
public class Rount_button extends JButton{

    public Rount_button() {
        init();
    }
    
        private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:50; margin:0,10,0,10");
    }
    
}
