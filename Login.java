
package hitchkkers;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton button1 , button2 , button3;
    JTextField text1;
    JPasswordField text2;
    
    Login(){
        
        setLayout(null);
        ImageIcon image11 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Icon.png"));
        Image image12 = image11.getImage();
        setIconImage(image12);
        setBounds(418,282,700,300);
        setResizable(false);
        setTitle("Login page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 300, 300);
        panel1.setBackground(new Color(94,223,255));
        panel1.setLayout(null);
        add(panel1);
        
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Login.png"));
        Image image2 = image1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label1 = new JLabel(image3);
        label1.setBounds(0,0 ,300 ,300 );
        panel1.add(label1);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(300, 0, 400, 300);
        panel2.setBackground(new Color(232,236,241));
        add(panel2);
        
        text1 = new JTextField("Username");
        text1.setForeground(new Color(115,147,167));
        text1.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text1.getText().trim().equals("Username")){
           text1.setText("");
           text1.setForeground(new Color(0,0,0));
        }
        else
          ;
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text1.getText().trim().equals("")){
           text1.setText("Username");
           text1.setForeground(new Color(115,147,167));
        }
        else
           ;
            } 
        }                
        );
        
        text1.setBounds(50, 40, 300, 30);
        text1.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text1.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text1);
        
        text2 = new JPasswordField("Password");
        text2.setForeground(new Color(115,147,167));
        text2.setEchoChar((char)0);
        text2.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text2.getText().trim().equals("Password")){
           text2.setText("");
           text2.setForeground(new Color(0,0,0));
           text2.setEchoChar('\u2022');
        }
        else
          ;
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text2.getText().trim().equals("")){
           text2.setText("Password");
           text2.setEchoChar((char)0);
           text2.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text2.setBounds(50, 90, 300, 30);
        text2.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text2.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text2);
                
        button1 = new JButton("Sign In");
        button1.setBounds(50,150 , 140, 30);
        button1.setBackground(new Color(94,223,255));
        button1.setForeground(Color.WHITE);
        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.addActionListener(this);
        panel2.add(button1);
        
        button2 = new JButton("Sign Up");
        button2.setBounds(210,150 , 140, 30);
        button2.setForeground(new Color(94,223,255));
        button2.setBackground(Color.WHITE);
        button2.setBorder(new LineBorder(new Color(94,223,255)));
        button2.addActionListener(this);
        panel2.add(button2);
        
        button3 = new JButton("<HTML><U>Forgot password</U></HTML>");
        button3.setBounds(50, 190, 100, 20);
        button3.setBackground(new Color(232,236,241));
        button3.setForeground(new Color(119,172,241));
        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.addActionListener(this);
        button3.setFont(new Font("oxygen",Font.PLAIN,13));
        panel2.add(button3);
        
        getRootPane().setDefaultButton(button1);
        setVisible(true);
        requestFocus();
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button1){
            try{
                String username = text1.getText();
                String password = text2.getText();
                String sql = "select * from account where username = '"+username+"' AND password = '"+password+"'";
                Data d = new Data();
                ResultSet result = d.s.executeQuery(sql);
                if(result.next()){
                    new Dashboard(username,"Home").setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Username/Password");
                }
            }catch(Exception ex){
                
            }       
        }else if(e.getSource()==button2){
            new Signup().setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Contact us at help@gmail.com");
        }
    }
    
    public static void main(String [] args){
        new Login();
    }
}
