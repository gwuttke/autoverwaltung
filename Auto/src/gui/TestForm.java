package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import database.SqlServer;


public class TestForm {
	//Vererbung von Klassen
	final static JFrame frame = new JFrame();
	final static Test t = new Test();
	final static SqlServer sqlS = new SqlServer();
	final static TestForm tf = new TestForm();
	//Komponente hinzufügen
	final static JComboBox<String> combo1 = new JComboBox<String>();
	final static JComboBox<String> combo2 = new JComboBox<String>();
	final static JTextField text1 = new JTextField();
	final static JTextField text2 = new JTextField();

	private boolean comboboxFill(String sql, String funktion)
			throws SQLException {
		try {
			sqlS.openConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = sqlS.retrieveRS(sql);
		} catch (Exception e) {
			return false;
		}

		if (funktion == "Benzinart") {
			while (rs.next()) {
				combo1.addItem(rs.getString("Benzienart"));
			}
		} else if (funktion == "Auto") {
			while (rs.next()) {
				combo2.addItem(rs.getString("Kennzeichen"));
			}
		}

		sqlS.closeResults(null, rs, sqlS.getConn());

		return true;

	}

	public static void main(String[] args) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			tf.comboboxFill("SELECT Kennzeichen FROM dbo.T_Auto", "Auto");
			tf.comboboxFill("SELECT Benzienart FROM dbo.T_Benzinart",
					"Benzinart");
			
		} catch (SQLException  e) {

			e.printStackTrace();
		}

		combo1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> selectedChoice = (JComboBox<String>) e
						.getSource();
				text1.setText(selectedChoice.getSelectedItem().toString());
			}
		});

		combo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> selectedChoice = (JComboBox<String>) e
						.getSource();
				text2.setText(selectedChoice.getSelectedItem().toString());
			}
		});

		// Komponentengröße Festlegen
		// text1.setSize(100, 100);

		// Komponente dem Frame Hinzufügen
		frame.add(combo1, BorderLayout.WEST);
		frame.add(combo2, BorderLayout.EAST);
		frame.add(text2, BorderLayout.CENTER);
		frame.add(text1, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);

	}

}
