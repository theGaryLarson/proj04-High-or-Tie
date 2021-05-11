import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class AudioClip {
    Clip clip;
    AudioInputStream audioIn;
    public AudioClip(String filepath) {
        File soundFx;
        try {
          soundFx = new File(filepath);
          audioIn = AudioSystem.getAudioInputStream(soundFx);
          clip = AudioSystem.getClip();
          clip.open(audioIn);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void play(){ ;
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
            e.printStackTrace();
        }

    }
}
