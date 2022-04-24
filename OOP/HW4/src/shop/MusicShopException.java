package shop;

// Exception for errors that accrues during
// sell of Instruments in some Music shop.
public class MusicShopException extends Exception {
	public MusicShopException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;
}
