import java.util.HashMap;
import java.util.Map;

/**
 * general sound effects manager
 * @author Gary Larson
 * @version 5/10/2021
 */
public class SoundClipManager {
    private Map<Fx, AudioClip> clips;

    /**
     * constructs a sound manager
     */
    public SoundClipManager() {
        clips = new HashMap<>();
        clips.put(Fx.WIN, new AudioClip("sounds\\winner.wav"));
        clips.put(Fx.STRIKE, new AudioClip("sounds\\strike_out.wav"));
        clips.put(Fx.LOSS, new AudioClip("sounds\\elimination.wav"));
    }


    /**
     * returns a sound effect
     * @param soundFx desired sound effect
     * @return target sound effect
     */
    public AudioClip get(Fx soundFx) {
        return clips.get(soundFx);
    }


    /**
     * defines the different sounds/wav files
     */
    public enum Fx {
        WIN,
        STRIKE,
        LOSS
    }
}
