package Expres;

public class PojemnikSkladnikowCieklych implements IPojemnikSkladnikowCieklych{

	private float ilosc;
	private String nazwaSkladnikaCieklego;
	private String nazwaKomponentu;
	
	
	public void ustawIlosc(float ilosc){
		try{
			ilosc = Math.abs(ilosc);
			this.ilosc = ilosc;
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Blednie podana ilosc!");
		}		
	}
	
	private void ustawNazweSkladnika(String nazwaSubstancjiCieklej){
		if(nazwaSubstancjiCieklej != null && !nazwaSubstancjiCieklej.isEmpty()){
			this.nazwaSkladnikaCieklego = nazwaSubstancjiCieklej;
		}
		else{
			throw new IllegalArgumentException("Pusta nazwa skladnika!");
		}	
	}

	public float podajDostepnaIlosc(){
		return this.ilosc;
	}

	public void wezOdpowiedniaIlosc(float ilosc){
		try{
			ilosc = Math.abs(ilosc);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Blednie podana ilosc!");
		}
		float iloscSubstancjiCieklejWPojemniku;
		iloscSubstancjiCieklejWPojemniku = podajDostepnaIlosc();
		if (iloscSubstancjiCieklejWPojemniku > ilosc || iloscSubstancjiCieklejWPojemniku == ilosc){
			ustawIlosc(iloscSubstancjiCieklejWPojemniku-ilosc);
		}
		else{
			Informacje.stworzPopup("Za malo substancji cieklej w pojemniku!", "Informacje");
		}
	}

	public String podajNazweSkladnika(){
		return this.nazwaSkladnikaCieklego;
	}
	
	public PojemnikSkladnikowCieklych(float iloscPodana, String podanaNazwaSkladnika){
		ustawNazweSkladnika(podanaNazwaSkladnika);
		ustawIlosc(iloscPodana);
		this.nazwaKomponentu = "pojemnikNa" + podanaNazwaSkladnika;
	}

	public String podajNazwe(){
		return this.nazwaKomponentu;
	}
}
