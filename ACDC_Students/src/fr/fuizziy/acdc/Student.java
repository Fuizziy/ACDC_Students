package fr.fuizziy.acdc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap; 
import java.util.Map; 

public class Student {

	public static String[] penalty_desc = new String[]
			{ "EXC:", "ABS:", "RET:", "FLR:", "MVC:" };
	public static Map<String, Student> students_class = new HashMap<String, Student>(); 
	public static void load_students(File file) throws IOException {
		FileReader file_reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(file_reader); 
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] student_values = line.split(" ");
			Student student = new Student(student_values[0],
					Short.parseShort(student_values[1]),
					Short.parseShort(student_values[2]),
					student_values[3]);
			System.out.println("loaded " + student.format()
			+ ". Penalities: " + student.convert_penalties());
			
		} 
		bufferedReader.close();
		file_reader.close();
	}
	public static void save_students(File file) throws IOException {
		new PrintWriter(file).close();
		System.out.println("saving students...");
		FileWriter fw = new FileWriter(file.getName(), true); 
	    BufferedWriter bw = new BufferedWriter(fw); 
	    PrintWriter pw = new PrintWriter(bw);
	    for (Student student : students_class.values())
	    	student.save(pw);
	    pw.close();
	    bw.close();
	    fw.close();
	}
	
	public String login;
	private short asked_times, good_answers;
	public int[] penalties;
	
	public Student(String login, short asked_times, short good_answers,
			String str_penalty) {
		this.login = login;
		this.good_answers = good_answers; 
		this.asked_times = asked_times;
		this.penalties = parse_penalties(str_penalty);
		students_class.put(this.login, this);  
	}
	
	public void add_penalty(int i) {
		penalties[i]++;
	}
	
	public int[] parse_penalties(String str) {
		int[] result = new int[penalty_desc.length]; 
		int index = 0;
		for (String digit : str.replaceAll("[^,0-9]", "").split(",")){
			result[index++] = Integer.parseInt(digit);
		}
		return result;
	}
	
	public String convert_penalties() {
		String result = "[";
		for (int i = 0; i < penalties.length; i++)
			result += penalty_desc[i] + penalties[i] + ",";
		return result + "]";
	}
	
	public String format() {
		return this.login + " with " + good_answers + "/" +
	asked_times + " (" + (this.good_answers) * 100 
	/ (this.asked_times != 0 ? this.asked_times : 1) + "%)";
	}
	
	public void answer(boolean right) {
		if (right)
			this.good_answers++;
		this.asked_times++; 
	}
	
	public String toString() {
		String format = "";
		format += this.login + " ";
		format += this.asked_times + " ";
		format += this.good_answers + " "; 
		format += this.convert_penalties();
		return format;
	}
	
	public void save(PrintWriter pw) {
		System.out.println(this.toString());
		pw.println(this.toString());
	}
}
