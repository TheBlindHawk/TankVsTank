/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author studente
 */
public class TankVsTank {

    /**
     * @param args the command line arguments
     */
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int L=(int) screenSize.getWidth()+20;
    static final int H=(int) screenSize.getHeight();
    public static void main(String[] args) {
        JFrame frame=new JFrame("TankVsTank");
        Pannello panel=new Pannello();
        Thread thread=new Thread(panel);
        thread.start();
        frame.add(panel);
        frame.setBounds(-10,0,L,H);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
