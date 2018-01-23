import java.util.*;

/**
 * represents a Team of Speakers
 * 
 * @author Adam Miller
 *
 */
public class Team implements Comparable {
	/**
	 * attributes
	 * 
	 */
	private int n = 0;
	private String teamcode = "";
	private int ballots = 0;
	int wins = 0;
	int losses = 0;
	private String schoolCode = "";
	private int randomNumber = 0;
	private boolean resultsEntered = false;
	private double teamSpeaks = 0;
	private Scanner s = new Scanner(System.in);
	private int breakPosition = 0;
	private int[] roundResults;
	private boolean isNov = false;

	public Team(int amtOfSpeakers, String teamcode, String school, int amtRounds) {
		n = amtOfSpeakers;
		this.setTeamcode(teamcode);
		schoolCode = school;
		roundResults = new int[amtRounds];
	}

	ArrayList<Speaker> TeamSpeakersList = new ArrayList<Speaker>(n);

	public void setSpeakers(Team t, int amtRounds) {
		for (int i = 0; i < n; i++) {
			String name = "";
			System.out.println("Speaker " + (i + 1) + "'s name please!");
			while (!s.hasNextLine()) {
			}
			name = s.nextLine();
			System.out.println("Is " + name + " a novice? ");
			Speaker p = new Speaker(name, false, t, amtRounds);
			TeamSpeakersList.add(p);

		}
	}

	public void setNov() {
		boolean goodNov = true;
		for (Speaker s : TeamSpeakersList) {
			if (!s.isNov()) {
				goodNov = false;
			}
		}
		this.isNov = goodNov;
	}

	public String getSchool() {
		return schoolCode;
	}

	public double getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int random) {
		randomNumber = random;
	}

	public String toString() {
		return getTeamcode();
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getBreakPosition() {
		return breakPosition;
	}

	public void setBreakPosition(int breakPosition) {
		this.breakPosition = breakPosition;
	}

	public String getTeamcode() {
		return teamcode;
	}

	public void setTeamcode(String teamcode) {
		this.teamcode = teamcode;
	}

	public double getTeamSpeaks() {
		return teamSpeaks;
	}

	public void setTeamSpeaks(double teamSpeaks) {
		this.teamSpeaks = teamSpeaks;
	}

	public int getBallots() {
		return ballots;
	}

	public void setBallots(int ballots) {
		this.ballots = ballots;
	}

	public int[] getRoundResults() {
		return roundResults;
	}

	public void setRoundResults(int[] roundResults) {
		this.roundResults = roundResults;
	}

	public void setRoundResults(int result, int counter) {
		this.roundResults[counter - 1] = result;
	}

	public boolean isResultsEntered() {
		return resultsEntered;
	}

	public void setResultsEntered(boolean resultsEntered) {
		this.resultsEntered = resultsEntered;
	}

	public boolean isNov() {
		return isNov;
	}

	public void setNov(boolean isNov) {
		this.isNov = isNov;
	}

	@Override
	public int compareTo(Object arg0) {
		Team t = (Team) arg0;
		if (this.wins > t.wins)
			return 1;
		if (this.wins < t.wins) {
			return -1;
		}
		if (this.wins == t.wins) {
			if (this.teamSpeaks > t.teamSpeaks) {
				return 1;
			} else if (this.teamSpeaks > t.teamSpeaks) {
				return -1;
			} else if (this.teamSpeaks == t.teamSpeaks)
				return 0;
		}
		return 0;
	}
}
