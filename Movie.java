import java.io.Serializable;

public class Movie extends Masterpiece implements Serializable {

	private static final long serialVersionUID = 1L;

	int length;
	float rating;

	public Movie(int value, int productID, int length, float rating) {
		super(value, productID);
		this.length = length;
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Length " + length + "m, " + "Rating " + rating;
	}

}
