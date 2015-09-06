package uk.co.ElliotPurvis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;


/**
 * Created by Elliot on 16/08/2015.
 */
public class MainWindow extends JFrame implements ActionListener {

    private final JLabel titleMain;
    private final JTextPane subtitle, postTitle;
    private final JButton calculateButton, resetButton;
    private final JTextField accelerationText, velocityText, initialVText, timeText, distanceText;

    private final Main main;

    private static String SYSTEM_FONT;

    public MainWindow(Main main) {
        super("SUVAT Solver");


        this.main = main;

        SYSTEM_FONT = new JLabel().getFont().getFontName();

        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        setSize(400, 300);


        titleMain = new JLabel("SUVAT Calculator");
        titleMain.setSize(900, 50);
        titleMain.setFont(new Font(SYSTEM_FONT, Font.BOLD, 25));
        add(titleMain);


        subtitle = textAreaProperties(new JTextPane());
        subtitle.setText("This program will take a set of values, and calculate the unknowns.");
        subtitle.setSize(400, 50);
        add(subtitle);


        setVisible(true);


        accelerationText = new JTextField("Acceleration");
        accelerationText.setPreferredSize(new Dimension(100, 30));
        accelerationText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                accelerationText.setText("");
            }
        });
        add(accelerationText);

        velocityText = new JTextField("Final velocity");
        velocityText.setPreferredSize(new Dimension(100, 30));
        velocityText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                velocityText.setText("");
            }
        });
        add(velocityText);

        initialVText = new JTextField("Initial velocity");
        initialVText.setPreferredSize(new Dimension(100, 30));
        initialVText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                initialVText.setText("");
            }
        });
        add(initialVText);

        distanceText = new JTextField("Distance");
        distanceText.setPreferredSize(new Dimension(100, 30));
        distanceText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                distanceText.setText("");
            }
        });
        add(distanceText);

        timeText = new JTextField("Time");
        timeText.setPreferredSize(new Dimension(100, 30));
        timeText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                timeText.setText("");
            }
        });
        add(timeText);

        postTitle = textAreaProperties(new JTextPane());


        //  new Insets(Top, left, bottom, right);
        postTitle.setMargin(new Insets(10, 30, 10, 30));

        postTitle.setText("Enter the above values and press calculate");
        postTitle.setSize(400, 50);
        add(postTitle);


        calculateButton = new JButton("Calculate : ");
        calculateButton.setVisible(true);
        calculateButton.addActionListener(this);
        add(calculateButton);

        resetButton = new JButton("Reset : ");
        resetButton.addActionListener(this);
        resetButton.setVisible(true);
        add(resetButton);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {

            HashMap<String, String> tempValues = new HashMap<String, String>();
            tempValues.put("V", velocityText.getText().toString());
            tempValues.put("S", distanceText.getText().toString());
            tempValues.put("U", initialVText.getText().toString());
            tempValues.put("T", timeText.getText().toString());
            tempValues.put("A", accelerationText.getText().toString());

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

            // Pass the parse values to our main class to be calculated.
            main.calculate();
            System.out.print("Finished calculating. Updating text boxes.");
            updateTextBoxes();

        } else if(e.getSource() == resetButton){
            velocityText.setText("Final Velocity:");
            initialVText.setText("Initial Velocity:");
            distanceText.setText(("Distance:"));
            accelerationText.setText("Acceleration:");
            timeText.setText("Time:");
        }
    }

    private JTextPane textAreaProperties(JTextPane textArea) {
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        return textArea;
    }

    public void updateTextBoxes(){
        velocityText.setText(Double.toString(main.getValue("V")));
        initialVText.setText(Double.toString(main.getValue("U")));
        distanceText.setText(Double.toString(main.getValue("U")));
        timeText.setText(Double.toString(main.getValue("T")));
        accelerationText.setText(Double.toString(main.getValue("A")));
    }


}
