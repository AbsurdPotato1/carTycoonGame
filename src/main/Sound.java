package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip; // contains audiofile
    URL[] soundURL = new URL[30]; // stores filepath of .wav file

    public Sound(){
        soundURL[0] = getClass().getClassLoader().getResource("sound/titlescreen-overdrive-matrika.wav"); // Absolute banger of background music
        soundURL[1] = getClass().getClassLoader().getResource("sound/copperPickUp.wav"); // copper pickup sound effect
        soundURL[2] = getClass().getClassLoader().getResource("sound/backgroundMusic.wav"); // Ethan's bng music
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
