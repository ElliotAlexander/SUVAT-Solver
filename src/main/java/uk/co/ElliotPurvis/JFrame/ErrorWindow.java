package uk.co.ElliotPurvis.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by Elliot on 06/09/2015.
 */
public class ErrorWindow extends JFrame implements ActionListener {

    private final JTextPane errorLabel;
    private static String SYSTEM_FONT;
    private JButton closeButton;
    private final Window w;

    public ErrorWindow(String title, String errorMessage){
        super("Error! " + title);

        w = this;

        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        setSize(600, 125);
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

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setVisible(true);
        //closeButton.setMargin(new Insets(0,0,20,0));
        add(closeButton);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
