/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SMMV.Component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPasswordField;

/**
 *
 * @author user
 */
public class Rount_textfieald_password extends JPasswordField{

    public Rount_textfieald_password() {
        init();
    }
    
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20; margin:0,10,0,10");
    }
}
