package model;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Ticket {
    LinkedHashMap<String, Integer> srcPlaces = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> destPlaces = new LinkedHashMap<String, Integer>();
    protected String dest;
    protected String source;
    protected int numSeats;
    protected int year, month, date;
    protected Calendar departureDate = Calendar.getInstance();

    Ticket() {
        srcPlaces.put("New Delhi", 750);
        srcPlaces.put("Mumbai", 600);
        srcPlaces.put("Chennai", 350);
        srcPlaces.put("Kolkata", 700);
        srcPlaces.put("Bengaluru", 450);
        srcPlaces.put("Pune", 550);

        destPlaces.put("New Delhi", 750);
        destPlaces.put("Mumbai", 600);
        destPlaces.put("Chennai", 350);
        destPlaces.put("Kolkata", 700);
        destPlaces.put("Bengaluru", 450);
        destPlaces.put("Pune", 550);
    }

}

public class Bus_Window extends Ticket implements ActionListener {

    private JFrame busFrame = new JFrame();
    private String sources[] = { "New Delhi", "Mumbai", "Chennai", "Kolkata", "Bengaluru", "Pune" };
    protected JComboBox srcCb = new JComboBox<String>(sources);
    private String destination[] = { "New Delhi", "Mumbai", "Chennai", "Kolkata", "Bengaluru", "Pune" };
    protected JComboBox destCb = new JComboBox<String>(destination);
    private JLabel l1 = new JLabel("Source:");
    private JLabel l2 = new JLabel("Destination:");
    private JLabel l3 = new JLabel("Departure:");
    private JLabel l4 = new JLabel("No. of tickets:");
    private JTextField t1 = new JTextField();
    private JTextField d1 = new JTextField();
    private JTextField d2 = new JTextField();
    private JTextField d3 = new JTextField();
    private JButton p1 = new JButton("Payment");
    // private JButton b1 = new JButton("Show");
    private String custName,emailId;
    private Long phoneNum;
    Bus_Window(){}
    Bus_Window(String custName,String emailId,Long phoneNum) {

        this.custName = custName;
        this.emailId = emailId;
        this.phoneNum =  phoneNum;

        l1.setBounds(30, 30, 80, 20);// source label
        srcCb.setBounds(100, 30, 80, 20);// src combobox
        l2.setBounds(200, 30, 80, 20);// destination label
        destCb.setBounds(300, 30, 80, 20);// destination combobox
        l3.setBounds(30, 300, 80, 20);// Departure Label
        d1.setBounds(30, 350, 80, 20);// date
        d2.setBounds(120, 350, 80, 20);// month
        d3.setBounds(210, 350, 80, 20);// year
        l4.setBounds(200, 300, 80, 20);// no. of tickets
        t1.setBounds(300, 300, 80, 20);// no. of tickets txtbox
        p1.setBounds(300, 400, 100, 40);// payment button


        busFrame.add(srcCb);
        busFrame.add(destCb);
        busFrame.add(l1);
        busFrame.add(l2);
        busFrame.add(l3);
        busFrame.add(l4);
        busFrame.add(p1);
        busFrame.add(t1);
        busFrame.add(d1);
        busFrame.add(d2);
        busFrame.add(d3);

        p1.addActionListener(this);

        busFrame.setSize(500, 500);
        busFrame.setLayout(null);
        busFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p1) {
            assignData();
            calcData();
        }

    }

    void assignData() {
        source = (String) srcCb.getItemAt(srcCb.getSelectedIndex());
        dest = (String) destCb.getItemAt(destCb.getSelectedIndex());
        numSeats = Integer.parseInt(t1.getText());
        date = Integer.parseInt(d1.getText());
        month = Integer.parseInt(d2.getText());
        year = Integer.parseInt(d3.getText());
        departureDate.set(year, month, date);
    }

    void calcAmount(float distance) {
        float cost;
        cost = 0;
        if (source.equals("Mumbai")) {
            float rate = (float) 5.50;
            cost = numSeats * distance * rate;
        } else if (source.equals("New Delhi")) {
            float rate = (float) 5.75;
            cost = numSeats * distance * rate;
        } else if (source.equals("Kolkata")) {
            float rate = (float) 3.75;
            cost = numSeats * distance * rate;
        } else if (source.equals("Chennai")) {
            float rate = (float) 4.25;
            cost = numSeats * distance * rate;
        } else if (source.equals("Bengaluru")) {
            float rate = (float) 4.75;
            cost = numSeats * distance * rate;
        } else if (source.equals("Pune")) {
            float rate = (float) 4.5;
            cost = numSeats * distance * rate;
        } else {
            System.err.println("Invalid Choice");
        }
        displayData(cost);
    }

    void calcData() {
        if (srcPlaces.containsKey(source) && destPlaces.containsKey(dest)) {
            if (!source.equals(dest)) {
                float distance = Math.abs(srcPlaces.get(source) - destPlaces.get(dest));
                calcAmount(distance);
            }
        } else {
            System.err.println("Invalid Choice");
        }

    }

    void displayData(float cost) {
        if(custName!=null)
        {
            System.out.println("Name: "+custName);
            System.out.println("EmailId: "+emailId);
            System.out.println("Contact: "+phoneNum);
        }
        System.out.println("Departure Date: " + departureDate.getTime());
        System.out.println("No. of tickets: " + numSeats);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + dest);
        System.out.println("Total Amount: " + cost);
        createBill(cost);
    }

    void createBill(float cost) {
        try {
            FileWriter fob = new FileWriter("Bill.txt");
            fob.write("\t\t\t Bus Login System\n\n\n");
            fob.write("\t\t\t\t\t Date:"+departureDate.getTime()+"\n\n");
            fob.write("\t\t\t Name: "+custName +"\n");
            fob.write("\t\t\t EmailId: "+emailId+"\n");
            fob.write("\t\t\t Contact: "+phoneNum+"\n");
            fob.write("\t\t\t No. of tickets: " + numSeats +"\n");
            fob.write("\t\t\t Source: " + source+"\n");
            fob.write("\t\t\t Destination: " + dest+"\n");
            fob.write("\t\t\t Amount: " + cost+"\n");
            fob.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
}
class Admin extends Bus_Window
{
    private JFrame adminFrame =  new JFrame();
    private JLabel label = new JLabel("Admin Window");
    private JButton but1 = new JButton("InSource");
    private JButton but2 = new JButton("InDestination");
    private JButton but3 = new JButton("Display");
    private JButton but4 = new JButton("Search");
    private JButton but5 = new JButton("DelSource");
    private JButton but6 = new JButton("DelDestination");
    Admin()
    {
        label.setBounds(10, 10, 150, 20);
        but1.setBounds(30,30, 80, 40);
        but2.setBounds(120,30, 80, 40);
        but3.setBounds(210,30, 80, 40);
        but4.setBounds(300,30, 80, 40);
        but5.setBounds(30,80, 80, 40);
        but6.setBounds(120,80, 80, 40);

        adminFrame.add(label);
        adminFrame.add(but1);
        adminFrame.add(but2);
        adminFrame.add(but3);
        adminFrame.add(but4);
        adminFrame.add(but5);
        adminFrame.add(but6);
        
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this);
        but4.addActionListener(this);
        but5.addActionListener(this);
        but6.addActionListener(this);


        adminFrame.setSize(500, 500);
        adminFrame.setLayout(null);
        adminFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e2) {
        if (e2.getSource() == but1)
        {
            String src=JOptionPane.showInputDialog(adminFrame,"Source");
            String s1=JOptionPane.showInputDialog(adminFrame,"Distance");
            int dist = Integer.parseInt(s1);
            insertSource(src,dist);
        }
        else if(e2.getSource() == but2)
        {
            String des=JOptionPane.showInputDialog(adminFrame,"Destination");
            String s1=JOptionPane.showInputDialog(adminFrame,"Distance");
            int dist = Integer.parseInt(s1);
            insertDestination(des,dist);
        }
        else if(e2.getSource() == but3)
        {
            displayRoutes();
        }
        else if(e2.getSource()== but4)
        {
            String src=JOptionPane.showInputDialog(adminFrame,"Source");
            String des=JOptionPane.showInputDialog(adminFrame,"Destination");
            searchRoute(src,des);
        }
        else if(e2.getSource() == but5)
        {
            String src=JOptionPane.showInputDialog(adminFrame,"Source");
            deleteSource(src);
        }
        else if(e2.getSource() == but6)
        {
            String des=JOptionPane.showInputDialog(adminFrame,"Destination");
            deleteDestination(des);
        }
    }
    void insertSource(String src, Integer dist)
    {
        srcPlaces.put(src,dist);
        srcCb.addItem(src);
        System.out.println("Data Inserted Successfully");
    }
    void insertDestination(String des,Integer dist)
    {
        destPlaces.put(des, dist);
        destCb.addItem(des);
        System.out.println("Data Inserted Successfully");
    }
    void displayRoutes()
    {
        System.out.println("All the available routes are:");
        System.out.println("From:");
        System.out.println(srcPlaces);
        System.out.println("To:");
        System.out.println(destPlaces);
    }
    void searchRoute(String src,String des)
    {
        if(srcPlaces.containsKey(src) && destPlaces.containsKey(des))
        {
            System.out.println("Your Route exists");
        }
        else
        {
            System.out.println("Route Does not exist in System.Sorry!!!");
        }
    }
    void deleteSource(String src)
    {
        if(srcPlaces.containsKey(src))
        {
            srcPlaces.remove(src);
            srcCb.removeItem(src);
        }
        else 
        {
            System.out.println("No such Source exists");
        }
    }
    void deleteDestination(String des)
    {
        if(destPlaces.containsKey(des))
        {
            destPlaces.remove(des);
            destCb.removeItem(des);
        }
        else 
        {
            System.out.println("No such Destination exists");
        }
    }
    void confirm()
    {
        System.out.println("In the Admin System");
    }
}