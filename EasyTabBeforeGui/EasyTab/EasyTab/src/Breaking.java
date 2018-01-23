import java.util.*;

public interface Breaking {

	/**
	 * precondition: teamsList is sorted in order of rank, with TeamsList.get(0)
	 * being last place, etc. postCondition:given the total sorted list of teams
	 * (teamsList): asks the user how many <code> Team</code>s are breaking, makes a new
	 * ArrayList<Team> with only teams breaking, puts that in breakingTeams AND
	 * originalBreak (originalBreak will never be changed), and puts the number of
	 * breakrounds into breakRounds, breakrounds = (int) (1.4427 * Math.log(amount
	 * teams Breaking); initializes int <b>breakRounds</b> public final ArrayList<Team>
	 * originalBreak; public ArrayList<Team> breakingTeams;
	 * 
	 * @param teamsList
	 *            total sorted list of teams
	 * @return the amount of breakrounds
	 */
	public int whosBreaking(ArrayList<Team> teamsList);

	//
	/**
	 * precondition, breakingTeams is sorted and only contains the amount of teams
	 * that are playing in that round // postCondition: breakingTeams is unchanged,
	 * // Prints out pairings (I have an algorithm for this so dw about it)
	 * 
	 * @return returns a 2-d Array with the pairings. It should have 2 cols, and //
	 *         breakingTeams.size/2 rows // Each row has one team in col 0 and the
	 *         other team in col 1 // Teams are paired up based on ranking: first vs
	 *         last, second vs second // last, etc.
	 */
	public Team[][] breakRound();
	//

	/**
	 * precondition: breakingTeams has all the elements of pairings sorts
	 * breakingTeams so that it is in the order of pairings: e.g.pairings[0][0] is
	 * element 1,pairings[0][1] is element 2,pairings[1][0] is element 3 etc.
	 * 
	 * Asks the user for each pairing (each pairing is a row), which team won
	 * postCondition: breakingTeams has all the losing teams deleted
	 * 
	 * @param pairings
	 *            the 2D array computed in breakRound, where Each row has one team
	 *            in col 0 and the other team in col 1
	 * 
	 * 
	 */
	public void breakResults(Team[][] pairings);

	/***
	 *
	 * @return originalBreak
	 */
	public ArrayList<Team> getOriginalBreak(); // returns originalBreak

	/**
	 * 
	 * @return breakingTeams
	 */
	public ArrayList<Team> getBreakingTeams(); // returns breakingTeams

}
