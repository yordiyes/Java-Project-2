import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ViewAllCourses {
    JFrame frame = new JFrame("All Courses");
    JPanel panel = new JPanel();
    JLabel header = new JLabel("All Courses");
    JTextArea area = new JTextArea(12,20);
    JButton exit = new JButton("Exit");
    JButton back = new JButton("back");
    public ViewAllCourses(){
        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        area.setFont(textFieldFont);
        exit.setFont(textFieldFont);
        back.setFont(textFieldFont);
        header.setFont(new Font("Arial", Font.BOLD, 19));
        area.setEditable(false);
        area.setBackground(Color.orange);

        header.setBounds(125,10,200,25);
        area.setBounds(10,40,390,200);
        back.setBounds(10,243,90,20);
        exit.setBounds(310,243,90,20);
        back.setBackground(Color.gray);
        exit.setBackground(Color.gray);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CourseManipulationPage();
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

            preparedStatement = conn.prepareStatement("SELECT * FROM course");


            rs = preparedStatement.executeQuery();

            // Process the result set
            while (rs.next()) {
                String course_name = rs.getString("course_name");
                int credit_hour = rs.getInt("credit_hour");
                String instructor = rs.getString("instructor")    ;
                String course_code = rs.getString("course_code");

                area.append(instructor + "  |  " + course_name + " | " + credit_hour + "Hour"  +" | " + course_code + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        panel.add(area);
        panel.add(header);
        panel.add(exit);
        panel.add(back);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(430,300);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ViewAllCourses();
    }
}
