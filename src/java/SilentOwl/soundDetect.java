package SilentOwl;

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
        format = new AudioFormat(44100, 16, 2, true, true);
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
    
    public TargetDataLine getLine() {
        return line;
    }

    public static int rms(byte[] audioData) { // audioData might
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

    public static int peak(byte[] audioData) {
        int peak = 0;

        for (int i = 0; i < audioData.length; i++) {
            if (audioData[i] > peak) {
                peak = audioData[i];
            }
        }
        return peak;
    }

    public static void printByte(byte[] audioData) {
        for (int i = 0; i < audioData.length; i++) {
            System.out.println(audioData[i]);
        }
    }

    public static void main(String[] args) {

        soundDetect test = new soundDetect();
        Timer t = new Timer(); // I used a timer here, code is below
        while (t.seconds < 2) {
            byte[] bytes = new byte[line.getBufferSize() / 5];
            line.read(bytes, 0, bytes.length);
            System.out.println("RMS Level: " + rms(bytes));
        }
    }
}


