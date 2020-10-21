public class Book extends Masterpiece {

	int numberOfPages;
	String publisher;
	
	public Book(int value, int productID, int numberOfPages, String publisher) {
		super(value, productID);
		this.numberOfPages = numberOfPages;
		this.publisher = publisher;
	}	

}
