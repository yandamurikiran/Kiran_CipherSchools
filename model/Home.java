 /*
WAP in C++/Java to implement Bus Reservation System which should consist of following operations:
1.Insert 
2.Display
3.Search
4.Fares
*/
package model;
import javax.swing.*;
import java.util.regex.*;
import java.awt.event.*;
class User_Window implements ActionListener
{
    private String custName;
    private String custEmailId;
    private Long phoneNum;
    private JFrame userFrame = new JFrame();
    private JLabel l1 =  new JLabel("Name");
    private JLabel l2 =  new JLabel("Email Id");  
    private JLabel l3 =  new JLabel("Contact No.");
    private JTextField tf1 = new JTextField();
    private JTextField tf2 = new JTextField();
    private JTextField tf3 = new JTextField();
    private JButton b1 =  new JButton("Submit");
    User_Window()
    {
        l1.setBounds(30, 30, 80, 20);
        tf1.setBounds(120, 30, 100, 20);
        l2.setBounds(30, 60, 80, 20);
        tf2.setBounds(120, 60, 100, 20);
        l3.setBounds(30, 90, 80, 20);
        tf3.setBounds(120, 90, 100, 20);
        b1.setBounds(150, 150, 80, 40);

        b1.addActionListener(this);
        
        userFrame.add(l1);
        userFrame.add(tf1);
        userFrame.add(l2);
        userFrame.add(tf2);
        userFrame.add(l3);
        userFrame.add(tf3);
        userFrame.add(b1);


        userFrame.setSize(300,300);  
        userFrame.setLayout(null);  
        userFrame.setVisible(true); 
    }
    public void actionPerformed(ActionEvent e)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";   //email validation
        String regexStr = "^[0-9]{10}$";    //phone number validation
        Pattern pat = Pattern.compile(emailRegex);
        if(e.getSource()==b1)
        {
            custName = tf1.getText();
            custEmailId = tf2.getText();
            phoneNum = Long.parseLong(tf3.getText());
            if (custEmailId == null ||custName == null ||phoneNum == null) 
            {
                JOptionPane.showMessageDialog(userFrame,"Some field is empty! Please Try Again.","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else if(!pat.matcher(custEmailId).matches())
            {
                JOptionPane.showMessageDialog(userFrame,"Invalid EmailId! Please Try Again.","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else if(!tf3.getText().matches(regexStr))
            {
                JOptionPane.showMessageDialog(userFrame,"Invalid Phone Number! Please Try Again.","Alert",JOptionPane.WARNING_MESSAGE);
            } 
            else if(custName.equals("admin007") && custEmailId.equals("admin007@gmail.com"))
            {
                Admin ob = new Admin();
                ob.confirm();
            }    
            else
            {
                Bus_Window ob = new Bus_Window(custName,custEmailId,phoneNum);
            }
        }   
    }
}

public class Home implements ActionListener
{
    JFrame homeFrame = new JFrame();
    JLabel l1 =  new JLabel("Welcome to Bus Login System");
    JButton b1 =  new JButton("Book Tickets");
    public Home()
    {

        l1.setBounds(30, 30, 200, 30);
        b1.setBounds(150, 150, 80, 40);

        homeFrame.add(l1);
        homeFrame.add(b1);

        b1.addActionListener(this);

        homeFrame.setSize(300,300);  
        homeFrame.setLayout(null);  
        homeFrame.setVisible(true); 
    }
    public void actionPerformed(ActionEvent e1)
    {
       User_Window u1= new User_Window();
    }    
}