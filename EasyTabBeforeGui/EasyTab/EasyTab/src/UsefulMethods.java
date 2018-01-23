import java.util.*;

/**
 * A utilities class, to be accessed STATICALLY
 * 
 * @author Adam Miller 950 251 234
 *
 */
public class UsefulMethods {
	/**
	 * Checks if an int is a power of two
	 * 
	 * @param n
	 *            the int to be checked
	 * @return true if the number is a power of 2
	 */
	public static boolean isPowerOf2(final int n) {
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;
	}

	/**
	 * A scanner to receive input
	 */
	static Scanner s = new Scanner(System.in);

	/**
	 * prints out pairings, given the brackets
	 * 
	 * @param brackets
	 *            the pairings to be printed
	 */
	public static void printPairings(Team[][] brackets, ArrayList<String> rooms) {
		System.out.println("Room" + "\t\t\t\t" + "Prop" + "\t\t\t\t" + "Opp");
		for (int i = 0; i < brackets.length; i++) {
			System.out.print(rooms.get(i) + "\t\t\t\t");
			for (int j = 0; j < 2; j++) {
				System.out.print(brackets[i][j].getTeamcode() + "\t\t\t\t");

			}
			System.out.println();
		}
	}

	/**
	 * Checks a yes/no
	 * 
	 * @return true is the user inputs y, false if the user inpus n
	 */
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

	/**
	 * returns a random number between low and hi
	 * 
	 * @param low
	 *            the low bound
	 * @param hi
	 *            the high bound
	 * @return the random number
	 */
	public static int getRand(int low, int hi) {
		return (int) (Math.random() * (hi - low + 1));
	}
}
