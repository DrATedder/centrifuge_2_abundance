import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class centrifuge_2_abundance extends JFrame {
    private JTextField inputDirField;
    private JTextField abundanceThresholdField;
    private JTextArea messageTextArea;

    private static final String AUTHOR_INFO = "Author: Dr. Andrew Tedder\nUniversity of Bradford";

    public centrifuge_2_abundance() {
        setTitle("Centrifuge to Abundance Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Input Directory:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputDirField = new JTextField(20);
        inputPanel.add(inputDirField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton browseInputDirButton = new JButton("Browse");
        browseInputDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseInputDir();
            }
        });
        inputPanel.add(browseInputDirButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Abundance Threshold:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        abundanceThresholdField = new JTextField(10);
        inputPanel.add(abundanceThresholdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton generateAbundanceButton = new JButton("Generate Abundance");
        generateAbundanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAbundance();
            }
        });
        inputPanel.add(generateAbundanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton startAgainButton = new JButton("Start Again");
        startAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        inputPanel.add(startAgainButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        inputPanel.add(exitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        messageTextArea = new JTextArea(10, 40);
        messageTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        inputPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton authorInfoButton = new JButton("Author Info");
        authorInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAuthorInfo();
            }
        });
        inputPanel.add(authorInfoButton, gbc);

        getContentPane().add(inputPanel);
    }

    private void browseInputDir() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            inputDirField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void generateAbundance() {
        String inputDir = inputDirField.getText();
        String abundanceThreshold = abundanceThresholdField.getText();

        // Construct the command to execute the Python script
        String pythonScript = "python";  // Assuming python is in the system PATH
        String scriptPath = "genus_level_read_count_abundance.py";  // Adjust this to your script's path
        String[] command = {pythonScript, scriptPath, inputDir, abundanceThreshold};

        try {
            // Execute the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Read the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Display the output in the messageTextArea
            messageTextArea.setText(output.toString());

        } catch (IOException e) {
            e.printStackTrace();
            messageTextArea.setText("Error: Failed to execute Python script.");
        }
    }

    private void showAuthorInfo() {
        JOptionPane.showMessageDialog(this, AUTHOR_INFO, "Author Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        inputDirField.setText("");
        abundanceThresholdField.setText("");
        messageTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new centrifuge_2_abundance().setVisible(true);
            }
        });
    }
}
