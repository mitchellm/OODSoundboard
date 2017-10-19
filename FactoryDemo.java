
public class SoundBoardFactoryDemo {
	public static void main(String[] args) {
		
		//creates soundboards
	      SoundBoardFactory factory = new SoundBoardFactory();

	       
	      SoundBoard soundboard1 = factory.getSoundboard("Analog");

	       
	      soundboard1.play();

	      
	      SoundBoard soundboard2 = factory.getSoundboard("Digital");


	       
	      soundboard2.play();

	     
	   }
	}
