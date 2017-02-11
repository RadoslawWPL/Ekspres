package Expres;

public class StartProgramu {
	public static void main(String[] args){
		Informacje.stworzPopup("Uruchamianie programu.", "Informacje");
		PanelDotykowy panel = new PanelDotykowy();
		//panel.gotoweSkladniki();
		panel.menuDodawaniaSkladnikow(false);
		panel.menuDodawaniaSkladnikow(true);
		panel.stworzPrzepis();
	}
}
