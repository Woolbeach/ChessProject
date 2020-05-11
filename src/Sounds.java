import javax.sound.sampled.*;
import java.io.File;

public class Sounds {
    File backgroundSound = new File("ressources/background.wav");
    File attackSound = new File("ressources/BrickDamage.wav");
    File moveSound = new File("ressources/MoveBrick.wav");
    File winSound = new File("ressources/win.wav");
    Clip Background;
    Clip Attack;
    Clip Move;
    Clip Win;


    FloatControl sfx_controller1;
    FloatControl sfx_controller2;
    FloatControl sfx_controller3;
    FloatControl bgmusic_controller;

    Sounds() {
        Background = loadFile(backgroundSound, bgmusic_controller);
        Attack = loadFile(attackSound, sfx_controller1);
        Move = loadFile(moveSound, sfx_controller2);
        Win = loadFile(winSound, sfx_controller3);
        //bgmusic_controller.setValue(-30.0f);
        sfx_controller1 = (FloatControl) Attack.getControl(FloatControl.Type.MASTER_GAIN);
        sfx_controller2 = (FloatControl) Move.getControl(FloatControl.Type.MASTER_GAIN);
        sfx_controller3 = (FloatControl) Win.getControl(FloatControl.Type.MASTER_GAIN);
        bgmusic_controller = (FloatControl) Background.getControl(FloatControl.Type.MASTER_GAIN);
        bgmusic_controller.setValue(-20);
    }

    public Clip loadFile(File input, FloatControl controller) {
        try {
            Clip audioClip;
            FloatControl gainControl;

            File audioFile = input;

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            return audioClip;
        } catch (Exception e) {
            System.err.println("Error loading sound!");
        }
        return null;
    }

    public void playAttackSound() {
        System.out.println("played attack");
        Attack.setMicrosecondPosition(0);
        Attack.start();
    }

    public void playMoveSound() {
        System.out.println("played move");
        Move.setMicrosecondPosition(0);
        Move.start();
    }

    public void playWin() {
        System.out.println("Played win");
        Background.stop();
        Win.setMicrosecondPosition(0);
        try {
            Thread.sleep(500);
            Win.start();
        } catch (Exception e) {
            System.err.println("Error loading sound!");
        }
    }

    public void playBackGround() {
        Background.setMicrosecondPosition(0);
        Background.start();
        Background.loop(15);
    }

    public void setMusicvolume(int value) {
        bgmusic_controller.setValue(sliderToFloat(value));
    }

    public void setSfxvolume(int value) {
        float temp1 = sliderToFloat(value);
        sfx_controller1.setValue(temp1);
        sfx_controller2.setValue(temp1);
        sfx_controller3.setValue(temp1);
    }

    public float sliderToFloat(int slider_value) {
        //en slider giver værdier 0-100 det skal laves om til en float

        //finder først det totalle område der kan sættes lyd i, det går fra -80 til 6,xxx
        //float onestep = (-1 * sfx_controller1.getMinimum() + sfx_controller1.getMaximum()) / 100;
        float onestep = (-1 * -60.0f + sfx_controller1.getMaximum()) / 100;

        float volume = sfx_controller1.getMaximum() - ((100 - slider_value) * onestep);

        return volume;
    }
}
