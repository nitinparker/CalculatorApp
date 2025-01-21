import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorUI extends JFrame {
    private JTextField inputField1; 
    private JTextField inputField2; 
    private JTextField resultField; 
    private JTextArea historyArea; 
    private ArrayList<String> history; 
    private JButton submitButton; 
    private String selectedOperation; 

    public CalculatorUI() {
        setTitle("Advanced Calculator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        history = new ArrayList<>();

        //Operation Buttons
        JPanel operationPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        operationPanel.setBorder(BorderFactory.createTitledBorder("Select Operation"));

        String[] operations = {
            "Expression Calculator", "Addition", "Subtraction", "Multiplication", "Division",
            "Area of Circle", "Area of Rectangle", "Area of Square",
            "Area of Triangle", "Perimeter of Rectangle",
            "Perimeter of Square", "Solve Simple Equation (ax+b=0)"
        };

        for (String operation : operations) {
            JButton button = new JButton(operation);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedOperation = operation;
                    configureInputFields(); 
                    submitButton.setEnabled(true); 
                }
            });
            operationPanel.add(button);
        }
        add(operationPanel, BorderLayout.NORTH);

        //Input Fields and Result
        JPanel middlePanel = new JPanel(new GridLayout(4, 1, 10, 10));

        
        inputField1 = new JTextField();
        inputField1.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField1.setBorder(BorderFactory.createTitledBorder("Input 1"));
        inputField1.setEnabled(false); 

        
        inputField2 = new JTextField();
        inputField2.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField2.setBorder(BorderFactory.createTitledBorder("Input 2"));
        inputField2.setEnabled(false); 
        inputField2.setVisible(false); 

        // Result field
        resultField = new JTextField();
        resultField.setFont(new Font("Arial", Font.PLAIN, 16));
        resultField.setBorder(BorderFactory.createTitledBorder("Result"));
        resultField.setEditable(false);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false); // Initially disabled
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Collect inputs based on visibility
                    String[] inputs;
                    if (inputField2.isVisible()) {
                        inputs = new String[]{inputField1.getText(), inputField2.getText()};
                    } else {
                        inputs = new String[]{inputField1.getText()};
                    }

                    
                    String result = CalculatorLogic.calculate(selectedOperation, inputs);
                    resultField.setText(result);

                    // Update history
                    String historyEntry = selectedOperation + ": " + String.join(", ", inputs) + " = " + result;
                    history.add(historyEntry);
                    updateHistory();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
                }
            }
        });

        middlePanel.add(inputField1);
        middlePanel.add(inputField2);
        middlePanel.add(resultField);
        middlePanel.add(submitButton);

        add(middlePanel, BorderLayout.CENTER);

        // History
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Calculation History"));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(historyArea);

        historyPanel.add(scrollPane, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.SOUTH);
    }

    
    private void configureInputFields() {
        inputField1.setEnabled(true);
        inputField1.setText("");
        inputField2.setText("");
        inputField2.setEnabled(false);
        inputField2.setVisible(false);

        switch (selectedOperation) {
            case "Area of Circle":
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Radius"));
                break;
            case "Area of Square":
            case "Perimeter of Square":
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Side Length"));
                break;
            case "Area of Rectangle":
            case "Perimeter of Rectangle":
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Length"));
                inputField2.setBorder(BorderFactory.createTitledBorder("Enter Width"));
                inputField2.setVisible(true);
                inputField2.setEnabled(true);
                break;
            case "Area of Triangle":
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Base"));
                inputField2.setBorder(BorderFactory.createTitledBorder("Enter Height"));
                inputField2.setVisible(true);
                inputField2.setEnabled(true);
                break;
            case "Solve Simple Equation (ax+b=0)":
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Equation (e.g., 2x+3=0)"));
                break;
            default:
                inputField1.setBorder(BorderFactory.createTitledBorder("Enter Expression or Inputs"));
                break;
        }
    }

    // Update the history area with the latest calculations
    private void updateHistory() {
        StringBuilder historyText = new StringBuilder();
        for (String historyItem : history) {
            historyText.append(historyItem).append("\n");
        }
        historyArea.setText(historyText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calculatorUI = new CalculatorUI();
            calculatorUI.setVisible(true);
        });
    }
}
