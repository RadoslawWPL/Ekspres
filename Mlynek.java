package Expres;

import java.util.concurrent.TimeUnit;

public class Mlynek extends Komponent implements IMlynek{

	private Glowica glowica;
	private String nazwaKomponentu = "Mlynek";
	private int czasMielenia = 2;
	
	public void miel(ISkladnik skladnikDoPrzemielenia) {
		if(skladnikDoPrzemielenia.wymogSkladnika() <= 0){
			System.out.println("Skladnik nie wymaga mielenia.");
			return;
		}
		System.out.println("Mielenie.");
		for (int mielenie = 1; mielenie < skladnikDoPrzemielenia.wymogSkladnika(); mielenie++){
			try {
				TimeUnit.SECONDS.sleep(czasMielenia);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("Mielenie: " + (mielenie/skladnikDoPrzemielenia.wymogSkladnika()) *100 + "(%)");
		}
		System.out.println("Mielenie zakonczone.");
	}

	public void przesypuj(ISkladnik nazwaSubstancjiSypkiej) {
		System.out.println("Przesypywanie do glowicy przemielonego skladnika.");	
		glowica.wsyp(nazwaSubstancjiSypkiej);
	}

	public Mlynek(Glowica glowica){
		this.glowica = glowica;
	}

	public String podajNazwe() {
		return this.nazwaKomponentu;
	}
}
