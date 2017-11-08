import java.util.*;

public class Tournament {
	public int amtSpeaks;
	public int amtTeams;
	public int amtRounds;
	Scanner s = new Scanner(System.in);
	public ArrayList<Speaker> totalSpeakers = new ArrayList<Speaker>(amtTeams * amtSpeaks);
	public ArrayList<Team> teamsList = new ArrayList<Team>(amtTeams);

	public Tournament(int speakersPerTeam, int numberOfTeams, int numberOfRounds) {
		amtSpeaks = speakersPerTeam;
		amtTeams = numberOfTeams;
		amtRounds = numberOfRounds;
	}

	public int getTeams() {
		return amtTeams;
	}

	public void addToSpeakersList(Speaker s) {
		totalSpeakers.add(s);
	}

	public void setTeams(int amt, boolean manual) {
		if (manual) {
			for (int i = 0; i < amt; i++) {
				String name = "";
				String code = "";
				System.out.println("What is team " + (i + 1) + "'s teamcode?");
				name = s.nextLine();
				System.out.println("What is the code of team " + (i + 1) + "'s school?");
				code = s.nextLine();
				Team w = new Team(amtSpeaks, name, code);
				w.setSpeakers();
				teamsList.add(w);
			}
		} else {
			Reader r = new Reader();
			teamsList = r.readIntoLists(teamsList, amtSpeaks);
		}
	}

	public Team[][] bracketedRound(int counter) {
		if (counter == 1) {
			System.out.println("Round 1 can't be bracketed. This will be an unBracketed round.");
			return nonBracketedRound(counter);
		}
		Team[][] brackets = new Team[teamsList.size() / 2][2];
		for (int i = 0, index = 0; i < brackets.length; i++, index += 2) {
			brackets[i][0] = teamsList.get(index);
			brackets[i][1] = teamsList.get(index + 1);
		}
		UsefulMethods.printPairings(brackets);
		return brackets;

	}

	public Team[][] schoolProtection(int counter) {
		ArrayList<Team> temp = new ArrayList<Team>();
		temp = (ArrayList<Team>) teamsList.clone();
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		boolean run = true;

		int i = 0;

		Team[][] draw = new Team[temp.size() / 2][2];
		Collections.shuffle(temp);

		// Time out if while loop is active for >5 seconds
		while (temp.size() > 0 && run == true) {

			int rand = (int) (Math.random() * temp.size());
			int rand2 = (int) (Math.random() * temp.size());

			// inserts random pairs into draw
			if ((temp.get(rand).getSchool() != temp.get(rand2).getSchool()) && (rand != rand2)) {

				draw[i][0] = temp.get(rand);
				draw[i][1] = temp.get(rand2);

				temp.remove(rand);
				if (rand2 > rand) {
					temp.remove(rand2 - 1);
				} else
					temp.remove(rand2);

				i++;

			}

			// if above statement does not execute, check if all remaining
			// schools are the same
			// if all remaining schools are the same, shuffle list, set temp to
			// teamsList
			else {
				for (int j = 1; j < temp.size(); j++) {
					if (!temp.get(0).getSchool().equals(temp.get(j).getSchool())) {
						break;
					} else if (j == temp.size() - 1) {
						Collections.shuffle(teamsList);
						temp = (ArrayList<Team>) teamsList.clone();
					}
				}
			}

			// check for TimeOut
			elapsedTime = (new Date()).getTime() - startTime;
			// if (elapsedTime > 1000) {
			// boolean cont = false;
			// System.out.println("Method timed out");
			// run = false;
			// Scanner runScan = new Scanner(System.in);
			// System.out.println("Type \"true\" to try again, type \"false\" to exit");
			// cont = runScan.nextBoolean();
			//
			// i = 0;
			// for (int row = 0; row < draw.length; row++) {
			// for (int col = 0; col < draw[0].length; col++) {
			// draw[row][col] = null;
			// }
			// }
			// startTime = System.currentTimeMillis();
			//
			// if (cont) {
			// run = cont;
			// Collections.shuffle(teamsList);
			// temp = (ArrayList<Team>) teamsList.clone();
			//
			// }
			//
			// }
			if (elapsedTime > 100) {
				counter++;
				System.out.println("Method timed out, trying again...");
				boolean cont = true;
				if (counter > 5) {
					System.out.println("Exceeded maximum allowable attempte");
					cont = false;
					run = true;
					Scanner runScan = new Scanner(System.in);
					System.out.println("Type \"true\" to try again, type \"false\" to exit");
					cont = runScan.nextBoolean();
					counter = 0;
				}

				i = 0;
				for (int row = 0; row < draw.length; row++) {
					for (int col = 0; col < draw[0].length; col++) {
						draw[row][col] = null;
					}
				}
				startTime = System.currentTimeMillis();

				if (cont) {
					run = cont;
					Collections.shuffle(teamsList);
					temp = (ArrayList<Team>) teamsList.clone();
					System.out.println("temp1 size" + temp.size());
					System.out.println("temp size" + temp.size());
				}

			}
		}
		System.out.println("Room" + "\t" + "Prop" + "\t" + "Opp");
		for (int k = 0; k < draw.length; k++) {
			System.out.print(k + 1 + "\t");
			for (int j = 0; j < 2; j++) {
				System.out.print(draw[k][j].teamcode + "\t");

			}
			System.out.println();
		}
		return draw;
	}

