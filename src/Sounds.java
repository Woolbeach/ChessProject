import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

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
    public  void getAttackSound(){
        sound(attackSound);
    }
    public void getMoveSound(){
        sound(moveSound);
    }
    public void getBackgroundSound(){
        sound(backgroundSound);
    }

}
