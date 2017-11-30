public class MediaAdapter implements MyMediaPlayer {
	AdvancedMediaPlayer myPlayer;
	String fileName;
	String audioT;
	public MediaAdapter(String audioType, String name){
	      if (audioType.equalsIgnoreCase("wav")) {
	         myPlayer = new WavPlayer();
	      } else if (audioType.equalsIgnoreCase("au")){
	    	  myPlayer = new AuPlayer();
	      }
	      fileName = name;
	      audioT = audioType;
	   }
	@Override
	public void play() {
		if (audioT.equalsIgnoreCase("wav")) {
	         myPlayer.playWav(fileName);
		} else if (audioT.equalsIgnoreCase("au")){
	    	 myPlayer.playAu(fileName);
		}
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setVolume(double value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMute(boolean onOff) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRate(double value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBalance(double vaue) {
		// TODO Auto-generated method stub
		
	}

}
