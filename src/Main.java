import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    //ArrayLists to store multiple submissions
    private static ArrayList<String> jobSubmissions = new ArrayList<>();
    private static ArrayList<String> carOwnerSubmissions = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vehicular Cloud Console");
        frame.setSize(1280, 720);
        frame.setLayout(new GridLayout(2, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Job Submitter Panel Setup and Information

        JPanel informationInputJobSubmitterPanel = new JPanel();
        informationInputJobSubmitterPanel.setLayout(new GridLayout(6, 2));

        JLabel clientIDLabel = new JLabel("Client ID: ");
        JTextField clientIDTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(clientIDLabel);
        informationInputJobSubmitterPanel.add(clientIDTextField);

        JLabel jobDurationLabel = new JLabel("Job Duration: ");
        JTextField jobDurationTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(jobDurationLabel);
        informationInputJobSubmitterPanel.add(jobDurationTextField );

        JLabel jobDeadlineLabel = new JLabel("Job Deadline: ");
        JTextField jobDeadlineTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(jobDeadlineLabel);
        informationInputJobSubmitterPanel.add(jobDeadlineTextField);

        // Job Submission Button

        JButton submitButton = new JButton("Submit");
        informationInputJobSubmitterPanel.add(new JLabel());
        informationInputJobSubmitterPanel.add(submitButton);


        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientID = clientIDTextField.getText();
                String jobDuration = jobDurationTextField.getText();
                String jobDeadline = jobDeadlineTextField.getText();

                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                try(FileWriter writer = new FileWriter("job_submitter_data.txt", true)){
                    writer.write("Client ID: " + clientID + "\n");
                    writer.write("Job Duration: " + jobDuration + "\n");
                    writer.write("Job Deadline: " + jobDeadline + "\n");
                    writer.write("Timestamp " + timestamp + "\n");
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }

                //Store submission in list
                jobSubmissions.add("Client ID: " + clientID + ", Job Duration: " + jobDuration + ", Job Deadline: " + jobDeadline + ", Timestamp: " + timestamp);


                clientIDTextField.setText("");
                jobDurationTextField.setText("");
                jobDeadlineTextField.setText("");

            }
        });

        //Button to view job submissions
        JButton viewJobsButton = new JButton("View Job Submissions");
        informationInputJobSubmitterPanel.add(new JLabel());
        informationInputJobSubmitterPanel.add(viewJobsButton);

        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, String.join("\n", jobSubmissions), "Job Submissions", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(informationInputJobSubmitterPanel);
        informationInputJobSubmitterPanel.setVisible(false);

        // Car Owner Panel Setup and Information

        JPanel informationInputCarOwnerPanel = new JPanel();

        informationInputCarOwnerPanel.setLayout(new GridLayout(6, 2));

        JLabel ownerIDLabel = new JLabel("Owner ID: ");
        JTextField ownerIDTextField = new JTextField(30);

        informationInputCarOwnerPanel.add(ownerIDLabel);
        informationInputCarOwnerPanel.add(ownerIDTextField);

        JLabel vinLabel = new JLabel("VIN Info: ");
        JTextField vinTextField = new JTextField(30);

        informationInputCarOwnerPanel.add(vinLabel);
        informationInputCarOwnerPanel.add(vinTextField);

        JLabel residencyLabel = new JLabel("Residency Time: ");
        JTextField residencyTextField = new JTextField(30);

        informationInputCarOwnerPanel.add(residencyLabel);
        informationInputCarOwnerPanel.add(residencyTextField);

        // Car Owner Submission Button

        JButton carOwnerSubmitButton = new JButton("Submit");
        informationInputCarOwnerPanel.add(new JLabel());
        informationInputCarOwnerPanel.add(carOwnerSubmitButton);


        carOwnerSubmitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ownerID = ownerIDTextField.getText();
                String vinInfo = vinTextField.getText();
                String residencyTime = residencyTextField.getText();

                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                try(FileWriter writer = new FileWriter("car_owner_data.txt",true)){
                    writer.write("Owner ID: " + ownerID + "\n");
                    writer.write("Vin Info: " + vinInfo + "\n");
                    writer.write("Residency Time: " + residencyTime + "\n");
                    writer.write("Timestamp " + timestamp + "\n");
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }

                //Store submission in list
                carOwnerSubmissions.add("Owner ID: " + ownerID + ", VIN Info: " + vinInfo + ", Residency Time: " + residencyTime + ", Timestamp: " + timestamp);

                ownerIDTextField.setText("");
                vinTextField.setText("");
                residencyTextField.setText("");
            }

        });

        //Button to view car owner submissions
        JButton viewOwnersButton = new JButton("View Car Owner Submissions");
        informationInputCarOwnerPanel.add(new JLabel());
        informationInputCarOwnerPanel.add(viewOwnersButton);

        viewOwnersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, String.join("\n", carOwnerSubmissions), "Car Owner Submissions", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(informationInputCarOwnerPanel);


        informationInputCarOwnerPanel.setVisible(false);

        // Part B

        // Part A

        JPanel currentSelectionPanel = new JPanel();

        JLabel currentSelectionButton = new JLabel("");

        //usernames and passwords
        ArrayList<Client> clientArray = new ArrayList<>();
        clientArray.add(new Client("user1","password",00001,"John Doe", 0));
        ArrayList<Owner> ownerArray = new ArrayList<>();
        ownerArray.add(new Owner("user2","password",00002,"Jane Doe",
                0, new Car("Atlas Cross Sport","Volkswagon", 2024,"1HGBH41JXMN109186","MM52SAM","gas")));

        JLabel userLabel = new JLabel("username: ");
        JTextField userTextField = new JTextField(30);

        currentSelectionPanel.add(userLabel);
        currentSelectionPanel.add(userTextField);

        JLabel passwordLabel = new JLabel("password: ");
        JPasswordField passwordTextField = new JPasswordField(30);

        currentSelectionPanel.add(passwordLabel);
        currentSelectionPanel.add(passwordTextField);

        currentSelectionPanel.add(currentSelectionButton);

        final int[] userArrayPosition = {0};

        //job submitter login
        JPanel clientJobSubmitterPanel = new JPanel();

        JButton clientJobSubmitterButton = new JButton("Job Submitter Login");
        currentSelectionPanel.add(clientJobSubmitterButton);

        clientJobSubmitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                String username = userTextField.getText();
                String password = passwordTextField.getText();
                for (int i = 0; i < clientArray.size(); i++) {
                    if (username.equals(clientArray.get(i).getUsername()) && password.equals(clientArray.get(i).getPassword())) {
                        currentSelectionButton.setText("Job Submitter");
                        currentSelectionPanel.setVisible(false);
                        informationInputJobSubmitterPanel.setVisible(true);
                        userArrayPosition[0] = i;
                    }
                }
            }
        });

        //car owner login

        JButton clientCarOwnerButton = new JButton("Car Owner Login");
        currentSelectionPanel.add(clientCarOwnerButton);

        clientCarOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                String username = userTextField.getText();
                String password = passwordTextField.getText();
                for (int i = 0; i < ownerArray.size(); i++) {
                    if (username.equals(ownerArray.get(i).getUsername()) && password.equals(ownerArray.get(i).getPassword())) {
                        currentSelectionButton.setText("Car Owner");
                        currentSelectionPanel.setVisible(false);
                        informationInputCarOwnerPanel.setVisible(true);
                        userArrayPosition[0] = i;
                    }
                }
            }
        });

        //client profile page
        JPanel clientProfilePanel = new JPanel();
        clientProfilePanel.setLayout(new GridLayout(4,2));

        JLabel clientNameLabel = new JLabel("Name: ");
        JLabel clientName = new JLabel(clientArray.get(userArrayPosition[0]).getFullName());

        clientProfilePanel.add(clientNameLabel);
        clientProfilePanel.add(clientName);

        JLabel clientUsernameLabel = new JLabel("Username: ");
        JLabel clientUsername = new JLabel(clientArray.get(userArrayPosition[0]).getUsername());

        clientProfilePanel.add(clientUsernameLabel);
        clientProfilePanel.add(clientUsername);

        JLabel clientProfileIDLabel = new JLabel("ID: ");
        JLabel clientProfileID = new JLabel(String.valueOf(clientArray.get(userArrayPosition[0]).getID()));

        clientProfilePanel.add(clientProfileIDLabel);
        clientProfilePanel.add(clientProfileID);

        JButton clientProfileButton = new JButton("Profile");
        informationInputJobSubmitterPanel.add(clientProfileButton);

        clientProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientProfilePanel.setVisible(true);
                informationInputJobSubmitterPanel.setVisible(false);
            }
        });
        clientProfilePanel.setVisible(false);

        //back to job submission page
        JButton returnToJobPage = new JButton("Home");
        clientProfilePanel.add(returnToJobPage);

        returnToJobPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientProfilePanel.setVisible(false);
                informationInputJobSubmitterPanel.setVisible(true);
            }
        });

        //car owner profile page
        JPanel ownerProfilePanel = new JPanel();
        ownerProfilePanel.setLayout(new GridLayout(4,2));

        JLabel ownerNameLabel = new JLabel("Name: ");
        JLabel ownerName = new JLabel(ownerArray.get(userArrayPosition[0]).getFullName());

        ownerProfilePanel.add(ownerNameLabel);
        ownerProfilePanel.add(ownerName);

        JLabel ownerUsernameLabel = new JLabel("Username: ");
        JLabel ownerUsername = new JLabel(ownerArray.get(userArrayPosition[0]).getUsername());

        ownerProfilePanel.add(ownerUsernameLabel);
        ownerProfilePanel.add(ownerUsername);

        JLabel ownerProfileIDLabel = new JLabel("ID: ");
        JLabel ownerProfileID = new JLabel(String.valueOf(ownerArray.get(userArrayPosition[0]).getID()));

        ownerProfilePanel.add(ownerProfileIDLabel);
        ownerProfilePanel.add(ownerProfileID);

        JButton ownerProfileButton = new JButton("Profile");
        informationInputCarOwnerPanel.add(ownerProfileButton);

        ownerProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerProfilePanel.setVisible(true);
                informationInputCarOwnerPanel.setVisible(false);
            }
        });
        ownerProfilePanel.setVisible(false);

        //back to car owner page
        JButton returnToCarPage = new JButton("Home");
        ownerProfilePanel.add(returnToCarPage);

        returnToCarPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerProfilePanel.setVisible(false);
                informationInputCarOwnerPanel.setVisible(true);
            }
        });

        //job submitter log out button
        JButton jobSubmitterLogoutButton = new JButton("Log Out");
        informationInputJobSubmitterPanel.add(jobSubmitterLogoutButton);

        jobSubmitterLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSelectionPanel.setVisible(true);
                informationInputJobSubmitterPanel.setVisible(false);
                currentSelectionButton.setText("");
            }
        });

        //car owner log out button
        JButton carOwnerLogoutButton = new JButton("Log Out");
        informationInputCarOwnerPanel.add(carOwnerLogoutButton);

        carOwnerLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSelectionPanel.setVisible(true);
                informationInputCarOwnerPanel.setVisible(false);
                currentSelectionButton.setText("");
            }
        });

        frame.add(clientJobSubmitterPanel);
        frame.add(currentSelectionPanel);

        // Part A

        // Part B

        frame.add(informationInputJobSubmitterPanel);
        frame.add(informationInputCarOwnerPanel);
        frame.add(clientProfilePanel);
        frame.add(ownerProfilePanel);

        // Part B
        frame.setVisible(true);

        System.out.println("Hello world!");
    }
}
