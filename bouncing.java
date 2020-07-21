import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
public class bouncing {
    public static void main(String[] args) {
        bouncingBall bb = new bouncingBall();
    }    
}

class bouncingBall extends JFrame{
    int width,height;
    //size of ball
    float radius = 35;
    float diameter = radius*2;

    //Center
    float X = radius+200;
    float Y = radius+200;

    //Direction
    float xDirection = 2;
    float yDirection = 2;

    public bouncingBall(){
        
        Thread thread = new Thread(() -> {
            while(true){
                width = getWidth();
                height = getHeight()-27;

                X += xDirection;
                Y += yDirection;
                
                if(X - radius < 0){
                    xDirection = -xDirection;
                    X = radius;
                } else if(X + radius > width){
                    xDirection = -xDirection;
                    X = width - radius;
                }
                if(Y - radius < 0){
                    yDirection = -yDirection;
                    Y = radius;
                } else if(Y + radius > height){
                    yDirection = -yDirection;
                    Y = height - radius;
                }
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
            }
        });
        thread.start();
        
        setVisible(true);
        setSize(640,500);
        setLocation(300,300);
        setResizable(false);
        setTitle("Bouncing Ball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.red);
        g.fillOval((int)(X-radius), (int)(Y - radius+27), (int)diameter, (int)diameter);
    }
}