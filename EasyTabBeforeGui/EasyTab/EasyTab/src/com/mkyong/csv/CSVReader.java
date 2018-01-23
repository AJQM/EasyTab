
package com.mkyong.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	public static void main(String[] args) {

		String csvFile = "C:/Users/user/Downloads/Test Spreadsheet for EasyTab - Sheet1.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int counter = 0;
			while ((line = br.readLine()) != null) {
				if (counter != 0) {
					// use comma as separator
					String[] parsed = line.split(cvsSplitBy);

					System.out.println("Teamcode: " + parsed[0] + "\tSchool : " + parsed[1] + "\tSpeaker1: " + parsed[2]
							+ "\tSpeaker2: " + parsed[3]);
					
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

	}

}
