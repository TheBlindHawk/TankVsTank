/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author studente
 */
public class Player implements Runnable,KeyListener{
    private volatile boolean up,sx,dx,down,shoot;
    private ArrayList <Sparo>pr=new ArrayList<Sparo>();
    private int x,y,dir=0,d=70,index=0,N=60;
    private int keys[]=new int [5];
    private Rectangle2D.Double rect;
    private Color colore;
    private int delay=3;
    private int tempo=1;
    private boolean partita=true;
    private Timer timer;
    Player(JPanel panel,int keys[],Color colore,int x,int y){
        this.keys=keys;
        this.colore=colore;
        this.x=x;
        this.y=y;
	timer=new Timer();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(this);
    }
    public ArrayList<Sparo> getArrayList(){
        return pr;
    }
    public void removeBullet(int i){
        pr.get(i).stopper();
        pr.remove(i);
        index--;
    }
    public Rectangle2D.Double getPlayer(){
        return rect;
    }
    public void setDelay(int millisec){
        delay=millisec;
    }
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(colore);
        rect=new Rectangle2D.Double(x, y, d, d);
        g2.fill(rect);
        //int count=index-1;
        if(!pr.isEmpty()){
            /*do{
                if(count>=0){
                    pr.get(count).paint(g);
                    count--;
                }
            }while(count>=0);*/
            for(int i=0;i<pr.size();i++)
                pr.get(i).paint(g);
                
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void U(int n){
        y-=n;
        aspetta(2);
    }
    public void R(int n){
        x+=n;
        aspetta(2);
    }
    public void D(int n){
        y+=n;
        aspetta(2);
    }
    public void L(int n){
        x-=n;
        aspetta(2);
    }
    public void Up(){
        dir=1;
        if(y>0)
            y--;
    }
    public void Dx(){
        dir=2;
        if(x<TankVsTank.L-85)
            x++;
    }
    public void Down(){
        dir=3;
        if(y<TankVsTank.H-130)
            y++;
    }
    public void Sx(){
        dir=4;
        if(x>0)
            x--;
    }
    public void Shoot(){
        if(dir!=0){
            Sparo p=new Sparo(x+d/2,y+d/2,dir);
            pr.add(p);
            p.setP();
            index += 1;
        }
    }
    public void aspetta(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setPartita(boolean partita){
        this.partita=partita;
    }
    @Override
    public void run() {
        while(partita){
            if(up)
                Up();
            else if(sx)
                Sx();
            else if(dx)
                Dx();
            else if(down)
                Down();
            aspetta(delay);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(partita){
            int key=ke.getKeyCode();
            if(key==keys[0])
                up=true;
            else if(key==keys[1])
                sx=true;
            else if(key==keys[2])
                dx=true;
            else if(key==keys[3])
                down=true;
            if(key==keys[4]){
		if(timer.getPermesso()){
		    timer.setPermesso(false);
		    Shoot();
		}
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(partita){
            int key=ke.getKeyCode();
            if(key==keys[0])
                up=false;
            else if(key==keys[1])
                sx=false;
            else if(key==keys[2])
                dx=false;
            else if(key==keys[3])
                down=false;
        }
    }
}
