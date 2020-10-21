public class Movie extends Masterpiece {

	int length;
	float rating;
	
	public Movie(int value, int productID, int length, float rating) {
		super(value, productID);
		this.length = length;
		this.rating = rating;
	}



}
