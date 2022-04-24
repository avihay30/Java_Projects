package shop;

import java.util.ArrayList;
import java.util.List;

// class that represent an Instrument shop,
// and holds a list of all instruments in this shop
public class Shop {
	private List<Instrument> instruments = new ArrayList<>();

	/**
	 * @param i the instrument to add to shop
	 */
	public void add(Instrument i) {
		instruments.add(i); // add() in ArrayList by-default adds to the end
	}

	/**
	 * @param serial the serial of some instrument
	 * @return the appropriate Instrument that holds
	 * the specified serial if exists, else returns null
	 */
	public Instrument get(int serial) {
		for (Instrument instrument : instruments) {
			if (instrument.getSerial() == serial) return instrument;
		}
		// logical else
		return null;
	}

	/**
	 * @return list of all instruments serials in this shop
	 */
	public List<Integer> allSerials() {
		List<Integer> serials = new ArrayList<>();
		for (Instrument instrument : instruments)
			serials.add(instrument.getSerial());
		
		return serials;
	}

	/**
	 * @param t Type of guitar to search for
	 * @return list of all instruments serials
	 * that are guitars of the specified Type t
	 */
	public List<Integer> guitarsOfType(Type t) {
		List<Integer> guitars = new ArrayList<>();
		for (Instrument instrument : instruments) {
			if (instrument instanceof Guitar) {
				if (((Guitar) instrument).getType().equals(t))
					guitars.add(instrument.getSerial());
			}
				
		}
		
		return guitars;
	}

	/**
	 * @param serial the instrument's serial to remove from shop list
	 * @throws MusicShopException
	 * if the is no such instrument in shop or
	 * if the instrument to sell is a Guitar and there is only one guitar in shop
	 */
	public void sell(int serial) throws MusicShopException {
		int numOfGuitars = 0;
		Instrument instrumentToSell = get(serial);
		// if there is not such serial in the shop
		if (instrumentToSell == null) throw new MusicShopException("The given serial doesn't exist in shop");

		// if the instrument for sell is a guitar
		if (instrumentToSell instanceof Guitar) {
			for (Instrument instrument : instruments) { // counting num of guitars
				if (instrument instanceof Guitar) numOfGuitars++;
			}

			if (numOfGuitars == 1)
				// if the instrument for sell is a guitar and there is only one guitar in shop
				throw new MusicShopException("Cannot preform sell, there is only one Guitar in shop");
		}
		// removes the instrument for sell from this shop list
		instruments.remove(instrumentToSell);
	}

	/**
	 * @param serials the instruments serials to remove from shop list
	 * @return amount of instruments that can't be sold
	 */
	public int sellAll(int[] serials) {
		int numOfFailed = 0;
		
		for (int serial : serials) { // iterating over serials
			try {
				sell(serial);
			} catch (MusicShopException e) { // if sell has been failed
				numOfFailed++;
			}
		}
		return numOfFailed;
	}
}
