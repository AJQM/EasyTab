
public class Tester {

	public static void main(String[] args) {

		Team a = new Team(2, "a", "a");
		Team b = new Team(2, "b", "b");
		Team c = new Team(2, "c", "c");
		Team d = new Team(2, "d", "d");
		Team m = new Team(2, "m", "m");
		Team q = new Team(2, "q", "q");

		Tournament t = new Tournament(2, 3, 1);
		a.wins++;
		c.wins++;
		d.wins++;
		d.teamSpeaks++;
		c.teamSpeaks--;
		a.teamSpeaks += 2;
		m.setWins(1);
		m.teamSpeaks = 92;
		q.setWins(1);
		q.teamSpeaks = 90;
		t.teamsList.add(m);
		t.teamsList.add(a);
		t.teamsList.add(b);
		t.teamsList.add(q);
		t.teamsList.add(c);
		t.teamsList.add(d);
		t.setRankings(1);

	}

}
