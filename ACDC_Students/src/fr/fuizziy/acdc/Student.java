package fr.fuizziy.acdc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Student {

	public static Set<Student> students_class = new HashSet<Student>();
	
	public static void load_students(File file) throws IOException {
		FileReader file_reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(file_reader); 
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] student_values = line.split(" ");
			Student student = new Student(student_values[0],
					Short.parseShort(student_values[1]),
					Short.parseShort(student_values[2]));
			System.out.println(student.loadMessage());
		} 
		bufferedReader.close();
		file_reader.close();
	}
	
	String login;
	short asked_times = 0;
	short good_answers = 0;
	
	public Student(String login, short asked_times, short good_answers) {
		this.login = login;
		this.good_answers = good_answers;
		this.asked_times = asked_times;
		students_class.add(this);
	}
	
	public String loadMessage() {
		return "Loaded " + this.login + " with " + good_answers + "/" +
	asked_times + " (" + this.good_answers * 100 / this.asked_times + "%)";
	}
	
	public void answer(boolean right) {
		if (right)
			this.good_answers++;
		this.asked_times++; 
	}
	
	public void save() {
		
	}
}
