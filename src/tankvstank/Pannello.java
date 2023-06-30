/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankvstank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author studente
 */
public class Pannello extends JPanel implements Runnable{
    private Player p1;
    private Player p2;
    private Tempo time;
    private Vita lifeP1;
    private Vita lifeP2;
    private Collision c1;
    private BulletCheck Check1,Check2;
    private PlayerCheck Pcheck1,Pcheck2;
    private Semaphore s1,s2;
    private Thread thP1;
    private Thread thP2;
    private Thread thTime;
    private Thread check1,check2,pcheck1,pcheck2;
    private Thread t1;
    private int [] m1={KeyEvent.VK_W,KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_S,KeyEvent.VK_SPACE};
    private int [] m2={KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN,KeyEvent.VK_ENTER};
    Pannello(){
        instanziamentoOggetti();
        instanziamentoThread();
        avvioThread();
    }
    public void instanziamentoOggetti(){
        s1=new Semaphore(1);
        s2=new Semaphore(1);
        p1=new Player(this,m1,Color.red,200,400);
        p2=new Player(this,m2,Color.blue,1700,400);
        time=new Tempo(3,1);
        lifeP1=new Vita(20,20,Color.red);
        lifeP2=new Vita(1500,20,Color.blue);
        Check1=new BulletCheck(p1.getArrayList(),s1);
        Check2=new BulletCheck(p2.getArrayList(),s2);
        Pcheck1=new PlayerCheck(p1,p2.getArrayList(),s2,lifeP1);
        Pcheck2=new PlayerCheck(p2,p1.getArrayList(),s1,lifeP2);
        c1=new Collision(p1,p2);
    }
    public void instanziamentoThread(){
        thP1=new Thread(p1);
        thP2=new Thread(p2);
        thTime=new Thread(time);
        check1=new Thread(Check1);
        check2=new Thread(Check2);
        pcheck1=new Thread(Pcheck1);
        pcheck2=new Thread(Pcheck2);
        t1=new Thread(c1);
    }
    public void avvioThread(){
        thP1.start();
        thP2.start();
        thTime.start();
        //check1.start();
        //check2.start();
        pcheck1.start();
        pcheck2.start();
        t1.start();
        
    }
    public void paint(Graphics g){
        super.paint(g);
        p1.paint(g);
        p2.paint(g);
        lifeP1.paint(g);
        lifeP2.paint(g);
        time.paint(g);
        checkCollisionBullet();
        checkVictory(g);
    }
    public void checkCollisionBullet(){//va fatto un thread perchè si bugga
        /*if(!p1.getArrayList().isEmpty()){
            for(int i=0;i<p1.getArrayList().size();i++){
                if(p2.getPlayer().intersects(p1.getArrayList().get(i).getPallottola())){
                    p1.removeBullet(i);
                    if(lifeP2.getPunti()!=0){
                        lifeP2.diminuisci();
                        lifeP2.caricaVettore();
                        lifeP2.Caricamento();
                    }
                }
            }
        }
        if(!p2.getArrayList().isEmpty()){
            for(int i=0;i<p2.getArrayList().size();i++){
                if(p1.getPlayer().intersects(p2.getArrayList().get(i).getPallottola())){
                    p2.removeBullet(i);
                    if(lifeP1.getPunti()!=0){
                        lifeP1.diminuisci();
                        lifeP1.caricaVettore();
                        lifeP1.Caricamento();
                    }
                }
            }
        }*/
        if(!p2.getArrayList().isEmpty()&&!p1.getArrayList().isEmpty())
            for(int i=0;i<p1.getArrayList().size();i++)
                for(int y=0;y<p2.getArrayList().size();y++)
                    if(p1.getArrayList().get(i).getPallottola().intersects(p2.getArrayList().get(y).getPallottola())){
                        p1.removeBullet(i);
                        p2.removeBullet(y);
                    }
        /*if(!p1.getArrayList().isEmpty())
            for(int i=0;i<p1.getArrayList().size();i++){
                double nx=p1.getArrayList().get(i).getX(),ny=p1.getArrayList().get(i).getY();
                if(nx<0||nx>TankVsTank.L-85||ny<0||ny>TankVsTank.H-130)
                    p1.removeBullet(i);
            }
        if(!p2.getArrayList().isEmpty())
            for(int i=0;i<p2.getArrayList().size();i++){
                double nx=p2.getArrayList().get(i).getX(),ny=p2.getArrayList().get(i).getY();
                if(nx<0||nx>TankVsTank.L-85||ny<0||ny>TankVsTank.H-130){
                    p2.removeBullet(i);
                }
            }*/
    }
    public void checkVictory(Graphics g){
        g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 100));
        g.setColor(Color.black);
        if(lifeP1.getPunti()==0){
            g.drawString("Vince P2", 700, 700);
            end();
        }
        else if(lifeP2.getPunti()==0){
            g.drawString("Vince P1", 700, 700);
            end();
        }
        else if((time.getMinutes()==0 && time.getSeconds()==0)||(lifeP1.getPunti()==0 && lifeP2.getPunti()==0)){
            g.drawString("Pareggio", 700, 700);
            end();
        }
    }
    public void end(){
        p1.setPartita(false);
        p2.setPartita(false);
        time.setPartita(false);
    }
    @Override
    public void run() {
        while(true){
            repaint();
        }
    }
    public void aspetta(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pannello.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
