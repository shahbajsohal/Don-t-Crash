import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	static Clip Audio;
	SoundEffect(){}
	static void PLAY(File SelectAudio) {
		try {
			Audio = AudioSystem.getClip();
			Audio.open(AudioSystem.getAudioInputStream(SelectAudio));
			Audio.start();
			Thread.sleep(Audio.getMicrosecondLength()/1000);
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	static void STOP() {
		Audio.stop();
		Audio.close();
	}
	public static void main (String [] args) {
		File TestAudio= new File("SoundAssets/mute city 3.5.wav");
		PLAY(TestAudio);
	}
}