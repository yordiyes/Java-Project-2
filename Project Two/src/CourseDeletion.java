import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class CourseDeletion implements ActionListener {
    Connection conn;
    Statement statement;
    JFrame frame = new JFrame("Course Deletion page");
    JLabel title = new JLabel("Course Deletion Form");
    JLabel courseName = new JLabel("Course Name:");
    JLabel creditHour = new JLabel("Credit Hour:");

    JPanel panel = new JPanel();
    public JTextField courseNameField = new JTextField(20);
    JTextField creditHourField = new JTextField(20);
    JButton delete = new JButton("Delete");
    JButton back = new JButton("Back");

    public CourseDeletion(){
        panel.setLayout(null);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        courseNameField.setFont(textFieldFont);
        creditHourField.setFont(textFieldFont);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        title.setFont(labelFont);
        courseName.setFont(labelFont);
        creditHour.setFont(labelFont);

        title.setBounds(80,10,250,25);
        courseName.setBounds(10,40,150,25);
        courseNameField.setBounds(120,40,190,25);
        creditHour.setBounds(10,80,100,25);
        creditHourField.setBounds(120,80,190,25);

        back.setBounds(10,140,80,25);
        delete.setBounds(210,140,100,25);

        Dimension buttonSize = new Dimension(100, 25);
        delete.setPreferredSize(buttonSize);
        delete.setFont(labelFont);
        back.setFont(labelFont);

        delete.setBackground(Color.cyan);
        back.setBackground(Color.gray);

        delete.addActionListener(this);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CourseManipulationPage();
            }
        });

        panel.setBackground(Color.lightGray);
        panel.add(title);
        panel.add(courseName);
        panel.add(courseNameField);
        panel.add(creditHour);
        panel.add(creditHourField);
        panel.add(delete);
        panel.add(back);


        frame.add(panel);
        frame.setSize(200,330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(330,220);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CourseDeletion();
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
            statement3 = conn.prepareStatement("DELETE FROM course WHERE course_name = ? and credit_hour = ?");
            statement3.setString(1, courseNameField.getText());
            statement3.setInt(2, Integer.parseInt(creditHourField.getText()));
            status  = statement3.executeUpdate();
            if (status > 0) {
                System.out.println(courseNameField.getText() + "Course Deleted successfully.");
                JOptionPane.showMessageDialog(panel, courseNameField.getText() + " Course Deleted successfully.");
                frame.setVisible(false);
                new CourseManipulationPage();
            }else if (status == 0){
                JOptionPane.showMessageDialog(panel, "Id that already exist In the database");
                frame.setVisible(false);
                new CourseManipulationPage();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
