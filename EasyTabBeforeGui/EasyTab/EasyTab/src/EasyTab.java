import java.util.*;

/**
 * A tabber for a debate tournament
 * 
 * @author Adam Miller
 * @version 0.3.2
 */
public class EasyTab {
	public static void main(String args[]) {
		System.out.println("Welcome to EasyTab! v 0.3.2");
		int amtSpeaks = 0;
		int amtTeams = 0;
		int amtRounds = 0;
		boolean conferral = false;
		String name = "";
		int CONSTANT = 1;
		boolean manual = false;
		boolean debug = true;
		Scanner s = new Scanner(System.in);
		Writer w = new Writer();

		System.out.println("What is the Name of this Tournament?");
		name = s.nextLine();
		while (CONSTANT == 1) {
			System.out.println("How many teams are competing?");
			try {
				@SuppressWarnings("resource")
				Scanner d = new Scanner(System.in);
				amtTeams = d.nextInt();
				if (amtTeams < 2)
					throw new IndexOutOfBoundsException();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("At least 2 teams must compete.");
			}
		}
		while (CONSTANT == 1) {
			System.out.println("How many Speakers per Team");
			try {
				@SuppressWarnings("resource")
				Scanner d = new Scanner(System.in);

				amtSpeaks = d.nextInt();
				if (amtSpeaks < 0 && debug) {
					throw new IndexOutOfBoundsException();
				} else if (amtSpeaks <= 0) {
					throw new IndexOutOfBoundsException();
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
			} catch (IndexOutOfBoundsException e) {
				if (debug == true) {
					System.out.println(e.getMessage());
					System.out.println("Please enter at least 0");
				} else
					System.out.println("Each team must have at least 1 speaker");
			}
		}

		while (CONSTANT == 1) {
			System.out.println("How many prelim rounds?");

			try {
				@SuppressWarnings("resource")
				Scanner d = new Scanner(System.in);

				amtRounds = d.nextInt();
				if (amtRounds < 1) {
					throw new IndexOutOfBoundsException();
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("At least one prelim round must occur");
			}
		}
		System.out.println("Will judges each have individual ballots (y) or will each room's panel confer (n)");
		conferral = UsefulMethods.yn();

		System.out.println("Would you like to enter teams/speakers from a spreadsheet (y) or manually (n)?");
		if (UsefulMethods.yn()) {
			manual = false;
		} else {

			manual = true;
		}

		Tournament t = new Tournament(amtSpeaks, amtTeams, amtRounds, name);
		String folder = w.createFolder(t);

		t.setTeams(amtTeams, manual, folder);
		System.out.println("Would you like to enter rooms from a spreadsheet (y) or manually (n)");
		if (UsefulMethods.yn()) {
			manual = false;
		} else {

			manual = true;
		}
		t.setRooms(manual, s, folder);
		// System.out.println("Would you like to enter judges from a spreadsheet (y) or
		// manually (n)");
		// if (UsefulMethods.yn()) {
		// manual = false;
		// } else {
		//
		// manual = true;
		// }
		// t.setJudges(manual, s));
		for (int i = 0; i < t.getTeamsList().size(); i++) {
			System.out.println(t.getTeamsList().get(i).getTeamcode());
			System.out.print("\t");
			for (int j = 0; j < t.amtSpeaks; j++) {
				System.out.print(t.getTeamsList().get(i).TeamSpeakersList.get(j).getName());
				System.out.print("\t");
			}
			System.out.println();
		}
		t.loadSpeakers();
		int counter = 1;
		while (counter <= amtRounds) {

			Team[][] pairings = t.round(counter);
			System.out.println("What is the motion for round " + counter + "?");
			@SuppressWarnings("resource")
			Scanner g = new Scanner(System.in);
			String motion = g.nextLine();
			t.setRoundMotion(motion);
			String resultsFile = w.writeDraw(pairings, counter, motion, t, folder, t.getRoomsList());
			System.out.println("Would you like to enter results from a spreadsheet (y) or manually (n)?");
			manual = false;
			if (UsefulMethods.yn()) {
				System.out
						.println("Make sure your spreadsheet is formatted like the example included with the program.");
				System.out.println("Enter y when you're ready to continue");
				int i = 1;
				while (i == 1) {
					if (s.nextLine().equals("y")) {
						break;
					}
				}
			} else {

				manual = true;
			}
			t.results(counter, pairings, conferral, resultsFile, manual);
			t.setRankings(counter);
			w.writeTab(t, counter, folder);
			counter++;
		}

		BreakingTournament b = new BreakingTournament(t.getRoomsList());
		counter = 1;
		System.out.println("How many teams will break?");
		int amtBreakRounds = b.whosBreaking(t.getTeamsList());
		while (counter <= amtBreakRounds) {
			Team[][] brackets = b.breakRound();
			b.breakResults(brackets);
			counter++;
		}
		System.out.println("Congratulations to the Champion, " + b.getBreakingTeams().get(0).getTeamcode());

		// now print out all results, put them in a spreadsheet
		// @lel
		w.writeTab(t, folder);
		s.close();
	}
}
