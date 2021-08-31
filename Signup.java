
package hitchkkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.regex.Matcher;
import javax.swing.border.LineBorder;
import java.util.regex.Pattern;
 


public class Signup extends JFrame implements ActionListener{
    
    JButton button1,button2;
    JCheckBox checkBox1;
    JTextField text1,text2,text3,text5,text6;
    JPasswordField text4;
    int flag=0;

    public static boolean isValidName( String name ) {
      return name.matches( "[A-Z][a-z]*" )||name.matches("[a-z]*");
   }
    
   public static boolean isValidPassword( String password ) {
      return password.length()>=8;
   } 
    
     public static boolean isValidUsername(String name)
    {
        String regex = "^[A-Za-z]\\w{5,29}$";
  
        Pattern p = Pattern.compile(regex);
  
        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);
  
        return m.matches();
    }
    
    public static boolean isValid(String email){  
        String emailFormate ="^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailFormate);  
        if(email == null){  
        return false;  
        }   
        return p.matcher(email).matches();  
    }
    
     public static boolean isValidDate(String d)
    {
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence)d);
        return matcher.matches();
    }
    
    Signup(){
        ImageIcon image11 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Icon.png"));
        Image image12 = image11.getImage();
        setIconImage(image12);
        setBounds(268,182,1000,500);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setResizable(false);
        setTitle("Sign Up");
        setLayout(null);
        
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 0, 500, 462);
        panel1.setBackground(new Color(94,223,255));
        panel1.setLayout(null);
        add(panel1);
        
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Signup.png"));
        Image image2 = image1.getImage().getScaledInstance(500, 462, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label1 = new JLabel(image3);
        label1.setBounds(0,0 ,500 ,462 );
        panel1.add(label1);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(500, 0, 500, 500);
        panel2.setBackground(new Color(232,236,241));
        add(panel2);
        
        text1 = new JTextField("First Name");
        text1.setForeground(new Color(115,147,167));
        text1.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text1.getText().trim().equals("First Name")){
           flag++;
           text1.setText("");
           text1.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text1.getText().trim().equals("")){
           flag--;
           text1.setText("First Name");
           text1.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text1.setBounds(100, 40, 300, 30);
        text1.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text1.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text1);
        
        text2 = new JTextField("Last Name");
        text2.setForeground(new Color(115,147,167));
        text2.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text2.getText().trim().equals("Last Name")){
           flag++;
           text2.setText("");
           text2.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text2.getText().trim().equals("")){
           flag--;
           text2.setText("Last Name");
           text2.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text2.setBounds(100, 90, 300, 30);
        text2.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text2.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text2);
        
        text3 = new JTextField("Username");
        text3.setForeground(new Color(115,147,167));
        text3.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text3.getText().trim().equals("Username")){
           flag++;
           text3.setText("");
           text3.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text3.getText().trim().equals("")){
           flag--;
           text3.setText("Username");
           text3.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text3.setBounds(100, 140, 300, 30);
        text3.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text3.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text3);
        
        text4 = new JPasswordField("Password");
        text4.setForeground(new Color(115,147,167));
        text4.setEchoChar((char)0);
        text4.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text4.getText().trim().equals("Password")){
           flag++;
           text4.setText("");
           text4.setForeground(new Color(0,0,0));
           text4.setEchoChar('\u2022');
      //     text4.setFont(new Font("mukta",Font.PLAIN,10));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text4.getText().trim().equals("")){
           flag--;
           text4.setText("Password");
           text4.setEchoChar((char)0);
           text4.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text4.setBounds(100, 190, 300, 30);
        text4.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text4.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text4);
        
        text5 = new JTextField("Email ID");
        text5.setForeground(new Color(115,147,167));
        text5.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text5.getText().trim().equals("Email ID")){
           flag++;
           text5.setText("");
           text5.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text5.getText().trim().equals("")){
           flag--;
           text5.setText("Email ID");
           text5.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text5.setBounds(100, 240, 300, 30);
        text5.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text5.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text5);
        
        
        text6 = new JTextField("Birthdate (dd/mm/yyyy)");
        text6.setForeground(new Color(115,147,167));
        text6.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(text6.getText().trim().equals("Birthdate (dd/mm/yyyy)")){
           flag++;
           text6.setText("");
           text6.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(text6.getText().trim().equals("")){
           flag--;
           text6.setText("Birthdate (dd/mm/yyyy)");
           text6.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        text6.setBounds(100, 290, 300, 30);
        text6.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        text6.setFont(new Font("mukta",Font.PLAIN,20));
        panel2.add(text6);
        
        checkBox1 = new JCheckBox("<HTML>&emsp;I have read terms and conditions and i <br>&emsp;agree with them</HTML>", true);  
        checkBox1.setBounds(100,330, 300,60); 
        checkBox1.setBackground(new Color(232,236,241));
        checkBox1.setForeground(new Color(119,172,241));
        checkBox1.setFont(new Font("oxygen",Font.PLAIN,13));
        checkBox1.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        checkBox1.addActionListener(this);
        panel2.add(checkBox1);
        
        button1 = new JButton("Sign Up");
        button1.setBounds(100,400 , 140, 30);
        button1.setBackground(new Color(94,223,255));
        button1.setForeground(Color.WHITE);
        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.addActionListener(this);
        panel2.add(button1);
        
        button2 = new JButton("Back");
        button2.setBounds(260,400 , 140, 30);
        button2.setForeground(new Color(94,223,255));
        button2.setBackground(Color.WHITE);
        button2.setBorder(new LineBorder(new Color(94,223,255)));
        button2.addActionListener(this);
        panel2.add(button2);
        
        
        getRootPane().setDefaultButton(button1);
        setVisible(true);
        requestFocus();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button1){
            String firstname = text1.getText();
            String lastname = text2.getText();
            String username = text3.getText();
            String password = text4.getText();
            String email = text5.getText();
            String dob = text6.getText();
            try{
            String sql = "select * from account where username = '"+username+"'";
                Data d = new Data();
                ResultSet result2 = d.s.executeQuery(sql);
                if(!(result2.next())&&isValidName(firstname)&&isValidName(lastname)&&isValid(email)&&isValidDate(dob)&&isValidUsername(username)&&flag>=6&&isValidPassword(password)){
                    flag=0;
                    String query = "insert into account values('"+firstname+"','"+lastname+"','"+username+"','"+password+"','"+email+"','"+dob+"')";
                    Data d1 = new Data();
                    d1.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Account created successfully");
                    this.setVisible(false);
                    new Login().setVisible(true);
                }else if(flag<6){
                    JOptionPane.showMessageDialog(null, "Fill all fields");
                }else if(!isValidName(firstname)){
                    JOptionPane.showMessageDialog(null, "Invalid First Name");
                }else if(!isValidName(lastname)){
                    JOptionPane.showMessageDialog(null, "Invalid Last Name");
                }else if(!isValidUsername(username)){
                    if(username.length()<6)
                        JOptionPane.showMessageDialog(null, "Username too short(Minimum length required : 6)");
                    else
                        JOptionPane.showMessageDialog(null, "Invalid Username");
                }else if(result2.next()){
                    JOptionPane.showMessageDialog(null, "Username taken");
                }else if(!isValidPassword(password)){
                    JOptionPane.showMessageDialog(null, "Password too short(Minimum length requird : 8)");
                }else if(!isValid(email)){
                    JOptionPane.showMessageDialog(null, "Invalid Email ID");
                }else if(!isValidDate(dob)){
                    JOptionPane.showMessageDialog(null, "Invalid Birthdate");
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR 404");
                }
            }catch( Exception ex){
                
            }
        }else if(e.getSource()==button2){
            new Login().setVisible(true);
            this.setVisible(false);
        }else{
            if(checkBox1.isSelected()){
                button1.setEnabled(true);
                button1.setBackground(new Color(94,223,255));
                button1.setForeground(Color.WHITE);
                }else{
                button1.setEnabled(false);
                button1.setForeground(Color.WHITE);
                button1.setBackground(new Color(211,224,234));
            }
        }
    }
    
    
    
    public static void main(String [] args){
        
        new Signup().setVisible(true);
        
        
    }
}
