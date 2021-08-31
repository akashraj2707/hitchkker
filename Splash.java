

package hitchkkers;

import javax.swing.*;
import java.awt.*;

public class Splash {
    public static void main (String [] args){
        
       SplashFrame frame = new SplashFrame();
       frame.setVisible(true);
       frame.setBounds(162, 132, 1200, 600); 
       
    }
}

class SplashFrame extends JFrame implements Runnable{
    
    Thread thread1 ;
    
    SplashFrame(){
        
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Splash.jpg"));
        Image image2 = image1.getImage().getScaledInstance(1200, 600, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label1 = new JLabel(image3);
        add(label1);  
        setUndecorated(true);
        thread1 = new Thread(this);
        thread1.start();
        
    }

    public void run(){
        
        try{
            
            Thread.sleep(5000);
            this.setVisible(false);
            new Login().setVisible(true);
            
        }catch(Exception e){}
            
    }
}
