package game.framework.resources;

import game.framework.screens.menu.SplashLoadScreen;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Class that loads and holds all sounds.
 */
public class Sounds {
    //TODO implement proper backgorund music play
    public static final int ELEMENTS = 7;
    public static MediaPlayer[] backgroundMusic = new MediaPlayer[2];

    //shots
    public static AudioClip akSingle;
    public static AudioClip akMulti;
    public static AudioClip artillery;
    public static AudioClip gun;
    public static AudioClip rocketLauncher;

    public static void loadSounds() {
        //background
        backgroundMusic[0] = loadMusic("assets/sounds/music/tier1.wav");
        backgroundMusic[1] = loadMusic("assets/sounds/music/tier2.mp3");

        for (MediaPlayer player : backgroundMusic) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
        }

        //shots
        akSingle = loadSound("assets/sounds/shots/ak_single.wav");
        akMulti = loadSound("assets/sounds/shots/ak_multi.wav");
        artillery = loadSound("assets/sounds/shots/artillery.wav");
        gun = loadSound("assets/sounds/shots/gun.wav");
        rocketLauncher = loadSound("assets/sounds/shots/rocket_launcher.wav");
    }

    public static MediaPlayer loadMusic(String path) {
        System.out.println("Loading " + path + " ...");
        SplashLoadScreen.elementLoaded();
        return new MediaPlayer(new Media(Paths.get(path).toUri().toString()));
    }

    public static AudioClip loadSound(String path) {
        System.out.println("Loading " + path + " ...");
        SplashLoadScreen.elementLoaded();
        return new AudioClip(Paths.get(path).toUri().toString());
    }

}
