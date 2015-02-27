package game.framework;

import game.framework.resources.Sounds;
import javafx.scene.media.MediaPlayer;

/**
 * Music player for background music.
 * Created by Jannes Peters on 2/25/2015.
 */
public class BackgroundMusicPlayer {
    private static int currentPlayer = 0;
    private static double volume = 0.5d;

    /**
     * Checks if the background music is running.
     * @return true if the music is currently playing.
     */
    public static boolean isPlaying() {
        return Sounds.backgroundMusic[currentPlayer].getStatus() == MediaPlayer.Status.PLAYING;
    }

    /**
     * Starts playing the music. If music was paused it resumes it.
     */
    public static void play() {
        Sounds.backgroundMusic[currentPlayer].setVolume(volume);
        Sounds.backgroundMusic[currentPlayer].play();
    }

    /**
     * Pauses the music playback.
     */
    public static void pause() {
        Sounds.backgroundMusic[currentPlayer].pause();
    }

    /**
     * Stops music playback and resets the position.
     */
    @SuppressWarnings("all")
    public static void stop() {
        Sounds.backgroundMusic[currentPlayer].stop();
    }

    /**
     * Sets the volume of the background music.
     * @param volume - double between 0 and 1
     */
    public static void setVolume(double volume) {
        BackgroundMusicPlayer.volume = volume;
        Sounds.backgroundMusic[currentPlayer].setVolume(volume);
    }

    /**
     * Returns the linear volume as a factor from 0d to 1d.
     * @return 0d-1d linear volume
     */
    @SuppressWarnings("all")
    public static double getVolume() {
        return volume;
    }
}
