import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
    

    private int xlen[]=new int[750];
    private int ylen[]=new int[750];
    
    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;
    private boolean gmovr=false;
    
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;    
    private ImageIcon body;
    
    private int length=3;
    private int moves=0;
    private int score=0;
    private int c=2;
    
    private int foodx[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int foody[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    
    private Random random=new Random();
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);
    
    private Timer timer;
    private int delay=100;
    
    private ImageIcon enemyImage;    
    private ImageIcon  titleImage;
    
    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start(); 
       
    }    
    public void paint(Graphics g)
    {
        
        if(moves==0)
        {
            for(int i=0;i<3;i++)
            {
                xlen[i]=100-i*25;               //initializing initial Position
                ylen[i]=100;
            }
        }        
        //Draw title border
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);
        
        //Draw title image
         titleImage=new ImageIcon("src/resource/snaketitle.jpg");
         titleImage.paintIcon(this,g,25,11);
        
        //Draw Gameplay Border
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        
        //Draw background for gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
        
        //Draw Score
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,13));
        g.drawString("SCORES: "+score,780,25);
        
         //Draw Length
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,13));
        g.drawString("LENGTH: "+length,780,40);
        
        //Life
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,13));
        g.drawString("LIFE: "+c,780,55);
        
         rightmouth=new ImageIcon("src/resource/rightmouth.png");
        rightmouth.paintIcon(this,g,xlen[0],ylen[0]);
        
        for(int a=0;a<length;a++)
        {
            if(a==0 && right)
            {
                rightmouth=new ImageIcon("src/resource/rightmouth.png");
                rightmouth.paintIcon(this,g,xlen[a],ylen[a]);
            }
             if(a==0 && left)
            {
                leftmouth=new ImageIcon("src/resource/leftmouth.png");
                leftmouth.paintIcon(this,g,xlen[a],ylen[a]);
            }
              if(a==0 && up)
            {
                upmouth=new ImageIcon("src/resource/upmouth.png");
                upmouth.paintIcon(this,g,xlen[a],ylen[a]);
            }
               if(a==0 && down)
            {
                downmouth=new ImageIcon("src/resource/downmouth.png");
                downmouth.paintIcon(this,g,xlen[a],ylen[a]);
            }
               if(a!=0)
            {
                body=new ImageIcon("src/resource/body.png");
                body.paintIcon(this,g,xlen[a],ylen[a]);
            }        
        }
        enemyImage=new ImageIcon("src/resource/foood.png");
        if(foodx[xpos]==xlen[0] && foody[ypos]==ylen[0]) 
        {
            score++;
            length++;
            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }
        enemyImage.paintIcon(this,g,foodx[xpos],foody[ypos]);   
        
        for(int i=1;i<length;i++)
        {
            if(xlen[i]==xlen[0] && ylen[i]==ylen[0])
            {
                if(c!=0)
                {
                    int t=length-i;                    
                    length=length-1-t;
                    c--;
                }
                else
                {
                 gmovr=true;
                 right=false;
                 left=false;
                 up=false;
                 down=false;                
                  g.setColor(Color.white);
                  g.setFont(new Font("arial",Font.BOLD,50));
                  g.drawString("GAME OVER ",300,300);  
                 
                  g.setColor(Color.white);
                  g.setFont(new Font("arial",Font.BOLD,20));
                  g.drawString("Press SPACE To RESTART ",320,340);  
                }
                
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        timer.start();
        if(right)
        {
            for(int r=length-1;r>=0;r--)
            {
                ylen[r+1]=ylen[r];
            }
            for(int r=length;r>=0;r--)
            {    
                   if(r==0)
                    xlen[r]=xlen[r]+25;
                else
                    xlen[r]=xlen[r-1];
                if(xlen[r]>850)
                    xlen[r]=25;
            }
            repaint();
        } 
        
         if(left)
        {
            for(int r=length-1;r>=0;r--)
            {
                ylen[r+1]=ylen[r];
            }
            for(int r=length;r>=0;r--)
            {
                if(r==0)
                    xlen[r]=xlen[r]-25;
                else
                    xlen[r]=xlen[r-1];
                if(xlen[r]<25)
                    xlen[r]=850;
            }
            repaint();
        } 
         
          if(down)
        {
            for(int r=length-1;r>=0;r--)
            {
                xlen[r+1]=xlen[r];
            }
            for(int r=length;r>=0;r--)
            {
                if(r==0)
                    ylen[r]=ylen[r]+25;
                else
                    ylen[r]=ylen[r-1];
                if(ylen[r]>625)
                    ylen[r]=75;
            }
            repaint();
        } 
          
          if(up)
        {
            for(int r=length-1;r>=0;r--)
            {
                xlen[r+1]=xlen[r];
            }
            for(int r=length;r>=0;r--)
            {
                if(r==0)
                    ylen[r]=ylen[r]-25;
                else
                    ylen[r]=ylen[r-1];
                if(ylen[r]<75)
                    ylen[r]=625;
            }
            repaint();
        } 
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
           if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
           {
               System.exit(0);
           }
        
        if(gmovr)
        {               
              
          if(e.getKeyCode()==KeyEvent.VK_SPACE)
         {
            moves=0;
            score=0;
            length=3;
            c= 2;
            gmovr=false;
            repaint();
         }
        }
        else if(!gmovr)
        {
        
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
         {
            moves++;
            right=true;
            if(!left)
                right=true;
            else
            {
                right=false;
                left=true;
            }
            up=false;
            down=false;
         }
        
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
         {
            moves++;
            left=true;
            if(!right)
                left=true;
            else
            {
                left=false;
                right=true;
            }
            up=false;
            down=false;
         }
        
         if(e.getKeyCode()==KeyEvent.VK_UP)
         {
            moves++;
            up=true;
            if(!down)
                up=true;
            else
            {
                up=false;
                down=true;
            }
            right=false;
            left=false;
         }
        
         if(e.getKeyCode()==KeyEvent.VK_DOWN)
         {
            moves++;
            down=true;
            if(!up)
                down=true;
            else
            {
                down=false;
                up=true;
            }
            right=false;
            left=false;
         }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
