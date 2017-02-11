package Expres;

public interface ISkladnik {
	public String podajNazwe();	
	public float wymogSkladnika();
	public float podajWymaganaTemperatura();	
	public float podajWymaganaIlosc();
	public void ustawIlosc(float ilosc);
}
