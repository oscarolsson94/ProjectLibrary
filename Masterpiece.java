import java.io.Serializable;

public class Masterpiece implements Serializable {

	private static final long serialVersionUID = 1L;

	public int value;
	public int productID;

	public Masterpiece(int value, int productID) {

		this.value = value;
		this.productID = productID;
	}

}
