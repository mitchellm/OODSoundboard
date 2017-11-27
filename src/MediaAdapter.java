
public class MediaAdapter implements MyMediaPlayer {
	AdvancedMediaPlayer myPlayer;
	public MediaAdapter(String audioType){
	      if (audioType.equalsIgnoreCase("wav")) {
	         myPlayer = new WavPlayer();
	      } else if (audioType.equalsIgnoreCase("au")){
	    	  myPlayer = new AuPlayer();
	      }
	   }
	@Override
	public void play(String audioType, String fileName) {
		if (audioType.equalsIgnoreCase("wav")) {
	         myPlayer.playWav(fileName);
		} else if (audioType.equalsIgnoreCase("au")){
	    	 myPlayer.playAu(fileName);
		}
	}

}
