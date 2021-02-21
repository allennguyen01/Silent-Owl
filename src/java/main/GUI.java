package main;

import com.sun.jdi.IntegerValue;

import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUI extends JFrame { //implements ActionListener, ChangeListener {

    JFrame frame = new JFrame("Silent Owl"); //Change the name

    // Text/Buttons that are always on screen
    JLabel title = new JLabel();
    JLabel owlImage;
    JButton settings = new JButton();
    JButton graph = new JButton();
    Color BACKGROUND_COLOR = new Color(0x282828);
    Color PINKY_RED_SALMON = new Color(0xFF7074);
    private boolean is_running;

    // Settings
    JTextPane settingsTitle = new JTextPane();
    JTextPane noise_threshold = new JTextPane();
    JButton threshold_up = new JButton();
    JButton threshold_down = new JButton();
    JTextPane inputDevice = new JTextPane();
    JTextPane scheduled = new JTextPane();
    JTextField dBInput = new JTextField();
    private boolean settings_open = false;
    //input device menu
    //scheduled

    // Screen 1
    JButton run = new JButton("Run");

    // Screen 2
    JButton stop = new JButton("Stop");
    JTextPane detecting_audio = new JTextPane();
    JTextPane noise_level = new JTextPane();


    // Program is not running
    public GUI() {
        initialize_frame();
        initialize_title();
        initialize_run_stop();
        initialize_settings();

        stoppedGUI();
        add_to_frame();
    }

    // Program is recording audio
    private void runningGUI() {

        Thread t1 = new Thread(){
            public void run(){
                soundDetect micAudio = new soundDetect();
                TargetDataLine audioLine = micAudio.getLine();
                while (is_running) {
                    byte[] bytes = new byte[audioLine.getBufferSize() / 5];
                    audioLine.read(bytes, 0, bytes.length);
                    System.out.println("RMS Level: " + soundDetect.rms(bytes));
                    String noise = String.valueOf(soundDetect.rms(bytes));
                    if(soundDetect.rms(bytes) > Integer.parseInt(noise_threshold.getText())){
                        noise_level.setForeground(PINKY_RED_SALMON);
                        noise_level.setText(noise);
                        try
                        {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("src/java/images/alert2.wav")));
                            clip.start();
                        }
                        catch (Exception exc)
                        {
                            exc.printStackTrace(System.out);
                        }
                    } else {
                        noise_level.setForeground(Color.WHITE);
                        noise_level.setText(noise);
                    }
                }
                audioLine.close();
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                stop.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (stop.isEnabled()) {
                            stop.setVisible(false);
                            stop.setEnabled(false);
                            run.setVisible(true);
                            run.setEnabled(true);
                            settings.setVisible(true);
                            settings.setEnabled(true);
                            detecting_audio.setVisible(false);
                            detecting_audio.setEnabled(false);
                            noise_level.setVisible(false);
                            noise_level.setEnabled(false);
                            is_running = false;
                            stoppedGUI();
                        }

                    }
                });
            }
        };
        t1.start();
        t2.start();

    }

    // Program is stopped
    private void stoppedGUI() {
        is_running = false;

        if(settings_open){
            close_settings();
        } else {
            open_settings();
        }
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (run.isEnabled()) {
                    run.setVisible(false);
                    run.setEnabled(false);
                    settings.setVisible(false);
                    settings.setEnabled(false);
                    stop.setVisible(true);
                    stop.setEnabled(true);
                    detecting_audio.setVisible(true);
                    detecting_audio.setEnabled(true);
                    noise_level.setVisible(true);
                    noise_level.setEnabled(true);
                    is_running = true;

                    runningGUI();
                }
            }
        });
    }

    private void open_settings(){
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settings.isEnabled()) {
                    noise_threshold.setVisible(true);
                    noise_threshold.setEnabled(true);
                    settings_open = true;
                }
            }
        });
    }

    private void close_settings(){
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settings.isEnabled()) {
                    noise_threshold.setVisible(false);
                    noise_threshold.setEnabled(false);
                    settings_open = false;
                }
            }
        });
    }


    private void initialize_run_stop(){
        //Run button
        run.setBounds(540,380,200, 90); //TODO: set the bounds
        run.setFocusable(false);
        run.setBackground(BACKGROUND_COLOR);
        run.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 40));
        run.setForeground(Color.WHITE);
        run.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        //stop button
        stop.setBounds(540,380,200, 90); //TODO: set the bounds
        stop.setFocusable(false);
        stop.setBackground(PINKY_RED_SALMON);
        stop.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 40));
        stop.setForeground(Color.BLACK);
        stop.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        detecting_audio.setText("Detecting Audio...");
        detecting_audio.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 20));
        detecting_audio.setBackground(BACKGROUND_COLOR);
        detecting_audio.setForeground(Color.WHITE);
        detecting_audio.setBounds(540,330,180, 50); //TODO: set the bounds

        noise_level.setText("0");
        noise_level.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 30));
        noise_level.setBackground(BACKGROUND_COLOR);
        noise_level.setForeground(Color.WHITE);
        noise_level.setBounds(635,510,90, 90); //TODO: set the bounds
    }

    private void initialize_title(){
        // Title
        title.setText("Silent Owl");
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 260));
    }

    private void initialize_settings(){
        //settingsTitle
        settingsTitle.setText("Settings");
        settingsTitle.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        settingsTitle.setBackground(BACKGROUND_COLOR);
        settingsTitle.setForeground(Color.WHITE);
        settingsTitle.setBounds(1493, 755, 228, 70); //TODO: set the bounds

        //dBInText
        noise_threshold.setText("53");
        noise_threshold.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 15));
        noise_threshold.setBackground(BACKGROUND_COLOR);
        noise_threshold.setForeground(Color.WHITE);
        noise_threshold.setBounds(1000, 600, 60, 60); //TODO: set the bounds

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

        //settings button
        ImageIcon settings_img = new ImageIcon("java/images/settings.png");
        settings.setText("Settings");
        settings.setIcon(settings_img);
        settings.setBounds(1100,600,80, 50); //TODO: set the bounds
        settings.setFocusable(false);
        settings.setBackground(BACKGROUND_COLOR);
        settings.setFont(new Font("Barlow Condensed ExtraLight", Font.PLAIN, 20));
        settings.setForeground(Color.WHITE);
        settings.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    private void initialize_frame(){
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
    }

    private void add_to_frame(){
        // ADD FRAMES HERE
        frame.add(run);
        frame.add(stop);
        frame.add(scheduled);
        frame.add(detecting_audio);
        detecting_audio.setVisible(false);
        detecting_audio.setEnabled(false);
        frame.add(settings);
        settings.setVisible(true);
        settings.setEnabled(true);
        frame.add(noise_level);
        noise_level.setVisible(false);
        noise_level.setEnabled(false);
        frame.add(noise_threshold);
        noise_threshold.setVisible(false);
        noise_threshold.setEnabled(false);

        ImageIcon img = new ImageIcon("src/java/images/Owl.png");
        owlImage = new JLabel(img);
        owlImage.setBounds(747, 115, 121, 194);
        frame.add(owlImage);
        owlImage.setVisible(true);
        owlImage.setEnabled(true);

        frame.add(title); // Keep this the last thing
        frame.setVisible(true);
    }

}
