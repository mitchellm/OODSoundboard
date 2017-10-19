
public class FactoryPatternDemo {
	public static void main(String[] args) {
		
		//creates soundboards
	      SFactory factory = new SFactory();

	       
	      Actions soundboard1 = factory.getSoundboard("Analog");

	       
	      soundboard1.play();

	      
	      Actions soundboard2 = factory.getSoundboard("Digital");


	       
	      soundboard2.play();

	     
	   }
	}
