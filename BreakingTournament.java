import java.util.ArrayList;
import java.util.Scanner;

public class BreakingTournament implements Breaking {
	private int breakRounds;
	Scanner s = new Scanner(System.in);
	private ArrayList<Team> originalBreak;
	private ArrayList<Team> breakingTeams;

	@Override
	public int whosBreaking(ArrayList<Team> teamsList) {
		// TODO Auto-generated method stub
		int amountTeamsBreaking = s.nextInt();
		ArrayList<Team> theBreak = new ArrayList<Team>();
		for (int i = 1; i <= amountTeamsBreaking; i++) {
			teamsList.get(teamsList.size() - i).setBreakPosition(i);
			theBreak.add(teamsList.get(teamsList.size() - i));
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
		UsefulMethods.printPairings(brackets);
		return brackets;
	}

	@Override
	public void sortBreak(Team[][] pairings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakResults(Team[][] pairings) {
		// TODO Auto-generated method stub

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
		BreakingTournament t = new BreakingTournament();
		ArrayList<Team> breakingTeams = new ArrayList<Team>();
		breakingTeams.add(new Team(0, "a", "a"));
		breakingTeams.add(new Team(0, "b", "a"));
		breakingTeams.add(new Team(0, "c", "a"));
		breakingTeams.add(new Team(0, "d", "a"));
		breakingTeams.add(new Team(0, "e", "a"));
		breakingTeams.add(new Team(0, "f", "a"));
		t.setBreakingTeams(breakingTeams);
		t.breakRound();
	}

}
