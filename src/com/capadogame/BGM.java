package com.capadogame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {
	private File fileIn;
	private AudioInputStream audioIn;
	private Clip clip;
	
	public BGM() {
		try {
			fileIn = new File("C:/Users/Hp/git/CapadoGame/res/bgm.wav");
			audioIn = AudioSystem.getAudioInputStream(fileIn);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException e1) { 
			// do nothing
		} catch (IOException e1) { 
			// do nothing
		} catch (LineUnavailableException e1) { 
			// do nothing
		}
		
	}
	
	public void play(){
        clip.setFramePosition(0);  // rewind
        clip.start();
        loop();
    }
	
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
       clip.stop();
    }
    
    public void close() {
    	clip.close();
    }
    
}
