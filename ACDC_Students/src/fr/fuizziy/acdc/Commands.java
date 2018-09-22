package fr.fuizziy.acdc;

public class Commands {

	public enum Sanction{
		KICKED_OUT(1),
		MISSING(2),
		LATE(3),
		FAIL_PUSH(4), 
		BEHAVIOR(5);
		
		int index;
		
		Sanction(int index){
			this.index = index;
		}
		
		String print() {
			return this.index + "->" + this.name();
		}
		
		void apply(String login) {
			if (!Student.students_class.containsKey(login)) {
				System.out.println("This student does not exist...");
				return;
			}
			Student student = Student.students_class.get(login);
			student.add_penalty(index - 1);
			System.out.println("Now has: " + student.convert_penalties());
		}
		 
		static void print_all() {
			for (int i = 0; i < Student.penalty_desc.length; i++)
				System.out.println(Sanction.values()[i].print());
		}
	}
	
	public static void help() {
		System.out.println("--- HELP ---");
		System.out.println("ACDC 2021, sups tracker by Antoine Allard");
		System.out.println("Arguments:"); 
		System.out.println("quit : exits the software");  
		System.out.println("help : displays these informations");  
	}
	
	public static void apply_sanction(String[] args) {
		int id_sanction = Integer.parseInt(args[2]) - 1;
		if (id_sanction > Student.penalty_desc.length) {
			System.out.println("index error. Please refer to the list above.");
			return;
		}
		Sanction.values()[id_sanction].apply(args[1]);
	}
}
