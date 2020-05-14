import java.util.InputMismatchException;
import java.util.Scanner;

class Seats {
	private String[][] seats;

	public Seats() {
		seats = new String[3][10];
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = "---";
			}
		}
	}

	public String[][] getSeats() {
		return this.seats;
	}
}

class GetSeatGrade {
	public String getSeatGrade(Scanner scanner) {
		int seatGradeNum;
		System.out.print("좌석구분 S(1), A(2), B(3)>>");
		seatGradeNum = scanner.nextInt();
		switch (seatGradeNum) {
		case 1:
			return "S";
		case 2:
			return "A";
		case 3:
			return "B";
		default:
			return null;
		}
	}
}

class Reserve {
	private GetSeatGrade seatgrader;
	private String[][] seats;
	private String seatGrade;

	public Reserve(String[][] seats) {
		this.seats = seats;
	}

	public void makeReserve(Scanner scanner) {
		seatgrader = new GetSeatGrade();
		while (true) {
			try {
				this.seatGrade = seatgrader.getSeatGrade(scanner);
				this.reserver(scanner);
				break;
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요");
				scanner.nextLine();
				continue;
			} catch (NullPointerException e) {
				System.out.println("숫자 1~3 사이를 입력하세요");
				scanner.nextLine();
				continue;
			}
		}

	}

	private void getReserverInfo(int seatGrade, Scanner scanner, String name, int num) {
		for (int i = 0; i < seats[seatGrade].length; i++) {
			System.out.print(" " + seats[seatGrade][i]);
		}
		System.out.println();
		System.out.print("이름>>");
		name = scanner.next();
		System.out.print("번호>>");
		while (true) {
			try {
				num = scanner.nextInt();
				if (num >= 1 && num <= 10) {
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("숫자를 입력하세요");
			}
			scanner.nextLine();
			System.out.println("없는 번호입니다. 1~10 중 하나만 선택하세요.");
		}
		if (!seats[seatGrade][num - 1].equals("---")) {
			System.out.println("이미 예약된 좌석입니다.");
			return;
		}
		seats[seatGrade][num - 1] = name;
	}

	private void reserver(Scanner scanner) {
		String name = "";
		int num = 0;

		if (this.seatGrade.equals("S")) {
			System.out.print("S>>");
			this.getReserverInfo(0, scanner, name, num);
		} else if (this.seatGrade.equals("A")) {
			System.out.print("A>>");
			this.getReserverInfo(1, scanner, name, num);
		} else if (this.seatGrade.equals("B")) {
			System.out.print("B>>");
			this.getReserverInfo(2, scanner, name, num);
		}
	}

}

class Lookup {
	private String[][] seats;

	public Lookup(String[][] seats) {
		this.seats = seats;
	}

	public void lookupIt() {
		for (int i = 0; i < seats.length; i++) {
			if (i == 0)
				System.out.print("S>>");
			else if (i == 1)
				System.out.print("A>>");
			else
				System.out.print("B>>");
			for (int j = 0; j < seats[i].length; j++) {
				System.out.print(" " + seats[i][j]);
			}
			System.out.println();
		}
		System.out.println("<<<조회를 완료하였습니다.>>>");
	}
}

class Cancel {
	private GetSeatGrade seatgrader;
	private String[][] seats;
	private String seatGrade;

	public Cancel(String[][] seats) {
		this.seats = seats;
	}

	public void makeCancel(Scanner scanner) {
		seatgrader = new GetSeatGrade();
		while (true) {
			try {
				this.seatGrade = seatgrader.getSeatGrade(scanner);
				this.canceler(scanner);
				break;
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요");
				scanner.nextLine();
				continue;
			} catch (NullPointerException e) {
				System.out.println("숫자 1~3 사이를 입력하세요");
				scanner.nextLine();
				continue;
			}
		}
	}

	private void getCancelerInfo(String name, Scanner scanner, int seatGrade, String stringSeatGrade) {
		for (int i = 0; i < this.seats[seatGrade].length; i++) {
			if (!seats[seatGrade][i].equals("---")) {
				break;
			}
			if (i == this.seats[seatGrade].length - 1) {
				System.out.println(stringSeatGrade + "석에 예약된 좌석이 없습니다.");
				scanner.nextLine();
				return;
			}
		}
		System.out.print(stringSeatGrade + ">>");
		for (int i = 0; i < seats[seatGrade].length; i++) {
			System.out.print(" " + seats[seatGrade][i]);

		}
		System.out.println();
		System.out.print("이름>>");
		name = scanner.next();
		for (int i = 0; i < seats[seatGrade].length; i++) {
			if (seats[seatGrade][i].equals(name)) {
				seats[seatGrade][i] = "---";
				return;
			}
			if (i == seats[seatGrade].length - 1) {
				System.out.println("없는 이름입니다. 다시 입력하세요.");
				System.out.print("이름>>");
				name = scanner.next();
				i = 0;
			}
		}
	}

	private void canceler(Scanner scanner) {
		String name = "";

		if (this.seatGrade.equals("S")) {
			this.getCancelerInfo(name, scanner, 0, "S");
		} else if (this.seatGrade.equals("A")) {
			this.getCancelerInfo(name, scanner, 1, "A");
		} else if (this.seatGrade.equals("B")) {
			this.getCancelerInfo(name, scanner, 2, "B");
		}

	}
}

public class ReserveManager {
	private int menuNum;
	private Reserve reserve;
	private Lookup lookup;
	private Cancel cancel;

	private void menu(Scanner scanner) {
		System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
		this.menuNum = scanner.nextInt();
	}

	public void run(Scanner scanner) {
		Seats seats = new Seats();
		while (true) {
			try {
				this.menu(scanner);
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요");
				scanner.nextLine();
				continue;
			}
			if (this.menuNum == 4) {
				break;
			}
			switch (this.menuNum) {
			case 1:
				reserve = new Reserve(seats.getSeats());
				reserve.makeReserve(scanner);
				break;
			case 2:
				lookup = new Lookup(seats.getSeats());
				lookup.lookupIt();
				break;
			case 3:
				cancel = new Cancel(seats.getSeats());
				cancel.makeCancel(scanner);
				break;
			default:
				System.out.println("없는 메뉴입니다. 다시 입력하세요.");
				break;
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		ReserveManager manager = new ReserveManager();
		Scanner scanner = new Scanner(System.in);
		manager.run(scanner);
		scanner.close();
	}

}
