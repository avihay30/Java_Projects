package shop;

// class that is an Instrument and represent a Piano with some octaves
public class Piano extends Instrument {
	private final int octaves;
	
	public Piano(String company, int price, int octaves) {
		super(company, price);
		this.octaves = octaves;
	}
	
	public int getOctaves() {
		return octaves;
	}
	
	public String toString() {
		return String.format("Piano(%d octaves)", octaves) + CompanyPriceString();
	}
	
	public static void main(String[] args) {
//		Piano piano = new Piano("ccc", 45, 2);
//		Guitar guitar = new Guitar("gui", 45, Type.CLASSICAL);
//		
//		System.out.println(piano);
//		System.out.println(guitar);
		
		Shop s = new Shop();
		s.add(new Guitar("Gibson", 1000, Type.ACOUSTIC));
		s.add(new Piano("Yamaha", 5000, 6));
		s.add(new Piano("Yamaha", 10000, 7));
		s.add(new Guitar("Fender", 4000, Type.ELECTRIC));

		System.out.println(s.allSerials());
		System.out.println(s.sellAll(new int[] {1, 3, 5, 0}));
		System.out.println(s.allSerials());
	}
}
