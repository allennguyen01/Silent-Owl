package main;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class GUI extends JFrame { //implements ActionListener, ChangeListener {

    JFrame frame = new JFrame("Silent Owl"); //Change the name

    // Text/Buttons that are always on screen
    JLabel title = new JLabel();
    JButton settings = new JButton();
    JButton graph = new JButton();
    Color BACKGROUND_COLOR = new Color(0x282828);

    // Settings
    JTextPane settingsTitle = new JTextPane();
    JTextPane dBInText = new JTextPane();
    JTextPane inputDevice = new JTextPane();
    JTextPane scheduled = new JTextPane();
    JTextField dBInput = new JTextField();
        //input device menu
        //scheduled

    // Screen 1
    JButton run = new JButton("Run");

    // Screen 2
    JButton stop = new JButton("Stop");


    // Program is not running
    public GUI() {
        // Title
        title.setText("SHUT THE FUCK UP"); //TODO: change later
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Barlow Condensed ExtraLight",Font.PLAIN,50));


        //settingsTitle
        settingsTitle.setText("Settings");
        settingsTitle.setFont(new Font("Barlow Condensed ExtraLight",Font.PLAIN,15));
        settingsTitle.setBackground(BACKGROUND_COLOR);
        settingsTitle.setForeground(Color.WHITE);
        settingsTitle.setBounds(1493, 755, 228, 70); //TODO: set the bounds

        //dBInText
        dBInText.setText("dB Limit");
        dBInText.setFont(new Font("Barlow Condensed ExtraLight",Font.PLAIN,15));
        dBInText.setBackground(BACKGROUND_COLOR);
        dBInText.setForeground(Color.WHITE);
        dBInText.setBounds(1381, 844, 291, 43); //TODO: set the bounds

        dBInText.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                dBInText.setEditable(true);
                dBInText.getCaret().setVisible(false);
            }

            @Override
            public void focusGained(FocusEvent e) {
                dBInText.setEditable(false);
                dBInText.getCaret().setVisible(false);
            }
        });

        //inputDevice
        inputDevice.setText("Input Device");
        inputDevice.setFont(new Font("Barlow Condensed ExtraLight",Font.PLAIN,15));
        inputDevice.setBackground(BACKGROUND_COLOR);
        inputDevice.setForeground(Color.WHITE);
        inputDevice.setBounds(1463, 890, 205, 41); //TODO: set the bounds

        //scheduled
        scheduled.setText("Scheduled");
        scheduled.setFont(new Font("Barlow Condensed ExtraLight",Font.PLAIN,15));
        scheduled.setBackground(BACKGROUND_COLOR);
        scheduled.setForeground(Color.WHITE);
        scheduled.setBounds(1492, 935, 178, 41); //TODO: set the bounds

        //Run button








        frame.setSize(1280, 720);
        frame.setResizable(false);

        frame.add(title);
        // ADD FRAMES HERE
        frame.setVisible(true);

        frame.getContentPane().setBackground(BACKGROUND_COLOR);
    }

    // Program is recording audio
    private void runningGUI() {

    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}
