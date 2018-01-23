import java.util.*;

public class Tests {

	public static void main(String[] args) {
		ArrayList<Team> teamsList = new ArrayList<Team>();
		Reader r = new Reader();
		teamsList = r.readTeamsIntoLists(teamsList, 2, 2,2, "hi");
		
		int winCounter = 0;
		Team[][] brackets = new Team[teamsList.size() / 2][2];
		for (int i = 0; i < teamsList.size(); i++) {
			if (teamsList.get(i + 1).getWins() == (teamsList.get(i)).getWins()) {
				winCounter++;
			}
		}

		if (winCounter % 2 == 0) {
			for (int i = 0; i < winCounter; i += 2) {
				brackets[i][0] = teamsList.get(i);
				brackets[i + 1][1] = teamsList.get(winCounter);
				i++;
				winCounter--;
			}
		} else {
			// if amount of teams with same wins is odd
			for (int i = 0; i < winCounter; i += 2) {
				brackets[i][0] = teamsList.get(i);
				brackets[i + 1][1] = teamsList.get(winCounter+1);
				i++;
				winCounter--;
			}
		}
		System.out.println("Room" + "\t" + "Prop" + "\t" + "Opp");
		for (int i = 0; i < brackets.length; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < 2; j++) {
				System.out.print(brackets[i][j].getTeamcode() + "\t");

			}
			System.out.println();
		}

	}
}
