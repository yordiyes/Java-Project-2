import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CourseInfo {
    JLabel header = new JLabel("Your Course Information");
    JTextArea area = new JTextArea(12,20);

    public CourseInfo(){

        JFrame frame = new JFrame("Course Page");
        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

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

            preparedStatement = conn.prepareStatement("SELECT * FROM course WHERE name = ?");

            preparedStatement.setString(1, HeadLoginPage.name.getText());

            rs = preparedStatement.executeQuery();

            // Process the result set
            while (rs.next()) {
                String firstName = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String department = rs.getString("department");

                // Print the data to the console
                System.out.println("First Name: " + firstName);
                System.out.println("Age: " + age);
                System.out.println("Sex: " + sex);
                System.out.println("Department: " + department);

                // Append the data to the text area
                area.append("Name: " + firstName + "\n");
                area.append("Age: " + age + "\n");
                area.append("Sex: " + sex + "\n");
                area.append("Department: " + department + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setLayout(new FlowLayout(1, 20, 20));
        frame.add(header);
        frame.add(area);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new StudentInfo();
    }
}
