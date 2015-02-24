package game.framework.resources;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sounds {

    public static MediaPlayer[] backgroundMusic = new MediaPlayer[1];

    public static void loadSounds() {
        //background
        backgroundMusic[0] = loadMusic("assets/sounds/music/tier1.mp3");


    }

    public static MediaPlayer loadMusic(String path) {
        System.out.println("Loading " + path + " ...");
        return new MediaPlayer(new Media(Paths.get(path).toUri().toString()));
    }

    public static AudioClip loadSound(String path) {
        System.out.println("Loading " + path + " ...");
        return new AudioClip(path);
    }

}
