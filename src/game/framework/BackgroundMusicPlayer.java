package game.framework;

import game.framework.resources.Sounds;
import javafx.scene.media.MediaPlayer;

/**
 * Music player for background music.
 * Created by Jannes Peters on 2/25/2015.
 */
public class BackgroundMusicPlayer {

    private static BackgroundMusicPlayer instance;

    private static int currentPlayer = 0;

    public static boolean isPlaying() {
        return Sounds.backgroundMusic[currentPlayer].getStatus() == MediaPlayer.Status.PLAYING;
    }

    public static void play() {
        Sounds.backgroundMusic[currentPlayer].play();
    }

    public static void pause() {
        Sounds.backgroundMusic[currentPlayer].pause();
    }

    @SuppressWarnings("all")
    public static void stop() {
        Sounds.backgroundMusic[currentPlayer].stop();
    }

    public static void setVolume(float volume) {
        Sounds.backgroundMusic[currentPlayer].setVolume(volume);
    }
}
