import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CourseManipulationPage {
    JLabel header = new JLabel("Choose The Operation You want ");
    JButton viewAll = new JButton("View Courses");
    JButton insert = new JButton("Insert Course");
    JButton update = new JButton("Update Course");
    JButton delete = new JButton("Delete Course");
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back");

    public CourseManipulationPage(){

        JFrame frame = new JFrame("Course Manipulation page");
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        header.setFont(textFieldFont);
        update.setFont(textFieldFont);
        insert.setFont(textFieldFont);
        delete.setFont(textFieldFont);
        viewAll.setFont(textFieldFont);
        exit.setFont(textFieldFont);
        back.setFont(textFieldFont);

        update.setBackground(Color.pink);
        insert.setBackground(Color.pink);
        delete.setBackground(Color.pink);
        viewAll.setBackground(Color.pink);
        exit.setBackground(Color.lightGray);
        back.setBackground(Color.lightGray);

        header.setBounds(15, 10 , 300, 25);
        update.setBounds(60, 50 , 150, 25);
        insert.setBounds(60, 80 , 150, 25);
        delete.setBounds(60, 110 , 150, 25);
        viewAll.setBounds(60, 140 , 150, 25);
        exit.setBounds(210, 210 , 69, 25);
        back.setBounds(10, 210 , 69, 25);

        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ViewAllCourses();
            }
        });
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CourseInsertion();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CourseDeletion();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new CourseUpdate();
            }
        });

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
                new HeadLoginPage();
            }
        });
        panel.setBackground(Color.gray);
        panel.add(header);
        panel.add(viewAll);
        panel.add(insert);
        panel.add(delete);
        panel.add(update);
        panel.add(exit);
        panel.add(back);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(540,200);
        frame.setSize(300,280);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new CourseManipulationPage();
    }
}
