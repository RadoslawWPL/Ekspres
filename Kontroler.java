package Expres;

import java.util.ArrayList;
import java.util.List;

public class Kontroler implements IKontroler{

	private List<Komponent> komponenty = new ArrayList<Komponent>();
	private Przepis aktualneZadanie;	
	private float pojemnoscKubka = 100;
	private List<IPojemnik> pojemniki = new ArrayList<IPojemnik>();
	
	public void ustawListePojemnikow(List<IPojemnik> pojemniki){
		this.pojemniki = pojemniki;
	}
		
	private void pobierzWymaganeSkladniki(){
		float ilosc;
		Informacje.stworzPopup("Pobieranie wymaganych skladnikow.", "Informacje");
		for (IPojemnik szukanyPojemnik : pojemniki){
			if (szukanyPojemnik.podajNazweSkladnika() == aktualneZadanie.podajSkladnikGlowny().podajNazwe()){
				ilosc = szukanyPojemnik.podajDostepnaIlosc();
				szukanyPojemnik.ustawIlosc(ilosc-aktualneZadanie.podajSkladnikGlowny().podajWymaganaIlosc());
			}
			else{
				for(ISkladnik szukanySkladnik : aktualneZadanie.podajListeDodatkow()){
					if (szukanyPojemnik.podajNazweSkladnika() == szukanySkladnik.podajNazwe()){
						ilosc = szukanyPojemnik.podajDostepnaIlosc();
						szukanyPojemnik.ustawIlosc(ilosc-szukanySkladnik.podajWymaganaIlosc());
					}	
				}		
			}	
		}
	}
	
	public void zrealizujPrzepis(){
		pobierzWymaganeSkladniki();
		Informacje.stworzPopup("Rozpocznij proces parzenia " 
				+ aktualneZadanie.podajSkladnikGlowny().podajNazwe() + ".", "Informacje");
		Glowica glowica = new Glowica();
		dodajKomponent(glowica);
		Pompa pompa = new Pompa(glowica);
		dodajKomponent(pompa);
		Grzalka grzalka = new Grzalka();
		dodajKomponent(grzalka);
		Mlynek mlynek = new Mlynek(glowica);
		dodajKomponent(mlynek);
		if (aktualneZadanie.podajSkladnikGlowny() instanceof SkladnikSypki){
			mlynek.miel(aktualneZadanie.podajSkladnikGlowny());
			mlynek.przesypuj(aktualneZadanie.podajSkladnikGlowny());
		}
		grzalka.podgrzejDoWymaganejTemperatury(aktualneZadanie.podajSkladnikGlowny());
		if (aktualneZadanie.podajSkladnikGlowny() instanceof SkladnikCiekly){
			pompa.tlocz(aktualneZadanie.podajSkladnikGlowny());
		}
		if (aktualneZadanie.podajSkladnikGlowny() instanceof SkladnikSypki){
			glowica.wsyp(aktualneZadanie.podajSkladnikGlowny());
		}
		for(ISkladnik szukanySkladnik : aktualneZadanie.podajListeDodatkow()){
			grzalka.podgrzejDoWymaganejTemperatury(szukanySkladnik);
			if (szukanySkladnik instanceof SkladnikCiekly){
				pompa.tlocz(szukanySkladnik);
			}
		}
		ISkladnik produktKoncowy = new SkladnikSypki(aktualneZadanie.podajSkladnikGlowny().podajNazwe(),
				pojemnoscKubka, aktualneZadanie.podajSkladnikGlowny().podajWymaganaTemperatura(),
				aktualneZadanie.podajSkladnikGlowny().wymogSkladnika());
		glowica.zalej(produktKoncowy);
	}

	public void dodajKomponent(Komponent komponent){
		komponenty.add(komponent);	
	}
	
	public void usunKomponent(String nazwaKomponentu){
		int iterator;
		boolean czyZnaleziono = false;
		for (iterator=0; iterator < komponenty.size(); iterator++){
			if (komponenty.get(iterator).podajNazwe() == nazwaKomponentu){
				komponenty.remove(iterator);
				czyZnaleziono = true;
				break;
			}	
		}	
		if(!czyZnaleziono){
			Informacje.stworzPopup("Nie ma takiego komponentu!", "Informacje");
		}		
	}
	
	public void dodajNoweZadanie(Przepis przepis){
		this.aktualneZadanie = przepis;
	}
	
	public float pobierzPojemnoscKubka(){
		return pojemnoscKubka;
	}	 
	
	public List<Komponent> dajListeKomponentow(){
		return komponenty;
	}
}