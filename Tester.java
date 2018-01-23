import com.sun.org.glassfish.external.statistics.Statistic;
import com.sun.org.glassfish.external.statistics.Stats;

public class Tester {

	public static void main(String[] args) {

		Team a = new Team(2, "a", "a", 2);
		Team b = new Team(2, "b", "b", 2);
		Team c = new Team(2, "c", "c", 2);
		Team d = new Team(2, "d", "d", 2);
		Team m = new Team(2, "m", "m", 2);
		Team q = new Team(2, "q", "q", 2);

		Tournament t = new Tournament(2, 3, 1, "Hi");
		a.setWins(a.getWins() + 1);
		c.setWins(c.getWins() + 1);
		d.setWins(d.getWins() + 1);
		d.setTeamSpeaks(d.getTeamSpeaks() + 1);
		c.setTeamSpeaks(c.getTeamSpeaks() - 1);
		a.setTeamSpeaks(a.getTeamSpeaks() + 2);
		m.setWins(1);
		m.setTeamSpeaks(92);
		q.setWins(1);
		q.setTeamSpeaks(90);
		t.getTeamsList().add(m);
		t.getTeamsList().add(a);
		t.getTeamsList().add(b);
		t.getTeamsList().add(q);
		t.getTeamsList().add(c);
		t.getTeamsList().add(d);
		t.setRankings(1);
	}

}
