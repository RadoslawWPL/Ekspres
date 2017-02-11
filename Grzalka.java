package Expres;

import java.util.concurrent.TimeUnit;

public class Grzalka extends Komponent implements IGrzalka{
	
	private int czasGrzania = 2;
	private float temperaturaWStanieSpoczynku = 21.1f;
	
	private float aktualnaTemperatura;
	private String nazwaKomponentu = "Grzalka";


	public float podajTemperature() 
	{
		return aktualnaTemperatura;
	}
	
	public String podajNazwe()
	{
		return this.nazwaKomponentu;
	}

	public void podgrzejDoWymaganejTemperatury(ISkladnik skladnikDoPodgrzania) 
	{
		System.out.println("Rozpoczecie podgrzewania: " + skladnikDoPodgrzania.podajNazwe());
		while ((int)Math.round(this.aktualnaTemperatura) != skladnikDoPodgrzania.podajWymaganaTemperatura())
		{
			try 
			{
				TimeUnit.SECONDS.sleep(czasGrzania);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			if (this.aktualnaTemperatura < skladnikDoPodgrzania.podajWymaganaTemperatura())
			{
				this.aktualnaTemperatura++;
			}
			else
			{
				this.aktualnaTemperatura--;
			}
			System.out.println("Aktualna temperatura: " + this.aktualnaTemperatura);
		}
		System.out.println("Podgrzewanie " + skladnikDoPodgrzania.podajNazwe() + " zakonczone sukcesem");
		schladzanie();
	}

	public Grzalka() {
		this.aktualnaTemperatura = temperaturaWStanieSpoczynku;
	}
	
	private void schladzanie()
	{
		this.aktualnaTemperatura = temperaturaWStanieSpoczynku;
	}
}
