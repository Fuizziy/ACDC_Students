package fr.fuizziy.acdc;
  
import java.io.File;
import java.io.FileReader;
import java.io.IOException; 

public class Main {
	
	public static void main(String[] args) throws IOException { 
		System.out.println("WELCOME. How will you annoy the sups today ?");  
		File file = new File("scores.txt"); 
		if (file.createNewFile() || file.length() == 0) {
			System.out.println("Generating new scores file...");
			System.out.println("Please configure it and come back.");
			return;
		}
		Student.load_students(file); 
	} 
	 
}
