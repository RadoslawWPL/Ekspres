package Expres;

public class SkladnikSypki implements ISkladnik{	
	private String nazwaSkladnika;
	private float wymaganyCzasMielenia;
	private float wymaganaTemperatura;
	private float wymaganaIlosc;
	
	public String podajNazwe(){
		return this.nazwaSkladnika;
	}
	
	public float wymogSkladnika(){
		return this.wymaganyCzasMielenia;
	}
	
	public float podajWymaganaTemperatura(){
		return this.wymaganaTemperatura;
	}
	
	public float podajWymaganaIlosc(){
		return this.wymaganaIlosc;
	}

	public void ustawIlosc(float ilosc) {
		this.wymaganaIlosc = ilosc;
	}
	
	public SkladnikSypki(String podanaNazwaSkladnika, float podanaWymaganaTemperatura,
			float podajWymaganyCzasMielenia, float wymaganaIlosc){
		this.nazwaSkladnika = podanaNazwaSkladnika;
		this.wymaganaTemperatura = podanaWymaganaTemperatura;
		this.wymaganyCzasMielenia = podajWymaganyCzasMielenia;
		this.wymaganaIlosc = wymaganaIlosc;
	}
}