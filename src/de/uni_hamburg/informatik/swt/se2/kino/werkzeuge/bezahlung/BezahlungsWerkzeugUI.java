package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahlung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

class BezahlungsWerkzeugUI {
	private final JTextField _eingabe = new JTextField();
	private final JTextField _restLabel = new JTextField();
	private final JButton _ok = new JButton("Ok");
	private final JButton _cancel = new JButton("Abbrechen");
	
	BezahlungsWerkzeugUI() {
		init();
	}
	
	private void init() {
		_ok.setEnabled(false);
		_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(_cancel);
            }
        });
		_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(_ok);
            }
        });
	}
	
	void enableOkButton(boolean enable) {
		_ok.setEnabled(enable);
	}
	
	void setRestText(String text) {
		_restLabel.setText(text);
	}
	
	String getBezahltText() {
		return _eingabe.getText();
	}

	boolean bezahlungsFenster(final int preis) 
	{
		_restLabel.setText("" + -preis);
		int optionClicked = JOptionPane.showOptionDialog(null, 
				panel(new JComponent[] {
				        new JLabel("Preis"),
				        disable(new JTextField(preis + "")),
				        new JLabel("Bezahlt"),
				        _eingabe,
				        new JLabel("Rest"),
				        disable(_restLabel)
				}),
				"Platz Bezahlung", JOptionPane.OK_CANCEL_OPTION, JOptionPane.NO_OPTION, null, 
				new Object[] {
						_ok,
						_cancel
				}, null);
		return optionClicked == 0;
	}
	
	private JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
	
	private JPanel panel(JComponent[] components) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		for (JComponent c : components)
			panel.add(c);
		
		return panel;
	}
	
	private JTextField disable(JTextField field) {
		field.setEditable(false);
		return field;
	}

	void onBezahltAenderung(DocumentListener documentListener) {
		_eingabe.getDocument().addDocumentListener(documentListener);
	}

	
	
	
}
