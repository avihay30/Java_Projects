package shop;

// class that represent some music Instrument
// TODO: why this is abstract??? - for not initializing it??
public abstract class Instrument {
	private final String company;
	private final int price;

	private final int serial; // holds the Instrument's serial number

	// holds the next unused serial number to allocate
	private static int currSerial;
	
	public Instrument(String company, int price) {
		this.company = company;
		this.price = price;
		this.serial = currSerial++;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getCompany() {
		return company;
	}
	
	public int getSerial() {
		return serial;
	}

	/**
	 * @return String representation of the company and price
	 * of this instrument (e.g " Yamaha(1), price = 5000")
	 */
	protected String CompanyPriceString() {
		return String.format(" %s(%d), price = %d", company, serial, price);
	}
}
