package Expres;

import java.util.ArrayList;
import java.util.List;

public class ObslugaKomponentow{
	private float iloscWybranegoSkladnika;
	private Przepis listaProduktow;
	private List<IPojemnik> pojemniki = new ArrayList<IPojemnik>();
	
	private void zainicjalizujWybrany(ISkladnik szukanySkladnik){
		if ((szukanySkladnik instanceof SkladnikCiekly)){
			PojemnikSkladnikowCieklych pojemnikNaWybranySkladnik = new PojemnikSkladnikowCieklych(
					iloscWybranegoSkladnika, szukanySkladnik.podajNazwe());
			dodajPojemnik(pojemnikNaWybranySkladnik);
		}
		else{
			PojemnikSkladnikowSypkich pojemnikNaWybranySkladnik = new PojemnikSkladnikowSypkich(
					iloscWybranegoSkladnika, szukanySkladnik.podajNazwe());
			dodajPojemnik(pojemnikNaWybranySkladnik);
		}	
	}
	
	public void zainicjalizujKomponenty()
	{
		if(!czyKomponentNaLiscie(listaProduktow.podajSkladnikGlowny())){
			zainicjalizujWybrany(listaProduktow.podajSkladnikGlowny());
		}
		for(ISkladnik szukanySkladnik : listaProduktow.podajListeDodatkow()){
			if(!czyKomponentNaLiscie(szukanySkladnik))
			{
				zainicjalizujWybrany(szukanySkladnik);
			}
		}	
	}
	
	public void ustawPojemnoscPojemnikow(float pojemnosc){
		this.iloscWybranegoSkladnika = pojemnosc;
	}
	
	public void ustawZadanie(Przepis listaProduktow){
		this.listaProduktow = listaProduktow;
	}

	private void dodajPojemnik(IPojemnik komponent){
		pojemniki.add(komponent);	
	}

	public void usunPojemnik(IPojemnik komponent){
		pojemniki.remove(komponent);	
	}
	
	private boolean czyKomponentNaLiscie(ISkladnik skladnikSzukany){
		for (IPojemnik szukanyPojemnik : pojemniki){
			if(szukanyPojemnik.podajNazweSkladnika() == skladnikSzukany.podajNazwe()){
				return true;
			}
		}
		return false;
	}
	
	public List<IPojemnik> podajPojemniki(){
		return pojemniki;
	}
}
