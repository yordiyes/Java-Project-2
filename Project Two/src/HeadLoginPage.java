import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HeadLoginPage {
    static Connection conn;
    static Statement statement;

    JFrame frame = new JFrame("Department Head Login In Page");
    JPanel panel = new JPanel();
    JLabel nameLabel = new JLabel("Your Name");
    JLabel passwordLabel = new JLabel("Your Password");

    static JTextField  name = new JTextField( 20);
    static JTextField password = new JTextField( 20);
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JButton clear = new JButton("Clear");
    JButton back = new JButton("Back");


    public HeadLoginPage() {
        panel.setLayout(null);
        panel.setBackground(Color.orange);
        panel.setBackground(Color.lightGray);


        Font labelFont = new Font("Arial", Font.BOLD, 16);
        nameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        name.setFont(textFieldFont);
        password.setFont(textFieldFont);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        loginButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        clear.setFont(buttonFont);
        back.setFont(buttonFont);

        loginButton.setBackground(Color.pink);
        registerButton.setBackground(Color.pink);
        back.setBackground(Color.lightGray);
        clear.setBackground(Color.lightGray);


        nameLabel.setBounds(30,40,100,25);
        name.setBounds(150,40,150,25);
        passwordLabel.setBounds(30,85,130,25);
        password.setBounds(150,85,150,25);
        clear.setBounds(210,120,85,20);
        loginButton.setBounds(30,40,100,25);
        loginButton.setBounds(70,150,100,25);
        registerButton.setBounds(190,150,100,25);
        back.setBounds(10,235,85,20);

        panel.add(nameLabel);
        panel.add(name);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(back);
        panel.add(clear);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HeadRegistrationPage();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HeadLoginPage.name.getText().equals("") && HeadLoginPage.password.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "You Must Enter Your Name And Password");
                }else if(HeadLoginPage.password.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "You Must Enter a Password");
                    new HeadLoginPage();
                }
                else if(HeadLoginPage.name.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "You Must Enter a name");
                }else {
                    frame.setVisible(false);
                    new CourseManipulationPage();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FirstPage();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                password.setText("");
            }
        });
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(540,200);
        frame.setSize(350,290);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HeadLoginPage::new);
    }
}
