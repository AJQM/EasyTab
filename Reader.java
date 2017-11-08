import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	public ArrayList<Team> readIntoLists(ArrayList<Team> teamsList, int amtSpeaks) {

		String csvFile = "C:\\Users\\Adam\\Downloads\\testSheet.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int counter = 0;
			while ((line = br.readLine()) != null) {
			//	System.out.println("got here!");
				if (counter != 0) {
				//	System.out.println("also got here!");
					// use comma as separator
					String[] parsed = line.split(cvsSplitBy);
					String name = "";
					String school = "";
					name = parsed[0];
					school = parsed[1];
					Team w = new Team(amtSpeaks, name, school);
					teamsList.add(w);
					int i = 0;
					while (i < amtSpeaks) {
						w.TeamSpeakersList.add(new Speaker(parsed[i + 2]));
						//System.out.println("Managed to get here somehow");
						i++;
					}
				}
				counter++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		return teamsList;
	}
}
