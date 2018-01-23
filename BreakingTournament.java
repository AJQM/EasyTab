import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BreakingTournament implements Breaking {
	private int breakRounds;
	private ArrayList<Team> originalBreak;
	private ArrayList<Team> breakingTeams;
	private int CONSTANT = 1;
	private ArrayList<String> roomsList;

	public BreakingTournament(ArrayList<String> roomsList) {
		this.roomsList = roomsList;
	}

	@Override
	public int whosBreaking(ArrayList<Team> teamsList) {
		// TODO Auto-generated method stub
		int amountTeamsBreaking = 0;
		while (CONSTANT == 1) {
			try {
				@SuppressWarnings("resource")
				Scanner s = new Scanner(System.in);

				amountTeamsBreaking = s.nextInt();
				if (!UsefulMethods.isPowerOf2(amountTeamsBreaking)) {
					System.out.println("Please re-enter the amount of teams Breaking. It must be a power of 2");
					amountTeamsBreaking = s.nextInt();
				}
				if (amountTeamsBreaking > teamsList.size())
					throw new ArrayIndexOutOfBoundsException();

				break;
			} catch (InputMismatchException e) {
				System.out.println("Please re-enter the amount of teams breaking. It must be an integer");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(
						"Please re-enter the amount of teams breaking. \nIt must be less than or equal to the total amount of teams at this tournament");
			}
		}

		ArrayList<Team> theBreak = new ArrayList<Team>();
		for (int i = 1; i <= amountTeamsBreaking; i++) {
			Team t = teamsList.get(teamsList.size() - i);
			t.setBreakPosition(i);
			theBreak.add(t);
		}
		setOriginalBreak(theBreak);
		setBreakingTeams(theBreak);
		System.out.println("The Break!");
		for (Team t : theBreak) {
			System.out.print(theBreak.indexOf(t) + 1);
			System.out.print("\t");
			System.out.println(t);
		}
		setBreakRounds((int) (1.4427 * Math.log(amountTeamsBreaking)));
		return getBreakRounds();
	}

	public Team[][] breakRound() {
		Team[][] brackets = new Team[breakingTeams.size() / 2][2];
		for (int i = 0, index = 0; i < brackets.length; i++, index += 2) {
			brackets[i][0] = breakingTeams.get(index);
			brackets[i][1] = breakingTeams.get(breakingTeams.size() - 1 - index);
		}
		UsefulMethods.printPairings(brackets, roomsList);
		return brackets;
	}

	@Override
	public void breakResults(Team[][] pairings) {
		for (int i = 0; i < pairings.length; i++) {
			System.out.println("For room " + (i + 1) + ", Did team " + pairings[i][0] + " (0) or team " + pairings[i][1]
					+ " (1) win?");
			int m = 1;
			while (m == 1) {
				try {
					int q = 0;
					while (CONSTANT == 1) {

						try {
							@SuppressWarnings("resource")
							Scanner d = new Scanner(System.in);
							d.nextLine();
							q = d.nextInt();
							break;
						} finally {

						}
					}
					if (q == 0) {

						if (pairings[i][0].getBreakPosition() > pairings[i][1].getBreakPosition()) {
							int index = breakingTeams.indexOf(pairings[i][1]);
							Team t = pairings[i][0];
							breakingTeams.remove(t);
							breakingTeams.set(index, t);
							breakingTeams.get(index).setBreakPosition(index + 1);
						} else {
							breakingTeams.remove(pairings[i][1]);

						}
						// d.close();
						break;
					} else if (q == 1) {
						if (pairings[i][1].getBreakPosition() > pairings[i][0].getBreakPosition()) {
							int index = breakingTeams.indexOf(pairings[i][0]);
							Team t = pairings[i][1];
							breakingTeams.remove(t);
							breakingTeams.set(index, t);
							breakingTeams.get(index).setBreakPosition(index + 1);

						} else {
							breakingTeams.remove(pairings[i][0]);

						}
						break;
					} else {
						// d.close();
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter either 1 or 0");
				} catch (InputMismatchException e) {
					System.out.println("Please enter either 1 or 0");

				} finally {
				}
			}
		}

	}

	@Override
	public ArrayList<Team> getOriginalBreak() {
		return originalBreak;
	}

	@Override
	public ArrayList<Team> getBreakingTeams() {
		return breakingTeams;
	}

	public int getBreakRounds() {
		return breakRounds;
	}

	public void setBreakRounds(int breakRounds) {
		this.breakRounds = breakRounds;
	}

	public void setOriginalBreak(ArrayList<Team> originalBreak) {
		this.originalBreak = originalBreak;
	}

	public void setBreakingTeams(ArrayList<Team> breakingTeams) {
		this.breakingTeams = breakingTeams;
	}

	/**
	 * test harness
	 */
	public static void main(String[] args) {
		// BreakingTournament t = new BreakingTournament();
		// ArrayList<Team> breakingTeams0 = new ArrayList<Team>();
		// breakingTeams0.add(new Team(0, "a", "a", 2));
		// breakingTeams0.add(new Team(0, "b", "a", 2));
		// breakingTeams0.add(new Team(0, "c", "a", 2));
		// breakingTeams0.add(new Team(0, "d", "a", 2));
		// breakingTeams0.add(new Team(0, "e", "a", 2));
		// breakingTeams0.add(new Team(0, "f", "a", 2));
		// breakingTeams0.add(new Team(0, "g", "a", 2));
		// breakingTeams0.add(new Team(0, "h", "a", 2));
		// int breakRounds = t.whosBreaking(breakingTeams0);
		// int i = 0;
		// while (i < breakRounds) {
		// Team[][] pairings = t.breakRound();
		// t.breakResults(pairings);
		// i++;
		// }
		// }

	}
}
