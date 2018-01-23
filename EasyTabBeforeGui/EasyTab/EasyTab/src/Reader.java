import java.util.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	/**
	 * 
	 * @param teamsList
	 * @param amtSpeaks
	 * @param amtTeams
	 * @param amtRounds
	 * @param folder
	 * @return
	 */
	public ArrayList<Team> readTeamsIntoLists(ArrayList<Team> teamsList, int amtSpeaks, int amtTeams, int amtRounds,
			String folder) {
		System.out.println("Ensure that your file is in the \"Tournament\" folder for this Tournament");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("What is the name of your file? Do no include the .csv");
		int q = 1;

		while (q == 1) {
			String csvFile = folder + "\\" + s.nextLine() + ".csv";

			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			try {

				br = new BufferedReader(new FileReader(csvFile));
				int counter = 0;
				int debug = 1;
				while ((line = br.readLine()) != null) {
					System.out.println(debug);
					debug++;
					// System.out.println("got here!");
					if (counter != 0) {
						// System.out.println("also got here!");
						// use comma as separator
						String[] parsed = line.split(cvsSplitBy);
						String name = "";
						String school = "";
						name = parsed[0];
						school = parsed[1];
						Team w = new Team(amtSpeaks, name, school, amtRounds);
						w.setNov();
						teamsList.add(w);
						int i = 0;
						while (i < amtSpeaks * 2) {
							w.TeamSpeakersList.add(new Speaker(parsed[i + 2], parsed[i + 3].equals("Y"), w, amtRounds));
							// System.out.println("Managed to get here somehow");
							i += 2;
						}
					}
					counter++;
				}
				break;
			} catch (FileNotFoundException e) {
				System.out.println("File not found. Ensure the name is correct.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return teamsList;
	}

	/**
	 * 
	 * @param folder
	 * @return
	 */
	public ArrayList<String> readRoomsIntoLists(String folder) {
		ArrayList<String> roomsList = new ArrayList<String>();
		System.out.println("Ensure that your file is in the \"Tournament\" folder for this Tournament");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("What is the name of your file? Do no include the .csv");
		int q = 1;

		while (q == 1) {
			String csvFile = folder + "\\" + s.nextLine() + ".csv";

			BufferedReader br = null;
			String line = "";
			// String cvsSplitBy = ",";
			try {

				br = new BufferedReader(new FileReader(csvFile));
				// int counter = 0;
				// int debug = 1;
				while ((line = br.readLine()) != null) {
					roomsList.add(line);
				}

				break;
			} catch (FileNotFoundException e) {
				System.out.println("File not found. Ensure the name is correct.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return roomsList;

	}

	/**
	 * 
	 * @param folder
	 * @return
	 */
	public ArrayList<Judge> readJudgesIntoLists(String folder) {

		ArrayList<Judge> judgesList = new ArrayList<Judge>();
		return judgesList;

	}

	/**
	 * 
	 * @param counter
	 * @param pairings
	 * @param conferral
	 * @param resultsFile
	 */
	public void readResults(int counter, Team[][] pairings, boolean conferral, String resultsFile) {
		// TODO
		String csvFile = resultsFile;

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			br.readLine();
			int index = 0;
			while ((line = br.readLine()) != null) {
				String[] ins = line.split(cvsSplitBy);
				Team t1 = pairings[index][0];
				Team t2 = pairings[index][1];
				if (ins[2].equals("1")) {
					t1.wins++;
					t1.setRoundResults(1, counter);
					t2.losses++;
					t2.setRoundResults(0, counter);

				} else {
					t1.losses++;
					t1.setRoundResults(0, counter);
					t2.wins++;
					t2.setRoundResults(1, counter);
				}

				t1.TeamSpeakersList.get(0).addSpeaks(Double.parseDouble(ins[4]), counter);
				t1.TeamSpeakersList.get(0).speaks += Double.parseDouble(ins[4]);
				t1.TeamSpeakersList.get(1).addSpeaks(Double.parseDouble(ins[6]), counter);
				t1.TeamSpeakersList.get(1).speaks += Double.parseDouble(ins[6]);
				t2.TeamSpeakersList.get(0).addSpeaks(Double.parseDouble(ins[10]), counter);
				t2.TeamSpeakersList.get(0).speaks += Double.parseDouble(ins[10]);
				t2.TeamSpeakersList.get(1).addSpeaks(Double.parseDouble(ins[12]), counter);
				t2.TeamSpeakersList.get(1).speaks += Double.parseDouble(ins[12]);
				t1.setTeamSpeaks(t1.getTeamSpeaks() + (Double.parseDouble(ins[4]) + Double.parseDouble(ins[6])));
				t2.setTeamSpeaks(t2.getTeamSpeaks() + (Double.parseDouble(ins[10]) + Double.parseDouble(ins[12])));
				index++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
