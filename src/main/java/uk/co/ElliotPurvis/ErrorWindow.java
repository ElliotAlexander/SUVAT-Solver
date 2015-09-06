package uk.co.ElliotPurvis;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Elliot on 06/09/2015.
 */
public class ErrorWindow extends JFrame{

    private final JTextPane errorLabel;
    private static String SYSTEM_FONT;
    private Window w;

    public ErrorWindow(String title, String errorMessage){
        super("Error! " + title);

        w = this;


        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        setSize(600, 100);
        setVisible(true);

        SYSTEM_FONT = new JLabel().getFont().getFontName();

        errorLabel = new JTextPane();
        // Order of arguements = new Insets(Top Inset, left Inset, bottom Inset, right);
        errorLabel.setMargin(new Insets(10, 40, 10, 40));
        errorLabel.setText(errorMessage);
        errorLabel.setSize(400, 50);
        add(errorLabel);

        errorLabel.setEditable(false);
        errorLabel.setCursor(null);
        errorLabel.setOpaque(false);

    }
}
