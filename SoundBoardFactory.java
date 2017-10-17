
public class SoundBoardFactory {
	
	 
	   public SoundBoard getSoundboard(String soundboardType){
	      if(soundboardType == null){
	         return null;
	      }		
	      //types of soundboards
	      if(soundboardType.equalsIgnoreCase("Analog")){
	         return new Analog();
	         
	      } else if(soundboardType.equalsIgnoreCase("Digital")){
	         return new Digital();
	         
	      }  
	      
	      return null;
	   }
	}