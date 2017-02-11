package Expres;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Informacje
{
  public static void stworzPopup(String trescWiadomosci, String tytul)
  {
	  String wiadomosc = trescWiadomosci;
	  JOptionPane pane = new JOptionPane();
	  pane.setMessage(new JLabel(wiadomosc, JLabel.CENTER));
	  JDialog d = pane.createDialog(null, tytul);
	  d.setVisible(true);
	  int wybor = pobierzWybor(pane);

	  switch (wybor) {
	  	case JOptionPane.OK_OPTION:
	  		System.out.println(trescWiadomosci + ": Potwierdzenie");
	  		break;
	  	case JOptionPane.CANCEL_OPTION:
	  		System.out.println(trescWiadomosci + ": Zaprzeczenie");
	  		break;
	  	default:
	  		System.out.println(trescWiadomosci + ": Inne");
	  }
  }

  private static int pobierzWybor(JOptionPane wybranaOpcja)
  {
	  int zwracanaWartosc = JOptionPane.CLOSED_OPTION;

	  Object wybranaWartosc = wybranaOpcja.getValue();
	  if (wybranaWartosc != null) {
		  Object opcje[] = wybranaOpcja.getOptions();
		  if (opcje == null) {
			  if (wybranaWartosc instanceof Integer) {
				  zwracanaWartosc = ((Integer) wybranaWartosc).intValue();
			  }
		  } 
		  else 
		  {
			  for (int i = 0, n = opcje.length; i < n; i++)
			  {
				  if (opcje[i].equals(wybranaWartosc)) {
					  zwracanaWartosc = i;
					  break;
				  }
			  }
		  }
	  }
	  return zwracanaWartosc;
	  }
}