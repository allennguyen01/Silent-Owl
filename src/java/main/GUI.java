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
        title.setText("Silent Owl");
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 50));


        //settingsTitle
        settingsTitle.setText("Settings");
        settingsTitle.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        settingsTitle.setBackground(BACKGROUND_COLOR);
        settingsTitle.setForeground(Color.WHITE);
        settingsTitle.setBounds(1493, 755, 228, 70); //TODO: set the bounds

        //dBInText
        dBInText.setText("dB Limit");
        dBInText.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
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
        inputDevice.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        inputDevice.setBackground(BACKGROUND_COLOR);
        inputDevice.setForeground(Color.WHITE);
        inputDevice.setBounds(1463, 890, 205, 41); //TODO: set the bounds

        //scheduled
        scheduled.setText("Scheduled");
        scheduled.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        scheduled.setBackground(BACKGROUND_COLOR);
        scheduled.setForeground(Color.WHITE);
        scheduled.setBounds(1492, 935, 178, 41); //TODO: set the bounds

        //Run button
        run.setVerticalAlignment(JButton.CENTER);
        run.setHorizontalAlignment(JButton.CENTER);
        run.setSize(20, 20); //TODO: set the bounds
        run.setFocusable(false);
        run.setBackground(BACKGROUND_COLOR);
        run.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        run.setForeground(Color.WHITE);
        run.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (run.isEnabled()) {

                    runningGUI();
                }
            }
        });

package main;

import javax.sound.sampled.*;

/*public class soundDetect {
    AudioFormat micAudio = new AudioFormat(800, 0, 1, true, true);

    public AudioFormat getMicAudio() {
        return micAudio;
    }
}*/

public class soundDetect {

    private static TargetDataLine line;
    private static AudioFormat format;
    private static DataLine.Info info;

    public soundDetect() {
        // Open a TargetDataLine for getting microphone input & sound level
        line = null;
        format = new AudioFormat(8000, 0, 1, true, true);
        info = new DataLine.Info(TargetDataLine.class, format); //     format is an AudioFormat object
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("The line is not supported.");
        }

        // Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException ex) {
            System.out.println("The TargetDataLine is Unavailable.");
        }
    }

    protected static int rms(byte[] audioData) { // audioData might
        //be buffered data read from a data line
        long lSum = 0;
        for (int i = 0; i < audioData.length; i++)
            lSum = lSum + audioData[i];

        double dAvg = lSum / audioData.length;

        double sumMeanSquare = 0d;
        for (int j = 0; j < audioData.length; j++)
            sumMeanSquare = sumMeanSquare + Math.pow(audioData[j] - dAvg, 2d);

        double averageMeanSquare = sumMeanSquare / audioData.length;
        return (int) (Math.pow(averageMeanSquare, 0.5d) + 0.5);
    }
}

public static void main(String[] args) {

    soundDetect test = new soundDetect();
    Timer t = new Timer(); // I used a timer here, code is below
    while (t.seconds < 2) {
        byte[] bytes = new byte[soundDetect.line.getBufferSize() / 5];
        soundDetect.line.read(bytes, 0, bytes.length);
        System.out.println("RMS Level: " + rms(bytes));
    }

}



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.setSize(1280, 720);
        frame.setResizable(false);

        //frame.setLayout(new GridLayout());
        frame.add(title);
        //frame.add(run);
        //frame.add(dBInput);
        // ADD FRAMES HERE
        frame.setVisible(true);


    }

    // Program is recording audio
    private void runningGUI() {

    }

}