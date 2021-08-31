
package hitchkkers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;




public class Dashboard extends JFrame implements ActionListener{

    
static int x[] = new int[50];
static int y=0,ry=0;
JButton lbutton,rbutton,photo,post;
JScrollPane scrollpane1,scrollpane2,scrollpane3;
JScrollPane lscroll;
JLayeredPane  mainpanel;
JTextArea write;
JTextField ltext,rtext,place,date;
JLayeredPane lclabel,feedlabel, rclabel;
JButton[] lbuttons = new JButton[50];
JButton[] ldbuttons = new JButton[50];
JButton[] rbuttons = new JButton[50];
JButton[] rdbuttons = new JButton[50];
static String[] places = new String[50];
static String[] rplaces = new String[50];
String username,fplace="Home",path = "hitchkkers/icons/v1.jpg";


public static boolean isValidPlace( String place ) {
      for(int i=0;i<50;i++){
          if(place.equals(places[i])) return false;
      }
      return true;
      
   }

public static boolean risValidPlace( String place ) {
      for(int i=0;i<50;i++){
          if(place.equals(rplaces[i])) return false;
      }
      return true;
      
   }

 public static boolean isValidDate(String d)
    {
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence)d);
        return matcher.matches();
    }
    
    Dashboard(String user,String currentplace) throws SQLException{
        
        fplace=currentplace;
        username = user;
        for(int i = 0;i<50;i++){
            x[i]=20+60*(i);
        }

        y=0;
        ry=0;
        
        
   
        
        //jframe
        ImageIcon image11 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Icon.png"));
        Image image12 = image11.getImage();
        setIconImage(image12);
        setLayout(null);
        setBounds(0,0,1536,864);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle(username+" "+currentplace);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        //mainpanel
        mainpanel = new JLayeredPane ();
        mainpanel.setBounds(0, 0, 1530, 860);
        mainpanel.setBackground(new Color(94,223,255));
        mainpanel.setLayout(null);
        add(mainpanel); 
        
        //background label
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/background.jpg"));
        Image image2 = image1.getImage().getScaledInstance(1530, 860, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel backgroundlabel = new JLabel(image3);
        backgroundlabel.setBounds(0, 0, 1530, 860);
        mainpanel.add(backgroundlabel,1,0);
        
        //llabel
        JLabel llabel = new JLabel();
        llabel.setBounds(20, 20, 344, 800);
        llabel.setOpaque(true);
        llabel.setBackground(new Color(255,255,255));
        llabel.setLayout(null);
        mainpanel.add(llabel,2,0);
        
        //rlabel
        JLabel rlabel = new JLabel();
        rlabel.setOpaque(true);
        rlabel.setBounds(1172, 20,338 , 800);
        rlabel.setBackground(new Color(255,255,255));
        rlabel.setLayout(null);
        mainpanel.add(rlabel,3,0);
        
        //write something about your day
        write = new JTextArea("Write something about your day",2,57);
        write.setLineWrap(true);
        write.setForeground(new Color(115,147,167));
        write.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(write.getText().trim().equals("Write something about your day")){
           write.setText("");
           write.setForeground(new Color(0,0,0));
        }
        else
          ; 
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(write.getText().trim().equals("")){
           write.setText("Write something about your day");
           write.setForeground(new Color(115,147,167));
        }
        else
           ;
            } 
        }                
        );
        
        write.setBounds(384, 40, 668, 80);
        write.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        write.setFont(new Font("mukta",Font.PLAIN,20));
        mainpanel.add(write,4,0);
        
        // photo
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/Photoicon.png"));
        photo = new JButton(icon);
        photo.setBounds(1072,40 ,80 , 80);
        photo.setBackground(Color.WHITE);
        photo.setForeground(Color.DARK_GRAY);
        photo.setBorder(BorderFactory.createEmptyBorder());
        photo.addActionListener(this);
        mainpanel.add(photo,5,0);
        
        //place
        place = new JTextField("Location");
        place.setForeground(new Color(115,147,167));
        place.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(place.getText().trim().equals("Location")){
           place.setText("");
           place.setForeground(new Color(0,0,0));
        }
        else
          ; 
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(place.getText().trim().equals("")){
           place.setText("Location");
           place.setForeground(new Color(115,147,167));
        }
        else
           ;
            } 
        }                
        );
        
        place.setBounds(384, 140, 324, 40);
        place.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        place.setFont(new Font("mukta",Font.PLAIN,20));
        mainpanel.add(place,6,0);
        
        //date
        date = new JTextField("Visiting Date");
        date.setForeground(new Color(115,147,167));
        date.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(date.getText().trim().equals("Visiting Date")){
           date.setText("");
           date.setForeground(new Color(0,0,0));
        }
        else
          ; 
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(date.getText().trim().equals("")){
           date.setText("Visiting Date");
           date.setForeground(new Color(115,147,167));
        }
        else
           ;
            } 
        }                
        );
        
        date.setBounds(728, 140, 324, 40);
        date.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        date.setFont(new Font("mukta",Font.PLAIN,20));
        date.addActionListener(e -> post.doClick());
        mainpanel.add(date,7,0);
        
        //post button
        post = new JButton("Post");
        post.setBounds(1072, 140, 80, 40);
        post.setForeground(new Color(140,140,140));
        post.setBackground(Color.WHITE);
        post.setBorder(BorderFactory.createEmptyBorder());
        post.addActionListener(this);
        mainpanel.add(post,8,0);
        
        //ltlabel
        JLabel ltlabel = new JLabel("Visited", SwingConstants.CENTER);
        ltlabel.setForeground(new Color(0,173,181));
        ltlabel.setBounds(20,20 ,344 , 80);
        ltlabel.setBackground(Color.WHITE);
        ltlabel.setFont(new Font("mukta",Font.PLAIN,20));
        mainpanel.add(ltlabel,9,0);
        
        //rtlabel
        JLabel rtlabel = new JLabel("To be visited", SwingConstants.CENTER);
        rtlabel.setForeground(new Color(255,154,140));
        rtlabel.setBounds(1172, 20 ,344 , 80);
        rtlabel.setBackground(Color.WHITE);
        rtlabel.setFont(new Font("mukta",Font.PLAIN,20));
        mainpanel.add(rtlabel,10,0);
        
        //lbutton
        lbutton = new JButton("Add visited");
        lbutton.setBounds(40,760 ,298 , 40);
        lbutton.setBackground(new Color(17,153,158));
        lbutton.setForeground(Color.WHITE);
        lbutton.setBorder(BorderFactory.createEmptyBorder());
        lbutton.addActionListener(this);
        mainpanel.add(lbutton,11,0);
        
        // ltext
        ltext = new JTextField("Add visited");
        ltext.setForeground(new Color(115,147,167));
        ltext.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(ltext.getText().trim().equals("Add visited")){
           ltext.setText("");
           ltext.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(ltext.getText().trim().equals("")){
           ltext.setText("Add visited");
           ltext.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        ltext.setBounds(40,705 ,298 , 40);
        ltext.setBackground(new Color(227,253,253));
        ltext.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        ltext.setFont(new Font("mukta",Font.PLAIN,20));
        ltext.addActionListener(e -> lbutton.doClick());
        mainpanel.add(ltext,12,0);
        
       //rbutton
        rbutton = new JButton("Add to be visited");
        rbutton.setBounds(1192,760 ,298 , 40);
        rbutton.setBackground(new Color(228,88,38));
        rbutton.setForeground(Color.WHITE);
        rbutton.setBorder(BorderFactory.createEmptyBorder());
        rbutton.addActionListener(this);
        mainpanel.add(rbutton,13,0);
        
        //rtext
        rtext = new JTextField("Add to be visited");
        rtext.setForeground(new Color(115,147,167));
        rtext.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
        if(rtext.getText().trim().equals("Add to be visited")){
           rtext.setText("");
           rtext.setForeground(new Color(0,0,0));
        }
        else
          ; //do nothing
        } 
          
          @Override
          public void focusLost(FocusEvent e) {
        if(rtext.getText().trim().equals("")){
           rtext.setText("Add to be visited");
           rtext.setForeground(new Color(115,147,167));
        }
        else
           ;//do nothing
            } 
        }                
        );
        
        rtext.setBounds(1192,705 ,298 , 40);
        rtext.setBackground(new Color(255,226,226));
        rtext.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        rtext.setFont(new Font("mukta",Font.PLAIN,20));
        rtext.addActionListener(e -> rbutton.doClick());
        mainpanel.add(rtext,14,0);
        
        //lclabel
        lclabel = new JLayeredPane();
        lclabel.setBounds(20, 100, 344, 1585);
        lclabel.setPreferredSize(new Dimension(344, 11585));
        lclabel.setOpaque(true);
        lclabel.setBackground(new Color(240,240,240));
        lclabel.setFont(new Font("mukta",Font.PLAIN,20));
       // mainpanel.add(lclabel,10,0);
        
        //rclabel
        rclabel = new JLayeredPane();
        rclabel.setBounds(1172, 100, 338, 1585);
        rclabel.setPreferredSize(new Dimension(338, 11585));
        rclabel.setOpaque(true);
        rclabel.setBackground(new Color(240,240,240));
        rclabel.setFont(new Font("mukta",Font.PLAIN,20));
        
        
        //scrollpane1
        scrollpane1 = new JScrollPane(lclabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane1.setBounds(20, 100, 344, 585);
        scrollpane1.setPreferredSize(new Dimension(344, 585));
        scrollpane1.setEnabled(true);
        scrollpane1.setBackground(new Color(40,40,40));
        scrollpane1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        scrollpane1.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane1.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        mainpanel.add(scrollpane1,100,0);
        
        
        //scrollpane2
        scrollpane3 = new JScrollPane(rclabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane3.setBounds(1172, 100, 338, 585);
        scrollpane3.setPreferredSize(new Dimension(338, 585));
        scrollpane3.setEnabled(true);
        scrollpane3.setBackground(new Color(40,40,40));
        scrollpane3.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        scrollpane3.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane3.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        mainpanel.add(scrollpane3,100,0);  
        
        feedlabel = new JLayeredPane();
        feedlabel.setBounds(384, 200, 768, 111585);
        feedlabel.setPreferredSize(new Dimension(344, 11585));
        feedlabel.setOpaque(true);
        feedlabel.setBackground(new Color(240,240,240));
        feedlabel.setFont(new Font("mukta",Font.PLAIN,20));
        
        scrollpane2 = new JScrollPane(feedlabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane2.setBounds(384, 200, 768, 620);
        scrollpane2.setPreferredSize(new Dimension(768, 680));
        scrollpane2.setEnabled(true);
        scrollpane2.setOpaque(true);
        scrollpane2.setBackground(new Color(40,40,40));
        scrollpane2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        scrollpane2.getVerticalScrollBar().setUnitIncrement(16);
        scrollpane2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        mainpanel.add(scrollpane2,100,0);
        
        
//        for(int i=0;i<10;i++){
//            JLabel location = new JLabel("Location,Date");
//            location.setBounds(20,40+i*590,728,40);
//            location.setBackground(new Color(113,201,206));
//            location.setOpaque(true);
//            location.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//            location.setForeground(Color.WHITE);
//            location.setFont(new Font("mukta",Font.PLAIN,20));
//            feedlabel.add(location,50,0);
//            
//            
//            String s=String.valueOf(i);  
//            String source = "hitchkkers/icons/v"+s+".jpg";
//            ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource(source));
//            Image image5 = image4.getImage().getScaledInstance(728, 409, Image.SCALE_DEFAULT);
//            ImageIcon image6 = new ImageIcon(image5);
//            JLabel feedimage = new JLabel(image6);
//            feedimage.setBounds(20, 80+i*590, 728, 409);
//            feedlabel.add(feedimage,50,0);
//
//            JLabel caption = new JLabel("Write something about your day");
//            caption.setBounds(20,489+i*590,728,80);
//            caption.setBackground(Color.WHITE);
//            caption.setOpaque(true);
//            caption.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//            caption.setForeground(new Color(20,83,116));
//            caption.setFont(new Font("mukta",Font.PLAIN,20));
//            caption.setVerticalAlignment(JLabel.TOP);
//            feedlabel.add(caption,50,0);
//            
//            JLabel sep = new JLabel();
//            sep.setBounds(0,599+i*590,768,1);
//            sep.setBackground(Color.BLACK);
//            sep.setOpaque(true);
//            feedlabel.add(sep,50,0);
//            
//        }
        

        int i=0;
        try{
            String query = "select * from feed where username = '"+username+"' AND place = '"+currentplace+"'";
            Data d = new Data();
            ResultSet result = d.s.executeQuery(query);
            while(result.next()){
            String locationp = result.getString(4);
            locationp = locationp.substring(0, 1).toUpperCase() + locationp.substring(1);
            String datep = result.getString(5);
            String captionp = result.getString(2);
            JLabel location = new JLabel(locationp+","+datep);
            location.setBounds(20,40+i*590,728,40);
            location.setBackground(new Color(113,201,206));
            location.setOpaque(true);
            location.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            location.setForeground(Color.WHITE);
            location.setFont(new Font("mukta",Font.PLAIN,20));
            feedlabel.add(location,50,0);
            
            
            String s=String.valueOf(i+1);  
            ImageIcon image4;
            try{String source = "hitchkkers/icons/"+username+currentplace+s+".jpg";
            image4 = new ImageIcon(ClassLoader.getSystemResource(source));}
            catch(Exception e){
                String source = "hitchkkers/icons/default.jpg";
                image4 = new ImageIcon(ClassLoader.getSystemResource(source));
            }
            Image image5 = image4.getImage().getScaledInstance(728, 409, Image.SCALE_DEFAULT);
            ImageIcon image6 = new ImageIcon(image5);
            JLabel feedimage = new JLabel(image6);
            feedimage.setBounds(20, 80+i*590, 728, 409);
            feedlabel.add(feedimage,50,0);

            JTextArea caption = new JTextArea(captionp);
            caption.setBounds(20,489+i*590,728,80);
            caption.setEditable(false);
            caption.setBackground(Color.WHITE);
            caption.setLineWrap(true);
            caption.setOpaque(true);
            caption.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            caption.setForeground(new Color(20,83,116));
            caption.setFont(new Font("mukta",Font.PLAIN,20));
//            caption.setVerticalAlignment(JLabel.TOP);
            feedlabel.add(caption,50,0);
            
            JLabel sep = new JLabel();
            sep.setBounds(0,599+i*590,768,1);
            sep.setBackground(Color.BLACK);
            sep.setOpaque(true);
            feedlabel.add(sep,50,0);
            i++;
            }
        }catch(Exception ex){
                
       }



        
        try{
        String query = "select * from tobevisitedtable where username = '"+username+"'";
        Data d = new Data();
        ResultSet result = d.s.executeQuery(query);
        while(result.next()){
           
            String rplace= result.getString(2);
            rbuttons[ry] = new JButton(rplace);
            rbuttons[ry].setBackground(Color.WHITE);
            rbuttons[ry].setBounds(20,x[ry] ,238 , 40);
            rbuttons[ry].setForeground(new Color(228,88,38));
            rbuttons[ry].setBorder(BorderFactory.createEmptyBorder());
            rbuttons[ry].addActionListener(this);
            rbuttons[ry].setFont(new Font("mukta",Font.PLAIN,20));
            rclabel.add(rbuttons[ry],50,0);
            
            
            ImageIcon deleteicon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/delete.png"));
            rdbuttons[ry] = new JButton(deleteicon);
            rdbuttons[ry].setBounds(278,x[ry] ,40 , 40);
            rdbuttons[ry].setBackground(Color.WHITE);
            rdbuttons[ry].setForeground(Color.DARK_GRAY);
            rdbuttons[ry].setBorder(BorderFactory.createEmptyBorder());
            rdbuttons[ry].addActionListener(this);
            rclabel.add(rdbuttons[ry],50,0);
            
            rplaces[ry] = rplace;
            ry++;
        }
        }catch(Exception ex){
                
       }
        
        String lplace= "Home";
            lbuttons[y] = new JButton(lplace);
            lbuttons[y].setBackground(Color.WHITE);
            lbuttons[y].setBounds(20,x[y] ,238 , 40);
            lbuttons[y].setForeground(new Color(17,153,158));
            lbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            lbuttons[y].addActionListener(this);
            lbuttons[y].setFont(new Font("mukta",Font.PLAIN,20));
            lclabel.add(lbuttons[y],50,0);
            
            
            ImageIcon deleteicon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/delete.png"));
            ldbuttons[y] = new JButton(deleteicon);
            ldbuttons[y].setBounds(278,x[y] ,40 , 40);
            ldbuttons[y].setBackground(Color.WHITE);
            ldbuttons[y].setForeground(Color.DARK_GRAY);
            ldbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            ldbuttons[y].addActionListener(this);
            lclabel.add(ldbuttons[y],50,0);
           
            places[y] = lplace;
            y++;
        
        
        try{
        String query = "select * from visitedtable where username = '"+username+"'";
        Data d = new Data();
        ResultSet result = d.s.executeQuery(query);
        while(result.next()){
           
            lplace= result.getString(2);
            lbuttons[y] = new JButton(lplace);
            lbuttons[y].setBackground(Color.WHITE);
            lbuttons[y].setBounds(20,x[y] ,238 , 40);
            lbuttons[y].setForeground(new Color(17,153,158));
            lbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            lbuttons[y].addActionListener(this);
            lbuttons[y].setFont(new Font("mukta",Font.PLAIN,20));
            lclabel.add(lbuttons[y],50,0);
            
            
            //ImageIcon deleteicon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/delete.png"));
            ldbuttons[y] = new JButton(deleteicon);
            ldbuttons[y].setBounds(278,x[y] ,40 , 40);
            ldbuttons[y].setBackground(Color.WHITE);
            ldbuttons[y].setForeground(Color.DARK_GRAY);
            ldbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            ldbuttons[y].addActionListener(this);
            lclabel.add(ldbuttons[y],50,0);
           
            places[y] = lplace;
            y++;
        }
        
        
        }catch(Exception ex){
                
       }
        
        
//        lbuttons[0].setEnabled(false);
//        lbuttons[0].setVisible(false);
//        lbuttons[0].setBounds(46, x[0], 298, 40);
//        lbuttons[0].setEnabled(true);
//        lbuttons[0].setVisible(true);
//        lclabel.add(lbuttons[0],50,0);
//        ldbuttons[0].setEnabled(false);
//        ldbuttons[0].setVisible(false);
       
        for(int k=0;k<y;k++){
            if(places[k].equals(fplace)){
                for(int j = 0;j<y;j++){
                    lbuttons[j].setBounds(20, x[j], 238, 40);
                    ldbuttons[j].setVisible(true);
                    ldbuttons[j].setEnabled(true);
                    ldbuttons[j].setBounds(278,x[j],40,40);
                }
                
                    lbuttons[0].setBounds(20, x[0], 298, 40);
                    ldbuttons[0].setEnabled(false);
                    ldbuttons[0].setVisible(false);
                    lbuttons[k].setBounds(46, x[k], 298, 40);
                    ldbuttons[k].setVisible(false);
                    ldbuttons[k].setEnabled(false);
        }}
            

        setVisible(true);
        requestFocus();

    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        if(e.getSource()==lbutton){
            String place = ltext.getText();
            place=place.toLowerCase();
            place = place.substring(0, 1).toUpperCase() + place.substring(1);
            if(!place.equals("Add visited")&&isValidPlace(place)&&!place.equals(""))
            {
                
            String query = "insert into visitedtable values('"+username+"','"+place+"')";
             Data d1 = new Data();
            try {
                d1.s.executeUpdate(query);
            } catch (SQLException ex) {
                
            }    
            lbuttons[y] = new JButton(place);
            lbuttons[y].setBackground(Color.WHITE);
            lbuttons[y].setBounds(20,x[y] ,238 , 40);
            lbuttons[y].setForeground(new Color(17,153,158));
            lbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            lbuttons[y].addActionListener(this);
            lbuttons[y].setFont(new Font("mukta",Font.PLAIN,20));
            lclabel.add(lbuttons[y],50,0);
            
            ImageIcon deleteicon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/delete.png"));
            ldbuttons[y] = new JButton(deleteicon);
            ldbuttons[y].setBounds(278,x[y] ,40 , 40);
            ldbuttons[y].setBackground(Color.WHITE);
            ldbuttons[y].setForeground(Color.DARK_GRAY);
            ldbuttons[y].setBorder(BorderFactory.createEmptyBorder());
            ldbuttons[y].addActionListener(this);
            lclabel.add(ldbuttons[y],50,0);
            
            
           
            places[y] = place;
            y++;
            }else if(!isValidPlace(place)){
                JOptionPane.showMessageDialog(null, "Place already added");
            }
            
            

            
        }
        
        for(int i = 0;i<y;i++){
            if(e.getSource()==lbuttons[i]){
                fplace=places[i];
                for(int j = 0;j<y;j++){
                    lbuttons[j].setBounds(20, x[j], 238, 40);
                    ldbuttons[j].setVisible(true);
                    ldbuttons[j].setEnabled(true);
                    ldbuttons[j].setBounds(278,x[j],40,40);
                }
                
                    lbuttons[0].setBounds(20, x[0], 298, 40);
                    ldbuttons[0].setEnabled(false);
                    ldbuttons[0].setVisible(false);
                    lbuttons[i].setBounds(46, x[i], 298, 40);
                    ldbuttons[i].setVisible(false);
                    ldbuttons[i].setEnabled(false);
                    
                    try {
                new Dashboard(username,fplace).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.setVisible(false);
            }
            
            
        }
        
        for(int i = 0;i<y;i++){
            if(e.getSource()==ldbuttons[i]){
                
                int dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete "+places[i],"WARNING",JOptionPane.YES_NO_OPTION);
                if(dialogButton == JOptionPane.YES_OPTION) {
                String query = "delete from visitedtable where username = '"+username+"' AND visited = '"+places[i]+"'";
                String query2 = "delete from feed where username = '"+username+"' AND place = '"+places[i]+"'";
                Data d1 = new Data();
                try {
                d1.s.executeUpdate(query);
                d1.s.executeUpdate(query2);
                } catch (SQLException ex) {
                
            }   
                
                for(int j=0;j<y;j++){
                    lbuttons[j].setEnabled(false);
                    lbuttons[j].setVisible(false);
                    ldbuttons[j].setEnabled(false);
                    ldbuttons[j].setVisible(false);
                }
                
                for(int j=i;j<y-1;j++){
                    lbuttons[j]=lbuttons[j+1];
                    ldbuttons[j]=ldbuttons[j+1];
                    places[j]=places[j+1];
                }
                places[y-1]=null;
                y--;
                for(int j=0;j<y;j++){
                    if(places[j].equals(fplace)){
                        lbuttons[j].setBounds(46, x[j], 298, 40);
                    } else lbuttons[j].setBounds(20,x[j] ,238 , 40);
                    lclabel.add(lbuttons[j],50,0);
                    lbuttons[j].setVisible(true);
                    lbuttons[j].setEnabled(true);
                    
                    ldbuttons[j].setBounds(278,x[j] ,40 , 40);
                    lclabel.add(ldbuttons[j],50,0);
                    if(places[j].equals(fplace)){
                        ldbuttons[j].setVisible(false);
                        ldbuttons[j].setEnabled(false);
                    }else{
                        ldbuttons[j].setVisible(true);
                        ldbuttons[j].setEnabled(true);
                    }
                    
                }
                ltext.requestFocus();
                
                lbuttons[0].setEnabled(false);
                lbuttons[0].setVisible(false);
                if(places[0].equals(fplace)){lbuttons[0].setBounds(46, x[0], 298, 40);}
                else lbuttons[0].setBounds(20, x[0], 298, 40);
                lbuttons[0].setEnabled(true);
                lbuttons[0].setVisible(true);
                lclabel.add(lbuttons[y],50,0);
                ldbuttons[0].setEnabled(false);
                ldbuttons[0].setVisible(false);
            }
            else {remove(dialogButton);}
            }
              
        }
        
        
        
        if(e.getSource()==rbutton){
             
            
            String rplace = rtext.getText();
            rplace=rplace.toLowerCase();
            rplace = rplace.substring(0, 1).toUpperCase() + rplace.substring(1);
            if(!rplace.equals("Add to be visited")&&risValidPlace(rplace)&&!rplace.equals(""))
            { 
            String query = "insert into tobevisitedtable values('"+username+"','"+rplace+"')";
             Data d1 = new Data();
            try {
                d1.s.executeUpdate(query);
            } catch (SQLException ex) {
                
            }
            rbuttons[ry] = new JButton(rplace);
            rbuttons[ry].setBackground(Color.WHITE);
            rbuttons[ry].setBounds(20,x[ry] ,238 , 40);
            rbuttons[ry].setForeground(new Color(228,88,38));
            rbuttons[ry].setBorder(BorderFactory.createEmptyBorder());
            rbuttons[ry].addActionListener(this);
            rbuttons[ry].setFont(new Font("mukta",Font.PLAIN,20));
            rclabel.add(rbuttons[ry],50,0);
            
            ImageIcon deleteicon = new ImageIcon(ClassLoader.getSystemResource("hitchkkers/icons/delete.png"));
            rdbuttons[ry] = new JButton(deleteicon);
            rdbuttons[ry].setBounds(278,x[ry] ,40 , 40);
            rdbuttons[ry].setBackground(Color.WHITE);
            rdbuttons[ry].setForeground(Color.DARK_GRAY);
            rdbuttons[ry].setBorder(BorderFactory.createEmptyBorder());
            rdbuttons[ry].addActionListener(this);
            rclabel.add(rdbuttons[ry],50,0);
            
            rplaces[ry] = rplace;
            ry++;
            }else if(!risValidPlace(rplace)){
                JOptionPane.showMessageDialog(null, "Place already added");
            }
               
        }
        

        
        for(int i = 0;i<ry;i++){
            if(e.getSource()==rdbuttons[i]){
                
                String query = "delete from tobevisitedtable where username = '"+username+"' AND tobevisited = '"+rplaces[i]+"'";
             Data d1 = new Data();
            try {
                d1.s.executeUpdate(query);
            } catch (SQLException ex) {
                
            }
                
                for(int j=0;j<ry;j++){
                    rbuttons[j].setEnabled(false);
                    rbuttons[j].setVisible(false);
                    rdbuttons[j].setEnabled(false);
                    rdbuttons[j].setVisible(false);
                }
                
                for(int j=i;j<ry-1;j++){
                    rbuttons[j]=rbuttons[j+1];
                    rdbuttons[j]=rdbuttons[j+1];
                    rplaces[j]=rplaces[j+1];
                }
                
                rplaces[ry-1]=null;
                ry--;
                for(int j=0;j<ry;j++){
                    rbuttons[j].setBounds(20,x[j] ,238 , 40);
                    rclabel.add(rbuttons[j],50,0);
                    rbuttons[j].setVisible(true);
                    rbuttons[j].setEnabled(true);
                    
                    rdbuttons[j].setBounds(278,x[j] ,40 , 40);
                    rclabel.add(rdbuttons[j],50,0);
                    rdbuttons[j].setVisible(true);
                    rdbuttons[j].setEnabled(true);
                }
            }    
        }
        
       
        
        
        if(e.getSource()==photo){
            
            
            LookAndFeel previousLF = UIManager.getLookAndFeel(); 
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                
                JFileChooser filechooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("files",ImageIO.getReaderFileSuffixes());
                filechooser.setFileFilter(filter);
                filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = filechooser.showSaveDialog(null);
                if(result==JFileChooser.APPROVE_OPTION){
                try {
                    String filename;
                    int count=1;
                    String query = "select * from feed where username = '"+username+"' AND place = '"+fplace+"'";
                    Data d = new Data();
                    ResultSet result1 = d.s.executeQuery(query);
                    while(result1.next()){
                        count++;
                    }
                    filename = username+fplace+count;
                    File postimage = filechooser.getSelectedFile();
                    String extension = postimage.getAbsolutePath().substring(postimage.getAbsolutePath().lastIndexOf('.')+1);
                    filename = filename +"."+ extension;
                    File source = new File(postimage.getAbsolutePath());
                    File destination = new File(filename);
                    
                    BufferedImage img = null;
                    img = ImageIO.read(source);
                    ImageIO.write( img, extension, new File("src/hitchkkers/icons/"+filename));

                    
                    JOptionPane.showMessageDialog(null, "Image uploaded");
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
                

                
            try {
                UIManager.setLookAndFeel(previousLF);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        
       if(e.getSource()==post) {
           String caption = write.getText();
           String location = place.getText();
           String fdate = date.getText();
           if(caption!="Write something about your day"&&location!="Location"&&fdate!="Visiting Date"&&isValidDate(fdate)){
              String query = "insert into feed values('"+username+"','"+caption+"','"+fplace+"','"+location+"','"+fdate+"','"+path+"')";
                    Data d1 = new Data();
            try {
                d1.s.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
           }
           
                    
            try {
                new Dashboard(username,fplace).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.setVisible(false);
       }
        
    }
    
     public static void main(String [] args) throws SQLException{

        
        new Dashboard("Username","Home").setVisible(true);
    }
    
}
