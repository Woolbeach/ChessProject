import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    File backgroundSound = new File("ressources/background.wav");
    File attackSound = new File("ressources/BrickDamage.wav");
    File moveSound = new File("ressources/MoveBrick.wav");

    public static void sound(File input) {
        try {
            Clip clip = AudioSystem.getClip();
            //Loads in the designated sound input
            clip.open(AudioSystem.getAudioInputStream(input));
            //Starts the clip
            clip.start();
        } catch (Exception e) {
        }
    }

    public static void play_a_sound(File input, int slidervalue){
        try {
            Clip audioClip;
            FloatControl gainControl;

            File audioFile = input;

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);


            float onestep = (gainControl.getMaximum() - gainControl.getMinimum()) / 100;


            float volume = -1 * (100 - slidervalue) * onestep;

            System.out.println("minimum: " + gainControl.getMinimum() + " max: " + gainControl.getMaximum() + " onestep: " + onestep +" sliderval: "+slidervalue+" newvol: " + volume);

            gainControl.setValue(volume); // Reduce volume by 10 decibels.
            audioClip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound!");
        }

    }


    public void getAttackSound(int slidervalue) {
        play_a_sound(attackSound, slidervalue);
        //sound(attackSound);
    }

    public void getMoveSound(int slidervalue) {
        play_a_sound(moveSound, slidervalue);
        //sound(moveSound);
    }

    public void getBackgroundSound(int slidervalue) {
        //sound(backgroundSound);
        play_a_sound(backgroundSound, slidervalue);
    }

}
