import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class Sound  {
    Clip clip;
    public Sound(String filepath) {
        File soundFx;
        try {
          soundFx = new File(filepath);
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFx);
          clip = AudioSystem.getClip();
          clip.open(audioIn);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void play(){
        clip.start();
        try {
            while(!clip.isRunning()){
                Thread.sleep(3);
            }
            while(clip.isRunning()) {
                Thread.sleep(3);
            }
            clip.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
