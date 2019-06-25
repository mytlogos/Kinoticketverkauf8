package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BezahlungsWerkzeug {

	private BezahlungsWerkzeugUI _ui;
	private int _preis;
	
	public BezahlungsWerkzeug()
	{
		_ui = new BezahlungsWerkzeugUI();
		initListener();
	}
	
	
	private void initListener() {
		this._ui.onBezahltAenderung(new DocumentListener(){
			  public void changedUpdate(DocumentEvent e) 
			  {
				  act(e);
			  }
			  public void removeUpdate(DocumentEvent e) 
			  {
				  act(e);
			  }
			  public void insertUpdate(DocumentEvent e) 
			  {
				  act(e);
			  }
			  private void act(DocumentEvent e)
			  {
				  try{
					  String bezahltEingabe = _ui.getBezahltText();
					  int bezahlt = bezahltEingabe.isEmpty() ? 0: Integer.parseInt(bezahltEingabe);
					  int rest = bezahlt - _preis;
				 
					  
					  _ui.setRestText("" + rest);
					  
					  _ui.enableOkButton(rest >= 0);
				  }
				  catch(Exception s)
				  {
					  _ui.setRestText("Ungültige Eingabe");
					  _ui.enableOkButton(false);
				  }
			  }
			});
	}
	
	


	public boolean bezahleErfolgreich(int preis)
	{
		_preis = preis;
		return _ui.bezahlungsFenster(preis);
	}
}
