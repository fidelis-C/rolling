/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import images.Obj1;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.Random;


/**
 *
 * @author HP
 */
public class Game extends JFrame implements KeyListener, ActionListener {
    //player properties
    public String player;
    public JOptionPane user;
    public int score;
    public File uScore;
    public File udScore;
    
    //game over properties
    public String gameOver = "";
    
    public int coin = 0;
    public boolean stopCoin = false;
    
    public int life = 3;
    
    public boolean start = true;
    public int time = 0;

    public int playX = 100;
    public int playY = 50;
    public int dx = 20;
    public int dy = 20;
    
    //blocks
    public int blockX = 20;
    public int blockY = 400;
    
    public int blockX1 = 100;
    public int blockY1 = 700;
    
    public int blockX2 = 200;
    public int blockY2 = 400;
    
    public int blockX3 = 300;
    public int blockY3 = 400;
    
    public int blockX4 = 400;
    public int blockY4 = 800;
    
    public int blockX5 = 500;
    public int blockY5 = 950;
    
    public int blockX6 = 600;
    public int blockY6 = 670;
    
    //set player speed 
    public double momentum = 1.0;
    
    //blocks starter
    public double bStarter = 1.5;
    
    //pane
    public int paneX = 5;
    public int paneY = 50;
   
    public Obj1 o;
    private Timer t;
    public Random random;
public Game(){
    o = new Obj1();
    random = new Random();
    
    
        player =JOptionPane.showInputDialog("Enter Name Here");
    
    this.setResizable(false);
    this.setBackground(Color.blue);
    this.setFocusable(true);
    this.setFocusTraversalKeysEnabled(false);
    this.setSize(500, 500);
    this.setVisible(true);
    this.setTitle("Rolling 2.0 Created by Fidelis Castro");
    
  t  = new Timer(5, this);
  t.start();
  
  this.addKeyListener(this);
  this.setFocusable(true);
    
}
//blocks functions
public void moveBlocks(){
    blockY-=1;
    blockY1 -=bStarter;
    blockY2 -=bStarter;
    blockY3 -=bStarter;
    blockY4 -=bStarter;
    
}


//user control functions
public int falling(){
    
    return playY += momentum;
}
public int right(){
    
    
   return playX +=4; 
}

//player life
public void life(){
    
    this.life-=1;
}


public int left(){
    
    return playX -=4;
}
    @Override
public void paint(Graphics g){
        


            g.setColor(Color.black);
            g.fillRect(1, 1, 500, 500);

            
            g.setColor(Color.blue);
            g.fillOval(playX, playY, dx, dy);

            //pane
            g.setColor(Color.red);
            g.drawRect(paneX, paneY, 490, 400);


            
            g.setColor(Color.GREEN);
            g.fillRect(blockX, blockY, 60, 20);
            g.fillRect(blockX1, blockY1, 60, 20);
            g.fillRect(blockX2, blockY2, 60, 20);
            g.fillRect(blockX3, blockY3, 60, 20);
            g.fillRect(blockX4, blockY4, 60, 20);

            //set player life here
            g.setColor(Color.GREEN);
            g.drawString(player + ':' + "\t" + "Life:\t" + life, 50, 45);

            //set time here
            g.setColor(Color.green);
            g.drawString("Time:\t" + time, 400, 45);

            //coin properties
            g.drawString("Coins:\t" + coin, 300, 45);

            
            g.setColor(Color.red);
            g.drawString(gameOver, 250, 250);
            File u = new File("C:/spell/players/" + player);
            File dirScore = new File("C:/spell/players/" + player+"/score");
            uScore = new File("C:/spell/players/" + player + "/score" +"/"+"0.txt");

            u.mkdir();
            dirScore.mkdir();
            try{
            uScore.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
       
}       
    
   
     public void actionPerformed(ActionEvent e) {
       
         if(start){
         time ++;
         udScore = new File("C:/spell/players/" + player + "/score" +"/"+time+".txt");
        moveBlocks();
        falling();
        
        if(new Rectangle(playX, playY, dx, dy).intersects(blockX, blockY, 60, 20)
                ||new Rectangle(playX, playY, dx, dy).intersects(blockX1, blockY1, 60, 20)
                ||new Rectangle(playX, playY, dx, dy).intersects(blockX2, blockY2, 60, 20)
                ||new Rectangle(playX, playY, dx, dy).intersects(blockX3, blockY3, 60, 20)
                ||new Rectangle(playX, playY, dx, dy).intersects(blockX4, blockY4, 60, 20)){
            momentum = 0.0;
            
            
            playY -=2;
            
        }
        else{
            momentum = 1.5;
        }
        
        if(new Rectangle(playX, playY, dx, dy).intersects(paneX, paneY, 490, 400)){}
        //game over AI
        
        if(playY == 50){
            life();
        }
        
        
        //add coin
        
        //life Ai
        if(life == 0){
            uScore.renameTo(udScore);
            start = false;
        }
        if(blockY == 50){
            blockY = 550;
            
            
        }
        if(blockY1 == 50){
           blockY1 = 600;
           
        }
        
        if(blockY2 == 50){
            blockY2 = 700;
        }
        
        if(blockY3 == 50){
            blockY3 = 650;
        }
        if(blockY4 == 50){
            blockY4 = 750;
        }
        repaint();
    }
     }
     
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() ==KeyEvent.VK_LEFT){
            left(); 
        }
        else{
           
        }
        
        if(e.getKeyCode() ==KeyEvent.VK_RIGHT){
            right(); 
        }
        else{
           
        }
        
        if(e.getKeyCode() ==KeyEvent.VK_SPACE){
            if(start == true){
            start = false;
            
            }
            else{
                start = true;
            }
        }
        else{
           
        }
        
        
    }

    public void keyReleased(KeyEvent e) {
       
    }

   
}