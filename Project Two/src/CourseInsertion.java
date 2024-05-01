import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CourseInsertion implements ActionListener {
    Connection conn;
    Statement statement;
    JFrame frame = new JFrame("Course Insertion page");
    JLabel title = new JLabel("Course Form");
    JLabel courseName = new JLabel("Course Name:");
    JLabel creditHour = new JLabel("Credit Hour:");
    JLabel instructor = new JLabel("Instructor Name:");
    JLabel courseCode = new JLabel("Course Code:");

    JPanel panel = new JPanel();
    public JTextField courseNameField = new JTextField(20);
    JTextField creditHourField = new JTextField(20);
    JTextField instructorField = new JTextField(20);
    JTextField courseCodeField = new JTextField(20);
    JButton insert = new JButton("Insert");
    JButton back = new JButton("Back");

    public CourseInsertion(){
        panel.setLayout(null);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        courseNameField.setFont(textFieldFont);
        creditHourField.setFont(textFieldFont);
        instructorField.setFont(textFieldFont);
        courseCodeField.setFont(textFieldFont);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        title.setFont(labelFont);
        courseName.setFont(labelFont);
        creditHour.setFont(labelFont);
        courseCode.setFont(labelFont);
        instructor.setFont(labelFont);

        title.setBounds(80,10,250,25);
        courseName.setBounds(10,40,150,25);
        courseNameField.setBounds(120,40,190,25);
        creditHour.setBounds(10,80,100,25);
        creditHourField.setBounds(120,80,190,25);
        instructor.setBounds(10,120,140,25);
        instructorField.setBounds(150,120,150,25);
        courseCode.setBounds(10,160,150,25);
        courseCodeField.setBounds(120,160,190,25);

        back.setBounds(10,250,80,25);
        insert.setBounds(200,250,100,25);

        Dimension buttonSize = new Dimension(100, 25);
        insert.setPreferredSize(buttonSize);
        insert.setFont(labelFont);
        back.setFont(labelFont);

        insert.setBackground(Color.cyan);
        back.setBackground(Color.gray);

        insert.addActionListener(this);
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
        panel.add(instructor);
        panel.add(instructorField);
        panel.add(courseCode);
        panel.add(courseCodeField);
        panel.add(insert);
        panel.add(back);


        frame.add(panel);
        frame.setSize(200,330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(330,320);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CourseInsertion();
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
            statement3 = conn.prepareStatement("INSERT INTO course (course_name, credit_hour, instructor, course_code) VALUES (?, ?, ?,?)");
            statement3.setString(1, courseNameField.getText());
            statement3.setInt(2, Integer.parseInt(creditHourField.getText()));
            statement3.setString(3, instructorField.getText());
            statement3.setString(4, courseCodeField.getText());
            status  = statement3.executeUpdate();
            if (status > 0) {
                System.out.println("A new Course added successfully.");
                JOptionPane.showMessageDialog(panel, courseNameField.getText() + " Course Inserted successfully.");
                frame.setVisible(false);
                new CourseManipulationPage();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
