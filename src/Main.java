import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    //ArrayLists to store multiple submissions
    private static ArrayList<Job> jobSubmissions = new ArrayList<>();
    private static ArrayList<String> carOwnerSubmissions = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vehicular Cloud Console");
        frame.setSize(480, 600);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame cloudFrame = new JFrame("VC Cloud Controller");
        cloudFrame.setSize(480,600);
        cloudFrame.setLayout(new GridBagLayout());
        cloudFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        // Job Submitter Panel Setup and Information
        List<Integer> tempJobClientIDs = new ArrayList<>();
        List<Integer> jobClientIDs = new ArrayList<>();
        List<Integer> tempJobDurations = new ArrayList<>();
        List<Integer> jobDurations = new ArrayList<>();

        JLabel jobSubmitterMessageLabel = new JLabel("Welcome Job Submitter!", SwingConstants.CENTER);
        
                            
        JPanel informationInputJobSubmitterPanel = new JPanel();
        informationInputJobSubmitterPanel.setLayout(new GridLayout(10, 2));
             
        informationInputJobSubmitterPanel.add(jobSubmitterMessageLabel);
        
        informationInputJobSubmitterPanel.add(new JLabel(""));

        JLabel clientIDLabel = new JLabel("Client ID: ");
        JTextField clientIDTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(clientIDLabel);
        informationInputJobSubmitterPanel.add(clientIDTextField);

        JLabel jobDurationLabel = new JLabel("Job Duration (hrs): ");
        JTextField jobDurationTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(jobDurationLabel);
        informationInputJobSubmitterPanel.add(jobDurationTextField );

        JLabel jobDeadlineLabel = new JLabel("Job Deadline (hrs): ");
        JTextField jobDeadlineTextField = new JTextField(30);

        informationInputJobSubmitterPanel.add(jobDeadlineLabel);
        informationInputJobSubmitterPanel.add(jobDeadlineTextField);
        
             
        // Job Submission Button

        JButton submitButton = new JButton("Submit");
        informationInputJobSubmitterPanel.add(new JLabel());
        informationInputJobSubmitterPanel.add(submitButton);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientID = clientIDTextField.getText();
                String jobDuration = jobDurationTextField.getText();
                String jobDeadline = jobDeadlineTextField.getText();

                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                int clientIDInt = Integer.parseInt(clientID);
                tempJobClientIDs.add(clientIDInt);

                int jobDurationInt = Integer.parseInt(jobDuration);
                tempJobDurations.add(jobDurationInt);

                // Create a job submission string and add it to the temporary jobSubmissions list
                Job jobSubmission = new Job(clientID, jobDuration, jobDeadline, timestamp);
                jobSubmissions.add(jobSubmission);

                // Clear the input fields after submission
                clientIDTextField.setText("");
                jobDurationTextField.setText("");
                jobDeadlineTextField.setText("");

                try {
                    ClientSocketManager clientConnection = new ClientSocketManager(jobDurationInt);
                    clientConnection.writeJob("Client ID: " + clientID + ", Job Duration (hrs): " + jobDuration + ", Job Deadline (hrs): " + jobDeadline + ", Timestamp: " + timestamp);
                    clientConnection.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Button to view pending job submissions
        JButton viewJobsButton = new JButton("Pending Job Submissions");
        informationInputJobSubmitterPanel.add(new JLabel());
        informationInputJobSubmitterPanel.add(viewJobsButton);

        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show job submissions in a dialog
                if (jobSubmissions.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No pending job submissions.", "Pending Job Submissions", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, String.join("\n", jobSubmissions.toString()), "Pending Job Submissions", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        informationInputJobSubmitterPanel.setVisible(false);

        // Car Owner Panel Setup and Information
        List<Integer> tempCarOwnerIDs = new ArrayList<>();
        List<Integer> carOwnerIDs = new ArrayList<>();

        JPanel informationInputCarOwnerPanel = new JPanel();
        informationInputCarOwnerPanel.setLayout(new GridLayout(10, 2));

        JLabel carOwnerMessageLabel = new JLabel("Welcome Car Owner!", SwingConstants.CENTER);
        
        informationInputCarOwnerPanel.add(carOwnerMessageLabel);

        informationInputCarOwnerPanel.add(new JLabel(""));
       
        JLabel ownerIDLabel = new JLabel("Owner ID: ");
        JTextField ownerIDTextField = new JTextField(25);

        informationInputCarOwnerPanel.add(ownerIDLabel);
        informationInputCarOwnerPanel.add(ownerIDTextField);

        JLabel vinLabel = new JLabel("VIN Info: ");
        JTextField vinTextField = new JTextField(30);

        informationInputCarOwnerPanel.add(vinLabel);
        informationInputCarOwnerPanel.add(vinTextField);

        JLabel residencyLabel = new JLabel("Residency Time (hrs): ");
        JTextField residencyTextField = new JTextField(30);

        informationInputCarOwnerPanel.add(residencyLabel);
        informationInputCarOwnerPanel.add(residencyTextField);

        // Car Owner Submission Button

        JButton carOwnerSubmitButton = new JButton("Submit");
        informationInputCarOwnerPanel.add(new JLabel());
        informationInputCarOwnerPanel.add(carOwnerSubmitButton);


        carOwnerSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ownerID = ownerIDTextField.getText();
                String vinInfo = vinTextField.getText();
                String residencyTime = residencyTextField.getText();

                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                int ownerIDint = Integer.parseInt(ownerID);
                tempCarOwnerIDs.add(ownerIDint);
                // Create a car submission string and add it to the temporary carOwnerSubmissions list
                String carSubmission = "Owner ID: " + ownerID + ", VIN Info: " + vinInfo + ", Residency Time (hrs): " + residencyTime + ", Timestamp: " + timestamp;
                carOwnerSubmissions.add(carSubmission);

                // Clear the input fields after submission
                ownerIDTextField.setText("");
                vinTextField.setText("");
                residencyTextField.setText("");

                try {
                    ClientSocketManager carOwnerConnection = new ClientSocketManager(0);
                    carOwnerConnection.writeCar(carSubmission);
                    carOwnerConnection.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Button to view car owner submissions
        JButton viewOwnersButton = new JButton("Pending Car Owner Submissions");
        informationInputCarOwnerPanel.add(new JLabel());
        informationInputCarOwnerPanel.add(viewOwnersButton);

        viewOwnersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carOwnerSubmissions.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No pending car owner submissions.", "Pending Car Owner Submissions", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, String.join("\n", carOwnerSubmissions), "Pending Car Owner Submissions", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        informationInputCarOwnerPanel.setVisible(false);
        
        //Back Button
        JButton backButton = new JButton("Back");

        //cloud controller panel
        JPanel cloudControllerPanel = new JPanel(new GridLayout(10,1));
        cloudControllerPanel.setVisible(false);
        
        JLabel cloudControllerMessageLabel = new JLabel("Welcome Cloud Controller!", SwingConstants.CENTER);
        cloudControllerPanel.add(cloudControllerMessageLabel);

        //Calculate Job Completion Times
        JButton calculateCompletionButton = new JButton("Calculate Job Completion Time");
        cloudControllerPanel.add(calculateCompletionButton);

        calculateCompletionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> completionTimes = new ArrayList<>();
                int totalDuration = 0;

                //Calculate the completion time for each job in FIFO order
                for (int duration : jobDurations) {
                    totalDuration += duration;
                    completionTimes.add(totalDuration);
                }

                //Format and display the completion times
                String message = "Client ID(s): " + jobClientIDs.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "\n" +
                        "Job Completion Times (hrs): " + completionTimes.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
                JOptionPane.showMessageDialog(cloudFrame, message);
            }
        });

        // Calculate Total Job Duration
        JButton calculateButton = new JButton("Total Job Duration");
        cloudControllerPanel.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalDuration = jobDurations.stream().mapToInt(Integer::intValue).sum();
                //Convert totalDuration to hours and format the message
                String message = String.format("Client ID(s): " + jobClientIDs.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "\n" +
                        "Total Job Duration: %d hr%s", totalDuration, totalDuration == 1 ? "" : "s");
                JOptionPane.showMessageDialog(cloudFrame, message);
            }
        });

        // log in panel
        JPanel logInPanel = new JPanel(new GridLayout(4,2));

        JLabel currentSelectionButton = new JLabel("");
        
        logInPanel.setVisible(false);

        //usernames and passwords
        ArrayList<Client> clientArray = new ArrayList<>();
        clientArray.add(new Client("user1","password",00001,"John Doe", 0.00));
        ArrayList<Owner> ownerArray = new ArrayList<>();
        ownerArray.add(new Owner("user2","password",00002,"Jane Doe",0.00,
                new Car("Atlas Cross Sport","Volkswagon", 2024,"1HGBH41JXMN109186","MM52SAM","gas", true)));

        JLabel userLabel = new JLabel("username: ");
        JTextField userTextField = new JTextField(30);

        logInPanel.add(userLabel);
        logInPanel.add(userTextField);

        JLabel passwordLabel = new JLabel("password: ");
        JPasswordField passwordTextField = new JPasswordField(30);

        logInPanel.add(passwordLabel);
        logInPanel.add(passwordTextField);
        
        backButton.setVisible(true);

        int[] userArrayPosition = new int[1];

        //job submitter login
        JButton clientJobSubmitterLoginButton = new JButton("Job Submitter Login");
        logInPanel.add(clientJobSubmitterLoginButton);

        clientJobSubmitterLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                String username = userTextField.getText();
                String password = passwordTextField.getText();
                
                try {
                    // Attempt to retrieve the Client data from the database
                    Client client = MySQLManager.getClient(username);

                    if (client != null && client.getPassword().equals(password)) {
                        currentSelectionButton.setText("Job Submitter");
                        logInPanel.setVisible(false);
                        informationInputJobSubmitterPanel.setVisible(true);
                        userArrayPosition[0] = clientArray.indexOf(client); // Optional: Save the client position if needed
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving data from database.");
                }
            }
        });	   			

        //car owner login
        JButton clientCarOwnerLoginButton = new JButton("Car Owner Login");
        logInPanel.add(clientCarOwnerLoginButton);

        clientCarOwnerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                String username = userTextField.getText();
                String password = new String(passwordTextField.getPassword());

                try {
                    // Attempt to retrieve the Owner data from the database
                    Owner owner = MySQLManager.getOwner(username);

                    if (owner != null && owner.getPassword().equals(password)) {
                        currentSelectionButton.setText("Car Owner");
                        logInPanel.setVisible(false);
                        informationInputCarOwnerPanel.setVisible(true);
                        userArrayPosition[0] = ownerArray.indexOf(owner); // Optional: Save the owner position if needed
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving data from database.");
                }
            }
        });
      //cloud controller log in
        JButton clientCloudControllerLoginButton = new JButton("Cloud Controller Login");
        logInPanel.add(clientCloudControllerLoginButton, BorderLayout.CENTER);

        clientCloudControllerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                String username = userTextField.getText();
                String password = passwordTextField.getText();
                if(username.equals("user") && password.equals("password")){
                    currentSelectionButton.setText("Cloud Controller");
                    cloudControllerPanel.setVisible(true);
                    cloudFrame.setVisible(true);
                    
                }
            }
        });
        
              
        
        // Button to view job submissions in Cloud Controller
        JButton viewJobsButtonCC = new JButton("Accept or Reject Job Submissions");
        cloudControllerPanel.add(new JLabel());
        cloudControllerPanel.add(viewJobsButtonCC);

     //Accept or Reject Job Submissions
        viewJobsButtonCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jobPanel = new JPanel();
                jobPanel.setLayout(new BoxLayout(jobPanel, BoxLayout.Y_AXIS)); 

                //Loops through the job submissions
                for (int i = 0; i < jobSubmissions.size(); i++) {
                    final int index = i;  
                    Job job = jobSubmissions.get(i);

                    JPanel singleJobPanel = new JPanel();
                    singleJobPanel.setLayout(new BoxLayout(singleJobPanel, BoxLayout.X_AXIS)); // Layout for job info and buttons

                    singleJobPanel.add(new JLabel(job.toString()));

                    JButton acceptButton = new JButton("Accept");
                    JButton rejectButton = new JButton("Reject");

                    //Accept Button Action
                    int finalI = i;
                    acceptButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //Write the accepted job to the file
                            try (FileWriter writer = new FileWriter("job_submitter_data.txt", true)) {
                                writer.write(job + "\n");
                                writer.write("---------------------------\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                MySQLManager.setJob(job, MySQLManager.getClient("user1"));
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            jobClientIDs.add(tempJobClientIDs.get(finalI));
                            jobDurations.add(tempJobDurations.get(finalI));
                            int clientIDInt = tempJobClientIDs.get(finalI);
                            String message = "Your job was accepted: " + job;
                            
                            //Notify the server
                            Server.notifyClient(clientIDInt, message);
                            JOptionPane.showMessageDialog(frame, job, "Job Accepted", JOptionPane.INFORMATION_MESSAGE);

                            // Remove the accepted job from the list
                            jobSubmissions.remove(index);

                            // Show acceptance message
                            JOptionPane.showMessageDialog(cloudFrame, "Job Accepted: " + job, "Job Status", JOptionPane.INFORMATION_MESSAGE);
                            jobPanel.setVisible(false);
                        }
                    });

                    // Reject Button Action
                    rejectButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	int clientIDInt = tempJobClientIDs.get(finalI);
                        	String message = "Your job was rejected: " + job;
                            
                        	//Notify the server
                        	Server.notifyClient(clientIDInt,  message);
                            JOptionPane.showMessageDialog(frame, job, "Job Rejected", JOptionPane.INFORMATION_MESSAGE);

                        	// Remove the rejected job from the list
                            jobSubmissions.remove(index);
                            JOptionPane.showMessageDialog(cloudFrame, "Job Rejected: " + job, "Job Status", JOptionPane.INFORMATION_MESSAGE);
                            jobPanel.setVisible(false);
                        }
                    });

                    singleJobPanel.add(acceptButton);
                    singleJobPanel.add(rejectButton);

                    jobPanel.add(singleJobPanel);
                }
                
             //Mass Acception and Rejection
                JButton acceptAllJobsButton = new JButton("Accept All");
                JButton rejectAllJobsButton = new JButton("Reject All");

                // Mass Accept 
                acceptAllJobsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < jobSubmissions.size(); i++) {
                            Job job = jobSubmissions.get(i);
                            try (FileWriter writer = new FileWriter("job_submitter_data.txt", true)) {
                                writer.write(job + "\n");
                                writer.write("---------------------------\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                MySQLManager.setJob(job, MySQLManager.getClient("user1"));
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            jobClientIDs.add(tempJobClientIDs.get(i));
                            jobDurations.add(tempJobDurations.get(i));
                            
                            int clientIDInt = tempJobClientIDs.get(i);
                            String message = "Your job was accepted: " + job;

                            // Notify the server
                            Server.notifyClient(clientIDInt, message);
                        }

                        
                        jobSubmissions.clear();
                        
                        JOptionPane.showMessageDialog(cloudFrame, "All jobs have been accepted!", "Mass Accept", JOptionPane.INFORMATION_MESSAGE);
                        jobPanel.setVisible(false);
                    }
                });

                // Mass Reject Action
                rejectAllJobsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < jobSubmissions.size(); i++) {
                            Job job = jobSubmissions.get(i);
                            int clientIDInt = tempJobClientIDs.get(i);
                            String message = "Your job was rejected: " + job;

                            // Notify the server
                            Server.notifyClient(clientIDInt, message);
                        }

                        
                        jobSubmissions.clear();
                        JOptionPane.showMessageDialog(cloudFrame, "All jobs have been rejected!", "Mass Reject", JOptionPane.INFORMATION_MESSAGE);
                        jobPanel.setVisible(false);
                    }
                });

                // Add the mass action buttons to the job panel
                jobPanel.add(acceptAllJobsButton);
                jobPanel.add(rejectAllJobsButton);


                JOptionPane.showMessageDialog(cloudFrame, jobPanel, "Job Submissions", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        });
        
        
                    
        // Button to view car submissions in Cloud Controller
        JButton viewCarsButtonCC = new JButton("Accept or Reject Car Submissions");
        cloudControllerPanel.add(new JLabel());
        cloudControllerPanel.add(viewCarsButtonCC);

    	// Accept or Reject Car Submissions
        viewCarsButtonCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel carPanel = new JPanel();
                carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS)); 

                // Loops through the car owner submissions
                for (int i = 0; i < carOwnerSubmissions.size(); i++) {
                    final int index = i;  
                    String car = carOwnerSubmissions.get(i);  

                    JPanel singleCarPanel = new JPanel();
                    singleCarPanel.setLayout(new BoxLayout(singleCarPanel, BoxLayout.X_AXIS)); 

                    singleCarPanel.add(new JLabel(car));

                    JButton acceptButton = new JButton("Accept");
                    JButton rejectButton = new JButton("Reject");

                    //Accept button action
                    int finalI = i;
                    acceptButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Write the accepted car to the file
                            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                            try (FileWriter writer = new FileWriter("car_owner_data.txt", true)) {
                                writer.write(car + "\n");
                                writer.write("---------------------------\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            
                            int ownerIDInt = tempCarOwnerIDs.get(finalI);
                            carOwnerIDs.add(ownerIDInt);
                            String message = "Your car submission was accepted: " + car;
                            
                            //Notify the server
                            Server.notifyClient(ownerIDInt, message);
                            JOptionPane.showMessageDialog(frame, message);

                            // Remove the accepted car from the list
                            carOwnerSubmissions.remove(index);
                            JOptionPane.showMessageDialog(cloudFrame, "Car Accepted: " + car, "Car Status", JOptionPane.INFORMATION_MESSAGE);
                            carPanel.setVisible(false);
                        }
                    });
                    
                    //Reject button action
                    rejectButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	int ownerIDInt = tempCarOwnerIDs.get(finalI);
                        	String message = "Your car submission was rejected: " + car;
                        	
                        	//Notify the server
                            Server.notifyClient(ownerIDInt, message);
                            JOptionPane.showMessageDialog(frame, message);

                            // Remove the rejected car from the list
                            carOwnerSubmissions.remove(index);
                            JOptionPane.showMessageDialog(cloudFrame, "Car Rejected: " + car, "Car Status", JOptionPane.INFORMATION_MESSAGE);
                            carPanel.setVisible(false);
                        }
                    });

                    singleCarPanel.add(acceptButton);
                    singleCarPanel.add(rejectButton);
                    carPanel.add(singleCarPanel);
                }
                
             // Mass Accept/Reject For Cars
                JButton acceptAllCarsButton = new JButton("Accept All");
                JButton rejectAllCarsButton = new JButton("Reject All");

                // Mass Accept 
                acceptAllCarsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < carOwnerSubmissions.size(); i++) {
                            String car = carOwnerSubmissions.get(i);
                            try (FileWriter writer = new FileWriter("car_owner_data.txt", true)) {
                                writer.write(car + "\n");
                                writer.write("---------------------------\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            int ownerIDInt = tempCarOwnerIDs.get(i);
                            String message = "Your car submission was accepted: " + car;

                            // Notify the server
                            Server.notifyClient(ownerIDInt, message);
                        }

                        
                        carOwnerSubmissions.clear();
                        JOptionPane.showMessageDialog(cloudFrame, "All cars have been accepted!", "Mass Accept", JOptionPane.INFORMATION_MESSAGE);
                        carPanel.setVisible(false);
                    }
                });

                // Mass Reject
                rejectAllCarsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < carOwnerSubmissions.size(); i++) {
                            String car = carOwnerSubmissions.get(i);
                            int ownerIDInt = tempCarOwnerIDs.get(i);
                            String message = "Your car submission was rejected: " + car;

                            // Notify the server
                            Server.notifyClient(ownerIDInt, message);
                        }

                        
                        carOwnerSubmissions.clear();
                        JOptionPane.showMessageDialog(cloudFrame, "All cars have been rejected!", "Mass Reject", JOptionPane.INFORMATION_MESSAGE);
                        carPanel.setVisible(false);
                    }
                });

                
                carPanel.add(acceptAllCarsButton);
                carPanel.add(rejectAllCarsButton);


                JOptionPane.showMessageDialog(cloudFrame, carPanel, "Car Owner Submissions", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
     //Button to view accepted jobs for Job Submitter
        JButton viewAcceptedJobsButton = new JButton("View Accepted Jobs");
        informationInputJobSubmitterPanel.add(viewAcceptedJobsButton);

        viewAcceptedJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Read accepted jobs from the job submitter file
                try (BufferedReader reader = new BufferedReader(new FileReader("job_submitter_data.txt"))) {
                    String line;
                    StringBuilder jobsContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        jobsContent.append(line).append("\n");
                    }
                    //Show the accepted jobs content in a dialog box
                    JOptionPane.showMessageDialog(frame, jobsContent.toString(), "Accepted Jobs", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error reading accepted jobs file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

     //Button to view accepted cars for Car Owner
        JButton viewAcceptedCarsButton = new JButton("View Accepted Cars");
        informationInputCarOwnerPanel.add(viewAcceptedCarsButton);

        viewAcceptedCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Read accepted cars from the car owner file
                try (BufferedReader reader = new BufferedReader(new FileReader("car_owner_data.txt"))) {
                    String line;
                    StringBuilder carsContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        carsContent.append(line).append("\n");
                    }
                    //Show the accepted cars content in a dialog box
                    JOptionPane.showMessageDialog(frame, carsContent.toString(), "Accepted Cars", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error reading accepted cars file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // create account
        JPanel createAccountPanel = new JPanel(new GridLayout(13,2));
        createAccountPanel.setVisible(false);

        JRadioButton JobSubmitterAccountRadioButton = new JRadioButton("Job Submitter");
        JRadioButton CarOwnerAccountRadioButton = new JRadioButton("Car Owner");

        ButtonGroup selectNewAccountType = new ButtonGroup();
        selectNewAccountType.add(JobSubmitterAccountRadioButton);
        selectNewAccountType.add(CarOwnerAccountRadioButton);
        createAccountPanel.add(JobSubmitterAccountRadioButton);
        createAccountPanel.add(CarOwnerAccountRadioButton);
        JobSubmitterAccountRadioButton.setSelected(true);

        JLabel userCreateLabel = new JLabel("Username: ");
        JTextField userCreateTextField = new JTextField(30);

        createAccountPanel.add(userCreateLabel);
        createAccountPanel.add(userCreateTextField);

        JLabel passwordCreateLabel = new JLabel("Password: ");
        JPasswordField passwordCreateTextField = new JPasswordField(30);

        createAccountPanel.add(passwordCreateLabel);
        createAccountPanel.add(passwordCreateTextField);

        JLabel fullnameCreateLabel = new JLabel("Full Name: ");
        JTextField fullnameCreateTextField = new JTextField(30);

        createAccountPanel.add(fullnameCreateLabel);
        createAccountPanel.add(fullnameCreateTextField);

        JLabel carModelCreateLabel = new JLabel("Car Model: ");
        JTextField carModelCreateTextField = new JTextField(30);

        createAccountPanel.add(carModelCreateLabel);
        createAccountPanel.add(carModelCreateTextField);
        carModelCreateLabel.setVisible(false);
        carModelCreateTextField.setVisible(false);

        JLabel carMakeCreateLabel = new JLabel("Car Make: ");
        JTextField carMakeCreateTextField = new JTextField(30);

        createAccountPanel.add(carMakeCreateLabel);
        createAccountPanel.add(carMakeCreateTextField);
        carMakeCreateLabel.setVisible(false);
        carMakeCreateTextField.setVisible(false);

        JLabel carYearCreateLabel = new JLabel("Car Year: ");
        JTextField carYearCreateTextField = new JTextField(30);

        createAccountPanel.add(carYearCreateLabel);
        createAccountPanel.add(carYearCreateTextField);
        carYearCreateLabel.setVisible(false);
        carYearCreateTextField.setVisible(false);

        JLabel carVinCreateLabel = new JLabel("Car VIN: ");
        JTextField carVinCreateTextField = new JTextField(30);

        createAccountPanel.add(carVinCreateLabel);
        createAccountPanel.add(carVinCreateTextField);
        carVinCreateLabel.setVisible(false);
        carVinCreateTextField.setVisible(false);

        JLabel carPlateNumberCreateLabel = new JLabel("Car Plate Number: ");
        JTextField carPlateNumberCreateTextField = new JTextField(30);

        createAccountPanel.add(carPlateNumberCreateLabel);
        createAccountPanel.add(carPlateNumberCreateTextField);
        carPlateNumberCreateLabel.setVisible(false);
        carPlateNumberCreateTextField.setVisible(false);

        JLabel carTypeCreateLabel = new JLabel("Car Type: ");
        JTextField carTypeCreateTextField = new JTextField(30);

        createAccountPanel.add(carTypeCreateLabel);
        createAccountPanel.add(carTypeCreateTextField);
        carTypeCreateLabel.setVisible(false);
        carTypeCreateTextField.setVisible(false);


        createAccountPanel.add(currentSelectionButton);

        //job submitter create account
        JButton clientJobSubmitterCreationButton = new JButton("Job Submitter Create Account");
        createAccountPanel.add(clientJobSubmitterCreationButton);

        clientJobSubmitterCreationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userCreateTextField.getText();
                String password = passwordCreateTextField.getText();
                String fullname = fullnameCreateTextField.getText();
                Client newClient = new Client(username, password, clientArray.size() + ownerArray.size() + 1, fullname, 0.00);
                clientArray.add(newClient);

                try {
                    // Insert new client into the database
                    MySQLManager.setClient(newClient);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error creating account.");
                    return;
                }

                currentSelectionButton.setText("Account Created");
                createAccountPanel.setVisible(false);
                informationInputJobSubmitterPanel.setVisible(true);
                userArrayPosition[0] = clientArray.size() - 1;
            }
        });

        //car owner create account
        JButton clientCarOwnerCreationButton = new JButton("Car Owner Create Account");
        createAccountPanel.add(clientCarOwnerCreationButton);
        clientCarOwnerCreationButton.setVisible(false);

        clientCarOwnerCreationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userCreateTextField.getText();
                String password = passwordCreateTextField.getText();
                String fullname = fullnameCreateTextField.getText();
                String carModel = carModelCreateTextField.getText();
                String carMake = carMakeCreateTextField.getText();
                int carYear = Integer.parseInt(carYearCreateTextField.getText());
                String carVin = carVinCreateTextField.getText();
                String carPlateNumber = carPlateNumberCreateTextField.getText();
                String carType = carTypeCreateTextField.getText();
                
                //Create a new owner and car objects
                Car newCar = new Car(carModel, carMake, carYear, carVin, carPlateNumber, carType, true);
                Owner newOwner = new Owner(username, password,clientArray.size() + ownerArray.size() + 1, fullname, 0.00, new Car(carModel, carMake, carYear, carVin, carPlateNumber, carType, true));
               	ownerArray.add(newOwner);
               	
               	try {
               		//Insert new owner and car into the database
               		MySQLManager.setOwner(newOwner);
               		MySQLManager.setCar(newCar, newOwner);
               	} catch (SQLException ex) {
               		ex.printStackTrace();
               		JOptionPane.showMessageDialog(frame, "Error creating account.");
               		return;
               	}
        
        		currentSelectionButton.setText("Account Created");
                createAccountPanel.setVisible(false);
                informationInputCarOwnerPanel.setVisible(true);
                userArrayPosition[0] = ownerArray.size() - 1;
            }
        });

        createAccountPanel.add(currentSelectionButton);

        JobSubmitterAccountRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModelCreateLabel.setVisible(false);
                carModelCreateTextField.setVisible(false);
                carMakeCreateTextField.setVisible(false);
                carMakeCreateLabel.setVisible(false);
                carYearCreateLabel.setVisible(false);
                carYearCreateTextField.setVisible(false);
                carVinCreateLabel.setVisible(false);
                carVinCreateTextField.setVisible(false);
                carPlateNumberCreateLabel.setVisible(false);
                carPlateNumberCreateTextField.setVisible(false);
                carTypeCreateLabel.setVisible(false);
                carTypeCreateTextField.setVisible(false);
                clientCarOwnerCreationButton.setVisible(false);
                clientJobSubmitterCreationButton.setVisible(true);
            }
        });

        CarOwnerAccountRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModelCreateLabel.setVisible(true);
                carModelCreateTextField.setVisible(true);
                carMakeCreateTextField.setVisible(true);
                carMakeCreateLabel.setVisible(true);
                carYearCreateLabel.setVisible(true);
                carYearCreateTextField.setVisible(true);
                carVinCreateLabel.setVisible(true);
                carVinCreateTextField.setVisible(true);
                carPlateNumberCreateLabel.setVisible(true);
                carPlateNumberCreateTextField.setVisible(true);
                carTypeCreateLabel.setVisible(true);
                carTypeCreateTextField.setVisible(true);
                clientCarOwnerCreationButton.setVisible(true);
                clientJobSubmitterCreationButton.setVisible(false);
            }
        });

        // Home page
        JPanel homePagePanel = new JPanel(new GridLayout(5,1));
        

        JLabel homePagePanelHeading = new JLabel("Welcome to the Vehicular Cloud Console");
        JLabel homePagePanelInfo = new JLabel("Connecting Jobs to Cars since 2024");

        homePagePanel.add(homePagePanelHeading);
        homePagePanel.add(homePagePanelInfo);

        JButton logInPageButton = new JButton("Log In");
        homePagePanel.add(logInPageButton);
        
       
        

        logInPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePagePanel.setVisible(false);
                logInPanel.setVisible(true);
                logInPanel.add(backButton);
            }
        });
        
        

        JButton createAccountPageButton = new JButton("Create Account");
        homePagePanel.add(createAccountPageButton);

        createAccountPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePagePanel.setVisible(false);
                createAccountPanel.setVisible(true);
                createAccountPanel.add(backButton);
            }
        });

        homePagePanel.setVisible(true);
        frame.add(homePagePanel);
        
      
      // Back Button Setup  
        backButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		logInPanel.setVisible(false);
        		createAccountPanel.setVisible(false);
        		homePagePanel.setVisible(true);
        	}
        });
        


        //client profile page
        JPanel clientProfilePanel = new JPanel();
        clientProfilePanel.setLayout(new GridLayout(8,2));

        JLabel clientNameLabel = new JLabel("Name: ");
        JLabel clientName = new JLabel();

        clientProfilePanel.add(clientNameLabel);
        clientProfilePanel.add(clientName);

        JLabel clientUsernameLabel = new JLabel("Username: ");
        JLabel clientUsername = new JLabel();

        clientProfilePanel.add(clientUsernameLabel);
        clientProfilePanel.add(clientUsername);

        JLabel clientProfileIDLabel = new JLabel("ID: ");
        JLabel clientProfileID = new JLabel();

        clientProfilePanel.add(clientProfileIDLabel);
        clientProfilePanel.add(clientProfileID);

        JLabel clientBalanceLabel = new JLabel("Account Balance: ");
        JLabel clientBalance = new JLabel();

        clientProfilePanel.add(clientBalanceLabel);
        clientProfilePanel.add(clientBalance);

        JButton clientProfileButton = new JButton("Profile");
        informationInputJobSubmitterPanel.add(clientProfileButton);

        clientProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		//Fetch the client from the database using the username
            		Client client = MySQLManager.getClient(clientArray.get(userArrayPosition[0]).getUsername());

                    // Update the labels with the fetched data
                    clientName.setText(client.getFullName());
                    clientUsername.setText(client.getUsername());
                    clientProfileID.setText(String.valueOf(client.getID()));
                    clientBalance.setText(String.valueOf(client.getBalance()));

                    // Make the profile panel visible and hide the job submitter panel
                    clientProfilePanel.setVisible(true);
                    informationInputJobSubmitterPanel.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving profile.");
                }
            }
        });

        clientProfilePanel.setVisible(false);

        // back to job submission page
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
        ownerProfilePanel.setLayout(new GridLayout(8,2));

        JLabel ownerNameLabel = new JLabel("Name: ");
        JLabel ownerName = new JLabel();

        ownerProfilePanel.add(ownerNameLabel);
        ownerProfilePanel.add(ownerName);

        JLabel ownerUsernameLabel = new JLabel("Username: ");
        JLabel ownerUsername = new JLabel();

        ownerProfilePanel.add(ownerUsernameLabel);
        ownerProfilePanel.add(ownerUsername);

        JLabel ownerProfileIDLabel = new JLabel("ID: ");
        JLabel ownerProfileID = new JLabel();

        ownerProfilePanel.add(ownerProfileIDLabel);
        ownerProfilePanel.add(ownerProfileID);

        JLabel ownerBalanceLabel = new JLabel("Account Balance: ");
        JLabel ownerBalance = new JLabel();
        		
        ownerProfilePanel.add(ownerBalanceLabel);
        ownerProfilePanel.add(ownerBalance);

        JLabel ownerCarLabel = new JLabel("Car: ");
        JLabel carInfo = new JLabel();

        ownerProfilePanel.add(ownerCarLabel);
        ownerProfilePanel.add(carInfo);

        JButton ownerProfileButton = new JButton("Profile");
        informationInputCarOwnerPanel.add(ownerProfileButton);

        ownerProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    // Fetch the owner from the database using the username
                    Owner owner = MySQLManager.getOwner(ownerArray.get(userArrayPosition[0]).getUsername());

                    // Update the labels with the fetched data
                    ownerName.setText(owner.getFullName());
                    ownerUsername.setText(owner.getUsername());
                    ownerProfileID.setText(String.valueOf(owner.getID()));
                    ownerBalance.setText(String.valueOf(owner.getBalance()));

                    // Fetch and display the car information
                    carInfo.setText(owner.getCars().toString()); // This will display the list of cars associated with the owner

                    // Make the profile panel visible and hide the car owner panel
                    ownerProfilePanel.setVisible(true);
                    informationInputCarOwnerPanel.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving profile.");
                }
            }
        });

        ownerProfilePanel.setVisible(false);

        // Back to car owner page
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
                homePagePanel.setVisible(true);
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
                homePagePanel.setVisible(true);
                informationInputCarOwnerPanel.setVisible(false);
                currentSelectionButton.setText("");
            }
        });

        //cloud controller log out
        JButton cloudControllerLogoutButton = new JButton("Log Out");
        cloudControllerPanel.add(cloudControllerLogoutButton);

        cloudControllerLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cloudControllerPanel.setVisible(false);
                cloudFrame.setVisible(false);
                currentSelectionButton.setText("");
            }
        });

        frame.add(logInPanel);
        frame.add(createAccountPanel);

        frame.add(informationInputJobSubmitterPanel);
        frame.add(informationInputCarOwnerPanel);
        frame.add(clientProfilePanel);
        frame.add(ownerProfilePanel);

        cloudFrame.add(cloudControllerPanel);

        frame.setVisible(true);
        cloudFrame.setVisible(false);

        System.out.println("Hello world!");
    }
}
     
