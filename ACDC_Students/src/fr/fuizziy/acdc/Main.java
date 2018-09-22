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
		Questions.Quest.instanciate();
		Student.load_students(save);   
		Scanner scanner = new Scanner(System.in);
		String cmd;
		System.out.print("Type 'help' for help & a list of commands."
				+ "\nCommand: ");
		loop_scanner:
		while ((cmd = scanner.nextLine()) != "quit") {  
			String[] options = cmd.split(" "); 
			if (cmd.replaceAll(" ", "").length() == 0) {
				Commands.help();
				System.out.print("\nCommand: ");
				continue; 
			} 
			switch (options[0]) {
				case "save": 
					Student.save_students(save);
					break;
				case "quit":
					break loop_scanner;  
				case "help":
				case "h":
					Commands.help();
					break;
				case "sanction":
				case "s":
					if (options.length != 3) {
						System.out.println("format: sanction login 1-"
											+ Student.penalty_desc.length);
						Commands.Sanction.print_all();
						break;
					} 
					Commands.apply_sanction(options);
					break;
				case "q":
				case "question":
					if (options.length == 3) {
						Student.students_class.get(options[1]).answer(options[2].equals("ok"));
						System.out.println(options[2].equals("ok") ? "Answered RIGHT !" : "Answered WRONG");
						System.out.println(Student.students_class.get(options[1]).format());
					} else {
						Questions.Quest.generate(); 
					}
					break;
				case "format":
					if (options.length == 2 && Student.students_class.containsKey(options[1]))
						System.out.println(Student.students_class.get(options[1]).format());
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
