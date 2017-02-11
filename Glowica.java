package Expres;

public class Glowica extends Komponent {
	private String nazwaKomponentu = "Glowica";
	
	public void wlej (ISkladnik produktCiekly, float ilosc)
	{
		System.out.println("Wlano do glowicy " + produktCiekly.podajNazwe() + " (ilosc: " + ilosc + ")");
	}
	
	public void wsyp (ISkladnik produktSypki)
	{
		System.out.println("Wsypano do glowicy " + produktSypki.podajNazwe() + " (ilosc: " 
				+ produktSypki.podajWymaganaIlosc() + ")");
	}
	
	public void zalej (ISkladnik produktKoncowy)
	{
		System.out.println("Zalano do kubka: " + produktKoncowy.podajNazwe() + " (ilosc: " 
				+ produktKoncowy.podajWymaganaIlosc() + ")");
		return;
	}

	public String podajNazwe() {
		return this.nazwaKomponentu;
	}
}
