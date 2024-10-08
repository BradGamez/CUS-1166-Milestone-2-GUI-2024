import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vehicular Cloud Console");
        frame.setSize(1280, 720);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
            }
        });
        clientCarOwnerPanel.add(clientCarOwnerButton);

        frame.add(clientJobSubmitterPanel);
        frame.add(currentSelectionPanel);
        frame.add(clientCarOwnerPanel);

        System.out.println("Hello world!");
    }
}