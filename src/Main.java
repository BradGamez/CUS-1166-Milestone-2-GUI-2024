import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vehicular Cloud Console");
        frame.setSize(1280, 720);
        frame.setLayout(new GridLayout(2, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Job Submitter Panel Setup and Information 

        JPanel informationInputJobSubmitterPanel = new JPanel();
        informationInputJobSubmitterPanel.setLayout(new GridLayout(4, 2));
        
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
        		
        		clientIDTextField.setText("");
        		jobDurationTextField.setText("");
        		jobDeadlineTextField.setText("");
        	
        	}
        });
        
        frame.add(informationInputJobSubmitterPanel);
        informationInputJobSubmitterPanel.setVisible(false);
        
        // Car Owner Panel Setup and Information 

        JPanel informationInputCarOwnerPanel = new JPanel();
        
        informationInputCarOwnerPanel.setLayout(new GridLayout(4, 2));
        
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
        		
        		ownerIDTextField.setText("");
        		vinTextField.setText("");
        		residencyTextField.setText("");
        	}
        	
        });
        
        frame.add(informationInputCarOwnerPanel);
        
        
        informationInputCarOwnerPanel.setVisible(false);

        // Part B

        // Part A

        JPanel currentSelectionPanel = new JPanel();
        
        JLabel currentSelectionButton = new JLabel("N/A");
        
        currentSelectionPanel.add(currentSelectionButton);

        JPanel clientJobSubmitterPanel = new JPanel();
        
        JButton clientJobSubmitterButton = new JButton("Select Job Submitter");
        
        clientJobSubmitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                currentSelectionButton.setText("Job Submitter");
                informationInputCarOwnerPanel.setVisible(false);
                informationInputJobSubmitterPanel.setVisible(true);
            }
        });
        clientJobSubmitterPanel.add(clientJobSubmitterButton);

        JPanel clientCarOwnerPanel = new JPanel();
        JButton clientCarOwnerButton = new JButton("Select Car Owner");
        clientCarOwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentSelectionButton.getText());
                currentSelectionButton.setText("Car Owner");
                informationInputJobSubmitterPanel.setVisible(false);
                informationInputCarOwnerPanel.setVisible(true);
            }
        });
        clientCarOwnerPanel.add(clientCarOwnerButton);

        frame.add(clientJobSubmitterPanel);
        frame.add(currentSelectionPanel);
        frame.add(clientCarOwnerPanel);

        // Part A

        // Part B

        frame.add(informationInputJobSubmitterPanel);
        frame.add(informationInputCarOwnerPanel);

        // Part B
        frame.setVisible(true);

        System.out.println("Hello world!");
    }
}