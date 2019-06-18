package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

public class BezahlungsWerkzeug {

	private BezahlungsWerkzeugUI _ui;
	
	public BezahlungsWerkzeug()
	{
		_ui = new BezahlungsWerkzeugUI();
	}
	
	public boolean bezahle(int preis)
	{
		
		return _ui.bezahlungsFenster(preis);
	}
}
