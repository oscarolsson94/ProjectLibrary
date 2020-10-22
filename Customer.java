import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	String name;
	String cellNumber;

	public Customer(String name, String cellNumber) {

		this.name = name;
		this.cellNumber = cellNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	@Override
	public String toString() {
		return "Customer: " + name + ", Phone number: " + cellNumber;
	}

}
