import java.io.*;
import java.util.ArrayList;

public class Writer {
	public String writeDraw(Team[][] draw, int counter, String motion, Tournament t, String folder,
			ArrayList<String> roomsList) {
		BufferedWriter out;
		int q = 1;
		while (q == 1) {
			try {
				out = new BufferedWriter(new FileWriter(folder + "\\" + t.getName() + "Round" + counter + "Draw.csv"));
				out.write("Round " + counter);
				out.newLine();
				out.write("Room, Prop, Opp,");
				out.newLine();
				for (int i = 0; i < draw.length; i++) {
					out.write(roomsList.get(i) + ",");
					for (int j = 0; j < 2; j++) {
						out.write(draw[i][j].getTeamcode() + ",");

					}
					out.newLine();
				}
				out.close();
				break;
			} catch (FileNotFoundException e) {
				System.out.println("File being used. Please wait to close it");

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			}
		}
		return Writer.writeResultsSheet(draw, t, counter, folder);
	}

	/**
	 * Write a tab after a certain round
	 * 
	 * @param t
	 *            the tournament to be tabbed
	 * @param counter
	 *            the round number
	 */
	public void writeTab(Tournament t, int counter, String folder) {
		try {
			BufferedWriter out = new BufferedWriter(
					new FileWriter(folder + "\\" + t.getName() + "Round" + counter + "tab.csv"));
			out.write("Tab for " + t.getName());
			out.newLine();
			out.write(counter);
			out.newLine();
			out.write("Team tab:,");
			out.newLine();
			out.write("Position,Team,");
			for (int i = 0; i < counter; i++) {
				out.write("Round " + (i + 1) + ",");
			}
			out.write("Wins,Team Speaks");
			out.newLine();
			// @TO-DO
			int position = 1;
			for (int i = t.getTeamsList().size() - 1; i >= 0; i--) {
				Team team = t.getTeamsList().get(i);
				out.write(position + "," + team.toString() + ",");
				for (int q = 0; q < counter; q++) {
					out.write(team.getRoundResults()[q] + ",");
				}
				out.write(team.getWins() + "," + team.getTeamSpeaks() + ",");
				position++;
				out.newLine();
			}
			out.write("Speaker tab");
			out.newLine();
			out.write("Position,Nov Position, Speaker, Team,");
			for (int i = 0; i < counter; i++) {
				out.write("Round " + (i + 1) + ",");
			}
			out.write("Total Speaks, Average Speaks");
			out.newLine();
			int novPosition = 1;
			position = 1;
			for (int i = t.getTotalSpeakers().size() - 1; i >= 0; i--) {
				Speaker speaker = t.getTotalSpeakers().get(i);
				out.write(String.valueOf(position));
				position++;
				if (speaker.isNov()) {
					out.write("," + novPosition);
					novPosition++;
				} else
					out.write(",");
				out.write("," + speaker.toString() + "," + speaker.getTeam() + ",");
				for (int q = -1; q < counter - 1; q++) {
					out.write(speaker.speaksPerRound[q + 1] + ",");
				}
				out.write(speaker.speaks + "," + speaker.speaks / counter);
				out.newLine();
			}

			out.close();

		} catch (

		FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeTab(Tournament t, String folder) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(folder + "\\" + t.getName() + "tab.csv"));
			out.write("Tab for " + t.getName() + ",");
			out.newLine();
			out.newLine();
			out.write("Team tab:,");
			out.newLine();
			out.write("Position,Team,");
			for (int i = 0; i < t.amtRounds; i++) {
				out.write("Round " + (i + 1) + ",");
			}
			out.write("Wins,Team Speaks");
			out.newLine();
			// @TO-DO
			int position = 1;
			for (int i = t.getTeamsList().size() - 1; i >= 0; i--) {
				Team team = t.getTeamsList().get(i);
				out.write(position + "," + team.toString() + ",");
				for (int q = -1; q < t.amtRounds - 1; q++) {
					out.write(team.getRoundResults()[q + 1] + ",");
				}
				out.write(team.getWins() + "," + team.getTeamSpeaks() + ",");
				position++;
				out.newLine();
			}
			out.write("Speaker tab");
			out.newLine();
			out.write("Position,Nov Position, Speaker, Team,");
			for (int i = 0; i < t.amtRounds; i++) {
				out.write("Round " + (i + 1) + ",");
			}
			out.write("Total Speaks, Average Speaks");
			out.newLine();
			int novPosition = 1;
			position = 1;
			for (int i = t.getTotalSpeakers().size() - 1; i >= 0; i--) {
				Speaker speaker = t.getTotalSpeakers().get(i);
				out.write(String.valueOf(position));
				position++;
				if (speaker.isNov()) {
					out.write("," + novPosition);
					novPosition++;
				} else
					out.write(",");
				out.write("," + speaker.toString() + "," + speaker.getTeam() + ",");
				for (int q = -1; q < t.amtRounds - 1; q++) {
					out.write(speaker.getSpeaksPerRound()[q + 1] + ",");
				}
				out.write(speaker.speaks + "," + speaker.speaks / t.amtRounds);
				out.newLine();
			}

			out.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String createFolder(Tournament t) {
		String username = System.getProperty("user.name");
		File f = new File("C:\\Users\\" + username + "\\Desktop\\EasyTabFiles\\Tournaments\\" + t.getName());
		System.out.println(f.mkdirs());
		return f.getAbsolutePath();
	}

	private static String writeResultsSheet(Team[][] draw, Tournament t, int counter, String folder) {
		BufferedWriter out;
		int q = 1;
		String name = "";
		while (q == 1) {
			try {
				name = folder + "\\" + t.getName() + "Round" + counter + "Results.csv";
				out = new BufferedWriter(new FileWriter(name));
				out.write("Round " + counter);
				out.newLine();
				out.write(
						"Room, Prop, W?, Speaker 1, Score, Speaker 2, Score,Opp, W?, Speaker 1, Score, Speaker 2, Score,");
				out.newLine();
				for (int i = 0; i < draw.length; i++) {
					out.write(i + 1 + ",");
					Team t1 = draw[i][0];
					out.write(t1.getTeamcode() + ",");
					out.write("," + t1.TeamSpeakersList.get(0) + ",," + t1.TeamSpeakersList.get(1) + ",,");
					t1 = draw[i][1];
					out.write(t1.getTeamcode() + ",");
					out.write("," + t1.TeamSpeakersList.get(0) + ",," + t1.TeamSpeakersList.get(1) + ",");
					out.newLine();
				}
				out.close();
				break;
			} catch (FileNotFoundException e) {
				System.out.println("File being used. Please wait to close it");

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			}
		}
		// File source = new File(folder + "\\" + t.getName() + "Round" + counter +
		// "Draw.csv");
		// File dest = new File(folder + "\\" + t.getName() + "Round" + counter +
		// "Results.csv");
		// try {
		// copyFileUsingChannel(source, dest);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// BufferedWriter out = new BufferedWriter(new FileWriter(dest));
		// BufferedReader in = new BufferedReader(new FileReader(dest));
		// String line;
		// in.readLine();
		// in.readLine();
		// counter = 2;
		// while ((line = in.readLine()) != null) {
		// String[] ins = line.split(",");
		//
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return name;
	}

}
