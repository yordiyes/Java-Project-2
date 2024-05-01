import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentInfo {
    JFrame frame = new JFrame("Student information page");
    JPanel panel = new JPanel();
    JLabel header = new JLabel("Your Information");
    JTextArea area = new JTextArea(12,20);
    JButton exit = new JButton("Exit");
    JButton viewAll = new JButton("All Courses");
    public StudentInfo(){

        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        area.setFont(textFieldFont);
        exit.setFont(textFieldFont);
        viewAll.setFont(textFieldFont);
        header.setFont(new Font("Arial", Font.BOLD, 19));
        area.setEditable(false);
        area.setBackground(Color.orange);

        header.setBounds(65,10,200,25);
        area.setBounds(10,40,260,250);
        exit.setBounds(10,293,90,20);
        viewAll.setBounds(140,293,130,20);

        viewAll.setBackground(Color.cyan);
        exit.setBackground(Color.gray);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StudentViewAllCourses();
            }
        });
        try {
            final String JDBC_URL = "jdbc:mysql://localhost:3306/registration";
            final String USERNAME = "root";
            final String PASSWORD = "";
            try {
                conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                statement = conn.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            statement = conn.createStatement();

            preparedStatement = conn.prepareStatement("SELECT * FROM student_info WHERE name = ? and id = ?");

            preparedStatement.setString(1, StudentLoginPage.name.getText());
            preparedStatement.setString(2, StudentLoginPage.password.getText());


            rs = preparedStatement.executeQuery();

            // Process the result set
            while (rs.next()) {
                String firstName = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String department = rs.getString("department");
                int id = rs.getInt("id");

                area.append("Name: " + firstName + "\n");
                area.append("Age: " + age + "\n");
                area.append("Sex: " + sex + "\n");
                area.append("Department: " + department + "\n");
                area.append("Id No: " + id + "\n\n");
                area.append("Registered for: \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            final String JDBC_URL = "jdbc:mysql://localhost:3306/registration";
            final String USERNAME = "root";
            final String PASSWORD = "";
            try {
                conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                statement = conn.createStatement();
                System.out.println("Connected to the database.");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            statement = conn.createStatement();

            preparedStatement = conn.prepareStatement("SELECT * FROM course");


            rs = preparedStatement.executeQuery();

            // Process the result set
            while (rs.next()) {
                String course_name = rs.getString("course_name");
                area.append(course_name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        panel.add(header);
        panel.add(area);
        panel.add(exit);
        panel.add(viewAll);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(540,200);
        frame.setSize(300,350);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new StudentInfo();
    }
}
