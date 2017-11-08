import java.util.*;

public class UsefulMethods {
	static Scanner s = new Scanner(System.in);

	public static void printPairings(Team[][] brackets) {
		System.out.println("Room" + "\t" + "Prop" + "\t" + "Opp");
		for (int i = 0; i < brackets.length; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < 2; j++) {
				System.out.print(brackets[i][j].teamcode + "\t");

			}
			System.out.println();
		}
	}

	public static boolean yn() {
		String result = s.nextLine();

		if (result.equals("y")) {
			return true;
		} else if (result.equals("n")) {
			return false;
		} else
			while (!result.equals("n") && !result.equals("y")) {
				System.out.println("Please enter y or n");
				result = s.nextLine();
				if (result.equals("y")) {
					return true;
				} else if (result.equals("n")) {
					return false;
				}
			}
		return false;
	}

	public static int getRand(int low, int hi) {
		return (int) (Math.random() * (hi - low + 1));
	}
}
