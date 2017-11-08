import java.util.*;

public class EasyTab {
	public static void main(String args[]) {
		System.out.println("Welcome to EasyTab! v 0.3.1");
		int amtSpeaks = 0;
		int amtTeams = 0;
		int amtRounds = 0;
		boolean manual = false;
		Scanner s = new Scanner(System.in);
		System.out.println("How many teams are competing?");
		try {
			amtTeams = s.nextInt();
		} catch (InputMismatchException e) {

		}
		System.out.println("How many Speakers per Team");
		amtSpeaks = s.nextInt();
		System.out.println("How many prelim rounds?");
		amtRounds = s.nextInt();

		System.out.println("Would you like to enter teams/speakers from a spreadsheet (y) or manually (n)?");
		if (UsefulMethods.yn()) {
			System.out.println("Make sure your spreadsheet is formatted like the example included with the program.");
			System.out.println("Press y when you're ready to continue");
			while (1 == 1) {
				if (s.nextLine().equals("y")) {
					break;
				}
			}
		} else {

			manual = true;
		}

		Tournament t = new Tournament(amtSpeaks, amtTeams, amtRounds);
		t.setTeams(amtTeams, manual);
		for (int i = 0; i < t.teamsList.size(); i++) {
			System.out.println(t.teamsList.get(i).teamcode);
			System.out.print("\t");
			for (int j = 0; j < t.amtSpeaks; j++) {
				System.out.print(t.teamsList.get(i).TeamSpeakersList.get(j).name);
				System.out.print("\t");
			}
			System.out.println();
		}
		t.loadSpeakers();
		int counter = 1;
		while (counter <= amtRounds) {
			Team[][] pairings = t.round(counter);
			t.results(counter, pairings);
			t.setRankings(counter);
			counter++;
		}

		// to be unCommented once breakTournament is written
		BreakingTournament b = new BreakingTournament();
		counter = 1;
		int amtBreakRounds = b.whosBreaking(t.teamsList);
		// while (counter <= amtBreakRounds){
		// b.breakRound;
		// b.sortBreak;
		// b.breakResults;
		// }
		// System.out.println("Congratulations to the Champion, " +
		// b.getBreakingTeams().get(0).teamcode);

		// now print out all results, put them in a spreadsheet
		// @lel
		s.close();
	}
}
