package fr.fuizziy.acdc;
  
import java.io.File; 
import java.io.IOException;
import java.util.Scanner; 

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
		Scanner scanner = new Scanner(System.in);
		String cmd;
		System.out.print("Type 'help' for help & a list of commands."
				+ "\nCommand: ");
		while ((cmd = scanner.nextLine()) != "quit") {  
			System.out.println(cmd);
			String[] options = cmd.split(" "); 
			if (cmd.replaceAll(" ", "").length() == 0) {
				Commands.help();
				System.out.print("\nCommand: ");
				continue;
			}
			switch (options[0]) {
				case "quit":
					return; 
				case "sanction":
				case "s":
					break;
				default:
					System.out.println("Invalid command");
					break;
			}
			System.out.print("\nCommand: ");
		};
		scanner.close();
		Student.save_students(save);
	} 
	
	
	 
}
