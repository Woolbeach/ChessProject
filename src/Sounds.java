import javax.sound.sampled.*;
import java.io.File;

public class Sounds {
    //initialiserer lydfilerne og opretter Clip objekter
    File backgroundSound = new File("ressources/background.wav");
    File attackSound = new File("ressources/BrickDamage.wav");
    File moveSound = new File("ressources/MoveBrick.wav");
    File winSound = new File("ressources/win.wav");
    Clip Background;
    Clip Attack;
    Clip Move;
    Clip Win;

    //opretter controllers til senere at styre lydstyrke
    FloatControl sfx_controller1;
    FloatControl sfx_controller2;
    FloatControl sfx_controller3;
    FloatControl bgmusic_controller;

    Sounds() {
        //definerer Clip som specifikke indlæste lydfiler, samt opretter volumen controllers til samme Clip
        Background = loadFile(backgroundSound, bgmusic_controller);
        Attack = loadFile(attackSound, sfx_controller1);
        Move = loadFile(moveSound, sfx_controller2);
        Win = loadFile(winSound, sfx_controller3);
        sfx_controller1 = (FloatControl) Attack.getControl(FloatControl.Type.MASTER_GAIN);
        sfx_controller2 = (FloatControl) Move.getControl(FloatControl.Type.MASTER_GAIN);
        sfx_controller3 = (FloatControl) Win.getControl(FloatControl.Type.MASTER_GAIN);
        bgmusic_controller = (FloatControl) Background.getControl(FloatControl.Type.MASTER_GAIN);
        bgmusic_controller.setValue(-20);
    }


    //denne method opsætter samtlige lydklip korrekt, så de kan benyttes med controllers og afspilles
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

    //afspiller attack lydfilen fra start
    public void playAttackSound() {
        Attack.setMicrosecondPosition(0);
        Attack.start();
    }

    //afspiller move lydfilen fra start
    public void playMoveSound() {
        Move.setMicrosecondPosition(0);
        Move.start();
    }

    //afspiller win lydfilen fra start, venter på attack lydfilen før den afspilles
    public void playWin() {
        Win.setMicrosecondPosition(0);
        try {
            Thread.sleep(500);
            Win.start();
        } catch (Exception e) {
            System.err.println("Error loading sound!");
        }
    }

    //afspiller background lydfilen fra start og looper 15 gange
    public void playBackGround() {
        Background.setMicrosecondPosition(0);
        Background.start();
        Background.loop(15);
    }

    //vores metode til at sætte musik volumen vha. metoden sliderToFloat til at formatere input fra int til float
    public void setMusicVolume(int value) {
        bgmusic_controller.setValue(sliderToFloat(value));
    }

    //metode til at sætte lydeffekter volumen vha. metoden sliderToFloat til at formatere input fra int til float
    public void setSFXVolume(int value) {
        float temp1 = sliderToFloat(value);
        sfx_controller1.setValue(temp1);
        sfx_controller2.setValue(temp1);
        sfx_controller3.setValue(temp1);
    }

    public float sliderToFloat(int slider_value) {
        //en slider giver værdier 0-100 det skal laves om til en float

        //finder først det totale område der kan sættes lyd i, det går fra -80 til 6,xxx
        float onestep = (-1 * -60.0f + sfx_controller1.getMaximum()) / 100;

        float volume = sfx_controller1.getMaximum() - ((100 - slider_value) * onestep);

        return volume;
    }
}
