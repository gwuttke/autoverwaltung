package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigInteger;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import dao.AutoDAO;

public class addAuto {

	// Klassen
	final static AutoDAO autoDao = new AutoDAO();

	// Komponenten
	final static JFrame frame = new JFrame();
	final static JList<String> ALL_AUTO_LIST = new JList<String>();
	final static JTextField KFZ_ZEICHEN_TEXT_FIELD = new JTextField();
	static JSpinner KM_STAND_SPINNER = new JSpinner(
			new SpinnerNumberModel());
	final static JSpinner KFZ_ERST_ZULASSUNG_SPINNER = new JSpinner(
			new SpinnerDateModel());
	final static JSpinner KFZ_KAUF_SPINNER = new JSpinner(new SpinnerDateModel());
	final static JButton NEW_AUTO_BUTTON = new JButton("neues Auto");
	final static JLabel KFZ_LABEL_LABEL = new JLabel("Kennzeichen:");
	final static JLabel KM_LABEL = new JLabel("Km-Stand:");
	final static JLabel KFZ_ERST_LABEL = new JLabel("Erst Zulassung:");
	final static JLabel KFZ_KAUF_LABEL = new JLabel("Kaufdatum:");

	private static JSpinner getKmSpinner() {
		double min = 0.000;
		double value = 10000.000;
		double max = 999999.900;
		double stepSize = 0.100;
		SpinnerNumberModel model = new SpinnerNumberModel(value, min, max,
				stepSize);
		KM_STAND_SPINNER = new JSpinner(model);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor) KM_STAND_SPINNER
				.getEditor();
		DecimalFormat format = editor.getFormat();
		format.setMinimumFractionDigits(3);
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = KM_STAND_SPINNER.getPreferredSize();
		d.width = 85;
		KM_STAND_SPINNER.setPreferredSize(d);
		JPanel panel = new JPanel();
		panel.add(KM_STAND_SPINNER);
		return KM_STAND_SPINNER;
	}

	public static void main(String[] args) {

		frame.add(KFZ_LABEL_LABEL, BorderLayout.WEST);
		frame.add(KFZ_ZEICHEN_TEXT_FIELD, BorderLayout.EAST);
		frame.add(getKmSpinner(), BorderLayout.EAST);
		frame.add(KM_LABEL, BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);
	}

}
