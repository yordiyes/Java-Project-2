import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HeadRegistrationPage implements ActionListener {
    Connection conn;
    Statement statement;
    JFrame frame = new JFrame("Head Registration Page");
    JLabel head = new JLabel("Registration Form for Head");
    JLabel name = new JLabel("Your Name:");
    JLabel sex = new JLabel("Your sex:");
    JLabel department = new JLabel("Your Department:");
    JLabel password = new JLabel("Your Password:");

    JPanel panel = new JPanel();
    public JTextField nameField = new JTextField(20);
    JTextField sexField = new JTextField(20);
    JTextField passwordField = new JTextField(20);
    Choice  deptList = new Choice();
    JButton register = new JButton("Register");
    JButton back = new JButton("Back");

    public HeadRegistrationPage(){
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        nameField.setFont(textFieldFont);
        sexField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        name.setFont(labelFont);
        department.setFont(labelFont);
        sex.setFont(labelFont);
        password.setFont(labelFont);

        head.setBounds(30,10,250,25);
        name.setBounds(10,40,100,25);
        nameField.setBounds(110,40,190,25);
        department.setBounds(10,80,140,25);
        deptList.setBounds(150,80,150,25);
        sex.setBounds(10,120,150,25);
        sexField.setBounds(110,120,190,25);
        password.setBounds(10,160,140,25);
        passwordField.setBounds(140,160,160,25);

        back.setBounds(10,200,80,25);
        register.setBounds(200,200,100,25);

        deptList.add("Software Engineering");
        deptList.add("Computer Science");
        deptList.add("Information Technology");
        deptList.add("Information System");

        Dimension buttonSize = new Dimension(100, 25);
        register.setPreferredSize(buttonSize);
        register.setFont(labelFont);
        back.setFont(labelFont);

        register.addActionListener(this);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HeadLoginPage();
            }
        });

        panel.add(head);
        panel.add(name);
        panel.add(nameField);
        panel.add(sex);
        panel.add(sexField);
        panel.add(department);
        panel.add(deptList);
        panel.add(password);
        panel.add(passwordField);
        panel.add(register);
        panel.add(back);


        frame.add(panel);
        frame.setSize(200,330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(320,278);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HeadRegistrationPage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/registration";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        PreparedStatement statement3;
        int status = 0;
        try {
            statement3 = conn.prepareStatement("INSERT INTO department_head (name, sex, department, password) VALUES (?, ?, ?, ?)");
            statement3.setString(1, nameField.getText());
            statement3.setString(2, sexField.getText());
            statement3.setString(3, deptList.getSelectedItem());
            statement3.setString(4, passwordField.getText());
            status = statement3.executeUpdate();
            if (status > 0) {
                System.out.println("A new department head has registered successfully.");
                JOptionPane.showMessageDialog(panel, "A new department head has registered successfully.");
                frame.setVisible(false);
                new HeadLoginPage();
            }
        } catch (SQLException exception) {
            if (exception.getErrorCode() == 1062) {
                // Error code 1062 means duplicate entry
                JOptionPane.showMessageDialog(frame, "Password already exists. Please enter a different password.", "Duplicate Password", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            } else {
                // For other SQL exceptions, print stack trace
                exception.printStackTrace();
            }
        }
    }

}
