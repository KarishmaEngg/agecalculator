import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Age calculator");

        //to display next message
        JLabel namelabel = new JLabel("Enter your Name");
        JLabel emaillabel = new JLabel("Enter your Email-id");
        JLabel mobileNolabel = new JLabel("Enter your MobilleNo");
        JLabel addresslabel = new JLabel("Enter your Address");
        JLabel bookingId = new JLabel("Booking Id");

        JRadioButton malegender = new JRadioButton("Male");
        JRadioButton femalegender = new JRadioButton("Female");

        //create object to got clear button
        JButton clearbutton = new JButton("Clear");
        JButton submitbutton = new JButton("Submit");

        JTextArea msgarea = new JTextArea(" ");

        JTextField nametf = new JTextField(" ");
        JTextField emailtf = new JTextField(" ");
        JTextField mobilenotf = new JTextField(" ");
        JTextArea addressta = new JTextArea(" ");

        namelabel.setBounds(20, 10, 200, 30);
        emaillabel.setBounds(20, 50, 200, 30);
        mobileNolabel.setBounds(20, 90, 200, 30);
        addresslabel.setBounds(20, 130, 200, 30);
        bookingId.setBounds(50, 170, 100, 30);

        nametf.setBounds(220, 10, 250, 30);
        emailtf.setBounds(220, 50, 250, 30);
        mobilenotf.setBounds(220, 90, 250, 30);
        addressta.setBounds(220, 130, 250, 30);

        clearbutton.setBounds(150, 250, 100, 40);
        submitbutton.setBounds(260, 250, 100, 40);
        msgarea.setBounds(500, 10, 300, 300);
        malegender.setBounds(10, 170, 200, 30);
        femalegender.setBounds(220, 170, 200, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(malegender);
        bg.add(femalegender);


        frame.add(namelabel);
        frame.add(emaillabel);
        frame.add(mobileNolabel);
        frame.add(addresslabel);

        frame.add(nametf);
        frame.add(emailtf);
        frame.add(mobilenotf);
        frame.add(addressta);

        frame.add(clearbutton);
        frame.add(submitbutton);
        frame.add(msgarea);
        frame.add(malegender);
        frame.add(femalegender);

        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(800, 1000);
        frame.setVisible(true);

        clearbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametf.setText("");
                emailtf.setText(" ");
                mobilenotf.setText("");
                addressta.setText(" ");
            }
        });
        submitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add the validation of form cant be empty
                if (nametf.getText().toString().isEmpty() ||
                        emailtf.getText().toString().isEmpty() ||
                        mobilenotf.getText().toString().isEmpty() ||
                        addressta.getText().toString().isEmpty()) {

                        bookingId.setText("Please fill the details");
                    } else{
                        String url = "jdbc:mysql://localhost:3306/agecalculator";

                        //database credentials pass to String
                        String username = "root";
                        String password = "";


                        //make the connection jdbc to mysql
                        try {
                            Connection connection = DriverManager.getConnection(url, username, password);
                            String sql = "insert into custmerdetail(name,email,mobile,address)"
                                    + "values(?,?,?,?)";
                            PreparedStatement preparedStmt = connection.prepareStatement(sql);
                            preparedStmt.setString(1, nametf.getText().toString());
                            preparedStmt.setString(2, emailtf.getText().toString());
                            preparedStmt.setString(3, mobilenotf.getText().toString());
                            preparedStmt.setString(4, addressta.getText().toString());

                            //name,address,city,state,pincode,phoneno,email,arrival-DnT,departure-DnT,NoOfKids,paymentMethod,total

                            preparedStmt.execute();

                            System.out.println("Database is Connected");

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex + "Database is not connected");
                        }

                    }


                    Random random = new Random();
                    int id = random.nextInt(999999);
                    bookingId.setText("Your booking is confirmed and BookingID is " + id);
                }
            });

        }

    }
