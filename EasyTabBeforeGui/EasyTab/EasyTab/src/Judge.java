
public class Judge {
	private int rating;
	private boolean isBinned;

	public Judge(int rating, boolean binned) {
		setRating(rating);
		setBinned(binned);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isBinned() {
		return isBinned;
	}

	public void setBinned(boolean isBinned) {
		this.isBinned = isBinned;
	}
}
