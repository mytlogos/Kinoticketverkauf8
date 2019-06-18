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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BezahlungsWerkzeugUI {

	public boolean bezahlungsFenster(final int preis) 
	{
		final JTextField eingabe = new JTextField();
		final JTextField restLabel = new JTextField("da");
		final JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(ok);
            }
        });
		ok.setEnabled(false);
		eingabe.getDocument().addDocumentListener(new DocumentListener(){
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
			  public void act(DocumentEvent e)
			  {
				  try{
					  int rest = Integer.parseInt(eingabe.getText()) - preis;
				  
					  restLabel.setText("" + rest);
					  
					  ok.setEnabled(rest >= 0);
				  }
				  catch(Exception s)
				  {
					  restLabel.setText("lol bis du dum");
					  ok.setEnabled(false);
				  }
			  }
			});
		

        final JButton cancel = new JButton("nich ok");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancel);
            }
        });
        
		int optionClicked = JOptionPane.showOptionDialog(null, 
				panel(new JComponent[] {
				        new JLabel("Preis"),
				        disable(new JTextField(preis + "")),
				        new JLabel("Bezahlt"),
				        eingabe,
				        new JLabel("Rest"),
				        disable(restLabel)
				}),
				"blah", JOptionPane.OK_CANCEL_OPTION, JOptionPane.NO_OPTION, null, 
				new Object[] {
						ok,
						cancel
				}, null);
		
		return optionClicked == 0;
	}
	
	protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
	
	JPanel panel(JComponent[] components) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		for (JComponent c : components)
			panel.add(c);
		
		return panel;
	}
	
	JTextField disable(JTextField field) {
		field.setEditable(false);
		return field;
	}

	
	
	
}
