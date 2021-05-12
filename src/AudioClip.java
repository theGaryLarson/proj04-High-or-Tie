import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

/**
 * represents a audio file for SFX
 * @author Gary Larson
 * @version 5/10/2021
 */
public class AudioClip {
    /** holds our audio for playback */
<<<<<<< HEAD
    private Clip clip;
    /** transfers our wave file into a stream */
    private AudioInputStream audioIn;
=======
<<<<<<< HEAD
<<<<<<< HEAD
    private Clip clip;
    /** transfers our wave file into a stream */
    private AudioInputStream audioIn;
=======
    Clip clip;
    /** transfers our wave file into a stream */
    AudioInputStream audioIn;
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
=======
    Clip clip;
    /** transfers our wave file into a stream */
    AudioInputStream audioIn;
>>>>>>> 3ff1ded26cfd2bfedb5020c940befeecf3541616
>>>>>>> 62d784686d4c4c06e9392ebf5d3e12532efa4eeb

    /**
     * constructs an audio clip
     * @param filepath path to sound file; (.wav format)
     */
    public AudioClip(String filepath) {
        File soundFx;
        try {
          soundFx = new File(filepath);
          audioIn = AudioSystem.getAudioInputStream(soundFx);
          clip = AudioSystem.getClip();
          clip.open(audioIn);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
           //do nothing
        }
    }

    /**
     * plays the sound
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
        try {
            while (!clip.isRunning()){
                Thread.sleep(1);
            }
            while (clip.isRunning()) {
                Thread.sleep(1);
            }

        }
        catch(Exception e) {
           //do nothing
        }

    }
}
