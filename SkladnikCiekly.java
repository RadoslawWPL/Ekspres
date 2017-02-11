package Expres;

public class SkladnikCiekly implements ISkladnik{
	private String nazwaSkladnika;
	private float wymaganeCisnienie;
	private float wymaganaTemperatura;
	private float wymaganaIlosc;
	
	public String podajNazwe(){
		return this.nazwaSkladnika;
	}
	
	public float wymogSkladnika(){
		return this.wymaganeCisnienie;
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
	
	public SkladnikCiekly(String podanaNazwaSkladnika, float podanaWymaganaTemperatura,
			float podanaWymaganeCisnienie, float wymaganaIlosc){
		this.nazwaSkladnika = podanaNazwaSkladnika;
		this.wymaganaTemperatura = podanaWymaganaTemperatura;
		this.wymaganeCisnienie = podanaWymaganeCisnienie;
		this.wymaganaIlosc = wymaganaIlosc;
	}
}