	public Team[][] nonProtection(int counter) {
		ArrayList<Team> temp = new ArrayList<Team>();
		temp = teamsList;
		// should set temp to be a deep-copied version of arry
		// problem with temp and teamsList pointing to same object
		// when temp is altered, so is teamsList

		Team[][] draw = new Team[temp.size() / 2][2];

		// randomize temp
		Collections.shuffle(temp);

		// convert randomized arrayList into array with two teams per row
		for (int i = 0; i < temp.size(); i++) {
			if (i % 2 == 0) {
				draw[i / 2][0] = temp.get(i);
			} else if (i % 2 == 1) {
				draw[i / 2][1] = temp.get(i);
			}
		}
		System.out.println("Room" + "\t" + "Prop" + "\t" + "Opp");
		for (int i = 0; i < draw.length; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < 2; j++) {
				System.out.print(draw[i][j].teamcode + "\t");

			}
			System.out.println();
		}
		return draw;
	}

	public Team[][] nonBracketedRound(int counter) {
		System.out.println("Will round " + counter + " have school protection? (y/n)");
		if (UsefulMethods.yn())
			return schoolProtection(counter);
		else
			return nonProtection(counter);
	}

	public void results(int counter, Team[][] pairings) {
		System.out.println("Time to enter results for round " + counter);
		for (int i = 0; i < pairings.length; i++) {
			int judgeNum = 0;
			// System.out.println("Are results available for room " + (i + 1) +
			// ": " + pairings[i][0].teamcode + " vs "
			// + pairings[i][1].teamcode + "?");
			// if (UsefulMethods.yn()) {
			System.out.println(
					"room " + (i + 1) + ": " + pairings[i][0].teamcode + " vs " + pairings[i][1].teamcode + "?");
			System.out.println(
					"How many judges were in this room? (If judges did not have seperate ballots and confered, enter 1)");
			while (judgeNum == 0) {
				s.hasNextInt();
				if (s.hasNextInt()) {
					judgeNum = s.nextInt();
				} else {
					System.out.println("Please enter a number");
				}
				s.nextLine();

			}

			for (int j = 0; j < judgeNum; j++) {
				System.out.println("Did judge " + (j + 1) + " give team " + pairings[i][0].teamcode + " the win?");

				if (UsefulMethods.yn()) {
					pairings[i][0].ballots++;
				} else {
					pairings[i][1].ballots++;
				}
				System.out.println("For team " + pairings[i][0].teamcode + " what speaker score was given to ");
				for (int q = 0; q < amtSpeaks; q++) {
					System.out.println(pairings[i][0].TeamSpeakersList.get(q).name + "?");
					int temp = (int) pairings[i][0].TeamSpeakersList.get(q).roundSpeaks;
					// rewrite
					while ((int) pairings[i][0].TeamSpeakersList.get(q).roundSpeaks == temp) {
						if (s.hasNextDouble()) {
							pairings[i][0].TeamSpeakersList.get(q).roundSpeaks += s.nextDouble();
							break;
						} else {
							System.out.println("Please enter a number");
						}
						s.nextLine();

					}
				}
				System.out.println("For team " + pairings[i][1].teamcode + " what speaker score was given to ");
				for (int q = 0; q < amtSpeaks; q++) {
					System.out.println(pairings[i][1].TeamSpeakersList.get(q).name + "?");
					int temp = (int) pairings[i][1].TeamSpeakersList.get(q).roundSpeaks;

					while ((int) pairings[i][1].TeamSpeakersList.get(q).roundSpeaks == temp) {
						// rewrite
						// @rewrite
						if (s.hasNextDouble()) {
							pairings[i][1].TeamSpeakersList.get(q).roundSpeaks += s.nextDouble();
							break;
						} else {
							System.out.println("Please enter a number");
						}
						s.nextLine();

					}
				}
			}
			for (int j = 0; j < amtSpeaks; j++) {
				pairings[i][0].TeamSpeakersList.get(j).speaks += pairings[i][0].TeamSpeakersList.get(j).roundSpeaks
						/ judgeNum;
				pairings[i][0].teamSpeaks += pairings[i][0].TeamSpeakersList.get(j).roundSpeaks / judgeNum;
				pairings[i][1].TeamSpeakersList.get(j).speaks += pairings[i][1].TeamSpeakersList.get(j).roundSpeaks
						/ judgeNum;
				pairings[i][1].teamSpeaks += pairings[i][1].TeamSpeakersList.get(j).roundSpeaks / judgeNum;
			}
			if (pairings[i][0].ballots > pairings[i][1].ballots) {
				pairings[i][0].setWins(pairings[i][0].getWins() + 1);
				pairings[i][1].losses++;

			} else {
				pairings[i][1].setWins(pairings[i][1].getWins() + 1);
				pairings[i][0].losses++;
			}
			pairings[i][0].ballots = 0;
			pairings[i][1].ballots = 0;

		}

	}

	// sorts teams into a draw for a round
	public Team[][] round(int counter) {
		System.out.println("Will round " + counter + " be bracketed? (y/n)");
		if (UsefulMethods.yn()) {
			return bracketedRound(counter);
		} else
			return nonBracketedRound(counter);

	}

	// loads speakers from individual teams into total speakers list
	public ArrayList<Speaker> loadSpeakers() {
		for (int i = 0; i < teamsList.size(); i++) {
			for (int j = 0; j < amtSpeaks; j++) {
				totalSpeakers.add(teamsList.get(i).TeamSpeakersList.get(j));
			}
		}
		return totalSpeakers;
	}

	public void setSpeakerRankings(int counter) {
		for (int i = 0; i < totalSpeakers.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < totalSpeakers.size(); j++) {
				if (totalSpeakers.get(j).speaks < totalSpeakers.get(index).speaks) {
					index = j;
				}

			}
			Speaker smallerNumber = totalSpeakers.get(index);
			totalSpeakers.set(index, totalSpeakers.get(i));
			totalSpeakers.set(i, smallerNumber);
		}

		// print rankings
		System.out.println("Speaker\t\t\tRank\t\tSpeaks\t\tAverage Speaks");
		for (int i = totalSpeakers.size() - 1; i >= 0; i--) {
			System.out.println(totalSpeakers.get(i).name + "\t\t\t" + (totalSpeakers.size() - i) + "\t\t "
					+ Math.floor(totalSpeakers.get(i).speaks * 100) / 100 + "\t\t"
					+ Math.floor(totalSpeakers.get(i).speaks / counter * 100) / 100);
			totalSpeakers.get(i).roundSpeaks = 0;
		}
	}

	/**
	 * sorts the teams in ascending order, first by wins, then by speaks
	 * teamsList.get(0) is in last place
	 * 
	 * @param counter
	 *            the round number
	 */
	;

	public void setRankings(int counter) {
		for (int i = 0; i < teamsList.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < teamsList.size(); j++)
				if (teamsList.get(j).getWins() < teamsList.get(min).getWins())
					min = j;
			Team temp = teamsList.get(i);
			teamsList.set(i, teamsList.get(min));
			teamsList.set(min, temp);
		}
		boolean sorted = false;
		while (!sorted) {
			for (int i = 0; i < teamsList.size() - 1; i++) {
				if (teamsList.get(i).getWins() == teamsList.get(i + 1).getWins()) {
					if (teamsList.get(i).teamSpeaks > teamsList.get(i + 1).teamSpeaks) {
						Team temp = teamsList.get(i);
						teamsList.set(i, teamsList.get(i + 1));
						teamsList.set(i + 1, temp);
					}
				}

			}
			sorted = true;
			for (int i = 0; i < teamsList.size() - 1; i++) {
				if (teamsList.get(i).getWins() == teamsList.get(i + 1).getWins()) {
					if (teamsList.get(i).teamSpeaks > teamsList.get(i + 1).teamSpeaks) {
						sorted = false;
					}
				}
			}
		}
		System.out.println("Position\tTeam\tWins\tTeam Speaks");

		for (int i = teamsList.size() - 1; i >= 0; i--) {
			System.out.print(teamsList.size() - i);
			System.out.print("\t\t");
			System.out.print(teamsList.get(i));
			System.out.print("\t");
			System.out.print(teamsList.get(i).getWins());
			System.out.print("\t");
			System.out.print(teamsList.get(i).teamSpeaks);
			System.out.println();

		}
		setSpeakerRankings(counter);
	}

}
