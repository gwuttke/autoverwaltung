package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import dao.AutoDAO;
import dao.BenzinDAO;
import database.Procedures;
import domain.Auto;
import domain.Benzinart;

public class Test2Form {
	final static BenzinDAO ben = new BenzinDAO();
	final static AutoDAO aut = new AutoDAO();
	final static Test2Form tf = new Test2Form();
	final static Procedures proc = new Procedures();

	final static JFrame frame = new JFrame();
	final static JComboBox<String> auto = new JComboBox<String>();
	final static JComboBox<String> benzin = new JComboBox<String>();
	final static JTextField text1 = new JTextField();
	final static JTextField text2 = new JTextField();



	public static JComboBox<String> getBenzinCombo() {
		return benzin;
	}

	private void allAktuallisieren() {
		try {
			aut.setAutoList();
			tf.comboboxFill("Auto");
			// ben.setAutoBenzinList();
			// tf.comboboxFill("Benzin");

			//ben.setBenzinList();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JComboBox<String> getAutoCombo() {
		return auto;
	}

	private void autoAktuallisieren(){
		try {
			aut.setAutoList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void benzinAktuallisieren() {
		try {
			ben.benzinList.clear();
			//auto.removeAllItems();
			aut.setAutoList();
			tf.comboboxFill("Benzin");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void comboboxFill(String funktion) {

		if (funktion == "Benzin") {
			benzin.removeAllItems();
			for (Auto a : aut.autos) {
				for (String ba : a.getBenzinArten()) {
					//getBenzinCombo.addItem(ba);
				}
			}
			// for (Benzinart b : ben.autoBenzinList)
			// combo1.addItem(b.getName());
		} else if (funktion == "Auto") {
			auto.removeAllItems();
			for (Auto a : aut.autos)
				getAutoCombo().addItem(a.getKfz());
		}
	}

	public static void main(String[] args) {

		tf.allAktuallisieren();

		tf.getAutoCombo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings({ "unchecked" })
				JComboBox<String> selectedChoice = (JComboBox<String>) e
						.getSource();

				tf.benzinAktuallisieren();
				// text2.setText(selectedChoice.getSelectedItem().toString());
				System.out.println(proc.getAutoAlter());
			}
		});

		frame.add(auto, BorderLayout.WEST);
		frame.add(getBenzinCombo(), BorderLayout.EAST);

		frame.pack();
		frame.setVisible(true);

	}

}
