package fr.fuizziy.acdc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import java.util.stream.Collectors;

public class Questions { 
	 
	
	static List<Integer> list = new ArrayList<Integer>();
	
	public static enum Quest{
		
		Q1("Quelle est le sens de la voiture ?", "tagF"),
		Q2("Quel est le modèle de la Peugeot ?", "vicieux"),
		Q3("Pourquoi le tableau ?", "ahah"),
		Q4("Combien de poils sur Ambroise ?", "pute"),
		Q5("Le Girondin, une région ?", "ahah"),
		Q6("En quoi Berlin agit-il comme un microcosme de la Guerre froide entre 1951 et 1954, au sortir de la seconde guerre mondiale ?", "ahah"),;
		
		String question;
		String vice;
		
		Quest(String question, String vice) {
			this.question = question;
			this.vice = vice;
		}
		
		void ask() {
			Student asked = get_random_student();
			System.out.println("[" + this.vice + "] " + asked.login + " -> " + question); 
		}
		
		static void instanciate() {
			for (int i = 0; i < Quest.values().length; i++)
				list.add(i);
		}
		
		static void generate() {
			Random rand = new Random();
			int n;
			int size = list.size();
			System.out.println(size + " questions loaded;");
			for (int i = 0; i < size; i++)
			{
				n = list.remove(rand.nextInt(list.size()));
				Quest.values()[n].ask();
			} 
		}
		
	}
	
	public static Student get_random_student() {
		Random rand = new Random();
		int n = rand.nextInt(Student.students_class.size());
		return Student.students_class.entrySet().stream()
				.map(p -> p.getValue()).collect(Collectors.toList()).get(n);
	}
	
}
