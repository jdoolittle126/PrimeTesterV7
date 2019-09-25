import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class TalkBot {
	private static HashMap<String, Integer> knownWordsByType = new HashMap<String, Integer>();
	private static HashMap<String, String> knownWordsBySyn = new HashMap<String, String>();
	private static ArrayList<String> words = new ArrayList<String>();
	private static ArrayList<String> response = new ArrayList<String>();
	private static ArrayList<Integer> responseType = new ArrayList<Integer>();
	private static ArrayList<Integer> wordsByType = new ArrayList<Integer>();
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		System.out.println("Hello, I am a test bot!");
		convoLoop();
	}
	
	public static void intake(String s){
		s = s.trim();
		s = s.toLowerCase();
		while(s.indexOf(" ") != -1){
			words.add(s.substring(0, s.indexOf(" ")));
			s = s.substring(s.indexOf(" ")+1, s.length());
		}
		define();
	}
	
	public static void define(){
		for(String word : words){
			if(! knownWordsBySyn.containsKey(word)) askQuestionS(word);
			if(! knownWordsByType.containsKey(word)) askQuestionT(word);
			else classifyByType();
		}
	}
	
	public static void askQuestionS(String word){
		System.out.println("Is there a synonym for this word? :" + word);
		knownWordsBySyn.put(word, getInput());
	}
	public static void askQuestionT(String word){
		System.out.println("What type of word is this? :" + word + "\n0 for filler, 1 for noun, 2 greeting, 3 farewell, 4 +, 5 -");
		knownWordsByType.put(word, Integer.valueOf(getInput()));
	}
	
	public static void classifyByType(){
		Iterator<String> iterator = words.iterator();
		while(iterator.hasNext())
		{
			String string = iterator.next();
			if(Integer.valueOf(knownWordsByType.get(string)) == 0) words.remove(string);
			else responseType.add(Integer.valueOf(knownWordsByType.get(string)));
		}
	}
	
	public static void respond(){
		//for()
	}
	
	public static String getInput(){
		return scanner.nextLine();
	}
	
	public static void convoLoop(){
		while(true){
			intake(getInput());
			respond();
			words.clear();
		}
	}
	
}
