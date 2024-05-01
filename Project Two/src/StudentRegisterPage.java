import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentRegisterPage implements ActionListener {
    Connection conn;
    Statement statement;
    JFrame frame = new JFrame("Student Registration Page");
    JLabel forStudents = new JLabel("Student Registration Form");
    JLabel name = new JLabel("Your Name:");
    JLabel department = new JLabel("Your Department:");
    JLabel age = new JLabel("Your Age:");
    JLabel sex = new JLabel("Your sex:");
    JLabel id = new JLabel("Your Id No:");

    JPanel panel = new JPanel();
    public JTextField nameField = new JTextField(20);
    JTextField ageField = new JTextField(20);
    JTextField sexField = new JTextField(20);
    JTextField idField = new JTextField(20);
    Choice  deptList = new Choice();
    JButton register = new JButton("Register");
    JButton back = new JButton("Back");

    public StudentRegisterPage(){
        panel.setLayout(null);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        nameField.setFont(textFieldFont);
        sexField.setFont(textFieldFont);
        ageField.setFont(textFieldFont);
        idField.setFont(textFieldFont);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        forStudents.setFont(labelFont);
        name.setFont(labelFont);
        age.setFont(labelFont);
        department.setFont(labelFont);
        sex.setFont(labelFont);
        id.setFont(labelFont);

        deptList.add("Software Engineering");
        deptList.add("Computer Science");
        deptList.add("Information Technology");
        deptList.add("Information System");

        forStudents.setBounds(30,10,250,25);
        name.setBounds(10,40,100,25);
        nameField.setBounds(110,40,190,25);
        age.setBounds(10,80,100,25);
        ageField.setBounds(110,80,190,25);
        department.setBounds(10,120,140,25);
        deptList.setBounds(150,120,150,25);
        sex.setBounds(10,160,100,25);
        sexField.setBounds(110,160,190,25);
        id.setBounds(10,200,100,25);
        idField.setBounds(110,200,190,25);

        back.setBounds(10,250,80,25);
        register.setBounds(200,250,100,25);

        Dimension buttonSize = new Dimension(100, 25);
        register.setPreferredSize(buttonSize);
        register.setFont(labelFont);
        back.setFont(labelFont);

        register.setBackground(Color.cyan);
        back.setBackground(Color.gray);

        register.addActionListener(this);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StudentLoginPage();
            }
        });

        panel.setBackground(Color.lightGray);
        panel.add(forStudents);
        panel.add(name);
        panel.add(nameField);
        panel.add(age);
        panel.add(ageField);
        panel.add(sex);
        panel.add(sexField);
        panel.add(department);
        panel.add(deptList);
        panel.add(id);
        panel.add(idField);
        panel.add(register);
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
        new StudentRegisterPage();
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
            return;
        }

        PreparedStatement statement3;
        try {
            statement3 = conn.prepareStatement("INSERT INTO student_info (name, age, sex, department, id) VALUES (?, ?, ?, ?, ?)");
            statement3.setString(1, nameField.getText());
            statement3.setInt(2, Integer.parseInt(ageField.getText()));
            statement3.setString(3, sexField.getText());
            statement3.setString(4, deptList.getSelectedItem());
            statement3.setInt(5, Integer.parseInt(idField.getText()));

            int status = statement3.executeUpdate();

            if (status > 0) {
                System.out.println("A new student has registered successfully.");
                JOptionPane.showMessageDialog(panel, "A new student has registered successfully.");
                frame.setVisible(false);
                new StudentLoginPage();
            }
        } catch (SQLException exception) {
            if (exception.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(panel, "The ID you entered already exists in the database. Please enter a different ID.");
                idField.setText("");
            } else {
                exception.printStackTrace();
            }
        }
    }
}
