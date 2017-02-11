
package Expres;

public class PojemnikSkladnikowSypkich implements IPojemnikSkladnikowSypkich {

	private float ilosc;
	private String nazwaSkladnikaSypkiego;
	private String nazwaKomponentu;
	
	public void ustawIlosc(float ilosc) {
		try{
			ilosc = Math.abs(ilosc);
			this.ilosc = ilosc;
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Blednie podana ilosc!");
		}		
	}

	private void ustawNazweSkladnika(String nazwaSubstancjiSypkiej){
		if(nazwaSubstancjiSypkiej != null && !nazwaSubstancjiSypkiej.isEmpty()){
			this.nazwaSkladnikaSypkiego = nazwaSubstancjiSypkiej;
		}
		else{
			throw new IllegalArgumentException("Pusta nazwa skladnika!");
		}	
	}
	
	public String podajNazweSkladnika(){
		return this.nazwaSkladnikaSypkiego;
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
		float ziarenkaWPojemniku;
		ziarenkaWPojemniku = podajDostepnaIlosc();
		if (ziarenkaWPojemniku > ilosc || ziarenkaWPojemniku == ilosc){
			ustawIlosc(ziarenkaWPojemniku-ilosc);
		}
		else{
			Informacje.stworzPopup("Za malo ziaren w pojemniku!", "Informacje");
		}
	}
	
	public PojemnikSkladnikowSypkich(float iloscPodana, String podanaNazwaSkladnika){
		ustawNazweSkladnika(podanaNazwaSkladnika);
		ustawIlosc(iloscPodana);
		this.nazwaKomponentu = "pojemnikNa" + podanaNazwaSkladnika;
	}

	public String podajNazwe(){
		return this.nazwaKomponentu;
	}
}
