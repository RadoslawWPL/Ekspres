package Expres;

import java.util.concurrent.TimeUnit;

public class Pompa extends Komponent implements IPompa{
	private float cisnienie;
	private Glowica glowica;
	private ISkladnik ciecz;
	private int czas = 2;
	private String nazwaKomponentu = "Pompa";
	
	public void tlocz(ISkladnik cieczDoPrzetloczenia) {
		System.out.println("Rozpoczecie tloczenia: " + cieczDoPrzetloczenia.podajNazwe());
		this.ciecz = cieczDoPrzetloczenia;
		float iloscCieczy = cieczDoPrzetloczenia.podajWymaganaIlosc();
		for (float cieczPrzetloczona = 0; cieczPrzetloczona < iloscCieczy; cieczPrzetloczona++){
			try {
				TimeUnit.SECONDS.sleep(czas);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}	
			this.cisnienie++;
			System.out.println("Przetloczona ciecz: " + cieczPrzetloczona);
			glowica.wlej(this.ciecz, cieczPrzetloczona);
			System.out.println("Aktualne cisnienie tloczonej cieczy: " + this.cisnienie);
			regulujCisnienie();
		}
		System.out.println("Zakonczenie tloczenia: " + cieczDoPrzetloczenia.podajNazwe());
	}

	private void regulujCisnienie(){		
		while ((int)Math.round(this.cisnienie) != ciecz.wymogSkladnika()){
			try {
				TimeUnit.SECONDS.sleep(czas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.cisnienie < ciecz.wymogSkladnika()){
				this.cisnienie++;
			}
			else {
				this.cisnienie--;
			}
			System.out.println("Aktualne cisnienie cieczy w pompie: " + this.cisnienie);
		}
		System.out.println("Aktualne cisnienie cieczy jest prawidlowe.");
	}

	public String podajNazwe() {
		return nazwaKomponentu;
	}
	
	public Pompa(Glowica glowica){
		this.glowica = glowica;
		this.cisnienie = 0;
	}
}
