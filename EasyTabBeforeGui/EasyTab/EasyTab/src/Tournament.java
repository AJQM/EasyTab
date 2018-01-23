import java.util.*;

public class Tournament {
	public int amtSpeaks;
	public int amtTeams;
	public int amtRounds;
	private String name;
	Scanner s = new Scanner(System.in);
	private ArrayList<Speaker> totalSpeakers = new ArrayList<Speaker>(amtTeams * amtSpeaks);
	private ArrayList<Team> teamsList = new ArrayList<Team>(amtTeams);
	private ArrayList<String> motions = new ArrayList<String>();
	private ArrayList<String> roomsList = new ArrayList<String>();

	public ArrayList<String> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(ArrayList<String> roomsList) {
		this.roomsList = roomsList;
	}

	public ArrayList<Team> getTeamsList() {
		return teamsList;
	}

	public Tournament(int speakersPerTeam, int numberOfTeams, int numberOfRounds, String name) {
		amtSpeaks = speakersPerTeam;
		amtTeams = numberOfTeams;
		amtRounds = numberOfRounds;
		setName(name);
	}

	public void setRoundMotion(String motion) {
		motions.add(motion);
	}

	public int getTeams() {
		return amtTeams;
	}

	public void addToSpeakersList(Speaker s) {
		totalSpeakers.add(s);
	}

	public void setTeams(int amt, boolean manual, String folder) {
		if (manual) {
			for (int i = 0; i < amt; i++) {
				String name = "";
				String code = "";
				System.out.println("What is team " + (i + 1) + "'s teamcode?");
				name = s.nextLine();
				System.out.println("What is the code of team " + (i + 1) + "'s school?");
				code = s.nextLine();
				Team w = new Team(amtSpeaks, name, code, amtRounds);
				w.setSpeakers(w, amtRounds);
				w.setNov();
				getTeamsList().add(w);

			}
		} else {
			Reader r = new Reader();
			setTeamsList(r.readTeamsIntoLists(getTeamsList(), amtSpeaks, amtTeams, amtRounds, folder));

		}
	}

	public Team[][] bracketedRound(int counter) {
		if (counter == 1) {
			System.out.println("Round 1 can't be bracketed. This will be an unBracketed round.");
			return nonBracketedRound(counter);
		}
		Team[][] brackets = new Team[getTeamsList().size() / 2][2];
		for (int i = 0, index = 0; i < brackets.length; i++, index += 2) {
			brackets[i][0] = getTeamsList().get(index);
			brackets[i][1] = getTeamsList().get(index + 1);
		}
		UsefulMethods.printPairings(brackets, roomsList);
		return brackets;

	}

