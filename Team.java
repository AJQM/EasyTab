import java.util.*;

/**
 * represents a Team of Speakers
 * 
 * @author Adam Miller
 *
 */
public class Team {
	int n = 0;
	String teamcode = "";
	int ballots = 0;
	int wins = 0;
	int losses = 0;
	String schoolCode = "";
	int randomNumber = 0;
	boolean resultsEntered = false;
	double teamSpeaks = 0;
	Scanner s = new Scanner(System.in);
	private int breakPosition = 0;

	public Team(int amtOfSpeakers, String teamcode, String school) {
		n = amtOfSpeakers;
		this.teamcode = teamcode;
		schoolCode = school;
	}

	ArrayList<Speaker> TeamSpeakersList = new ArrayList<Speaker>(n);

	public void setSpeakers() {
		for (int i = 0; i < n; i++) {
			String name = "";
			System.out.println("Speaker " + (i + 1) + "'s name please!");
			while (!s.hasNextLine()) {
			}
			name = s.nextLine();
			Speaker p = new Speaker(name);
			TeamSpeakersList.add(p);

		}
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
		return teamcode;
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
}
