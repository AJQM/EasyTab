
public class Speaker implements Comparable {
	double speaks = 0;
	private String name;
	double roundSpeaks = 0;
	private boolean isNov = false;
	private Team teamName;
	double[] speaksPerRound;
	int amtRounds;

	public Speaker(String nameIn, boolean isNov, Team team, int amtRounds) {
		setName(nameIn);
		setNov(isNov);
		setTeam(team);
		this.speaksPerRound = new double[amtRounds * 4];
		this.amtRounds = amtRounds;
	}

	public String toString() {
		return getName();
	}

	public boolean isNov() {
		return isNov;
	}

	public void setNov(boolean isNov) {
		this.isNov = isNov;
	}

	public void setTeam(Team team) {
		teamName = team;
	}

	public Team getTeam() {
		// TODO Auto-generated method stub
		return teamName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSpeaks(double speaks, int counter) {
		speaksPerRound[counter - 1] = speaks;
	}

	public double[] getSpeaksPerRound() {
		return speaksPerRound;
	}

	public void setSpeaksPerRound(double[] speaksPerRound) {
		this.speaksPerRound = speaksPerRound;
	}

	public double getStandardDev() {
		double standardDev = 0;
		double mean = 0;
		for (int i = 0; i < amtRounds; i++) {
			mean += speaksPerRound[i];
		}
		mean = mean / amtRounds;
		for (int i = 0; i < amtRounds; i++) {
			double temp = speaksPerRound[i] - mean;
			standardDev += Math.pow(temp, 2);
		}
		standardDev = standardDev / amtRounds;
		standardDev = Math.pow(standardDev, .5);
		return standardDev;
	}

	@Override
	/**
	 * finish with standard dev
	 */
	public int compareTo(Object arg0) {
		Speaker s = (Speaker) arg0;
		if (this.speaks > s.speaks)
			return 1;
		if (this.speaks < s.speaks)
			return -1;
		return 0;
	}

}
