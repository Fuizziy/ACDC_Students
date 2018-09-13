package fr.fuizziy.acdc;
  
import java.io.File; 
import java.io.IOException; 

public class Main {
	
	public static File save;
	
	public static void main(String[] args) throws IOException { 
		System.out.println("WELCOME. How will you annoy the sups today ?");  
		save = new File("scores.txt"); 
		if (save.createNewFile() || save.length() == 0) {
			System.out.println("Generating new scores file...");
			System.out.println("Please configure it and come back.");
			return;
		}
		Student.load_students(save); 
		Student.save_students(save);
	} 
	
	
	 
}
