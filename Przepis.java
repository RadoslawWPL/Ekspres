package Expres;

import java.util.ArrayList;
import java.util.List;

public class Przepis{
	private List<ISkladnik> dodatki = new ArrayList<ISkladnik>();
	private ISkladnik skladnikGlowny;
	
	public void dodajDoPrzepisu(ISkladnik skladnik){
		dodatki.add(skladnik);
	}
	
	public ISkladnik podajSkladnikGlowny(){
		return this.skladnikGlowny;
	}
	
	public List<ISkladnik> podajListeDodatkow(){
		return this.dodatki;
	}
	
	public Przepis(ISkladnik wybranySkladnik){	
		this.skladnikGlowny = wybranySkladnik;
	}
}