import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPage {
    JFrame frame = new JFrame("Welcome Page");
    JPanel panel = new JPanel();
    JLabel welcome = new JLabel("Welcome User");
    JLabel to = new JLabel("Please Select From The Options");
    JButton studentButton = new JButton("Student");
    JButton departmentHeadButton = new JButton("Department Head");


    public FirstPage() {
        panel.setBackground(Color.lightGray);
        panel.setSize(240,300);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        studentButton.setFont(buttonFont);
        departmentHeadButton.setFont(buttonFont);
        welcome.setFont(new Font("Arial", Font.BOLD, 21));
        to.setFont(new Font("Arial", Font.BOLD, 21));

        panel.setLayout(null);

        welcome.setBounds(100,45,150,20);
        to.setBounds(10,70,400,25);
        studentButton.setBounds(10,130,120,35);
        departmentHeadButton.setBounds(155,130,170,35);

        studentButton.setBackground(Color.WHITE);
        departmentHeadButton.setBackground(Color.WHITE);

        studentButton.setForeground(Color.BLACK);
        departmentHeadButton.setForeground(Color.BLACK);

        panel.add(welcome);
        panel.add(to);
        panel.add(studentButton);
        panel.add(departmentHeadButton);


        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StudentLoginPage();
            }
        });

        departmentHeadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HeadLoginPage();
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(540,200);
        frame.setSize(360,320);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstPage::new);
    }
}