	/**
	 * @deprecated
	 * @param counter
	 *            the round number
	 * @return a draw
	 */
	@SuppressWarnings("unchecked")
	public Team[][] schoolProtection(int counter) {
		ArrayList<Team> temp = new ArrayList<Team>();
		temp = (ArrayList<Team>) getTeamsList().clone();
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
						Collections.shuffle(getTeamsList());
						temp = (ArrayList<Team>) getTeamsList().clone();
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
					@SuppressWarnings("resource")
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
					Collections.shuffle(getTeamsList());
					temp = (ArrayList<Team>) getTeamsList().clone();
					System.out.println("temp1 size" + temp.size());
					System.out.println("temp size" + temp.size());
				}

			}
		}
		UsefulMethods.printPairings(draw, roomsList);
		return draw;
	}

	public Team[][] nonProtection(int counter) {
		ArrayList<Team> temp = new ArrayList<Team>();
		temp = getTeamsList();
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
		UsefulMethods.printPairings(draw, roomsList);
		return draw;
	}

	public Team[][] nonBracketedRound(int counter) {
		// System.out.println("Will round " + counter + " have school protection?
		// (y/n)");
		// if (UsefulMethods.yn())
		// return schoolProtection(counter);
		// else
		return nonProtection(counter);
	}

	public void results(int counter, Team[][] pairings, boolean conferral, String resultsFile, boolean manual) {
		if (manual) {
			System.out.println("Time to enter results for round " + counter);
			for (int i = 0; i < pairings.length; i++) {
				int judgeNum = 0;
				// System.out.println("Are results available for room " + (i + 1) +
				// ": " + pairings[i][0].teamcode + " vs "
				// + pairings[i][1].teamcode + "?");
				// if (UsefulMethods.yn()) {
				System.out.println("room " + (i + 1) + ": " + pairings[i][0].getTeamcode() + " vs "
						+ pairings[i][1].getTeamcode() + "?");
				if (!conferral) {
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
				} else
					judgeNum = 1;

				for (int j = 0; j < judgeNum; j++) {
					System.out.println(
							"Did judge " + (j + 1) + " give team " + pairings[i][0].getTeamcode() + " the win?");

					if (UsefulMethods.yn()) {
						pairings[i][0].setBallots(pairings[i][0].getBallots() + 1);
					} else {
						pairings[i][1].setBallots(pairings[i][1].getBallots() + 1);
					}
					System.out
							.println("For team " + pairings[i][0].getTeamcode() + " what speaker score was given to ");
					for (int q = 0; q < amtSpeaks; q++) {
						System.out.println(pairings[i][0].TeamSpeakersList.get(q).getName() + "?");
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
					System.out
							.println("For team " + pairings[i][1].getTeamcode() + " what speaker score was given to ");
					for (int q = 0; q < amtSpeaks; q++) {
						System.out.println(pairings[i][1].TeamSpeakersList.get(q).getName() + "?");
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
					double speaks;
					speaks = pairings[i][0].TeamSpeakersList.get(j).roundSpeaks / judgeNum;
					pairings[i][0].TeamSpeakersList.get(j).speaks += speaks;
					pairings[i][0].TeamSpeakersList.get(j).addSpeaks(speaks, counter);
					pairings[i][0].setTeamSpeaks(pairings[i][0].getTeamSpeaks() + speaks);
					pairings[i][1].TeamSpeakersList.get(j).speaks += pairings[i][1].TeamSpeakersList.get(j).roundSpeaks
							/ judgeNum;
					pairings[i][1].TeamSpeakersList.get(j)
							.addSpeaks(pairings[i][1].TeamSpeakersList.get(j).roundSpeaks / judgeNum, counter);
					pairings[i][1].setTeamSpeaks(pairings[i][1].getTeamSpeaks()
							+ pairings[i][1].TeamSpeakersList.get(j).roundSpeaks / judgeNum);
				}
				if (pairings[i][0].getBallots() > pairings[i][1].getBallots()) {
					pairings[i][0].setWins(pairings[i][0].getWins() + 1);
					pairings[i][0].setRoundResults(1, counter);
					pairings[i][1].losses++;
					pairings[i][1].setRoundResults(0, counter);

				} else {
					pairings[i][1].setWins(pairings[i][1].getWins() + 1);
					pairings[i][1].setRoundResults(1, counter);

					pairings[i][0].losses++;
					pairings[i][0].setRoundResults(0, counter);

				}
				pairings[i][0].setBallots(0);
				pairings[i][1].setBallots(0);

			}
		} else {
			Reader r = new Reader();
			r.readResults(counter, pairings, conferral, resultsFile);
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
		for (int i = 0; i < getTeamsList().size(); i++) {
			for (int j = 0; j < amtSpeaks; j++) {
				getTotalSpeakers().add(getTeamsList().get(i).TeamSpeakersList.get(j));
			}
		}
		return getTotalSpeakers();
	}

	public void setSpeakerRankings(int counter) {
		for (int i = 0; i < getTotalSpeakers().size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < getTotalSpeakers().size(); j++) {
				if (getTotalSpeakers().get(j).speaks < getTotalSpeakers().get(index).speaks) {
					index = j;
				}

			}
			Speaker smallerNumber = getTotalSpeakers().get(index);
			getTotalSpeakers().set(index, getTotalSpeakers().get(i));
			getTotalSpeakers().set(i, smallerNumber);
		}

		// print rankings
		System.out.println("Speaker\t\t\tRank\t\tSpeaks\t\tAverage Speaks");
		for (int i = getTotalSpeakers().size() - 1; i >= 0; i--) {
			System.out.println(getTotalSpeakers().get(i).getName() + "\t\t\t" + (getTotalSpeakers().size() - i)
					+ "\t\t " + Math.floor(getTotalSpeakers().get(i).speaks * 100) / 100 + "\t\t"
					+ Math.floor(getTotalSpeakers().get(i).speaks / counter * 100) / 100);
			getTotalSpeakers().get(i).roundSpeaks = 0;
		}
	}

	/**
	 * sorts the teams in ascending order, first by wins, then by speaks
	 * teamsList.get(0) is in last place
	 * 
	 * @param counter
	 *            the round number
	 */
	public void setRankings(int counter) {
		for (int i = 0; i < getTeamsList().size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < getTeamsList().size(); j++)
				if (getTeamsList().get(j).getWins() < getTeamsList().get(min).getWins())
					min = j;
			Team temp = getTeamsList().get(i);
			getTeamsList().set(i, getTeamsList().get(min));
			getTeamsList().set(min, temp);
		}
		boolean sorted = false;
		while (!sorted) {
			for (int i = 0; i < getTeamsList().size() - 1; i++) {
				if (getTeamsList().get(i).getWins() == getTeamsList().get(i + 1).getWins()) {
					if (getTeamsList().get(i).getTeamSpeaks() > getTeamsList().get(i + 1).getTeamSpeaks()) {
						Team temp = getTeamsList().get(i);
						getTeamsList().set(i, getTeamsList().get(i + 1));
						getTeamsList().set(i + 1, temp);
					}
				}

			}
			sorted = true;
			for (int i = 0; i < getTeamsList().size() - 1; i++) {
				if (getTeamsList().get(i).getWins() == getTeamsList().get(i + 1).getWins()) {
					if (getTeamsList().get(i).getTeamSpeaks() > getTeamsList().get(i + 1).getTeamSpeaks()) {
						sorted = false;
					}
				}
			}
		}
		System.out.println("Position\tTeam\tWins\tTeam Speaks");

		for (int i = getTeamsList().size() - 1; i >= 0; i--) {
			System.out.print(getTeamsList().size() - i);
			System.out.print("\t\t");
			System.out.print(getTeamsList().get(i));
			System.out.print("\t");
			System.out.print(getTeamsList().get(i).getWins());
			System.out.print("\t");
			System.out.print(getTeamsList().get(i).getTeamSpeaks());
			System.out.println();

		}
		setSpeakerRankings(counter);
	}

	public ArrayList<String> getMotions() {
		return motions;
	}

	public void setMotions(ArrayList<String> motions) {
		this.motions = motions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeamsList(ArrayList<Team> teamsList) {
		this.teamsList = teamsList;
	}

	public ArrayList<Speaker> getTotalSpeakers() {
		return totalSpeakers;
	}

	public void setTotalSpeakers(ArrayList<Speaker> totalSpeakers) {
		this.totalSpeakers = totalSpeakers;
	}

	public void setRooms(boolean manual, Scanner s, String folder) {

		if (manual) {
			System.out.println("How many rooms to enter?");
			int amt = -1;
			do {
				try {
					amt = s.nextInt();
				} catch (NumberFormatException e) {
				}

			} while (amt <= 0);
			for (int i = 0; i < amt; i++) {
				System.out.println("What is the room number?");
				roomsList.add(s.nextLine());
			}
		} else {
			Reader r = new Reader();
			setRoomsList(r.readRoomsIntoLists(folder));

		}
	}

}
