/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author littlelion
 */
public class Sparo extends Thread{
    private Rectangle2D pallottola;
    private volatile boolean stopper=true;
    private double x,y;
    private int d=20,dir;
    private final int delay=4;
    Sparo(int x,int y,int dir){
        this.start();  
        this.x=x-d/2;
        this.y=y-d/2;
        this.dir=dir;
    }
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        if(pallottola!=null)
            g2.fill(pallottola);
    }
    public Rectangle2D getPallottola(){
        return pallottola;
    }
    public void stopper(){
        stopper=false;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setP(){
        pallottola=new Rectangle2D.Double(x,y,d,d);
    }
    public void aspetta(int millisec){
        try {
            this.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        while(stopper){
            aspetta(delay);
            disegna();
        }
    }
    public void disegna(){
        int n=4;
        switch(dir){
            case 1:
                y-=n;
                break;
            case 2:
                x+=n;
                break;
            case 3:
                y+=n;
                break;
            case 4:
                x-=n;
                break;
        }
        setP();
    }
}
