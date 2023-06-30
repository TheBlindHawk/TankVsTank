/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utente
 */
public class Tempo implements Runnable{
    private int minutes;
    private int seconds;
    private boolean partita=true;
    private Color colore=Color.black;
    Tempo(int min,int sec){
        minutes=min;
        seconds=sec;
    }
    public void paint(Graphics g){
        String m=Integer.toString(minutes);
        String s=Integer.toString(seconds);
        g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 100));
        g.setColor(colore);
        g.drawString(m+":"+s, 870, 100);       
    }
    public int getMinutes(){
        return minutes;
    }
    public int getSeconds(){
        return seconds;
    }
    public void setPartita(boolean partita){
        this.partita=partita;
    }
    public void aspetta(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        while(minutes>=0 && seconds>=0 && partita){
            if(minutes==1 && seconds==1)
                colore=Color.red;
            if(!(minutes==0 && seconds==0)){
                if(seconds==0){
                    seconds=59;
                    minutes--;
                }
                else
                    seconds--;
                aspetta(1000);
            }
        }
    }
}
