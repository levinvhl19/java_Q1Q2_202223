import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame implements ActionListener {
    private final JTextField textField1;
    private final JTextField textField2;
    private final JButton addButton;
    private final JButton subtractButton;
    private final JButton multiplyButton;
    private final JButton divideButton;

    public CalculatorUI() {
        // Set the frame properties
        setTitle("Calculator");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the text fields
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        // Create the buttons
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");

        // Add action listeners to the buttons
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);

        // Create a panel for the text fields and labels
        JPanel textPanel = new JPanel(new GridLayout(2, 2));
        textPanel.add(new JLabel("Number 1:"));
        textPanel.add(textField1);
        textPanel.add(new JLabel("Number 2:"));
        textPanel.add(textField2);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);

        // Create a panel for the output
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputPanel.setBackground(Color.BLACK);
        outputPanel.setPreferredSize(new Dimension(400, 100));

        // Add the panels to the frame
        add(textPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        // Get the input values
        int num1, num2;
        try {
            num1 = Integer.parseInt(textField1.getText());
            num2 = Integer.parseInt(textField2.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform the selected operation
        int result = 0;
        if (e.getSource() == addButton) {
            result = num1 + num2;
        } else if (e.getSource() == subtractButton) {
            result = num1 - num2;
        } else if (e.getSource() == multiplyButton) {
            result = num1 * num2;
        } else if (e.getSource() == divideButton) {
            if (num2 == 0) {
                JOptionPane.showMessageDialog(this, "Error: Division by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            result = num1 / num2;
        }

        // Display the result in a popup
        JOptionPane.showMessageDialog(this, "Result: " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        CalculatorUI calculator = new CalculatorUI();
        calculator.setVisible(true);
    }
}
