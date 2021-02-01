import java.util.TreeMap;
import java.util.Scanner;

public class Main {
	String answer = "";
	int sellCount = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeMap<String, Integer> tm = new TreeMap<>();
		int repeatTime = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < repeatTime; i++) {
			String bookTitle = sc.nextLine();
			if (tm.containsKey(bookTitle)) tm.put(bookTitle, tm.get(bookTitle) + 1);
			else tm.put(bookTitle, 1);
		}
		
		Main boj = new Main();
		tm.forEach((key, value) -> {
			if (value > boj.sellCount) {
				boj.answer = key;
				boj.sellCount = value;
			}
		});
		
		System.out.println(boj.answer);
		
		sc.close();
	}
}
