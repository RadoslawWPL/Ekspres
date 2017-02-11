package Expres;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PanelDotykowy implements IPanelDotykowy{
	private List<ISkladnik> skladniki = new ArrayList<ISkladnik>();
	private List<ISkladnik> dodatki = new ArrayList<ISkladnik>();
	private ISkladnik wybranySkladnik;
	private ISkladnik wybranyDodatek;
	private ISkladnik stworzonySkladnik;
	private int iloscKubkowWPojemniku = 40;
	
	
	public void gotoweSkladniki(){	
		ISkladnik Nescafe = new SkladnikSypki("Nescafe", 40, 23, 24);
		dodajNowySkladnikDoWyboru(Nescafe);
		ISkladnik Mleko = new SkladnikCiekly("Mleko", 33, 23, 24);
		dodajNowyDodatekDoWyboru(Mleko);
	}
	
	private ISkladnik pobierzSkladnik(List<ISkladnik> wybranaLista){
		JFrame frame = new JFrame();
		String listaPrawidlowa[] = new String[wybranaLista.size()];
		for (int i = 0; i < wybranaLista.size(); i++){
			listaPrawidlowa[i] = wybranaLista.get(i).podajNazwe();
		}
		String nazwaSkladnika = ((String)JOptionPane.showInputDialog(frame, "Co wybierasz", "Panel Dotykowy",
				JOptionPane.QUESTION_MESSAGE, null, listaPrawidlowa, "Skladniki"));	
		return znajdzSkladnikNaPodstawieNazwy(nazwaSkladnika, wybranaLista);
	}
	
	private boolean czyPobranoSkladnik(List<ISkladnik> wybranaLista, boolean czyGlowny, String tekst) {
		if (wybranaLista.isEmpty()){
			Informacje.stworzPopup("Nie ma "+ tekst + " do wyboru.", "Panel Dotykowy");
			return false;
		}
		if(czyGlowny){
			this.wybranySkladnik = pobierzSkladnik(wybranaLista);
		}
		else{
			this.wybranyDodatek = pobierzSkladnik(wybranaLista);
		}
		return true;	
	}
	
	private ISkladnik znajdzSkladnikNaPodstawieNazwy(String Nazwa, List<ISkladnik> wybranaLista){
		String znalezionySkladnik;
		int numerNaLiscie = 0;
		for (int i = 0; i < wybranaLista.size(); i++) {
			znalezionySkladnik = wybranaLista.get(i).podajNazwe();
			if (znalezionySkladnik == Nazwa){
				numerNaLiscie = i;
				break;
			}	
		}
		return wybranaLista.get(numerNaLiscie);
	}
	
	private boolean czyChcesz(String opcjaA, String opcjaB, String tekstDoPopup){
		Object opcje[] = {opcjaA, opcjaB};
		int odpowiedz = JOptionPane.showOptionDialog(null, tekstDoPopup, "Koniec?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcje, opcje[1]);
		if (odpowiedz == 0){
			return true;
		}
		return false;
	}
	
	private boolean czyChceszDodacNowySkladnik(String typSkladnika){
		return czyChcesz("Tak", "Nie", "Chcesz dodac nowy " + typSkladnika + "?");
	}
	
	private boolean chceszJeszczeDodatek(){
		return czyChcesz("Tak", "Nie", "Chcesz jeszcze jakis dodatek?");
	}
	
	private boolean czyPokazacMenuGlowne(){
		return czyChcesz("Tak", "Nie", "Chcesz wybrac napoj?");
	}
	
	private boolean czySkladnikSypki()
	{
		return czyChcesz("Sypki", "Ciekly", "Jakiego typu stworzyc produkt?");
	}
	
	private void okreslIloscDodatku()
	{
		JTextField podanaIlosc = new JTextField();
		Object[] message = {"Ile chcesz " + wybranyDodatek.podajNazwe() + " w kubku?", "Podaj ilosc:", podanaIlosc};
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.CLOSED_OPTION);
		pane.createDialog(null, "Wybor ilosci dodatku").setVisible(true);
		try{
			czyPodanaIloscJestPoprawna(Float.parseFloat(podanaIlosc.getText()));
		}
		catch (NumberFormatException e){
			Informacje.stworzPopup("Blednie podane wartosci.", "Informacje");
		}
	}
	
	private boolean czyPodanaIloscJestPoprawna(float ilosc)
	{
		if(ilosc > 0){
			this.wybranyDodatek.ustawIlosc(ilosc);
			return true;
		}
		Informacje.stworzPopup("Ilosc musi byc wieksza od zera", "Panel Dotykowy");
		return false;
	}
	
	
	public void menuDodawaniaSkladnikow(boolean czyDodatek){
		String typSkladnika;
		if(czyDodatek){
			typSkladnika = "dodatek";
		}
		else{
			typSkladnika = "skladnik";
		}
		while (czyChceszDodacNowySkladnik(typSkladnika)){
			boolean czySypki = czySkladnikSypki();		
			if (czyDodatek && czyUtworzonoNowySkladnik(czySypki)){
				dodajNowyDodatekDoWyboru(stworzonySkladnik);
			}
			else if (!czyDodatek && czyUtworzonoNowySkladnik(czySypki)){
				dodajNowySkladnikDoWyboru(stworzonySkladnik);
			}
		}
	}
	
	private String pobierzWlasciwyTekst(boolean czySypki){
		if(czySypki){
			return "wymagany czas mielenia";
		}
		return "wymagane cisnienie";
	}
	
	private void stworzSkladnikNaPodstawieDanych(JTextField nazwaSkladnika, JTextField wymaganaTemperatura,
			JTextField wymogSkladnika, JTextField wymaganaIlosc, boolean czySypki)
	{
		float temperatura = Float.parseFloat(wymaganaTemperatura.getText());
		float wymog = Float.parseFloat(wymogSkladnika.getText());
		float ilosc = Float.parseFloat(wymaganaIlosc.getText());
		ISkladnik stworzonySkladnik;
		if (czySypki == true){
			stworzonySkladnik = new SkladnikSypki(nazwaSkladnika.getText(), temperatura, wymog, ilosc);
		}
		else{
			stworzonySkladnik = new SkladnikCiekly(nazwaSkladnika.getText(), temperatura, wymog, ilosc);
		}	
    	Informacje.stworzPopup("Dodano nowa opcje wyboru.", "Panel Dotykowy");
		this.stworzonySkladnik = stworzonySkladnik;
	}
	
	private boolean czyWartosciNowegoSkladnikaSaPrawidlowe(JTextField nazwaSkladnika,
			JTextField wymaganaTemperatura, JTextField wymogSkladnika, JTextField wymaganaIlosc){
		if (nazwaSkladnika.getText().isEmpty() == true){
			Informacje.stworzPopup("Nie podano nazwy.", "Informacje");
			return false;
		}
		float wymog; float ilosc;
		try{
			Float.parseFloat(wymaganaTemperatura.getText());
			wymog = Float.parseFloat(wymogSkladnika.getText());
			ilosc = Float.parseFloat(wymaganaIlosc.getText());
		}
		catch (NumberFormatException e){
			Informacje.stworzPopup("Blednie podane wartosci.", "Informacje");
			return false;
		}
		if(wymog < 0 || ilosc <= 0){
			Informacje.stworzPopup("Te wartosci nie moga rowne lub mniejsze od zera.", "Informacje");
			return false;
		}
		return true;
	}
	
	private boolean czyUtworzonoNowySkladnik(boolean czySypki){
		JTextField nazwaSkladnika = new JTextField();
		JTextField wymaganaTemperatura = new JTextField();
		JTextField wymogSkladnika = new JTextField();
		JTextField wymaganaIlosc = new JTextField();
		Object[] message = {"Opisz nowy rodzaj", "Podaj nazwe:",nazwaSkladnika,
				"Podaj wymagana temperature:" ,wymaganaTemperatura,
				"Podaj " + pobierzWlasciwyTekst(czySypki) + ":", wymogSkladnika, 
				"Podaj wymagana ilosc:", wymaganaIlosc};
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.CLOSED_OPTION);
		pane.createDialog(null, "Tworzenie nowej opcji").setVisible(true);	
		if (pane.getValue() == null || czyWartosciNowegoSkladnikaSaPrawidlowe(nazwaSkladnika, 
				wymaganaTemperatura, wymogSkladnika, wymaganaIlosc) == false){
			return false;
    	}
		stworzSkladnikNaPodstawieDanych(nazwaSkladnika, wymaganaTemperatura, wymogSkladnika,
				wymaganaIlosc, czySypki);
		return true;
	}

	private void dodajNowySkladnikDoWyboru(ISkladnik skladnik){
		this.skladniki.add(skladnik);
	}
	
	private void dodajNowyDodatekDoWyboru(ISkladnik skladnik){
		this.dodatki.add(skladnik);
	}
	
	public void stworzPrzepis(){
		while (czyPokazacMenuGlowne()){
			Kontroler kontrol = new Kontroler();		
			if (czyPobranoSkladnik(this.skladniki, true, "produktow") != true){
				break;
			}
			Przepis przepis = new Przepis(this.wybranySkladnik);
			boolean jeszczeDodatki = true;
			while(jeszczeDodatki){
				if (czyPobranoSkladnik(this.dodatki, false, "dodatkow") != true){
					break;
				}
				okreslIloscDodatku();
				przepis.dodajDoPrzepisu(this.wybranyDodatek);
				jeszczeDodatki = chceszJeszczeDodatek();
			}	
			kontrol.dodajNoweZadanie(przepis);
			ObslugaKomponentow obsluga = new ObslugaKomponentow();
			obsluga.ustawZadanie(przepis);
			obsluga.ustawPojemnoscPojemnikow(kontrol.pobierzPojemnoscKubka()*iloscKubkowWPojemniku);
			obsluga.zainicjalizujKomponenty();
			kontrol.ustawListePojemnikow(obsluga.podajPojemniki());
			kontrol.zrealizujPrzepis();	
		}
		Informacje.stworzPopup("Koniec dzialania programu", "Panel Dotykowy");
	}
}
