public class Book extends Masterpiece {

	
	int numberOfPages;
	String publisher;
	
	public Book(int value, int productID, int numberOfPages, String publisher) {
		super(value, productID);
		this.numberOfPages = numberOfPages;
		this.publisher = publisher;
	}	

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Pages " + numberOfPages + ", Publisher " + publisher;
	}

}
