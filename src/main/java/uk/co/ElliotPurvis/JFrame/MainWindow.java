package uk.co.ElliotPurvis.JFrame;

import uk.co.ElliotPurvis.Main;
import uk.co.ElliotPurvis.TextPrompt;
import uk.co.ElliotPurvis.exceptions.InsufficientValuesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


/**
 * Created by Elliot on 16/08/2015.
 */
public class MainWindow extends JFrame implements ActionListener {

    private final JLabel titleMain;
    private final JTextPane subtitle, postTitle, indiciesText;
    private final JButton calculateButton, resetButton;
    private final JTextField accelerationText, velocityText, initialVText, timeText, distanceText;
    private final Main main;
    private static String SYSTEM_FONT;

    public MainWindow(Main main) {
        super("SUVAT Solver");


        this.main = main;

        SYSTEM_FONT = new JLabel().getFont().getFontName();

        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));

        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        setSize(400, 270);
        setVisible(true);



        titleMain = new JLabel("SUVAT Calculator");
        titleMain.setFont(new Font(SYSTEM_FONT, Font.BOLD, 25));
        add(titleMain);


        subtitle = textAreaProperties(new JTextPane());
        subtitle.setText("Enter three or more unknown values; nulls will be calculated");
        add(subtitle);

        accelerationText = new JTextField(10);
        accelerationText.setPreferredSize(new Dimension(100, 30));
        add(accelerationText);

        velocityText = new JTextField(10);
        velocityText.setPreferredSize(new Dimension(100, 30));
        add(velocityText);

        initialVText = new JTextField(10);
        initialVText.setPreferredSize(new Dimension(100, 30));
        add(initialVText);

        distanceText = new JTextField(10);
        distanceText.setPreferredSize(new Dimension(100, 30));
        add(distanceText);

        timeText = new JTextField(10);
        timeText.setPreferredSize(new Dimension(100, 30));
        add(timeText);

        postTitle = textAreaProperties(new JTextPane());
        // Order of arguements = new Insets(Top Inset, left Inset, bottom Inset, right);
        postTitle.setMargin(new Insets(10, 30, 10, 30));
        postTitle.setText("Enter the above values and press calculate,");
        postTitle.setSize(400, 50);
        add(postTitle);

        calculateButton = new JButton("Calculate");
        calculateButton.setVisible(true);
        calculateButton.addActionListener(this);
        add(calculateButton);

        resetButton = new JButton("Reset");
        resetButton.setVisible(true);
        resetButton.addActionListener(this);
        add(resetButton);

        indiciesText = textAreaProperties(new JTextPane());
        indiciesText.setText("To enter indicies, use the format: 6.10^10  ->  6.0E10 ");
        add(indiciesText);


        // Add hint text to all Text input boxes.
        resetTextPrompts();
    }


    // Placed in private method to decrease hard coding for Reset button + setup.
    private void resetTextPrompts(){

        // Clear existing texts as they take priority over TextPrompt.
        timeText.setText("");
        velocityText.setText("");
        distanceText.setText("");
        initialVText.setText("");
        accelerationText.setText("");


        // Add text prompt hints to text input boxes.
        TextPrompt tpTime = new TextPrompt("Time", timeText);
        tpTime.setShow(TextPrompt.Show.FOCUS_LOST);

        TextPrompt tpDistance = new TextPrompt("Distance", distanceText);
        tpDistance.setShow(TextPrompt.Show.FOCUS_LOST);

        TextPrompt tpInitialV = new TextPrompt("Initial Velocity", initialVText);
        tpInitialV.setShow(TextPrompt.Show.FOCUS_LOST);

        TextPrompt tpVelocity = new TextPrompt("Velocity", velocityText);
        tpVelocity.setShow(TextPrompt.Show.FOCUS_LOST);

        TextPrompt tpAcceleration = new TextPrompt("Acceleration", accelerationText);
        tpAcceleration.setShow(TextPrompt.Show.FOCUS_LOST);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {

            HashMap<String, String> tempValues = new HashMap<String, String>();
            tempValues.put("V", velocityText.getText().toString());
            tempValues.put("S", distanceText.getText().toString());
            tempValues.put("U", initialVText.getText().toString());
            tempValues.put("T", timeText.getText().toString());
            tempValues.put("A", accelerationText.getText().toString());

            for(String key : tempValues.keySet()){
                System.out.print(key + " : " +  tempValues.get(key) + "\n");
            }


            HashMap<String, Double> finalValues = new HashMap<String, Double>();

            for (String key : tempValues.keySet()) {
                try {
                    Double x = Double.parseDouble(tempValues.get(key));
                    finalValues.put(key, x);
                } catch (NumberFormatException numberFormatException) {
                    System.out.print("Couldn't load value " + key + " | Assigning to null \n");
                }
                main.setValue(key, finalValues.get(key));
            }

            // Pass the parse values to our main class to` be calculated.
            try {
                main.calculate();
            } catch(InsufficientValuesException exception){
                exception.printStackTrace();
                return;
            }
            updateTextBoxes();
        } else if(e.getSource() == resetButton){
            resetTextPrompts();
        }
    }

    private JTextPane textAreaProperties(JTextPane textArea) {
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        return textArea;
    }

    public void updateTextBoxes() {
        velocityText.setText(Double.toString(main.getValue("V")));
        initialVText.setText(Double.toString(main.getValue("U")));
        distanceText.setText(Double.toString(main.getValue("S")));
        timeText.setText(Double.toString(main.getValue("T")));
        accelerationText.setText(Double.toString(main.getValue("A")));
    }


}
