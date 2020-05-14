//I had changed some

import java.util.Scanner;

class Dictionary {
	private static String[] kor = { "사랑", "아기", "돈", "미래", "희망" };
	private static String[] eng = { "love", "baby", "money", "future", "hope" };

	public static String kor2Eng(String word) {
		for (int i = 0; i < kor.length; i++) {
			if (word.equals(kor[i])) {
				return eng[i];
			}
		}
		return null;
	}
}

public class DicApp {
	public DicApp() {
		System.out.println("한영 단어 검색 프로그램입니다.");
	}

	public void search(Scanner scanner) {
		String searchWord;
		while (true) {
			System.out.print("한글 단어?");
			searchWord = scanner.next();
			if (Dictionary.kor2Eng(searchWord) != null)
				System.out.println(searchWord + "은 " + Dictionary.kor2Eng(searchWord));
			else if (searchWord.equals("그만")) {
				break;
			} else {
				System.out.println(searchWord + "는 저의 사전에 없습니다.");
			}
		}

	}

	public static void main(String[] args) {
		DicApp dicapp = new DicApp();
		Scanner scanner = new Scanner(System.in);
		
		dicapp.search(scanner);
		scanner.close();
	}
}
